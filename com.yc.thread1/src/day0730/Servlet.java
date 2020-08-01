package day0730;

import java.io.IOException;

/*
 * 继承关系
 */
public interface Servlet {
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException;

}
