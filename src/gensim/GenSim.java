package gensim;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.spi.IIORegistry;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Andrew
 */
public class GenSim extends JFrame implements Runnable {

    private File currentFile;
    private boolean isSaved;
    private ArrayList<Animal> animals;
    private int mother;
    private int father;
    private String[] animalTitles;
    private int clutchSize;
    private AtomicInteger animalCount;
    private JTable table;
    private DisplayTableModel tableModel;
    private StatusBar statusBar;
    private JToolBar toolBar;

    public GenSim() {
        super("GenSim");
        isSaved = true;
        animals = new ArrayList<>();
        currentFile = null;
        clutchSize = 4;
        animalTitles = Chicken.getTitles();
        statusBar = new StatusBar("Chicken");
        toolBar = new JToolBar();
        table = new JTable();

        mother = -1;
        father = -1;
        animalCount = new AtomicInteger(0);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage("GenSim icon large.png"));
        
        /*IIORegistry.getDefaultInstance().registerApplicationClasspathSpis();
        ImageIO.scanForPlugins();
        try {
            File image = new File("GenSim Logo.png");
            if (image.exists()) {
                Image icon = ImageIO.read(image);
                setIconImage(icon);
            }
        } catch (IOException ex) {
            System.out.println("You dun goofed");
        }*/
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new GenSim());
    }

    @Override
    public void run() {
        setSize(675, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) { }

        makeMenuBar();
        makeToolBar();
        
        //setIconImage(null);
        
        setupTable();
        showStatusBar();
        showToolBar();

        add(new JScrollPane(table), BorderLayout.CENTER);
        
        setVisible(true);
    }

    public void makeMenuBar() {
        JMenuBar mbar = new JMenuBar();
        setJMenuBar(mbar);

        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        JMenuItem printItem = new JMenuItem("Print");
        JMenuItem quitItem = new JMenuItem("Quit");

        fileMenu.setMnemonic('f');

        newItem.setAccelerator(KeyStroke.getKeyStroke("control N"));
        newItem.setMnemonic('n');
        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JFileChooser.APPROVE_OPTION;
                if (currentFile == null) {
                    choice = selectSaveFile();
                }

                if (!isSaved && choice == JFileChooser.APPROVE_OPTION) {
                    try {
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentFile))) {
                            oos.writeObject(animals);
                            oos.writeInt(father);
                            oos.writeInt(mother);
                        }
                    } catch (FileNotFoundException ex) {
                        System.out.printf("File %s not found\n", currentFile.getAbsolutePath());
                    } catch (IOException ex) {
                    }
                }

                animalCount.set(0);
                statusBar.setCount(0);
                animals.clear();
                father = -1;
                mother = -1;
                setupTable();
                isSaved = true;
                fixTitle();
            }
        });

        openItem.setAccelerator(KeyStroke.getKeyStroke("control O"));
        openItem.setMnemonic('o');
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectOpenFile() == JFileChooser.APPROVE_OPTION) {
                    try {
                        animals.clear();
                        father = -1;
                        mother = -1;
                        animalCount.set(0);
                        statusBar.setCount(0);

                        ArrayList<Animal> tmp;

                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(currentFile))) {
                            tmp = (ArrayList<Animal>) ois.readObject();
                            father = ois.readInt();
                            mother = ois.readInt();
                        }

                        setupTable();

                        for (Animal a : tmp) {
                            /*for (String str : a.getPhenotypes()) {
                             System.out.print(str + " ");
                             }
                             System.out.println();*/
                            addAnimal(a);
                        }

                        if (mother != -1) {
                            tableModel.setValueAt("P", mother, 1);
                        }
                        if (father != -1) {
                            tableModel.setValueAt("P", father, 1);
                        }

                        statusBar.setCount(getAnimalCount());

                        isSaved = true;
                        fixTitle();

                        revalidate();
                    } catch (FileNotFoundException ex) {
                        System.out.printf("File %s not found\n", currentFile.getAbsolutePath());
                    } catch (IOException ex) {
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace(System.err);
                    }
                }
            }
        });

        saveItem.setAccelerator(KeyStroke.getKeyStroke("control S"));
        saveItem.setMnemonic('s');
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFile == null) {
                    selectSaveFile();
                }

                if (!isSaved) {
                    try {
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentFile))) {
                            oos.writeObject(animals);
                            oos.writeInt(father);
                            oos.writeInt(mother);
                        }
                        isSaved = true;
                        fixTitle();
                    } catch (FileNotFoundException ex) {
                        System.out.printf("File %s not found\n", currentFile.getAbsolutePath());
                    } catch (IOException ex) {
                    }
                }
            }
        });

        saveAsItem.setAccelerator(KeyStroke.getKeyStroke("control alt S"));
        saveAsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectSaveFile() == JFileChooser.APPROVE_OPTION) {
                    try {
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentFile))) {
                            oos.writeObject(animals);
                            oos.writeInt(father);
                            oos.writeInt(mother);
                        }
                        isSaved = true;
                        fixTitle();
                    } catch (FileNotFoundException ex) {
                        System.out.printf("File %s not found\n", currentFile.getAbsolutePath());
                    } catch (IOException ex) {
                    }
                }
            }
        });

        printItem.setAccelerator(KeyStroke.getKeyStroke("control P"));
        printItem.setMnemonic('p');
        printItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.print(JTable.PrintMode.FIT_WIDTH);
                } catch (PrinterException ex) {
                }
            }
        });

        quitItem.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        quitItem.setMnemonic('q');
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mbar.add(fileMenu);
        fileMenu.add(newItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(openItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(printItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(quitItem);

        JMenu animalMenu = new JMenu("Animal");

        animalMenu.setMnemonic('a');

        JMenuItem addAnimalItem = new JMenuItem("Add new animal");
        addAnimalItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        addAnimal(new AddAnimalPopup().showAddAnimalDialog());
                    }
                }.start();
            }
        });
        addAnimalItem.setMnemonic('a');

        JMenuItem setParentItem = new JMenuItem("Set as parent");
        setParentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sel = table.getSelectedRow();
                if (sel != -1  && sel < animalCount.get()) {
                    sel = Integer.parseInt((String) table.getValueAt(sel, 0)) - 1;
                    isSaved = false;
                    fixTitle();
                    if (animals.get(sel).isMale()) {
                        if (father != -1) {
                            tableModel.setValueAt("", father, 1);
                        }
                        father = sel;
                    } else {
                        if (mother != -1) {
                            tableModel.setValueAt("", mother, 1);
                        }
                        mother = sel;
                    }
                    tableModel.setValueAt("P", sel, 1);
                }
            }
        });
        setParentItem.setMnemonic('p');

        JMenuItem setClutchSizeItem = new JMenuItem("Set clutch size");
        setClutchSizeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean okay;
                    int newSize = clutchSize;
                do {
                    okay = true;
                    String buf = JOptionPane.showInputDialog(GenSim.this, "Enter the clutch size", clutchSize);
                    try {
                        newSize = Integer.parseInt(buf);
                        if (newSize < 1) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        int choice = JOptionPane.showConfirmDialog(GenSim.this, "Invalid clutch size", "Error", JOptionPane.OK_CANCEL_OPTION);
                        if (choice == JOptionPane.OK_OPTION) {
                            okay = false;
                        } else if (choice == JOptionPane.CANCEL_OPTION) {
                            newSize = clutchSize;
                            okay = true;
                        }
                    }
                } while (!okay);
                clutchSize = newSize;
            }
        });
        setClutchSizeItem.setMnemonic('c');

        JMenuItem mateItem = new JMenuItem("Mate");
        mateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cores = Runtime.getRuntime().availableProcessors();
                int threads = clutchSize / 500;
                if (threads < 1) {
                    threads = 1;
                } else if (threads > cores) {
                    threads = cores;
                }
                
                int initial = animalCount.get();
                int size = Math.max(clutchSize / threads, 500);
                
                for(int start = 0; start < clutchSize; start += size) {
                    final int end = Math.min(clutchSize, start + size);
                    new Thread() {
                        @Override
                        public void run() {
                            for (int i = 0; i < end; i++) {
                                if (mother != -1 && father != -1) {
                                    addAnimal(animals.get(mother).breed(animals.get(father)));
                                }
                            }
                        }
                    }.start();
                }
                
                while(animalCount.get() - initial < clutchSize) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
                
                /*for (int i = 0; i < clutchSize; i++) {
                    if (mother != -1 && father != -1) {
                        addAnimal(animals.get(mother).breed(animals.get(father)));
                    }
                }*/
            }
        });
        mateItem.setMnemonic('m');
        
        JMenuItem showParentsItem = new JMenuItem("Show Parents");
        showParentsItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
            
        });

        mbar.add(animalMenu);
        animalMenu.add(addAnimalItem);
        animalMenu.add(new JSeparator());
        animalMenu.add(setParentItem);
        animalMenu.add(setClutchSizeItem);
        animalMenu.add(new JSeparator());
        animalMenu.add(mateItem);
        
        JMenu statsMenu = new JMenu("Statistics");
        
        statsMenu.setMnemonic('s');
        
        JMenuItem countItem = new JMenuItem("Count...");
        countItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Animal> toCount = new ArrayList<>();
                int[] selected = table.getSelectedRows();
                if (selected.length == 0) {
                    toCount = animals;
                } else {
                    for(int row : selected) {
                        toCount.add(animals.get(Integer.parseInt((String)tableModel.getValueAt(row, 0)) - 1));
                    }
                }
                new CounterPopup().showCounterDialog(toCount);
            }
        });
        countItem.setMnemonic('c');
        
        JMenuItem chiSquaredItem = new JMenuItem("Chi-squared...");
        chiSquaredItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(UIManager.getString("OptionPaneUI"));
                System.out.println(UIManager.getString("Label.font"));
                new ChiSquaredPopup().showTestWindow();
            }
        });
        chiSquaredItem.setMnemonic('s');
        
        mbar.add(statsMenu);
        statsMenu.add(countItem);
        statsMenu.add(chiSquaredItem);

        JMenu optionsMenu = new JMenu("Options");

        optionsMenu.setMnemonic('o');

        JMenu fontMenu = new JMenu("Font");
        fontMenu.setMnemonic('f');
        
        JMenuItem smallFontItem = new JMenuItem("Small");
        smallFontItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeGlobalFontSize(-1);
            }
            
        });
        smallFontItem.setMnemonic('m');
        
        JMenuItem normalFontItem = new JMenuItem("Normal");
        normalFontItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeGlobalFontSize(0);
            }
            
        });
        normalFontItem.setMnemonic('n');
        
        JMenuItem largeFontItem = new JMenuItem("Large");
        largeFontItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeGlobalFontSize(2);
            }
            
        });
        largeFontItem.setMnemonic('l');
        
        fontMenu.add(smallFontItem);
        fontMenu.add(normalFontItem);
        fontMenu.add(largeFontItem);

        final JCheckBoxMenuItem toolbarItem = new JCheckBoxMenuItem("Toolbar", true);
        toolbarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toolbarItem.isSelected()) {
                    showToolBar();
                } else {
                    removeToolBar();
                }
            }
        });
        toolbarItem.setMnemonic('t');

        final JCheckBoxMenuItem statusBarItem = new JCheckBoxMenuItem("Status bar", true);
        statusBarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statusBarItem.isSelected()) {
                    showStatusBar();
                } else {
                    removeStatusBar();
                }
            }
        });
        statusBarItem.setMnemonic('s');

        mbar.add(optionsMenu);
        optionsMenu.add(fontMenu);
        optionsMenu.add(new JSeparator());
        optionsMenu.add(toolbarItem);
        optionsMenu.add(statusBarItem);

        JMenu aboutMenu = new JMenu("About");

        aboutMenu.setMnemonic('b');

        JMenuItem aboutItem = new JMenuItem("About GenSim");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(GenSim.this, "GenSim v 1.1a\n\nBy: Andrew Vitkus\n\nLast Updated: 3/2013", "About GenSim", JOptionPane.PLAIN_MESSAGE);
            }
        });
        aboutItem.setMnemonic('a');

        mbar.add(aboutMenu);
        aboutMenu.add(aboutItem);
    }

    private void showStatusBar() {
        add(statusBar, BorderLayout.SOUTH);
        revalidate();
    }

    private void removeStatusBar() {
        remove(statusBar);
        revalidate();
    }

    private void makeToolBar() {
        JButton mateButton = new JButton("Mate");
        mateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < clutchSize; i++) {
                    if (mother != -1 && father != -1) {
                        addAnimal(animals.get(mother).breed(animals.get(father)));
                    }
                }
            }
        });
        toolBar.add(mateButton);

        JButton parentButton = new JButton("Set Parent");
        parentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sel = table.getSelectedRow();
                if (sel != -1 && sel < animalCount.get()) {
                    sel = Integer.parseInt((String) table.getValueAt(sel, 0)) - 1;
                    isSaved = false;
                    fixTitle();
                    if (animals.get(sel).isMale()) {
                        if (father != -1) {
                            tableModel.setValueAt("", father, 1);
                        }
                        father = sel;
                    } else {
                        if (mother != -1) {
                            tableModel.setValueAt("", mother, 1);
                        }
                        mother = sel;
                    }
                    tableModel.setValueAt("P", sel, 1);
                }
            }
        });
        toolBar.add(parentButton);

        toolBar.addSeparator();

        JButton countButton = new JButton("Count");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Animal> toCount = new ArrayList<>();
                int[] selected = table.getSelectedRows();
                if (selected.length == 0) {
                    toCount = animals;
                } else {
                    for(int row : selected) {
                        toCount.add(animals.get(Integer.parseInt((String)tableModel.getValueAt(row, 0)) - 1));
                    }
                }
                new CounterPopup().showCounterDialog(toCount);
            }
        });
        toolBar.add(countButton);

        JButton chiSquaredButton = new JButton("Chi-Squared");
        chiSquaredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChiSquaredPopup().showTestWindow();
            }
        });
        toolBar.add(chiSquaredButton);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
    }

    private void showToolBar() {
        add(toolBar, BorderLayout.NORTH);
        revalidate();
    }

    private void removeToolBar() {
        remove(toolBar);
        revalidate();
    }

    private void setupTable() {
        tableModel = new DisplayTableModel(animalTitles);

        DisplayTableColumnModel columnModel = new DisplayTableColumnModel();
        columnModel.addColumn(new TableColumn(0));
        columnModel.getColumn(0).setHeaderValue("#");
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.addColumn(new TableColumn(1));
        columnModel.getColumn(1).setHeaderValue("P");
        columnModel.getColumn(1).setPreferredWidth(5);

        for (int i = 0; i < animalTitles.length; i++) {
            columnModel.addColumn(new TableColumn(i + 2));
            columnModel.getColumn(i + 2).setHeaderValue(animalTitles[i]);
            columnModel.getColumn(i + 2).setPreferredWidth((int) (animalTitles[i].length() * 4.5));
        }
        //table.setColumnModel(columnModel);
        TableRowSorter sorter = new TableRowSorter(tableModel);

        sorter.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i1 = Integer.parseInt(o1);
                int i2 = Integer.parseInt(o2);

                if (i1 > i2) {
                    return 1;
                } else if (i1 < i2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        table.setModel(tableModel);
        table.setColumnModel(columnModel);
        table.setRowSorter(sorter);
    }

    private void addRow(String[] data) {
        tableModel.addRow(data);
        table.setModel(tableModel);
        isSaved = false;
        fixTitle();
        repaint();
    }

    private int selectOpenFile() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter textFilter = new FileNameExtensionFilter("GenSim file", "gsm");

        fc.setFileFilter(textFilter);
        

        int chosen = fc.showOpenDialog(GenSim.this);

        if (chosen == JFileChooser.APPROVE_OPTION) {
            currentFile = fc.getSelectedFile();
        }

        return chosen;

    }

    private int selectSaveFile() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter textFilter = new FileNameExtensionFilter("GenSim file", "gsm");

        fc.setFileFilter(textFilter);

        int chosen = fc.showSaveDialog(GenSim.this);


        if (chosen == JFileChooser.APPROVE_OPTION) {
            currentFile = fc.getSelectedFile();
            try {
                String fileName = currentFile.getCanonicalPath();
                if(!fileName.endsWith(".gsm")) {
                    currentFile = new File(fileName + ".gsm");
                }
            } catch (IOException ex) {
            }
        }

        return chosen;
    }

    private synchronized void addAnimal(Animal a) {
        animals.add(a);
        animalCount.incrementAndGet();
        String[] row = new String[a.getPhenotypes().length + 2];
        row[0] = String.valueOf(getAnimalCount());
        row[1] = "";
        int i = 2;
        for (String name : a.getPhenotypes()) {
            row[i] = name;
            i++;
        }
        addRow(row);
        statusBar.setCount(getAnimalCount());
    }

    private void addAnimal(String[] genotype) {
        if (genotype != null) {
            Animal a = new Chicken(genotype);
            addAnimal(a);
        }
    }

    private int getAnimalCount() {
        return animalCount.get();
    }

    private void fixTitle() {
        if (isSaved) {
            setTitle("GenSim");
        } else {
            setTitle("GenSim*");
        }
    }
    
    private void changeGlobalFontSize(int change) {
        changeFontSize("Button.font", change);
        changeFontSize("TextField.font", change);
        changeFontSize("Label.font", change);
        changeFontSize("Menu.font", change);
        changeFontSize("MenuItem.font", change);
        changeFontSize("CheckBoxMenuItem.font", change);
        changeFontSize("ComboBox.font", change);
        changeFontSize("OptionPane.font", change);
        changeFontSize("RadioButton.font", change);
        javax.swing.SwingUtilities.updateComponentTreeUI(this);
    }
    
    private void changeFontSize(String key, int change) {
        LookAndFeel sysDefault = null;
        try {
            sysDefault = (LookAndFeel) Class.forName(UIManager.getSystemLookAndFeelClassName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
        }
        Font defaultFont = sysDefault.getDefaults().getFont(key);
        FontUIResource res = new FontUIResource(defaultFont.deriveFont(defaultFont.getSize2D() + change));
        UIManager.put(key, res);
    }

    protected class StatusBar extends JPanel {

        private String animalName;
        private int animalCount;
        private JLabel nameLabel;
        private JLabel countLabel;

        protected StatusBar(String name) {
            animalName = name;
            animalCount = 0;

            nameLabel = new JLabel("Animal: " + animalName);
            countLabel = new JLabel("Animals: " + animalCount);
            countLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

            setLayout(new GridLayout(1, 2));

            build();
            setVisible(true);
        }

        protected void setAnimalName(String newName) {
            animalName = newName;

            nameLabel.setText("Animal: " + newName);

            repaint();
        }

        protected String getAnimalName() {
            return animalName;
        }

        protected void setCount(int count) {
            animalCount = count;

            countLabel.setText("Animals: " + count);

            repaint();
        }

        protected int getCount() {
            return animalCount;
        }

        private void build() {
            add(nameLabel);
            add(countLabel);
        }
    }
}
