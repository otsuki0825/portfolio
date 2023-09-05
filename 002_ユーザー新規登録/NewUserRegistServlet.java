package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.TrainingDao;

@WebServlet("/NewUserRegistServlet")
public class NewUserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		if(id ==""|| password == "") {
			request.setAttribute("NgNewUser", "IDまたはパスワードを入力してください");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/newUserRegist.jsp");
			rd.forward(request, response);
		}else {
	        TrainingDao dao = new TrainingDao();
	        dao.newUserRegist(id, password);

	        request.setAttribute("newUser", "新規登録しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
			rd.forward(request, response);
		}
	}
}
