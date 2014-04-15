/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author Miguel
 * @version $Revision: 1.0 $
 */
public class CardImgPanel extends JPanel {
    private Image img;
    
    /**
     * Method paintComponent.
     * @param g Graphics
     */
    @Override   
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		// paint the background image and scale it to fill the entire space
        if (img != null) {
        	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
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
