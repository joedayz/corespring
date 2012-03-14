package rewards.ws;

import org.springframework.util.xml.DomUtils;
import org.springframework.ws.server.endpoint.AbstractDomPayloadEndpoint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.AccountContribution.Distribution;

public class RewardNetworkEndpoint extends AbstractDomPayloadEndpoint {

	private static final String NAMESPACE_URI = "http://www.springsource.com/reward-network";

	private RewardNetwork rewardNetwork;

	public RewardNetworkEndpoint(RewardNetwork rewardNetwork) {
		this.rewardNetwork = rewardNetwork;
	}

	@Override
	protected Element invokeInternal(Element requestElement, Document document) throws Exception {
		Element diningElement = DomUtils.getChildElementByTagName(requestElement, "dining");
		Dining dining = mapDining(diningElement);
		RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);
		Element responseElement = document.createElementNS(NAMESPACE_URI, "rewardAccountForDiningResponse");
		responseElement.appendChild(mapRewardConfirmation(document, confirmation));
		return responseElement;
	}

	private Dining mapDining(Element diningElement) {
		// TODO 3: Extract attributes from diningElement to create a Dining object using the Dining.createDining()
		// method
		return null;
	}

	private Element mapRewardConfirmation(Document document, RewardConfirmation confirmation) {
		Element confirmationElement = document.createElementNS(NAMESPACE_URI, "rewardConfirmation");
		confirmationElement.setAttribute("confirmationNumber", confirmation.getConfirmationNumber());
		confirmationElement.setAttribute("accountNumber", confirmation.getAccountContribution().getAccountNumber());
		confirmationElement.setAttribute("amount", confirmation.getAccountContribution().getAmount().toString());
		for (Distribution distribution : confirmation.getAccountContribution().getDistributions()) {
			Element distributionElement = document.createElementNS(NAMESPACE_URI, "distribution");
			distributionElement.setAttribute("beneficiary", distribution.getBeneficiary());
			distributionElement.setAttribute("amount", distribution.getAmount().toString());
			distributionElement.setAttribute("percentage", distribution.getPercentage().toString());
			distributionElement.setAttribute("totalSavings", distribution.getTotalSavings().toString());
			confirmationElement.appendChild(distributionElement);
		}
		return confirmationElement;
	}
}