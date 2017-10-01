/**
 * class that implements support methods needed by algorithm computerPlay
 */
package assignment2;

public class Achi {
	// Attribute Declarations
	private char[][] gameBoard;
	private int board_size;
	private int max_levels; 
	
	/**
	 * Constructor creates a 2D array of specified size
	 * @param board_size - amount of columns/rows of array
	 * @param max_levels - level of difficulty 
	 */
	public Achi (int board_size, int max_levels){
		this.max_levels = max_levels; 
		this.board_size = board_size; 
		this.gameBoard = new char[board_size][board_size]; 
		int i, j = 0; 
		// create an empty gameboard 
		for (i=0; i<board_size; i++){
			for (j=0; j<board_size; j++){
				gameBoard[i][j]=' ';
			}
		}
	}
	
	/**
	 * class that creates a dictionary 
	 * @return - dictionary of selected size
	 */
	public Dictionary createDictionary(){
		return new Dictionary(); 
	}
	
	/**
	 * Represents the contents of gameBoard as a string then checks if the string is in configurations dictionary 
	 * @param configurations - dictionary that contains all configuration
	 * @return score of configuration if the configuration is in the dictionary, -1 if score isn't in dictionary 
	 */
	public int repeatedConfig(Dictionary configurations){
		String boardConfig = ""; 
		// turn the board configurations into a string 
		for (int i=0; i<board_size; i++){
			for (int j=0; j<board_size; j++){
				boardConfig = boardConfig + gameBoard[i][j];
			}
		}
		// check if the configuration is in a dictionary 
		return configurations.find(boardConfig);
	}
	
	/**
	 * Represents contents of gameBoard as a string then inserts string and score into configurations dictionary 
	 * @param configurations - dictionary that contains all configuration
	 * @param score - score of that configuration 
	 * @throws DictionaryException 
	 */
	public void insertConfig(Dictionary configurations, int score){
		String boardConfig = ""; 
		// represents contents of gameBoard as a string
		for (int i =0; i<board_size; i++){
			for (int j=0; j<board_size; j++){
				boardConfig = boardConfig + gameBoard[i][j];
			}
		}
		// put the data into a configdata object
		ConfigData config = new ConfigData(boardConfig, score);
		// insert into the dictionary, catch any exceptions generated 
		try {
			configurations.insert(config);
		} catch (DictionaryException e) {
			e.printStackTrace();
		}
		} 
	
	/**
	 * stores symbol in gameBoard[row][column]
	 * @param row - row that you want to insert symbol
	 * @param col - column that you want to insert symbol
	 * @param symbol
	 */
	public void storePlay(int row, int col, char symbol){
		gameBoard[row][col]=symbol; 
	}
	
