import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;


public class Board extends JFrame implements ActionListener
{
    
    private static final int HEIGHT = 500;
    private static final int LENGTH = 500;
    private static Game g;
    private JPanel jp;
    private GridPanel gp;
    public static Player[] players;
    public int currentPlayer = 1;
    public int requiredMoves = 0;
    
    public Board() {
        super("Tic-Tac-Toe");
        JPanel jp = new JPanel();
        JLabel l1 = new JLabel( "Choose which player:" );
        l1.setBounds( LENGTH/2 - 75, HEIGHT/2 - 100, 200, 30 );

        JLabel l2 = new JLabel( "Tic-Tac-Toe Game" );
        l2.setBounds( LENGTH/2 - 150, HEIGHT/2 - 200, 300, 30 );
        l2.setFont (l2.getFont ().deriveFont (32.0f));
        
        JButton b1 = new JButton( "X (Goes first)" );
        b1.setBounds( LENGTH/2 - 200, HEIGHT/2 - 40, 150, 80 );
        b1.addActionListener( this );

        JButton b2 = new JButton( "O (Goes second)" );
        b2.setBounds( LENGTH/2 + 50, HEIGHT/2 - 40, 150, 80 );
        b2.addActionListener( this );

        jp.add( b1 ); 
        jp.add( b2 );
        jp.add( l1 );
        jp.add( l2 );

        jp.setSize( HEIGHT, LENGTH );
        setSize( HEIGHT, LENGTH );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        jp.setLayout( null );
        jp.setVisible(true);
        setVisible(true);
        add(jp);
        this.jp = jp;
    }
    
    public void startGame() {
        g = new Game();
        jp.setVisible( false );
        remove(jp);
        
        gp = new GridPanel(HEIGHT, LENGTH, g);
        add(gp);

        gp.repaint();
        
        g.setGridPanel( gp );

        
    }
    
    public void move()
    {
        currentPlayer = ( currentPlayer + 1 ) % 2;
        Player p = players[currentPlayer];
        int gameState = g.isWon();
        if ( gameState != -1 )
        {
            currentPlayer = ( currentPlayer + 1 ) % 2;
            p = players[currentPlayer];
            gp.repaint();
            if (gameState == 3) {
                gp.update("It's a tie!");
            }
            else {
                gp.update(p.getWinMessage());
            }
        }
        else {
            gp.repaint();
            p.makeMove();
        }

        
        
    }
    
    public void actionPerformed( ActionEvent e )
    {
        startGame();
        players = new Player[2];        
        JButton b = (JButton)e.getSource();
        if (b.getText().charAt( 0 ) == 'X') {
            players[0] = new HumanPlayer (this, g, 0, gp);
            players[1] = new ComputerPlayer(this, g, 1);
        }
        else {
            players[1] = new HumanPlayer (this, g, 1, gp);
            players[0] = new ComputerPlayer(this, g, 0);
        }
        move();
    }
    
    public static void main( String[] args )
    {
        Board b = new Board();
    }
}