package rewards.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;

/**
 * Accepts HTTP requests from browser user agents to reward for dining. Maps HTTP requests to application operations.
 * 
 * A controller. Part of the presentation layer. A client of the application layer.
 */
public class RewardsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5038314165401848504L;
	private RewardNetwork rewardNetwork;

	@Override
	public void init() throws ServletException {
		// lookup the bean container hosting the application
		BeanFactory beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		// lookup the entry-point into the application, our reward network
		rewardNetwork = (RewardNetwork) beanFactory.getBean("rewardNetwork");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String creditCardNumber = request.getParameter("creditCardNumber");
		String amount = request.getParameter("amount");
		String merchantNumber = request.getParameter("merchantNumber");

		// prepare input into the application service
		Dining dining = Dining.createDining(amount, creditCardNumber, merchantNumber);

		// expose in "request scope" for other resources to access
		request.setAttribute("dining", dining);

		try {
			// invoke application
			RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);
			// expose in "request scope" for other resources to access
			request.setAttribute("rewardConfirmation", confirmation);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rewardConfirmation.jsp");
			// forward to a confirmation JSP resource for rendering
			dispatcher.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rewardError.jsp");
			// forward to an error JSP resource for rendering
			dispatcher.forward(request, response);
		}
	}
}