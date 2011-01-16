package com.codepoetics.jd;

import com.codepoetics.jd.api.Instantiator;

public class NewInstanceInstantiator<T> implements Instantiator<T> {

	public static <T> Instantiator<T> newInstanceOf(Class<T> clazz) {
		return new NewInstanceInstantiator<T>(clazz);
	}
	
	private final Class<T> clazz;

	private NewInstanceInstantiator(Class<T> clazz) {
		this.clazz = clazz;	
	}

	@Override
	public T instantiate() {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
}
