/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.views;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author Miguel
 * @version $Revision: 1.0 $
 */
public class PlayingCardJPanel extends JPanel {

    private final int value;
    private boolean selected;
    private Image img;

    /**
     * Inner panels for displaying this card.
     */
    private final JPanel innerCardPanel;
    private final JLabel cardLabel;

    /**
     * Constructor for PlayingCardJPanel.
     * @param value int
     * @param selected boolean
     */
    public PlayingCardJPanel(int value, boolean selected) {
        this.value = value;
        this.selected = selected;
        
        this.updateBorder();

        this.innerCardPanel = new CardImgPanel();
        this.cardLabel = new JLabel();

        innerCardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        cardLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        cardLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cardLabel.setText(Integer.toString(value));

       GroupLayout innerCardPanelLayout = new GroupLayout(innerCardPanel);
        innerCardPanel.setLayout(innerCardPanelLayout);
        innerCardPanelLayout.setHorizontalGroup(
                innerCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(innerCardPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                        .addContainerGap())
        );
        
        innerCardPanelLayout.setVerticalGroup(
                innerCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(innerCardPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addContainerGap())
        );

        GroupLayout thisLayout = new GroupLayout(this);
        this.setLayout(thisLayout);
        thisLayout.setHorizontalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(innerCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        thisLayout.setVerticalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(innerCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }

    public void toggle() {
        this.selected = !this.selected;
        this.updateBorder();
    }
    
    public void select() {
        this.selected = true;
        this.updateBorder();
    }
    
    public void deselect() {
        this.selected = false;
        this.updateBorder();
    }

    private void updateBorder() {
        Color borderColor = (this.selected ? new Color(204, 255, 204) : Color.white);
        this.setBorder(BorderFactory.createLineBorder(borderColor, 10));
    }

    /**
     * Method getValue.
    
     * @return int */
    public int getValue() {
        return (this.selected ? value : 0);
    }

}
