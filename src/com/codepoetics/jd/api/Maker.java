package com.codepoetics.jd.api;

public interface Maker<T1, T2> {

	T2 of(T1 input);
	
}
