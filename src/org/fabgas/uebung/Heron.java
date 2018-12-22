package org.fabgas.uebung;

public class Heron {
	public static double solve(double ini,double epsilon) {
		double last=1;
		double aprox=(ini+last)/2;
		while(Math.abs(last-aprox)>epsilon) {
			last=aprox;
			aprox=((ini/last)+last)/2;
		}
		return aprox;
	}
}
