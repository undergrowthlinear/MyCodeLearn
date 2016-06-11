package mylearncode.undergrowth.designmode.state.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mylearncode.undergrowth.designmode.state.GumballMachine;

public class GumballMachineTest {

	@Test
	public void test() {
		GumballMachine gumball=new GumballMachine(10);
		System.out.println(gumball);
		gumball.insertCoins();
		gumball.pressSold();
		System.out.println(gumball);
		gumball.insertCoins();
		gumball.pressSold();
		System.out.println(gumball);
		gumball.coinsHopper();
		gumball.insertCoins();
		System.out.println(gumball);
		gumball.coinsHopper();
		System.out.println(gumball);
		gumball.insertCoins();
		gumball.pressSold();
		System.out.println(gumball);
		gumball.insertCoins();
		gumball.pressSold();
		gumball.insertCoins();
		gumball.pressSold();
		gumball.insertCoins();
		gumball.pressSold();
		System.out.println(gumball);
	}

}
