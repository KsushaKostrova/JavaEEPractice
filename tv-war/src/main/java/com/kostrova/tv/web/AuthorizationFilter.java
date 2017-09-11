package com.kostrova.tv.web;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

	@Inject
	private LoginView loginView;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// prevent infinite loop
		// prevent issue with requesting resources
		if (request.getRequestURI().contains("/home") || request.getRequestURI().contains("/registration")
				|| request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) {
			chain.doFilter(req, res);
			return;
		}

		HttpSession session = ((HttpServletRequest) req).getSession(false);
		if (session == null || !loginView.getIsAuthenticated()) {
			// user is not logged in, redirect to login page
			response.sendRedirect(request.getContextPath() + "/home.xhtml");
		} else {
			// session is fine, so just continue request
			chain.doFilter(req, res);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// nothing to do
	}

	@Override
	public void destroy() {
		// nothing to do
	}

}
