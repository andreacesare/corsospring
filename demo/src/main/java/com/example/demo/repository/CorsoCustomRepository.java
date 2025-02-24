package com.example.demo.repository;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;

import java.util.List;

public interface CorsoCustomRepository {

    List<Corso> ricerca(String nome, String data, String durata, String docente);
}
