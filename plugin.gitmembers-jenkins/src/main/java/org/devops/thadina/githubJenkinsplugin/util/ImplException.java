package org.devops.thadina.githubJenkinsplugin.util;

public class ImplException extends Exception {

	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Throwable exception;
	
	/**
	 * 1 - CRITICA PARA USUÁRIO
	 * -1 - ERRO SISTEMICO
	 */
	private Integer codErro = -1;
	private String msgErro = "";

	/**
	 * Construtor da classe ImplException
	 */
	public ImplException() {
		
	}

	/**
	 * Construtor da classe ImplException
	 *
	 * @param msgErro
	 */
	public ImplException(String msgErro) {
		super(msgErro);
		this.msgErro = msgErro;
	}

	/**
	 * Construtor da classe ImplException
	 *
	 * @param msgErro
	 * @param exception
	 */
	public ImplException(String msgErro, Throwable exception) {
		super(msgErro,exception);
		this.msgErro = msgErro;
		this.exception = exception;
	}
	
	/**
	 * Construtor da classe ImplException
	 *
	 * @param codErro
	 * @param msgErro
	 */
	public ImplException(Integer codErro, String msgErro) {
		super(msgErro);
		this.codErro = codErro;
		this.msgErro = msgErro;
	}
	
	/**
	 * Construtor da classe ImplException
	 *
	 * @param codErro
	 * @param msgErro
	 */
	public ImplException(Integer codErro, String msgErro, Throwable exception) {
		super(msgErro);
		this.codErro = codErro;
		this.msgErro = msgErro;
		this.exception = exception;
	}

	/**
	 * @return o valor do atributo exception
	 */
	public Throwable getException() {
		return exception;
	}

	/**
	 * @param exception o valor a ser atribuído no atributo exception
	 */
	public void setException(Throwable exception) {
		this.exception = exception;
	}

	/**
	 * @return o valor do atributo codErro
	 */
	public Integer getCodErro() {
		return codErro;
	}

	/**
	 * @param codErro o valor a ser atribuído no atributo codErro
	 */
	public void setCodErro(Integer codErro) {
		this.codErro = codErro;
	}

	/**
	 * @return o valor do atributo msgErro
	 */
	public String getMsgErro() {
		return msgErro;
	}

	/**
	 * @param msgErro o valor a ser atribuído no atributo msgErro
	 */
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	public static ImplException error(ImplException msgErro) {
		return msgErro;
	}

}