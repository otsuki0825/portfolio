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

@WebServlet("/EventSearchServlet")
public class EventSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			 List<Training> groupList = (List<Training>)session.getAttribute("groupList");
				String groupName = request.getParameter("groupName");
				String id = (String)session.getAttribute("id");

		        TrainingDao dao = new TrainingDao();
		        Training tr = new Training();
		        int groupId = dao.searchGroupId(id, groupName);
		        List<Training> eventList = dao.searchEvent(id, groupId);

		        for(int i = 0; i < eventList.size(); i++) {
				    tr = new Training();
				    tr = eventList.get(i);
		         	String eventName = tr.getEventName();
		         	if(eventName.equals("")) {
		         		dao.deleteeGroupByeventName(id, eventName);
		         	}
		         }
		        eventList = dao.searchEvent(id, groupId);
				session.setAttribute("eventList", eventList);
				session.setAttribute("groupName", groupName);
				String groupIdS = String.valueOf(groupId);
				session.setAttribute("groupId", groupIdS);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/eventInfo.jsp");
			rd.forward(request, response);
		}


	}