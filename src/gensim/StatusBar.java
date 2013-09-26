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

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Andrew Vitkus
 */
class StatusBar extends JPanel {

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