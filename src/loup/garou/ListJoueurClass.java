/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fullc
 */
public class ListJoueurClass extends AbstractTableModel {

    private List<Joueur> tabJoueur = new ArrayList<Joueur>();
    String[] col = new String[]{"Nom", "Role", "Statut"};

    @Override
    public int getRowCount() {
        return tabJoueur.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return tabJoueur.get(rowIndex).getNom();
            case 1:
                return tabJoueur.get(rowIndex).getRole();
            case 2:
                return "statut";
            default:
                return null;
        }

    }

}
