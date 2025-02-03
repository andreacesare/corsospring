package com.example.demo.Converter;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;

public class CorsoConverter {

    public static CorsoDTO toDTO(Corso corso) {
        DocenteDTO doc=DocenteConverter.toDTO(corso.getDocente()!=null?corso.getDocente():null);
        return new CorsoDTO(corso.getId(), corso.getNome(), corso.getData_inizio(),corso.getDurata(),doc);
    }

    public static CorsoDTO toDTO2(Corso corso) {
        CorsoDTO corsod=new CorsoDTO();
        corsod.setId(corso.getId());
        corsod.setNome(corso.getNome());
        corsod.setData_inizio(corso.getData_inizio());
        corsod.setDurata(corso.getDurata());
        return corsod;
    }

    public static Corso toEntity(CorsoDTO corsoDTO) {
        Corso corso = new Corso();
        if(corsoDTO.getId() != null) corso.setId(corsoDTO.getId());
        corso.setNome(corsoDTO.getNome());
        corso.setData_inizio(corsoDTO.getData_inizio());
        corso.setDurata(corsoDTO.getDurata());
        corso.setDocente(DocenteConverter.toEntity(corsoDTO.getDocente()));
        return corso;


    }

}
