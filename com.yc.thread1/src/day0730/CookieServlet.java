package day0730;

import java.io.IOException;

public class CookieServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 向浏览器发送Cookie值name=--
		 */
 
		Cookie cookie=new Cookie("name","wusong");
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);
		cookie = new Cookie("sex","0");
 		cookie.setMaxAge(60 * 60 * 24);
 		response.addCookie(cookie);
		response.getWriter().append("cookie");
		/*
		 * 请为Cookie对象设置过期时间maxage
		 */
	}

}
