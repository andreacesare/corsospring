package com.example.demo.Converter;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;

import java.util.List;

public class DiscenteConverter {

    public static DiscenteDTO toDTO(Discente discente) {
        DiscenteDTO dto = new DiscenteDTO();
        if (discente.getid()!=null) dto.setid(discente.getid());
        dto.setNome(discente.getNome());
        dto.setCognome(discente.getCognome());
        dto.setMatricola(discente.getMatricola());
        dto.setData_nascita(discente.getData_nascita());
        if(discente.getCorsi()!=null) {
            List<CorsoDTO> corsi = discente.getCorsi().stream().map(CorsoConverter::toDTO2).toList();
            dto.setCorsi(corsi);
        }
        return dto;
    }

    public static Discente toEntity(DiscenteDTO dto) {
        Discente discente = new Discente();
        if(dto.getid()!=null) discente.setid(dto.getid());
        discente.setNome(dto.getNome());
        discente.setCognome(dto.getCognome());
        discente.setMatricola(dto.getMatricola());
        discente.setData_nascita(dto.getData_nascita());
        if(dto.getCorsi()!=null) {
            List<Corso> corsi = dto.getCorsi().stream().map(CorsoConverter::toEntity).toList();
            discente.setCorsi(corsi);
        }
        return discente;

    }


}
