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

package gensim.util;

import java.util.Comparator;

/**
 *
 * @author Andrew
 */
public class VersionComparator implements Comparator<String> {

    @Override
    public int compare(String v1, String v2) {
        String[] parts1 = v1.split("\\.");
        String[] parts2 = v2.split("\\.");
        
        if(Integer.parseInt(parts1[0]) > Integer.parseInt(parts2[0])) {
            return 1;
        } else if (Integer.parseInt(parts1[0]) < Integer.parseInt(parts2[0])) {
            return -1;
        } else {
            int[] sub1 = new int[3];
            int[] sub2 = new int[3];
            
            try {
                sub1[0] = Integer.parseInt(parts1[1]);
                sub1[1] = 2;
                sub1[2] = 0;
            } catch (NumberFormatException e) {
                int preLoc = parts1[1].indexOf('b');
                sub1[1] = 1;
                if (preLoc == -1) {
                     preLoc = parts1[1].indexOf('a');
                    sub1[1] = 0;
                }
                sub1[0] = Integer.parseInt(parts1[1].substring(0, preLoc));
                if (preLoc + 1 == parts1[1].length()) {
                    sub1[2] = 0;
                } else {
                    sub1[2] = Integer.parseInt(parts1[1].substring(preLoc + 1, parts1[1].length()));
                }
            }
            
            try {
                sub2[0] = Integer.parseInt(parts2[1]);
                sub2[1] = 2;
                sub2[2] = 0;
            } catch (NumberFormatException e) {
                int preLoc = parts2[1].indexOf('b');
                sub2[1] = 1; 
                if (preLoc == -1) {
                     preLoc = parts2[1].indexOf('a');
                     sub2[1] = 0;
                }
                sub2[0] = Integer.parseInt(parts2[1].substring(0, preLoc));
                if (preLoc + 1 == parts2[1].length()) {
                    sub2[2] = 0;
                } else {
                    sub2[2] = Integer.parseInt(parts2[1].substring(preLoc + 1, parts2[1].length()));
                }
            }
            
            if (sub1[0] > sub2[0]) {
                return 1;
            } else if (sub1[0] < sub2[0]) {
                return -1;
            } else {
                if (sub1[1] > sub2[1]) {
                    return 1;
                } else if (sub1[1] < sub2[1]) {
                    return -1;
                }else {
                    if (sub1[2] > sub2[2]) {
                        return 1;
                    } else if (sub1[2] < sub2[2]) {
                        return -1;
                    }
                }
            }
        }
        return 0;
    }
    
}
