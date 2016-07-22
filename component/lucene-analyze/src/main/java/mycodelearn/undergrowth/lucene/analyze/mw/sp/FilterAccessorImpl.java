package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.util.List;

/**
 * 
* Description: TODO(提供访问列表的控制)
* @author <a href="undergrowth@126.com">undergrowth</a>
* @date 2016年7月21日
* @version 1.0.0
 */
public class FilterAccessorImpl implements FilterAccessor {

	private List<String> accessList;
	private String fieldName;
	private boolean state;
	
	
	public List<String> getAccessList() {
		return accessList;
	}

	public FilterAccessorImpl(List<String> accessList,String fieldName) {
		super();
		this.accessList = accessList;
		this.fieldName=fieldName;
	}

	@Override
	public void set() {
		// TODO Auto-generated method stub
		this.state=true;
	}

	@Override
	public String[] values() {
		// TODO Auto-generated method stub
		String[] values=new String[accessList.size()];
		return  accessList.toArray(values);
	}

	@Override
	public String getField() {
		// TODO Auto-generated method stub
		return this.fieldName;
	}

	@Override
	public void setField(String fieldName) {
		// TODO Auto-generated method stub
		this.fieldName=fieldName;
	}

	@Override
	public void  clear() {
		// TODO Auto-generated method stub
		this.state=false;
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return this.state;
	}

}
