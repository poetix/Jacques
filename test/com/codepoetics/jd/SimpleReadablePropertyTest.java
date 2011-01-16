package com.codepoetics.jd;

import static com.codepoetics.jd.api.JD.getter;
import static com.codepoetics.jd.SimpleReadablePropertyTest.Person.NAME;
import static java.awt.Color.RED;
import static org.hamcrest.Matchers.is;

import java.awt.Color;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.codepoetics.jd.api.Getter;
import com.codepoetics.jd.api.ReadableProperty;


public class SimpleReadablePropertyTest {

	public static final class Person {
		private final String name;
		public final static  Getter<Person, String> NAME =
			getter(Person.class, "name");
		private final Color favouriteColour;
		
		public Person(String name, Color favouriteColour) {
			this.name = name;
			this.favouriteColour = favouriteColour;
		}
		
		public String getName() { return name; }
		public Color getFavouriteColour() { return favouriteColour; }
	}
	
	@Test public void
	hasStaticMethodToCreateReadableProperty() {
		Person dominic = new Person("Dominic", RED);
		ReadableProperty<String> nameOfDominic =
			SimpleReadableProperty.property(NAME).of(dominic);
		
		MatcherAssert.assertThat(nameOfDominic.get(), is("Dominic"));
	}
}
