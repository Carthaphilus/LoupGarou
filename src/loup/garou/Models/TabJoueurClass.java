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
 * @author fullc
 */
public class TabJoueurClass extends AbstractTableModel {

    private List<Joueur> tabJoueur = new ArrayList<Joueur>();
    String[] col;

    public TabJoueurClass(List Joueurs, String[] entete) {
        tabJoueur = Joueurs;
        col = entete;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (Master.getInstance().amoureuxDefined() == false) {
            if (columnIndex == 3) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public int getRowCount() {
        return tabJoueur.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return col[columnIndex];
    }

    public void setValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            tabJoueur.get(rowIndex).setAmoureux();

        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return tabJoueur.get(rowIndex).getNom();
            case 1:
                return tabJoueur.get(rowIndex).getRole().getNom();
            case 2:
                if (tabJoueur.get(rowIndex).getTourMort() == 0) {
                    return "En vie";
                } else {
                    return "Mort";
                }
            case 3:
                return tabJoueur.get(rowIndex).getAmoureux();
            default:
                return null;
        }
    }

    public Joueur getJoueurInTab(int rowIndex) {
        return tabJoueur.get(rowIndex);
    }
}
