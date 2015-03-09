package com.saurabh.main;
import java.util.Scanner;

enum TicTacToeState {  
	   PLAYING, DRAW, CROSS_WON, NOUGHT_WON
};

enum Mark { 
		   EMPTY, CROSS, NOUGHT
		};

		
class Cell {  
			   Mark mark; 
			   int row, col; 
			 
			   /** Constructor to initialize this cell */
			   public Cell(int row, int col) {
			      this.row = row;
			      this.col = col;
			      mark = Mark.EMPTY;
			   }
			 
			   public void clear() {
				      mark = Mark.EMPTY;
				   }		 
			   // Paint itself //
			   public void paint() {
			      switch (mark) {
			         case CROSS:  System.out.print(" X "); break;
			         case NOUGHT: System.out.print(" O "); break;
			         case EMPTY:  System.out.print("   "); break;
			      }
			   }
}		
		
		
class GameBoard {  
	
		public static final int ROWS = 3;
		public static final int COLS = 3;
	 
	  
	   Cell[][] cells;  
	   int currentRow, currentCol;  
	 
	   /** Constructor to initialize the game board */
	   public GameBoard() {
	      cells = new Cell[ROWS][COLS];  // allocate the array
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            cells[row][col] = new Cell(row, col); // allocate element of the array
	         }
	      }
	   }
	 
	   /** Initialize cells of the game board */
	   public void init() {
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            cells[row][col].clear();  // clear the cell content
	         }
	      }
	   }
	 
	   /** Return true if it is a draw (i.e., no more EMPTY cell) */
	   public boolean isDraw() {
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            if (cells[row][col].mark == Mark.EMPTY) {
	               return false; // an empty seed found, not a draw, exit
	            }
	         }
	      }
	      return true; // no empty cell, it's a draw
	   }
	 
	   /** Return true if the player with "MARK" has won after placing at
	       (currentRow, currentCol) */
	   public boolean hasWon(Mark theMark) {
	      return (cells[currentRow][0].mark == theMark         // 3-in-the-row
	                   && cells[currentRow][1].mark == theMark
	                   && cells[currentRow][2].mark == theMark
	              || cells[0][currentCol].mark == theMark      // 3-in-the-column
	                   && cells[1][currentCol].mark == theMark
	                   && cells[2][currentCol].mark == theMark
	              || currentRow == currentCol            // 3-in-the-diagonal
	                   && cells[0][0].mark == theMark
	                   && cells[1][1].mark == theMark
	                   && cells[2][2].mark == theMark
	              || currentRow + currentCol == 2    // 3-in-the-opposite-diagonal
	                   && cells[0][2].mark == theMark
	                   && cells[1][1].mark == theMark
	                   && cells[2][0].mark == theMark);
	   }
	 
	   /** Paint itself */
	   public void paint() {
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            cells[row][col].paint();   // each cell paints itself
	            if (col < COLS - 1) System.out.print("|");
	         }
	         System.out.println();
	         if (row < ROWS - 1) {
	            System.out.println("-----------");
	         }
	      }
	   }
	}		

public class TicTacToe {
	
	private GameBoard board;            
    private TicTacToeState currentState; 
	private Mark currentPlayer; 
	private int totCross,totNought;
	private static Scanner in = new Scanner(System.in);
	
	/** Constructor to setup the game */
	   public TicTacToe() {
		board = new GameBoard();
		initGame();
		}
	   public void initGame() {
		      board.init();  // clear the board contents
		      currentPlayer = Mark.CROSS;       // CROSS plays first
		      currentState = TicTacToeState.PLAYING; // ready to play
		      totCross=0;
		      totNought=0;
		   }
	   
	   public void startGame(){
		   do {
		         inputPlayerMove(currentPlayer); // update the content, currentRow and currentCol
		         board.paint();             // ask the board to paint itself
		         updateGame(currentPlayer); // update currentState
		         // Print message if game-over
		         if (currentState == TicTacToeState.CROSS_WON) {
		            System.out.println("'X' won! in"+totCross+" moves");
		         } else if (currentState == TicTacToeState.NOUGHT_WON) {
		            System.out.println("'O' won! in "+totNought+" moves");
		         } else if (currentState == TicTacToeState.DRAW) {
		            System.out.println("It's Draw! reintializing...");
		            initGame();
		            startGame();
		         }
		         // Switch player
		         currentPlayer = (currentPlayer == Mark.CROSS) ? Mark.NOUGHT : Mark.CROSS;
		      } while (currentState == TicTacToeState.PLAYING);  // repeat until game-over
	   }
	   public void inputPlayerMove(Mark thePlayermark) {
		      boolean validInput = false;  // for validating input
		      boolean restart=false;
		      do {
		         if (thePlayermark == Mark.CROSS) {
		            System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
		            totCross++;
		         } else {
		            System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): ");
		            totNought++;
		         }
		         int row = in.nextInt() - 1;
		         int col = in.nextInt() - 1;
		         if (row >= 0 && row < GameBoard.ROWS && col >= 0 && col < GameBoard.COLS
		               && board.cells[row][col].mark == Mark.EMPTY) {
		            board.cells[row][col].mark = thePlayermark;
		            board.currentRow = row;
		            board.currentCol = col;
		            validInput = true; // input okay, exit loop
		         }else if(row <0 ||col<0){
		        	 System.out.println("Restarting Game....");
		        	 validInput = true; 
		        	 restart=true;
		        	 
		         }
		         else {
		            System.out.println("This move at (" + (row + 1) + "," + (col + 1)
		                  + ") is not valid. Try again...");
		         }
		      } while (!validInput);   // repeat until input is valid
		      
		      if(restart){
		    	  initGame();
		          startGame();
		      }
		   }
	
	   /** Update the currentState after the player has marked */
	   public void updateGame(Mark theMark) {
	      if (board.hasWon(theMark)) {  // check for win
	         currentState = (theMark == Mark.CROSS) ? TicTacToeState.CROSS_WON : TicTacToeState.NOUGHT_WON;
	      } else if (board.isDraw()) {  // check for draw
	         currentState = TicTacToeState.DRAW;
	      }
	      // Otherwise, no change to current state (still PLAYING).
	   }
	   
	   public static void main(String[] args) {
		 TicTacToe gameInstance=new TicTacToe();  
		 gameInstance.startGame();
		 
	   }
	
}