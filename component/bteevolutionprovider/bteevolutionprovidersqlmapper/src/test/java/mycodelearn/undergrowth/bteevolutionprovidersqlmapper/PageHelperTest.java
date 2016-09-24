package mycodelearn.undergrowth.bteevolutionprovidersqlmapper;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import mycodelearn.undergrowth.bteevolutionprovidersqlmapper.mapper.UnderTestMapper;
import mycodelearn.undergrowth.bteevolutionprovidersqlmapper.model.UnderTest;
import mycodelearn.undergrowth.bteevolutionprovidersqlmapper.model.UnderTestExample;

public class PageHelperTest {

	int numAll = 1001;

	@Test
	public void testPage() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		UnderTestMapper underTestMapper = sqlSession.getMapper(UnderTestMapper.class);
		try {
			// 获取第1页，10条内容，默认查询总数count
			PageHelper.startPage(1, 10);

			UnderTestExample example = new UnderTestExample();
			// 紧跟着的第一个select方法会被分页
			List<UnderTest> list = underTestMapper.selectByExample(example);
			assertEquals(1, list.get(0).getId().intValueExact());
			assertEquals(10, list.size());
			// 分页时，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>，
			// 或者使用PageInfo类（下面的例子有介绍）
			assertEquals(numAll, ((Page) list).getTotal());
			// System.out.println("乱码");
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testPage2() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		UnderTestMapper underTestMapper = sqlSession.getMapper(UnderTestMapper.class);
		try {
			// 获取第1页，10条内容，默认查询总数count
			PageHelper.startPage(1, 10);

			// 紧跟着的第一个select方法会被分页
			UnderTestExample example = new UnderTestExample();
			// 紧跟着的第一个select方法会被分页
			List<UnderTest> list = underTestMapper.selectByExample(example);

			// 后面的不会被分页，除非再次调用PageHelper.startPage
			List<UnderTest> list2 = underTestMapper.selectByExample(example);
			// list1
			assertEquals(1, list.get(0).getId().intValue());
			assertEquals(10, list.size());
			// 分页时，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>，
			// 或者使用PageInfo类（下面的例子有介绍）
			assertEquals(numAll, ((Page) list).getTotal());
			// list2
			assertEquals(1, list2.get(0).getId().intValue());
			assertEquals(numAll, list2.size());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testPage3() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		UnderTestMapper underTestMapper = sqlSession.getMapper(UnderTestMapper.class);
		try {
			// 获取第1页，10条内容，默认查询总数count
			PageHelper.startPage(1, 10);

			// 紧跟着的第一个select方法会被分页
			UnderTestExample example = new UnderTestExample();
			// 紧跟着的第一个select方法会被分页
			List<UnderTest> list = underTestMapper.selectByExample(example);

			// 用PageInfo对结果进行包装
			PageInfo page = new PageInfo(list);

			// 测试PageInfo全部属性
			// PageInfo包含了非常全面的分页属性
			assertEquals(1, page.getPageNum());
			assertEquals(10, page.getPageSize());
			assertEquals(1, page.getStartRow());
			assertEquals(10, page.getEndRow());
			assertEquals(numAll, page.getTotal());
			assertEquals(101, page.getPages());
			assertEquals(1, page.getFirstPage());
			//assertEquals(11, page.getLastPage());
			assertEquals(true, page.isIsFirstPage());
			assertEquals(false, page.isIsLastPage());
			assertEquals(false, page.isHasPreviousPage());
			assertEquals(true, page.isHasNextPage());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testPage4() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		UnderTestMapper underTestMapper = sqlSession.getMapper(UnderTestMapper.class);
		try {
			// 获取第1页，10条内容，默认查询总数count
			PageHelper.startPage(1, 10, "id desc");

			// 紧跟着的第一个select方法会被分页
			UnderTestExample example = new UnderTestExample();
			// 紧跟着的第一个select方法会被分页
			List<UnderTest> list = underTestMapper.selectByExample(example);

			// 用PageInfo对结果进行包装
			PageInfo page = new PageInfo(list);

			// 测试PageInfo全部属性
			// PageInfo包含了非常全面的分页属性
			assertEquals(1, page.getPageNum());
			assertEquals(10, page.getPageSize());
			assertEquals(1, page.getStartRow());
			assertEquals(10, page.getEndRow());
			assertEquals(numAll, page.getTotal());
			assertEquals(101, page.getPages());
			assertEquals(1, page.getFirstPage());
			//assertEquals(11, page.getLastPage());
			assertEquals(true, page.isIsFirstPage());
			assertEquals(false, page.isIsLastPage());
			assertEquals(false, page.isHasPreviousPage());
			assertEquals(true, page.isHasNextPage());
			assertEquals(numAll, list.get(0).getId().intValue());
		} finally {
			sqlSession.close();
		}
	}

}
