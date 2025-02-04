package com.example.demo.Converter;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteCorsiDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;

import java.util.List;

public class DocenteConverter {

    public static DocenteDTO toDTO(Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        if(docente.getid()!=null){docenteDTO.setid(docente.getid());}
        if(docente.getNome()!=null){docenteDTO.setNome(docente.getNome());}
        if(docente.getCognome()!=null){docenteDTO.setCognome(docente.getCognome());}
        if(docente.getCorsi()!=null){
            List<CorsoDTO> corsi=docente.getCorsi().stream().map(CorsoConverter::toDTO2).toList();
            docenteDTO.setCorsi(corsi);
        }

        return docenteDTO;
    }

    public static DocenteCorsiDTO DocCorsitoDTO(Docente docente) {
        DocenteCorsiDTO doc=new DocenteCorsiDTO();
        if (docente!=null) {
            doc.setid(docente.getid());
            doc.setNome(docente.getNome());
            doc.setCognome(docente.getCognome());
            List<CorsoDTO> lista=docente.getCorsi().stream().map(CorsoConverter::toDTO2).toList();
            doc.setCorsi(lista);
        }
        return doc;
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
