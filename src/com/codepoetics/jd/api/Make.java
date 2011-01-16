package com.codepoetics.jd.api;

import static com.codepoetics.jd.CompoundAssigner.compoundAssigner;

import com.codepoetics.jd.NewInstanceInstantiator;

public class Make<T> {

	private final Instantiator<? extends T> instantiator;

	public static <T> Make<T> a(Class<? extends T> clazz) {
		return a(NewInstanceInstantiator.newInstanceOf(clazz));
	}
	
	public static <T> Make<T> a(Instantiator<? extends T> instantiator) {
		return new Make<T>(instantiator);
	}
		
	private Make(Instantiator<? extends T> instantiator) {
		this.instantiator = instantiator;
	}
	
	public T with(Assigner<? super T>...assigners) {
		T newInstance = instantiator.instantiate();
		compoundAssigner(assigners).assignTo(newInstance);
		return newInstance;
	}
		
}
