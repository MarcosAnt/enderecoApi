package br.com.enderecoApi.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.core.io.InputStreamResource;

import br.com.enderecoApi.entity.CidadeCSV;

/**
 * 
 * @author MarcosAnt
 *
 */

public class BuildCSV {
	
	public InputStreamResource build(ArrayList<CidadeCSV> listaCidades) {
	    String[] csvHeader = {
	            "idEstado",
	            "siglaEstado",
	            "regiaoNome",
	            "nomeCidade",
	            "nomeMesorregiao",
	            "nomeFormatado"
	    };
	
	    List<List<String>> csvBody = new ArrayList<>();
	    
	    for(CidadeCSV cidadeCSV : listaCidades) {
	    	csvBody.add(
	        		Arrays.asList(
        				cidadeCSV.getIdEstado().toString(),
        				cidadeCSV.getNomeCidade(),
        				cidadeCSV.getNomeFormatado(),
        				cidadeCSV.getNomeMesorregiao(),
        				cidadeCSV.getRegiaoNome(),
        				cidadeCSV.getSiglaEstado())
	        		);
	    }
	
	    ByteArrayInputStream byteArrayOutputStream;
	    
	    try (
	            ByteArrayOutputStream out = new ByteArrayOutputStream();
	            CSVPrinter csvPrinter = new CSVPrinter(
	                    new PrintWriter(out),
	                    CSVFormat.DEFAULT.withHeader(csvHeader));
	    ) {
	        for (List<String> record : csvBody)
	            csvPrinter.printRecord(record);
	
	        csvPrinter.flush();
	
	        byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
	    }
	    catch (IOException e) {
	        throw new RuntimeException(e.getMessage());
	    }
	
	    return new InputStreamResource(byteArrayOutputStream);
	}
}
