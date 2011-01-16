package com.codepoetics.jd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.codepoetics.jd.api.Accessor;
import com.codepoetics.jd.api.Getter;
import com.codepoetics.jd.api.MapGetter;
import com.codepoetics.jd.api.MapKey;
import com.codepoetics.jd.api.MapSetter;
import com.codepoetics.jd.api.Setter;

public class SimpleAccessorTest {

	private final Mockery context = new Mockery();
	
	@Test public void
	hasStaticFactoryMethod() {
		Accessor<Map<? super String, ? super Integer>, Integer> accessor =
			SimpleAccessor.accessor(MapKey.<String, Integer>mapKey("test"));
		
		assertThat(accessor, is(notNullValue(Accessor.class)));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	accessesInstanceUsingSuppliedGetter() {
		final Getter<Object, Integer> getter = context.mock(MapGetter.class);
		final Setter<Object, Integer> setter = context.mock(MapSetter.class);
		final Object instance = new Object();
		
		Accessor<Object, Integer> accessor =
			SimpleAccessor.accessor(getter, setter);
		
		context.checking(new Expectations() {{
			oneOf(setter).set(instance, 42); will(returnValue(42));
			oneOf(getter).get(instance); will(returnValue(42));
		}});
		
		accessor.set(instance, 42);
		assertThat(accessor.get(instance), is(42));
	}
}
