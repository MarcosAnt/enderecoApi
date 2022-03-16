package br.com.enderecoApi.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.enderecoApi.entity.Cidade;
import br.com.enderecoApi.entity.CidadeCSV;
import br.com.enderecoApi.entity.Estado;
import br.com.enderecoApi.util.ApiError;
import br.com.enderecoApi.util.BuildCSV;
import br.com.enderecoApi.util.UtilController;

/**
 * 
 * @author MarcosAnt
 *
 */

@RestController
@RequestMapping("/cidades")
public class CidadeController extends UtilController {

	private RestTemplate restTemplate;
	
	private Logger logger = getLogger(CidadeController.class);
	
	@GetMapping("/listaJSON")
	@SuppressWarnings("unchecked")
	public Object listaCidadesJSON() {
		logger.info("-> Chamada de CidadeController.listaCidadesJSON");
		
		ArrayList<Cidade> listaCidades = new ArrayList<>();
		Collection<Estado> listaUF;
		Collection<?> lista = getListaUF();
		
		if(lista.isEmpty()) {
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, null, "Não foi possível recuperar os dados.");
		}
		else {
			listaUF = (Collection<Estado>) lista;
		}
		
		listaUF.forEach(estado -> {
			ResponseEntity<ArrayList<Cidade>> response = getRestTemplate().exchange(
					WS_LOCALIDADES_ESTADOS + "/" + estado.getId() + "/municipios",
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<ArrayList<Cidade>>() {});
			listaCidades.addAll(response.getBody());
		});
		
		return (new CidadeCSV()).build(listaCidades);
	}
	
	@GetMapping("/listaCSV")
	@SuppressWarnings("unchecked")
	public Object listaCidadesCSV() {
		logger.info("-> Chamada de CidadeController.listaCidadesCSV");
		
		ArrayList<Cidade> listaCidades = new ArrayList<>();
		Collection<Estado> listaUF;
		Collection<?> lista = getListaUF();
		
		if(lista.isEmpty()) {
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, null, "Não foi possível recuperar os dados");
		}
		else {
			listaUF = (Collection<Estado>) lista;
		}
		
		listaUF.forEach(estado -> {
			ResponseEntity<ArrayList<Cidade>> response = getRestTemplate().exchange(
					WS_LOCALIDADES_ESTADOS + "/" + estado.getId() + "/municipios",
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<ArrayList<Cidade>>() {});
			listaCidades.addAll(response.getBody());
		});
		
		ArrayList<CidadeCSV> listaCidadeCSV = (new CidadeCSV()).build(listaCidades);
		
		InputStreamResource fileInputStream = (new BuildCSV()).build(listaCidadeCSV);
		
	    // setting HTTP headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + ARQV_CIDADE_CSV);
	    // defining the custom Content-Type
	    headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

	    return new ResponseEntity<>(
	            fileInputStream,
	            headers,
	            HttpStatus.OK
	    );
	}
	
	@GetMapping("/nome/{nome}")
	@Cacheable("idCidade")
	@SuppressWarnings("unchecked")
	public Object cidadeId(@PathVariable String nome) {
		logger.info("-> Chamada de CidadeController.cidadeId");
		
		Collection<?> lista = getListaUF();
		Collection<Estado> listaUF;
		
		if(lista.isEmpty()) {
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, null, "Não foi possível recuperar a lista de UF.");
		}
		else {
			listaUF = (Collection<Estado>) lista;
		}
		
		return getIdMunicipio(listaUF, nome);
		
	}
	
	public Object getIdMunicipio(Collection<Estado> listaUF, String nomeMunicipio) {
		ArrayList<Cidade> listaCidades = new ArrayList<>();
		
		listaUF.forEach(estado -> {
			ResponseEntity<ArrayList<Cidade>> response = getRestTemplate().exchange(
					WS_LOCALIDADES_ESTADOS + "/" + estado.getId() + "/municipios",
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<ArrayList<Cidade>>() {});
			listaCidades.addAll(response.getBody());
		});
		
		List<Cidade> cidade = listaCidades
							.stream()
							.filter(c -> c.getNome().equalsIgnoreCase(nomeMunicipio))
							.collect(Collectors.toList());
		
		if(cidade.isEmpty()) {
			return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, null, "Não foi possível recuperar as informações da cidade.");
		}
		else {
			return cidade.get(0).getId();
		}
	}
	
	public Collection<?> getListaUF() {
		EstadoController estadoController = new EstadoController();
		
		Object lista = estadoController.listaEstados();
		
		if(lista instanceof RestClientException) {
			logger.error("-> Erro em CidadeController.getListaUF");
			return Collections.EMPTY_LIST;
		}
		else {
			return (Collection<?>) lista;
		}
	}
	
	private RestTemplate getRestTemplate() {
		if(restTemplate == null) {
			restTemplate = new RestTemplate();
		}
		
		return restTemplate;
	}
}
