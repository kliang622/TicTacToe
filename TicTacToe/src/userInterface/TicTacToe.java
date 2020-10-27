package userInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	static ArrayList<Integer> playerSpot = new ArrayList<Integer>();
	static ArrayList<Integer> CPUspot = new ArrayList<Integer>();

	public static void main (String [] args) 
	{
		
		//chats making up game board
		char[][] gameBoard = 
			{{' ','|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ','|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ','|', ' ', '|', ' '}};
		
		printGameBoard(gameBoard);
		//gets placement from super by taking in int
		
		//displays game placements only while there is an input- continues the game saving the positions everytime
		while(true)
		{
			Scanner scan = new Scanner (System.in);
			System.out.println("Enter your board spot (num 1-9)");
			int playerPosition= scan.nextInt();
			
			//stops CPU and player from overwritting taken placements
			while (playerSpot.contains(playerPosition) || CPUspot.contains(playerPosition))
			{
				System.out.println("This position is already taken! Enter another one~");
				playerPosition = scan.nextInt();
			}
			
			//puts down piece under user's name
			positionPiece(gameBoard,playerPosition, "Player");
			
			Random rando= new Random();
			int CPUposition = rando.nextInt(9)+1;
			while (playerSpot.contains(CPUposition) || CPUspot.contains(CPUposition))
			{
				CPUposition = rando.nextInt(9)+1;
			}
			
			positionPiece(gameBoard,CPUposition, "CPU");
			
			printGameBoard(gameBoard);
			
			String result= winner();
			//exits game is someone wins
			if (result.length()>0)
			{
				System.out.println(result);
				break;
			}
			
		}
		
				
		}
		

	//prints gameBoard onto screen
	public static void printGameBoard(char[][] gameBoard)
	{
		for (char []row: gameBoard)
		{
			for (char col: row)
			{
				System.out.print(col);
			}
			System.out.println();
		}
	}
	//defines what character a user will represent in the game and marks a specific spot on their game board with that letter when they make move
	public static void positionPiece(char [][]gameBoard, int placement, String user)
	{
		char symbol =' ';
		if(user.equals("Player"))
		{
			symbol='X';
			playerSpot.add(placement);
		}
		else if (user.equals("CPU"))
		{
			symbol= 'O';
			CPUspot.add(placement);
		}
		
		switch (placement)
		{
			case 1:
				gameBoard[0][0] =symbol;
				break;
			case 2:
				gameBoard[0][2] =symbol;
				break;
			case 3:
				gameBoard[0][4] =symbol;
				break;
			case 4:
				gameBoard[2][0] =symbol;
				break;
			case 5:
				gameBoard[2][2] =symbol;
				break;
			case 6:
				gameBoard[2][4] =symbol;
				break;
			case 7:
				gameBoard[4][0] =symbol;
				break;
			case 8:
				gameBoard[4][2] =symbol;
				break;
			case 9:
				gameBoard[4][4] =symbol;
				break;
			default:
				break;
	}
	}
	//stores and checks conditions to see which user won game
	public static String winner()
	{
		List topRow=Arrays.asList(1 , 2 , 3);
		List midRow=Arrays.asList(4 , 5 , 6);
		List lastRow=Arrays.asList(7 , 8 , 9);
		
		List leftCol=Arrays.asList(1 , 4 , 7);
		List midCol=Arrays.asList(2 , 5 , 8);
		List rightCol=Arrays.asList(3 , 6 , 9);
		
		List diagLeft=Arrays.asList(1 , 5 , 9);
		List diagRight=Arrays.asList(7 , 5 , 3);
		
		List<List> winCond = new ArrayList<List>();
		winCond.add(topRow);
		winCond.add(midRow);
		winCond.add(lastRow);
		winCond.add(leftCol);
		winCond.add(rightCol);
		winCond.add(midCol);
		winCond.add(topRow);
		winCond.add(diagLeft);
		winCond.add(diagRight);
		
		for (List l: winCond)
		{
			if(playerSpot.containsAll(l))
			{
				return "You Won!";
			}
			else if (CPUspot.containsAll(l))
			{
				return "You lost :/";
			}
			else if (playerSpot.size()+CPUspot.size() == 9)
			{
				return "Tie!!";
			}
		}
		return "";
		
	}
}
