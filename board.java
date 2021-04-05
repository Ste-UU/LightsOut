

public class Board {	
	private ReadFile f;
	private int row;
	private int col;
	private String startState[][];
	
	
	public Board() {
		
		 f= new ReadFile();
		 initialMatrix();
	}

	private void initialMatrix() {
		String[] result = f.getBoard().split(",");
		int nrow = 0;
		int ncolumns = result[0].length();
		for (String element : result) {
			nrow++;
			System.out.println(element);
		}
		setRow(nrow);
		setCol(ncolumns);
		String initialState = f.getBoard().replaceAll(",", "");

		String[][] layoutMatrix = new String[nrow][ncolumns];
		int count = 0;
		for (int i = 0; i < nrow; i++) {
			for (int j = 0; j < ncolumns; j++) {
				String n = ""+initialState.charAt(count);
				layoutMatrix[i][j] = n;
				count++;
			}
		}
		setStartState(layoutMatrix);
	}
	
	public String[][] getStartState() {
		return startState;
	}

	public void setStartState(String[][] startState) {
		this.startState = startState;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	
}
