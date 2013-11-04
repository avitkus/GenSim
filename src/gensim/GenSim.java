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

import gensim.windows.MainWindow;

/**
 *
 * @author Andrew Vitkus
 */
public class GenSim {
    public static final String VERSION_NUMBER = "1.5";
    public static final String MINIMUM_SUPPORTED_VERSION = "1.5";
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new MainWindow());
    }
}
