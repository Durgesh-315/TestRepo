<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="parti.mainModule.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<style>
table {
  border-collapse: collapse;
  width: 30%;
}
tr, th, td {
  text-align: left;
  height:35px;
  font-size:15px;
}
</style>
<style>
.form-submit-button {
background: #0066A2;
color: white;
border-style: outset;
border-color: #0066A2;
height: 30px;
width: 70px;
font: bold17px arial,sans-serif;
text-shadow: none;
}
</style>
<!-- Begin: Button Link -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- End: Button Link -->
  
<!--  Begin: DatePicker -->
<link rel = "stylesheet"
href = "//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script type = "text/javascript"
src = "https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js">
</script>
<script>
   $(function() {
   });
</script>
<!--  End: DatePicker -->
<!--  Begin: Data Table -->
<link rel="stylesheet" type="text/css" href="jquery.dataTables.css">
<!-- <script type="text/javascript" language="javascript" src="jquery-3.5.1.js"></script> -->
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
<script type="text/javascript">
function popup(){
	var url=window. location. href;
	url=url.replace("opr=search", "opr=downloadPrint");
	window.open(url, 'popUpWindow', 'height = 300, width = 500, left = 100, top = 100, scrollbars = yes, resizable = yes, menubar = no, toolbar = yes, location = no, directories = no, status = yes')
	}
</script>
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
    
    <h1>Search Content</h1>
  <% 	
    int pageNo = (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("pageNo")));	
    int limit = (null==request.getParameter("limit")?0:Integer.parseInt(request.getParameter("limit")));	
	int totalPages= (null==request.getAttribute("totalPages")?0:(Integer)request.getAttribute("totalPages"));	
	%>	
  
  <!-- begin:search form -->
  
    <!--  Begin: Dynamic Codes -->

<%
DropDownDBService dropDownDBService =new DropDownDBService();
Participant participant = (Participant)request.getSession().getAttribute("participantSearch");
ArrayList<DropDownDTO> list = new ArrayList<DropDownDTO>();
DropDownDTO ddDto=new DropDownDTO();
Iterator<DropDownDTO> it = list.iterator();
%>
<form action="participantCntrl"  id="myform" name="myform" accept-charset="UTF-8" >
<table cellspacing="10px">

<tr>
<td>
TIMESTAMP:
</td>

<td>
<input type="text" name="timestamp" value="<%=participant.getTimestamp()%>" size="20">
</td>

</tr>

<tr>
<td>
EMAIL ADDRESS:
</td>

<td>
<input type="text" name="email_address" value="<%=participant.getEmail_address()%>" size="30">
</td>

</tr>

<tr>
<td>
REGISTERED EMAIL ID:
</td>

<td>
<input type="text" name="registered_email_id" value="<%=participant.getRegistered_email_id()%>" size="30">
</td>

</tr>

<tr>
<td>
MOBILE NO:
</td>

<td>
<input type="text" name="mobile_no" value="<%=participant.getMobile_no()%>" size="30">
</td>

</tr>

</table>

<input type="hidden" name="page" value="searchParticipant">
<input type="hidden" name="pageNo" value="<%=pageNo%>">
<input type="hidden" name="limit" value="<%=limit%>">
</form>

<button type="submit" form="myform"  name="opr" value= "search" class="form-submit-button">Search</button>
<%if(pageNo==0){ %>
<a href="participantCntrl?page=searchParticipant&opr=showNone&pageNo=0&limit=0"><button type="button" class="form-submit-button">Reset</button></a>
<%} else {%>
<a href="participantCntrl?page=searchParticipant&opr=showNone&pageNo=1&limit=<%=limit%>"><button type="button" class="form-submit-button">Reset</button></a>
<% } %>
<% dropDownDBService.closeConnection(); %>

<%if(pageNo!=0){ %>
<div align="left">
<font color="blue" size="3">
<%if(pageNo==1){%>|&lt;&nbsp;&nbsp;&nbsp;&lt;&lt;<%} else { %><a href="participantCntrl?page=searchParticipant&opr=searchFirst&pageNo=<%=1%>&limit=<%=limit%>">|&lt;</a>&nbsp;&nbsp;&nbsp;<a href="participantCntrl?page=searchParticipant&opr=searchPrev&pageNo=<%=pageNo-1%>&limit=<%=limit%>">&lt;&lt;</a><%}%>
&nbsp;&nbsp;&nbsp;Page: <%=pageNo%>/<%=totalPages%>
<%if(pageNo==totalPages){%>&nbsp;&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;&nbsp;&gt;|<%} else { %>&nbsp;&nbsp;&nbsp;<a href="participantCntrl?page=searchParticipant&opr=searchNext&pageNo=<%=pageNo+1%>&limit=<%=limit%>">&gt;&gt;</a>&nbsp;&nbsp;&nbsp;<a href="participantCntrl?page=searchParticipant&opr=searchLast&pageNo=<%=totalPages%>&limit=<%=limit%>">&gt;|</a><%}%>
</font>
</div>
<%} %><br/>
<div align="right">
<button onclick="popup()">Download/Print</button><br/><br/>
</div>

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
 <% String opr= (String)request.getAttribute("opr"); %>
<%if(null==opr||!opr.equals("showNone")) { %>
<% ArrayList<Participant> participantList = (ArrayList<Participant>)request.getAttribute("participantList"); %>
    <% Iterator<Participant> it2= participantList.iterator(); %>
 <tbody>
<% int count=(pageNo-1)*limit+1; %>
<% while(it2.hasNext()){
	Participant participant2 = (Participant)it2.next();
	%>
<tr>
<td><%=count++%></td>
<td><%=participant2.getId()%></td>
<td><%=participant2.getTimestamp()%></td>
<td><%=participant2.getEmail_address()%></td>
<td><%=participant2.getParticipant_name()%></td>
<td><%=participant2.getRegistered_email_id()%></td>
<td><%=participant2.getMobile_no()%></td>
<td><%=participant2.getTraining_programme_code()%></td>
<td><%=participant2.getQuery_type()%></td>
<td><%=participant2.getQuery_details()%></td>
<td><%=participant2.getUpload_screenshot_url()%></td>
<td><a href="participantCntrl?page=searchParticipant&opr=view&id=<%=participant2.getId()%>&pageNo=<%=pageNo%>&limit=<%=limit%>"><button type="button" class="btn btn-primary">View</button></a></td>
<td><a href="participantCntrl?page=searchParticipant&opr=edit&id=<%=participant2.getId()%>&pageNo=<%=pageNo%>&limit=<%=limit%>"><button type="button" class="btn btn-success">Edit</button></a></td>
<td><a href="participantCntrl?page=searchParticipant&opr=delete&id=<%=participant2.getId()%>&pageNo=<%=pageNo%>&limit=<%=limit%>"><button type="button" class="btn btn-danger">Delete</button></a></td>
</tr>
	<%} %>
</tbody>
	</table>
	<%}%>	
	</div>
    <!--  end: main content -->
</div>
<!--  end: container -->
</div>
</body>
</html>
