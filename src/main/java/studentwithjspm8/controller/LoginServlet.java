package studentwithjspm8.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	for(Student student:students) {
		if(email.equals(student.getEmail())) {
			value=true;
			dbPassword=student.getPassword();
			break;
		}
	}
	if(value) {
//		value=true that enail is present in the database
		if(password.equals(dbPassword)) {
//			login success
			PrintWriter printWriter=resp.getWriter();
			printWriter.print("LOGIN SUCCESS");
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
