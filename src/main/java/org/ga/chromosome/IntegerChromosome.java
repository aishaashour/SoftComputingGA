// This file does not contain any Markdown code fence markers.
package org.ga.chromosome;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class IntegerChromosome extends Chromosome<Integer>{
    private final List<Integer>  genes;
    private double fitness;
    private final Random random = new Random();
      public IntegerChromosome(int size) {
        genes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            genes.add(random.nextInt(10)) ;
        }
    }
   

    public IntegerChromosome(List<Integer>  genes){
        this.genes=genes;
    }
    @Override
    public List<Integer> getGenes(){
        return genes;
    }

    @Override
    public Chromosome<Integer> clone() {
        IntegerChromosome clone = new IntegerChromosome(new ArrayList<>(this.genes));
        clone.setFitness(this.fitness);
        return clone;
    }
    

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Chromosome<Integer> o) {
        return Double.compare(this.getFitness(), o.getFitness());
    }

}
