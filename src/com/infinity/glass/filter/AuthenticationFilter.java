package com.infinity.glass.filter;

import java.io.IOException;

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
        
        try {
			LoginContext lc = new LoginContext("glass");
			lc.login();
			System.out.println(lc);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        if(session == null && !(uri.endsWith("/#/"))){
            this.context.log("Unauthorized access request");
//            res.sendRedirect("/glass/#/main");
			chain.doFilter(request, response);
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
         
         
    }
 
     
 
    public void destroy() {
        //close any resources here
    }
 
}