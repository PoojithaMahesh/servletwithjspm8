package studentwithjspm8.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithjspm8.dao.StudentDao;
import studentwithjspm8.dto.Student;

public class SignUpServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String address=req.getParameter("address");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	long phone=Long.parseLong(req.getParameter("phone"));
//	ServletContext context=getServletContext();
//	String fees1=context.getInitParameter("fees");
	double fees=Double.parseDouble(getServletContext().getInitParameter("fees"));

	Student student=new Student();
	student.setAddress(address);
	student.setEmail(email);
	student.setFees(fees);
	student.setName(name);
	student.setPassword(password);
	student.setPhone(phone);
	
	StudentDao dao=new StudentDao();
	List<Student> students=dao.getAllStudents();
	boolean value=true;
	for(Student st:students) {
		if(email.equals(st.getEmail())) {
			value=false;
			break;
		}
	}
	
//	value=true when that email is not present in the database
//	value=false when that email is mapped to other Student
	if(value) {
		dao.saveStudent(student);
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, resp);
		
	}else {
		req.setAttribute("message", "Sorry Email already mapped to other student please give another email");
		RequestDispatcher dispatcher=req.getRequestDispatcher("signup.jsp");
		dispatcher.include(req, resp);
	}
	
	
	
	
	
	
	
	
	
	
}
}
