package com.example.demo.DTO;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class DocenteDTO {
    private Integer id;
    private String nome;
    private String cognome;
    private List<CorsoDTO> corsi = new ArrayList<>();

    public DocenteDTO() {}

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



