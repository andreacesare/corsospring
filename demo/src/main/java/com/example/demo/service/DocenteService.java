package com.example.demo.service;

import com.example.demo.Converter.DocenteConverter;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;



@Service
public class DocenteService {


    private final DocenteRepository docenteRepository;
    public final CorsoRepository corsoRepository;

    public DocenteService(DocenteRepository docenteRepository,CorsoRepository corsoRepository) {
        this.docenteRepository = docenteRepository;
        this.corsoRepository=corsoRepository;
    }

    public DocenteDTO getDocenteById(int id) {
        Docente docente=docenteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Docente con ID " + id + " non trovato."));
        return DocenteConverter.toDTO(docente);
    }

    public List<DocenteDTO> getAllDocente() {
        List<Docente> docenti=docenteRepository.findAll();
        List<DocenteDTO> docdto=new ArrayList<>();
        for(Docente docente:docenti){
            docdto.add(DocenteConverter.toDTO(docente));
        }
        return docdto;
    }

    public DocenteDTO saveDocente(DocenteDTO docenteDTO) {
        Docente docente=DocenteConverter.toEntity(docenteDTO);
        docente=docenteRepository.save(docente);

        return DocenteConverter.toDTO(docente);
    }

    public DocenteDTO deleteDocente(int id) {

        Docente docente=docenteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Docente con ID " + id + " non trovato."));
       if(docente.getCorsi()!=null){
            for(Corso corso:docente.getCorsi()) {
            corso.setDocente(null);
            corsoRepository.save(corso);
            }
       }
        docenteRepository.deleteById(id);


        return DocenteConverter.toDTO(docente);
    }

    public DocenteDTO updateDocente(Integer id,DocenteDTO docenteDTO) {
        Docente docente=docenteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Docente non trovato"));
        if(docenteDTO.getNome()!=null){docente.setNome(docenteDTO.getNome());}
        if(docenteDTO.getCognome()!=null){docente.setCognome(docenteDTO.getCognome());}
        Docente doc=docenteRepository.save(docente);
        return DocenteConverter.toDTO(doc);
    }

    public List<DocenteDTO> ricerca(String text){
        return docenteRepository.ricerca(text).stream().map(DocenteConverter::toDTO).toList();
    }


}
