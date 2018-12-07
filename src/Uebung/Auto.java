package Uebung;

public class Auto {
	public double tempo=0;
	public String hupton="tut";
	public String name;
	public Auto(String name) {this.name=name;};
	public void hupen() {
		System.out.println(hupton);
	}
	public double beschleunigen(double inc) {
		this.tempo+=inc;
		return this.tempo;
	}
}
