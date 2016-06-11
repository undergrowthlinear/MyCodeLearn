package mylearncode.undergrowth.designmode.iterator.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mylearncode.undergrowth.designmode.iterator.ChineseFood;
import mylearncode.undergrowth.designmode.iterator.CoffeeMenu;
import mylearncode.undergrowth.designmode.iterator.Menu;
import mylearncode.undergrowth.designmode.iterator.PanCakeHouse;
import mylearncode.undergrowth.designmode.iterator.Waitress;
import mylearncode.undergrowth.designmode.iterator.WaitressList;

public class WaitressTest {

	@Test
	public void test() {

    Menu panMenu = new PanCakeHouse();
	Menu coffeeMenu = new CoffeeMenu();
	Menu chineseMenu = new ChineseFood();
	Waitress waitress=new Waitress(panMenu, coffeeMenu, chineseMenu);
    
	waitress.printMenu();
		
	System.out.println("\n\n\n\n");
	
	List<Menu> menus=new ArrayList<>();
	menus.add(panMenu);
	menus.add(coffeeMenu);
	menus.add(chineseMenu);
	WaitressList waitressList=new WaitressList(menus);
	waitressList.printMenu();
	
	}

}
