package rewardsadmin.web.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
 * A Spring MVC controller for managing reward entities on behalf of authorized
 * user requests. This controller encapsulates all reward controller logic in
 * distinct handler methods. Each handler method performs a distinct user
 * interface action and is mapped to a URL by convention. The URL mapping
 * conventions are:
 * 
 * <ul>
 * <li>GET /reward/new -> calls {@link #newForm()}
 * to setup and render a form to create a new reward.
 * <li>POST /reward/new -> calls {@link #create(RewardForm, BindingResult)} to
 * create the new reward entity and save it to the database.
 * <li>GET /reward/show/${confirmationNumber} -> calls {@link #show(HttpServletRequest, ModelMap)
 * to display the details about the reward with the confirmation number.
 * </ul>
 * 
 * To carry out its actions, this controller depends on services in the
 * application layer. These services are injected into this controller using
 * constructor-dependency injection.
 */
@Controller
@RequestMapping("/reward/*/**")
public class RewardController {

	private static final String FORM_VIEW = "reward/new";
	private static final String SHOW_VIEW = "reward/show";
	private static final String REDIRECT_SHOW_VIEW = "redirect:show";

	/**
	 * The reward network application responsible for creating rewards.
	 */
	@Autowired
	private RewardNetwork rewardNetwork;

	/**
	 * Responsible for retrieving details on previously confirmed rewards.
	 */
	@Autowired
	private RewardLookupService rewardLookupService;

    /**
     * Configures the data binder by setting required fields and registering custom
     * property editors to be used for display and binding of custom types
     * @param binder the data binder to configure
     */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
		binder.setRequiredFields(new String[] { "creditCardNumber", "amount", "merchantNumber" });
		binder.registerCustomEditor(MonetaryAmount.class, new MonetaryAmountEditor());
		binder.registerCustomEditor(SimpleDate.class, new SimpleDateEditor());
    }	
	
	/**
	 * Prepares the "new reward" form for display before it is rendered, then
	 * selects it for display. Preparation logic consists of registering a
	 * custom property editor to properly format initial form field values.
	 * @return a selection of the "new reward" view with the data needed to
	 * render it
	 */
	@RequestMapping(value="/reward/new", method = RequestMethod.GET)
	public ModelAndView newForm() {
		RewardForm rewardForm = new RewardForm();
		WebDataBinder binder = new WebDataBinder(rewardForm, "rewardForm");
		initBinder(binder);
		return new ModelAndView(FORM_VIEW, binder.getBindingResult().getModel());
	}

	/**
	 * Creates a new reward by binding incoming data in the POST request to a
	 * {@link RewardForm} backing object, performing type conversion and
	 * validation, then invoking the configured {@link RewardNetwork}.
	 * 
	 * If there are data binding errors, this action method will select the new
	 * reward form again to allow the user to correct their errors.
	 * @param rewardForm the bound reward form
	 * @param result the binding result object
	 * @return on error(s), the form view logical name, forcing the user back to the
	 * form to correct the error(s). On success, a redirect to the show method of this
	 * controller to display confirm reward details
	 */
	@RequestMapping(value="/reward/new", method = RequestMethod.POST)
	public String create(@ModelAttribute("rewardForm") RewardForm rewardForm, BindingResult result) {
		if (result.hasErrors()) {
			return FORM_VIEW;
		} else {
			Dining dining = rewardForm.createDining();
			RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);
			return REDIRECT_SHOW_VIEW + "/" + confirmation.getConfirmationNumber();
		}
	}

	/**
	 * Displays details about a previously confirmed reward. View preparation
	 * logic consists of installing a data binder the view will use to render
	 * formatted reward values such as amounts and dates.
	 * @param request the request
	 * @param model the model map into which the reward object will be stored
	 * @return a model and view containing the show view name and the reward object
	 */
	@RequestMapping(value="/reward/show/*", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request, ModelMap model) {
		Reward reward = rewardLookupService.lookupReward(extractConfirmationNumber(request));
		WebDataBinder binder = new WebDataBinder(reward, "reward");
		initBinder(binder);
		return new ModelAndView(SHOW_VIEW, binder.getBindingResult().getModel());
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