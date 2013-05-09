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
    
    public Animal[] getParents();
}
