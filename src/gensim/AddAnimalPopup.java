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
import java.util.Observable;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Andrew Vitkus
 */
public class AddAnimalPopup extends JFrame {

    private String[] genotype = new String[7];
    private Integer[] geneSelections = {0, 0, 0, 0, 0, 0, 0};
    private static EventListenerList listenerList = new EventListenerList();
    private AddAnimalEvent addAnimalEvent = null;

    public void showAddAnimalDialog() {
        setup();
    }

    private void buildGenotypeFromPhenotype() {
        if (geneSelections[2] == 1) {
            genotype[0] = "Z";
            if (geneSelections[0] == 0) {
                genotype[0] += Math.random() > .5 ? "Z" : "z";
            } else {
                genotype[0] += "W";
            }
        } else {
            genotype[0] = "z";
            if (geneSelections[0] == 0) {
                genotype[0] += "z";
            } else {
                genotype[0] += "W";
            }
        }

        if (geneSelections[3] == 0) {
            genotype[1] = "FF";
        } else if (geneSelections[3] == 1) {
            genotype[1] = "Ff";
        } else {
            genotype[1] = "ff";
        }

        if (geneSelections[1] == 1) {
            genotype[2] = "ee";
            genotype[3] = Math.random() > .5 ? "CC" : "Cc";
        } else if (geneSelections[1] == 2) {
            genotype[2] = "ee";
            genotype[3] = "cc";
        } else {
            Random rand = new Random();

            switch (rand.nextInt(6)) {
                case 0:
                    genotype[2] = "EE";
                    genotype[3] = "CC";
                    break;
                case 1:
                    genotype[2] = "EE";
                    genotype[3] = "Cc";
                    break;
                case 2:
                    genotype[2] = "EE";
                    genotype[3] = "cc";
                    break;
                case 3:
                    genotype[2] = "Ee";
                    genotype[3] = "CC";
                    break;
                case 4:
                    genotype[2] = "Ee";
                    genotype[3] = "Cc";
                    break;
                case 5:
                    genotype[2] = "Ee";
                    genotype[3] = "cc";
                    break;
            }
        }

        genotype[4] = geneSelections[4] == 0 ? "cc" : "Cc";

        if (geneSelections[5] == 0) {
            genotype[5] = "B";
            genotype[5] += Math.random() < .5 ? "B" : "b";
        } else {
            genotype[5] = "bb";
        }
        if (geneSelections[6] == 0) {
            genotype[6] = "B";
            genotype[6] += Math.random() > .5 ? "B" : "b";
        } else {
            genotype[6] = "bb";
        }
    }

    private void setup() {
        setSize(300, 375);
        setResizable(false);
        setTitle("Select traits");
        setLocationByPlatform(true);

        buildWindow();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/GenSim icon large.png")));

        setVisible(true);
    }

