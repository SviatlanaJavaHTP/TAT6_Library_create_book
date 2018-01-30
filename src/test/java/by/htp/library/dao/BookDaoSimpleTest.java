package by.htp.library.dao;

import org.junit.Before;
import org.junit.Test;

import by.htp.library.dao.database.BookDaoMySqlImpl;
import by.htp.library.dao.simple.SimpleBookDaoImpl;

import org.junit.Assert;

public class BookDaoSimpleTest {

	
	private BookDao dao = null;

	@Before
	public void initData() {
		dao = new BookDaoMySqlImpl();
		//dao = new SimpleBookDaoImpl();
	}

	@Test
	public void testReadAllListNotNull() {
		Assert.assertNotNull(dao);
		Assert.assertNotNull(dao.readAll());
	}

}
