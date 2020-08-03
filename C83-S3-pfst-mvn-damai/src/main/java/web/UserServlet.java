package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Dao.UserDao;

@WebServlet("/DUsers.do")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
      private UserDao userdao=new UserDao(); 
      
      //用户登录
      
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
	   
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String captcha = request.getParameter("captcha");
		  
		System.out.println(username+password);
		
		String scode=(String) request.getSession().getAttribute("vcode");
		Map<String, Object> user = userdao.selectByLogin(username, password);
	//	System.out.println("id====="+user.get("id"));  
//		String uid=String.valueOf(user.get("id"));
//		request.getSession().setAttribute("loginedUserId", uid);

		if (user != null) {
			if(captcha.equals(scode)) {
				request.getSession().setAttribute("loginedUser", user);
				System.out.println("session"+request.getSession().getAttribute("loginedUser"));
				response.getWriter().print("登录成功"); 
			}else {
				if(captcha==null||captcha.isEmpty()==true) {
					response.getWriter().print("请填写验证码 ");

				}else {
					response.getWriter().print("验证码错误");
				}
				
			
			}
		} else {
			response.getWriter().print("用户名或密码错误");
		}
	}
	 
   //用户注册
	protected void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
	   
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword =request.getParameter("repassword");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String captcha = request.getParameter("captcha");
		System.out.println("password:"+password );
		System.out.println("repassword:"+repassword);
	   String scode=(String) request.getSession().getAttribute("vcode");
	  //判断用户名是否存在
	   List<?> list=userdao.getUsername(username);
		 if(list.size()==0) {
				 
		
	  if (username!= null&&username.isEmpty()==false&&password!= null&&password.isEmpty()==false&&
			  email!= null&&email.isEmpty()==false&&phone!= null&&phone.isEmpty()==false &&
			  name!= null&&name.isEmpty()==false&&sex!= null&&sex.isEmpty()==false 
			  ) {
		     if(password.equals(repassword)) {
		    	  
		      
			if(captcha.equals(scode)) {
				userdao.insert(username, name, password, email, phone, sex);
				 response.getWriter().print("注册成功"); 
			}else {
				if(captcha==null||captcha.isEmpty()==true) {
					response.getWriter().print("请填写验证码 ");

				}else {
					response.getWriter().print("验证码错误");
				}
				
			
			}
		     }else {
		    	 response.getWriter().print("密码不一致"); 
		     }
		} else {
			response.getWriter().print("信息不能为空");
		}
		 }else {
			 print(response,"用户名已存在");
		 }
	}
	 
	
    //检测用户名是否已存在
	protected void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		List<?> list=userdao.getUsername(username);
		 if(list.size()!=0) {
			 
				print(response,"用户名已存在");
		 }
