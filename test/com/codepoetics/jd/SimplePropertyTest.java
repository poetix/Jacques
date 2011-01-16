package com.codepoetics.jd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.codepoetics.jd.api.Accessor;
import com.codepoetics.jd.api.Property;

public class SimplePropertyTest {

	private final Mockery context = new Mockery();
	
	@Test public void
	hasStaticMaker() {
		Accessor<Object, Integer> accessor = null;
		Object instance = null;
		
		Property<Integer> property = SimpleProperty.property(accessor).of(instance);
		
		assertThat(property, is(notNullValue(Property.class)));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	accessesInstanceThroughAccessor() {	
		final Accessor<Object, Integer> accessor = context.mock(Accessor.class);
		final Object instance = null;
		
		context.checking(new Expectations() {{
			oneOf(accessor).set(instance, 42);
			oneOf(accessor).get(instance); will(returnValue(42));
		}});
		
		Property<Integer> property = SimpleProperty.property(accessor).of(null);
		
		property.set(42);
		assertThat(property.get(), is(42));
		
		context.assertIsSatisfied();
	}
}
