package com.codepoetics.jd.api;

public interface Property<T> extends ReadableProperty<T> {
	
	T set(T value);
	
}
