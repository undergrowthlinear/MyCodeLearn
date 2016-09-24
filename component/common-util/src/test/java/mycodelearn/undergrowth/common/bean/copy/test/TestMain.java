package mycodelearn.undergrowth.common.bean.copy.test;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.cglib.beans.BeanCopier;

import mycodelearn.undergrowth.common.bean.copy.FromBean;
import mycodelearn.undergrowth.common.bean.copy.FromSecondBean;
import mycodelearn.undergrowth.common.bean.copy.IMethodCallBack;
import mycodelearn.undergrowth.common.bean.copy.ToBean;

public class TestMain {

	 /**
     * @param args
     */
    public static void main(String[] args) {
    	FromSecondBean fromSecondBean=new FromSecondBean();
        fromSecondBean.setAddress("北京市朝阳区大屯路");
        fromSecondBean.setAge(20);
        fromSecondBean.setMoney(30000.111);
        fromSecondBean.setIdno("110330219879208733");
        fromSecondBean.setName("测试");
        //
    	FromBean fb = new FromBean();
        fb.setAddress("北京市朝阳区大屯路");
        fb.setAge(20);
        fb.setMoney(30000.111);
        fb.setIdno("110330219879208733");
        fb.setName("测试");
        fb.setFromSecondBean(fromSecondBean);

        IMethodCallBack beanutilCB = new IMethodCallBack() {

            @Override
            public String getMethodName() {
                return "BeanUtil.copyProperties";
            }

            @Override
            public ToBean callMethod(FromBean frombean) throws Exception {

                ToBean toBean = new ToBean();
                BeanUtils.copyProperties(toBean, frombean);
                return toBean;
            }
        };

        IMethodCallBack propertyCB = new IMethodCallBack() {

            @Override
            public String getMethodName() {
                return "PropertyUtils.copyProperties";
            }

            @Override
            public ToBean callMethod(FromBean frombean) throws Exception {
                ToBean toBean = new ToBean();
                PropertyUtils.copyProperties(toBean, frombean);
                return toBean;
            }
        };

        IMethodCallBack springCB = new IMethodCallBack() {

            @Override
            public String getMethodName() {
                return "org.springframework.beans.BeanUtils.copyProperties";
            }

            @Override
            public ToBean callMethod(FromBean frombean) throws Exception {
                ToBean toBean = new ToBean();
                org.springframework.beans.BeanUtils.copyProperties(frombean,
                        toBean);
                return toBean;
            }
        };

        IMethodCallBack cglibCB = new IMethodCallBack() {
            BeanCopier bc = BeanCopier.create(FromBean.class, ToBean.class,
                    false);

            @Override
            public String getMethodName() {
                return "BeanCopier.create";
            }

            @Override
            public ToBean callMethod(FromBean frombean) throws Exception {
                ToBean toBean = new ToBean();
                bc.copy(frombean, toBean, null);
                return toBean;
            }
        };

        // 数量较少的时候，测试性能
        BenchmarkTest bt = new BenchmarkTest(10);
        bt.benchmark(beanutilCB, fb);
        bt.benchmark(propertyCB, fb);
        bt.benchmark(springCB, fb);
        bt.benchmark(cglibCB, fb);
        System.out.println("*********************************************************");
        // 测试一万次性能测试
        BenchmarkTest bt10000 = new BenchmarkTest(10000);
        bt10000.benchmark(beanutilCB, fb);
        bt10000.benchmark(propertyCB, fb);
        bt10000.benchmark(springCB, fb);
        bt10000.benchmark(cglibCB, fb);
        System.out.println("*********************************************************");
        // 担心因为顺序问题影响测试结果
        BenchmarkTest bt1000R = new BenchmarkTest(10000);
        bt1000R.benchmark(cglibCB, fb);
        bt1000R.benchmark(springCB, fb);
        bt1000R.benchmark(propertyCB, fb);
        bt1000R.benchmark(beanutilCB, fb);
        System.out.println("*********************************************************");
        // 担心因为顺序问题影响测试结果
        BenchmarkTest bt100000R = new BenchmarkTest(100000);
        bt100000R.benchmark(cglibCB, fb);
        bt100000R.benchmark(springCB, fb);
        bt100000R.benchmark(propertyCB, fb);
        bt100000R.benchmark(beanutilCB, fb);
    }
	
}
