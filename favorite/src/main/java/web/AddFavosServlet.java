package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

import bean.Favorite;
import dao.FavoriteMapper;
import dao.TagMapper;
import util.FavoriteBiz;
import util.MyBatisHelper;
 
@WebServlet("/addFavs.do")
public class AddFavosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		SqlSession session = MyBatisHelper.openSession();
		FavoriteBiz fb=new FavoriteBiz();        
        Favorite f=new Favorite();
       String flabel=request.getParameter("flabel");
       String furl= request.getParameter("furl");
       String ftags=request.getParameter("ftags");
       String fdesc=request.getParameter("fdesc");
       if(flabel!=null) {
    	   f.setFlabel(flabel);
		f.setFurl(furl);
		f.setFdesc(ftags);
		f.setFtags(fdesc);
		fb.addFavorite(f);
		response.getWriter().append("收藏成功");
       }
		  
	
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
