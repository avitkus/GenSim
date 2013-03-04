package gensim;

/**
 *
 * @author Andrew
 */
public class FrizzleFeatherGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "FF";
        genotypes[1] = "Ff";
        genotypes[2] = "ff";

        phenotypes = new String[3];
        phenotypes[0] = "Normal";
        phenotypes[1] = "Frizzle";
        phenotypes[2] = "Curly";
    }

    public FrizzleFeatherGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "FF":
                phenotype = "Normal";
                break;
            case "Ff":
            case "fF":
                phenotype = "Frizzle";
                break;
            case "ff":
                phenotype = "Curly";
                break;
            default:
                System.err.println("Invalid genotype set for frizzle feather!");
        }
    }

    @Override
    public String getGeneName() {
        return "Frizzle Feather";
    }

    @Override
    public String getEffectedTrait() {
        return "Frizzle Feather";
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
