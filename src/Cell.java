package src;

public class Cell {
	
	// ---Attributes---
	private int[] adress;
	private String data = "";
	private boolean isDirty = false;

	// ---Constructors---
	// Default
	public Cell(int x, int y) {
		setData("");
		setAdress(x, y);
	}
	// Data Provided
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
	public boolean isDirty() {
		return isDirty;
	}

	// --Setters--
	public void setAdress(int x, int y) {
		int[] temp = {x,y};
		adress = temp;
	}
	public void setData(String data) {		// Need to add checks
		if (data!=null && (data.isEmpty() || data.length()<=5)) {
			if(!(this.data.equals(data))) {		// if new data is different than old data
				this.data = data;
				isDirty=true;
			}
		}
		System.out.println("cell data after setting: "+this.data);
	}
	public void setDataFromDB(String data) {
		this.data = data;
		this.isDirty = false;
	}

	// marks cell as clean
	public void clean() {
		isDirty = false;
	}

	// toDefault
	public void toDefault() {
		setData("");
	}
	
	// --toString--
	public String toString() {
		if (data==null || data.isEmpty() || data.isBlank())
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
