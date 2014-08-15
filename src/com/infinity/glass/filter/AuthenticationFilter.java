package com.infinity.glass.filter;

import java.io.IOException;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.security.auth.callback.DialogCallbackHandler;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
 
    private ServletContext context;
     
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }
     
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
         
        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);
         
        HttpSession session = req.getSession(false);
        if(session == null && !(uri.endsWith("/#/"))){
            this.context.log("Unauthorized access request");
            res.sendRedirect("login.html");
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }         
    }
 
     
 
    public void destroy() {
        //close any resources here
    }
 
    private class GlassCallbackHandler implements CallbackHandler {

		@Override
		public void handle(Callback[] callbacks) throws IOException,
				UnsupportedCallbackException {
			System.out.println("In handle()");
			
		}
    	
    }
}