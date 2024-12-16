package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corsotest")
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(name = "data_inizio")
    private String dataInizio;
    private String durata;
    @ManyToOne
    @JoinColumn(name ="id_docente")
    private Docente docente;

    @ManyToMany
    @JoinTable(name = "discente_corso", joinColumns = @JoinColumn(name="id_corso"),inverseJoinColumns = @JoinColumn(name="id_discente"))
    private List<Discente> discenti=new ArrayList<>();

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
    public Docente getDocente() {return this.docente;}
    public void setDocente(Docente docente) {this.docente = docente;}
    public void aggiungiDiscente(Discente discente) {discenti.add(discente);}

    public List<Discente> getDiscenti() {
        return discenti;
    }
    public void setDiscenti(List<Discente> discenti) {
        this.discenti = discenti;
    }
}
