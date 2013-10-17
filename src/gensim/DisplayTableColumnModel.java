/*
 * This file is part of GenSim.
 *
 * GenSim is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GenSim is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GenSim.  If not, see <http://www.gnu.org/licenses/>.
 */
package gensim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Andrew Vitkus
 */
public class DisplayTableColumnModel implements TableColumnModel {

    protected ArrayList<TableColumn> tableColumns;
    protected int columnMargin;
    protected ListSelectionModel selectionModel;
    protected EventListenerList listenerList = new EventListenerList();
    protected boolean columnSelectionAllowed;
    transient protected ChangeEvent changeEvent = null;

    public DisplayTableColumnModel() {
        tableColumns = new ArrayList<>();
        selectionModel = new DefaultListSelectionModel();
    }

    @Override
    public void addColumn(TableColumn aColumn) {
        tableColumns.add(aColumn);

        fireColumnAdded(new TableColumnModelEvent(this, 0, getColumnCount() - 1));
    }

    @Override
    public void removeColumn(TableColumn column) {
        int loc = tableColumns.indexOf(column);
        tableColumns.remove(loc);
        selectionModel.removeIndexInterval(loc, loc);
        fireColumnRemoved(new TableColumnModelEvent(this, 0, loc));
    }

    @Override
    public void moveColumn(int columnIndex, int newIndex) {
        if ((columnIndex < 0) || (columnIndex >= getColumnCount()) || (newIndex < 0) || (newIndex >= getColumnCount())) {
            throw new IllegalArgumentException("moveColumn() - Index out of range");
        }
        if (columnIndex != 0 && newIndex != 0) {
            TableColumn temp = tableColumns.remove(columnIndex);
            tableColumns.add(newIndex, temp);
        }

        fireColumnMoved(new TableColumnModelEvent(this, columnIndex, newIndex));
    }

    @Override
    public void setColumnMargin(int newMargin) {
        columnMargin = newMargin;
    }

    @Override
    public int getColumnCount() {
        return tableColumns.size();
    }

    @Override
    public Enumeration<TableColumn> getColumns() {
        return Collections.enumeration(tableColumns);
    }

    @Override
    public int getColumnIndex(Object columnIdentifier) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TableColumn getColumn(int columnIndex) {
        return tableColumns.get(columnIndex);
    }

    @Override
    public int getColumnMargin() {
        return columnMargin;
    }

    @Override
    public int getColumnIndexAtX(int xPosition) {
        if (xPosition < 0) {
            return -1;
        }
        int cc = getColumnCount();
        for (int column = 0; column < cc; column++) {
            xPosition -= getColumn(column).getWidth();
            if (xPosition < 0) {
                return column;
            }
        }
        return -1;
    }

    @Override
    public int getTotalColumnWidth() {
        int widths = 0;

        for (TableColumn col : tableColumns) {
            widths += col.getWidth();
        }

        return widths;
    }

    @Override
    public void setColumnSelectionAllowed(boolean flag) {
        columnSelectionAllowed = flag;
    }

    @Override
    public boolean getColumnSelectionAllowed() {
        return columnSelectionAllowed;
    }

    @Override
    public int[] getSelectedColumns() {
        int min = selectionModel.getMinSelectionIndex();
        int max = selectionModel.getMaxSelectionIndex();

        if ((min == -1) || (max == -1)) {
            return new int[0];
        }

        int[] selected = new int[max - min];

        int i = 0;

        for (; min < max; min++) {
            if (selectionModel.isSelectedIndex(min)) {
                selected[i] = min;
                i++;
            }
        }

        return Arrays.copyOfRange(selected, 0, i);
    }

    @Override
    public int getSelectedColumnCount() {
        int min = selectionModel.getMinSelectionIndex();
        int max = selectionModel.getMaxSelectionIndex();

        if ((min == -1) || (max == -1)) {
            return 0;
        }

        int selected = 0;

        for (; min < max; min++) {
            if (selectionModel.isSelectedIndex(min)) {
                selected++;
            }
        }

        return selected;
    }

    @Override
    public void setSelectionModel(ListSelectionModel newModel) {
        selectionModel = newModel;
    }

    @Override
    public ListSelectionModel getSelectionModel() {
        return selectionModel;
    }

    @Override
    public void addColumnModelListener(TableColumnModelListener x) {
        listenerList.add(TableColumnModelListener.class, x);
    }

    @Override
    public void removeColumnModelListener(TableColumnModelListener x) {
        listenerList.remove(TableColumnModelListener.class, x);
    }

    protected void fireColumnAdded(TableColumnModelEvent e) {
        TableColumnModelListener[] listeners = listenerList.getListeners(TableColumnModelListener.class);
        for (TableColumnModelListener listener : listeners) {
            listener.columnAdded(e);
        }
    }

    protected void fireColumnRemoved(TableColumnModelEvent e) {
        TableColumnModelListener[] listeners = listenerList.getListeners(TableColumnModelListener.class);
        for (TableColumnModelListener listener : listeners) {
            listener.columnRemoved(e);
        }
    }

    protected void fireColumnMoved(TableColumnModelEvent e) {
        TableColumnModelListener[] listeners = listenerList.getListeners(TableColumnModelListener.class);
        for (TableColumnModelListener listener : listeners) {
            listener.columnMoved(e);
        }
    }

    protected void fireColumnMarginChanged() {
        TableColumnModelListener[] listeners = listenerList.getListeners(TableColumnModelListener.class);
        for (TableColumnModelListener listener : listeners) {
            listener.columnMarginChanged(changeEvent);
        }
    }
}
