package com.cc.redis.secondkill;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Servlet implementation class SecondKillServlet
 */
public class SecondKillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SecondKillService secondKillService = new SecondKillService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondKillServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prdId = request.getParameter("prdId");
		boolean result = secondKillService.secondKill(prdId);
		response.getWriter().append(result + "");
	}
	

}
