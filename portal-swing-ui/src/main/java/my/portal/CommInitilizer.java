package my.portal;

import my.portal.common.controllers.CDI;
import my.portal.controllers.impl.SwingHttpClientConfig;

public class CommInitilizer {

	public static void initializeCommLibrary() {
		SwingHttpClientConfig config = new SwingHttpClientConfig();
		config.setServiceUrl("http://localhost/portal-web/exec");
		CDI.registerLookup(SwingHttpClientConfig.class, config);
	}
}
