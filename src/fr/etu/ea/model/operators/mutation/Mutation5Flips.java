package fr.etu.ea.model.operators.mutation;

import java.util.ArrayList;
import java.util.List;

import fr.etu.ea.model.Individu;
import fr.etu.ea.util.Utilities;

public class Mutation5Flips implements IMutation {

	@Override
	public void mutate(Individu individu) {
		List<Integer> previousPosition = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			int position = Utilities.random.nextInt(individu.getBits().length);
			while(previousPosition.contains(position))
				position = Utilities.random.nextInt(individu.getBits().length);
			previousPosition.add(position);
			if(individu.getBits()[position] == 1)
				individu.getBits()[position] = 0;
			else if(individu.getBits()[position] == 0)
				individu.getBits()[position] = 1;
		}
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
