<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="parti.mainModule.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<!--  Begin: lines added by PNH on 06.12.2022 -->
<style>
@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Koulen&family=Lato&family=Nunito&family=Playfair+Display:ital@1&family=Prata&family=Raleway:ital,wght@1,100&family=Roboto&family=Roboto+Condensed&family=Teko&display=swap');
.btn{
font-family: Roboto, sans-serif;
font-weight: 0;
font-size: 14px;
color: #fff;
background-color: #32CD32;
padding: 10px 30px;
border: 2px solid #32CD32;
box-shadow: rgb(0, 0, 0) 0px 0px 0px 0px;
border-radius: 50px;
align-items: center;
cursor: pointer;
}
.btn:hover{
background-color: #fff;
color: #0066cc;
border: solid 2px #0066cc;
}
.btn1{
font-family: Roboto, sans-serif;
font-weight: 0;
font-size: 14px;
color: #fff;
background-color: #DE3163;
padding: 10px 30px;
border: 2px solid #DE3163;
box-shadow: rgb(0, 0, 0) 0px 0px 0px 0px;
border-radius: 50px;
align-items: center;
cursor: pointer;
}
.btn1:hover{
background-color: #fff;
color: #0066cc;
border: solid 2px #0066cc;
}
.btn3{
font-family: Roboto, sans-serif;
font-weight: 0;
font-size: 14px;
color: #fff;
background-color: #6495ED;
padding: 10px 30px;
border: 2px solid #6495ED;
box-shadow: rgb(0, 0, 0) 0px 0px 0px 0px;
border-radius: 50px;
align-items: center;
cursor: pointer;
}
.btn3:hover{
background-color: #fff;
color: #0066cc;
border: solid 2px #0066cc;
}
</style>
<!-- 2️code HTML below -->
<!--  End: lines added by PNH on 06.12.2022 -->
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
<!-- Begin: Data Table Section -->
<style>
.mytable{
  border-collapse: collapse;
  width: 100%;
}
.mytable th, 
.mytable td {
  text-align: left;
  padding: 8px;
}
.mytable tr:nth-child(even){background-color: #f2f2f2}
.mytable th {
  background-color: #C0C0C0;
  color: black;
}
</style>
<!-- End: Data Table Section -->
<!-- Begin:Add/Update Section -->
<!--  Begin: Date Picker -->
<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>
<script>
    $(document).ready(function () {
    	$('input[id$=dob]').datepicker({
    	    dateFormat: 'dd-mm-yy'
    	});
    });
</script>
<!-- End: Date Picker -->
<!-- End:Add/Update Section -->
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

<%
DropDownDBService dropDownDBService =new DropDownDBService();
Participant participant = (Participant)request.getAttribute("participant");
String homeURL=(String)request.getSession().getAttribute("homeURL");
String pageNo=(null==(String)request.getParameter("pageNo")?"":(String)request.getParameter("pageNo"));
String limit=(null==request.getParameter("limit")?"":(String)request.getParameter("limit"));
ArrayList<DropDownDTO> list = new ArrayList<DropDownDTO>();
DropDownDTO ddDto=new DropDownDTO();
Iterator<DropDownDTO> it = list.iterator();
%>
<form action="participantCntrl" id="myform" name="myform" accept-charset="UTF-8" >

<h2>Add/Update Section</h2>
<%if(null!=request.getSession().getAttribute("msg")){ %>
<%String msg = (String)request.getSession().getAttribute("msg");%>
<%if(!msg.equals("")){ %>
<br/><h3><font color="red"><%=msg%></font></h3><br/>
<%}%>
<%request.getSession().removeAttribute("msg");%>
<%}%>
<table cellspacing="10px">

<tr>

<td>ID:  <%=participant.getId()%></td>
</tr>

<tr>

<td>
TIMESTAMP<font color="red">*</font>:<br/>

<input required type="text" name="timestamp" value="<%=participant.getTimestamp()%>" size="20">
</td>

<td>
EMAIL ADDRESS<font color="red">*</font>:<br/>
<input required type="text" name="email_address" value="<%=participant.getEmail_address()%>" size="30">
</td>

</tr>

<tr>

<td>
PARTICIPANT NAME<font color="red">*</font>:<br/>

<input required type="text" name="participant_name" value="<%=participant.getParticipant_name()%>" size="30">
</td>

<td>
REGISTERED EMAIL ID<font color="red">*</font>:<br/>
<input required type="text" name="registered_email_id" value="<%=participant.getRegistered_email_id()%>" size="30">
</td>

</tr>

<tr>

<td>
MOBILE NO<font color="red">*</font>:<br/>

<input required type="text" name="mobile_no" value="<%=participant.getMobile_no()%>" size="30">
</td>

<td>
TRAINING PROGRAMME CODE<font color="red">*</font>:<br/>
<input required type="text" name="training_programme_code" value="<%=participant.getTraining_programme_code()%>" size="30">
</td>

</tr>

<tr>

<td>
QUERY TYPE<font color="red">*</font>:<br/>

<input required type="text" name="query_type" value="<%=participant.getQuery_type()%>" size="30">
</td>

<td>
QUERY DETAILS<font color="red">*</font>:<br/>
<input required type="text" name="query_details" value="<%=participant.getQuery_details()%>" size="30">
</td>

</tr>

<tr>

<td>
UPLOAD SCREENSHOT URL<font color="red">*</font>:<br/>

<input required type="text" name="upload_screenshot_url" value="<%=participant.getUpload_screenshot_url()%>" size="30">
</td>

</tr>

</table>

<input type="hidden" name="page" value= "saveShowNextParticipant">
<input type="hidden" name="pageNo" value= "<%=pageNo%>">
<input type="hidden" name="limit" value= "<%=limit%>">
<input type="hidden" name="id" value= "<%=participant.getId()%>">
</form>

<form action="participantCntrl"  id="myform1" name="myform1" accept-charset="UTF-8" >
<input type="hidden" name="page" value= "saveShowNextParticipant">
<input type="hidden" name="pageNo" value= "<%=pageNo%>">
<input type="hidden" name="limit" value= "<%=limit%>">
<input type="hidden" name="id" value= "<%=participant.getId()%>">
</form>    
    
<%String buttonClicked = request.getParameter("opr");%>
<%if(buttonClicked.equals("edit")) {%>
<button type="submit" form="myform"  name="opr" value= "update" class="btn3">Update</button>
<button type="submit" form="myform1"  name="opr" value= "cancel" class="btn3">Cancel</button>
<%} else { %>
<button type="submit" form="myform"  name="opr" value= "addNew" class="btn3">Add New</button>
<button type="submit" form="myform1"  name="opr" value= "reset" class="btn3">Reset</button>
<%} %>
<% dropDownDBService.closeConnection(); %>
    <!--  End: Dynamic Codes -->
    
    <!-- End: Add/Update Section -->
    <h1>Data Table</h1>
  
    <% 	
    int pageNum = (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("pageNo")));	
    int limitNum = (null==request.getParameter("limit")?0:Integer.parseInt(request.getParameter("limit")));	
	int totalPages= (null==request.getAttribute("totalPages")?0:(Integer)request.getAttribute("totalPages"));	
	%>	
    <% ArrayList<Participant> participantList = (ArrayList<Participant>)request.getAttribute("participantList"); %>
    <% Iterator<Participant> it1= participantList.iterator(); %>
    
