package mylearncode.undergrowth.designmode.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tea extends CaffeineBeverage {

	@Override
	void brew() {
		// TODO Auto-generated method stub
		System.out.println("�÷�ˮ���ݲ�Ҷ");
	}

	@Override
	void addCondiments() {
		// TODO Auto-generated method stub
		System.out.println("������");
	}

	/**
	 * ��д���ӷ���
	 */
	@Override
	boolean isAddCondiment() {
		// TODO Auto-generated method stub
		boolean isAdd=true;
		String input=getInput();
		if(!"y".equalsIgnoreCase(input)){
			isAdd=false;
		}
		return isAdd;
	}

	private String getInput() {
		// TODO Auto-generated method stub
		String input=null;
		System.out.println("�Ƿ���Ҫ�ڲ����������");
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			input=reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;
	}
    
	
}
