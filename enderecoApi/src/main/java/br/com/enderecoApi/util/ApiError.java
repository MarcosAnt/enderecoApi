package br.com.enderecoApi.util;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author MarcosAnt
 *
 */

public class ApiError {

    private HttpStatus status;
    private String localizedMessage;
    private String message;

    public ApiError(HttpStatus status, String localizedMessage, String message) {
        super();
        this.status = status;
        this.localizedMessage = message;
        this.message = message;
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getLocalizedMessage() {
		return localizedMessage;
	}

	public void setLocalizedMessage(String localizedMessage) {
		this.localizedMessage = localizedMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}