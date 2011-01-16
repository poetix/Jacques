package com.codepoetics.jd;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.codepoetics.jd.api.Assigner;

public class CompoundAssigner<I> implements Assigner<I> {

	public static <I> CompoundAssigner<I> compoundAssigner() {
		return new CompoundAssigner<I>();
	}
	
	public static <I> CompoundAssigner<I> compoundAssigner(Assigner<? super I> assigner) {
		CompoundAssigner<I> compoundAssigner = new CompoundAssigner<I>();
		compoundAssigner.add(assigner);
		return compoundAssigner;
	}
	
	public static <I> CompoundAssigner<I> compoundAssigner(Assigner<? super I>...assigners) {
		return compoundAssigner(asList(assigners));
	}
	
	public static <I> CompoundAssigner<I> compoundAssigner(List<Assigner<? super I>> assigners) {
		CompoundAssigner<I> compoundAssigner = new CompoundAssigner<I>();
		compoundAssigner.add(assigners);
		return compoundAssigner;
	}
	
	private final List<Assigner<? super I>> assigners = new LinkedList<Assigner<? super I>>();
	
	private CompoundAssigner() { }

	@Override
	public void assignTo(I instance) {
		for (Assigner<? super I> assigner : assigners) {
			assigner.assignTo(instance);
		}
	}
	
	public CompoundAssigner<I> add(Assigner<? super I> assigner) {
		assigners.add(assigner);
		return this;
	}
	
	public CompoundAssigner<I> add(Assigner<? super I>...assigners) {
		return add(asList(assigners));
	}
	
	public CompoundAssigner<I> add(Collection<Assigner<? super I>> assigners) {
		this.assigners.addAll(assigners);
		return this;
	}	
}
