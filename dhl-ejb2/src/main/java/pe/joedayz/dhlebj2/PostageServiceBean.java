package pe.joedayz.dhlebj2;

import javax.ejb.CreateException;

import org.springframework.ejb.support.AbstractStatelessSessionBean;

public class PostageServiceBean extends AbstractStatelessSessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1065706300653579273L;
	private PostageService postageService;

	protected void onEjbCreate() throws CreateException {
		postageService = (PostageService) getBeanFactory().getBean(
				"postageService");
	}

	public double calculatePostage(String country, double weight) {
		return postageService.calculatePostage(country, weight);
	}

}
