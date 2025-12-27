package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;	// Im not sure why this isnt inculded in swing.* 
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Main {
	static Cell[][] cGrid = new Cell[9][9];
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
	
	// String grid = 	"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 				"|"+cGrid[1][1]+"|"+cGrid[2][1]+"|"+cGrid[3][1]+"|"+cGrid[4][1]+"|"+cGrid[5][1]+"|"+cGrid[6][1]+"|"+cGrid[7][1]+"|"+cGrid[8][1]+"|\n"+
	// 				"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 				"|"+cGrid[1][2]+"|"+cGrid[2][2]+"|"+cGrid[3][2]+"|"+cGrid[4][2]+"|"+cGrid[5][2]+"|"+cGrid[6][2]+"|"+cGrid[7][2]+"|"+cGrid[8][2]+"|\n"+
	// 				"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 				"|"+cGrid[1][3]+"|"+cGrid[2][3]+"|"+cGrid[3][3]+"|"+cGrid[4][3]+"|"+cGrid[5][3]+"|"+cGrid[6][3]+"|"+cGrid[7][3]+"|"+cGrid[8][3]+"|\n"+
	// 				"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 				"|"+cGrid[1][4]+"|"+cGrid[2][4]+"|"+cGrid[3][4]+"|"+cGrid[4][4]+"|"+cGrid[5][4]+"|"+cGrid[6][4]+"|"+cGrid[7][4]+"|"+cGrid[8][4]+"|\n"+
	// 				"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 				"|"+cGrid[1][5]+"|"+cGrid[2][5]+"|"+cGrid[3][5]+"|"+cGrid[4][5]+"|"+cGrid[5][5]+"|"+cGrid[6][5]+"|"+cGrid[7][5]+"|"+cGrid[8][5]+"|\n"+
	// 				"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 				"|"+cGrid[1][6]+"|"+cGrid[2][6]+"|"+cGrid[3][6]+"|"+cGrid[4][6]+"|"+cGrid[5][6]+"|"+cGrid[6][6]+"|"+cGrid[7][6]+"|"+cGrid[8][6]+"|\n"+
	// 				"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 				"|"+cGrid[1][7]+"|"+cGrid[2][7]+"|"+cGrid[3][7]+"|"+cGrid[4][7]+"|"+cGrid[5][7]+"|"+cGrid[6][7]+"|"+cGrid[7][7]+"|"+cGrid[8][7]+"|\n"+
	// 				"+-----+-----+-----+-----+-----+-----+-----+-----+\n"+
	// 				"|"+cGrid[1][8]+"|"+cGrid[2][8]+"|"+cGrid[3][8]+"|"+cGrid[4][8]+"|"+cGrid[5][8]+"|"+cGrid[6][8]+"|"+cGrid[7][8]+"|"+cGrid[8][8]+"|\n"+
	// 				"+-----+-----+-----+-----+-----+-----+-----+-----+\n";


	public static void main(String[] args) throws FileNotFoundException, IOException {

		// Create Cell Grid
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				cGrid[i][j] = new Cell(i, j);
			}
		}
		// cGrid[1][1].setData("0");

		SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Mini Spreadsheet");
            frame.setSize(610, 620);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Display area
            JTextArea display = new JTextArea();
            display.setEditable(false);
            display.setBackground(Color.BLACK);			// Background color of text/display area
            display.setForeground(Color.WHITE);			// Frreground color = text color
            display.setCaretColor(Color.WHITE);			// Caret color = color of the blinking line that tells you where you are typing
			display.setMargin(new Insets(10, 10, 10, 10));
            display.setFont(new Font("Monospaced", Font.PLAIN, 18));
			// Removes Scroll Bar Border	--- TO DO
			// JScrollPane scrollPane = new JScrollPane(display);
			// scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        	// scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			// // add(scrollPane, BorderLayout.CENTER);

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
			
			// Set Starting Text
			display.setText(buildGrid());
			// Border
            frame.setLayout(new BorderLayout());
            frame.add(new JScrollPane(display), BorderLayout.CENTER);
            frame.add(input, BorderLayout.SOUTH);
            frame.setVisible(true);
			
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
						input.setText("Insert Command Here...");
					}
            	}
			});

			// Handle input
            input.addActionListener(e -> {
				// Read in User Input
				String userInput = input.getText();

				// Clear input field
                input.setText("");

				// Parse Commands
				if( !(userInput == null || userInput.isBlank())) {
					String[] command = userInput.toLowerCase().split(" ");
					if(runCommand(command)) {
						// Update Display
						display.setText(buildGrid() + "> " + userInput);
					}
				} else {
					error("Blank Command");
				}
            });
        });

		
	}
	// returns true on successful function call
	// returns false otherwise
	public static boolean runCommand(String[] command) {
		switch (command[0]) {
			case "set":
				// set (1,1) to (2,2)
				// set (1,1) to 5
				if(command.length==4) {
					if(isCord(command[1]) && command[2].equals("to"))
						if(isCord(command[3])) {
							setCordCord(fc(command[1]), fc(command[3]));
							return true;
						} else if(isValidNum(command[3])) {
							setCordNum(fc(command[1]), command[3]);
							return true;
						} else if(command[3].length()<=5) {
							setCordString(fc(command[1]), command[3]);
							return true;
						}
				} else
					error("Set Format");
				break;

			case "add":
				// add (1,1) and (2,1)
				// add (1,1) and 5
				if(command.length==4) {
					if(isCord(command[1]) && command[2].equals("and")) {
						if(isCord(command[3])) {
							return addCordCord(fc(command[1]), fc(command[3]));
						} else if (isValidNum(command[3])) {
							return addCordNum(fc(command[1]), command[3]);
						}
					}
				} else
					error("Add Format");
				break;

			case "sub":
				break;

			case "concat":
				break;

			case "clear":
				break;

			case "function":
			case "func":
				break;

			default:
				System.out.println("Unreconigzed Command");
				break;
		}
		return false;
	}

	public static boolean addCordCord(String main, String toAdd) {
		if(isValidNum(cordToCell(main).getData()) && isValidNum(cordToCell(toAdd).getData())) {
			if(isValidNum(Integer.valueOf(cordToCell(main).getData()) + Integer.valueOf(cordToCell(toAdd).getData()))) {
				cordToCell(main).setData(Integer.valueOf(cordToCell(main).getData()) + Integer.valueOf(cordToCell(toAdd).getData()) + "");
				return true;
			} else {
				error("Sum is too long to be stored in cell");
			}
		} else {
			error("Can only add two numbers");
		}
		return false;
	}
	public static boolean addCordNum(String main, String toAdd) {
		if(isValidNum(cordToCell(main).getData()) && isValidNum(toAdd)) {
			if(isValidNum(Integer.valueOf(cordToCell(main).getData()) + Integer.valueOf(toAdd))) {
				cordToCell(main).setData(Integer.valueOf(cordToCell(main).getData()) + Integer.valueOf(toAdd) + "");
				return true;
			} else {
				error("Sum is too long to be stored in cell");
			}
		} else {
			error("Can only add two numbers");
		}
		return false;
	}

	public static Cell cordToCell(String s) {
		return cGrid[Integer.valueOf(s.charAt(1)+"")][Integer.valueOf(s.charAt(3)+"")];
	}

	public static void setCordCord(String y, String x) {
		cordToCell(y).setData(cordToCell(x).getData());
	}
	public static void setCordNum(String y, String x) {
		cordToCell(y).setData(x);
	}
	public static void setCordString(String y, String s) {
		cordToCell(y).setData(s);
	}

	public static void error(String s) {
		System.out.println(s+" Error");
	}

	public static boolean isValidNum(int n) {
		return (n)>-10000 && (n)<100000;
	}
	public static boolean isValidNum(String n) {
		if(n!=null && !(n.isEmpty()) && !(n.isBlank())) {
			try {
				Integer.parseInt(n);
				return isValidNum(Integer.parseInt(n));
			} catch (NumberFormatException e) {
				error("Non-Valid Number");
			}
		}
		return false;
	}

	public static boolean isCord(String cord) {
		return isCellReference(cord) || isOrderedPair(cord);
	}
	// EX: (1,2), (4,6)
	public static boolean isOrderedPair(String cord) {
		if (cord.length()==5) {
			if (cord.charAt(0)=='(' && cord.charAt(4)==')')
				if(cord.charAt(2)==',')
					if(
						Character.isDigit(cord.charAt(1)) &&
						Character.isDigit(cord.charAt(3))
					) {
						int temp1 = Integer.valueOf(cord.charAt(1)+"");
						int temp2 = Integer.valueOf(cord.charAt(3)+"");
						if(1<=temp1 && temp1<=8 && 1<=temp2 && temp2<=8)
							return true;
						else {
							error("Cordinate x or y is out of Range");
							return false;
						}
					}
		}
		error("Invalid Cordinate Syntax");
		return false;
	}
	// EX: A1, B5
	public static boolean isCellReference(String cord) {
		if(cord.length()==2) {
			if('a' <= cord.charAt(0) && cord.charAt(1) <= 'h') {
				if(Character.isDigit(cord.charAt(1))) {
					int temp = Integer.valueOf(cord.charAt(1)+"");
					if(1<=temp && temp <=8)
						return true;
				}
			}
		}
		return false;
	}

	// f = format
	// after checking if a string is a valid cord (ordered pair or cell reference), use this function to make it in the form of ordered pair (becuase the other functions rely on this form)
	public static String fc(String cord) {
		if(cord.length()==2) {
			String temp = "("+(cord.charAt(0)-96)+","+cord.substring(1)+")";
			return temp;
		} else if (cord.length()==5)
			return cord;
		error("Non-Valid Cord Passed");
		return "";
	}

	public static String buildGrid() {
		String s = 	"     A     B     C     D     E     F     G     H\n"+
					"  +-----+-----+-----+-----+-----+-----+-----+-----+\n";
		for (int y = 1; y <= 8; y++) {
			s = s + y+ " ";
			for (int x = 1; x <= 8; x++) {
				s = s + "|" + cGrid[x][y];
			}
			s = s + "|\n"+
					"  +-----+-----+-----+-----+-----+-----+-----+-----+\n";
		}
		return s;
	}


}
