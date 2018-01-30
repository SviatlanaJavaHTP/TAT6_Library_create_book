package by.htp.library.action.console;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.action.BaseAction;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.database.BookDaoMySqlImpl;
import by.htp.library.dao.simple.SimpleBookDaoImpl;

public class BookListConsoleImpl implements BaseAction {

	// private BookDao dao = new SimpleBookDaoImpl();
	private BookDao dao = new BookDaoMySqlImpl();

	@Override
	public void doSmth(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		List<Book> books = dao.readAll();
		
		PrintWriter out = response.getWriter();

		if (books != null) {
			for (Book book : books) {
				out.println(book);
			}
		}else{
			out.print("Emptry library");
		}
	}

}
