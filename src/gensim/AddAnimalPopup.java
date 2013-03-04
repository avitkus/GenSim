package gensim;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author Andrew Vitkus
 * @author Teddy Wong
 */
public class AddAnimalPopup extends JFrame {

    private String[] genotype = new String[6];
    private Integer[] geneSelections = new Integer[6];
    protected int selection = -1;

    public String[] showAddAnimalDialog() {
        setup();

        while (selection == -1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }

        removeAll();
        dispose();

        if (selection == 0) {
            buildGenotypeFromPhenotype();
            return genotype;
        }
        return null;
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
            genotype[2] = "ww";
            genotype[3] = Math.random() > .5 ? "CC" : "Cc";
        } else {
            Random rand = new Random();

            switch (rand.nextInt(7)) {
                case 0:
                    genotype[2] = "WW";
                    genotype[3] = "CC";
                    break;
                case 1:
                    genotype[2] = "WW";
                    genotype[3] = "Cc";
                    break;
                case 2:
                    genotype[2] = "WW";
                    genotype[3] = "cc";
                    break;
                case 3:
                    genotype[2] = "Ww";
                    genotype[3] = "CC";
                    break;
                case 4:
                    genotype[2] = "Ww";
                    genotype[3] = "Cc";
                    break;
                case 5:
                    genotype[2] = "Ww";
                    genotype[3] = "cc";
                    break;
                case 6:
                    genotype[2] = "ww";
                    genotype[3] = "cc";
                    break;
            }
        }

        genotype[4] = geneSelections[4] == 0 ? "cc" : "Cc";

        if (geneSelections[5] == 1) {
            genotype[5] = "B";
            genotype[5] += Math.random() > .5 ? "B" : "b";
        } else {
            genotype[5] = "bb";
        }
    }

    private void setup() {
        setSize(225, 375);
        setResizable(false);
        setTitle("Select traits");
        setLocationByPlatform(true);

        buildWindow();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

        addComponent(maleButton, 3, 0, 1);
        addComponent(femaleButton, 3, 4, 1);

        addComponent(new JLabel("Feather Color:"), GridBagConstraints.BOTH, 6, 0, 2);
        ButtonGroup featherColorButtons = new ButtonGroup();
        JRadioButton whiteFeatherButton = new JRadioButton("White");
        whiteFeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[1] = 0;
            }
        });
        JRadioButton colorFeatherButton = new JRadioButton("Colored");
        colorFeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[1] = 1;
            }
        });
        featherColorButtons.add(whiteFeatherButton);
        featherColorButtons.add(colorFeatherButton);

        addComponent(whiteFeatherButton, 3, 0, 3);
        addComponent(colorFeatherButton, 3, 4, 3);

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

        addComponent(normalLegButton, 3, 0, 9);
        addComponent(creeperLegButton, 3, 4, 9);

        addComponent(new JLabel("Egg Shell Color:"), GridBagConstraints.BOTH, 6, 0, 10);
        ButtonGroup shellColorButtons = new ButtonGroup();
        JRadioButton blueShellButton = new JRadioButton("Blue");
        blueShellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[5] = 0;
            }
        });
        JRadioButton whiteShellButton = new JRadioButton("White");
        whiteShellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneSelections[5] = 1;
            }
        });
        shellColorButtons.add(blueShellButton);
        shellColorButtons.add(whiteShellButton);

        addComponent(blueShellButton, 3, 0, 11);
        addComponent(whiteShellButton, 3, 4, 11);


        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selection = 0;
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selection = 1;
            }
        });


        addComponent(addButton, 1, 1, 12);
        addComponent(cancelButton, 1, 5, 12);
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
}
