package mycodelearn.undergrowth.mongodb.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mycodelearn.undergrowth.mongodb.MongoDbLearn;

public class MongoDbTest {

	MongoDbLearn mon=null;
	String collectionName=null;
	
	@Before
	public void setUp() throws Exception {
		mon=new MongoDbLearn();
		mon.connect("root", "root", "192.168.126.129", 27017,"test");
		collectionName="test";
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
		mon.findCollection(collectionName);
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
