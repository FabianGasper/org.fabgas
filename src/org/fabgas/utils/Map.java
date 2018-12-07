package org.fabgas.utils;

public interface Map<K,V> extends java.util.Map<K,V>{
	
	public default Entry<K,V> put(Entry<K,V> e) {
		this.put(e.getKey(),e.getValue());
		for(Entry<K,V> ee : this.entrySet()) {
			if(ee.getKey().equals(e.getKey())) {
				return ee;
			}
		}
        return null;
    }

}
	

