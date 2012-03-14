package rewardsadmin.web.springmvc20;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.DataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewardsadmin.app.Reward;
import rewardsadmin.app.RewardLookupService;

import common.datetime.SimpleDate;
import common.datetime.SimpleDateEditor;
import common.money.MonetaryAmount;
import common.money.MonetaryAmountEditor;

/**
 * A Spring MVC controller for managing reward entities on behalf of authorized user requests.
 * This controller is a MultiActionController, encapsulating all reward controller logic in
 * distinct handler methods. Each handler method performs a distinct user interface action
 * and is mapped to a URL by convention. The URL mapping conventions are:
 * 
 * <ul>
 * <li>GET /reward/new -> calls {@link #newForm(HttpServletRequest, HttpServletResponse)}
 * to setup and render a form to create a new reward.
 * <li>POST /reward/new -> calls {@link #create(HttpServletRequest, HttpServletResponse)}
 * to create the new reward entity and save it to the database.
 * <li>GET /reward/show/${confirmationNumber} -> calls {@link #show(HttpServletRequest, HttpServletResponse)}
 * to display the details about the reward with the confirmation number.
 * </ul>
 * 
 * To carry out its actions, this controller depends on services in the application layer. These services are injected
 * into this controller using constructor-dependency injection.
 */
public class RewardController extends MultiActionController {

	/**
	 * The reward network application responsible for creating rewards.
	 */
	private RewardNetwork rewardNetwork;

	/**
	 * Responsible for retrieving details on previously confirmed rewards.
	 */
	private RewardLookupService rewardLookupService;

	/**
	 * Creates a reward controller.
	 * @param rewardNetwork application responsible for creating rewards
	 * @param rewardLookupService responsible for retrieving historical
	 * information on previously confirmed rewards.
	 */
	public RewardController(RewardNetwork rewardNetwork, RewardLookupService rewardLookupService) {
		this.rewardNetwork = rewardNetwork;
		this.rewardLookupService = rewardLookupService;
		// resolves the request to a method on this controller class
		setMethodNameResolver(new PathBasedMethodNameResolver());
	}

	/**
	 * Prepares the "new reward" form for display before it is rendered, then
	 * selects it for display. Preparation logic consists of registering a
	 * custom property editor to properly format initial form field values.
	 * @param request the request
	 * @param response the response
	 * @return a selection of the "new reward" view with the data needed to render it
	 */
	public ModelAndView newForm(HttpServletRequest request, HttpServletResponse response) {
		RewardForm rewardForm = new RewardForm();
		DataBinder binder = new DataBinder(rewardForm, "rewardForm");
		// installs a type converter to convert from String to MonetaryAmount during form data binding
		binder.registerCustomEditor(MonetaryAmount.class, new MonetaryAmountEditor());
		return new ModelAndView("reward/new", binder.getBindingResult().getModel());
	}

	/**
	 * Creates a new reward by binding incoming data in the POST request to a
	 * {@link RewardForm} backing object, performing type conversion and
	 * validation, then invoking the configured {@link RewardNetwork}.
	 * 
	 * If there are data binding errors, this action method will select the new
	 * reward form again to allow the user to correct their errors.
	 * @param request the request
	 * @param response the response
	 * @return on error, a selection of the "reward/new" containing the errors
	 * the user will need to correct; on success, a redirect to the "reward/show"
	 * controller to display confirmed reward details
	 */
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
		RewardForm rewardForm = new RewardForm();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(rewardForm, "rewardForm");
		// enforces these fields are present in the form
		binder.setRequiredFields(new String[] { "creditCardNumber", "amount", "merchantNumber" });
		binder.registerCustomEditor(MonetaryAmount.class, new MonetaryAmountEditor());
		// copies parameters in the request to the reward form
		binder.bind(request);
		if (binder.getBindingResult().hasErrors()) {
			return new ModelAndView("reward/new", binder.getBindingResult().getModel());
		} else {
			Dining dining = rewardForm.createDining();
			RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);
			return new ModelAndView("redirect:show/" + confirmation.getConfirmationNumber());
		}
	}

	/**
	 * Displays details about a previously confirmed reward. View preparation logic consists of installing a data binder
	 * the view will use to render formatted reward values such as amounts and dates.
	 * @param request the request
	 * @param response the response
	 * @return a selection of the "show" view to render the reward
	 */
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
		Reward reward = rewardLookupService.lookupReward(extractConfirmationNumber(request));
		DataBinder binder = new DataBinder(reward, "reward");
		binder.registerCustomEditor(MonetaryAmount.class, new MonetaryAmountEditor());
		binder.registerCustomEditor(SimpleDate.class, new SimpleDateEditor());
		return new ModelAndView("reward/show", binder.getBindingResult().getModel());
	}

	/**
	 * Simple little helper method that extracts the confirmation number from the HTTP request path.
	 * @param request the request e.g. with a URL like /reward/1
	 * @return the confirmation number e.g. 1
	 */
	private String extractConfirmationNumber(HttpServletRequest request) {
		int lastSlash = request.getPathInfo().lastIndexOf('/');
		return request.getPathInfo().substring(lastSlash + 1);
	}
}