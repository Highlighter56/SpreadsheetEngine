package src;

import java.util.Map;

public class Cell implements Comparable<Cell>{
	
	private int[] adress;
	private String data;

	// --Constructors--
	// Default
	public Cell(int x, int y) {
		setData(null);
		setAdress(x, y);
	}
	public Cell(int x, int y, String data) {
		setData(data);
		setAdress(x, y);
	}
	
	
	// --Getters--
	public int[] getAdress() {
		return adress;
	}
	public String getData() {
		return data;
	}

	// --Setters--
	public void setAdress(int x, int y) {
		int[] temp = {x,y};
		adress = temp;
	}
	public void setData(String data) {		// Need to add checks
		if (data==null || data.length()<=5)
			this.data = data;
		else {
			this.data = data.substring(data.length()-5);
		}
	}

	// --compareTo--
	public int compareTo(Cell cell) {
		return this.data.compareTo(data);
	}
	
	// --toString--
	public String toString() {
		if (data == null)
			return "     ";
		else if (data.length() == 1)
			return "  "+data+"  ";
		else if (data.length() == 2)
			return " "+data+"  ";
		else if (data.length() == 3)
			return " "+data+" ";
		else if (data.length() == 4)
			return data+" ";
		return data;
	}

}
