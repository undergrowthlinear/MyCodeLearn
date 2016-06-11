package mylearncode.undergrowth.designmode.adapter;

import java.util.Enumeration;

public class  NumEnum implements Enumeration{

	int length;
	int count;
	String[] data=null;
	public NumEnum(String[] data){
		this.data=data;
		count=0;
		length=data.length;
	}
	
	@Override
	public boolean hasMoreElements() {
		// TODO Auto-generated method stub
		return (count<length);
	}

	@Override
	public Object nextElement() {
		// TODO Auto-generated method stub
		return data[count++];
	}
	
}
