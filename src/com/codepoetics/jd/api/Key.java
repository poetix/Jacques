package com.codepoetics.jd.api;

public interface Key<K, I, T> {
	public K getKey();
	public Getter<I, T> getter();
	public Setter<I, T> setter();
}
