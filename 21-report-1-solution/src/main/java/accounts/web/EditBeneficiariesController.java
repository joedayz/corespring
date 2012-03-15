package accounts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import accounts.Account;
import accounts.BeneficiaryManager;

@Controller
@RequestMapping("/editBeneficiaries.htm")
public class EditBeneficiariesController {
	
	@Autowired
	private BeneficiaryManager beneficiaryManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ModelAttribute("account") Account 
				setupForm(@RequestParam("entityId") Long id) {
		
		return beneficiaryManager.findAccountById(id);
	}

}
