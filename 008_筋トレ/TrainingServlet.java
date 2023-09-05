package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet("/TrainingServlet")
public class TrainingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(date);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			request.setCharacterEncoding("UTF-8");
			String kgS = (String)request.getParameter("kg");
			String repsS = (String)request.getParameter("reps");

			HttpSession session = request.getSession();
			String groupName = (String)session.getAttribute("groupName");
			String eventName = (String)session.getAttribute("eventName");
			List<Training> eventList = (List<Training>)session.getAttribute("eventList");
			String eventCountS = (String)session.getAttribute("eventCount");
			String setsS = (String)session.getAttribute("sets");
			String trainingIdS = (String)session.getAttribute("triningId");
			if(trainingIdS == null) {
				trainingIdS = "0";
			}
			String id = (String)session.getAttribute("id");
			String groupIdS = (String)session.getAttribute("groupId");
			int groupId = Integer.parseInt(groupIdS);
            int eventId = 0;


			if(kgS == "" || repsS == "") {
				request.setAttribute("ng", "㎏またはrepsを入力してください");

				RequestDispatcher rd = request.getRequestDispatcher("/jsp/training.jsp");
				rd.forward(request, response);
			}else {
				int kg = Integer.parseInt(kgS);
				int reps = Integer.parseInt(repsS);
				int sets = Integer.parseInt(setsS);
				int trainingId = Integer.parseInt(trainingIdS);

				TrainingDao dao = new TrainingDao();
				Training tr = new Training();
				if(eventCountS != null) {
					int eventCount = Integer.parseInt(eventCountS);
					tr = eventList.get(eventCount);
					eventId = tr.getEventId();
				}else {
                    eventId = 1;
				}

	            int count = 1;
				int preSets = sets - 1;

				int preEventId = 0;
	            int preKg = 0;
	            int preReps = 0;
	            int preCount = 0;
	            String preDt = null;

				if(sets > 1) {
					tr = dao.trainingComparison(id,eventId, kg, reps, preSets);
	                if(tr != null) {
						preEventId = tr.getEventId();
			            preKg = tr.getKg();
			            preReps = tr.getReps();
			            preSets = tr.getSets();
			            preCount = tr.getCount();
			            preDt = tr.getDt();
					}else {
						preEventId = 20230904;
			            preKg = 0;
			            preReps = 0;
			            preCount = 0;
			            preDt = "hoge";
					}
				}
				if(sets <= 1) {
					trainingId++;
					dao.trainingRegist(id, groupId, eventId, kg, reps, sets, count, trainingId);
					sets++;
					setsS = String.valueOf(sets);
					session.setAttribute("sets", setsS);
				}else if (preEventId == eventId && preKg == kg && preReps == reps  && preDt.equals(today)){
					count = preCount + 1;
					trainingId++;
					dao.trainingUpDate(id,eventId, kg, sets,count, trainingId);
					sets++;
					setsS = String.valueOf(sets);
					session.setAttribute("sets", setsS);
				}else {
					count = 1;
					trainingId++;
					dao.trainingRegist(id, groupId, eventId, kg, reps, sets, count, trainingId);
					sets++;
					setsS = String.valueOf(sets);
					session.setAttribute("sets", setsS);
				}
				trainingIdS = String.valueOf(trainingId);
				session.setAttribute("triningId", trainingIdS);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/training.jsp");
				rd.forward(request, response);
			}
        }
}