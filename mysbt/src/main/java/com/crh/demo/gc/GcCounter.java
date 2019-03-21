package com.crh.demo.gc;

public class GcCounter {
	private static final int temp= 1024 * 1024; 
	/** 
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收过 
     */  
    private byte[] bigSize = new byte[100 * temp];  
	public GcCounter instance;
	public static void main(String[] args) {
		GcCounter gcA=new GcCounter();
		GcCounter gcB=new GcCounter();
		gcA.instance=gcB;
		gcB.instance=gcA;
		gcA=null;
		gcB=null;
		System.gc();
	}
}
