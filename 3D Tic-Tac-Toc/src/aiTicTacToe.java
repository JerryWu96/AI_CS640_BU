import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.*;

public class aiTicTacToe {

	public int player; //1 for player 1 and 2 for player 2
	private List<List<positionTicTacToe>> winningLines  = new ArrayList<>(); // Added winningLines for termination
	private List<positionTicTacToe> curBoard = new ArrayList<positionTicTacToe>(64);
	private int[][][] heuristics = new int[4][4][4];
	private int lookAhead = 2;
	private int lookAheadCounter = 0;

	private int getStateOfPositionFromBoard(positionTicTacToe position)
	{
		//a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
		int index = position.x*16+position.y*4+position.z;
		return curBoard.get(index).state;
	}


	private List<List<positionTicTacToe>> initializeWinningLines()
	{
		//create a list of winning line so that the game will "brute-force" check if a player satisfied any winning condition(s).
		List<List<positionTicTacToe>> winningLines = new ArrayList<List<positionTicTacToe>>();

		//48 straight winning lines
		//z axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,j,0,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,1,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,2,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,3,-1));
				winningLines.add(oneWinCondtion);
			}
		//y axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,0,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,1,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,2,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,3,j,-1));
				winningLines.add(oneWinCondtion);
			}
		//x axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(1,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(2,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(3,i,j,-1));
				winningLines.add(oneWinCondtion);
			}

		//12 main diagonal winning lines
		//xz plane-4
		for(int i = 0; i<4; i++)
		{
			List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
			oneWinCondtion.add(new positionTicTacToe(0,i,0,-1));
			oneWinCondtion.add(new positionTicTacToe(1,i,1,-1));
			oneWinCondtion.add(new positionTicTacToe(2,i,2,-1));
			oneWinCondtion.add(new positionTicTacToe(3,i,3,-1));
			winningLines.add(oneWinCondtion);
		}
		//yz plane-4
		for(int i = 0; i<4; i++)
		{
			List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
			oneWinCondtion.add(new positionTicTacToe(i,0,0,-1));
			oneWinCondtion.add(new positionTicTacToe(i,1,1,-1));
			oneWinCondtion.add(new positionTicTacToe(i,2,2,-1));
			oneWinCondtion.add(new positionTicTacToe(i,3,3,-1));
			winningLines.add(oneWinCondtion);
		}
		//xy plane-4
		for(int i = 0; i<4; i++)
		{
			List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
			oneWinCondtion.add(new positionTicTacToe(0,0,i,-1));
			oneWinCondtion.add(new positionTicTacToe(1,1,i,-1));
			oneWinCondtion.add(new positionTicTacToe(2,2,i,-1));
			oneWinCondtion.add(new positionTicTacToe(3,3,i,-1));
			winningLines.add(oneWinCondtion);
		}

		//12 anti diagonal winning lines
		//xz plane-4
		for(int i = 0; i<4; i++)
		{
			List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
			oneWinCondtion.add(new positionTicTacToe(0,i,3,-1));
			oneWinCondtion.add(new positionTicTacToe(1,i,2,-1));
			oneWinCondtion.add(new positionTicTacToe(2,i,1,-1));
			oneWinCondtion.add(new positionTicTacToe(3,i,0,-1));
			winningLines.add(oneWinCondtion);
		}
		//yz plane-4
		for(int i = 0; i<4; i++)
		{
			List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
			oneWinCondtion.add(new positionTicTacToe(i,0,3,-1));
			oneWinCondtion.add(new positionTicTacToe(i,1,2,-1));
			oneWinCondtion.add(new positionTicTacToe(i,2,1,-1));
			oneWinCondtion.add(new positionTicTacToe(i,3,0,-1));
			winningLines.add(oneWinCondtion);
		}
		//xy plane-4
		for(int i = 0; i<4; i++)
		{
			List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
			oneWinCondtion.add(new positionTicTacToe(0,3,i,-1));
			oneWinCondtion.add(new positionTicTacToe(1,2,i,-1));
			oneWinCondtion.add(new positionTicTacToe(2,1,i,-1));
			oneWinCondtion.add(new positionTicTacToe(3,0,i,-1));
			winningLines.add(oneWinCondtion);
		}

		//4 additional diagonal winning lines
		List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,0,0,-1));
		oneWinCondtion.add(new positionTicTacToe(1,1,1,-1));
		oneWinCondtion.add(new positionTicTacToe(2,2,2,-1));
		oneWinCondtion.add(new positionTicTacToe(3,3,3,-1));
		winningLines.add(oneWinCondtion);

		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,0,3,-1));
		oneWinCondtion.add(new positionTicTacToe(1,1,2,-1));
		oneWinCondtion.add(new positionTicTacToe(2,2,1,-1));
		oneWinCondtion.add(new positionTicTacToe(3,3,0,-1));
		winningLines.add(oneWinCondtion);

		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(3,0,0,-1));
		oneWinCondtion.add(new positionTicTacToe(2,1,1,-1));
		oneWinCondtion.add(new positionTicTacToe(1,2,2,-1));
		oneWinCondtion.add(new positionTicTacToe(0,3,3,-1));
		winningLines.add(oneWinCondtion);

		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,3,0,-1));
		oneWinCondtion.add(new positionTicTacToe(1,2,1,-1));
		oneWinCondtion.add(new positionTicTacToe(2,1,2,-1));
		oneWinCondtion.add(new positionTicTacToe(3,0,3,-1));
		winningLines.add(oneWinCondtion);

		return winningLines;

	}


	public boolean isWin(int player, positionTicTacToe nextMove)
	{
		int index = nextMove.x * 16 + nextMove.y * 4 + nextMove.z;
		//curBoard.get(index).state = player;

		for(int i = 0; i < winningLines.size(); i++)
		{
			positionTicTacToe p0 = winningLines.get(i).get(0);
			positionTicTacToe p1 = winningLines.get(i).get(1);
			positionTicTacToe p2 = winningLines.get(i).get(2);
			positionTicTacToe p3 = winningLines.get(i).get(3);

			int state0 = getStateOfPositionFromBoard(p0);
			int state1 = getStateOfPositionFromBoard(p1);
			int state2 = getStateOfPositionFromBoard(p2);
			int state3 = getStateOfPositionFromBoard(p3);
			//if they have the same state (marked by same player) and they are not all marked.
			if (state0 == player && state0 == state1 && state1 == state2 && state2 == state3)
			{
				curBoard.get(index).state = 0;
				return true;
			}
		}
		return false;
	}


	public positionTicTacToe blockMove(int player)
	{
		//int index = nextMove.x * 16 + nextMove.y * 4 + nextMove.z;
		//curBoard.get(index).state = player;
		int opponent = (player == 1 ? 2 : 1);
		positionTicTacToe blockMove = new positionTicTacToe(-1, -1, -1, player);
		for(int i = 0; i < winningLines.size(); i++)
		{
			positionTicTacToe p0 = winningLines.get(i).get(0);
			positionTicTacToe p1 = winningLines.get(i).get(1);
			positionTicTacToe p2 = winningLines.get(i).get(2);
			positionTicTacToe p3 = winningLines.get(i).get(3);

			int state0 = getStateOfPositionFromBoard(p0);
			int state1 = getStateOfPositionFromBoard(p1);
			int state2 = getStateOfPositionFromBoard(p2);
			int state3 = getStateOfPositionFromBoard(p3);
			if (state0 == opponent && state1 == opponent && state2 == opponent && state3 == 0)
			{
				blockMove.x = p3.x;
				blockMove.y = p3.y;
				blockMove.z = p3.z;
				blockMove.state = player;
			}
			//return blockMove = p3;
			else if (state0 == opponent && state1 == opponent && state2 == 0 && state3 == opponent)
			{
				blockMove.x = p2.x;
				blockMove.y = p2.y;
				blockMove.z = p2.z;
				blockMove.state = player;
			}
			//return blockMove = p2;
			else if (state0 == opponent && state1 == 0 && state2 == opponent && state3 == opponent)
			{
				blockMove.x = p1.x;
				blockMove.y = p1.y;
				blockMove.z = p1.z;
				blockMove.state = player;
			}
			//return blockMove = p1;
			else if (state0 == 0 && state1 == opponent && state2 == opponent && state3 == opponent)
			{
				blockMove.x = p0.x;
				blockMove.y = p0.y;
				blockMove.z = p0.z;
				blockMove.state = player;
			}
		}
		return blockMove;
	}

	public positionTicTacToe winMove(int player)
	{
		positionTicTacToe nextMove = new positionTicTacToe(-1, -1, -1, player);
		for(int i = 0; i < winningLines.size(); i++)
		{
			positionTicTacToe p0 = winningLines.get(i).get(0);
			positionTicTacToe p1 = winningLines.get(i).get(1);
			positionTicTacToe p2 = winningLines.get(i).get(2);
			positionTicTacToe p3 = winningLines.get(i).get(3);

			int state0 = getStateOfPositionFromBoard(p0);
			int state1 = getStateOfPositionFromBoard(p1);
			int state2 = getStateOfPositionFromBoard(p2);
			int state3 = getStateOfPositionFromBoard(p3);

			int playerCounter = 0;
			if(state0 == player)
				playerCounter++;
			if(state1 == player)
				playerCounter++;
			if(state2 == player)
				playerCounter++;
			if(state3 == player)
				playerCounter++;
			if (playerCounter == 3)
			{
				if (state3 == 0) {
					nextMove.x = p3.x;
					nextMove.y = p3.y;
					nextMove.z = p3.z;
					return nextMove;
				}
				//return blockMove = p3;
				else if (state2 == 0) {
					nextMove.x = p2.x;
					nextMove.y = p2.y;
					nextMove.z = p2.z;
					return nextMove;
				}
				//return blockMove = p2;
				else if (state1 == 0) {
					nextMove.x = p1.x;
					nextMove.y = p1.y;
					nextMove.z = p1.z;
					return nextMove;
				}
				//return blockMove = p1;
				else if (state0 == 0) {
					nextMove.x = p0.x;
					nextMove.y = p0.y;
					nextMove.z = p0.z;
					return nextMove;
				}
			}
		}
		return nextMove;
	}


	private int heuristic(int player)
	{
		int opponent = (player == 1 ? 2 : 1);
		int winCounter = 0; // number of possible winning lines
		int loseCounter = 0; // number of possible losing lines
		for(int i = 0; i < winningLines.size(); i++)
		{
			positionTicTacToe p0 = winningLines.get(i).get(0);
			positionTicTacToe p1 = winningLines.get(i).get(1);
			positionTicTacToe p2 = winningLines.get(i).get(2);
			positionTicTacToe p3 = winningLines.get(i).get(3);

			int state0 = getStateOfPositionFromBoard(p0);
			int state1 = getStateOfPositionFromBoard(p1);
			int state2 = getStateOfPositionFromBoard(p2);
			int state3 = getStateOfPositionFromBoard(p3);

			int playerCounter = 0;
			int opponentCounter = 0;

			if(state0 == player)
				playerCounter++;
			else if(state0 == opponent)
				opponentCounter++;
			if(state1 == player)
				playerCounter++;
			else if(state1 == opponent)
				opponentCounter++;
			if(state2 == player)
				playerCounter++;
			else if(state2 == opponent)
				opponentCounter++;
			if(state3 == player)
				playerCounter++;
			else if(state3 == opponent)
				opponentCounter++;

			if(playerCounter == 3 && (state0 == 0 || state1 == 0 || state2 == 0 || state3 == 0))
			{
				// opponent is going to lose without placing at the fourth position
				//return Integer.MAX_VALUE;
				return 500;
			}
			if(opponentCounter == 3 && (state0 == 0 || state1 == 0 || state2 == 0 || state3 == 0))
			{
				// player is going to lose without placing at the fourth position
				//return Integer.MIN_VALUE;
				return -500;
			}
			// a possible winning line - no opponents occupied
			if(state0 != opponent && state1 != opponent && state2 != opponent && state3 != opponent)
				winCounter += playerCounter;
			// a possible losing line - no players occupied
			if(state0 != player && state1 != player && state2 != player && state3 != player)
				loseCounter += opponentCounter;
		}
		return winCounter - loseCounter;
	}




	/**
	 * Minimax method implemented with alpha-beta pruning
	 * @param player current maximizer
	 * @param isMax  decide if the current level is the maximizer's or the minimizer's turn
	 * @param Alpha  the minimum score that the maximizing player is assured of
	 * @param Beta   the maximum score that the minimizing player is assured of
	 * @return heuristic value of the current cell on the board
	 */
	private int lookAhead(int player, boolean isMax, int Alpha, int Beta)
	{
		int alpha = Alpha;
		int beta = Beta;

		int opponent = (player == 1 ? 2 : 1);
		if(lookAheadCounter <= lookAhead)
		{
			lookAheadCounter++;

			// player's turn
			if(isMax)
			{
				int hValue;
				positionTicTacToe myNextMove;
				for (int i = 0; i < 4; i++)
				{
					for (int j = 0; j < 4; j++)
					{
						for(int k = 0; k < 4; k++)
						{
							int index = i * 16 + j * 4 + k;
							//board.get(index).state = player;

							positionTicTacToe curPos = new positionTicTacToe(i, j, k);
							int curState = getStateOfPositionFromBoard(curPos);

							if(curState == 0)
							{
								myNextMove = new positionTicTacToe(i, j, k, player);
								curBoard.get(index).state = player;
								//Recursive look ahead
								hValue = lookAhead(player, !isMax, alpha, beta);
								if(hValue > alpha)
									alpha = hValue;
								curBoard.get(index).state = 0;
								//Break if the alpha value is larger than the beta value
								if (alpha >= beta)
									break;
							}
						}
					}
				}

				return alpha;
			}

			// opponent's turn
			else
			{
				int hValue;
				positionTicTacToe myNextMove;
				for (int i = 0; i < 4; i++)
				{
					for (int j = 0; j < 4; j++)
					{
						for(int k = 0; k < 4; k++)
						{
							int index = i * 16 + j * 4 + k;

							positionTicTacToe curPos = new positionTicTacToe(i, j, k);
							int curState = getStateOfPositionFromBoard(curPos);

							if(curState == 0)
							{
								myNextMove = new positionTicTacToe(i, j, k, opponent);
								//Recursive look ahead
								hValue = lookAhead(player, !isMax, alpha, beta);
								if(hValue < beta)
									beta = hValue;
								curBoard.get(index).state = 0;
								//Break if the alpha value is larger than the beta value
								if (alpha >= beta)
									break;
							}
						}
					}
				}

				return beta;
			}
		}
		// reach the end of the minimax function
		else
		{
			return heuristic(player);
		}
	}


	public positionTicTacToe myAIAlgorithmRand(List<positionTicTacToe> board, int player)
	{
		//TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
		positionTicTacToe myNextMove = new positionTicTacToe(0,0,0);
		curBoard.clear();
		curBoard = new ArrayList<positionTicTacToe>(board);
		do
		{
			Random rand = new Random();
			int x = rand.nextInt(4);
			int y = rand.nextInt(4);
			//int y = 0;
			int z = rand.nextInt(4);
			myNextMove = new positionTicTacToe(x,y,z);
		}while(getStateOfPositionFromBoard(myNextMove)!=0);
		return myNextMove;
	}

	/**
	 * Backtracking is used to traverse through all cells and evaluate minmax function for all empty cells.
	 * The algorithm evaluates the moves that lead to a terminal state based on the players’ turn.
	 * It will choose the move with maximum score when it is the AI’s turn and choose the move with the minimum score when it is the human player’s turn.
	 * Using this strategy, minimax avoids losing to the human player.
	 * @param board The current status of tictactoe board
	 * @return the best move
	 */
	public positionTicTacToe myAIAlgorithm(List<positionTicTacToe> board, int player)
	{
		//TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
		curBoard.clear();
		curBoard = new ArrayList<positionTicTacToe>(board);

		System.out.println("player " + player + "'s turn\n");
		int bestVal = -500;// Integer.MIN_VALUE;
		int hValue = 0;

		positionTicTacToe myNextMove;
		positionTicTacToe myBestMove = new positionTicTacToe(-1, -1, -1, player);

		positionTicTacToe blockMove = blockMove(player);
		if (blockMove.x != -1)
			return blockMove;

		positionTicTacToe winMove = winMove(player);
		if (winMove.x != -1)
			return winMove;

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				for (int k = 0; k < 4; k++)
				{
					int index = i * 16 + j * 4 + k;
					positionTicTacToe curPos = new positionTicTacToe(i, j, k);
					int curState = getStateOfPositionFromBoard(curPos);
					// 0 is state "not marked"
					if (curState == 0)
					{
						// make the move
						myNextMove = new positionTicTacToe(i, j, k, player);
						if(isWin(player, myNextMove))
						{
							System.out.println("Player" + player + "Wins!\n");
							return myNextMove;
						}
						else
						{
//							hValue = lookAhead(player, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
							hValue = lookAhead(player, true, -500, 500);
							lookAheadCounter = 0;
							heuristics[k][i][j] = hValue;
							if (hValue > bestVal)
							{
								bestVal = hValue;
								myBestMove.x = i;
								myBestMove.y = j;
								myBestMove.z = k;
							}
						}
					}
				}
		System.out.println("Heuristic values: \n");
		printHeuristic();
		return myBestMove;
	}


	public void printBoardTicTacToe()
	{
		//print in "graphical" display
		for (int i=0;i<4;i++)
		{
			System.out.println("level(z) "+i);
			for(int j=0;j<4;j++)
			{
				System.out.print("["); // boundary
				for(int k=0;k<4;k++)
				{
					if (getStateOfPositionFromBoard(new positionTicTacToe(j,k,i))==1)
					{
						System.out.print("X"); //print cross "X" for position marked by player 1
						System.out.print("X"); //print cross "X" for position marked by player 1
					}
					else if(getStateOfPositionFromBoard(new positionTicTacToe(j,k,i))==2)
					{
						System.out.print("O"); //print cross "O" for position marked by player 2
					}
					else if(getStateOfPositionFromBoard(new positionTicTacToe(j,k,i))==0)
					{
						System.out.print("_"); //print "_" if the position is not marked
					}
					if(k==3)
					{
						System.out.print("]"); // boundary
						System.out.println();
					}


				}

			}
			System.out.println();
		}
	}

	public void printHeuristic()
	{
		//print in "graphical" display
		for (int i=0;i<4;i++)
		{
			System.out.println("level(z) "+i);
			for(int j=0;j<4;j++)
			{
				System.out.print("["); // boundary
				for(int k=0;k<4;k++)
				{
					System.out.print(heuristics[i][j][k] + " "); //print cross "X" for position marked by player 1
					if(k==3)
					{
						System.out.print("]"); // boundary
						System.out.println();
					}
				}
			}
			System.out.println();
		}
	}

	public aiTicTacToe(int setPlayer)
	{
		player = setPlayer;
		winningLines = initializeWinningLines();
	}
}