package fr.etu.ea.model.operators.mutation;

import java.util.Arrays;

import fr.etu.ea.model.Individu;
import fr.etu.ea.model.Population;
import fr.etu.ea.model.operators.IOperators;
import fr.etu.ea.util.Utilities;

public interface IMutation extends IOperators {
		
	public void mutate(Individu individu);
	
	public default void mutationAll(Population population, int probability) {
		for(int i = 0; i < population.getIndividus().length; i++) {
			if(Utilities.probabilite(probability)) {
				Integer[] previous = Arrays.copyOf(population.getIndividus()[i].getBits(), population.getIndividus()[i].getBits().length);
				
				this.mutate(population.getIndividus()[i]);
				
				Individu previousIndividu = new Individu(previous.length);
				previousIndividu.setBits(previous);
				if(population.getIndividus()[i].fitness() < previousIndividu.fitness()) 
					population.getIndividus()[i].setBits(previous);
			}
		}
	}

}
