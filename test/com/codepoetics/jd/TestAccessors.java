package com.codepoetics.jd;

import static com.codepoetics.jd.SimpleMapAccessor.accessor;

import java.awt.Color;

import com.codepoetics.jd.api.MapAccessor;

public final class TestAccessors {

	public static final MapAccessor<String, String> NAME = accessor("name");
	public static final MapAccessor<String, Color> FAVOURITE_COLOUR
	= accessor("favouriteColour");

	private TestAccessors() { }
	
}
