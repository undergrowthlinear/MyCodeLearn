package mylearncode.undergrowth.designmode.command.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mylearncode.undergrowth.designmode.command.Light;
import mylearncode.undergrowth.designmode.command.LightOffCommand;
import mylearncode.undergrowth.designmode.command.LightOnCommand;
import mylearncode.undergrowth.designmode.command.RemoteControl;

public class RemoteControlTest {

	@Test
	public void test() {
		RemoteControl control=new RemoteControl();
		Light light=new Light();
		LightOnCommand lightOnCommand=new LightOnCommand(light);
		LightOffCommand lightOffCommand=new LightOffCommand(light);
		control.setCommand(0, lightOnCommand, lightOffCommand);
		System.out.println(control);
		control.buttonOnWasPress(0);
		control.buttonOffWasPress(0);
		System.out.println(control);
		control.cancel();
		
	}

}
