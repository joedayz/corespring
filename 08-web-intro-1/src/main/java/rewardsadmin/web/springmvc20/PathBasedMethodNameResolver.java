package rewardsadmin.web.springmvc20;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.util.UrlPathHelper;

/**
 * Helper that maps a HTTP request to a handler method name based on conventions. The conventions are based on the HTTP
 * method (GET or POST) and the request path portion of the request URL.
 * 
 * The implemented mapping conventions are:
 * <ul>
 * <li>A GET request to <code>${controller}/</code> maps to a method named <code>index</code>
 * <li>A GET request to <code>${controller}/new</code> maps to a method named <code>newForm</code>
 * <li>A POST request to <code>${controller}/new</code> maps to a method named <code>create</code>
 * <li>Any other GET request to <code>${controller}/${action}</code> maps to a method named <code>${action}</code>
 * <li>Any other type of request results in a UnsupportedOperationException.
 * </ul>
 * 
 * If the request URL ends in the <code>${controller}/${action}/<code> format and the action has data prepended
 * as additional request path elements, the <code>_dataRequestPath</code> request attribute will be set exposing
 * this data in an unparsed form.
 */
public class PathBasedMethodNameResolver implements MethodNameResolver {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	/**
	 * Set if URL lookup should always use full path within current servlet context. Else, the path within the current
	 * servlet mapping is used if applicable (i.e. in the case of a ".../*" servlet mapping in web.xml). Default is
	 * "false".
	 * @see org.springframework.web.util.UrlPathHelper#setAlwaysUseFullPath
	 */
	public void setAlwaysUseFullPath(boolean alwaysUseFullPath) {
		this.urlPathHelper.setAlwaysUseFullPath(alwaysUseFullPath);
	}

	/**
	 * Set if context path and request URI should be URL-decoded. Both are returned <i>undecoded</i> by the Servlet
	 * API, in contrast to the servlet path.
	 * <p>
	 * Uses either the request encoding or the default encoding according to the Servlet spec (ISO-8859-1).
	 * <p>
	 * Note: Setting this to "true" requires JDK 1.4 if the encoding differs from the VM's platform default encoding, as
	 * JDK 1.3's URLDecoder class does not offer a way to specify the encoding.
	 * @see org.springframework.web.util.UrlPathHelper#setUrlDecode
	 */
	public void setUrlDecode(boolean urlDecode) {
		this.urlPathHelper.setUrlDecode(urlDecode);
	}

	/**
	 * Set the UrlPathHelper to use for resolution of lookup paths.
	 * <p>
	 * Use this to override the default UrlPathHelper with a custom subclass, or to share common UrlPathHelper settings
	 * across multiple MethodNameResolvers and HandlerMappings.
	 * @see org.springframework.web.servlet.handler.AbstractUrlHandlerMapping#setUrlPathHelper
	 */
	public void setUrlPathHelper(UrlPathHelper urlPathHelper) {
		this.urlPathHelper = urlPathHelper;
	}

	/**
	 * Retrieves the URL path to use for lookup and delegates to <code>getHandlerMethodNameForUrlPath</code>.
	 * Converts <code>null</code> values to NoSuchRequestHandlingMethodExceptions.
	 * @see #getHandlerMethodNameForUrlPath
	 */
	public final String getHandlerMethodName(HttpServletRequest request) throws NoSuchRequestHandlingMethodException {
		String urlPath = this.urlPathHelper.getLookupPathForRequest(request);
		String name = getHandlerMethodNameForUrlPath(request, urlPath);
		if (name == null) {
			throw new NoSuchRequestHandlingMethodException(request);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Returning handler method name '" + name + "' for lookup path: " + urlPath);
		}
		return name;
	}

	protected String getHandlerMethodNameForUrlPath(HttpServletRequest request, String urlPath) {
		urlPath = urlPath.substring(1);
		int nextSlash = urlPath.indexOf('/');
		if (nextSlash == -1 || nextSlash == (urlPath.length() - 1)) {
			if ("GET".equals(request.getMethod())) {
				return "index";
			} else {
				throw new UnsupportedOperationException("Not supported request method POST for " + urlPath);
			}
		}
		urlPath = urlPath.substring(nextSlash + 1);
		nextSlash = urlPath.indexOf('/');
		if (nextSlash != -1) {
			urlPath = urlPath.substring(0, nextSlash);
			if ((urlPath.length() - 1) > nextSlash) {
				request.setAttribute("_dataRequestPath", urlPath.substring(nextSlash + 1));
			}
		}
		if ("GET".equals(request.getMethod())) {
			if (urlPath.equals("new")) {
				return "newForm";
			} else {
				// could assert not one of POST only methods
				return urlPath;
			}
		} else if ("POST".equals(request.getMethod())) {
			if (urlPath.equals("new")) {
				return "create";
			} else {
				// could assert not one of GET only methods
				return urlPath;
			}
		}
		throw new UnsupportedOperationException("Not supported request URL " + urlPath);
	}
}