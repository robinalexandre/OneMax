package fr.etu.ea;

import fr.etu.ea.model.operators.crossover.CrossoverOneCut;
import fr.etu.ea.model.operators.crossover.CrossoverTwoCut;
import fr.etu.ea.model.operators.crossover.CrossoverUniforme;
import fr.etu.ea.model.operators.crossover.ICrossover;
import fr.etu.ea.model.operators.insertion.IInsertion;
import fr.etu.ea.model.operators.insertion.InsertionFitness;
import fr.etu.ea.model.operators.mutation.IMutation;
import fr.etu.ea.model.operators.mutation.Mutation1Flips;
import fr.etu.ea.model.operators.mutation.Mutation3Flips;
import fr.etu.ea.model.operators.mutation.Mutation5Flips;
import fr.etu.ea.model.operators.mutation.MutationBitFlips;
import fr.etu.ea.model.operators.selection.ISelection;
import fr.etu.ea.model.operators.selection.SelectionTournoi;

public class Launcher {

	public static void main(String[] args) {
		///Initialisation des valeurs
		Integer populationSize = 30;
		Integer individuSize = 1000;
				
		int probabilityMutation = 100; //Probability in %
		int probabilityCroisement = 100;
		
		int nbSelection = 2; //Nombre pair d'individu selectionnés
		
		int windowSize = 100;
		float pmin = (float) 0.1;
		float alpha = (float) 0.3;
		float beta = (float) 0.3;
		float scalingFactor = (float) 0.6;
		float tolerance = (float) 0.15;
		float threshold = (float) 5;
		
		ISelection[] selection = {new SelectionTournoi()};
		ICrossover[] crossover = {new CrossoverOneCut(), new CrossoverTwoCut(), new CrossoverUniforme()};
		IMutation[] mutation = {new Mutation1Flips(), new Mutation3Flips(), new Mutation5Flips(), new MutationBitFlips()};
		IInsertion[] insertion = {new InsertionFitness()};
				
		Thread thread = new Thread(new RunGeneticAlgorithm(individuSize, populationSize, probabilityMutation, nbSelection, probabilityCroisement, (ISelection)new SelectionTournoi(), (IMutation)new Mutation5Flips(), (ICrossover)new CrossoverUniforme(), (IInsertion)new InsertionFitness()));
		thread.start();
		
		Thread thread2 = new Thread(new RunAdaptiveRouletteWheel(individuSize, populationSize, probabilityMutation, probabilityCroisement, nbSelection, pmin, alpha, windowSize, selection, crossover, mutation, insertion));
		thread2.start();
		
		Thread thread3 = new Thread(new RunAdaptivePursuit(individuSize, populationSize, probabilityMutation, probabilityCroisement, nbSelection, pmin, beta, alpha, windowSize, selection, crossover, mutation, insertion));
		thread3.start();
		
		Thread thread4 = new Thread(new RunUCB(individuSize, populationSize, probabilityMutation, probabilityCroisement, nbSelection, pmin, alpha, windowSize, scalingFactor, tolerance, threshold, selection, crossover, mutation, insertion));
		thread4.start();
		
	}
}
