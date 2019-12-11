package com.thread;

import javax.sound.midi.Soundbank;

public class TomAndJerryJob implements Runnable{

	private BankAccount account = new BankAccount();
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			makeWithdraw(15);
			if(account.getBalance() < 0){
				System.out.println("Overdrawn");
			}
		}
	}
	
	public static void main(String[] args) {
		TomAndJerryJob job = new TomAndJerryJob();
		Thread tom = new Thread(job);
		Thread jerry = new Thread(job);
		tom.setName("tom");
		jerry.setName("jerry");
		tom.start();
		jerry.start();
	}
	
	
	/**
	 * 取钱
	 * @param amount
	 */
	private synchronized void makeWithdraw(int amount){
//	private void makeWithdraw(int amount){
		if(account.getBalance() >= amount){
			System.out.println(Thread.currentThread().getName()+" is about to withdraw");
			try {
				System.out.println(Thread.currentThread().getName()+" is going to sleep");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" wake up");
			account.withdraw(amount);
			System.out.println(Thread.currentThread().getName()+" complete the withdraw and the overage is "+account.getBalance());
		}else {
			System.out.println("sorry,not enough for "+Thread.currentThread().getName());
		}
	}
	
}


// 银行账户类
class BankAccount{
	private int balance = 100;	//默认账户初始只有100元
	public int getBalance() {
		return balance;
	}
	public void withdraw(int amount){
		balance = balance - amount;
	}
}