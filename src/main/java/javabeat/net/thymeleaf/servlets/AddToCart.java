package javabeat.net.thymeleaf.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javabeat.net.thymeleaf.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCart
 */
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  Enumeration<String> headers = req.getHeaderNames();
		    while (headers.hasMoreElements()) {
		      String header  =(String) headers.nextElement();
		      System.out.println(header+": "+req.getHeader(header));
		    }

		    Cart cart = getCartFromSession(req);

		    String action = req.getParameter("action");
		    String item = req.getParameter("item");
		    
		    if ((action != null)) {

		      if ("add".equals(action)) {
		        cart.addItem(item);

		      } else if ("remove".equals(action)) {
		        cart.removeItem(item);

		      }
		    }

		    String cartJSon = cart.toJSon();
		    res.setContentType("application/json");
		    res.getWriter().write(cartJSon);
	}
	
	  private Cart getCartFromSession(HttpServletRequest req) {

		    HttpSession session = req.getSession(true);
		    Cart cart = (Cart)session.getAttribute("cart");
		   
		    if (cart == null) {
		      cart = new Cart();
		      session.setAttribute("cart", cart);
		    }

		    return cart;
		 }

}
