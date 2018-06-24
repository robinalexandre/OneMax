package fr.etu.ea.model;

import java.util.List;

public class Population {
	
	Individu[] individus;
	
	/**
	 * Initialise la population avec une taille populationSize
	 * @param populationSize
	 */
	public Population(Integer populationSize) {
		this.individus = new Individu[populationSize];
	}
	
	/**
	 * Initialise la population avec une taille populationSize et chaque individu à 0 avec une taille individuSize
	 * @param populationSize
	 * @param individuSize
	 */
	public Population(Integer populationSize, Integer individuSize) {
		this.individus = new Individu[populationSize];
		for(int i = 0; i < populationSize; i++) {
			this.individus[i] = new Individu(individuSize);
		}
	}
	
	public Individu[] getIndividus() {
		return this.individus;
	}
	
	public void setIndividus(Individu[] population) {
		this.individus = population;
	}
	
	public String toString() {
		String pop = "";
		for(int i = 0; i < this.getIndividus().length; i++) {
			pop += "Individu: " + i + "\n" + individus[i].toString() + "\n";
		}
		return pop;
	}
	
	/**
	 * Renvoie l'index de l'individu (sauf ceux dans index "index") ayant le meilleur fitness dans la population
	 * @param index à ne pas prendre en compte
	 * @return index du meilleur individu
	 */
	public int bestFitnessIndex(List<Integer> index) {
		int indexBestFitness = -1;
		int bestFitness = -1;
		for(int i = 0; i < getIndividus().length ; i++) {
			int fitness = getIndividus()[i].fitness();
			if(bestFitness < fitness && (index == null || !index.contains(i))) {
				indexBestFitness = i;
				bestFitness = fitness;
			}
		}
		return indexBestFitness;
	}
	
	
	/**
	 * Renvoie le fitness du meilleur individu
	 * @return fitness du meilleur individu
	 */
	public int bestFitness() {
		return this.getIndividus()[bestFitnessIndex(null)].fitness();
	}
	
	/**
	 * Renvoie l'index de l'individu (sauf ceux dans "index") ayant le moins bon fitness dans la population
	 * @param index à ne pas prendre en compte
	 * @return index du moins bon individu
	 */
	public int worseFitnessIndex(List<Integer> index) {
		int indexWorseFitness = -1;
		Integer worseFitness = null;
		for(int i = 0; i < this.getIndividus().length; i++) {
			int fitness = this.getIndividus()[i].fitness();
			if((worseFitness == null || worseFitness > fitness) && ( index == null || !index.contains(i))) {
				indexWorseFitness = i;
				worseFitness = fitness;
			}
		}
		return indexWorseFitness;
	}
	
	/**
	 * Renvoie la moyenne fitness de la population
	 * @return la moyenne fitness de la population
	 */
	public double averageFitness() {
		double moyenne = 0.0;
		for(int i = 0; i < this.getIndividus().length; i++) {
			moyenne += this.getIndividus()[i].fitness();
		}
		return moyenne/(double)this.getIndividus().length;
	}
	
	/**
	 * Renvoie l'index de l'individu (sauf ceux dans "index") ayant le plus vieil age dans la population
	 * @param index à ne pas prendre en compte
	 * @return index du plus vieil individu
	 */
	public int getOlderIndex(List<Integer> index) {
		int indexOlder = -1;
		int valueOlder = -1;
		for(int i = 0; i < this.getIndividus().length; i++) {
			int age = this.getIndividus()[i].getAge();
			if(valueOlder < age && !index.contains(i)) {
				indexOlder = i;
				valueOlder = age;
			}
		}
		return indexOlder;
	}
	
	/**
	 * Permet de faire vieillir la population en ajoutant 1 à l'age de chaque individu
	 */
	public void getOld() {
		for(int i = 0; i < this.getIndividus().length; i++) {
			this.getIndividus()[i].setAge(this.getIndividus()[i].getAge()+1);
		}
	}
}
