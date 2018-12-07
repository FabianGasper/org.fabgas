package org.fabgas.uebung;

public class Main {
	public static void main(String[] args) {
		Auto a = new Auto("Fiesta");
		a.hupen();
		a.hupton="möp";
		a.hupen();
		System.out.println("Geschwindigkeit: "+a.beschleunigen(25.5));
		System.out.println("Geschwindigkeit: "+a.beschleunigen(25.5));
	}
}
