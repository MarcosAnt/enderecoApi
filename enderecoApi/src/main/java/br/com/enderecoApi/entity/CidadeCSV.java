package br.com.enderecoApi.entity;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author MarcosAnt
 *
 */

public class CidadeCSV {

	Logger logger = LoggerFactory.getLogger(CidadeCSV.class);

    private Long idEstado;
    private String siglaEstado;
    private String regiaoNome;
    private String nomeCidade;
    private String nomeMesorregiao;
    private String nomeFormatado;

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getRegiaoNome() {
        return regiaoNome;
    }

    public void setRegiaoNome(String regiaoNome) {
        this.regiaoNome = regiaoNome;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getNomeMesorregiao() {
        return nomeMesorregiao;
    }

    public void setNomeMesorregiao(String nomeMesorregiao) {
        this.nomeMesorregiao = nomeMesorregiao;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

    public void setNomeFormatado(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }

    public ArrayList<CidadeCSV> build(ArrayList<Cidade> cidades ){
        logger.info("-> Chamada de CidadeCSV.build(cidades)");

        ArrayList<CidadeCSV> listaCidadeCSV = new ArrayList<>();

        cidades.forEach(cidade -> {
        	CidadeCSV cidadeCSV = new CidadeCSV();
            cidadeCSV.setIdEstado(cidade.getMicrorregiao().getMesorregiao().getUF().getId());
            cidadeCSV.setSiglaEstado(cidade.getMicrorregiao().getMesorregiao().getUF().getSigla());
            cidadeCSV.setRegiaoNome(cidade.getMicrorregiao().getMesorregiao().getUF().getRegiao().getNome());
            cidadeCSV.setNomeCidade(cidade.getNome());
            cidadeCSV.setNomeMesorregiao(cidade.getMicrorregiao().getMesorregiao().getNome());
            cidadeCSV.setNomeFormatado(cidadeCSV.getNomeCidade() + "/" + cidadeCSV.getSiglaEstado());

            listaCidadeCSV.add(cidadeCSV);
        });

        return listaCidadeCSV;
    }
}
