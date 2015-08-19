package com.aca.action;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.delegate.RegisterMgrDelegate;
import com.aca.util.UtilConstants;

public class ChekCitizenUserAction extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		
		
		boolean flag=false;
		
		String target="";
		
		
		try{
			
			
			String userid=request.getParameter("userName");
			String role=request.getParameter("role");
			if(role.equalsIgnoreCase("NGO"))
			{
				target="./jsps/ngosregistrationform.jsp";
				
			}
			else if(role.equalsIgnoreCase("EMPLOYER"))
			{
				target="./jsps/employerregistration.jsp";
			}
			else 
			{ 
				target="./jsps/citizenregistrationform.jsp";
			}
			
			System.out.println("in check user availability.........userid....."+userid+role);
			
			
			
			
			flag=new RegisterMgrDelegate().checkAvailability(userid);
			
			System.out.println("flag is========"+flag);
			
			
			
			if(flag==false){
				
				request.setAttribute("status", UtilConstants._USER_AVAILABLE);
				
				
				
				
			}
			else
			{
				
              request.setAttribute("status", UtilConstants._USER_NO_AVAILABLE);
				
              
				
			}
			}
		catch (Exception e) {
			
			e.printStackTrace();
			
			request.setAttribute("status", "Sorry.. Try again ...");
			
			
			
		}
		RequestDispatcher rd=request.getRequestDispatcher(target);
		rd.forward(request, response);
		
		
	}

	
}

