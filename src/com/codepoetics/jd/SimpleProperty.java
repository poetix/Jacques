package com.codepoetics.jd;

import com.codepoetics.jd.api.Accessor;
import com.codepoetics.jd.api.Maker;
import com.codepoetics.jd.api.Property;

public class SimpleProperty<I, T> implements Property<T> {

	private final Accessor<? super I, T> accessor;
	private final I instance;

	public static <I, T> Maker<I, Property<T>> property(final Accessor<? super I, T> accessor) {
		return new Maker<I, Property<T>>() {
			@Override
			public Property<T> of(I instance) {
				return new SimpleProperty<I, T>(accessor, instance);
			}
		};
	}
	
	private SimpleProperty(Accessor<? super I, T> accessor, I instance) {
		this.accessor = accessor;
		this.instance = instance;
	}
	
	public T get() {
		return accessor.get(instance);
	}
	
	public T set(T value) {
		return accessor.set(instance, value);
	}
}
