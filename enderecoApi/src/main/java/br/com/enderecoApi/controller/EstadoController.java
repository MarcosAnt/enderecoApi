package br.com.enderecoApi.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.enderecoApi.entity.Estado;
import br.com.enderecoApi.util.ApiError;
import br.com.enderecoApi.util.UtilController;

/**
 * 
 * @author MarcosAnt
 *
 */

@RestController
public class EstadoController extends UtilController {

	private RestTemplate restTemplate;
	
	Logger logger = getLogger(EstadoController.class);
	
	public Object listaEstados() {
		logger.info("-> Chamada de EstadoController.listaEstatos");
		
		try {
			ResponseEntity<ArrayList<Estado>> response = getRestTemplate().exchange(
					WS_LOCALIDADES_ESTADOS, 
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<ArrayList<Estado>>() {});
			
			return response.getBody();
		}
		catch (RestClientException restException) {
			logger.error("-> Erro em EstadoController.listaEstatos: " + restException.getMessage());
			restException.printStackTrace();
			
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, restException.getLocalizedMessage(), restException.getMessage());
			
			return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
		}
	}
	
	private RestTemplate getRestTemplate() {
		if(restTemplate == null) {
			restTemplate = new RestTemplate();
		}
		
		return restTemplate;
	}
	
}
