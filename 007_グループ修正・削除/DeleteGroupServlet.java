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

@WebServlet("/DeleteGroupServlet")
public class DeleteGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String groupName =(String)session.getAttribute("groupName");
        String id = (String)session.getAttribute("id");

		TrainingDao dao = new TrainingDao();
		dao.deletetGroup(id, groupName);
		dao.deleteeGroup(id ,groupName);

		List<Training> groupList = dao.searchGroup(id);

		session.setAttribute("groupList", groupList);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/home.jsp");
		rd.forward(request, response);

	}

}
