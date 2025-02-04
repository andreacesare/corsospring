package com.example.demo.Converter;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;


public class CorsoConverter {

    public static CorsoDTO toDTO(Corso corso) {
        DocenteDTO doc=DocenteConverter.toDTO(corso.getDocente()!=null?corso.getDocente():null);
        CorsoDTO corsoDTO=new CorsoDTO();
        corsoDTO.setId(corso.getId());
        corsoDTO.setNome(corso.getNome());
        corsoDTO.setDurata(corso.getDurata());
        corsoDTO.setDocente(doc);
        corsoDTO.setData_inizio(corso.getData_inizio());
        corsoDTO.setDiscenti(corso.getDiscenti().stream().map(DiscenteConverter::toDTO).toList());
        return corsoDTO;
    }

    public static CorsoDTO toDTO2(Corso corso) {
        CorsoDTO corsodto=new CorsoDTO();
        corsodto.setId(corso.getId());
        corsodto.setNome(corso.getNome());
        corsodto.setData_inizio(corso.getData_inizio());
        corsodto.setDurata(corso.getDurata());
        return corsodto;
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
