package com.example.demo.DTO;

import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class CorsoDTO {

    private Integer id;
    private String nome;
    private String dataInizio;
    private String durata;;
    private DocenteDTO docente;

    public CorsoDTO() {}

    public CorsoDTO(Integer id, String nome, String dataInizio, String durata, DocenteDTO docente) {
        this.id = id;
        this.nome = nome;
        this.dataInizio = dataInizio;
        this.durata = durata;
        this.docente = docente;
    }


     public CorsoDTO(Integer id, String nome, String dataInizio, String durata) {
         this.id = id;
         this.nome = nome;
         this.dataInizio = dataInizio;
         this.durata = durata;

     }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {this.nome = nome;}
    public String getData_inizio() {
        return this.dataInizio;
    }
    public void setData_inizio(String data_inizio) {this.dataInizio = data_inizio;}
    public String getDurata() {return this.durata;}
    public void setDurata(String durata) {this.durata = durata;}
    public Integer getId() {return this.id;}
    public void setId(int id) {this.id = id;}
    public DocenteDTO getDocente() {return this.docente;}
    public void setDocente(DocenteDTO docente) {this.docente = docente;}


}