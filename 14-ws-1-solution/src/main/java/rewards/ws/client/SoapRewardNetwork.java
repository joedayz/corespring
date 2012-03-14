package rewards.ws.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.springframework.util.xml.DomUtils;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.AccountContribution.Distribution;

import common.money.MonetaryAmount;
import common.money.Percentage;

public class SoapRewardNetwork extends WebServiceGatewaySupport implements RewardNetwork {

	private static final String NAMESPACE_URI = "http://www.springsource.com/reward-network";

	public RewardConfirmation rewardAccountFor(Dining dining) {
		Element requestElement = prepareRequest(dining);
		DOMSource source = new DOMSource(requestElement);
		DOMResult result = new DOMResult();
		getWebServiceTemplate().sendSourceAndReceiveToResult(source, result);
		return processResponse(result.getNode());
	}

	private Element prepareRequest(Dining dining) {
		Document document = getDocument();
		Element requestElement = document.createElementNS(NAMESPACE_URI, "rewardAccountForDiningRequest");
		requestElement.appendChild(mapDining(document, dining));
		return requestElement;
	}

	private Element mapDining(Document document, Dining dining) {
		Element diningElement = document.createElementNS(NAMESPACE_URI, "dining");
		diningElement.setAttribute("amount", dining.getAmount().toString());
		diningElement.setAttribute("creditCardNumber", dining.getCreditCardNumber());
		diningElement.setAttribute("merchantNumber", dining.getMerchantNumber());
		return diningElement;
	}

	private Document getDocument() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	private RewardConfirmation processResponse(Node node) {
		Element responseElement = (Element) node.getFirstChild();
		return mapRewardConfirmation(DomUtils.getChildElementByTagName(responseElement, "rewardConfirmation"));
	}

	private RewardConfirmation mapRewardConfirmation(Element confirmationElement) {
		String confirmationNumber = confirmationElement.getAttribute("confirmationNumber");
		String accountNumber = confirmationElement.getAttribute("accountNumber");
		String amount = confirmationElement.getAttribute("amount");
		List<Element> distributionElements = DomUtils.getChildElementsByTagName(confirmationElement, "distribution");
		AccountContribution contribution = new AccountContribution(accountNumber, MonetaryAmount.valueOf(amount),
				mapDistributions(distributionElements));
		return new RewardConfirmation(confirmationNumber, contribution);
	}

	private Set<Distribution> mapDistributions(List<Element> distributionElements) {
		Set<Distribution> distributions = new HashSet<Distribution>(distributionElements.size());
		for (Element element : distributionElements) {
			String beneficiary = element.getAttribute("beneficiary");
			String amount = element.getAttribute("amount");
			String percentage = element.getAttribute("percentage");
			String totalSavings = element.getAttribute("totalSavings");
			Distribution distribution = new Distribution(beneficiary, MonetaryAmount.valueOf(amount), Percentage
					.valueOf(percentage), MonetaryAmount.valueOf(totalSavings));
			distributions.add(distribution);
		}
		return distributions;
	}

}
