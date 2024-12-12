package com.example.demo.service;

import com.example.demo.Converter.DocenteConverter;
import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteCorsiDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.demo.Converter.DocenteConverter.toDTO;

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
        List<DocenteDTO> docdto=new ArrayList<DocenteDTO>();
        for(Docente docente:docenti){
            DocenteConverter.toDTO(docente);
            docdto.add(DocenteConverter.toDTO(docente));
        }
        return docdto;
    }
    public List<DocenteCorsiDTO> getAllDocenteCorso() {
        List<Docente> docenti=docenteRepository.findAll();
        List<DocenteCorsiDTO> doc=docenti.stream().map(DocenteConverter::DocCorsitoDTO).toList();
        return doc;
    }
    public DocenteDTO saveDocente(DocenteDTO docenteDTO) {
        Docente docente=DocenteConverter.toEntity(docenteDTO);
        docente=docenteRepository.save(docente);

        return DocenteConverter.toDTO(docente);
    }

    public DocenteDTO deleteDocente(int id) {

        Docente docente=docenteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Docente con ID " + id + " non trovato."));
        DocenteDTO docdto=DocenteConverter.toDTO(docente);

        return docdto;
    }

    public DocenteDTO updateDocente(Integer id,DocenteDTO docenteDTO) {
        Docente docente=docenteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Docente non trovato"));
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
        Docente doc=docenteRepository.save(docente);
        return DocenteConverter.toDTO(doc);
    }

}
