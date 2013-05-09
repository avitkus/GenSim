package gensim;

/**
 *
 * @author Andrew
 */
public class BredaCombGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "BB";
        genotypes[1] = "Bb";
        genotypes[2] = "bb";

        phenotypes = new String[3];
        phenotypes[0] = "Comb";
        phenotypes[1] = "Comb";
        phenotypes[2] = "Combless";
    }

    public BredaCombGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "BB":
                phenotype = "Comb";
                break;
            case "Bb":
            case "bB":
                phenotype = "Comb";
                break;
            case "bb":
                phenotype = "Combless";
                break;
            default:
                System.err.println("Invalid genotype set for Breda comb presence!");
        }
    }

    @Override
    public String getGeneName() {
        return "Bred Comb";
    }

    @Override
    public String getEffectedTrait() {
        return "Breda Comb";
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
