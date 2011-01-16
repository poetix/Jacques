package com.codepoetics.jd.api;

public interface Setter<I, T> {
	public T set(I instance, T value);
}
