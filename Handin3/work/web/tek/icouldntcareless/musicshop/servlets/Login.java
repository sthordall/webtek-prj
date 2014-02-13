package web.tek.icouldntcareless.musicshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.org.mozilla.javascript.internal.ast.ThrowStatement;

public class Login extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print("<html><head><title>WebTekProjekt</title></head><body>"
				+ "<h1>MUSICSHOP</h1>" + "1234!"
				+ "</body></html>");

	}
}
