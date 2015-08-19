package com.aca.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.bean.RegisterBean;
import com.aca.delegate.RegisterMgrDelegate;
import com.aca.exception.ConnectionException;

public class AddLocalBodyAction extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean flag=false;
        String path="";
        System.out.println("111");
		RegisterBean rb=new RegisterBean();
		System.out.println("2");
		//Map map=request.getParameterMap();
		System.out.println("3");
		
		System.out.println("4");
		 try {
			 RegisterMgrDelegate rmd=new RegisterMgrDelegate();
			 System.out.println("5");
			
			 String name=request.getParameter("name");
			 rb.setLocalbodyname(request.getParameter("name"));
			 rb.setDistrict(request.getParameter("dist"));
			 rb.setState(request.getParameter("state"));
			 
			 
			 rb.setCity(request.getParameter("city"));
			
			
			 
			 rb.setPhoto(request.getParameter("photo"));
			 System.out.println("5...1");
			 flag=rmd.insertLocalBody(rb);
			 System.out.println("5......2"+flag+name);
			
			
		} 
		
		 
		 catch (ConnectionException ce) {
			 System.out.println("8");
			throw new ServletException("Connection Failed");
			
		}
		    
		if(flag){
			 System.out.println("5......3");
			path="./jsps/adminhome.jsp";
			request.setAttribute("status", "Added successfull");
		}
		else {
			 System.out.println("5......4");
			path="./jsps/adminhome.jsp";
		}
		RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
