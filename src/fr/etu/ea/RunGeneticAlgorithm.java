package fr.etu.ea;

import fr.etu.chart.Chart;
import fr.etu.ea.model.Population;
import fr.etu.ea.model.operators.crossover.ICrossover;
import fr.etu.ea.model.operators.insertion.IInsertion;
import fr.etu.ea.model.operators.mutation.IMutation;
import fr.etu.ea.model.operators.selection.ISelection;

public class RunGeneticAlgorithm implements Runnable {
	
	private Integer individuSize;
	private Integer populationSize;
	private int probabilityMutation;
	private int probabilityCroisement;
	private int nbSelection;
	private ISelection selection;
	private ICrossover crossover;
	private IMutation mutation;
	private IInsertion insertion;

	public RunGeneticAlgorithm(Integer individuSize, Integer populationSize, int probabilityMutation,
			int nbSelection, int probabilityCroisement, ISelection selection,
			IMutation mutation, ICrossover crossover, IInsertion insertion) {
		super();
		this.individuSize = individuSize;
		this.populationSize = populationSize;
		this.probabilityMutation = probabilityMutation;
		this.probabilityCroisement = probabilityCroisement;
		this.nbSelection = nbSelection;
		this.selection = selection;
		this.mutation = mutation;
		this.crossover = crossover;
		this.insertion = insertion;
	}

	@Override
	public void run() {
		//Création du graphe
		Chart graph = new Chart("Genetic Algorithm");
				
		Population population = new Population(populationSize, individuSize);
		int iteration = 0;
		int bestIndividuIndex = population.bestFitnessIndex(null);
		int bestFitness = population.getIndividus()[bestIndividuIndex].fitness();
		graph.addBestXY(iteration, population.getIndividus()[bestIndividuIndex].fitness());
		graph.addMinXY(iteration, population.getIndividus()[population.worseFitnessIndex(null)].fitness());
		graph.addMoyenneXY(iteration, population.averageFitness());
		
		while(bestFitness != individuSize) {			
			
			Population parents = this.selection.selection(population, nbSelection);
			Population enfants = this.crossover.crossoverAll(parents, probabilityCroisement);
			this.mutation.mutationAll(enfants, probabilityMutation);
			this.insertion.insert(population, enfants);
			
			bestIndividuIndex = population.bestFitnessIndex(null);
			bestFitness = population.getIndividus()[bestIndividuIndex].fitness();
			
			graph.addBestXY(iteration, population.getIndividus()[bestIndividuIndex].fitness());
			graph.addMinXY(iteration, population.getIndividus()[population.worseFitnessIndex(null)].fitness());
			graph.addMoyenneXY(iteration, population.averageFitness());
			
			iteration++;
		}
		System.out.println("\n\nGA ------------Iteration "+ (iteration-1) +"------------");
		System.out.print("------------Best individu: "+ bestIndividuIndex +", Fitness: "+ bestFitness +"------------\nBits: " + population.getIndividus()[bestIndividuIndex].toString());
		
		graph.setTitleGraph(graph.getTitleGraph() + "\nMeilleur individu (1...1) : " + bestIndividuIndex + ", generation :" + (iteration-1));		
	}
}
