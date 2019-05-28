package org.devops.thadina.githubJenkinsplugin;


import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import hudson.util.FormValidation;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;

@Extension
public class GlobalConfig extends GlobalConfiguration {
	
	private String token;

    public GlobalConfig() { 
        load();       
    }


    public FormValidation doCheckToken(@QueryParameter String value) {
		if (value == null || value.trim().isEmpty())
			return FormValidation.error("Por favor, insira o token para acessar o github do projeto");
		return FormValidation.ok();
	}

		
    @Override
    public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
        req.bindJSON(this, json);
        save();
        return true;
    }


	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}


	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
    


}
