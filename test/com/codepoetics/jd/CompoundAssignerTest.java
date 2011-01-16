package com.codepoetics.jd;

import static com.codepoetics.jd.CompoundAssigner.compoundAssigner;
import static com.codepoetics.jd.TestAccessors.FAVOURITE_COLOUR;
import static com.codepoetics.jd.TestAccessors.NAME;
import static java.awt.Color.RED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class CompoundAssignerTest {

	private final Map<String, Object> map = new HashMap<String, Object>();
	
	@Test public void
	hasStaticInitializer() {
		CompoundAssigner<Map<? super String, ? super Object>> compoundAssigner =
			CompoundAssigner.compoundAssigner();
		
		assertThat(compoundAssigner, instanceOf(CompoundAssigner.class));
	}
	
	@Test public void
	canAddSingleAssigner() {
		CompoundAssigner<Map<? super String, ? super Object>> compoundAssigner =
			CompoundAssigner.compoundAssigner();
		
		compoundAssigner.add(NAME.of("Dominic"));
		compoundAssigner.assignTo(map);
		
		assertThat(NAME.get(map), is("Dominic"));
 	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	canAddMultipleAssigners() {
		CompoundAssigner<Map<? super String, ? super Object>> compoundAssigner =
			CompoundAssigner.compoundAssigner();
		
		compoundAssigner.add(NAME.of("Dominic"), FAVOURITE_COLOUR.of(RED));
		compoundAssigner.assignTo(map);
		
		assertThat(NAME.get(map), is("Dominic"));
		assertThat(FAVOURITE_COLOUR.get(map), is(RED));
 	}
	
	@Test public void
	canInitializeWithSingleAssigner() {
		CompoundAssigner<Map<? super String, ? super Object>> compoundAssigner =
			CompoundAssigner.compoundAssigner(NAME.of("Dominic"));
		
		compoundAssigner.assignTo(map);
		
		assertThat(NAME.get(map), is("Dominic"));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	canInitializeWithMultipleAssigners() {
		CompoundAssigner<Map<? super String, ? super Object>> compoundAssigner =
			compoundAssigner(NAME.of("Dominic"),
					FAVOURITE_COLOUR.of(RED));
		
		compoundAssigner.assignTo(map);
		
		assertThat(NAME.get(map), is("Dominic"));
		assertThat(FAVOURITE_COLOUR.get(map), is(RED));
	}
}
