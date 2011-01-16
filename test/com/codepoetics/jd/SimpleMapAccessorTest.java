package com.codepoetics.jd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.codepoetics.jd.api.MapAccessor;
import com.codepoetics.jd.api.MapKey;


public class SimpleMapAccessorTest {

	@Test public void
	hasStaticInitializer() {
		MapKey<String, Integer> key = MapKey.mapKey("test");
		MapAccessor<String, Integer> accessor = SimpleMapAccessor.accessor(key);
		
		assertThat(accessor, is(Matchers.notNullValue(MapAccessor.class)));
	}
	
	@Test public void
	hasAlternativeStaticInitializerWhichCreatesKey() {
		MapAccessor<String, Integer> accessor = SimpleMapAccessor.accessor("test");
		
		assertThat(accessor, is(Matchers.notNullValue(MapAccessor.class)));
	}
	
	@Test public void
	canGetAValueFromAMap() {
		MapAccessor<String, Integer> accessor = SimpleMapAccessor.accessor("test");
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("test", 3);
		assertThat(accessor.get(map), is(3));
	}
	
	@Test public void
	canSetAValueInAMap() {
		MapAccessor<String, Integer> accessor = SimpleMapAccessor.accessor("test");
		Map<String, Object> map = new HashMap<String, Object>();
		
		accessor.set(map, 3);
		assertThat((Integer) map.get("test"), is(3));
	}
}
