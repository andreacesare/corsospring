package com.example.demo.service;
import com.example.demo.Converter.CorsoConverter;
import com.example.demo.Converter.DiscenteConverter;
import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DiscenteService {
    private final DiscenteRepository discenteRepository;
    private final CorsoRepository corsoRepository;

    public DiscenteService(DiscenteRepository discenteRepository, CorsoRepository corsoRepository){
        this.discenteRepository=discenteRepository;
        this.corsoRepository = corsoRepository;
    }

    public DiscenteDTO getDiscenteById(int id){
        Discente discente=discenteRepository.findById(id).orElseThrow(()->new NoSuchElementException("No se encontro el discente"));
        return DiscenteConverter.toDTO(discente);
    }

    public List<DiscenteDTO> getAllDiscente(){
        List<Discente> discenti=discenteRepository.findAll();
        List<DiscenteDTO> lista=discenti.stream().map(DiscenteConverter::toDTO).toList();
        return lista;
    }

    public DiscenteDTO saveDiscente(DiscenteDTO discenteDTO){
        Discente discente=DiscenteConverter.toEntity(discenteDTO);
        if(discenteDTO.getCorsi()!=null) {
            List<CorsoDTO> corsidto = discenteDTO.getCorsi();
            List<Integer> corsiid = corsidto.stream().map(CorsoDTO::getId).toList();
            List<Corso> corsi = corsoRepository.findAllById(corsiid);
            discente.setCorsi(corsi);
            corsi.forEach(c->c.getDiscenti().add(discente));
        }
        discenteRepository.save(discente);
        return DiscenteConverter.toDTO(discente);
    }


    public DiscenteDTO deleteDiscente(int id){
        Discente discente=discenteRepository.findById(id).orElseThrow(()->new NoSuchElementException("No se encontro el discente"));
        DiscenteDTO dto=DiscenteConverter.toDTO(discente);
        if(discente.getCorsi()!=null) {
            for (Corso c : discente.getCorsi()) {
                c.getDiscenti().remove(discente);
            }
        }
        discenteRepository.deleteById(id);
        return dto;
    }

    public DiscenteDTO updateDiscente(Integer id,DiscenteDTO discenteDTO){
        Discente discente=discenteRepository.findById(id).orElseThrow(()->new NoSuchElementException("No se encontro el discente"));
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setData_nascita(discenteDTO.getData_nascita());
        discente.setMatricola(discenteDTO.getMatricola());
        discenteRepository.save(discente);
        return DiscenteConverter.toDTO(discente);
        
    }

    public DiscenteDTO addCorso(Integer idDiscente,Integer idCorso) throws Exception{
        Corso corso=corsoRepository.findById(idCorso).orElseThrow(()->new NoSuchElementException("No se encontro el corso"));
        Discente d=discenteRepository.findById(idDiscente).orElseThrow(()->new NoSuchElementException("No se encontro el discente"));
        if(!corso.getDiscenti().contains(d)) {
            corso.getDiscenti().add(d);
            d.getCorsi().add(corso);
            discenteRepository.save(d);
            corsoRepository.save(corso);
            return DiscenteConverter.toDTO(d);
        }
        else throw new Exception("corso gia seguito");
    }
}


