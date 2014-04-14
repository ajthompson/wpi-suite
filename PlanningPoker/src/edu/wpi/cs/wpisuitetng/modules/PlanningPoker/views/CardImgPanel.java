package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CardImgPanel extends JPanel {
    private Image img;
    
    @Override   
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		// paint the background image and scale it to fill the entire space
        if (img != null) {
        	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        	System.out.println("Width = " + this.getWidth());
        	System.out.println("Height = " + this.getHeight());
        }
    }
    
    public CardImgPanel() {
    	// Load the background image
        try {
            img = ImageIO.read(getClass().getResource("wpiCardFrontWPI_roundedEdges.png"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
