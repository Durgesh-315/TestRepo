package parti.mainModule;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class ParticipantCntrl
 */
@WebServlet("/parti/mainModule/participantCntrl")
public class ParticipantCntrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantCntrl() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page= request.getParameter("page");
		String opr = request.getParameter("opr");
		int pageNo = (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("pageNo")));
		int limit= (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("limit")));
		
		RequestDispatcher rd;
		ParticipantDBService participantDBService =new ParticipantDBService();
		Participant participant =new Participant();
		//Action for close buttons
		String homeURL=(null==request.getSession().getAttribute("homeURL")?"":(String)request.getSession().getAttribute("homeURL"));		
		if(page.equals("participantDashboard"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="participantCntrl?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			
			if(opr.equals("showAll")) 
			{
				ArrayList<Participant> participantList =new ArrayList<Participant>();
				
				if(pageNo==0)
				participantList = participantDBService.getParticipantList();
				else { //pagination
					int totalPages= participantDBService.getTotalPages(limit);
					participantList = participantDBService.getParticipantList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("participantList",participantList);
				rd = request.getRequestDispatcher("participantDashboard.jsp");
				rd.forward(request, response);
			} 
			else if(opr.equals("addNew")) //CREATE
			{
				participant.setDefaultValues();
				participant.displayValues();
				request.setAttribute("participant",participant);
				rd = request.getRequestDispatcher("addNewParticipant.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) //UPDATE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				participant = participantDBService.getParticipant(id);
				request.setAttribute("participant",participant);
				rd = request.getRequestDispatcher("updateParticipant.jsp");
				rd.forward(request, response);
			}
			//Begin: modified by Dr PNH on 06-12-2022
			else if(opr.equals("editNext")) //Save and Next
			{
				int id = Integer.parseInt(request.getParameter("id"));
				participant = participantDBService.getParticipant(id);
				request.setAttribute("participant",participant);
				rd = request.getRequestDispatcher("updateNextParticipant.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("saveShowNext")) //Save, show & next
			{
				participant.setDefaultValues();
				participant.displayValues();
				request.setAttribute("participant",participant);
				
				ArrayList<Participant> participantList =new ArrayList<Participant>();
				
				if(pageNo==0)
				participantList = participantDBService.getParticipantList();
				else { //pagination
					int totalPages= participantDBService.getTotalPages(limit);
					participantList = participantDBService.getParticipantList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("participantList",participantList);
				rd = request.getRequestDispatcher("saveShowNextParticipant.jsp");
				rd.forward(request, response);
			}
			//End: modified by Dr PNH on 06-12-2022
			else if(opr.equals("delete")) //DELETE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				participant.setId(id);
				participantDBService.deleteParticipant(id);
				request.setAttribute("participant",participant);
				rd = request.getRequestDispatcher("deleteParticipantSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) //READ
			{
				int id = Integer.parseInt(request.getParameter("id"));
				participant = participantDBService.getParticipant(id);
				request.setAttribute("participant",participant);
				rd = request.getRequestDispatcher("viewParticipant.jsp");
				rd.forward(request, response);
			}
			
		}
		else if(page.equals("addNewParticipant")) 
		{
			if(opr.equals("save"))
			{
				participant.setRequestParam(request);
				participant.displayValues();
				participantDBService.createParticipant(participant);
				request.setAttribute("participant",participant);
				if(pageNo!=0) {//pagination
					int totalPages= participantDBService.getTotalPages(limit);
					homeURL="participantCntrl?page=participantDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				rd = request.getRequestDispatcher("addNewParticipantSuccess.jsp");
				rd.forward(request, response);
			}
		}
		//Begin: modified by Dr PNH on 06-12-2022
		else if(page.equals("updateNextParticipant")) 
		{
			if(opr.equals("update"))
			{
				participant.setRequestParam(request);
				participantDBService.updateParticipant(participant);
				request.setAttribute("participant",participant);
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("participantCntrl?page=participantDashboard&opr=editNext&pageNo=0&limit=0&id=10");			}
		}
		else if(page.equals("saveShowNextParticipant")) 
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="participantCntrl?page=participantDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0";
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("addNew")) //save new record
			{
				participant.setRequestParam(request);
				participant.displayValues();
				participantDBService.createParticipant(participant);
				request.setAttribute("participant",participant);
				if(pageNo!=0) {//pagination
					int totalPages= participantDBService.getTotalPages(limit);
					homeURL="participantCntrl?page=participantDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("participantCntrl?page=participantDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd = request.getRequestDispatcher("participantCntrl?page=participantDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd.forward(request, response);
			}
			else if(opr.equals("edit"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				participant = participantDBService.getParticipant(id);
				request.setAttribute("participant",participant);
				
				ArrayList<Participant> participantList =new ArrayList<Participant>();
				if(pageNo==0)
				participantList = participantDBService.getParticipantList();
				else { //pagination
					int totalPages= participantDBService.getTotalPages(limit);
					participantList = participantDBService.getParticipantList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("participantList",participantList);
				rd = request.getRequestDispatcher("saveShowNextParticipant.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("update"))
			{
				participant.setRequestParam(request);
				participantDBService.updateParticipant(participant);
				request.setAttribute("participant",participant);
				request.getSession().setAttribute("msg", "Record updated successfully");
				response.sendRedirect("participantCntrl?page=participantDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
			}
			else if(opr.equals("delete"))
			{
					int id = Integer.parseInt(request.getParameter("id"));
					participant.setId(id);
					participantDBService.deleteParticipant(id);
					request.setAttribute("participant",participant);
					request.getSession().setAttribute("msg", "Record deleted successfully");
					response.sendRedirect("participantCntrl?page=participantDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			else if(opr.equals("reset")||opr.equals("cancel"))
			{
					response.sendRedirect("participantCntrl?page=participantDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			
		}
		//End: modified by Dr PNH on 06-12-2022
		else if(page.equals("updateParticipant")) 
		{
			if(opr.equals("update"))
			{
				participant.setRequestParam(request);
				participantDBService.updateParticipant(participant);
				request.setAttribute("participant",participant);
				rd = request.getRequestDispatcher("updateParticipantSuccess.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("viewParticipant")) 
		{
			if(opr.equals("print")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				participant = participantDBService.getParticipant(id);
				request.setAttribute("participant",participant);
				rd = request.getRequestDispatcher("printParticipant.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("searchParticipant"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="participantCntrl?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("search")) 
			{
				participant.setRequestParam(request);
				participant.displayValues();
				request.getSession().setAttribute("participantSearch",participant);
				request.setAttribute("opr","search");
				ArrayList<Participant> participantList =new ArrayList<Participant>();
				if(pageNo==0)
				participantList = participantDBService.getParticipantList(participant);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=participantDBService.getTotalPages(participant,limit);
					pageNo=1;
					participantList = participantDBService.getParticipantList(participant,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("participantList",participantList);
				rd = request.getRequestDispatcher("searchParticipant.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
//begin:code for download/print button
			else if(opr.equals("downloadPrint")) 
			{
				participant.setRequestParam(request);
				participant.displayValues();
				request.getSession().setAttribute("participantSearch",participant);
				request.setAttribute("opr","participant");
				ArrayList<Participant> participantList =new ArrayList<Participant>();
				if(pageNo==0)
				participantList = participantDBService.getParticipantList(participant);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=participantDBService.getTotalPages(participant,limit);
					pageNo=1;
					participantList = participantDBService.getParticipantList(participant,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("participantList",participantList);
				rd = request.getRequestDispatcher("searchParticipantDownloadPrint.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			//end:code for download/print button
			
			else if(opr.equals("showAll")) 
			{
				participant=(Participant)request.getSession().getAttribute("participantSearch");
				ArrayList<Participant> participantList =new ArrayList<Participant>();
				if(pageNo==0)
				participantList = participantDBService.getParticipantList(participant);
				else { //pagination
					int totalPages= participantDBService.getTotalPages(participant,limit);
					participantList = participantDBService.getParticipantList(participant,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("participantList",participantList);
				rd = request.getRequestDispatcher("searchParticipant.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("searchNext")||opr.equals("searchPrev")||opr.equals("searchFirst")||opr.equals("searchLast")) 
			{
				request.setAttribute("opr","search");
				participant=(Participant)request.getSession().getAttribute("participantSearch");
				ArrayList<Participant> participantList =new ArrayList<Participant>();
				if(pageNo==0)
				participantList = participantDBService.getParticipantList(participant);
				else { //pagination
					int totalPages= participantDBService.getTotalPages(participant,limit);
					participantList = participantDBService.getParticipantList(participant,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("participantList",participantList);
				rd = request.getRequestDispatcher("searchParticipant.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("showNone"))
			{
				participant.setDefaultValues();
				participant.displayValues();
				request.getSession().setAttribute("participantSearch",participant);
				request.setAttribute("opr","showNone");
				rd = request.getRequestDispatcher("searchParticipant.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				participant = participantDBService.getParticipant(id);
				request.setAttribute("participant",participant);
				rd = request.getRequestDispatcher("updateParticipant.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("delete")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				participant.setId(id);
				participantDBService.deleteParticipant(id);
				request.setAttribute("participant",participant);
				rd = request.getRequestDispatcher("deleteParticipantSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) 
			{
 			int id = Integer.parseInt(request.getParameter("id"));
				participant = participantDBService.getParticipant(id);
				request.setAttribute("participant",participant);
				rd = request.getRequestDispatcher("viewParticipant.jsp");
				rd.forward(request, response);
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("page=updateParticipant&opr=close&homePage=participantDashboard");
		String v = uri.getQuery();
		
	}
}
