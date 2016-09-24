package mycodelearn.undergrowth.common.bean.copy.test;

import mycodelearn.undergrowth.common.bean.copy.FromBean;
import mycodelearn.undergrowth.common.bean.copy.IMethodCallBack;
import mycodelearn.undergrowth.common.bean.copy.ToBean;

public class BenchmarkTest {

	private int count;

    public BenchmarkTest(int count) {
        this.count = count;
        System.out.println("性能测试" + this.count + "==================");
    }

    public void benchmark(IMethodCallBack m, FromBean frombean) {
        try {
            long begin = new java.util.Date().getTime();
            ToBean tobean = null;
            System.out.println(m.getMethodName() + "开始进行测试");
            for (int i = 0; i < count; i++) {

                tobean = m.callMethod(frombean);

            }
            long end = new java.util.Date().getTime();
            System.out.println(m.getMethodName() + "耗时" + (end - begin));
           /* System.out.println(tobean.getAddress());
            System.out.println(tobean.getAge());
            System.out.println(tobean.getIdno());
            System.out.println(tobean.getMoney());
            System.out.println(tobean.getName());
            System.out.println(tobean.getFromSecondBean());*/
            System.out.println(tobean);
            System.out.println(m.getMethodName() + "结束测试进行测试==================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
