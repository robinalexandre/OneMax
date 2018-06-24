package fr.etu.ea.model.operators.insertion;

import java.util.ArrayList;
import java.util.List;

import fr.etu.ea.model.Population;

public class InsertionAge implements IInsertion {

	@Override
	public void insert(Population population, Population enfants) {
		List<Integer> olderAges = new ArrayList<>();
		int index;
		for(int i = 0; i < enfants.getIndividus().length; i++) {
			index = population.getOlderIndex(olderAges);
			//System.out.println("Replace age: "+ this.getPopulation()[index].getAge());
			population.getIndividus()[index] = enfants.getIndividus()[i];
			olderAges.add(index);
		}
		population.getOld();
	}

	public String toString() {
		return this.getClass().getSimpleName();
	}
}
