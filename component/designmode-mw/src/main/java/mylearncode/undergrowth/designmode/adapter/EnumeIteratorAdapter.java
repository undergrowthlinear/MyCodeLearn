package mylearncode.undergrowth.designmode.adapter;
/**
 * 将Enumeration转换为Iterator
 */
import java.util.Enumeration;
import java.util.Iterator;

public class EnumeIteratorAdapter implements Iterator {

	Enumeration enumeration;
	
	public EnumeIteratorAdapter(Enumeration enumeration){
		this.enumeration=enumeration;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return enumeration.hasMoreElements();
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return enumeration.nextElement();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