    private void buildWindow() {
        setLayout(new GridBagLayout());

        addComponent(new JLabel("Gender:"), GridBagConstraints.BOTH, 6, 0, 0);

        ButtonGroup genderButtons = new ButtonGroup();
        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[0] = 0;
            }
        });
        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[0] = 1;
            }
        });
        genderButtons.add(maleButton);
        genderButtons.add(femaleButton);
        genderButtons.setSelected(maleButton.getModel(), true);

        addComponent(maleButton, 3, 0, 1);
        addComponent(femaleButton, 3, 4, 1);

        addComponent(new JLabel("Feather Color:"), GridBagConstraints.BOTH, 6, 0, 2);
        ButtonGroup featherColorButtons = new ButtonGroup();
        JRadioButton blackFeatherButton = new JRadioButton("Black");
        blackFeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[1] = 0;
            }
        });
        JRadioButton columbianFeatherButton = new JRadioButton("Columbian");
        columbianFeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[1] = 1;
            }
        });
        JRadioButton wheatenFeatherButton = new JRadioButton("Wheaten");
        wheatenFeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[1] = 2;
            }
        });
        featherColorButtons.add(blackFeatherButton);
        featherColorButtons.add(columbianFeatherButton);
        featherColorButtons.add(wheatenFeatherButton);
        featherColorButtons.setSelected(blackFeatherButton.getModel(), true);

        addComponent(blackFeatherButton, 2, 0, 3);
        addComponent(columbianFeatherButton, 2, 3, 3);
        addComponent(wheatenFeatherButton, 2, 5, 3);

        addComponent(new JLabel("Feather Decorations:"), GridBagConstraints.BOTH, 6, 0, 4);
        ButtonGroup barredFeatherStyleButtons = new ButtonGroup();
        JRadioButton solidFeatherButton = new JRadioButton("Solid");
        solidFeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[2] = 0;
            }
        });
        JRadioButton barredFeatherButton = new JRadioButton("Barred");
        barredFeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[2] = 1;
            }
        });
        barredFeatherStyleButtons.add(solidFeatherButton);
        barredFeatherStyleButtons.add(barredFeatherButton);
        barredFeatherStyleButtons.setSelected(solidFeatherButton.getModel(), true);

        addComponent(solidFeatherButton, 3, 0, 5);
        addComponent(barredFeatherButton, 3, 4, 5);

        addComponent(new JLabel("Feather Style:"), GridBagConstraints.BOTH, 6, 0, 6);
        ButtonGroup featherStyleButtons = new ButtonGroup();
        JRadioButton normalFeatherButton = new JRadioButton("Normal");
        normalFeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[3] = 0;
            }
        });
        JRadioButton frizzleFeatherButton = new JRadioButton("Frizzle");
        frizzleFeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[3] = 1;
            }
        });
        JRadioButton curlyFeatherButton = new JRadioButton("Curly");
        curlyFeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[3] = 2;
            }
        });
        featherStyleButtons.add(normalFeatherButton);
        featherStyleButtons.add(frizzleFeatherButton);
        featherStyleButtons.add(curlyFeatherButton);
        featherStyleButtons.setSelected(normalFeatherButton.getModel(), true);

        addComponent(normalFeatherButton, 2, 0, 7);
        addComponent(frizzleFeatherButton, 2, 3, 7);
        addComponent(curlyFeatherButton, 2, 5, 7);

        addComponent(new JLabel("Leg Style:"), GridBagConstraints.BOTH, 6, 0, 8);
        ButtonGroup legStypeButtons = new ButtonGroup();
        JRadioButton normalLegButton = new JRadioButton("Normal");
        normalLegButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[4] = 0;
            }
        });
        JRadioButton creeperLegButton = new JRadioButton("Creeper");
        creeperLegButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[4] = 1;
            }
        });
        legStypeButtons.add(normalLegButton);
        legStypeButtons.add(creeperLegButton);
        legStypeButtons.setSelected(normalLegButton.getModel(), true);

        addComponent(normalLegButton, 3, 0, 9);
        addComponent(creeperLegButton, 3, 4, 9);

        addComponent(new JLabel("Breda Comb:"), GridBagConstraints.BOTH, 6, 0, 10);
        ButtonGroup bredaCombButtons = new ButtonGroup();
        JRadioButton combButton = new JRadioButton("Comb");
        combButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[5] = 0;
            }
        });
        JRadioButton comblessButton = new JRadioButton("Combless");
        comblessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[5] = 1;
            }
        });
        bredaCombButtons.add(combButton);
        bredaCombButtons.add(comblessButton);
        bredaCombButtons.setSelected(combButton.getModel(), true);

        addComponent(combButton, 3, 0, 11);
        addComponent(comblessButton, 3, 4, 11);

        addComponent(new JLabel("Egg Shell Color:"), GridBagConstraints.BOTH, 6, 0, 12);
        ButtonGroup shellColorButtons = new ButtonGroup();
        JRadioButton blueShellButton = new JRadioButton("Blue");
        blueShellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[6] = 0;
            }
        });
        JRadioButton whiteShellButton = new JRadioButton("White");
        whiteShellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[6] = 1;
            }
        });
        shellColorButtons.add(blueShellButton);
        shellColorButtons.add(whiteShellButton);
        shellColorButtons.setSelected(blueShellButton.getModel(), true);

        addComponent(blueShellButton, 3, 0, 13);
        addComponent(whiteShellButton, 3, 4, 13);


        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildGenotypeFromPhenotype();
                Animal a = new Chicken(genotype);
                addAnimalEvent = new AddAnimalEvent(a, this);
                fireAddAnimalEvent();
                removeAll();
                dispose();
            }
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                dispose();
            }
        });
        
        addComponent(addButton, 1, 1, 14);
        addComponent(cancelButton, 1, 5, 14);
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

    private void addComponent(Component component, int fill, int width, int x, int y) {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = fill;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = width;
        c.gridx = x;
        c.gridy = y;

        add(component, c);
    }

    public void addAddAnimalEventListener(AddAnimalEventListener l) {
        listenerList.add(AddAnimalEventListener.class, l);
    }

    public void removeAddAnimalEventListener(AddAnimalEventListener l) {
        listenerList.remove(AddAnimalEventListener.class, l);
    }

    protected void fireAddAnimalEvent() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == AddAnimalEventListener.class) {
                // Lazily create the event:
                if (addAnimalEvent == null) {
                    addAnimalEvent = new AddAnimalEvent(this);
                }
                ((AddAnimalEventListener) listeners[i + 1]).animalAdded(addAnimalEvent);
            }
        }
    }
}
