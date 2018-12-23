package org.fabgas.tests;



import java.io.File;

import org.fabgas.booleanEvaluation.BooleanEvaluation;
import org.fabgas.xml.Document;
import org.fabgas.xml.DocumentImpl;
import org.w3c.dom.Element;



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
	/*
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
		*/

		String xml = "<?xml version=\"1.0\"?><class><tag a=\"foo\"/></class>";
		try {
			Document d = new DocumentImpl(xml);
			//Document d = new DocumentImpl(new File("C:\\Downloads\\test.xml"));
			//System.out.println(d.documentToString());
			Document d2 = d.deepCopy();
			
			for(Element e:d2.getElementsListByTagName("tag")) {
				e.setAttribute("a", "bar");
			}
			System.out.println(d.documentToString());
			System.out.println("######################");
			System.out.println(d2.documentToString());
			System.out.println("######################");
			d2=d;
			System.out.println(d2.documentToString());
			//d.saveDocumentToFile(new File("C:\\Downloads\\test2.xml"));
		}catch(Exception e){e.printStackTrace();}
		
	} 
}
