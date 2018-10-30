/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.bean.Emitente;

/**
 *
 * @author Joe
 */
public class EmitenteJTableModel extends AbstractTableModel {

    private List<Emitente> dados = new ArrayList<>();
    private String[] colunas = {"CNPJ", "Inscrição Estadual", "Razão Social"};

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Emitente emitente = dados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return emitente.getCNPJ();
            case 1:
                return emitente.getInscricaoEstadual();
            case 2:
                return emitente.getRazaoSocial();
        }

        return null;
    }

    public void addList(List<Emitente> emitentes) {
        int oldCount = getRowCount();

        dados.addAll(emitentes);

        fireTableRowsInserted(oldCount, getRowCount() - 1);
    }

    public void clear() {
        dados.clear();
        fireTableDataChanged();
    }
}
