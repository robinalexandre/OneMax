package fr.etu.ea.model.operators.mutation;

import fr.etu.ea.model.Individu;
import fr.etu.ea.util.Utilities;

public class MutationBitFlips implements IMutation {

	@Override
	public void mutate(Individu individu) {
		for(int i = 0; i < individu.getBits().length; i++) {
			boolean probability = Utilities.random.nextInt(individu.getBits().length) == 0;
			if(probability)
				if(individu.getBits()[i] == 0) 
					individu.getBits()[i] = 1;
				else 
					individu.getBits()[i] = 0;
		}
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
