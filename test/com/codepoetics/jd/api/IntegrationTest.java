package com.codepoetics.jd.api;

import static com.codepoetics.jd.api.IntegrationTest.Line.END;
import static com.codepoetics.jd.api.IntegrationTest.Line.LINE;
import static com.codepoetics.jd.api.IntegrationTest.Line.POINT;
import static com.codepoetics.jd.api.IntegrationTest.Line.START;
import static com.codepoetics.jd.api.IntegrationTest.Line.X;
import static com.codepoetics.jd.api.IntegrationTest.Line.Y;
import static com.codepoetics.jd.api.JD.accessor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class IntegrationTest {

	public static class Line {
		
		public static Class<Line> LINE = Line.class;
		public static Class<Point> POINT = Point.class;
		
		public Point start;
		public static Accessor<Line, Point> START = accessor(LINE, "start");
		
		public Point end;
		public static Accessor<Line, Point> END = accessor(LINE, "end");
		
		public static Accessor<Point, Integer> X = accessor(POINT, "x");
		public static Accessor<Point, Integer> Y = accessor(POINT, "y");
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	makeAValueTypeWithPublicFields() {
		Line line = Make.a(LINE).with(
				START.of(Make.a(POINT)
							 .with(X.of(0), Y.of(0))),
				END.of(Make.a(POINT)
						   .with(X.of(42), Y.of(23))));
		
		assertThat(line.start.x, is(0));
		assertThat(line.start.y, is(0));
		assertThat(line.end.x, is(42));
		assertThat(line.end.y, is(23));
	}
	
	@Test public void
	calculateAveragePoint() {
		List<Point> points = Arrays.asList(
			new Point(0, 0),
			new Point(10, 0),
			new Point(0, 10),
			new Point(10, 10));
	
		Point averagePoint = new Point(
				average(X).of(points),
				average(Y).of(points));
		
		assertThat(X.get(averagePoint), is(5));
		assertThat(Y.get(averagePoint), is(5));
	}

	private <T> Maker<List<T>, Integer> average(final Getter<T, Integer> getter) {
		return new Maker<List<T>, Integer>() {

			@Override
			public Integer of(List<T> input) {
				int sum = 0;
				int count = 0;
				for (T t : input) {
					sum += getter.get(t);
					count++;
				}
				return sum / count;
			}
		};
	}
}