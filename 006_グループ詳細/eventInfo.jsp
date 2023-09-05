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
<title>グループ詳細</title>
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
    <li><a class="dropdown-item" href="/trainingManager/jsp/eventUpdateDelete.jsp">修正・削除</a></li>
    <li><hr class="dropdown-divider"></li>
    <li><a class="dropdown-item" href="/trainingManager/LogoutServlet">ログアウト</a></li>
   </ul>
  </div>
 </div>
</div>
<form action="/trainingManager/EventSearchServlet" method="post" >
<div class="mx-auto p-2" style="width: 300px;">
    <%
    String groupName = (String)session.getAttribute("groupName");
    List<Training> eventList = (List<Training>)session.getAttribute("eventList");
    String ng = (String)request.getAttribute("ng");
    if(ng != null){
    %>
      <div class="alert alert-danger" role="alert">
      <%=ng %>
      </div>
    <%
    }
    %>
     <p class="fw-bold fs-2"><%=groupName %></p>
    <%
    if(eventList != null){
        for(int i = 0; i < eventList.size(); i++){
        	Training tr = eventList.get(i);
        	if(tr.getEventName() == ""){
        		break;
        	}
    %>
     		<p class="fs-4">・<%=tr.getEventName() %></p><br>
    <%
        }
     }
    %>
    </div>
 </form>
    <br>
<div class="mx-auto p-0" style="width: 250px;">
    <a href="/trainingManager/jsp/training.jsp"class="p-2 g-col-6 ml-6 link-danger">
       <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-fire" viewBox="0 0 16 16">
       <path d="M8 16c3.314 0 6-2 6-5.5 0-1.5-.5-4-2.5-6 .25 1.5-1.25 2-1.25 2C11 4 9 .5 6 0c.357 2 .5 4-2 6-1.25 1-2 2.729-2 4.5C2 14 4.686 16 8 16Zm0-1c-1.657 0-3-1-3-2.75 0-.75.25-2 1.25-3C6.125 10 7 10.5 7 10.5c-.375-1.25.5-3.25 2-3.5-.179 1-.25 2 1 3 .625.5 1 1.364 1 2.25C11 14 9.657 15 8 15Z"/>
       </svg>
        筋トレ開始
    </a>
    <a href="/trainingManager/jsp/home.jsp">
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