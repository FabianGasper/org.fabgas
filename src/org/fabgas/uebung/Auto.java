package org.fabgas.uebung;

public class Auto {
	private double tempo;
	private int ps;
	public String hupton;
	private String name;
	private double verbrauch100;
	
	public Auto(String name) {
		this.name=name;
		this.tempo=0;
	};
	
	
	
	public void hupen() {
		System.out.println(hupton);
	}
	public double beschleunigen(double inc) {
		this.tempo+=inc;
		return this.tempo;
	}
	public double bremsen(double inc) {
		this.tempo-=inc;
		return this.tempo;
	}
}
