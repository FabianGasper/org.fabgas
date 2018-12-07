package org.fabgas.tests;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.fabgas.utils.HashMap;
import org.fabgas.utils.KeyValuePair;
import org.fabgas.utils.Map;
import org.fabgas.utils.SimpleDateFormat;

public class Main {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		Entry<String,String> kvp = new KeyValuePair<String, String>("foo", "bar");
		kvp=map.put(kvp);
/*		System.out.println(map.get("foo"));
		System.out.println(kvp.setValue("test"));
		System.out.println(map.get("foo"));
		System.out.println(map.put("foo","bla"));
		System.out.println(kvp.getValue());
*/
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//1390518720000
/*	
		try {
			System.out.println(dt.strictParse("2014-12-24 13:75").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
*/		
		ArrayList<String> a = new ArrayList<String>();

		a.add("1");
		a.add("2");
		a.add("foo");
//		System.out.println(a.remove(0));
//		System.out.println(a);
		for(String i:a) {
			System.out.println("Hallo: "+i);
		}
		
	} 
}
