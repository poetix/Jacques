package com.codepoetics.jd;

import static com.codepoetics.jd.TestAccessors.FAVOURITE_COLOUR;
import static com.codepoetics.jd.TestAccessors.NAME;
import static java.awt.Color.RED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.codepoetics.jd.api.Instantiator;

public class HashMapInitializerTest {

	@Test public void
	createsEmptyHashMapByDefault() {
		Instantiator<Map<String, Object>> instantiator = HashMapInstantiator.hashMap();
		Map<String, Object> map = instantiator.instantiate();
		
		assertThat(map, instanceOf(HashMap.class));
		assertThat(map.values(), empty());
	}
	
	@Test public void
	instantiatesANewHashMapEveryTime() {
		Instantiator<Map<String, Object>> instantiator = HashMapInstantiator.hashMap();
		Map<String, Object> map1 = instantiator.instantiate();
		Map<String, Object> map2 = instantiator.instantiate();
		
		assertThat(map1, not(sameInstance(map2)));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	canBePrepopulatedWithMultipleValuesUsingAssigners() {
		HashMapInstantiator<String, Object> instantiator =
			HashMapInstantiator.hashMap(String.class).with(
					NAME.of("Dominic"),
					FAVOURITE_COLOUR.of(RED));
		
		Map<String, Object> map = instantiator.instantiate();
		
		assertThat(NAME.get(map), is("Dominic"));
		assertThat(FAVOURITE_COLOUR.get(map), is(RED));
	}
	
	@Test public void
	canBePrepopulatedWithChainedSingleValues() {
		HashMapInstantiator<String, Object> instantiator =
			HashMapInstantiator.hashMap(String.class)
			.with(NAME.of("Dominic"))
			.with(FAVOURITE_COLOUR.of(RED));
		
		Map<String, Object> map = instantiator.instantiate();
		
		assertThat(NAME.get(map), is("Dominic"));
		assertThat(FAVOURITE_COLOUR.get(map), is(RED));
	}
}
