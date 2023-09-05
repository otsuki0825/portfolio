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

@WebServlet("/EventSearchServlet2")
public class EventSearchServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			String groupName = request.getParameter("key");
			String id = (String)session.getAttribute("id");

	        TrainingDao dao = new TrainingDao();
			int groupId = dao.searchGroupId(id,groupName);
	        List<Training> eventrainingList2 = dao.searchEvent(id,groupId);
	        Training tr = null;

		        for(int i = 0; i < eventrainingList2.size(); i++) {
				    tr = new Training();
				    tr = eventrainingList2.get(i);
		         	String eventName = tr.getEventName();
		         	if(eventName.equals("")) {
		         		dao.deleteeGroupByeventName(id, eventName);
		         	}
		         }
	        List<Training> trainingList = dao.trainingListByIdAndGroupId(id,groupId);

	        eventrainingList2 = dao.searchEvent(id, groupId);
			session.setAttribute("eventrainingList2", eventrainingList2);
			session.setAttribute("trainingList", trainingList);
			session.setAttribute("groupName", groupName);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/report2.jsp");
			rd.forward(request, response);
		}
	}