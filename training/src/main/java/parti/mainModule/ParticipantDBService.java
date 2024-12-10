package parti.mainModule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
public class ParticipantDBService {
	Connection con;
	
	
	public ParticipantDBService()
	{
		DBConnectionDTO conDTO = new DBConnectionDTO();
		con=conDTO.getConnection();
	}
	
	public void closeConnection()
	{
		try {
			con.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
public int getTotalPages(int limit)
	{
		String query="select count(*) from participant";
	    int totalRecords=0;
	    int totalPages=0;
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	totalRecords= rs.getInt(1);
	    }
	    stmt.close();
	    rs.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		totalPages=totalRecords/limit;
		if(totalRecords%limit!=0)
		{
			totalPages+=1;
		}
		return totalPages;
	}
	
	//pagination
	public int getTotalPages(Participant participant,int limit)
	{
		String query=getDynamicQuery2(participant);
		int totalRecords=0;
	    int totalPages=0;
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	totalRecords= rs.getInt(1);
	    }
	    stmt.close();
	    rs.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		totalPages=totalRecords/limit;
		if(totalRecords%limit!=0)
		{
			totalPages+=1;
		}
		return totalPages;
	}
	
	
	public int getParticipantId(Participant participant)
	{
		int id=0;
		String query="select id from participant";
String whereClause = " where "+ "timestamp=? and email_address=? and participant_name=? and registered_email_id=? and mobile_no=? and training_programme_code=? and query_type=? and query_details=? and upload_screenshot_url=?";
	    query+=whereClause;
		System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, participant.getTimestamp());
pstmt.setString(2, participant.getEmail_address());
pstmt.setString(3, participant.getParticipant_name());
pstmt.setString(4, participant.getRegistered_email_id());
pstmt.setString(5, participant.getMobile_no());
pstmt.setString(6, participant.getTraining_programme_code());
pstmt.setString(7, participant.getQuery_type());
pstmt.setString(8, participant.getQuery_details());
pstmt.setString(9, participant.getUpload_screenshot_url());
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()) {
	       	id = rs.getInt("id");
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return id;
	}
	public void createParticipant(Participant participant)
	{
		
String query="INSERT INTO participant(timestamp,email_address,participant_name,registered_email_id,mobile_no,training_programme_code,query_type,query_details,upload_screenshot_url) VALUES(?,?,?,?,?,?,?,?,?)";
	
    System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, participant.getTimestamp());
pstmt.setString(2, participant.getEmail_address());
pstmt.setString(3, participant.getParticipant_name());
pstmt.setString(4, participant.getRegistered_email_id());
pstmt.setString(5, participant.getMobile_no());
pstmt.setString(6, participant.getTraining_programme_code());
pstmt.setString(7, participant.getQuery_type());
pstmt.setString(8, participant.getQuery_details());
pstmt.setString(9, participant.getUpload_screenshot_url());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	  
  	System.out.println(e);
		}
		int id = getParticipantId(participant);
		participant.setId(id);
	}
	public void updateParticipant(Participant participant)
	{
		
String query="update participant set "+"timestamp=?,email_address=?,participant_name=?,registered_email_id=?,mobile_no=?,training_programme_code=?,query_type=?,query_details=?,upload_screenshot_url=? where id=?";
	   
 System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, participant.getTimestamp());
