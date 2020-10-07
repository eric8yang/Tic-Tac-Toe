import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridPanel extends JPanel
{
    private int HEIGHT, LENGTH;
    private Game game;
    private BufferedImage[] shapes;
    private JLabel display;
    public GridPanel (int h, int l, Game g) {
        HEIGHT = h;
        LENGTH = l;
        setSize( HEIGHT, LENGTH );
        setLayout( null );
        
        game = g;
        
        JLabel l2 = new JLabel( "Tic-Tac-Toe Game" );
        l2.setBounds( LENGTH/2 - 150, HEIGHT/2 - 200, 300, 30 );
        l2.setFont (l2.getFont ().deriveFont (32.0f));
        add(l2);
       
        display = new JLabel("Your turn");
        display.setBounds( 175, 410, 300, 30 );
        display.setFont( display.getFont().deriveFont( 32.0f ) );
        add(display);
        
        
        try
        {
            BufferedImage circle = ImageIO.read(new File("circle.png"));
            BufferedImage cross = ImageIO.read(new File("X.png"));
            shapes = new BufferedImage[2];
            shapes[0] = cross;
            shapes[1] = circle;
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine( 200, 100, 200, 400 );
        g.drawLine( 300, 100, 300, 400 );
        g.drawLine( 100, 200, 400, 200 );
        g.drawLine( 100, 300, 400, 300 );
        
        int[][] grid = game.getGrid();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != -1) {
                    g.drawImage(shapes[grid[i][j]], 
                        (j + 1) * 100 + 5, (i + 1) * 100 + 5, 90, 90, this);
                }
            }
        }
    }
    
    public void update (String text) {
        display.setText(text);
    }
}
