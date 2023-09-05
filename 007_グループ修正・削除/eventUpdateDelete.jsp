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
<title>修正・削除</title>
<script src="/trainingManager/JS/textbox.js"></script>
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

<div class="mx-auto p-2" style="width: 300px;">
 <%
    int add = 5;
    String groupName = (String)session.getAttribute("groupName");
    List<Training> eventList = (List<Training>)session.getAttribute("eventList");
    %>
     <p class="fw-bold fs-2"><%=groupName %></p>
    <%
    if(!eventList.isEmpty()){
        for(int i = 0; i < eventList.size(); i++){
        	 Training tr = eventList.get(i);
        	 if(tr != null){
    %>
			   <div class="mb-3">
	           <input type="text" class="form-control" name="eventName1" value="<%=tr.getEventName() %>" form="eventName1" id="testText1">
	           <input type="hidden" class="form-control" name="eventName2" value="<%=tr.getEventName() %>" form="eventName2" id="testText2">
	           </div>

    <%
        	 }else{
     %>
			   <div class="mb-3">
	            <input type="text" class="form-control" name="eventName1" form="eventName1" id="testText1">
	            <input type="hidden" class="form-control" name="eventName2" form="eventName2" id="testText2">
	           </div>
    <%
        	}
        }
     }else{
    	 String addS = String.valueOf(add);
    	 session.setAttribute("add", addS);
    	 for(int i = 0; i < add; i++){
    %>
	           <div class="mb-3">
	            <input type="text" class="form-control" name="eventName1" form="eventName1" id="testText1">
	            <input type="hidden" class="form-control" name="eventName2" form="eventName2" id="testText2">
	           </div>
    <%
    	 }
     }
    %>
    <br>
    <div class="row g-2">
     <div class="col-sm">
      <form action="/trainingManager/EventUpdateDeleteServlet" method="post"id="eventName1">
       <input type="submit" class="btn btn-outline-primary" value="変更">
      </form>
     </div>
     <div class="col-sm-3">
      <form action="/trainingManager/TextboxAddServlet" method="post"id="eventName2">
       <input type="submit" class="btn btn-outline-success" value="Box追加" >
      </form>
     </div>
    </div>
     <br>
     <div class="col-sm">
       <form action="/trainingManager/DeleteGroupServlet" method="post">
        <input type="submit" class="btn btn-outline-danger" value="グループ削除" >
      </form>
     </div>
     <br>
    <a href="/trainingManager/jsp/eventInfo.jsp">
      <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-reply" viewBox="0 0 16 16">
      <path d="M6.598 5.013a.144.144 0 0 1 .202.134V6.3a.5.5 0 0 0 .5.5c.667 0 2.013.005 3.3.822.984.624 1.99 1.76 2.595 3.876-1.02-.983-2.185-1.516-3.205-1.799a8.74 8.74 0 0 0-1.921-.306 7.404 7.404 0 0 0-.798.008h-.013l-.005.001h-.001L7.3 9.9l-.05-.498a.5.5 0 0 0-.45.498v1.153c0 .108-.11.176-.202.134L2.614 8.254a.503.503 0 0 0-.042-.028.147.147 0 0 1 0-.252.499.499 0 0 0 .042-.028l3.984-2.933zM7.8 10.386c.068 0 .143.003.223.006.434.02 1.034.086 1.7.271 1.326.368 2.896 1.202 3.94 3.08a.5.5 0 0 0 .933-.305c-.464-3.71-1.886-5.662-3.46-6.66-1.245-.79-2.527-.942-3.336-.971v-.66a1.144 1.144 0 0 0-1.767-.96l-3.994 2.94a1.147 1.147 0 0 0 0 1.946l3.994 2.94a1.144 1.144 0 0 0 1.767-.96v-.667z"/>
      </svg>
      戻る
    </a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous">
</script>

</body>
</html>