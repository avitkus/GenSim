package gensim;

/**
 *
 * @author Andrew
 */
public class BlackFeatherGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "EE";
        genotypes[1] = "Ee";
        genotypes[2] = "ee";

        phenotypes = new String[3];
        phenotypes[0] = "Black";
        phenotypes[1] = "Black";
        phenotypes[2] = "Wheaten";
    }

    public BlackFeatherGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "EE":
                phenotype = "Black";
                break;
            case "Ee":
            case "eE":
                phenotype = "Black";
                break;
            case "ee":
                phenotype = "Wheaten";
                break;
            default:
                System.err.println("Invalid genotype set for black feathers!");
        }
    }

    @Override
    public String getGeneName() {
        return "Black Feathers";
    }

    @Override
    public String getEffectedTrait() {
        return "Black Feathers";
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
