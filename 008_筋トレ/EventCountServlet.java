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

import entity.Training;

@WebServlet("/EventCountServlet")
public class EventCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

 	protected void doPost(HttpServletRequest request, HttpServletResponse response)
 			throws ServletException, IOException {
 		request.setCharacterEncoding("UTF-8");

 		HttpSession session = request.getSession();
 		List<Training> eventList = (List<Training>)session.getAttribute("eventList");
 		String eventCountS = (String)session.getAttribute("eventCount");
 		String setsS = (String)session.getAttribute("sets");
 		int eventCount = Integer.parseInt(eventCountS);
 		int sets = Integer.parseInt(setsS);

 		eventCount += 1;
 		sets = 0;

 		if(eventCount < eventList.size()){
	 		eventCountS = String.valueOf(eventCount);
	 		setsS = String.valueOf(sets);

	 		session.setAttribute("eventCount", eventCountS);
	 		session.setAttribute("sets", setsS);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/training.jsp");
			rd.forward(request, response);
 		}else {
 			RequestDispatcher rd = request.getRequestDispatcher("/jsp/o2.jsp");
			rd.forward(request, response);
 		}
	}

}
