package org.fabgas.utils;

import java.util.Comparator;
import java.util.SortedMap;

public class TreeMap<K,V> extends java.util.TreeMap<K,V> implements Map<K,V> {
	private static final long serialVersionUID = 1L;
	public TreeMap(){
		super();
	}
	public TreeMap(Comparator<? super K> comparator){
		super(comparator);
	}
	public TreeMap(Map<? extends K,? extends V> m){
		super(m);
	}
	public TreeMap(SortedMap<K,? extends V> m){
		super(m);
	}
};