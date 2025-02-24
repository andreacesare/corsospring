package com.example.demo.entity;


import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "docentetest")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cognome;

    @OneToMany(mappedBy = "docente")
    private List<Corso> corsi;

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
    public List<Corso> getCorsi() { return corsi;}
    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }
    public void addCorso(Corso corso) {
        corsi.add(corso);
    }
}
