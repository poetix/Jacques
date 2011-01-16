package com.codepoetics.jd;

import static java.awt.Color.RED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.awt.Color;
import java.awt.Point;

import org.junit.Test;

import com.codepoetics.jd.api.Getter;
import com.codepoetics.jd.api.Key;
import com.codepoetics.jd.api.Setter;

public class ReflectiveKeyTest {
	
	public static class Person {
		
		private String name;
		private Color favouriteColor;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Color getFavouriteColor() {
			return favouriteColor;
		}
		public void setFavouriteColor(Color favouriteColor) {
			this.favouriteColor = favouriteColor;
		}
		
	}
	
	@Test public void
	hasStaticInitializer() {
		Key<String, Point, Integer> xKey =
			ReflectiveKey.key(Point.class, "x");
		
		assertThat(xKey.getKey(), is("x"));
	}
	
	@Test public void
	createsGetterForPublicFieldOfTargetClass() {
		Key<String, Point, Integer> xKey =
			ReflectiveKey.key(Point.class, "x");
		Getter<Point, Integer> getX = xKey.getter();
		
		Point p = new Point();
		p.x = 42;
			
		assertThat(getX.get(p), is(42));
	}
	
	@Test public void
	createsSetterForPublicFieldOfTargetClass() {
		Key<String, Point, Integer> xKey =
			ReflectiveKey.key(Point.class, "x");
		Setter<Point, Integer> setX = xKey.setter();
		
		Point p = new Point();
		setX.set(p, 42);
			
		assertThat(p.x, is(42));
	}
	
	@Test public void
	createsGetterForPrivateFieldWithGetMethod() {
		Key<String, Person, String> name =
			ReflectiveKey.key(Person.class, "name");
		
		Getter<Person, String> getName = name.getter();
		
		Person person = new Person();
		person.setName("Dominic");
		
		assertThat(getName.get(person), is("Dominic"));
	}
	
	@Test public void
	createsSetterForPrivateFieldWithSetMethod() {
		Key<String, Person, Color> favouriteColour =
			ReflectiveKey.key(Person.class, "favouriteColor");
		
		Setter<Person, Color> favouriteColourSetter = favouriteColour.setter();
		
		Person person = new Person();
		favouriteColourSetter.set(person, RED);
		
		assertThat(person.getFavouriteColor(), is(RED));
	}
}
