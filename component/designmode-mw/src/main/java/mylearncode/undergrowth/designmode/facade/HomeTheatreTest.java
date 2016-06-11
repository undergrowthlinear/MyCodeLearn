package mylearncode.undergrowth.designmode.facade;

import static org.junit.Assert.*;

import org.junit.Test;

public class HomeTheatreTest {

	@Test
	public void test() {
		Amplifier amp=new Amplifier();
		Projector pro=new Projector();
		Screen scr=new Screen();
		Curtain cur=new Curtain();
		HomeTheatre homeTheatre=new HomeTheatre(amp, pro, scr, cur);
		homeTheatre.watchMovie();
		System.out.println("=================���Ƿָ���==================");
		homeTheatre.closeMovie();
	}

}
