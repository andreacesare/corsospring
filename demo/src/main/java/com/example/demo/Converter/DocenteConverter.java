package com.example.demo.Converter;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;

public class DocenteConverter {

    public static DocenteDTO toDTO(Docente docente) {
        if (docente!=null) {
            DocenteDTO doc = new DocenteDTO(docente.getid(), docente.getNome(), docente.getCognome());
            return doc;
        } else {
            return null;
        }
    }


    public static Docente toEntity(DocenteDTO dto) {
        Docente docente = new Docente();
        if (dto!=null) {
            if (dto.getid() != null) {
                docente.setid(dto.getid());
            }
            docente.setNome(dto.getNome()!=null?dto.getNome():"");
            docente.setCognome(dto.getCognome()!=null?dto.getCognome():"");
        }
            return docente;

    }




}
