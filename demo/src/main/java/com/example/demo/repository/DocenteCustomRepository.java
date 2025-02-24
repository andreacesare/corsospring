package com.example.demo.repository;

import com.example.demo.entity.Docente;


import java.util.List;


public interface DocenteCustomRepository {

    List<Docente> ricerca(String query);



}
