package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


// Cell Data Acess Object
public class CellDAO {

	// Attributes
	private Connection conn;
	
	// Forms the connection to the database
	public void connect() throws IOException, SQLException {

		Properties props = new Properties();
		try(FileInputStream fis = new FileInputStream("config/db.properties.private")) {
			props.load(fis);
		}

		this.conn = DriverManager.getConnection(
			props.getProperty("db.url"),
			props.getProperty("db.user"),
			props.getProperty("db.password")
		);
	}
	
	// loads all data from the database into cells / memory
	public void loadAllCells(Cell[][] grid) throws SQLException {
		String sql = "SELECT row_index, col_index, value FROM cells";

		try (Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {

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

                    stmt.setInt(1, row);
                    stmt.setInt(2, col);
                    stmt.setString(3, cell.getData());
					// System.out.print(stmt);
                    stmt.executeUpdate();
					// In the statment there are ? These are place holders. 1,2,3 tells hava which question mark to replace with that value

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
