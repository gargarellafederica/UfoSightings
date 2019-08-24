package it.polito.tdp.ufo.model;

import java.time.Year;

public class AnnoCount {
	private Year Anno;
	private int count;
	
	public AnnoCount(Year anno, int count) {
		super();
		Anno = anno;
		this.count = count;
	}
	public Year getAnno() {
		return Anno;
	}
	public void setAnno(Year anno) {
		Anno = anno;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return Anno + "(" + count + ")";
	}

	
}
