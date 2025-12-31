package src;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


// Cell Data Acess Object
public class CellDAO {

	private Connection conn;
	
	// Forms the connection to the database
	public void connect() throws IOException, SQLException {

		// Gets database properties from file
		Properties props = new Properties();
		try(FileInputStream fis = new FileInputStream("config/db.properties.private")) {
			props.load(fis);
		}

		// Creates connection
		this.conn = DriverManager.getConnection(
			props.getProperty("db.url"),
			props.getProperty("db.user"),
			props.getProperty("db.password")
		);
	}
	
	// loads all data from the database into cells / memory
	public void loadAllCells(Cell[][] grid) throws SQLException {
		String sql = "SELECT row_index, col_index, value FROM cells";

		try (
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		) {

			while (rs.next()) {
				int row = rs.getInt("row_index");
				int col = rs.getInt("col_index");
				String data = rs.getString("value");

				grid[col][row].setDataFromDB(data);
			}
		}
	}

	// Updates the database for any dirty/modified cells
	public void saveDirty(Cell[][] cGrid) throws SQLException{
		String sql = """
            INSERT INTO cells (row_index, col_index, value)
			VALUES (?, ?, ?) AS incoming
			ON DUPLICATE KEY UPDATE value = incoming.value;
        """;

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int row = 1; row <= 8; row++) {
                for (int col = 1; col <= 8; col++) {

                    Cell cell = cGrid[col][row];
                    if (!cell.isDirty()) continue;

					// replaces the '?' in stmt with the specified row/col/value
                    stmt.setInt(1, row);
                    stmt.setInt(2, col);
                    stmt.setString(3, cell.getData());
					// System.out.print(stmt);
                    stmt.executeUpdate();

					// Once data is stored from a dirty/modified cell, mark it as clean/not modified
                    cell.clean();
                }
            }
		}
	}

	// Closes the connection to the database
	public void close() throws SQLException {
        if (conn != null) conn.close();
    }

}
