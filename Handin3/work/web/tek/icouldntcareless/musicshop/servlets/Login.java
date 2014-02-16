package web.tek.icouldntcareless.musicshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 7116008849202444786L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print("<html><head><title>WebTekProjekt</title></head><body>"
				+ "<h1>MUSICSHOP</h1>" + "1234!"
				+ "</body></html>");

	}
}
