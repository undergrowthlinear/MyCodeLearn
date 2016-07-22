package mycodelearn.undergrowth.lucene.analyze.mw.sp;

public interface FilterAccessor {

	public void set();
	public void clear();
	public boolean getState();
	public String[] values();
	public String getField();
	public void setField(String fieldName);
}
