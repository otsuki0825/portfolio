package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.TrainingDao;
import entity.Training;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		TrainingDao dao = new TrainingDao();
		Training tr = dao.searchUser(id, password);
        try {
			if(tr.getId().equals(id) && tr.getPassword().equals(password)){
                List<Training> groupList = dao.searchGroup(id);

				HttpSession session = request.getSession();
				session.setAttribute("groupList", groupList);
				session.setAttribute("id", id);

				RequestDispatcher rd = request.getRequestDispatcher("/jsp/home.jsp");
				rd.forward(request, response);
			}
        }catch(NullPointerException e) {
        	request.setAttribute("ng", "IDまたはパスワードが間違っています");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
			rd.forward(request, response);
        }
	}
}
