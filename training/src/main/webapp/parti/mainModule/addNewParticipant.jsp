<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="parti.mainModule.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
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
<!--  Begin: Date Picker -->
<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>
<script>
</script>
<!-- End: Date Picker -->
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
    display:flex;
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
<!-- begin: banner -->
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
    
    <!--  Begin: Dynamic Codes -->

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
<form action="participantCntrl"  id="myform" name="myform" accept-charset="UTF-8" >
<table cellspacing="10px">
<tr>
<td>
TIMESTAMP<font color="red">*</font>:
</td>

<td>
<input required type="text" name="timestamp" value="<%=participant.getTimestamp()%>" size="20">
</td>

</tr>
<tr>
<td>
EMAIL ADDRESS<font color="red">*</font>:
</td>

<td>
<input required type="text" name="email_address" value="<%=participant.getEmail_address()%>" size="30">
</td>

</tr>
<tr>
<td>
PARTICIPANT NAME<font color="red">*</font>:
</td>

<td>
<input required type="text" name="participant_name" value="<%=participant.getParticipant_name()%>" size="30">
</td>

</tr>
<tr>
<td>
REGISTERED EMAIL ID<font color="red">*</font>:
</td>

<td>
<input required type="text" name="registered_email_id" value="<%=participant.getRegistered_email_id()%>" size="30">
</td>

</tr>
<tr>
<td>
MOBILE NO<font color="red">*</font>:
</td>

<td>
<input required type="text" name="mobile_no" value="<%=participant.getMobile_no()%>" size="30">
</td>

</tr>
<tr>
<td>
TRAINING PROGRAMME CODE<font color="red">*</font>:
</td>

<td>
<input required type="text" name="training_programme_code" value="<%=participant.getTraining_programme_code()%>" size="30">
</td>

</tr>
<tr>
<td>
QUERY TYPE<font color="red">*</font>:
</td>

<td>
<input required type="text" name="query_type" value="<%=participant.getQuery_type()%>" size="30">
</td>

</tr>
<tr>
<td>
QUERY DETAILS<font color="red">*</font>:
</td>

<td>
<input required type="text" name="query_details" value="<%=participant.getQuery_details()%>" size="30">
</td>

</tr>
<tr>
<td>
UPLOAD SCREENSHOT URL<font color="red">*</font>:
</td>

<td>
<input required type="text" name="upload_screenshot_url" value="<%=participant.getUpload_screenshot_url()%>" size="30">
</td>

</tr>

</table>

<input type="hidden" name="page" value= "addNewParticipant">
<input type="hidden" name="pageNo" value= "<%=pageNo%>">
<input type="hidden" name="limit" value= "<%=limit%>">
</form>

<button type="submit" form="myform"  name="opr" value= "save" class="form-submit-button">Save</button>
<a href="<%=homeURL%>"><button type="button" class="form-submit-button">Close</button></a>
<% dropDownDBService.closeConnection(); %>
    <!--  End: Dynamic Codes -->
    </div>
    <!--  end: main content -->
</div>
<!--  end: container -->
</div>
</body>
</html>