pstmt.setString(2, participant.getEmail_address());
pstmt.setString(3, participant.getParticipant_name());
pstmt.setString(4, participant.getRegistered_email_id());
pstmt.setString(5, participant.getMobile_no());
pstmt.setString(6, participant.getTraining_programme_code());
pstmt.setString(7, participant.getQuery_type());
pstmt.setString(8, participant.getQuery_details());
pstmt.setString(9, participant.getUpload_screenshot_url());
pstmt.setInt(10, participant.getId());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	    	System.out.println(e);
		}
		
	}
	public String getValue(String code,String table) {
		
		String value="";
		String query="select value from "+table+" where code='"+code+"'";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	    	value=rs.getString("value");
	    }
		}
		catch (Exception e) {
			System.out.println(e);
		}
	    return value;
	}
	
	public Participant getParticipant(int id)
	{
		Participant participant =new Participant();
		String query="select * from participant where id="+id;
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	
participant.setId(rs.getInt("id")==0?0:rs.getInt("id"));
participant.setTimestamp(rs.getString("timestamp")==null?"":rs.getString("timestamp"));
participant.setEmail_address(rs.getString("email_address")==null?"":rs.getString("email_address"));
participant.setParticipant_name(rs.getString("participant_name")==null?"":rs.getString("participant_name"));
participant.setRegistered_email_id(rs.getString("registered_email_id")==null?"":rs.getString("registered_email_id"));
participant.setMobile_no(rs.getString("mobile_no")==null?"":rs.getString("mobile_no"));
participant.setTraining_programme_code(rs.getString("training_programme_code")==null?"":rs.getString("training_programme_code"));
participant.setQuery_type(rs.getString("query_type")==null?"":rs.getString("query_type"));
participant.setQuery_details(rs.getString("query_details")==null?"":rs.getString("query_details"));
participant.setUpload_screenshot_url(rs.getString("upload_screenshot_url")==null?"":rs.getString("upload_screenshot_url"));
	    	
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return participant;
	}
	
	
	public ArrayList<Participant> getParticipantList()
	{
		ArrayList<Participant> participantList =new ArrayList<Participant>();
		String query="select * from participant";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	Participant participant =new Participant();
participant.setId(rs.getInt("id")==0?0:rs.getInt("id"));
participant.setTimestamp(rs.getString("timestamp")==null?"":rs.getString("timestamp"));
participant.setEmail_address(rs.getString("email_address")==null?"":rs.getString("email_address"));
participant.setParticipant_name(rs.getString("participant_name")==null?"":rs.getString("participant_name"));
participant.setRegistered_email_id(rs.getString("registered_email_id")==null?"":rs.getString("registered_email_id"));
participant.setMobile_no(rs.getString("mobile_no")==null?"":rs.getString("mobile_no"));
participant.setTraining_programme_code(rs.getString("training_programme_code")==null?"":rs.getString("training_programme_code"));
participant.setQuery_type(rs.getString("query_type")==null?"":rs.getString("query_type"));
participant.setQuery_details(rs.getString("query_details")==null?"":rs.getString("query_details"));
participant.setUpload_screenshot_url(rs.getString("upload_screenshot_url")==null?"":rs.getString("upload_screenshot_url"));
	    	participantList.add(participant);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return participantList;
	}
	
	public ArrayList<Participant> getParticipantList(int pageNo,int limit)
	{
		ArrayList<Participant> participantList =new ArrayList<Participant>();
String query="select * from participant limit "+limit +" offset "+limit*(pageNo-1);
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	Participant participant =new Participant();
participant.setId(rs.getInt("id")==0?0:rs.getInt("id"));
participant.setTimestamp(rs.getString("timestamp")==null?"":rs.getString("timestamp"));
participant.setEmail_address(rs.getString("email_address")==null?"":rs.getString("email_address"));
participant.setParticipant_name(rs.getString("participant_name")==null?"":rs.getString("participant_name"));
participant.setRegistered_email_id(rs.getString("registered_email_id")==null?"":rs.getString("registered_email_id"));
participant.setMobile_no(rs.getString("mobile_no")==null?"":rs.getString("mobile_no"));
participant.setTraining_programme_code(rs.getString("training_programme_code")==null?"":rs.getString("training_programme_code"));
participant.setQuery_type(rs.getString("query_type")==null?"":rs.getString("query_type"));
participant.setQuery_details(rs.getString("query_details")==null?"":rs.getString("query_details"));
participant.setUpload_screenshot_url(rs.getString("upload_screenshot_url")==null?"":rs.getString("upload_screenshot_url"));
	    	participantList.add(participant);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return participantList;
	}
	
	public void deleteParticipant(int id) {
		
			String query="delete from participant where id="+id;
		    System.out.println(query);
				
			
		    try {
			Statement stmt = con.createStatement();
		    int x = stmt.executeUpdate(query);
		    }
		    catch (Exception e) {
		    	System.out.println(e);
			}
		
	}
	
