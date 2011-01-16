package com.codepoetics.jd;

import com.codepoetics.jd.api.Getter;
import com.codepoetics.jd.api.Maker;
import com.codepoetics.jd.api.ReadableProperty;

public class SimpleReadableProperty<I, T> implements ReadableProperty<T> {

	public static <I, T> Maker<I, ReadableProperty<T>> property(
			final Getter<? super I, T> getter) {
		return new Maker<I, ReadableProperty<T>>() {
			@Override
			public ReadableProperty<T> of(I instance) {
				return new SimpleReadableProperty<I, T>(getter, instance);
			}
		};
	}
	
	private final Getter<? super I, T> getter;
	private final I instance;

	private SimpleReadableProperty(Getter<? super I, T> getter, I instance) {
		this.getter = getter;
		this.instance = instance;
	}
	
	@Override
	public T get() {
		return getter.get(instance);
	}

}
