package rewardsadmin.web.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import rewardsadmin.app.Reward;
import rewardsadmin.app.RewardLookupService;

import common.datetime.SimpleDateEditor;
import common.money.MonetaryAmountEditor;

/**
 * A Struts Action responsible fo looking up details about previously confirmed rewards. This action depends on the
 * {@link RewardLookupService} to carry out this lookup.
 * 
 * The {@link RewardLookupService} dependency is resolved using dependency lookup against the root Spring web
 * application context. Struts-managed actions are unable to participate in dependency injection as they are not managed
 * by a dependency injection container like Spring.
 * 
 * The returned Reward is wrapped in a {@link FormattedReward} helper that format reward fields for display by the view.
 * As Struts provides limited formatting capabilities for strongly-typed properties, this is the action's responsiblity.
 */
@SuppressWarnings("deprecation")
public class RewardLookupAction extends ActionSupport {

	private RewardLookupService rewardLookupService;

	@Override
	protected void onInit() {
		// lookup the reward lookup service needed to retrieve previously confirmed rewards
		rewardLookupService = (RewardLookupService) getWebApplicationContext().getBean("rewardLookupService");
	}

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Reward reward = rewardLookupService.lookupReward(extractConfirmationNumber(request));
		request.setAttribute("reward", new FormattedReward(reward));
		return actionMapping.findForward("show");
	}

	private String extractConfirmationNumber(HttpServletRequest request) {
		int lastSlash = request.getPathInfo().lastIndexOf('/');
		return request.getPathInfo().substring(lastSlash + 1);
	}

	/**
	 * A trivial wrapper around a Reward record that formats values such as the "rewardAmount" and "rewardDate" for
	 * display.
	 */
	public static class FormattedReward {

		private Reward reward;

		private MonetaryAmountEditor amountEditor;

		private SimpleDateEditor dateEditor;

		public FormattedReward(Reward reward) {
			amountEditor = new MonetaryAmountEditor();
			amountEditor.setValue(reward.getAmount());
			dateEditor = new SimpleDateEditor();
			dateEditor.setValue(reward.getDate());
			this.reward = reward;
		}

		public String getConfirmationNumber() {
			return reward.getConfirmationNumber();
		}

		public String getAccountNumber() {
			return reward.getAccountNumber();
		}

		public String getMerchantNumber() {
			return reward.getMerchantNumber();
		}

		public String getDate() {
			return dateEditor.getAsText();
		}

		public String getAmount() {
			return amountEditor.getAsText();
		}
	}
}