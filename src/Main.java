package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;	// Im not sure why this isnt inculded in swing.* 
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Main {

	// static String grid = 	"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 						"|     |     |     |     |     |     |     |     |\n"+
	// 						"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 						"|     |     |     |     |     |     |     |     |\n"+
	// 						"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 						"|     |     |     |     |     |     |     |     |\n"+
	// 						"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 						"|     |     |     |     |     |     |     |     |\n"+
	// 						"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 						"|     |     |     |     |     |     |     |     |\n"+
	// 						"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 						"|     |     |     |     |     |     |     |     |\n"+
	// 						"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 						"|     |     |     |     |     |     |     |     |\n"+
	// 						"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 						"|     |     |     |     |     |     |     |     |\n"+
	// 						"+-----+-----+-----+-----+-----+-----+-----+-----+\n";


	public static void main(String[] args) throws FileNotFoundException, IOException {

		// Create Cell Grid
		Cell[][] cGrid = new Cell[8][8];
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				cGrid[i][j] = new Cell(i, j);
			}
		}
		String grid = 		"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
							"|"+cGrid[0][0]+"|"+cGrid[1][0]+"|"+cGrid[2][0]+"|"+cGrid[3][0]+"|"+cGrid[4][0]+"|"+cGrid[5][0]+"|"+cGrid[6][0]+"|"+cGrid[7][0]+"|\n"+
							"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
							"|"+cGrid[0][1]+"|"+cGrid[1][1]+"|"+cGrid[2][1]+"|"+cGrid[3][1]+"|"+cGrid[4][1]+"|"+cGrid[5][1]+"|"+cGrid[6][1]+"|"+cGrid[7][1]+"|\n"+
							"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
							"|"+cGrid[0][2]+"|"+cGrid[1][2]+"|"+cGrid[2][2]+"|"+cGrid[3][2]+"|"+cGrid[4][2]+"|"+cGrid[5][2]+"|"+cGrid[6][2]+"|"+cGrid[7][2]+"|\n"+
							"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
							"|"+cGrid[0][3]+"|"+cGrid[1][3]+"|"+cGrid[2][3]+"|"+cGrid[3][3]+"|"+cGrid[4][3]+"|"+cGrid[5][3]+"|"+cGrid[6][3]+"|"+cGrid[7][3]+"|\n"+
							"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
							"|"+cGrid[0][4]+"|"+cGrid[1][4]+"|"+cGrid[2][4]+"|"+cGrid[3][4]+"|"+cGrid[4][4]+"|"+cGrid[5][4]+"|"+cGrid[6][4]+"|"+cGrid[7][4]+"|\n"+
							"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
							"|"+cGrid[0][5]+"|"+cGrid[1][5]+"|"+cGrid[2][5]+"|"+cGrid[3][5]+"|"+cGrid[4][5]+"|"+cGrid[5][5]+"|"+cGrid[6][5]+"|"+cGrid[7][5]+"|\n"+
							"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
							"|"+cGrid[0][6]+"|"+cGrid[1][6]+"|"+cGrid[2][6]+"|"+cGrid[3][6]+"|"+cGrid[4][6]+"|"+cGrid[5][6]+"|"+cGrid[6][6]+"|"+cGrid[7][6]+"|\n"+
							"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
							"|"+cGrid[0][7]+"|"+cGrid[1][7]+"|"+cGrid[2][7]+"|"+cGrid[3][7]+"|"+cGrid[4][7]+"|"+cGrid[5][7]+"|"+cGrid[6][7]+"|"+cGrid[7][7]+"|\n"+
							"+-----+-----+-----+-----+-----+-----+-----+-----+\n";


		SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Mini Spreadsheet");
            frame.setSize(590, 570);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Display area
            JTextArea display = new JTextArea();
            display.setEditable(false);
            display.setBackground(Color.BLACK);			// Background color of text/display area
            display.setForeground(Color.WHITE);			// Frreground color = text color
            display.setCaretColor(Color.WHITE);			// Caret color = color of the blinking line that tells you where you are typing
			display.setMargin(new Insets(10, 10, 10, 10));
            display.setFont(new Font("Monospaced", Font.PLAIN, 18));
			// Removes Scroll Bar Border
			JScrollPane scrollPane = new JScrollPane(display);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			// add(scrollPane, BorderLayout.CENTER);

            // Input field
            JTextField input = new JTextField("Insert Commands Here...");
            input.setBackground(Color.BLACK);
            input.setForeground(Color.WHITE);
            input.setCaretColor(Color.WHITE);
			// input.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
			// input.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
			Border outline = BorderFactory.createLineBorder(Color.WHITE, 2);
			Border margin = BorderFactory.createEmptyBorder(8, 8, 8, 8);
			input.setBorder(BorderFactory.createCompoundBorder(outline, margin));
            input.setFont(new Font("Monospaced", Font.PLAIN, 18));

			// Prompt Text
			input.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					// Clear the text only if it hasn't been changed by the user
					if (input.getText().equals("Insert Commands Here...")) {
						input.setText("");
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					// If the field is empty when focus is lost, restore the prompt
					if (input.getText().isEmpty()) {
						input.setText("Insert Commands Here...");
					}
            	}
			});

			// Handle input
            input.addActionListener(e -> {
                String command = input.getText();
                input.setText("");

                display.append("> " + command + "\n");
            });

			display.append(grid);

            frame.setLayout(new BorderLayout());
            frame.add(new JScrollPane(display), BorderLayout.CENTER);
            frame.add(input, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
	}						
}
