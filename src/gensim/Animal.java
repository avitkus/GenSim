/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gensim;

/**
 *
 * @author Andrew
 */
public interface Animal {

    public Animal breed(Animal mate);

    public String[] getPhenotypes();

    public String[] getGenotypes();

    public String[] getGeneList();

    public boolean isMale();

    public boolean isAlive();
}
