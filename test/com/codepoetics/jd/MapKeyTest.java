package com.codepoetics.jd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.codepoetics.jd.api.Key;
import com.codepoetics.jd.api.MapGetter;
import com.codepoetics.jd.api.MapKey;
import com.codepoetics.jd.api.MapSetter;

public class MapKeyTest {

	@Test public void
	hasStaticInitializer() {
		Key<String, Map<? super String, ? super Integer>, Integer> mapKey =
			MapKey.mapKey("test");
		
		assertThat(mapKey.getKey(), is("test"));
	}
	
	@Test public void
	createsGetterForMap() {
		MapKey<String, Integer> mapKey = MapKey.mapKey("test");
		MapGetter<String, Integer> getter = mapKey.getter();
		
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("test", 42);
		
		assertThat(getter.get(testMap), is(42));
	}
	
	@Test public void
	createsSetterForMap() {
		MapKey<String, Integer> mapKey = MapKey.mapKey("test");
		MapSetter<String, Integer> setter = mapKey.setter();
		
		Map<String, Object> testMap = new HashMap<String, Object>();
		setter.set(testMap, 42);
		
		assertThat((Integer) testMap.get("test"), is(42));
	}
}