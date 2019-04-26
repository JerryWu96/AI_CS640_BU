# 3D Tic-Tac-Toe

This folder contains my third project created for BU CS640 Artificial Intelligence. 

## Problem Definition

3D tic-tac-toe is an  strategy board game, generally designed for two players taking turns and compete against each other. It is similar to the traditional 3 by 3 tic-tac-toe but is played in a cubic array of cells. For this particular game, I used 4 by 4 by 4 board.

Check out the wiki page for the rules:

<https://en.wikipedia.org/wiki/3D_tic-tac-toe>

## Algorithms

I implement a recursive minimax to serve as the core decision rule of my AI. It minimizes the possible loss for a worst case scenario. My AI is the maximizer and human player is the minimizer, consider the fact that human player is always taking the optimal move in every turn.

Alpha-beta pruning is used to greatly improve the computational complexity for deep lookahead settings. Each cell on board can have one of three states, 0 for cell being empty, 1 for being occupied by Player 1, 2 for being occupied by Player 2. 

There're in total 76 winning combinations for this scenario. A heuristic value is calculated for each of the cells that ultimatly determines the AI's next move. Heuristic value calculation can be another important factor which influences the performance of minimax.

My AI program is guaranteed to beat AI with randomness move and is able to determine the winning and loss positions in each turn. 

With increased lookAhead value, it is generally more  difficult for players to beat it. In comparison, my code is of medium difficulty compared to online tic-tac-toc games.

Please take a look at my HTML report for implementaitons of minimax and heuristic value calculation.

## File Structure
src:
- positionTicTacToe.java: Data structure of the board, with x, y, z coordinates and states.
- aiTicTacToe.java: The core method implements the strategy of my ai.
- runTicTacToe.java: The initialization of the board and settings. Entry method of the project.


report:
- My project report in HTML foramt.


## Run the Code

I used **JAVA** to implement. Please take a look at the run() method in runTicTacToe.java for details.

## Credits

Boston University Department of Computer Science, CS640, Professor Betke

This repo was created by:

Xiankang (Jerry) Wu
