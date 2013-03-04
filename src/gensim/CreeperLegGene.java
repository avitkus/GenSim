package gensim;

/**
 *
 * @author Andrew
 */
public class CreeperLegGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "CC";
        genotypes[1] = "Cc";
        genotypes[2] = "cc";

        phenotypes = new String[3];
        phenotypes[0] = "Dead";
        phenotypes[1] = "Creeper";
        phenotypes[2] = "Normal";
    }

    public CreeperLegGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "CC":
                phenotype = "Dead";
                break;
            case "Cc":
            case "cC":
                phenotype = "Creeper";
                break;
            case "cc":
                phenotype = "Normal";
                break;
            default:
                System.err.println("Invalid genotype set for creeper legs!");
        }
    }

    @Override
    public String getGeneName() {
        return "Creeper Legs";
    }

    @Override
    public String getEffectedTrait() {
        return "Creeper Legs";
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
