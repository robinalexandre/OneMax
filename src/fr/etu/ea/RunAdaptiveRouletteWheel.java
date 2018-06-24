package fr.etu.ea;

import fr.etu.chart.Chart;
import fr.etu.ea.apprentissage.SelectionAdaptiveRouletteWheel;
import fr.etu.ea.apprentissage.SelectionOperators;
import fr.etu.ea.model.Population;
import fr.etu.ea.model.operators.IOperators;
import fr.etu.ea.model.operators.crossover.ICrossover;
import fr.etu.ea.model.operators.insertion.IInsertion;
import fr.etu.ea.model.operators.mutation.IMutation;
import fr.etu.ea.model.operators.selection.ISelection;

public class RunAdaptiveRouletteWheel implements Runnable {

	private Integer individuSize;
	private Integer populationSize;
	private Integer probabilityMutation;
	private Integer probabilityCroisement;
	private int nbSelection;
	private float pmin;
	private float alpha;
	private int windowSize;
	ISelection[] selection;
	ICrossover[] crossover;
	IMutation[] mutation;
	IInsertion[] insertion;
	private int mutationSelected;
	private int crossoverSelected;
	
	public RunAdaptiveRouletteWheel(Integer individuSize, Integer populationSize, Integer probabilityMutation, Integer probabilityCroisement, int nbSelection, float pmin, float alpha, int windowSize, ISelection[] selection, ICrossover[] crossover, IMutation[] mutation, IInsertion[] insertion) {
		super();
		this.individuSize = individuSize;
		this.populationSize = populationSize;
		this.probabilityMutation = probabilityMutation;
		this.probabilityCroisement = probabilityCroisement;
		this.nbSelection = nbSelection;
		this.pmin = pmin;
		this.alpha = alpha;
		this.windowSize = windowSize;
		this.selection = selection;
		this.crossover = crossover;
		this.mutation = mutation;
		this.insertion = insertion;
	}
	
	@Override
	public void run() {
		//Création du graphe
		Chart graph = new Chart("Adaptive Roulette Wheel");
		//Chart graphOperateurs = new Chart();
				
		Population population = new Population(populationSize, individuSize);
		int iteration = 0;
		int bestIndividuIndex = population.bestFitnessIndex(null);
		int bestFitness = population.getIndividus()[bestIndividuIndex].fitness();
		
		SelectionOperators selectionsOperatorsCrossover = new SelectionAdaptiveRouletteWheel((IOperators[])this.crossover, this.alpha, this.pmin, this.windowSize);
		SelectionOperators selectionsOperatorsMutation = new SelectionAdaptiveRouletteWheel((IOperators[])this.mutation, this.alpha, this.pmin, this.windowSize);
		
		graph.addBestXY(iteration, population.getIndividus()[bestIndividuIndex].fitness());
		graph.addMoyenneXY(iteration, population.averageFitness());
		graph.addMinXY(iteration, population.getIndividus()[population.worseFitnessIndex(null)].fitness());
		
		while(bestFitness != individuSize) {
			//System.out.println("------------ Iteration" + iteration + "--------------");
			crossoverSelected = selectionsOperatorsCrossover.getChoosenOperator(population);
			mutationSelected = selectionsOperatorsMutation.getChoosenOperator(population);
						
			Population parents = this.selection[0].selection(population, nbSelection);
			
			bestIndividuIndex = parents.bestFitnessIndex(null);
			bestFitness = parents.getIndividus()[bestIndividuIndex].fitness();
			selectionsOperatorsCrossover.setBestFitness(bestFitness);
			
			Population enfants = this.crossover[crossoverSelected].crossoverAll(parents, probabilityCroisement);
			
			bestIndividuIndex = enfants.bestFitnessIndex(null);
			bestFitness = enfants.getIndividus()[bestIndividuIndex].fitness();
			selectionsOperatorsCrossover.addLastPerformance(bestFitness, crossoverSelected);
			selectionsOperatorsMutation.setBestFitness(bestFitness);
			
			this.mutation[mutationSelected].mutationAll(enfants, probabilityMutation);
			
			bestIndividuIndex = enfants.bestFitnessIndex(null);
			bestFitness = enfants.getIndividus()[bestIndividuIndex].fitness();
			selectionsOperatorsMutation.addLastPerformance(bestFitness, mutationSelected);
			
			this.insertion[0].insert(population, enfants);
						
			bestIndividuIndex = population.bestFitnessIndex(null);
			bestFitness = population.getIndividus()[bestIndividuIndex].fitness();
			graph.addBestXY(iteration, bestFitness);
			graph.addMinXY(iteration, population.getIndividus()[population.worseFitnessIndex(null)].fitness());
			graph.addMoyenneXY(iteration, population.averageFitness());
			
			/*graphOperateurs.addKFlip1(iteration, mutations.getUtilities()[0]);
			graphOperateurs.addKFlip3(iteration, mutations.getUtilities()[1]);
			graphOperateurs.addKFlip5(iteration, mutations.getUtilities()[2]);
			graphOperateurs.addBitFlips(iteration, mutations.getUtilities()[3]);
			
			graphOperateurs.addUniforme(iteration, croisements.getUtilities()[0]);
			graphOperateurs.addOneCut(iteration, croisements.getUtilities()[1]);
			graphOperateurs.addTwoCuts(iteration, croisements.getUtilities()[2]);

			graphOperateurs.addAleatoire(iteration, selections.getUtilities()[0]);
			graphOperateurs.addBest(iteration, selections.getUtilities()[1]);
			graphOperateurs.addTournoi(iteration, selections.getUtilities()[2]);*/
			
			//graph.busyWait(0.005);
			
			iteration++;
		}
		System.out.println("\n\nAdaptive Roulette Wheel------------Iteration "+ (iteration-1) +"------------");
		System.out.print("------------Best individu: "+ bestIndividuIndex +", Fitness: "+ bestFitness +"------------\nBits: " + population.getIndividus()[bestIndividuIndex].toString());
			
		graph.setTitleGraph(graph.getTitleGraph() + "\nMeilleur individu (1...1) , generation :" + (iteration-1));		
	}

}
