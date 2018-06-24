package fr.etu.ea.model;

public class Individu {
	
	private Integer[] bits;
	private int age;
	
	/**
	 * Initialisation d'un individu de taille N à 0
	 * @param taille
	 */
	public Individu(int taille) {
		this.age = 0;
		this.bits = new Integer[taille];
		for(int i = 0; i < taille; ++i) 
			this.bits[i] = 0;
	}
	
	/**
	 * Retourne le tableau de bits de l'individu
	 * @return
	 */
	public Integer[] getBits() {
		return this.bits;
	}
	
	public void setBits(Integer[] newChild) {
		this.bits = newChild;
	}
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Renvoie le calcul de fitness de cet individu
	 * @return le fitness de l'individu
	 */
	public int fitness() {
		int value = 0;
		for(int bit: this.bits)
			value += bit;
		return value;
	}
	
	public String toString() {
		String individu = "";
		for(int j = 0; j < this.getBits().length; j++) {
			individu += this.getBits()[j];
		}
		return individu;
	}
}

