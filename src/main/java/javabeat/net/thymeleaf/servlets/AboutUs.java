package javabeat.net.thymeleaf.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javabeat.net.thymeleaf.model.Cart;
import javabeat.net.thymeleaf.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Servlet implementation class AboutUs
 */
public class AboutUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutUs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		// XHTML is the default mode, but we will set it anyway for better
		// understanding of code
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setPrefix("/WEB-INF/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheTTLMs(3600000L);
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		WebContext ctx = new WebContext(req, resp, getServletConfig()
				.getServletContext(), req.getLocale());

		ctx.setVariable("today", Calendar.getInstance());						

		// This will be prefixed with /WEB-INF/ and suffixed with .html
		templateEngine.process("aboutUs", ctx, resp.getWriter());
		resp.setContentType("text/html;charset=UTF-8");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 1000);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
