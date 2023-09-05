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

@WebServlet("/EventUpdateDeleteServlet")
public class EventUpdateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		List<Training> eventList = (List<Training>)session.getAttribute("eventList");
		String groupName = (String)session.getAttribute("groupName");
		String groupIdS = (String)session.getAttribute("groupId");
		int groupId = Integer.parseInt(groupIdS);
		String addS = (String)session.getAttribute("add");
		String id = (String)session.getAttribute("id");
		int add = 5;
		if(addS != null ) {
			add = Integer.parseInt(addS);
		}
        String[] newEventList = request.getParameterValues("eventName1");
        TrainingDao dao = new TrainingDao();
		Training tr = null;
		//種目登録がされていた場合
		if(!(eventList.isEmpty())){
			//テキストボックスの値を更新リストに格納

			//新しい種目の数、更新前の種目の数、その差分を格納
			int num1 =newEventList.length;
			int num2 = eventList.size();
			int num3 = 0;
			if(num1 > num2) {
				num3 = num1 - num2;
			}
			//差分の分だけ、更新前リストに要素追加して、要素数そろえる
			for(int i = 0; i < num3; i++) {
				tr = new Training();
				tr.setEventName("");
				eventList.add(tr);
			}
			//更新リストと更新前リストを比較して差があれば、更新
			for(int i = 0; i < num1; i++) {
				tr = new Training();
				tr = eventList.get(i);
				int newEventId = i + 1;
				String newEventName =newEventList[i];
				int eventId = tr.getEventId();
				String eventName = tr.getEventName();
				//更新リストに値がある場合
				if(newEventName != "") {
					//更新リストに値があるが、更新前リストに値がない場合
					if(eventName == "") {
						dao.eventNameEventTableRegist(id, groupId, newEventId, newEventName);
					//更新リストと更新前リストがちがう場合
					}else if (!(newEventName.equals(eventName))){
						dao.eventNameEventTableUpdate(id, eventId, eventName, newEventId, newEventName);
				    }
				//更新前リストに値があるが、更新リストに値がない場合
				}else if(eventName != "") {
					dao.eventNameEventTableUpdate(id, eventId, eventName, newEventId, newEventName);
				}
			}
		//種目が登録されていない場合、
		}else{
			//更新リストの種目を登録していく
			for(int i = 0; i < add; i++) {
				String newEventName =newEventList[i];
				int newEventId = i + 1;
				if(!(newEventName.equals(""))) {
					dao.eventNameEventTableRegist(id, groupId, newEventId, newEventName);
				}
			}
		}
		session.setAttribute("eventList", eventList);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/home.jsp");
		rd.forward(request, response);
	}
}
