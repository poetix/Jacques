package com.codepoetics.jd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.codepoetics.jd.api.Accessor;
import com.codepoetics.jd.api.Assigner;

public class SimpleAssignerTest {

	private final Mockery context = new Mockery();
	
	@Test public void
	hasStaticMaker() {
		Accessor<Object, String> accessor = null;
		Assigner<Object> assigner = SimpleAssigner.value("test").of(accessor);
		
		assertThat(assigner, is(notNullValue(Assigner.class)));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	assignsValueToGivenInstanceUsingAccessor() {
		final Accessor<Object, String> accessor = context.mock(Accessor.class);
		final Object instance = new Object();
		Assigner<Object> assigner = SimpleAssigner.value("test").of(accessor);
		
		context.checking(new Expectations() {{
			oneOf(accessor).set(instance, "test"); will(returnValue("test"));
		}});
		
		assigner.assignTo(instance);
		
		context.assertIsSatisfied();
	}
}
