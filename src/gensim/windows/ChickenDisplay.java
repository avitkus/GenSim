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

import gensim.animals.Chicken;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Andrew Vitkus
 */
public class ChickenDisplay extends JFrame implements Runnable {

    private Chicken c;

    private ChickenDisplay(Chicken c) {
        super("Chicken Viewer");
        this.c = c;
    }

    @Override
    public void run() {
        setSize(300, 300);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/GenSim icon large.png")));

        BufferedImage chicken = buildChickenImage(c.getPhenotypes());
        if (chicken != null) {
            JPanel view = new JPanel();
            view.paint(chicken.getGraphics());
            add(new JLabel(new ImageIcon(chicken)));
        }


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

    public static void showChickenDisplay(Chicken c) {
        javax.swing.SwingUtilities.invokeLater(new ChickenDisplay(c));
    }

    private BufferedImage buildChickenImage(String[] phenotypes) {
        try {
            BufferedImage chickenImg = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
            Graphics2D chicken = chickenImg.createGraphics();
            chicken.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            chicken.drawImage(ImageIO.read(getClass().getResource("/images/legs/" + phenotypes[0] + "-" + phenotypes[4] + ".png")), 0, 0, null);
            //System.out.println("/images/body/" + phenotypes[1] + "-" + phenotypes[3] + "-" + phenotypes[2] + "-" + phenotypes[0] + ".png");
            chicken.drawImage(ImageIO.read(getClass().getResource("/images/body/" + phenotypes[1] + "-" + phenotypes[3] + "-" + phenotypes[2] + "-" + phenotypes[0] + ".png")), 0, 0, null);
            //System.out.println("/images/egg/" + phenotypes[6] + ".png");
            chicken.drawImage(ImageIO.read(getClass().getResource("/images/egg/" + phenotypes[6] + ".png")), 0, 0, null);
            if (phenotypes[5].equalsIgnoreCase("Comb")) {
                //System.out.println("/images/comb/" + phenotypes[0] + ".png");
                chicken.drawImage(ImageIO.read(getClass().getResource("/images/comb/" + phenotypes[0] + ".png")), 0, 0, null);
            }
            chicken.dispose();
            //ImageIO.write(chickenImg, "png", Files.newOutputStream(Paths.get("compositeChicken.png")));
            return chickenImg;
        } catch (IOException | NullPointerException ex) {
            return null;
        }
    }
}
