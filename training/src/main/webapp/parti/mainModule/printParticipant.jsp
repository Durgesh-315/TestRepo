<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="parti.mainModule.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="tablecss1.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
  background: rgb(204,204,204); 
}
page {
  background: white;
  display: block;
  margin: 0 auto;
  margin-bottom: 0.5cm;
  
}
page[size="A4"] {  
  width: 21cm;
  height: 29.7cm; 
}
page[size="A4"][layout="landscape"] {
  width: 29.7cm;
  height: 21cm;  
}
page[size="A3"] {
  width: 29.7cm;
  height: 42cm;
}
page[size="A3"][layout="landscape"] {
  width: 42cm;
  height: 29.7cm;  
}
page[size="A5"] {
  width: 14.8cm;
  height: 21cm;
}
page[size="A5"][layout="landscape"] {
  width: 21cm;
  height: 14.8cm;  
}
@media print {
  body, page {
    margin: 0;
    box-shadow: 0;
  }
}
</style>
</head>
<body>
<page size="A4">
<div align="center">
<h1> Print Form Content </h1>
    <!--  Begin: Dynamic Codes -->

<%
Participant participant = (Participant)request.getAttribute("participant");
%>
<table cellspacing="10px">
<tr>
<td align="right">
ID:
</td>

<td>
<%=participant.getId()%>
</td>

</tr>
<tr>
<td align="right">
TIMESTAMP:
</td>

<td>
<%=participant.getTimestamp()%>
</td>

</tr>
<tr>
<td align="right">
EMAIL ADDRESS:
</td>

<td>
<%=participant.getEmail_address()%>
</td>

</tr>
<tr>
<td align="right">
PARTICIPANT NAME:
</td>

<td>
<%=participant.getParticipant_name()%>
</td>

</tr>
<tr>
<td align="right">
REGISTERED EMAIL ID:
</td>

<td>
<%=participant.getRegistered_email_id()%>
</td>

</tr>
<tr>
<td align="right">
MOBILE NO:
</td>

<td>
<%=participant.getMobile_no()%>
</td>

</tr>
<tr>
<td align="right">
TRAINING PROGRAMME CODE:
</td>

<td>
<%=participant.getTraining_programme_code()%>
</td>

</tr>
<tr>
<td align="right">
QUERY TYPE:
</td>

<td>
<%=participant.getQuery_type()%>
</td>

</tr>
<tr>
<td align="right">
QUERY DETAILS:
</td>

<td>
<%=participant.getQuery_details()%>
</td>

</tr>
<tr>
<td align="right">
UPLOAD SCREENSHOT URL:
</td>

<td>
<%=participant.getUpload_screenshot_url()%>
</td>

</tr>

</table>
</div>
</page>
<!-- <page size="A4"></page>
<page size="A4" layout="landscape"></page>
<page size="A5"></page>
<page size="A5" layout="landscape"></page>
<page size="A3"></page>
<page size="A3" layout="landscape"></page>
 -->
</body>
</html>
