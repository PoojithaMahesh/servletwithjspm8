package studentwithjspm8.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentwithjspm8.dao.StudentDao;
import studentwithjspm8.dto.Student;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	StudentDao dao=new StudentDao();
	List<Student> students=dao.getAllStudents();
	boolean value=false;
	String dbPassword=null;
	String nameofthestudentwhologgedin=null;
	for(Student student:students) {
		if(email.equals(student.getEmail())) {
			value=true;
			nameofthestudentwhologgedin=student.getName();
			dbPassword=student.getPassword();
			break;
		}
	}
//	create a Cookie
	
	Cookie cookie=new Cookie("studentwhologgedinandtriedtochangethedetails", nameofthestudentwhologgedin);
	resp.addCookie(cookie);
	
//	Create HTTPSEssion
	HttpSession httpSession=req.getSession();
	httpSession.setAttribute("studentnamewhologgein", nameofthestudentwhologgedin);
	
	if(value) {
//		value=true that enail is present in the database
		if(password.equals(dbPassword)) {
//			login success
			req.setAttribute("students",students);
			RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
			dispatcher.forward(req, resp);
		}else {
//			invalid password
			req.setAttribute("message", "Sorry Invalid Password");
			RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);
		}
	}else {
//		email is not present
		req.setAttribute("message", "Sorry Invalid Email");
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.include(req, resp);
	}
	
	
	
	
	
	
	
	
	
	
	
}
}
