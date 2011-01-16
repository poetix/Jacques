package com.codepoetics.jd.api;

public interface Getter<I, T> {
	public T get(I instance);
}