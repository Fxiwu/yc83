package day0730;

import java.io.IOException;

public class ToIndexServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 实现响应重定向
		 */
		response.sendRedirect("/photo/index.html");

	}

}
