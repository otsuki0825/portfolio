<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Training" %>
<!DOCTYPE html>

<html lang="jp" data-bs-theme="auto">

<head>
<meta charset="UTF-8">
<title>レポート</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
 integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
 crossorigin="anonymous">
<link href="/trainingManager/assets/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
<div class="position-relative">
 <div class="p-2 mb-2 bg-primary text-white">
  <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6 text-white" href="/trainingManager/jsp/home.jsp">
   筋トレ管理
  </a>
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

<div class="container text-center">
<div class="row">
 <div class="col-lg-2 px-lg-12">
  <ul class="nav flex-column">
<%
 List<Training> groupList = (List<Training>)session.getAttribute("groupList");
 if(groupList != null){
    for(int i = 0; i < groupList.size(); i++){
   	    Training tr = groupList.get(i);
%>
   <li class="nav-item">
    <input type="submit" class="btn btn-light d-flex align-items-center gap-2" form="gp" name="key" value="<%=tr.getGroupName()%>">
   </li>
<%
    }
 }
%>
  </ul>
 </div>

  <main class="col-md-9 ms-sm-auto col-lg-10 px-md-10">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">筋トレ管理　レポート</h1>
        <div class="btn-toolbar mb-2 mb-md-0 dropdown">
         <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle d-flex align-items-center gap-1" data-bs-toggle="dropdown">
            種目
          </button>
         <ul class="dropdown-menu">
<%
 List<Training> eventrainingList2 = (List<Training>)session.getAttribute("eventrainingList2");
 if(eventrainingList2 != null){
    for(int i = 0; i < eventrainingList2.size(); i++){
    	Training tr = eventrainingList2.get(i);
%>
            <li>
             <a class="dropdown-item" href="/trainingManager/trainingSearchServlet?key2=<%=tr.getEventName() %>"><%=tr.getEventName() %>
             </a>
            </li>
<%
  	}
 }
%>
          </ul>
        </div>
      </div>

  <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>
<!--
  <canvas class="my-4 w-100" id="sample" width="900" height="380"></canvas>
   <canvas id="stage"></canvas>
-->
      <h2>筋トレ種目</h2>
      <div class="table-responsive small" >
        <table class="table table-primary table-striped table-sm" >
          <thead>
            <tr>
              <th scope="col">日付</th>
              <th scope="col">種目名</th>
              <th scope="col">㎏</th>
              <th scope="col">reps</th>
              <th scope="col">set</th>
              <th scope="col">count</th>
            </tr>
          </thead>
           <tbody id ="targetTable">
<%
 List<Training> trainingList = (List<Training>)session.getAttribute("trainingList");
 if(trainingList != null){
    for(int i = 0; i < trainingList.size(); i++){
    	Training tr = trainingList.get(i);
%>
            <tr>
              <td scope="row"><%=tr.getDt() %></td>
              <td ><%=tr.getEventName() %></td>
              <td><%=tr.getKg() %></td>
              <td><%=tr.getReps() %></td>
              <td><%=tr.getSets() %></td>
              <td><%=tr.getCount() %></td>
            </tr>
<%
  	}
 }
%>
         </tbody>
        </table>
      </div>
      <form action="/trainingManager/EventSearchServlet2" id="gp"></form>
    </main>
 </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous">
</script>

<script src="https://cdn.jsdelivr.net/npm/chart.js@4.2.1/dist/chart.umd.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns/dist/chartjs-adapter-date-fns.bundle.min.js"></script>

<script src="/trainingManager/JS/sample.js"></script>
</body>
</html>