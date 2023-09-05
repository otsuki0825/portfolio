<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
      crossorigin="anonymous">
<meta charset="UTF-8">
<title>グループ登録</title>
</head>
<body>
<div class="position-relative">
  <div class="p-2 mb-2 bg-primary text-white">筋トレ管理</div>
</div>
<div class="mx-auto p-2" style="width: 250px;">
	<form action="/trainingManager/GroupRegistServlet" method="post" >
<%
    String ng = (String)request.getAttribute("ng");
    if(ng != null){
%>
    	 <div class="alert alert-danger" role="alert">
         <%=ng %>
         </div>
<%
   }
%>
		<div class="mb-3">
		  <label for="exampleFormControlInput1" class="form-label">グループ名</label>
		  <input type="text" class="form-control" name="groupName" id="exampleFormControlInput1">
		</div>
           <input type="submit" class="btn btn-outline-primary" value="登録">
        <br>
        <br>
           <a href="/trainingManager/jsp/home.jsp">
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-reply" viewBox="0 0 16 16">
            <path d="M6.598 5.013a.144.144 0 0 1 .202.134V6.3a.5.5 0 0 0 .5.5c.667 0 2.013.005 3.3.822.984.624 1.99 1.76 2.595 3.876-1.02-.983-2.185-1.516-3.205-1.799a8.74 8.74 0 0 0-1.921-.306 7.404 7.404 0 0 0-.798.008h-.013l-.005.001h-.001L7.3 9.9l-.05-.498a.5.5 0 0 0-.45.498v1.153c0 .108-.11.176-.202.134L2.614 8.254a.503.503 0 0 0-.042-.028.147.147 0 0 1 0-.252.499.499 0 0 0 .042-.028l3.984-2.933zM7.8 10.386c.068 0 .143.003.223.006.434.02 1.034.086 1.7.271 1.326.368 2.896 1.202 3.94 3.08a.5.5 0 0 0 .933-.305c-.464-3.71-1.886-5.662-3.46-6.66-1.245-.79-2.527-.942-3.336-.971v-.66a1.144 1.144 0 0 0-1.767-.96l-3.994 2.94a1.147 1.147 0 0 0 0 1.946l3.994 2.94a1.144 1.144 0 0 0 1.767-.96v-.667z"/>
            </svg>戻る
           </a>
	</form>
</div>
</body>
</html>