package mycodelearn.undergrowth.mongodb.spring.mongo;

import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public interface  IDbDao {

	/**
	 * 插入单个
	 * @param obj
	 * @return
	 */
	public abstract DBObject insert(DBObject obj);
	
	/**
	 * 插入json格式字符串
	 * @param jsonStr
	 * @return
	 */
	public abstract WriteResult insert(String jsonStr);
	/**
	 * 批量插入
	 * @param list
	 */
	public abstract void insertBatch(List<DBObject> list);
	/**
	 * 插入json文件
	 * @param list
	 */
	public abstract void insertFile(String fileName);

	/**
	 * 删除单个
	 * @param obj
	 */
	public abstract void delete(DBObject obj);
	
	/**
	 * 批量删除
	 * @param list
	 */
	public abstract void deleteBatch(List<DBObject> list);

	
	/**
	 * 修改
	 * @param searchFields  要修改的查询条件
	 * @param updateFields  修改的值
	 */
	public abstract void update(DBObject searchFields,DBObject updateFields);

	/**
	 * 按条件查询
	 * @return
	 */
	public abstract List<BasicDBObject> find();
	
	/**
	 * 按条件查询
	 * @param query 查询条件
	 * @return
	 */
	public abstract List<BasicDBObject> find(DBObject query);
	 
	/**
	 * 排序查询
	 * @param query
	 * @param sort   new BasicDBObject("age", true)   则对age字段降序  注意1是升序，－1是降序 
	 * @param _class  要返回的实体bean类型
	 * @return
	 */
	public abstract List<BasicDBObject> find(DBObject query,DBObject sort);
	
	/**
	 * 分页查询
	 * @param query 查询条件
	 * @param start 起始
	 * @param limit 每页多少数据
	 * @return
	 */
	public abstract List<BasicDBObject> find(DBObject query,int start,int limit);
	
	/**
	 * 分页查询
	 * @param query 查询条件
	 * @param sort  排序
	 * @param start 起始
	 * @param limit 每页多少数据
	 * @return
	 */
	public abstract List<BasicDBObject> find(DBObject query,DBObject sort,int start,int limit);
	
	/**
	 * 按条件查询
	 * @param _class  要返回的实体bean类型
	 * @return
	 */
	public abstract List<Object> find(Class _class);
	
	/**
	 * 按条件查询
	 * @param query 查询条件
	 * @param _class  要返回的实体bean类型
	 * @return
	 */
	public abstract List<Object> find(DBObject query,Class _class);
	 
	/**
	 * 排序查询
	 * @param query 查询条件
	 * @param sort   new BasicDBObject("age", true)   则对age字段降序  注意1是升序，－1是降序 
	 * @param _class  要返回的实体bean类型
	 * @return
	 */
	public abstract List<Object> find(DBObject query,DBObject sort,Class _class);
	
	/**
	 * 分页查询
	 * @param query 查询条件
	 * @param start 起始
	 * @param limit 每页多少数据
	 * @param _class  要返回的实体bean类型
	 * @return
	 */
	public abstract List<Object> find(DBObject query,int start,int limit,Class _class);
	
	/**
	 * 分页查询
	 * @param query 查询条件
	 * @param sort  排序
	 * @param start 起始
	 * @param limit 每页多少数据
	 * @param _class  要返回的实体bean类型
	 * @return
	 */
	public abstract List<Object> find(DBObject query,DBObject sort,int start,int limit,Class _class);
	/**
	 * 按条件查询，返回查询个数
	 * @param query 查询条件
	 * @return
	 */
	public abstract int findCount(DBObject query);
	 
	/**
	 * 排序查询，返回查询个数
	 * @param query
	 * @param sort   new BasicDBObject("age", true)   则对age字段降序  注意1是升序，－1是降序 
	 * @param _class  要返回的实体bean类型
	 * @return
	 */
	public abstract int findCount(DBObject query,DBObject sort);
	/**
	 * 获取总数
	 * @param obj
	 * @return
	 */
	public abstract long getCount(DBObject obj);
	
	/**
	 * 获取总数
	 * @param obj
	 * @return
	 */
	public abstract long getCount();
	

	/**
	 * 利用java驱动自带函数分组查询
	 * @param key 用来分组文档的字段
	 * @param cond 执行过滤的条件
	 * @param initial reduce中使用变量的初始化
	 * @param reduce reduce（参数：当前文档和累加器文档.）
	 * @param fn
	 * @return
	 */
	public abstract DBObject group(DBObject key,DBObject cond,DBObject initial,String reduce,String fn );
	
	
	/**
	 * 使用聚合框架（参考http://docs.mongodb.org/manual/reference/sql-aggregation-comparison/）
	 * @param firstDb
	 * @param optionDb
	 * @return
	 */
	public abstract AggregationOutput  aggregation(DBObject firstDb,DBObject ...optionDb);
	
	public abstract DBCollection getCollection();
	
	/**
	 * 创建索引
	 * @param obj  new BasicDBObject("name", 1)); // 1代表升序 -1代表降序
	 */

}