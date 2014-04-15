/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models;

import javax.swing.table.AbstractTableModel;

/**
 * A Table Model that holds data and actions for the Team Members' Estimations table
 * @author hlong290494
 * @version $Revision: 1.0 $
 */
public class TeamEstimateTableModel extends AbstractTableModel{
	/**
	 * Table column 
	 */
	String[] columnNames = {"Username", "Estimation"};
	
	/**
	 * Table data - contains team estimation
	 */
	Object[][] data = {
			{"Long", "1"},
			{"Chris", "2"},
			{"Ryan", "4"}
	};

    /**
     * Method getColumnCount.
    
    
     * @return int * @see javax.swing.table.TableModel#getColumnCount() */
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Method getRowCount.
    
    
     * @return int * @see javax.swing.table.TableModel#getRowCount() */
    public int getRowCount() {
        return data.length;
    }

    /**
     * Method getColumnName.
     * @param col int
    
    
     * @return String * @see javax.swing.table.TableModel#getColumnName(int) */
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     * Method getValueAt.
     * @param row int
     * @param col int
    
    
     * @return Object * @see javax.swing.table.TableModel#getValueAt(int, int) */
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /**
     * Method getColumnClass.
     * @param c int
    
    
     * @return Class * @see javax.swing.table.TableModel#getColumnClass(int) */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /**
     * Method isCellEditable.
     * @param row int
     * @param col int
    
    
     * @return boolean * @see javax.swing.table.TableModel#isCellEditable(int, int) */
    public boolean isCellEditable(int row, int col) {
    	return false;
    }

    /**
     * Method setValueAt.
     * @param value Object
     * @param row int
     * @param col int
    
     * @see javax.swing.table.TableModel#setValueAt(Object, int, int) */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}