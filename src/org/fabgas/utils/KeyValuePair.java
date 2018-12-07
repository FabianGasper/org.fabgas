package org.fabgas.utils;

import java.util.Map;

public class KeyValuePair<K,V> implements Map.Entry<K,V>{
	private K key;
	private V val;
	public KeyValuePair(K key, V val) {
		this.key=key;
		this.val=val;
	}
	
	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public V getValue() {
		return this.val;
	}

	@Override
	public V setValue(V value) {
		V tmp = this.val;
		this.val = value;
		return tmp;
	}

}
