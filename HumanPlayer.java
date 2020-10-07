import java.awt.*;

import java.awt.event.*;

public class HumanPlayer implements Player, MouseListener
{
    private int symbol;
    private Game g;
    private Board board;
    private boolean humanMove;
    
    public HumanPlayer(Board b, Game g, int s, GridPanel gp) {
        symbol = s;
        this.g = g;
        gp.addMouseListener( this );
        board = b;
    }
    
    public void mouseReleased( MouseEvent e )
    {
        int x = e.getX();
        int y = e.getY();
        
        int col = (x - 100) / 100;
        int row = (y - 100) / 100;
        
        if (humanMove) {
            g.addMove( row, col, symbol);
            humanMove = !humanMove;
            board.move();
        }
    }
    
    public void makeMove()
    {
        humanMove = true;
        
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
    
    public void mouseClicked( MouseEvent e )
    {
    }
    public void mousePressed( MouseEvent e )
    {  
    }
    public void mouseEntered( MouseEvent e )
    {
    }
    public void mouseExited( MouseEvent e )
    {      
    }

}
