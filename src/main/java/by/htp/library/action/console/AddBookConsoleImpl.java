package by.htp.library.action.console;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.action.BaseAction;
import by.htp.library.bean.Author;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.database.BookDaoMySqlImpl;

public class AddBookConsoleImpl implements BaseAction{
	
	private BookDao dao = new BookDaoMySqlImpl();

	@Override
	public void doSmth(HttpServletRequest request, 
			HttpServletResponse response) {
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String year = request.getParameter("year");
		
		int authorId= Integer.parseInt(author);
		
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(new Author(authorId));
		
		dao.create(book);
		// setYear
		
	}

}
