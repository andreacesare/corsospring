package com.example.demo.DTO;

import com.example.demo.entity.Corso;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocenteCorsiDTO {
    private Integer id;
    private String nome;
    private String cognome;
    private List<CorsoDTO> corsi;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setid(int id) {
        this.id = id;
    }

    public Integer getid() {
        return id;
    }
    public void setCorsi(List<CorsoDTO> corsi) {
        this.corsi = corsi;
    }
    public List<CorsoDTO> getCorsi() {
        return corsi;
    }
}
