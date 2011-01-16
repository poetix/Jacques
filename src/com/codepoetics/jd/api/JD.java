package com.codepoetics.jd.api;

import java.util.List;

import com.codepoetics.jd.CompoundAssigner;
import com.codepoetics.jd.HashMapInstantiator;
import com.codepoetics.jd.NewInstanceInstantiator;
import com.codepoetics.jd.ReflectiveKey;
import com.codepoetics.jd.ReflectiveSimpleAccessor;
import com.codepoetics.jd.SimpleAccessor;
import com.codepoetics.jd.SimpleAssigner;
import com.codepoetics.jd.SimpleMapAccessor;
import com.codepoetics.jd.SimpleProperty;
import com.codepoetics.jd.SimpleReadableProperty;

public final class JD {

	public static <I, T> Maker<I, Property<T>> property(final Accessor<? super I, T> accessor) {
		return SimpleProperty.property(accessor);
	}
	
	public static <I, T> Maker<I, ReadableProperty<T>> property(
			final Getter<? super I, T> getter) {
		return SimpleReadableProperty.property(getter);
	}
	
	public static <K, V> MapAccessor<K, V> accessor(K key) {
		return SimpleMapAccessor.accessor(MapKey.<K, V>mapKey(key));
	}
	
	public static <K, V> MapAccessor<K, V> accessor(MapKey<K, V> key) {
		return SimpleMapAccessor.accessor(key);
	}
	
	public static <I, T> Maker<Accessor<I, T>, Assigner<I>> value(final T value) {
		return SimpleAssigner.value(value);
	}
	
	public static <K, I, T> Accessor<I, T> accessor(Key<K, I, T> key) {
		return SimpleAccessor.accessor(key);
	}
	
	public static <I, T> Accessor<I, T> accessor(
			Getter<? super I, ? extends T> getter,
			Setter<? super I, T> setter) {
		return SimpleAccessor.accessor(getter, setter);
	}
	
	public static <I, T> ReflectiveSimpleAccessor<I, T> accessor(Class<I> targetClass, String fieldName) {
		return ReflectiveSimpleAccessor.accessor(targetClass, fieldName);
	}
	
	public static <I, T> Key<String, I, T> key(Class<I> targetClass, String fieldName) {
		return ReflectiveKey.key(targetClass, fieldName);
	}
	
	public static <I, T> Getter<I, T> getter(Class<I> targetClass, String fieldName) {
		Key<String, I, T> key = ReflectiveKey.key(targetClass, fieldName);
		return key.getter();
	}
	
	public static <K> HashMapInstantiator<K, Object> hashMap(Class<K> keyClass) {
		return HashMapInstantiator.hashMap(keyClass);
	}
	
	public static <K, V> HashMapInstantiator<K, V> hashMap() {
		return HashMapInstantiator.hashMap();
	}
	
	public static <I> CompoundAssigner<I> compoundAssigner() {
		return CompoundAssigner.compoundAssigner();
	}
	
	public static <I> CompoundAssigner<I> compoundAssigner(Assigner<? super I> assigner) {
		return CompoundAssigner.compoundAssigner(assigner);
	}
	
	public static <I> CompoundAssigner<I> compoundAssigner(Assigner<? super I>...assigners) {
		return CompoundAssigner.compoundAssigner(assigners);
	}
	
	public static <I> CompoundAssigner<I> compoundAssigner(List<Assigner<? super I>> assigners) {
		return CompoundAssigner.compoundAssigner(assigners);
	}
	
	public static <T> Instantiator<T> newInstanceOf(Class<T> clazz) {
		return NewInstanceInstantiator.newInstanceOf(clazz);
	}

	
	private JD() { }
	
}
