package mycodelearn.undergrowth.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;

import io.netty.util.internal.StringUtil;

/**
 * mongodb----分布式/c++/nosql(not only sql)/开源/文档存储/ document is bson(binary json)
 * 
 * CRUD: javascript shell mongodb----json
 * db.person.insert({"name":"under","age":123,"desc":"learn mondos"})
 * db.person.insert({"name":"under2","age":345,"desc":"learn2 mondos"})
 * db.person.find() db.person.update({"age":123},{"desc":"update age"})
 * db.person.update({"desc":"update age"},{"name":"under","age":123,"desc":
 * "learn mondos"}) db.person.count() db.person.remove({"age":123})
 * db.person.remove() db.person.count()
 * 
 * =====================javascript shell============================== var
 * single={"name":"js","age":567,"address":{"city":"gz","provi":"gd"},"fav":[
 * "basketball","football"]} db.person.insert(single) single.name="otherjs"
 * single.address={"provi":"gd","city":"gz","x":"th"} single.fav=["dy","xs"]
 * db.person.insert(single) =====================javascript
 * shell============================== $gt $lt $ne $in $or----以key形式展现
 * db.person.find() db.person.find({"name":"under2"})
 * db.person.find({"name":"under2","age":345})
 * db.person.find({"name":"js","address.city":"gz"})
 * db.person.find({$or:[{"name":"js"},{"address.city":"gz1"}]})
 * db.person.find({"age":{$gt:10}})
 * ========================整体更新、局部更新、upsert=====================================
 * ====== var model=db.person.findOne({"name":"under2"}) model.age=900
 * db.person.update({"name":"under2"},model)
 * db.person.update({"name":"under2"},{$inc:{"age":100}})
 * db.person.update({"name":"under2"},{$set:{"age":100}})
 * db.person.update({"name":"under5"},{$set:{"age":100}},true) Description:
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年8月3日
 * @version 1.0.0
 */
public class MongoDbLearn {

	private Logger logger = LoggerFactory.getLogger(MongoDbLearn.class);

	MongoClient mongoClient = null;
	MongoDatabase mongoDatabase = null;

	public void connect(String myUserName, String myPassword, String serverIp, int serverPort, String databaseName) {
		try {
			// 连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
			// ServerAddress()两个参数分别为 服务器地址 和 端口
			ServerAddress serverAddress = new ServerAddress(serverIp, serverPort);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);

			// MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
			MongoCredential credential = MongoCredential.createScramSha1Credential(myUserName, databaseName,
					myPassword.toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);

			// 通过连接认证获取MongoDB连接
			mongoClient = new MongoClient(addrs, credentials);

			// 连接到数据库
			mongoDatabase = mongoClient.getDatabase(databaseName);
			logger.info("Connect to " + databaseName + " database successfully");
		} catch (Exception e) {
			logger.error("Connect to " + databaseName + " database fail," + "\t" + e.getClass().getName() + ": "
					+ e.getMessage());
		}
	}

	public void createCollection(String collectionName) {
		mongoDatabase.createCollection(collectionName);
		logger.info("create " + collectionName + " collection successfully");
	}

	public void getCollection(String collectionName) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		logger.info("get " + collectionName + " collection successfully," + collection.count());
	}

	public void insertManyDocument(String collectionName, int numInerst) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		logger.info("get " + collectionName + " collection successfully," + collection.count());
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < numInerst; i++) {
			Document document = new Document("title", "MongoDB" + i).append("description", "database" + i)
					.append("likes", 100 + i).append("by", "Fly" + i);
			documents.add(document);
		}
		collection.insertMany(documents);
		logger.info("insert to " + collectionName + " collection successfully");
	}

	public void findCollection(String collectionName, List<String> disColumn) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		// iteratorCursor(collection);
		iteratorDocument(collection, disColumn);
	}

	private void iteratorDocument(MongoCollection<Document> collection, List<String> disColumn) {
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			Document document = mongoCursor.next();
			String jsonDocument = document.toJson();
			logger.info(jsonDocument);
			logger.info(document.keySet().toString());
			logger.info(document.values().toString());
			for (String column : disColumn) {
				logger.info(column + ":" + getValuebyColumnName(document, column));
			}

		}
	}

	/**
	 * 按照列的路径查找元素
	 * eg:
	 * pic.size.h----递归查找值
	 * @param document
	 * @param column
	 * @return
	 */
	private Object getValuebyColumnName(Document document, String column) {
		String[] columns = column.split("\\.");
		if (columns == null || columns.length < 2) {// 单层,直接查找返回
			return document.get(column);
		}
		Object value = null;
		for (int i = 0; i < columns.length; i++) {
			value = document.get(columns[i]);
			if (value != null) {
				logger.info(columns[i] + ":" + value + "\t" + value.getClass().toString()
						+ "\tvalue instanceof java.util.ArrayList:"
						+ ((value instanceof java.util.ArrayList) ? "true" : "false") + "\t"
						+ ((value instanceof Document) ? "true" : "false"));
			}
			if (value == null) {// 为空返回
				break;
			}
			if (value instanceof java.util.ArrayList) { // 处理集合
				ArrayList<Document> aList = (ArrayList<Document>) value;
				String[] dest = new String[columns.length - i - 1];
				System.arraycopy(columns, i + 1, dest, 0, dest.length); // 复制剩下的元素
				for (Document document2 : aList) {
					// 递归查找元素
					Object valueMore = getValuebyColumnName(document2, transString(dest));
					if (valueMore != null)
						return valueMore;
				}
			}
			if (value instanceof Document) { // 处理单个文档
				document = (Document) value;
			}

		}
		return value;
	}

	private String transString(String[] dest) {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		for (String str : dest) {
			if (builder.length() > 0) {
				builder.append(".");
			}
			builder.append(str);
		}
		return builder.toString();
	}

	private void iteratorCursor(MongoCollection<Document> collection) {
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			Document document = mongoCursor.next();
			String jsonDocument = document.toJson();
			// DBObject dbObject=(DBObject) JSON.parse(jsonDocument);
			logger.info(jsonDocument);
			// logger.info("serialize----"+JSON.serialize(dbObject));
		}
	}

	public void updateMany(String collectionName) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));
		iteratorCursor(collection);
	}

	public void deleteOne(String collectionName) {
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		// 删除符合条件的第一个文档
		collection.deleteOne(Filters.eq("likes", 200));
		// 删除所有符合条件的文档
		collection.deleteMany(Filters.eq("likes", 200));
		iteratorCursor(collection);
	}

}
