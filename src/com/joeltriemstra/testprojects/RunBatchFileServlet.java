package com.joeltriemstra.testprojects;

import java.io.*;
import java.lang.ProcessBuilder.Redirect;
import java.util.Properties;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RunBatchFileServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		//temp allow GET for testing
		doPost(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			resp.getWriter().print("Running batch from " + getWorkingDirectory());
		}
		catch (IOException ex)
		{
			//Just making the compiler happy
		}
		
		try
		{
			 ProcessBuilder pb = new ProcessBuilder(getWorkingDirectory() + "\\ci.bat");
			 pb.directory(new File(getWorkingDirectory()));
			 File log = new File(getWorkingDirectory() + "\\log-pull.txt");
			 pb.redirectErrorStream(true);
			 pb.redirectOutput(Redirect.appendTo(log));
			 Process p = pb.start();
		}
		catch (IOException ex)
		{
			try
			{
				resp.getWriter().print(ex.toString());
			}
			catch (IOException ex1)
			{
				//Just making the compiler happy
			}
		}
	}
	
	private String getWorkingDirectory() throws IOException
	{
		InputStream input = getServletContext().getResourceAsStream("/WEB-INF/nocommit.properties");
		Properties properties = new Properties();
		properties.load(input);
	
		return properties.getProperty("workingfolder.escaped");
	}
}
