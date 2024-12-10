<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="parti.mainModule.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<!-- Begin: Button Link -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- End: Button Link -->
  
<!--  Begin: Data Table -->
<link rel="stylesheet" type="text/css" href="jquery.dataTables.css">
<script type="text/javascript" language="javascript" src="jquery-3.5.1.js"></script>
<script type="text/javascript" language="javascript" src="jquery.dataTables.js"></script>
	
<script type="text/javascript" language="javascript">
	
$(document).ready(function() {
	$('#example').DataTable( {
		"paging":   false,
		"ordering": true,
		"info":     true
	} );
} );
 
 /* $(document).ready(function() {
    $('#example').DataTable();
} );
 */
</script>
	
<!--  End: Data Table -->
<style>
div#banner {
background-color: white;
height: 120px;
}
div#main {
border: solid 2px blue;
margin-left: 100px;
min-height: 300px;
background-color: yellow;
}
div#leftMenu {
background-color: green;
width: 80px;
position: relative;
top: 0px;
min-height: 300px;
}
div#topMenu {
border: 0px solid red;
background-color: orange;
font-size: 20px;
}
div#main {
border: 2px solid red;
background-color: red;
}
div#content {
border: 2px solid red;
background-color: red;
}
.mycontainer {
    background-color:white;
    display:inline-flex;
}
.fixed-left-menu {
    background-color:white;
    width: 200px;
    
}
.flex-item {
    background-color:white;
    flex-grow: 1;
}
</style>
</head>
<meta charset="ISO-8859-1">
<title>Dashboard Template</title>
</head>
<body>
<div id="outer">
<!-- begin: banner -->
<div id="banner" align="center">
<img alt="nitttr-bpl" src="banner.jpg" width="70%" height="100%" >
</div>
<!-- end: banner -->
<br/>
<!--  begin: top menu -->
<div id="topMenu" class="topMenu">
<%@include file="topMenu.jsp" %>
</div>
<!--  end: top menu -->
<!--  begin: container -->
<div class="mycontainer">
    
    <!-- begin: side navigation -->
    <%@include file="leftMenu.jsp" %>
    <!-- end: side navigation -->
    <!--  begin: main content -->
    <div class="flex-item" align="center">
    
    <h1>Main Content</h1>
  
    <% 	
    int pageNo = (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("pageNo")));	
    int limit = (null==request.getParameter("limit")?0:Integer.parseInt(request.getParameter("limit")));	
	int totalPages= (null==request.getAttribute("totalPages")?0:(Integer)request.getAttribute("totalPages"));	
	%>	
    <% ArrayList<Participant> participantList = (ArrayList<Participant>)request.getAttribute("participantList"); %>
    <% Iterator<Participant> it= participantList.iterator(); %>
    
<%if(pageNo!=0){ %>   	
	<div align="left">	
	<font color="blue" size="3">	
	<%if(pageNo==1){%>|&lt;&nbsp;&nbsp;&nbsp;&lt;&lt;<%} else { %><a href="participantCntrl?page=participantDashboard&opr=showAll&pageNo=<%=1%>&limit=<%=limit%>">|&lt;</a>&nbsp;&nbsp;&nbsp;<a href="participantCntrl?page=participantDashboard&opr=showAll&pageNo=<%=pageNo-1%>&limit=<%=limit%>">&lt;&lt;</a><%}%>	
	&nbsp;&nbsp;&nbsp;Page: <%=pageNo%>/<%=totalPages%>	
	<%if(pageNo==totalPages){%>&nbsp;&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;&nbsp;&gt;|<%} else { %>&nbsp;&nbsp;&nbsp;<a href="participantCntrl?page=participantDashboard&opr=showAll&pageNo=<%=pageNo+1%>&limit=<%=limit%>">&gt;&gt;</a>&nbsp;&nbsp;&nbsp;<a href="participantCntrl?page=participantDashboard&opr=showAll&pageNo=<%=totalPages%>&limit=<%=limit%>">&gt;|</a><%}%>	
	</font>	
	</div>    	
	<%} %>	
    

<table id="example" class="display" style="width:100%">
<thead>
<tr>
<th>SN</th>
<th>ID</th>
<th>TIMESTAMP</th>
<th>EMAIL ADDRESS</th>
<th>PARTICIPANT NAME</th>
<th>REGISTERED EMAIL ID</th>
<th>MOBILE NO</th>
<th>TRAINING PROGRAMME CODE</th>
<th>QUERY TYPE</th>
<th>QUERY DETAILS</th>
<th>UPLOAD SCREENSHOT URL</th>
<th>View</th>
<th>Edit</th>
<th>Delete</th>
 </tr>
 </thead>
 <tbody>
<% int count=(pageNo-1)*limit+1; %>
<% while(it.hasNext()){
	Participant participant = (Participant)it.next();
	%>
<tr>
<td><%=count++%></td>
<td><%=participant.getId()%></td>
<td><%=participant.getTimestamp()%></td>
<td><%=participant.getEmail_address()%></td>
<td><%=participant.getParticipant_name()%></td>
<td><%=participant.getRegistered_email_id()%></td>
<td><%=participant.getMobile_no()%></td>
<td><%=participant.getTraining_programme_code()%></td>
<td><%=participant.getQuery_type()%></td>
<td><%=participant.getQuery_details()%></td>
<td><%=participant.getUpload_screenshot_url()%></td>
<td><a href="participantCntrl?page=participantDashboard&opr=view&id=<%=participant.getId()%>&pageNo=<%=pageNo%>&limit=<%=limit%>"><button type="button" class="btn btn-primary">View</button></a></td>
<td><a href="participantCntrl?page=participantDashboard&opr=edit&id=<%=participant.getId()%>&pageNo=<%=pageNo%>&limit=<%=limit%>"><button type="button" class="btn btn-success">Edit</button></a></td>
<td><a href="participantCntrl?page=participantDashboard&opr=delete&id=<%=participant.getId()%>&pageNo=<%=pageNo%>&limit=<%=limit%>"><button type="button" class="btn btn-danger">Delete</button></a></td>
</tr>
	<%} %>
</tbody>
	</table>    </div>
    <!--  end: main content -->
</div>
<!--  end: container -->
</div>
</body>
</html>
