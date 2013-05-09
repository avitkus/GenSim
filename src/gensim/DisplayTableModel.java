package gensim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Andrew
 */
public class DisplayTableModel implements TableModel {

    protected String[] columnNames;
    protected List<String[]> data;
    protected EventListenerList listenerList = new EventListenerList();

    DisplayTableModel(String[] columnNames) {
        this.columnNames = new String[columnNames.length + 2];
        this.columnNames[0] = "#";
        this.columnNames[1] = "Parent";
        int i = 2;
        for (String name : columnNames) {
            this.columnNames[i] = name;
            i++;
        }
        //this.columnNames = columnNames;
        data = Collections.synchronizedList(new ArrayList<String[]>());//new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex <= 1) {
            return Integer.class;
        } else {
            return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (data.get(rowIndex)[columnIndex]);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex] = aValue.toString();
        for (TableModelListener tml : listenerList.getListeners(TableModelListener.class)) {
            tml.tableChanged(new TableModelEvent(this, rowIndex, rowIndex, columnIndex, TableModelEvent.UPDATE));
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listenerList.add(TableModelListener.class, l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listenerList.remove(TableModelListener.class, l);
    }

    public void addRow(String[] row) {
        data.add(row);
        for (TableModelListener tml : listenerList.getListeners(TableModelListener.class)) {
            tml.tableChanged(new TableModelEvent(this, data.size() - 1, data.size() - 1, row.length, TableModelEvent.INSERT));
        }
    }
}