<%if(pageNum!=0){ %>   	
	<div align="left">	
	<font color="blue" size="3">	
	<%if(pageNum==1){%>|&lt;&nbsp;&nbsp;&nbsp;&lt;&lt;<%} else { %><a href="participantCntrl?page=participantDashboard&opr=showAll&pageNo=<%=1%>&limit=<%=limit%>">|&lt;</a>&nbsp;&nbsp;&nbsp;<a href="participantCntrl?page=participantDashboard&opr=showAll&pageNo=<%=pageNum-1%>&limit=<%=limit%>">&lt;&lt;</a><%}%>	
	&nbsp;&nbsp;&nbsp;Page: <%=pageNum%>/<%=totalPages%>	
	<%if(pageNum==totalPages){%>&nbsp;&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;&nbsp;&gt;|<%} else { %>&nbsp;&nbsp;&nbsp;<a href="participantCntrl?page=participantDashboard&opr=showAll&pageNo=<%=pageNo+1%>&limit=<%=limit%>">&gt;&gt;</a>&nbsp;&nbsp;&nbsp;<a href="participantCntrl?page=participantDashboard&opr=showAll&pageNo=<%=totalPages%>&limit=<%=limit%>">&gt;|</a><%}%>	
	</font>	
	</div>    	
	<%} %>	
    

<table class="mytable" style="width:100%">
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
<th>Edit</th>
<th>Delete</th>
 </tr>
 </thead>
 <tbody>
<% int count=(pageNum-1)*limitNum+1; %>
<% while(it1.hasNext()){
	Participant participant1 = (Participant)it1.next();
	%>
<tr>
<td><%=count++%></td>
<td><%=participant1.getId()%></td>
<td><%=participant1.getTimestamp()%></td>
<td><%=participant1.getEmail_address()%></td>
<td><%=participant1.getParticipant_name()%></td>
<td><%=participant1.getRegistered_email_id()%></td>
<td><%=participant1.getMobile_no()%></td>
<td><%=participant1.getTraining_programme_code()%></td>
<td><%=participant1.getQuery_type()%></td>
<td><%=participant1.getQuery_details()%></td>
<td><%=participant1.getUpload_screenshot_url()%></td>
<td><a href="participantCntrl?page=saveShowNextParticipant&opr=edit&id=<%=participant1.getId()%>&pageNo=<%=pageNo%>&limit=<%=limit%>"><button type="button" class="btn">Edit</button></a></td>
<td><a href="participantCntrl?page=saveShowNextParticipant&opr=delete&id=<%=participant1.getId()%>&pageNo=<%=pageNo%>&limit=<%=limit%>"><button type="button" class="btn1">Delete</button></a></td>
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