//		List<?> name=userdao.getUsername();
//	 
//		for(Object n:name) {
//			Map<String,Object> uname=(Map<String, Object>) n;
//			String u=(String) uname.get("ename");
//			if(u.equals(username)) {
//				print(response,"用户名已存在");
//			}
			 
	//	}
		
	}
	//获取当前登录用户信息
		protected void  query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession(); 
		   Map<String,Object> user=(Map<String, Object>) session.getAttribute("loginedUser");
		  String id=String.valueOf(user.get("id")) ;
			List<?> list=userdao.userinfo(id);
            print(response,list);
            System.out.println(list);
  
		}
		//修改用户信息
		protected void  modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession(); 
		   Map<String,Object> user=(Map<String, Object>) session.getAttribute("loginedUser");
		  String id=String.valueOf(user.get("id")) ;
		 
		  String ename=request.getParameter("ename");
		  String cname=request.getParameter("cname");
		  String password=request.getParameter("password");
		  String email=request.getParameter("email");
		  String phone=request.getParameter("phone");
		  String sex=request.getParameter("sex");
		  int i=userdao.change(ename, cname, password, email, phone, sex, id);
		 System.out.println("i"+i);
		  if(i!=0) {
			   print(response,"修改成功");
		   }
		}
		 //找回密码中检测用户名是否已存在,并发送邮件
		protected void findcheckUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username=request.getParameter("username");
			String captcha = request.getParameter("captcha");
			List<?> list=userdao.getUsername(username);
 			String scode=(String) request.getSession().getAttribute("vcode");
			 if(list.size()!=0) {
				 if(captcha.equals(scode)) {
 					//接收页面传过来的QQ邮箱号
				       String email  = request.getParameter("email");
				       //传到数据库，判断是否存在，如果存在，返回用户账号信息
				        
				          //to接收的是用户注册时的邮箱号，也就是就是收件人的，将接收到后台发送的密码
				          String to =request.getParameter("email");
				  	    
					      // 发件人电子邮箱，你可以改成自己的邮箱号
					      String from = "1363121722@qq.com";
					 
					      // 指定发送邮件的主机为 smtp.qq.com
					      String chost = "smtp.qq.com";  //QQ 邮件服务器
					 
					      // 创建用于连接邮件服务器的参数配置
				            Properties props = new Properties();
				            // 设置使用SMTP协议
				            props.setProperty("mail.transport.protocol", "smtp");
				            // 设置发件人的SMTP服务器地址
				            props.setProperty("mail.smtp.host", chost);
				            // 设置需要验证
				            props.setProperty("mail.smtp.auth", "true");
				            
				            //Linux端口25没有开放，放弃25端口，使用ssl加密并改用465端口--此举可以兼顾window和Linux
				            //使用ssl加密
				            props.setProperty("mail.smtp.ssl.enable", "true");
				            //设置端口
				            props.setProperty("mail.smtp.port", "465");
				            props.setProperty("mail.smtp.socketFactory.port", "465");
					      // 获取默认session对象
					      Session session = Session.getDefaultInstance(props,new Authenticator(){
					        public PasswordAuthentication getPasswordAuthentication()
					        {
					         return new PasswordAuthentication("1363121722@qq.com", "ghqqwswculzsiajh"); 
					          //发件人邮件用户名、授权码（授权码要与QQ邮箱相对应，可以从邮箱设置里面获得，详细步骤在博客开头）
					        }
					       });	 
					      try{
					         // 创建默认的 MimeMessage 对象
					         MimeMessage message = new MimeMessage(session);	 
					         // Set From: 头部头字段
					         message.setFrom(new InternetAddress(from));	 
					         // Set To: 头部头字段
					         message.addRecipient(Message.RecipientType.TO,
					                                  new InternetAddress(to));
					 
					         // Set Subject: 头部头字段
					         message.setSubject("找回密码");
					 
					         // 设置消息体
					         message.setContent("点击重置密码:<br/><a href='http://39.100.96.159:8080/damai/resetpassword.html?#"+username+"'>点击重新设置密码</a>","text/html;charset=utf-8"); 
					         message.saveChanges();
					         // 发送消息
					         Transport.send(message);
					         //System.out.println("Sent message successfully....from runoob.com");
					         //传到页面
					     	 request.setAttribute("MSG3", "发送成功，请注意查收！");
					         //request.getRequestDispatcher("findPassword.html").forward(request, response);
					          print(response,"发送成功，请注意查收！");
					      }catch (MessagingException mex) {
					         mex.printStackTrace();
					      }	
			     }else {
					 print(response,"验证码错误");
				 } 
 			}else {
				 print(response,"该用户不存在");
			 }
				 
		//	}
			
		}
		//重置密码
		protected void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String repassword=request.getParameter("repassword");
			if(password.equals(repassword)) {
				int i=userdao.changepassword(username, password);
				if(i>0) {
					print(response,"密码重置成功");
				}
			}else {
				print(response,"两次密码不一致");
			}

			
			
		}
		 

	//验证码生成
	protected void Img(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  //获取session必须在输出图片之前
				HttpSession session=request.getSession();
				//向页面输入一张验证码的图片
				String vcode=VerifyCodeUtils.outputImage(response);
				
				//将验证码设置到会话中
				session.setAttribute("vcode", vcode);
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
