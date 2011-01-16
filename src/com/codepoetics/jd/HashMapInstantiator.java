package com.codepoetics.jd;

import static com.codepoetics.jd.CompoundAssigner.compoundAssigner;
import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.codepoetics.jd.api.Assigner;
import com.codepoetics.jd.api.Instantiator;

public class HashMapInstantiator<K, V> implements Instantiator<Map<K, V>>{

	public static <K> HashMapInstantiator<K, Object> hashMap(Class<K> keyClass) {
		return new HashMapInstantiator<K, Object>();
	}
	
	public static <K, V> HashMapInstantiator<K, V> hashMap() {
		return new HashMapInstantiator<K, V>();
	}
	
	private HashMapInstantiator() { }
	
	private CompoundAssigner<Map<? super K, ? super V>> assigners = compoundAssigner();
	
	@Override
	public Map<K, V> instantiate() {
		Map<K, V> newMap = new HashMap<K, V>();
		assigners.assignTo(newMap);
		return newMap;
	}

	public HashMapInstantiator<K, V> with(Assigner<? super Map<? super K, ? super V>> assigner) {
		this.assigners.add(assigner);
		return this;
	}
	
	public HashMapInstantiator<K, V> with(Assigner<? super Map<? super K, ? super V>>...assigners) {
		return with(asList(assigners));
	}
	
	public HashMapInstantiator<K, V> with(Collection<Assigner<? super Map<? super K, ? super V>>> assigners) {
		this.assigners.add(assigners);
		return this;
	}
}
