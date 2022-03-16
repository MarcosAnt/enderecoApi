package br.com.enderecoApi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author MarcosAnt
 *
 */

public class UtilController {

	protected static final String WS_LOCALIDADES_ESTADOS = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
	protected static final String ARQV_CIDADE_CSV = "lista-cadades.csv";
	
	public Logger getLogger(Class<?> pClass) {
		return LoggerFactory.getLogger(pClass);
	}
	
}
