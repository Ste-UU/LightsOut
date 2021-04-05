import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	public ArrayList<String> file = new ArrayList<>();
	public int depth;
	public String board;
	public String pieces;


	public ReadFile() {
		
		readFile();
		
	}

	private void readFile() {

		try {
			File level = new File("01.txt");

			Scanner s = new Scanner(level);

			while (s.hasNextLine()) {
				file.add(s.nextLine());
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Invalid File");
		}

		setDepth(Integer.parseInt(file.get(0)));
		setBoard(file.get(1));
		setPieces(file.get(2));
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public String getPieces() {
		return pieces;
	}

	public void setPieces(String pieces) {
		this.pieces = pieces;
	}

	
}
