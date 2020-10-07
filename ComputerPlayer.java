import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

 
public class ComputerPlayer implements Player
{
    private int symbol;
    private int opponentSymbol;
    private Game g;
    private boolean humanMove;
    private int numMove;
    private Board board;
    
    public ComputerPlayer(Board b, Game g, int s) {
        symbol = s;
        if (s == 0) {
            opponentSymbol = 1;
        }
        else {
            opponentSymbol = 0;
        }
        this.g = g;
        numMove = 0;
        board = b;
    }

    
    public void makeMove()
    {
        numMove++;
        int[][] grid = g.getGrid();
        Game sample = new Game();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sample.addMove( i, j, grid[i][j] );
            }
        }
        
        
        //test each space
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sample.addMove(i, j, symbol)) {
                    //if you can win the game play there
                    if (sample.isWon() == symbol) {
                        g.addMove( i, j, symbol );
                        board.move();
                        return;
                    }
                    else {
                        sample.removeMove( i, j, symbol );
                    }
                }
            }
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sample.addMove(i, j, opponentSymbol)) {
                    //if you can block win play there
                    if (sample.isWon() == opponentSymbol) {
                        g.addMove( i, j, symbol );
                        board.move();
                        return;
                    }
                    else {
                        sample.removeMove( i, j, opponentSymbol );
                    }
                }
            }
        }

        //if going first
        if (symbol == 0) {
            
            if (numMove == 1) {
                g.addMove( 0, 0, symbol );
                board.move();
            }
            
            else if (numMove == 2) {
                if (grid[0][1] != -1 || grid[0][2] != -1 
                                || grid[1][2] != -1 || grid[2][2] != -1) {
                    g.addMove( 2, 0, symbol );
                    board.move();
                }
                else if (grid[1][0] != -1 || grid[2][0] != -1 
                                || grid[2][1] != -1) {
                    g.addMove( 0, 2, symbol );
                    board.move();
                }
                else {
                    g.addMove( 2, 2, symbol );
                    board.move();
                }
            }
            
            else if (numMove == 3) {
                if (grid[2][2] == -1) {
                    g.addMove( 2, 2, symbol );
                    board.move();
                }
                else {
                    if (grid[0][2] == symbol) {
                        g.addMove( 2, 0, symbol );
                        board.move();
                    }
                    else {
                        g.addMove( 0, 2, symbol );
                        board.move();
                    }
                }
            }
            else {
                int[] coords = getRandom(grid);
                g.addMove( coords[0], coords[1], symbol );
                board.move();
            }
        }
        
        //if going second
        else {
            int[] coords = getRandom(grid);
            g.addMove( coords[0], coords[1], symbol );
            board.move();
        }
    }
    
    public int[] getRandom(int[][] grid) {
        ArrayList<int[]> choices = new ArrayList<int[]>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == -1) {
                    int[] coord = {i , j};
                    choices.add( coord );
                }
            }
        }
        int choice = (int)(Math.random() * choices.size());
        return choices.get( choice );
    }
    
    public String getWinMessage()
    {
        if (symbol == 0) {
            return "X wins!";
        }
        else {
            return "O wins!";
        }

    }

}
