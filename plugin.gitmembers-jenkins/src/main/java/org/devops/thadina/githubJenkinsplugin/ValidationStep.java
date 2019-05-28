package org.devops.thadina.githubJenkinsplugin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.devops.thadina.githubJenkinsplugin.util.ImplException;

/**
 * ValidationStep validação minina da URL recebida
 * @author thadina
 *
 */
public class ValidationStep {	
	
	private static final Logger LOGGER = LogManager.getLogger(ValidationStep.class);

	/**
	 * metodo para validar a URL
	 * @param url
	 * @throws ImplException
	 */
	public static void validationURL(final String url) throws ImplException {
		LOGGER.info("url:"+url);
		doCheckUrl(url);
	}
	
	public static void doCheckUrl(final String value) throws ImplException {
		if (value == null || value.trim().isEmpty())
			throw new ImplException ("Por favor, insira a url ser valida");		
	}


}