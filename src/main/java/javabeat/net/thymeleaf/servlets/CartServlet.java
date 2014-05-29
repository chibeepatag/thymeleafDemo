/**
 * 
 */
package javabeat.net.thymeleaf.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javabeat.net.thymeleaf.model.Cart;       
import javabeat.net.thymeleaf.model.Catalog;
import javabeat.net.thymeleaf.model.Dog;
import javabeat.net.thymeleaf.model.Item;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * @author Celine Patag
 * 
 */
public class CartServlet extends HttpServlet {
	/**
	 * Updates Cart, and outputs XML representation of contents
	 * 
	 * @param req
	 * @param res
	 * @throws java.io.IOException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws java.io.IOException {

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
		
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("hat001", "Hat",
				"Stylish bowler hat (SALE!)", new BigDecimal(19.99)));
		items.add(new Item("dog001", "Dog",
				"Chocolate labrador puppy", new BigDecimal(12.15)));
		items.add(new Item("sou001", "Soup",
				"Can of tasty tomato soup", new BigDecimal(1.95)));
		items.add(new Item("cha001", "Chair",
				"Swivelling office chair", new BigDecimal(25.65)));
		items.add(new Item("str001", "String",
				"Metric tonne of bailing twine", new BigDecimal(66.34)));
		items.add(new Item("qua001", "Quark",
				"Everyone's favorite sub-atomic particle", new BigDecimal(76.65)));								
		ctx.setVariable("items", items);
		
		Cart cart = getCartFromSession(req);
		ctx.setVariable("cart", cart.getContents());
		

		// This will be prefixed with /WEB-INF/ and suffixed with .html
		templateEngine.process("cart", ctx, resp.getWriter());
		resp.setContentType("text/html;charset=UTF-8");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 1000);
	}

	/**
	 * Receives get requests and processes it through the post method.
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		// Bounce to post, for debugging use
		// Hit this servlet directly from the browser to see XML
		doPost(req, res);
	}

	/**
	 * This method gets the shopping cart from the session.
	 * 
	 * @param req
	 *            The HttpServletRequest
	 */
	private Cart getCartFromSession(HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		return cart;
	}

	private void removeCartFromSession(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		session.removeAttribute("cart");
	}
}
