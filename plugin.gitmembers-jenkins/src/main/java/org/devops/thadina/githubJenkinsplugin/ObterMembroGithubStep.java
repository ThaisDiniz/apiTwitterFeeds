package org.devops.thadina.githubJenkinsplugin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.CheckForNull;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.devops.thadina.githubJenkinsplugin.model.GitDetailMemberData;
import org.devops.thadina.githubJenkinsplugin.model.GitMemberResponseData;
import org.devops.thadina.githubJenkinsplugin.model.MemberAccessLevelData;
import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepDescriptor;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import com.google.common.collect.ImmutableSet;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;

public class ObterMembroGithubStep extends Step implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ValidationStep.class);

	private String URLGithub;

	
	@DataBoundConstructor
	public ObterMembroGithubStep(@CheckForNull String URLGithub) {
		this.URLGithub = URLGithub;
	}
	
	public String getURLGithub() {
		return this.URLGithub;
	}
	
	@DataBoundSetter
	public void setURLGithub(String URLGithub) {
		this.URLGithub = URLGithub;
	}


	@Override
	public StepExecution start(StepContext context) throws Exception {
		return new ObterMembroGithubStepExecution(context, this);
	}
	
	@Extension
	public static class DescriptorImpl extends StepDescriptor {

		@Override
		public Set<? extends Class<?>> getRequiredContext() {
			return ImmutableSet.of();
		}

		@Override
		public String getFunctionName() {
			return "obterMembroGithub";
		}
		
		@Override
		public String getDisplayName() {
			return "Consultar lista de membros do projeto e access_level";
		}
	}
	public static class ObterMembroGithubStepExecution extends StepExecution {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String URLGithub;

		
		ObterMembroGithubStepExecution(StepContext context, ObterMembroGithubStep step) throws Exception {
	        super(context);
	        this.URLGithub = step.URLGithub;	   
	    }

			@Override
			public boolean start(){		
				try {
					
					ValidationStep.validationURL(URLGithub);
					
				
					GlobalConfig globalConfig = GlobalConfiguration.all().get(GlobalConfig.class);
					if (globalConfig == null) {
						throw new IllegalStateException("GlobalConfig not found");
					}
					
					List<GitMemberResponseData> memberAccess = new ArrayList<GitMemberResponseData>();
					
					GetMemberRestClient rest = new GetMemberRestClient();
					List<MemberAccessLevelData> members = rest.getMemberByURL(URLGithub,globalConfig.getToken());
					
					for(MemberAccessLevelData member : members) {
						GitMemberResponseData memberResult = new GitMemberResponseData();
						memberResult.setId(member.getId());
						memberResult.setName(member.getName());
						memberResult.setUsername(member.getUsername());
						memberResult.setAccess_level(member.getAccess_level());						
						GitDetailMemberData memberDetail = rest.getMemberByID(member.getId(),globalConfig.getToken());
						memberResult.setEmail(memberDetail.getEmail());
						memberAccess.add(memberResult);						
					}
					
					getContext().onSuccess(memberAccess);
				} catch (Exception e) {
					LOGGER.error("ObterMembroGithubStep.start:"+e);
					getContext().onFailure(e);
				}
				return true;
			}
		
		  @Override
		    public void stop(Throwable cause) throws Exception {
		        getContext().onFailure(cause);
		    }
			
		    @Override
		    public void onResume() {
		        super.onResume();
		        start();
		    }
		
	}
	
	
	
}