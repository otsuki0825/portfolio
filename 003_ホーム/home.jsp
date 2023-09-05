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
<title>ホーム画面</title>
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
     <li><a class="dropdown-item" href="/trainingManager/jsp/groupRegist.jsp">グループ登録</a></li>
     <li><a class="dropdown-item" href="/trainingManager/jsp/report2.jsp">レポート</a></li>
     <li><a class="dropdown-item" href=""></a></li>
     <li><hr class="dropdown-divider"></li>
     <li><a class="dropdown-item" href="/trainingManager/LogoutServlet">ログアウト</a></li>
   </ul>
  </div>
 </div>
</div>

<div class="mx-auto p-2" style="width: 400px;">
 <%
    List<Training> groupList = (List<Training>)session.getAttribute("groupList");
    if(groupList != null){
        for(int i = 0; i < groupList.size(); i++){
        	Training tr = groupList.get(i);
 %>
 <form action="/trainingManager/EventSearchServlet" method="post" >
  <div class="row g-3">
   <div class="col">
      <input type="text" readonly class="form-control-plaintext form-control-lg" id="staticEmail" value="<%=tr.getGroupName()%>" name="groupName">
   </div>
   <div class="col">
      <input type="submit" class="btn btn-outline-primary" value="詳細">
   </div>
  </div>
 </form>
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