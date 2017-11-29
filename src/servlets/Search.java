package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search/*")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String FLIGHTS_HTML_PATH = "/html/flights.html";
	private final String HOTELS_HTML_PATH = "/html/hotels.html";

	public Search() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String subPath = request.getPathInfo();
		if (subPath.equals("/Hotels")) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.append("Reached search hotels servlet");
			out.close();
			response.setStatus(200);
			return;
		}
		response.setStatus(404);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
