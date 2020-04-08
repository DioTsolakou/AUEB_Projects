package scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genetic
{
    private ArrayList<Chromosome> population;
    private ArrayList<Integer> fitnessBounds;

    Main main = new Main();

    public Genetic()
    {
        this.population = null;
        this.fitnessBounds = null;
    }

    public Chromosome geneticAlgorithm(int populationSize, double mutationChance, int minFitness, int maxSteps)
    {
        initializePopulation(populationSize);
        Random random = new Random();
        for (int current = 0; current < maxSteps; current++)
        {
            ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
            for (int i = 0; i < populationSize; i++)
            {
                int xIndex = this.fitnessBounds.get(random.nextInt(this.fitnessBounds.size()));
                Chromosome x = this.population.get(xIndex);
                int yIndex = this.fitnessBounds.get(random.nextInt(this.fitnessBounds.size()));
                while (yIndex == xIndex)
                {
                    yIndex = this.fitnessBounds.get(random.nextInt(this.fitnessBounds.size()));
                }
                Chromosome y = this.population.get(yIndex);
                Chromosome child = this.reproduce(x, y);
                if (random.nextDouble() < mutationChance)
                {
                    child.mutate();
                }
                newPopulation.add(child);
            }
            this.population = new ArrayList<Chromosome>(newPopulation);
            Collections.sort(this.population, Collections.reverseOrder());
            if (this.population.get(0).getFitness() >= minFitness)
            {
                System.out.println("Finished after " + current + " steps.");
                return this.population.get(0);
            }
            this.updateFitnessBounds();
        }
        System.out.println("Finished after " + maxSteps + " steps.");
        return this.population.get(0);
    }

    private void initializePopulation(int populationSize)
    {
        this.population = new ArrayList<Chromosome>();
        for (int i = 0; i < populationSize; i++)
        {
            this.population.add(new Chromosome());
        }
        this.updateFitnessBounds();
    }

    private void updateFitnessBounds()
    {
        this.fitnessBounds = new ArrayList<Integer>();
        for (int i = 0; i < this.population.size(); i++)
        {
            for (int j = 0; j < this.population.get(i).getFitness(); j++)
            {
                this.fitnessBounds.add(i);
            }
        }
    }

    public Chromosome reproduce(Chromosome x, Chromosome y)
    {
        Random random = new Random();
        int intersection = random.nextInt(main.totalHours*3) + 1;
        int[][] children = new int[main.totalHours*3][2];
        for (int i = 0; i < children[0].length; i++)
        {
            for (int j = 0; j < intersection; j++)
            {
                children[j][i] = x.getGenes()[j][i];
            }
        }
        for (int i = 0; i < children[0].length; i++)
        {
            for (int j = intersection; j < children.length; j++)
            {
                children[j][i] = y.getGenes()[j][i];
            }
        }
        return new Chromosome(children);
    }
}