package com.codepoetics.jd;

import static com.codepoetics.jd.HashMapInstantiator.hashMap;
import static com.codepoetics.jd.ReflectiveSimpleAccessor.accessor;
import static com.codepoetics.jd.TestAccessors.FAVOURITE_COLOUR;
import static com.codepoetics.jd.TestAccessors.NAME;
import static java.awt.Color.RED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.awt.Point;
import java.util.Map;

import org.junit.Test;

import com.codepoetics.jd.api.Accessor;
import com.codepoetics.jd.api.Make;


public class MakeTest {

	@SuppressWarnings("unchecked")
	@Test public void
	canMakeAndPopulateAHashMap() {
		Map<String, Object> map = Make.a(hashMap(String.class)).with(
				NAME.of("Dominic"),
				FAVOURITE_COLOUR.of(RED));
		
		assertThat(NAME.get(map), is("Dominic"));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	canMakeAndPopulateAnObject() {
		Accessor<Point, Integer> X = accessor(Point.class, "x");
		Accessor<Point, Integer> Y = accessor(Point.class, "y");
		
		Point p = Make.a(Point.class).with(X.of(10), Y.of(20));
		
		assertThat(X.get(p), is(10));
		assertThat(Y.get(p), is(20));
	}
}
