package mycodelearn.undergrowth.mongodb.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mycodelearn.undergrowth.mongodb.MongoDbLearn;

public class MongoDbTest {

	MongoDbLearn mon=null;
	String collectionName=null;
	List<String> disColumn;
	
	@Before
	public void setUp() throws Exception {
		mon=new MongoDbLearn();
		mon.connect("root", "root", "192.168.126.129", 27017,"test");
		collectionName="testBasicModel";
		disColumn=new ArrayList<>();
		disColumn.add("name");
		disColumn.add("address.all");
		disColumn.add("phone.fix");
		disColumn.add("areaCode");
		disColumn.add("_id.$oid");
		disColumn.add("knowMore.know2");
		disColumn.add("pic.size.h");
		disColumn.add("pic.title.t1");
		disColumn.add("pic.title.h");
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testCreateCollection() {
		mon.createCollection(collectionName);
	}

	@Test
	public void testGetCollection() {
		mon.getCollection(collectionName);
	}

	@Test
	public void testInsertManyDocument() {
		mon.insertManyDocument(collectionName,100);
	}

	@Test
	public void testFindCollection() {
		mon.findCollection(collectionName,disColumn);
	}

	@Test
	public void testUpdateMany() {
		mon.updateMany(collectionName);
	}

	@Test
	public void testDeleteOne() {
		mon.deleteOne(collectionName);
	}

}
