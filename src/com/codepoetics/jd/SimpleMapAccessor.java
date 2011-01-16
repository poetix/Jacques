package com.codepoetics.jd;

import java.util.Map;

import com.codepoetics.jd.api.Getter;
import com.codepoetics.jd.api.MapAccessor;
import com.codepoetics.jd.api.MapKey;
import com.codepoetics.jd.api.Setter;

public class SimpleMapAccessor<K, V> extends SimpleAccessor<Map<? super K, ? super V>, V>
	implements MapAccessor<K, V> {

	public static <K, V> MapAccessor<K, V> accessor(K key) {
		return accessor(MapKey.<K, V>mapKey(key));
	}
	
	public static <K, V> MapAccessor<K, V> accessor(MapKey<K, V> key) {
		return new SimpleMapAccessor<K, V>(key.getter(), key.setter());
	}
	
	protected SimpleMapAccessor(Getter<Map<? super K, ? super V>, V> getter,
			Setter<Map<? super K, ? super V>, V> setter) {
		super(getter, setter);
	}

}
