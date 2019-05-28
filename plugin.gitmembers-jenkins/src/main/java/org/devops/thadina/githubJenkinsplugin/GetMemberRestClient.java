package org.devops.thadina.githubJenkinsplugin;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.devops.thadina.githubJenkinsplugin.model.GitDetailMemberData;
import org.devops.thadina.githubJenkinsplugin.model.MemberAccessLevelData;
import org.devops.thadina.githubJenkinsplugin.util.Constant;
import org.devops.thadina.githubJenkinsplugin.util.ImplException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jenkins.model.GlobalConfiguration;

/**
 * RestServices 
 * @author Thadina* 
 *
 */

public class GetMemberRestClient {
	
	 private HttpClient httpClient;	
     private static final Logger LOGGER = LogManager.getLogger(GetMemberRestClient.class);

   
    /**    
     * 
     * @param globalConfig 
     * @throws ImplException
     */
   	public GetMemberRestClient() throws ImplException {
   				
   		GlobalConfig globalConfig = GlobalConfiguration.all().get(GlobalConfig.class);
        if (!(globalConfig == null || globalConfig.getToken()==null)) {
	        SSLContext sslContext = null;
			try {
					sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
					public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
						return true;
					}
				}).build();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.httpClient = HttpClientBuilder.create().setSSLContext(sslContext).build();
        }
   	}
 
   	/**
   	 * obtem dados os do usuário do gitHUB por ID 
   	 * @param id
   	 * @return dadosMembros
   	 * @throws IOException
   	 * @throws ImplException 
   	 */
    public GitDetailMemberData getMemberByID(final Integer id, final String token) throws IOException, ImplException {
    	GitDetailMemberData gitDetailMemberVO = null;
    	try {
    		ObjectMapper mapper = new ObjectMapper();
        	HttpResponse response = null;
        	
	    	HttpUriRequest request = new HttpGet(Constant.GITLAB_API_URL+"users/"+id);
	    	request.addHeader("Private-Token", token);
	        request.addHeader("ignoreSslErrors","true");
	        request.addHeader("url","urlChamadaDetalheUsuario");
	  	  
	        response = httpClient.execute(request);       
	        
	        if (response.getStatusLine().getStatusCode() != Constant.STATUS_OK) {
	     	   throw new RuntimeException("Erro listando membro");
	     	  }
	        gitDetailMemberVO  = mapper.readValue(response.getEntity().getContent(), GitDetailMemberData.class);
    	}catch (Exception e) {
    		LOGGER.error("GetMemberRestClient.getMemberByID:"+e);
    		throw new ImplException("Erro na chamada do github por id",e);
		}
 	  return  gitDetailMemberVO;
 	 }
    
   /**   
    * getMemberByURL
    * @param URL
    * @return Lista de membros do projeto
    * @throws ImplException
    */
   public  List<MemberAccessLevelData> getMemberByURL(final String URL, final String token) throws ImplException {
    	
    	List<MemberAccessLevelData> listMembers = null;
			    		
		try {
			
			String gitLabNamespace = this.montaURL(URL);
	   		
	   		ObjectMapper mapper = new ObjectMapper();
        	HttpResponse response = null;
        	
        	System.out.println(Constant.GITLAB_API_URL+"projects/"+gitLabNamespace+"/members");
	    	HttpUriRequest request = new HttpGet(Constant.GITLAB_API_URL+"projects/"+gitLabNamespace+"/members");
	        request.addHeader("Private-Token", token);
	        request.addHeader("ignoreSslErrors","true");
	        request.addHeader("url","urlChamadaDetalheUsuario");
	  	  
	        response = httpClient.execute(request);        
	
	        System.out.println(response.getStatusLine().getStatusCode());
	        
	        if (response.getStatusLine().getStatusCode() != Constant.STATUS_OK) {
	     	   throw new RuntimeException("Erro ao listar membro");
	     	  }
	        
	        listMembers  = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<MemberAccessLevelData>>(){});
	        
    	}catch (Exception e) {
    		LOGGER.error("GetMemberRestClient.getMemberByID:"+e);
    		 throw new ImplException("Erro na chamada do github para listar os membros",e);
		}
    		
    	return listMembers;
    }

	/**
	 * @param URL
	 * @param extensao
	 * @param gitLabNamespace
	 * @return URL github
	 */
	private String montaURL(final String URL) {
		String gitLabNamespace ="";
		
		String ambiente =Constant.GITLAB_PIPELINE_HOST;
   		
		if(URL.endsWith(Constant.EXTENSAO)){
			gitLabNamespace = URL.substring(0,URL.length()-Constant.EXTENSAO.length());
		}	
		
		if(gitLabNamespace.startsWith(ambiente)) {
			gitLabNamespace = gitLabNamespace.substring(39,gitLabNamespace.length());
		}
		
		if (gitLabNamespace.length()>0){
			gitLabNamespace = gitLabNamespace.replaceAll("/","%2F");
		}
		return gitLabNamespace;
	}
   
}