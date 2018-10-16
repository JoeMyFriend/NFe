/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.bean.Emitente;

/**
 *
 * @author Joe
 */
public class EmitenteJTableModel extends AbstractTableModel {
    private List<Emitente> dados;
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
        
        switch (columnIndex){
            case 0:
                return emitente.getCNPJ();
            case 1:
                return emitente.getInscricaoEstadual();
            case 2:
                return emitente.getRazaoSocial();
        }
        
        return null;
    }
    
}
