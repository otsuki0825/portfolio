<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Training" %>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
      crossorigin="anonymous">
<meta charset="UTF-8">
<title>レッツ筋トレ</title>
</head>
<body>
<div class="position-relative">
 <div class="p-2 mb-2 bg-primary text-white">筋トレ管理
  <div class="btn-group position-absolute top-0 end-0">
    <button type="button" role="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" data-bs-auto-close="true" aria-expanded="false">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-justify" viewBox="0 0 16 16">
      <path fill-rule="evenodd" d="M2 12.5a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5z"/>
      </svg>
   </button>
   <ul class="dropdown-menu">
    <li><hr class="dropdown-divider"></li>
    <li><a class="dropdown-item" href="/trainingManager/LogoutServlet">ログアウト</a></li>
   </ul>
  </div>
 </div>
</div>
<%
    int sets = 1;
    int eventCount = 0;
    String eventCountS = (String)session.getAttribute("eventCount");
    String setsS = (String)session.getAttribute("sets");
    String groupName = (String)session.getAttribute("groupName");
    String ng = (String)request.getAttribute("ng");
    List<Training> eventList = (List<Training>)session.getAttribute("eventList");
    if((eventList.isEmpty())){
    	request.setAttribute("ng", "種目を登録してください");

    	RequestDispatcher rd = request.getRequestDispatcher("/jsp/eventInfo.jsp");
		rd.forward(request, response);
    }else{
%>
<div class="mx-auto p-2" style="width: 300px;">
	 <p class="fw-bold fs-2"><%=groupName %></p>
<%
     if(ng != null){
%>
		<div class="alert alert-danger" role="alert">
    	 <%=ng%>
		</div>
<%
     }

     if(setsS == null || setsS.equals("0")){
    	setsS = String.valueOf(sets);
    	if(eventCountS != null){
    		eventCount = Integer.parseInt(eventCountS);
    	}else{
    		eventCountS = "0";
    	}
    	session.setAttribute("sets", setsS);
    	session.setAttribute("eventCount", eventCountS);
       	Training tr = eventList.get(eventCount);
       	session.setAttribute("eventName", tr.getEventName());
%>
        <p class="fs-4">・<%=tr.getEventName() %></p><br>
	     		<form action="/trainingManager/TrainingServlet" method="post">
		        <div class="row g-2">
				  <div class="col-sm-3">
				    <input type="text" class="form-control" name="kg" aria-label="City" id="kg">
				  </div>
				  <div class="col-sm">
				    <label for="kg" class="col-form-label">kg</label>
				  </div>
				  <div class="col-sm-3">
				    <input type="text" class="form-control" name="reps" aria-label="State" id="reps">
				  </div>
				  <div class="col-sm">
				    <label for="reps" class="col-form-label">reps</label>
				  </div>
				</div>
				<br>
				<div class="row g-2">
				    <div class="col-sm">
				     <input type="submit" class="btn btn-outline-primary" value="登録" >
				    </div>
				</form>
				    <div class="col-sm">
				        <form action="/trainingManager/EventCountServlet" method="post">
				        <input type="submit" class="btn btn-outline-primary" value="次の種目" >
				         </form>
				     </div>
				</div>
<%
    }else{
    	   sets = Integer.parseInt(setsS);
    	   session.setAttribute("sets", setsS);
    	   if(sets > 1){
       		     eventCount = Integer.parseInt(eventCountS);
       	   }else{
       		eventCountS = "0";
       	   }
       	   session.setAttribute("eventCount", eventCountS);
	        	Training tr = eventList.get(eventCount);
	        	session.setAttribute("eventName", tr.getEventName());
%>
	     		<p class="fs-4">・<%=tr.getEventName() %></p><br>
	     		<form action="/trainingManager/TrainingServlet" method="post">
		        <div class="row g-2">
				  <div class="col-sm-3">
				    <input type="text" class="form-control" name="kg" aria-label="City" id="kg">
				  </div>
				  <div class="col-sm">
				    <label for="kg" class="col-form-label">kg</label>
				  </div>
				  <div class="col-sm-3">
				    <input type="text" class="form-control" name="reps" aria-label="State" id="reps">
				  </div>
				  <div class="col-sm">
				    <label for="reps" class="col-form-label">reps</label>
				  </div>
				  <div class="col-sm">
				     <h3><span class="badge bg-danger"><%=sets %>set</span></h3>
				  </div>
				</div>
				<br>
                <div class="row g-2">
				    <div class="col-sm">
				     <input type="submit" class="btn btn-outline-primary" value="登録" >
				    </div>
				 </form>
				    <div class="col-sm">
				        <form action="/trainingManager/EventCountServlet" method="post">
				        <input type="submit" class="btn btn-outline-primary" value="次の種目" >
				         </form>
				     </div>
				</div>
<%
	     	}
    }
%>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous">
</script>
</body>
</html>