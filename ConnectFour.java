//Maggie Chen
//EECS 1510
//2-11-2016
//Connect Four
//this program will create the game connect four, where 2 players take turns dropping
//disks (R and Y) into the 6 by 7 board. The object of the game is to get 4 disks 
//of the same color four in a row, either in a row, diagonally, or up and down.
//When one of the players gets that to happen, the game is over and the player that
//got four in a row wins. 

import java.util.Scanner; //need to import the scanner because users will need to input info for
						  //their disc
public class ConnectFour {
	static char[][] board = new char[6][7];//global board variable
	static int turns = 0;//global turns variable
	public static void main(String[] args) //main method where i will put all the things for players one and two, and the methods to be called here. 
	{
		int userColumn = 0;
		char disc = ' '; // current player's disc color (R/Y) 
		Scanner input = new Scanner(System.in);
		displayBoard(board);
		System.out.println("WELCOME TO CONNECT FOUR!!!");

		//set each value of the array to a space so the board does not think it is full already.
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				board[row][column] = ' ';
			}
		}
		
		for (int turn = 0; turn <= 42; turn++) //create a turn counter so we know which player goes first, etc.
		{
			//within the turn counter, put in all the things each player must do
			if (turn == 42)//check for a tie if the board is filled up with no four in a row.
			   {
			  	 System.out.println("IT'S A TIE!!!");
			  	 return;
			   }
			if (turn % 2 == 0) 
			{
				System.out.println("Player 1's turn! "); //this block of code within the if statement 
														//is all the things that player 1 must do in the game.
				disc = 'R';
				System.out.print("Choose a column: ");
				userColumn = input.nextInt();
				while (userColumn < 0 || userColumn > 6 || columnIsFull(userColumn) ) {
					System.out.print("Not a valid input, pick another column: ");
					userColumn = input.nextInt();
				}
				dropDisk(disc, userColumn);
				displayBoard(board);

				if (theWinner(board)) {
					System.out.println("Cool, four in a row! YOU WIN, WOOHOO!");
					return;
				}
			} 
			else 
			{
				System.out.print("Player 2's turn!");//this block of code is all
													//the tasks that player 2 must do in the game.
				disc = 'Y';
				System.out.print("Choose a column: ");
				userColumn = input.nextInt();
				while (userColumn < 0 || userColumn > 6 || columnIsFull(userColumn)) {
					System.out.println("Not a valid input, pick another column: ");
					userColumn = input.nextInt();
				}
				dropDisk(disc, userColumn);
				displayBoard(board);
				if (theWinner(board)) {
					System.out.println("Cool, four in a row, YOU WIN, WOOHOO!");
					return;
				}
				
			}
		}
	
}

 //this method is to check if a column on the board is full, and if so, to prompt user to enter another input.
 private static boolean columnIsFull(int userColumn)
	{
		if(board[0][userColumn] != ' ')
		{
			System.out.print("Column is full, choose another please: ");
			return true;
		}
		return false;
	}


public static boolean theWinner(char[][] board) //this method will need to check the four cases of ending the game
  {
   //first case is having four pieces horizontally in a rows
	//in this case it is necessary to increment the columns not the rows
   for (int row = 0; row < 6; row++) 
   {
    for (int column = 0; column < 4; column++) 
    {
     if (board[row][column] == board[row][column + 1] && board[row][column + 1] == board[row][column + 2] && board[row][column + 2] == board[row][column + 3] && board[row][column] != ' ') 
     {
      return true;
     }
    }
   }

   //second case is checking for four pieces vertically in a row.
   //For this case you need to increment the rows instead of the columns
   for (int row = 0; row < 3; row++) 
   {
    for (int column = 0; column < 6; column++) 
    {
     if (board[row][column] == board[row + 1][column] && board[row + 1][column] == board[row + 2][column] && board[row + 2][column] == board[row + 3][column] && board[row][column] != ' ') 
     {
      return true;
     }
    }
   }
   //third case is checking for four diagonally in a row. Need two blocks of code for this 
   //diagonal check because you need to check from the top left and the bottom right.
   for (int row = 0; row < 3; row++) 
   {
    for (int column = 0; column < 4; column++) 
    {
     if (board[row][column] == board[row + 1][column + 1] && board[row + 1][column + 1] == board[row + 2][column + 2] && board[row + 2][column + 2] == board[row + 3][column + 3] && board[row][column] != ' ') 
     {
      return true;  
     }
   }
 }
   for (int row = 0; row < 3; row++) 
   {
    for (int column = 3; column < 7; column++) 
    {
     if (board[row][column] == board[row + 1][column - 1] && board[row + 1][column - 1] == board[row + 2][column - 2] && board[row + 2][column - 2] == board[row + 3][column - 3] && board[row][column] != ' ') 
     {
      return true;
     }
   }
 }
   
    return false;
  }

 public static void dropDisk(char disc, int userColumn)//method to be able to drop a disk (R or Y) into the 6 by 7 game board. 
 {
  
  for (int row = 5; row >= 0; row--){ // Starting the dropDisk method, need to use for loop to increment rows.
  
   if (board[row][userColumn] == ' ') //from 0 to 5 to check whether all the rows contain a space
   { 								  //if it is a space, put disk in that spot on board, if not a space,
    board[row][userColumn] = disc;	  //check next set of rows and columns for same results.
    return;
   }
  }
  
 }

 public static void displayBoard(char[][] board)//method to display the connect four game board.
 {
  for (int row = 0; row < 6; row++) //increment rows
  {
   for (int column = 0; column < 7; column++) //increment columns
   {
    System.out.print("|");
    System.out.print(board[row][column]);//printing the board with columns and rows
   }
   System.out.print("|");
   System.out.println("");
  }
  System.out.println(" 0 1 2 3 4 5 6");


 }
}