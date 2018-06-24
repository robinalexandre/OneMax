package fr.etu.ea.model.operators.crossover;

import fr.etu.ea.model.Individu;
import fr.etu.ea.util.Utilities;

public class CrossoverOneCut implements ICrossover {
	
	@Override
	public Individu[] crossover(Individu parent1, Individu parent2) {
		int cutPoint = Utilities.random.nextInt(parent1.getBits().length-1) + 1;		
		Individu child = new Individu(parent1.getBits().length);
		Individu child2 = new Individu(parent2.getBits().length);
		for(int i = 0; i < parent1.getBits().length; i++) {
			if( i < cutPoint) {
				child.getBits()[i] = parent1.getBits()[i];
				child2.getBits()[i] = parent2.getBits()[i];
			} else {
				child.getBits()[i] = parent2.getBits()[i];
				child2.getBits()[i] = parent1.getBits()[i];
			}
		}
		Individu[] children = {child, child2};
		return children;
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
