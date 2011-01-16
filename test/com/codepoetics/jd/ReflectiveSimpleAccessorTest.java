package com.codepoetics.jd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.awt.Point;

import org.junit.Test;

import com.codepoetics.jd.api.Accessor;
import com.codepoetics.jd.api.Make;


public class ReflectiveSimpleAccessorTest {

	@SuppressWarnings("unchecked")
	@Test public void
	providesStaticUtilityMethodToCreateWithClassAndFieldName() {
		Accessor<Point, Integer> X =
			ReflectiveSimpleAccessor.accessor(Point.class, "x");
		Accessor<Point, Integer> Y =
			ReflectiveSimpleAccessor.accessor(Point.class, "y");
		
		Point p = Make.a(Point.class).with(
				X.of(20),
				Y.of(10));
		
		assertThat(p.x, is(20));
		assertThat(p.y, is(10));
		assertThat(X.get(p), is(20));
		assertThat(Y.get(p), is(10));
	}
}
