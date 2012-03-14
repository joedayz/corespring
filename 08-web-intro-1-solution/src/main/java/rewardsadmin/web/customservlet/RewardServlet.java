package rewardsadmin.web.customservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewardsadmin.app.Reward;
import rewardsadmin.app.RewardLookupService;

/**
 * Servlet for managing rewards.
 * 
 * This servlet is mapped to the "/reward" path and understands several different types of requests:
 * <ul>
 * <li>GET /new: display a form to the user to create a new reward.
 * <li>POST /new: process the user's submission to create the reward.
 * <li>GET /show/${reward.confirmationNumber}: display the details of the reward with the provided confirmation number
 * </li>
 * </ul>
 * A user interface controller. Part of the presentation layer. A client of the application layer.
 */
public class RewardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3036929972925241617L;

	private RewardNetwork rewardNetwork;

	private RewardLookupService rewardLookupService;

	@Override
	public void init() throws ServletException {
		// lookup the bean container hosting the application
		BeanFactory beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		// lookup the entry-point into the rewards application (which is likely owned by another team)
		rewardNetwork = (RewardNetwork) beanFactory.getBean("rewardNetwork");
		// get our reward lookup service
		rewardLookupService = (RewardLookupService) beanFactory.getBean("rewardLookupService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getPathInfo().equals("/new")) {
			// forward to a form JSP to start a new reward dialog
			request.getRequestDispatcher("/WEB-INF/customservlet/reward/new.jsp").forward(request, response);
		} else if (request.getPathInfo().startsWith("/show/")) {
			// locate the reward with the confirmation number provided in the request
			Reward reward = rewardLookupService.lookupReward(extractConfirmationNumber(request));
			request.setAttribute("reward", reward);
			// forward to a JSP to show the reward details
			request.getRequestDispatcher("/WEB-INF/customservlet/reward/show.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		if (request.getPathInfo().equals("/new")) {
			// start of create reward processing - extract submitted user input from the request
			String creditCardNumber = request.getParameter("creditCardNumber");
			String amount = request.getParameter("amount");
			String merchantNumber = request.getParameter("merchantNumber");
			// prepare the input needed by the application service
			Dining dining = Dining.createDining(amount, creditCardNumber, merchantNumber);
			// invoke the application
			RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);
			// redirect to the lookup servlet for displaying the confirmed reward details
			response.sendRedirect("show/" + confirmation.getConfirmationNumber());
		}
	}

	private String extractConfirmationNumber(HttpServletRequest request) {
		int lastSlash = request.getPathInfo().lastIndexOf('/');
		return request.getPathInfo().substring(lastSlash + 1);
	}
}