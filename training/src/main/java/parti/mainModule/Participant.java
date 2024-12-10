package parti.mainModule;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class Participant {
 
int id;
String timestamp;
String email_address;
String participant_name;
String registered_email_id;
String mobile_no;
String training_programme_code;
String query_type;
String query_details;
String upload_screenshot_url;

public int getId()
{
return id;
}
public void setId(int id)
{
this.id=id;
}
public String getTimestamp()
{
return timestamp;
}
public void setTimestamp(String timestamp)
{
this.timestamp=timestamp;
}
public String getEmail_address()
{
return email_address;
}
public void setEmail_address(String email_address)
{
this.email_address=email_address;
}
public String getParticipant_name()
{
return participant_name;
}
public void setParticipant_name(String participant_name)
{
this.participant_name=participant_name;
}
public String getRegistered_email_id()
{
return registered_email_id;
}
public void setRegistered_email_id(String registered_email_id)
{
this.registered_email_id=registered_email_id;
}
public String getMobile_no()
{
return mobile_no;
}
public void setMobile_no(String mobile_no)
{
this.mobile_no=mobile_no;
}
public String getTraining_programme_code()
{
return training_programme_code;
}
public void setTraining_programme_code(String training_programme_code)
{
this.training_programme_code=training_programme_code;
}
public String getQuery_type()
{
return query_type;
}
public void setQuery_type(String query_type)
{
this.query_type=query_type;
}
public String getQuery_details()
{
return query_details;
}
public void setQuery_details(String query_details)
{
this.query_details=query_details;
}
public String getUpload_screenshot_url()
{
return upload_screenshot_url;
}
public void setUpload_screenshot_url(String upload_screenshot_url)
{
this.upload_screenshot_url=upload_screenshot_url;
}


public void setRequestParam(HttpServletRequest request) {

this.setId(null!=request.getParameter("id")&&!request.getParameter("id").equals("")?Integer.parseInt((String)request.getParameter("id")):0);
this.setTimestamp(null!=request.getParameter("timestamp")?request.getParameter("timestamp"):"");
this.setEmail_address(null!=request.getParameter("email_address")?request.getParameter("email_address"):"");
this.setParticipant_name(null!=request.getParameter("participant_name")?request.getParameter("participant_name"):"");
this.setRegistered_email_id(null!=request.getParameter("registered_email_id")?request.getParameter("registered_email_id"):"");
this.setMobile_no(null!=request.getParameter("mobile_no")?request.getParameter("mobile_no"):"");
this.setTraining_programme_code(null!=request.getParameter("training_programme_code")?request.getParameter("training_programme_code"):"");
this.setQuery_type(null!=request.getParameter("query_type")?request.getParameter("query_type"):"");
this.setQuery_details(null!=request.getParameter("query_details")?request.getParameter("query_details"):"");
this.setUpload_screenshot_url(null!=request.getParameter("upload_screenshot_url")?request.getParameter("upload_screenshot_url"):"");

}

public void displayReqParam(HttpServletRequest request) {


System.out.println("------Begin:Request Param Values---------");
System.out.println("id = "+request.getParameter("id"));
System.out.println("timestamp = "+request.getParameter("timestamp"));
System.out.println("email_address = "+request.getParameter("email_address"));
System.out.println("participant_name = "+request.getParameter("participant_name"));
System.out.println("registered_email_id = "+request.getParameter("registered_email_id"));
System.out.println("mobile_no = "+request.getParameter("mobile_no"));
System.out.println("training_programme_code = "+request.getParameter("training_programme_code"));
System.out.println("query_type = "+request.getParameter("query_type"));
System.out.println("query_details = "+request.getParameter("query_details"));
System.out.println("upload_screenshot_url = "+request.getParameter("upload_screenshot_url"));

System.out.println("------End:Request Param Values---------");
}

public void displayValues() {

System.out.println("Id = "+this.getId());
System.out.println("Timestamp = "+this.getTimestamp());
System.out.println("Email_address = "+this.getEmail_address());
System.out.println("Participant_name = "+this.getParticipant_name());
System.out.println("Registered_email_id = "+this.getRegistered_email_id());
System.out.println("Mobile_no = "+this.getMobile_no());
System.out.println("Training_programme_code = "+this.getTraining_programme_code());
System.out.println("Query_type = "+this.getQuery_type());
System.out.println("Query_details = "+this.getQuery_details());
System.out.println("Upload_screenshot_url = "+this.getUpload_screenshot_url());

}

public void setDefaultValues() {

this.setTimestamp("");
this.setEmail_address("");
this.setParticipant_name("");
this.setRegistered_email_id("");
this.setMobile_no("");
this.setTraining_programme_code("");
this.setQuery_type("");
this.setQuery_details("");
this.setUpload_screenshot_url("");

}
}