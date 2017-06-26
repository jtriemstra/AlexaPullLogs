package com.joeltriemstra.testprojects;

import java.io.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HelloWorldServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			resp.getWriter().print("Hello world");
		}
		catch (IOException ex)
		{
			//Just making the compiler happy
		}
	}
}
