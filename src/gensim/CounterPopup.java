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

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Andrew Vitkus
 */
public class CounterPopup extends JFrame {

    private ArrayList<Animal> animals;
    private String[] keys = new String[7];

    public void showCounterDialog(ArrayList<Animal> animals) {
        this.animals = animals;

        Arrays.fill(keys, "Any");

        buildDisplay();

        setLocationByPlatform(true);
        setSize(300, 250);

        setTitle("Counter");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        }

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/GenSim icon large.png")));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (hasFocus() && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        setVisible(true);
    }

    private void buildDisplay() {
        setLayout(new GridBagLayout());

        final JLabel count = new JLabel("Total: " + animals.size());
        addComponent(count, 1, 2, 3);

        addComponent(new JLabel("Gender:"), 1, 0, 0);
        final JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Any", "Male", "Female"});
        genderCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[0] = genderCombo.getItemAt(genderCombo.getSelectedIndex());
                count.setText("Total: " + count());
            }
        });
        addComponent(genderCombo, 1, 1, 0);

        addComponent(new JLabel("Feather Style:"), 1, 0, 1);
        final JComboBox<String> featherStyleCombo = new JComboBox<>(new String[]{"Any", "Normal", "Frizzle", "Curly"});
        featherStyleCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[1] = featherStyleCombo.getItemAt(featherStyleCombo.getSelectedIndex());
                count.setText("Total: " + count());
            }
        });
        addComponent(featherStyleCombo, 1, 1, 1);

        addComponent(new JLabel("Feather Decoration:"), 1, 0, 2);
        final JComboBox<String> featherDecorationCombo = new JComboBox<>(new String[]{"Any", "Solid", "Barred"});
        featherDecorationCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[2] = featherDecorationCombo.getItemAt(featherDecorationCombo.getSelectedIndex());
                count.setText("Total: " + count());
            }
        });
        addComponent(featherDecorationCombo, 1, 1, 2);

        addComponent(new JLabel("Feather Color:"), 1, 0, 3);
        final JComboBox<String> featherColorCombo = new JComboBox<>(new String[]{"Any", "Black", "Columbian", "Wheaten"});
        featherColorCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[3] = featherColorCombo.getItemAt(featherColorCombo.getSelectedIndex());
                count.setText("Total: " + count());
            }
        });
        addComponent(featherColorCombo, 1, 1, 3);

        addComponent(new JLabel("Leg Style:"), 1, 0, 4);
        final JComboBox<String> legStyleCombo = new JComboBox<>(new String[]{"Any", "Normal", "Creeper"});
        legStyleCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[4] = legStyleCombo.getItemAt(legStyleCombo.getSelectedIndex());
                count.setText("Total: " + count());
            }
        });
        addComponent(legStyleCombo, 1, 1, 4);

        addComponent(new JLabel("Breda Comb:"), 1, 0, 5);
        final JComboBox<String> bredaCombCombo = new JComboBox<>(new String[]{"Any", "Comb", "Combless"});
        bredaCombCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[5] = bredaCombCombo.getItemAt(bredaCombCombo.getSelectedIndex());
                count.setText("Total: " + count());
            }
        });
        addComponent(bredaCombCombo, 1, 1, 5);

        addComponent(new JLabel("Egg Shell Color:"), 1, 0, 6);
        final JComboBox<String> eggShellColorCombo = new JComboBox<>(new String[]{"Any", "White", "Blue"});
        eggShellColorCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[6] = eggShellColorCombo.getItemAt(eggShellColorCombo.getSelectedIndex());
                count.setText("Total: " + count());
            }
        });
        addComponent(eggShellColorCombo, 1, 1, 6);
    }

    private int count() {
        int total = 0;

        for (Animal a : animals) {
            boolean match = true;
            for (int i = 0; i < keys.length; i++) {
                if (!keys[i].equals("Any")) {
                    if (!keys[i].equals(a.getPhenotypes()[i])) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                total++;
            }
        }

        return total;
    }

    private void addComponent(Component component, int width, int x, int y) {
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = width;
        c.gridx = x;
        c.gridy = y;

        add(component, c);
    }
}
