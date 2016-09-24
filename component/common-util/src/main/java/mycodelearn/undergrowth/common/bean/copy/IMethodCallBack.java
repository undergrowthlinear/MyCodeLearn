package mycodelearn.undergrowth.common.bean.copy;

public interface IMethodCallBack {
	String getMethodName();

	ToBean callMethod(FromBean frombean) throws Exception;
}
