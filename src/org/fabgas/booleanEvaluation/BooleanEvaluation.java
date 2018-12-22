package org.fabgas.booleanEvaluation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BooleanEvaluation {
	
	public interface Func{
		public boolean call(String exp);
	}
	
	public static class Expression{
		private String val=null;
		private char op;
		private List<Expression> list = new ArrayList<>();
		private boolean hasOp;
		private boolean term;
		public Expression(String val){
			this(val,'-',false,false);
		}
		public Expression(String val,char op,boolean hasOp,boolean term){
		if(val!=null)
			this.val=val.replace(" ","").replace("\t", "");
			this.op=op;
			this.hasOp=hasOp;
			this.term=term;
		}
		public boolean evaluate(Func func) throws ParseException{
			//System.out.println(">"+val+"<");
			if(val==null)return false;
			int parCount=0; 
			int i=0;
			boolean inComm=false;
			while(i<val.length()) {	
				char c = val.charAt(i);   
				if(c=='"') {
					inComm=!inComm;
				}
				if(inComm) {
					i++;
					continue;	
				}
				
				if(i==val.length()-1) {
					if((parCount>0 && c!=')' ) || (c=='|' || c=='&')   ) 
						throw new ParseException("Unerwartetes Ende des Ausdrucks: "+val,i);
					if(val.charAt(i)!=')')
						list.add(new Expression(val, '-',false,true));
					else {
						if(val.charAt(0)=='!')
							list.add(new Expression(val,'-',false,false));	
						else
							list.add(new Expression(val.substring(1, i),'-',false,false));	
					}

				}
			    if(c=='(') {
			    	if(i>0 && (val.charAt(i-1)!='(' && val.charAt(i-1)!='&'  && val.charAt(i-1)!='|' && val.charAt(i-1)!='!' )  ) 
			    		throw new ParseException("Unerwartete oeffnende Klammer",i);
			    	parCount++;
			    }
			    if(c==')') {
			    	if(parCount==0)
			    		throw new ParseException("Unerwartete schliessende Klammer",i);
			    	parCount--;
			    	if(parCount==0) {
				    	if(i==val.length()-2) throw new ParseException("Fehlende rechte Seite des Ausdrucks",i);
				    	if(i<val.length()-2) {
				    		if(val.charAt(i+1)!='&' && val.charAt(i+1)!='|')
					    		throw new ParseException("Unbekannter Operator: "+val.charAt(i+1),i+1);
				    		list.add(new Expression(val.substring(0, i+1),val.charAt(i+1),true,false));	
				    		val=val.substring(i+2, val.length());
				    		i=-1;
				    	}
			    	}
			    }
			    if((c=='&' || c=='|') && parCount==0) {
			    	if(i==0)
			    		throw new ParseException("Unerwarteter Anfang des Ausdrucks: "+val,i);
	    			list.add(new Expression(val.substring(0, i),c,true,false));
	    			val=val.substring(i+1, val.length());
		    		i=-1;
			    }
			    i++;
			}
			if(inComm)throw new ParseException("Anfuehrungszeichen <\"> wurde nicht geschlossen",i);
			boolean res=true;
			boolean first=true;
			boolean lastHad=false;
			char last='-';
			for(Expression e:list) {
				boolean val;
				if(e.term) {
					if(e.val.length()==1) {if(e.val.charAt(0)=='!')val=false;else val=func.call(e.val.replace("\"", ""));}
					else {
						if(e.val.charAt(0)=='!')val=!func.call(e.val.substring(1).replace("\"", ""));
						else val=func.call(e.val.replace("\"", ""));
					}
				}
				else { 
					if(e.val.length()>1) {
						if(e.val.charAt(0)=='!') {
							e.val=e.val.substring(1);
							val=!e.evaluate(func);
						}else {
							val=e.evaluate(func);
						}
					}
					else val=e.evaluate(func);
				}
				if(first) {
					first=false;
					res=val;
				}else if(lastHad){
						if(last=='&') res=res && val;
						else if(last=='|') res=res || val;
						else throw new ParseException("Unbekannter Operator",-1);
					}else {
						throw new ParseException("Term ohne Operator",-1);
					}
				if(e.hasOp) {
					last=e.op;
					lastHad=true;
				}
				else lastHad=false;
			}
			return res;
		}
	}
	
	public static boolean evaluate(String expression, Func func) {
		Expression n = new Expression(expression);
		try {
			return n.evaluate(func);
		}catch(ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
}
