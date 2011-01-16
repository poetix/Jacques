package com.codepoetics.jd;

import com.codepoetics.jd.api.Accessor;
import com.codepoetics.jd.api.Assigner;
import com.codepoetics.jd.api.Getter;
import com.codepoetics.jd.api.Key;
import com.codepoetics.jd.api.Setter;

public class SimpleAccessor<I, T> implements Accessor<I, T> {
	
	public static <K, I, T> Accessor<I, T> accessor(Key<K, I, T> key) {
		return accessor(key.getter(), key.setter());
	}
	
	public static <I, T> Accessor<I, T> accessor(
			Getter<? super I, ? extends T> getter,
			Setter<? super I, T> setter) {
		return new SimpleAccessor<I, T>(getter, setter);
	}
	
	private final Getter<? super I, ? extends T> getter;
	private final Setter<? super I, T> setter;

	protected SimpleAccessor(
			Getter<? super I, ? extends T> getter,
			Setter<? super I, T> setter) {
		this.getter = getter;
		this.setter = setter;
		
	}
	@Override
	public T get(I instance) {
		return getter.get(instance);
	}

	@Override
	public T set(I instance, T value) {
		return setter.set(instance, value);
	}

	@Override
	public Assigner<I> of(T input) {
		return SimpleAssigner.<I, T>value(input).of(this);
	}

}
