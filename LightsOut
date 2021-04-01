import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LightsOut extends JFrame implements ActionListener {
	private int row;
	private int col;
	private int depth;
	private ArrayList<String> rFile = new ArrayList<>();
	private JButton[][] gameButtons;
	private ArrayList<String[][]> availablePieces = new ArrayList<>();
	private ArrayList<String> pieceDim = new ArrayList<>();
	private int nextPieceNum;
	private ArrayList<String> solution = new ArrayList<>();
	private JTextArea np;

	public LightsOut() {
		readFile();
		pieces();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Sets title and size of the JFrame.
		setTitle("Lights Out");
		setSize(500, 500);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		int[][] layoutMatrix = initialMatrix();

		// Buttons
		JPanel buttonPanel = new JPanel();
		gameButtons = new JButton[getRow()][getCol()];
		buttonPanel.setLayout(new GridLayout(getRow(), getCol()));

		for (int i = 0; i < getRow(); i++) {
			for (int j = 0; j < getCol(); j++) {

				JButton button = new JButton();
				gameButtons[i][j] = button;
				button.setName("" + i + j);
				button.setBackground(Color.BLACK);

				int state = layoutMatrix[i][j];
				for(int s=0; s<state;s++) // changes color according to state
					backgroundColor(button);
			
				button.addActionListener(this);
				buttonPanel.add(button);
			}
		}

		mainPanel.add(buttonPanel, "Center");
		
		setContentPane(mainPanel);
		nextPiece();
		
	}

	public void readFile() {
		ArrayList<String> result = new ArrayList<>();
		System.out.println("Input level (ex:03): ");
		Scanner sc = new Scanner(System.in);
		try {
			File level = new File(sc.nextLine()+".txt");

			Scanner s = new Scanner(level);

			while (s.hasNextLine()) {
				result.add(s.nextLine());
			}
			s.close();
			setrFile(result);
		} catch (FileNotFoundException e) {
			System.out.println("Choose valid level");
			readFile();
		}

	}

	private int[][] initialMatrix() {
		ArrayList<String> fileArray = getrFile();
		String[] result = fileArray.get(1).split(",");
		int nrow = 0;
		int ncolumns = result[0].length();
		for (String element : result) {
			nrow++;
	//		System.out.println(element);
		}
		setRow(nrow);
		setCol(ncolumns);
		setDepth(Integer.parseInt(fileArray.get(0)));
	//	System.out.println("rows " + getRow() + " columns " + getCol() + " Depth " + getDepth());
		String initialState = fileArray.get(1).replaceAll(",", "");

		int[][] layoutMatrix = new int[nrow][ncolumns];
		int count = 0;
		for (int i = 0; i < nrow; i++) {
			for (int j = 0; j < ncolumns; j++) {
				char n = initialState.charAt(count);
				layoutMatrix[i][j] = Character.getNumericValue(n);
				count++;
			}
		}
		return layoutMatrix;

	}

	private void pieces() {
		ArrayList<String> fileArray = getrFile();
		String[] p = fileArray.get(2).split(" "); // each piece

		for (String element : p) {
			// find out nr of rows and columns
			String[] pState = element.split(",");
			int nrow = pState.length;
			int ncolumns = pState[0].length();
			pieceDim.add("" + nrow + ncolumns);

			// place states inside matrix
			String sp = element.replaceAll(",", "");// state inside the piece
			int count = 0;
			String[][] pieceMatrix = new String[nrow][ncolumns];
			for (int i = 0; i < nrow; i++) {
				for (int j = 0; j < ncolumns; j++) {
					pieceMatrix[i][j] = String.valueOf(sp.charAt(count));
					count++;
				}
			}
			availablePieces.add(pieceMatrix);
		}
		setNextPieceNum(0);
	}

	private void nextPiece() {
		
		String[][] p = availablePieces.get(getNextPieceNum());
		char rowChar = pieceDim.get(nextPieceNum).charAt(0); // get n of rows in piece
		char colChar = pieceDim.get(nextPieceNum).charAt(1); // get n of columns in piece
		int col = Character.getNumericValue(colChar);
		int row = Character.getNumericValue(rowChar);

		System.out.println("-------NEXT PIECE---------");
		String printable = "";
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				printable=printable+(p[i][j]);
			}
			printable=printable+ "\n";
		}
		System.out.println(printable);
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {

		JButton button = (JButton) e.getSource(); // get clicked button

		String location = button.getName(); // gets the name of the current button

		char colChar = location.charAt(1);
		char rowChar = location.charAt(0);
		int col = Character.getNumericValue(colChar);
		int row = Character.getNumericValue(rowChar);

		int pieceRow = Character.getNumericValue(pieceDim.get(nextPieceNum).charAt(0));
		int pieceCol = Character.getNumericValue(pieceDim.get(nextPieceNum).charAt(1));

		if (getNextPieceNum() < availablePieces.size() - 1) { 						//checks if there is another available piece
			if (pieceRow - 1 + row < getRow() && pieceCol - 1 + col < getCol()) { 	//checks if piece is in bounds
				solution.add("(" + rowChar + "," + colChar + ")");

				String[][] piece = availablePieces.get(getNextPieceNum());				
				for (int i = 0; i < pieceRow; i++) {
					for (int j = 0; j < pieceCol; j++) {
						if (piece[i][j].equals("X")) {
							JButton temp = new JButton();
							temp = gameButtons[row+i][col+j];
							backgroundColor(temp);							
						}
					}
				}
				setNextPieceNum(getNextPieceNum() + 1);
				nextPiece();
			} else {
				JOptionPane.showMessageDialog(this, "Piece out of bounds!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "No more pieces");
			System.out.println("Solution: " + solution);
		}
	}

	private void backgroundColor(JButton b) {
		switch (depth) {
		case 2:
			if (b.getBackground() == Color.BLACK) {
				b.setBackground(Color.YELLOW);
			} else {
				b.setBackground(Color.BLACK);
			}
			break;
		case 3:
			if (b.getBackground() == Color.BLACK) {
				b.setBackground(Color.YELLOW);
			} else if (b.getBackground() == Color.YELLOW) {
				b.setBackground(Color.RED);
			} else
				b.setBackground(Color.BLACK);

			break;
		case 4:
			if (b.getBackground() == Color.BLACK) {
				b.setBackground(Color.YELLOW);
			} else if (b.getBackground() == Color.YELLOW) {
				b.setBackground(Color.RED);
			} else if (b.getBackground() == Color.RED) {
				b.setBackground(Color.GREEN);
			} else
				b.setBackground(Color.BLACK);
			break;

		}

	}

	public ArrayList<String> getrFile() {
		return rFile;
	}

	public void setrFile(ArrayList<String> rFile) {
		this.rFile = rFile;
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

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getNextPieceNum() {
		return nextPieceNum;
	}

	public void setNextPieceNum(int nextPiece) {
		this.nextPieceNum = nextPiece;
	}

}
