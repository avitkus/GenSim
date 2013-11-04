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
package gensim.windows;

import gensim.animals.Animal;
import gensim.animals.Chicken;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Andrew Vitkus
 */
public class ParentDisplayWindow extends JFrame {

    public void showParents(Chicken c) {
        if (c.getParents()[0] == null) {
            JOptionPane.showMessageDialog(rootPane, "No records exist for this chicken's parents!");
        } else {
            buildWindow();

            JTable table = new JTable();

            String[] animalTitles = Chicken.getTitles();

            DefaultTableModel tableModel = new DefaultTableModel();

            for (int i = 0; i < animalTitles.length; i++) {
                tableModel.addColumn(animalTitles[i]);
            }

            for (Animal parent : c.getParents()) {
                tableModel.addRow(parent.getPhenotypes());
            }

            DefaultTableColumnModel columnModel = new DefaultTableColumnModel();
            for (int i = 0; i < animalTitles.length; i++) {
                columnModel.addColumn(new TableColumn(i));
                columnModel.getColumn(i).setHeaderValue(animalTitles[i]);
                columnModel.getColumn(i).setPreferredWidth((int) (animalTitles[i].length() * 4.5));
            }
            table.setModel(tableModel);
            table.setColumnModel(columnModel);
            table.setEnabled(false);

            add(new JScrollPane(table), BorderLayout.CENTER);

            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    dispose();
                }
            });

            addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        dispose();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }

    private void buildWindow() {
        setSize(550, 87);
        setResizable(false);
        setLocationByPlatform(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/GenSim icon large.png")));
        setTitle("Chicken Parents' Phenotypes");
        setVisible(true);
    }
}
