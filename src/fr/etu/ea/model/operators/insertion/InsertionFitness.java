package fr.etu.ea.model.operators.insertion;

import java.util.ArrayList;
import java.util.List;

import fr.etu.ea.model.Population;

public class InsertionFitness implements IInsertion {

	@Override
	public void insert(Population population, Population enfants) {
		List<Integer> worseFitnessIndex = new ArrayList<>();
		int index;
		for(int i = 0; i < enfants.getIndividus().length; i++) {
			index = population.worseFitnessIndex(worseFitnessIndex);
			population.getIndividus()[index] = enfants.getIndividus()[i];
			worseFitnessIndex.add(index);
		}
		population.getOld();
	}

	public String toString() {
		return this.getClass().getSimpleName();
	}
}
