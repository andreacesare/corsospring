package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discentetest")
public class Discente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cognome;
    private String matricola;
    @Column(name = "data_nascita")
    private String dataNascita;

    @ManyToMany(mappedBy = "discenti")
    private List<Corso> corsi=new ArrayList();

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

    public void setid(Integer id) {
        this.id = id;
    }

    public Integer getid() {
        return id;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getMatricola() {
        return matricola;
    }
    public void setData_nascita(String dataNascita) {this.dataNascita = dataNascita;}

    public String getData_nascita() {
        return dataNascita;
    }
    public void addCorso(Corso corso) {
        corsi.add(corso);
        corso.getDiscenti().add(this);
    }
    public void removeCorso(Corso corso) {
        corsi.remove(corso);
        corso.getDiscenti().remove(this);
    }

    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }
    public List<Corso> getCorsi() {
        return corsi;
    }
}
