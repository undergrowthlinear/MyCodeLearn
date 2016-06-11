package mylearncode.undergrowth.designmode.template;

/**
 * Ѽ�ӱȽ���
 * @author Administrator
 *
 */
public class DuckComparable implements Comparable<Object> {

	int weight;
	String name;
	
	public DuckComparable(int weight, String name) {
		super();
		this.weight = weight;
		this.name = name;
	}
    
	/**
	 * �Ƚ�����Ѽ�ӵ����� �Ƿ����
	 */
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		DuckComparable otherDuckComparable=(DuckComparable) o;
		if(this.weight>otherDuckComparable.weight) return 1;
		else if(this.weight==otherDuckComparable.weight) return 0;
		else {
		  return -1;	
		}
	}



	@Override
	public String toString() {
		return "DuckComparable [weight=" + weight + ", name=" + name + "]";
	}

}
