package com.codepoetics.jd;

import static java.lang.String.format;
import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isPublic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.codepoetics.jd.api.Getter;
import com.codepoetics.jd.api.Key;
import com.codepoetics.jd.api.Setter;

public class ReflectiveKey<I, T> extends AbstractKey<String, I, T> {

	public static <I, T> Key<String, I, T> key(Class<I> targetClass, String fieldName) {
		return new ReflectiveKey<I, T>(targetClass, fieldName);
	}
	
	private final class ReflectiveMethodGetter implements Getter<I, T> {
		private final Method method;

		private ReflectiveMethodGetter(Method method) {
			this.method = method;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T get(I instance) {
			try {
				return (T) method.invoke(instance);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private final class ReflectiveMethodSetter implements Setter<I, T> {
		private final Method method;

		private ReflectiveMethodSetter(Method method) {
			this.method = method;
		}

		@Override
		public T set(I instance, T value) {
			try {
				method.invoke(instance, value);
				return value;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private final class ReflectiveFieldGetter implements Getter<I, T> {
		
		private final Field field;

		public ReflectiveFieldGetter(Field field) {
			this.field = field;		
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public T get(I instance) {
			try {
				return (T) field.get(instance);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private final class ReflectiveFieldSetter implements Setter<I, T> {
		
		private final Field field;

		public ReflectiveFieldSetter(Field field) {
			this.field = field;
		}
		
		@Override
		public T set(I instance, T value) {
			try {
				field.set(instance, value);
				return value;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	private final Class<I> targetClass;
	
	private ReflectiveKey(Class<I> targetClass, String fieldName) {
		super(fieldName);
		this.targetClass = targetClass;
	}

	@Override
	public Getter<I, T> getter() {
		final Field field = getField();
		if (isPublic(field.getModifiers())) {
			return new ReflectiveFieldGetter(field);
		}
		final Method method = getGetterMethod();
		return new ReflectiveMethodGetter(method);
	}
	
	private Field getField() {
		try {
			return targetClass.getDeclaredField(key);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Method getGetterMethod() {
		try {
			return targetClass.getDeclaredMethod(
					format("get%s", uppercaseFirstCharKey()));
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Method getSetterMethod() {
		String setterName = format("set%s", uppercaseFirstCharKey());
		for (Method method : targetClass.getDeclaredMethods()) {
			if (method.getName().equals(setterName)) {
				return method;
			}
		}
		throw new RuntimeException(
				format("Method %s of class %s not found", setterName, targetClass));
	}
	
	private String uppercaseFirstCharKey() {
		return key.substring(0, 1).toUpperCase().concat(key.substring(1));
	}

	@Override
	public Setter<I, T> setter() {
		final Field field = getField();
		if (isPublic(field.getModifiers()) &! isFinal(field.getModifiers())) {
			return new ReflectiveFieldSetter(field);
		}
		final Method method = getSetterMethod();
		return new ReflectiveMethodSetter(method);
	}

}
