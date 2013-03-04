package gensim;

/**
 *
 * @author Andrew
 */
public class ShellColorGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "BB";
        genotypes[1] = "Bb";
        genotypes[2] = "bb";

        phenotypes = new String[3];
        phenotypes[0] = "Blue";
        phenotypes[1] = "Blue";
        phenotypes[2] = "White";
    }

    public ShellColorGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "BB":
                phenotype = "Blue";
                break;
            case "Bb":
            case "bB":
                phenotype = "Blue";
                break;
            case "bb":
                phenotype = "White";
                break;
            default:
                System.err.println("Invalid genotype set for shell color!");
        }
    }

    @Override
    public String getGeneName() {
        return "Egg Color";
    }

    @Override
    public String getEffectedTrait() {
        return "Egg Color";
    }

    @Override
    public String getGenotype() {
        return genotype;
    }

    @Override
    public String getPhenotype() {
        return phenotype;
    }
}
