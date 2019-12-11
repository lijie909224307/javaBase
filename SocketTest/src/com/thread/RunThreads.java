package com.thread;


public class RunThreads implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 25; i++) {
			String name = Thread.currentThread().getName();
			System.out.println(name+" is running!");
			
		}
	}
	
	public static void main(String[] args) {
		Runnable runner = new RunThreads();
		Thread tom = new Thread(runner);
		Thread jerry = new Thread(runner);
		tom.setName("tom");
		jerry.setName("jerry");
		tom.start();
		jerry.start();
	}
	
}
