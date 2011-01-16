package com.codepoetics.jd.api;

public interface Accessor<I, T> extends Getter<I, T>,
										Setter<I, T>,
										Maker<T, Assigner<I>> {
	
}
