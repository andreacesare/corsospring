package com.example.demo.service;

import com.example.demo.Converter.CorsoConverter;
import com.example.demo.Converter.DiscenteConverter;
import com.example.demo.Converter.DocenteConverter;
import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CorsoService {
    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;
    private final DiscenteRepository discenteRepository;

    public CorsoService(CorsoRepository corsoRepository, DocenteRepository docenteRepository, DiscenteRepository discenteRepository) {
        this.corsoRepository = corsoRepository;
        this.docenteRepository = docenteRepository;
        this.discenteRepository = discenteRepository;
    }

    public CorsoDTO getCorsoById(Integer id) {
        Corso corso = corsoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Corso con id:"+id+" non trovato"));
        return CorsoConverter.toDTO(corso);
    }

    public List<CorsoDTO> getAllCorsi() {
       return corsoRepository.findAll().stream().map(CorsoConverter::toDTO).collect(Collectors.toList());

    }

    public CorsoDTO saveCorso(CorsoDTO corsoDTO) {
        Corso corso = CorsoConverter.toEntity(corsoDTO);
        Docente docente =docenteRepository.findById(corso.getDocente().getid()).orElseThrow();
        corso.setDocente(docente);
        corsoRepository.save(corso);
        return CorsoConverter.toDTO(corso);
    }

    @Transactional
    public CorsoDTO deleteCorso(int id) {
        Corso corso=corsoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Corso con id:"+id+" non trovato"));
        corsoRepository.deleteById(id);
        return CorsoConverter.toDTO(corso);
    }

    public CorsoDTO updateCorso(Integer id,CorsoDTO corsoDTO) {
        Corso corso=corsoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Corso con id:"+id+" non trovato"));
        if(corsoDTO.getNome()!=null){corso.setNome(corsoDTO.getNome());}
        if(corsoDTO.getDurata()!=null){corso.setDurata(corsoDTO.getDurata());}
        if(corsoDTO.getData_inizio()!=null){corso.setData_inizio(corsoDTO.getData_inizio());}
        if(corsoDTO.getDocente()!=null){corso.setDocente(DocenteConverter.toEntity(corsoDTO.getDocente()));}
        corsoRepository.save(corso);
        return CorsoConverter.toDTO(corso);
    }

    public List<CorsoDTO> getCorsoByDurata(String d){
    List<Corso> corsi =corsoRepository.findCorsoByDurata(d);
    return corsi.stream().map(CorsoConverter::toDTO).collect(Collectors.toList());
    }

    public CorsoDTO addStudente(Integer idDiscente, Integer idCorso) throws Exception{
        Corso corso=corsoRepository.findById(idCorso).orElseThrow(()->new NoSuchElementException("No se encontro el corso"));
        Discente d=discenteRepository.findById(idDiscente).orElseThrow(()->new NoSuchElementException("No se encontro el discente"));
        if(!corso.getDiscenti().contains(d)) {
            corso.getDiscenti().add(d);
            d.getCorsi().add(corso);
            discenteRepository.save(d);
            corsoRepository.save(corso);
            return CorsoConverter.toDTO(corso);
        }
        else throw new Exception("corso gia seguito");
    }

    public CorsoDTO removeStudente(Integer idDiscente, Integer idCorso) {
        Corso corso=corsoRepository.findById(idCorso).orElseThrow();
        Discente discente=discenteRepository.findById(idDiscente).orElseThrow();
        corso.getDiscenti().remove(discente);
        discente.getCorsi().remove(corso);
        corsoRepository.save(corso);
        discenteRepository.save(discente);
        return CorsoConverter.toDTO(corso);
    }

    public List<CorsoDTO> ricerca(String nome,String data,String durata,String docente){
        return corsoRepository.ricerca(nome,data,durata,docente)
                .stream().map(CorsoConverter::toDTO).toList();
    }
}
