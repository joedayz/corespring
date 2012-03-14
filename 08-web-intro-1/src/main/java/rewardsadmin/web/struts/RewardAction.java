package rewardsadmin.web.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;

/**
 * A Struts Action responsible for creating new rewards. This action depends on the {@link RewardNetwork} to carry out
 * this creation.
 * 
 * The {@link RewardNetwork} dependency is resolved using dependency lookup against the root Spring web application
 * context. Struts-managed actions are unable to participate in dependency injection as they are not managed by a
 * dependency injection container like Spring.
 */
@SuppressWarnings("deprecation")
public class RewardAction extends ActionSupport {

	private RewardNetwork rewardNetwork;

	@Override
	protected void onInit() {
		// lookup the reward network needed to create new rewards
		rewardNetwork = (RewardNetwork) getWebApplicationContext().getBean("rewardNetwork");
	}

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RewardActionForm rewardForm = (RewardActionForm) actionForm;
		// prepare input into the application service
		Dining dining = rewardForm.createDining();
		// invoke the application
		RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);
		// redirect to reward show action to display the confirmed reward
		return new ActionForward("show/" + confirmation.getConfirmationNumber(), true);
	}
}