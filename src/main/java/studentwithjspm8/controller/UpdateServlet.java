package studentwithjspm8.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentwithjspm8.dao.StudentDao;
import studentwithjspm8.dto.Student;
@WebServlet("/update")
public class UpdateServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	StudentDao dao=new StudentDao();
	Student student=dao.findStudentById(id);
//	create a httpsession
	HttpSession httpSession=req.getSession();
	String name=(String)httpSession.getAttribute("studentnamewhologgein");
	if(name!=null) {
//		he is coming from the loginservlet means he is a valid student
		req.setAttribute("student", student);
		RequestDispatcher dispatcher=req.getRequestDispatcher("edit.jsp");
		dispatcher.forward(req, resp);
	}else {
//		he is not coming from loginservlet he just copied update url
//		he is a scammer
		req.setAttribute("message", "Hey Scammer please login first idiot fellow");
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.include(req, resp);
	}
}
}
