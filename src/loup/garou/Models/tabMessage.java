/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou.Models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jeremy
 */
public class tabMessage extends AbstractTableModel{
    private static List<String> tabMsgLoup = new ArrayList<>();
    String[] col = new String[]{"Message"};
    
    public tabMessage() {
    }

    public List<String> getTabMsgLoup() {
        return tabMsgLoup;
    }
    
    public void setMessage(String Msg){
        tabMsgLoup.add(Msg);
    }
    
    @Override
    public int getRowCount() {
        return tabMsgLoup.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return col[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
