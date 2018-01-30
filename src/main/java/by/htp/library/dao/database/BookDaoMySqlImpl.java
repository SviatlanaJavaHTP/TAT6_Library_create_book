package by.htp.library.dao.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import by.htp.library.bean.Author;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDao;

public class BookDaoMySqlImpl implements BookDao {

	private static final String SQL_SELECT_BOOKS = "select * from book";
	private static final String SQL_INSERT_BOOK = 
			"insert into book (title, author, year) values (?, ?, ?)";
	{
		try {
			Class.forName(getConnectInitValue()[3]);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void create(Book entity) {
		if (entity != null) {

			String url = getConnectInitValue()[0];
			String login = getConnectInitValue()[1];
			String pass = getConnectInitValue()[2];
			
			try(Connection cn = DriverManager.getConnection(url, login, pass)){
			
				PreparedStatement ps = cn.prepareStatement(SQL_INSERT_BOOK);
				ps.setString(1, entity.getTitle()); // title
				ps.setInt(2, entity.getAuthor().getId()); // author
				ps.setDate(3, new Date(0)); // date
				ps.executeUpdate();
				
			} catch ( SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Book read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Book entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> readAll() {

		List<Book> list = new ArrayList<>();

		Connection cn = null;
		try {

			String url = getConnectInitValue()[0];
			String login = getConnectInitValue()[1];
			String pass = getConnectInitValue()[2];
			
			cn = DriverManager.getConnection(url,login, pass);
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_BOOKS);

			Book book = new Book();
			while (rs.next()) {

				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				Author author = new Author();
				author.setId(rs.getInt("author"));
				book.setAuthor(author);
				// rs.getDate("year");
			}
			list.add(book);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	private String[] getConnectInitValue(){
		ResourceBundle rb = ResourceBundle.getBundle("db_config");
		String dbURL = rb.getString("db.url");
		String user = rb.getString("db.login");
		String pass = rb.getString("db.pass");
		String driver = rb.getString("db.driver.name");
		
		return new String[]{dbURL, user, pass, driver};
	}
}
