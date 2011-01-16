package com.codepoetics.jd;

import com.codepoetics.jd.api.Accessor;
import com.codepoetics.jd.api.Assigner;
import com.codepoetics.jd.api.Maker;

public class SimpleAssigner<I, T> implements Assigner<I> {

	public static <I, T> Maker<Accessor<I, T>, Assigner<I>> value(final T value) {
		return new Maker<Accessor<I, T>, Assigner<I>>() {

			@Override
			public Assigner<I> of(Accessor<I, T> accessor) {
				return new SimpleAssigner<I, T>(accessor, value);
			}
		};
	}
	private final Accessor<I, T> accessor;
	private final T value;

	private SimpleAssigner(Accessor<I, T> accessor, T value) {
		this.accessor = accessor;
		this.value = value;		
	}
	
	@Override
	public void assignTo(I instance) {
		accessor.set(instance, value);
	}
	

}
