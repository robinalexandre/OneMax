package fr.etu.ea.model.operators.crossover;

import java.util.Random;

import fr.etu.ea.model.Individu;
import fr.etu.ea.util.Utilities;

public class CrossoverTwoCut implements ICrossover {

	@Override
	public Individu[] crossover(Individu parent1, Individu parent2) {
		int cutPoint = Utilities.random.nextInt(parent1.getBits().length-1) + 1;		
		int cutPoint2 = Utilities.random.nextInt(parent1.getBits().length-1) + 1;
		while(cutPoint == cutPoint2)
			cutPoint2 = new Random().nextInt(parent1.getBits().length-1) + 1;
		Individu child = new Individu(parent1.getBits().length);
		Individu child2 = new Individu(parent2.getBits().length);
		for(int i = 0; i < parent1.getBits().length; i++) {
			if( i < Math.min(cutPoint, cutPoint2)) {
				child.getBits()[i] = parent1.getBits()[i];
				child2.getBits()[i] = parent2.getBits()[i];
			} else if (i >= Math.min(cutPoint, cutPoint2) && i <= Math.max(cutPoint, cutPoint2)) {
				child.getBits()[i] = parent2.getBits()[i];
				child2.getBits()[i] = parent1.getBits()[i];
			}
			else if (i > Math.max(cutPoint, cutPoint2)) {
				child.getBits()[i] = parent1.getBits()[i];
				child2.getBits()[i] = parent2.getBits()[i];
			}
		}
		Individu[] children = {child, child2};
		return children;
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
