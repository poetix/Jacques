package com.codepoetics.jd;

import com.codepoetics.jd.api.Getter;
import com.codepoetics.jd.api.Key;
import com.codepoetics.jd.api.Setter;

public class ReflectiveSimpleAccessor<I, T> extends SimpleAccessor<I, T> {

	public static <I, T> ReflectiveSimpleAccessor<I, T> accessor(Class<I> targetClass, String fieldName) {
		return accessor(ReflectiveKey.<I, T>key(targetClass, fieldName));
	}
	
	public static <I, T> ReflectiveSimpleAccessor<I, T> accessor(Key<String, I, T> key) {
		return new ReflectiveSimpleAccessor<I, T>(key.getter(), key.setter());
	}
	
	protected ReflectiveSimpleAccessor(Getter<? super I, ? extends T> getter,
			Setter<? super I, T> setter) {
		super(getter, setter);
	}

}
