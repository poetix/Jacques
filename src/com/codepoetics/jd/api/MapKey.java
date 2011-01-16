package com.codepoetics.jd.api;

import java.util.Map;

public class MapKey<K, V> implements Key<K, Map<? super K, ? super V>, V> {

	public static <K, V> MapKey<K, V> mapKey(K key) {
		return new MapKey<K, V>(key);
	}
	
	private final K key;
	
	private MapKey(K key) {
		this.key = key;
	}
	
	@Override
	public K getKey() { return key; }

	@Override
	public MapGetter<K, V> getter() {
		return new MapGetter<K, V>() {

			@SuppressWarnings("unchecked")
			@Override
			public V get(Map<? super K, ? super V> instance) {
				return (V) instance.get(key);
			}
		};
	}

	@Override
	public MapSetter<K, V> setter() {
		return new MapSetter<K, V>() {

			@Override
			public V set(Map<? super K, ? super V> instance, V value) {
				instance.put(key, value);
				return value;
			}
		};
	}
}
