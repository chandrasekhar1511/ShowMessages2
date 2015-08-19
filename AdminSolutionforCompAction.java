package com.aca.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aca.bean.ComplaintBean;
import com.aca.delegate.RegisterMgrDelegate;
import com.aca.exception.ConnectionException;

public class AdminSolutionforCompAction extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session=request.getSession();
		
		
		   String loginid=(String)session.getAttribute("user");
				
		
		
		boolean flag=false;
     String target="";
     System.out.println("111");
		
		ComplaintBean cb=new ComplaintBean();
		System.out.println("2");
		//Map map=request.getParameterMap();
		System.out.println("3");
		
		System.out.println("4");
		 try {
			 RegisterMgrDelegate rmd=new RegisterMgrDelegate();
			 System.out.println("5");
			// BeanUtils.populate(rb, map);
			 
			cb.setCompid(request.getParameter("compid"));
			 cb.setUserid(loginid);
			 cb.setSolution(request.getParameter("sol"));
			
			
			 
			 
			 System.out.println("5...1");
			 flag=rmd.postComplaintSolution(cb);
			 System.out.println("5......2");
			 
			 
			 if(flag){
				 
					
						target="./jsps/adminhome.jsp";
					
				       request.setAttribute("status","Complaint Solved ");
			}
			else {
				 System.out.println("5......4");
				 target="./jsps/adminhome.jsp";
					
					request.setAttribute("status", "Complaint Not Solved...due to some problem. Try later");
					
			}
			 
			
		} 
		
		 
		 catch (ConnectionException ce) {
			 System.out.println("8");
			throw new ServletException("Connection Failed");
			
		}
		    
		
		RequestDispatcher rd=request.getRequestDispatcher(target);
		rd.forward(request, response);
		
	}

}