public String getDynamicQuery(Participant participant)
{
String query="select * from participant ";
String whereClause="";
whereClause+=(null==participant.getTimestamp()||participant.getTimestamp().equals(""))?"":" timestamp='"+participant.getTimestamp()+"'";
if(whereClause.equals(""))
whereClause+=(null==participant.getEmail_address()||participant.getEmail_address().equals(""))?"":" email_address='"+participant.getEmail_address()+"'";
else
whereClause+=(null==participant.getEmail_address()||participant.getEmail_address().equals(""))?"":" and email_address='"+participant.getEmail_address()+"'";
if(whereClause.equals(""))
whereClause+=(null==participant.getRegistered_email_id()||participant.getRegistered_email_id().equals(""))?"":" registered_email_id='"+participant.getRegistered_email_id()+"'";
else
whereClause+=(null==participant.getRegistered_email_id()||participant.getRegistered_email_id().equals(""))?"":" and registered_email_id='"+participant.getRegistered_email_id()+"'";
if(whereClause.equals(""))
whereClause+=(null==participant.getMobile_no()||participant.getMobile_no().equals(""))?"":" mobile_no='"+participant.getMobile_no()+"'";
else
whereClause+=(null==participant.getMobile_no()||participant.getMobile_no().equals(""))?"":" and mobile_no='"+participant.getMobile_no()+"'";
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public String getDynamicQuery2(Participant participant)
{
String query="select count(*) from participant ";
String whereClause="";
whereClause+=(null==participant.getTimestamp()||participant.getTimestamp().equals(""))?"":" timestamp='"+participant.getTimestamp()+"'";
if(whereClause.equals(""))
whereClause+=(null==participant.getEmail_address()||participant.getEmail_address().equals(""))?"":" email_address='"+participant.getEmail_address()+"'";
else
whereClause+=(null==participant.getEmail_address()||participant.getEmail_address().equals(""))?"":" and email_address='"+participant.getEmail_address()+"'";
if(whereClause.equals(""))
whereClause+=(null==participant.getRegistered_email_id()||participant.getRegistered_email_id().equals(""))?"":" registered_email_id='"+participant.getRegistered_email_id()+"'";
else
whereClause+=(null==participant.getRegistered_email_id()||participant.getRegistered_email_id().equals(""))?"":" and registered_email_id='"+participant.getRegistered_email_id()+"'";
if(whereClause.equals(""))
whereClause+=(null==participant.getMobile_no()||participant.getMobile_no().equals(""))?"":" mobile_no='"+participant.getMobile_no()+"'";
else
whereClause+=(null==participant.getMobile_no()||participant.getMobile_no().equals(""))?"":" and mobile_no='"+participant.getMobile_no()+"'";
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public ArrayList<Participant> getParticipantList(Participant participant)
{
ArrayList<Participant> participantList =new ArrayList<Participant>();
String query=getDynamicQuery(participant);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
Participant participant2 =new Participant();
participant2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
participant2.setTimestamp(rs.getString("timestamp")==null?"":rs.getString("timestamp"));
participant2.setEmail_address(rs.getString("email_address")==null?"":rs.getString("email_address"));
participant2.setParticipant_name(rs.getString("participant_name")==null?"":rs.getString("participant_name"));
participant2.setRegistered_email_id(rs.getString("registered_email_id")==null?"":rs.getString("registered_email_id"));
participant2.setMobile_no(rs.getString("mobile_no")==null?"":rs.getString("mobile_no"));
participant2.setTraining_programme_code(rs.getString("training_programme_code")==null?"":rs.getString("training_programme_code"));
participant2.setQuery_type(rs.getString("query_type")==null?"":rs.getString("query_type"));
participant2.setQuery_details(rs.getString("query_details")==null?"":rs.getString("query_details"));
participant2.setUpload_screenshot_url(rs.getString("upload_screenshot_url")==null?"":rs.getString("upload_screenshot_url"));
    	participantList.add(participant2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return participantList;
}
	
public ArrayList<Participant> getParticipantList(Participant participant,int pageNo,int limit)
{
ArrayList<Participant> participantList =new ArrayList<Participant>();
String query=getDynamicQuery(participant);
query+= " limit "+limit +" offset "+limit*(pageNo-1);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
Participant participant2 =new Participant();
participant2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
participant2.setTimestamp(rs.getString("timestamp")==null?"":rs.getString("timestamp"));
participant2.setEmail_address(rs.getString("email_address")==null?"":rs.getString("email_address"));
participant2.setParticipant_name(rs.getString("participant_name")==null?"":rs.getString("participant_name"));
participant2.setRegistered_email_id(rs.getString("registered_email_id")==null?"":rs.getString("registered_email_id"));
participant2.setMobile_no(rs.getString("mobile_no")==null?"":rs.getString("mobile_no"));
participant2.setTraining_programme_code(rs.getString("training_programme_code")==null?"":rs.getString("training_programme_code"));
participant2.setQuery_type(rs.getString("query_type")==null?"":rs.getString("query_type"));
participant2.setQuery_details(rs.getString("query_details")==null?"":rs.getString("query_details"));
participant2.setUpload_screenshot_url(rs.getString("upload_screenshot_url")==null?"":rs.getString("upload_screenshot_url"));
    	participantList.add(participant2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return participantList;
}
	
	
	public static void main(String[] args) {
		
		ParticipantDBService participantDBService =new ParticipantDBService();
		
		
		
		 //Test-1 : Create Participant
		  
		  Participant participant = new Participant(); participant.setDefaultValues();
		  participantDBService.createParticipant(participant);
		  
		 ArrayList<Participant> participantList = participantDBService.getParticipantList();
		ParticipantService participantService =new ParticipantService();
		participantService.displayList(participantList);
		
	}
}