	/**
	 * check if the tile at a specified position on the board is empty
	 * @param row 
	 * @param col
	 * @return true if the tile is empty, false otherwise 
	 */
	public boolean tileIsEmpty(int row, int col){
		if (gameBoard[row][col]==' '){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * check if the tile at a specified position on the board is the computer ('O')
	 * @param row
	 * @param col
	 * @return true if the tile is the computer, false otherwise
	 */
	public boolean tileIsComputer(int row, int col){
		if (gameBoard[row][col]== 'O'){
			return true; 
		}
		else{
			return false;
		}
	}
	
	/**
	 * check if the tile at a specified position on the board is human ('X')
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean tileIsHuman(int row, int col){
		if (gameBoard[row][col]=='X'){
			return true; 	
		}
		else{
			return false;
		}
	}
	
	/**
	 * check if there is a win on the board (adjacent tiles of type symbol in same row, column, or diagonal) 
	 * @param symbol - symbol that you are checking if there is a win
	 * @return true if there is a win for the symbol, false if not 
	 */
	public boolean wins(char symbol){
		boolean horizontalWin = true; // variable for horizontal win
		boolean verticalWin = true; // variable for vertical win
		boolean diagonalWin1 = true; // variable for diagonal win 
		boolean diagonalWin2 = true; 
		int h = 0; 
		int v = 0; 
		int d1 = 0; 
		int d2 = 0; 
		for (int i=0; i<board_size; i++){ // iterate through rows of array 
			// check horizontal win 
			while (h<board_size){ // iterate through a column 
				if (gameBoard[i][h]!=symbol){ // check each element against the symbol
					horizontalWin = false; // found that an element doesn't equal the symbol
				}
				h++; // increment count
			}
			if (horizontalWin == true){ // if all element in row match symbol, return true
				return true; 
			}
			else{ // reset variable to check next row 
				h=0; 
				horizontalWin = true; 
			}
		}
		// check for vertical wins 
	    for (int q=0; q<board_size; q++){ // iterate through column of array
			while (v<board_size){ // iterate through a row 
				if (gameBoard[v][q]!=symbol){ // check each element in column against the symbol
					verticalWin=false; // found that an element on row doesn't equal symbol 
				}
				v++; // increment count
			}
			if (verticalWin == true){ // if all elements in column match symbol, return true
				return true; 
			}
			else{ // reset variable to check next column 
				v=0; 
				verticalWin = true; 
			}
	    }
		//check for diagonal wins top left to bottom right
		for (d1=0; d1<board_size; d1++){ // iterate through rows 
			if (gameBoard[d1][d1]!=symbol){ // check each spot with the same row and column coordinate
				diagonalWin1 = false; 
			}
	    }
		if (diagonalWin1==true){
			return true; 
		}
		//check for diagonal top right to bottom left 
		for (d2=0; d2<board_size; d2++){
			if (gameBoard[d2][board_size-1-d2]!=symbol){
				diagonalWin2=false; 
				break; 
			}
		}
		if (diagonalWin2==true){
			return true; 
		}
		
		return false; 
	}
	
	/**
	 * class that determines if the board is a draw 
	 * @param symbol - symbol of the next move
	 * @return true if the board is a draw, false otherwise 
	 */
	public boolean isDraw(char symbol){
		int spaceCount = 0; 
		int spaceRow=0; 
		int spaceColumn=0; 
		boolean surSymbol = true; 
		// find the space in the board and remember the row and column number 
		for (int r=0; r<board_size; r++){
			for (int c=0; c<board_size; c++){
				if (gameBoard[r][c]==' '){
					spaceRow = r; 
					spaceColumn = c; 
					spaceCount++; 
				}
			}
		}
		
		// Check if there is more than one space 
		if (spaceCount > 1){
			surSymbol = false; 
		}
		else{
			// check spot below
			if (spaceRow+1<board_size){ 
				if (gameBoard[spaceRow+1][spaceColumn]==symbol){
					surSymbol = false;
				}
			}
			// check spot to the right
			if (spaceColumn+1<board_size){ 
				if (gameBoard[spaceRow][spaceColumn+1]==symbol){
					surSymbol = false;
				}
			}
			// check spot above
			if (spaceRow-1>0){ 
				if (gameBoard[spaceRow -1][spaceColumn]==symbol){
					surSymbol = false; 
				}
			}
			// check spot to the left
			if (spaceColumn-1>0){
				if (gameBoard[spaceRow][spaceColumn-1]==symbol){
					surSymbol = false; 
				}
			}
			 // check upper left corner
			if ((spaceRow-1>0) && (spaceColumn-1)>0){
				if (gameBoard[spaceRow-1][spaceColumn-1]==symbol){
					surSymbol = false; 
				}
			}
			// check lower right corner
			if ((spaceRow+1<board_size) && (spaceColumn+1)<board_size){ 
				if (gameBoard[spaceRow+1][spaceColumn+1]==symbol){
					surSymbol = false; 
				}
			}
			// check upper right corner
			if ((spaceRow-1>0) && (spaceColumn+1)<board_size){ 
				if (gameBoard[spaceRow-1][spaceColumn+1]==symbol){
					surSymbol = false; 
				}
			}
			// check lower right corner
			if ((spaceRow+1<board_size) && (spaceColumn-1)>0){
				if (gameBoard[spaceRow+1][spaceColumn-1]==symbol){
					surSymbol = false; 
				}
			}
		}
		return surSymbol; 

		}
	
	/**
	 * method that evaluates the winner on the board
	 * @param symbol
	 * @return
	 */
	public int evalBoard(char symbol){ 
		int points=1; 
		// computer wins
		if (wins('O')==true){ 
			points =3;
		}
		//human wins
		else if (wins('X')==true){ 
			points=0;
		}
		//game is a draw
		else if (isDraw(symbol)==true){ 
			points=2;
		}
		// game is still undecided 
		return points; 
	}
}