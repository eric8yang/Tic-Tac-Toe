import java.util.Arrays;

public class Game
{
    private GridPanel gp;
    private int[][] grid;
    // 3 by 3 array of integers:
    // -1 = not yet filled
    // 0 = X
    // 1 = O
    
    public Game() {
        grid = new int[3][3];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill( grid[i], -1 );
        }
    }
    
    public void setGridPanel(GridPanel gridPanel) {
        gp = gridPanel;
    }
    
    // -1 = not won
    //0 = X won
    //1 = O won
    public int isWon() {
        for (int i = 0; i < grid.length; i++) {
            if (checkCol(i) != -1) {
                return checkCol(i);
            }
            else if (checkRow(i) != -1) {
                return checkRow(i);
            }
        }
        
        if (checkDiagonals() != -1) {
            return checkDiagonals();
        }
        else {
            return full();
        }

    }
    
    private int checkCol (int i) {
        if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
            return grid[0][i];
        }
        return -1;
    }
    
    private int checkRow (int i) {
        if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
            return grid[i][0];
        }
        return -1;
    }
    
    private int checkDiagonals() {
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] || 
                        grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) {
            return grid[1][1];
        }
        return -1;
    }
    
    private int full() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == -1) {
                    return -1;
                }
            }
        }
        return 3;
    }
    
    public boolean addMove(int row, int col, int symbol) {
        if (grid[row][col] == -1) {
            grid[row][col] = symbol;
            //gp.repaint();
            return true;
        }
        return false;  
    }
    
    public boolean removeMove(int row, int col, int symbol) {
        if (grid[row][col] != -1) {
            grid[row][col] = -1;
            return true;
        }
        return false;
    }
    
    public int[][] getGrid(){
        return grid;
    }
}
