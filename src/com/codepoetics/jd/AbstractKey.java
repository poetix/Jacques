package com.codepoetics.jd;

import com.codepoetics.jd.api.Key;

public abstract class AbstractKey<K, I, T> implements Key<K, I, T> {

	protected final K key;
	
	public AbstractKey(K key) {
		this.key = key;
	}
	
	@Override
	public K getKey() { return key; }
}
