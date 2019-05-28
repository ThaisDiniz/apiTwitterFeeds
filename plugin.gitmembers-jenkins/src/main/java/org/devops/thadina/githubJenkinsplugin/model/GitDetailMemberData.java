package org.devops.thadina.githubJenkinsplugin.model;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class GitDetailMemberData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String username;
	private String state;
	private String avatar_url;
	private String web_url;
	private String created_at;
	private String bio;
	private String location;
	private String skype;
	private String linkedin;
	private String twitter;
	private String website_url;
	private String organization;
	private String last_sign_in_at;
	private String confirmed_at;
	private String last_activity_on;
	private String email;
	private String theme_id;
	private String color_scheme_id;
	private String projects_limit;
	private String current_sign_in_at;
	@JsonProperty("identities")
	private JsonNode identities ;
	private String can_create_group;
	private String can_create_project;
	private String two_factor_enabled;
	private String external;
	private String is_admin;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public String getWeb_url() {
		return web_url;
	}
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getWebsite_url() {
		return website_url;
	}
	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getLast_sign_in_at() {
		return last_sign_in_at;
	}
	public void setLast_sign_in_at(String last_sign_in_at) {
		this.last_sign_in_at = last_sign_in_at;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getConfirmed_at() {
		return confirmed_at;
	}
	public void setConfirmed_at(String confirmed_at) {
		this.confirmed_at = confirmed_at;
	}
	public String getLast_activity_on() {
		return last_activity_on;
	}
	public void setLast_activity_on(String last_activity_on) {
		this.last_activity_on = last_activity_on;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(String theme_id) {
		this.theme_id = theme_id;
	}
	public String getColor_scheme_id() {
		return color_scheme_id;
	}
	public void setColor_scheme_id(String color_scheme_id) {
		this.color_scheme_id = color_scheme_id;
	}
	public String getProjects_limit() {
		return projects_limit;
	}
	public void setProjects_limit(String projects_limit) {
		this.projects_limit = projects_limit;
	}
	public String getCurrent_sign_in_at() {
		return current_sign_in_at;
	}
	public void setCurrent_sign_in_at(String current_sign_in_at) {
		this.current_sign_in_at = current_sign_in_at;
	}
	public JsonNode getIdentities() {
		return identities;
	}
	public void setIdentities(JsonNode identities) {
		this.identities = identities;
	}
	public String getCan_create_group() {
		return can_create_group;
	}
	public void setCan_create_group(String can_create_group) {
		this.can_create_group = can_create_group;
	}
	public String getCan_create_project() {
		return can_create_project;
	}
	public void setCan_create_project(String can_create_project) {
		this.can_create_project = can_create_project;
	}
	public String getTwo_factor_enabled() {
		return two_factor_enabled;
	}
	public void setTwo_factor_enabled(String two_factor_enabled) {
		this.two_factor_enabled = two_factor_enabled;
	}
	public String getExternal() {
		return external;
	}
	public void setExternal(String external) {
		this.external = external;
	}
	public String getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}
	  
	
	  
	  


}
