package org.fabgas.tests;


import org.fabgas.booleanEvaluation.BooleanEvaluation;


public class Main {
	public static class Func implements BooleanEvaluation.Func {
		
		public boolean call(String exp) {
				if(exp==null)return false;
				if(exp.equals("t")) {
					return true;
				}
				if(exp.equals("f")) {
					return false;
				}
				System.out.println("darf nicht sein1 >"+exp+"<");
				return false;
		}
	} 
	
	
	
	public static void main(String[] args) {
	
		Func f = new Func();  
		
		System.out.println("Ergebnis: t "+BooleanEvaluation.evaluate("(t|f)|f",f));
		
		System.out.println("Ergebnis: f "+BooleanEvaluation.evaluate("(t|f)&f",f));
		
		System.out.println("Ergebnis: t "+BooleanEvaluation.evaluate("(t&f)|t",f));
		System.out.println("Ergebnis: f "+BooleanEvaluation.evaluate("(t&f)&t",f));
		System.out.println("Ergebnis: t "+BooleanEvaluation.evaluate("f|t",f));
		System.out.println("Ergebnis: f "+BooleanEvaluation.evaluate("t&f",f));
	
		System.out.println("Ergebnis: f "+BooleanEvaluation.evaluate("(t&f)",f));
		System.out.println("Ergebnis: t "+BooleanEvaluation.evaluate("(t|f)",f));
		
		System.out.println("Ergebnis: t "+BooleanEvaluation.evaluate("(t|f)&(t|f)",f));
		System.out.println("Ergebnis: f "+BooleanEvaluation.evaluate("(t|f)&(t&f)",f));
		System.out.println("Ergebnis: t "+BooleanEvaluation.evaluate("(t|f|f)",f));
		
		
		//System.out.println("Ergebnis: "+BooleanEvaluation.evaluate("((f&!t|t)&(!f|f))",f));
		System.out.println("Ergebnis: t "+BooleanEvaluation.evaluate("\"t\"",f));

	} 
}
