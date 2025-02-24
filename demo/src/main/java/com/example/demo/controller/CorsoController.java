package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.service.CorsoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("corso")
public class
CorsoController {
    private final CorsoService corsoService;
    public CorsoController(CorsoService corsoService) {
        this.corsoService = corsoService;
    }

    @GetMapping("/getCorsoById/{idCorso}")
    public CorsoDTO getCorsoById(@PathVariable("idCorso") Integer id) {
        return corsoService.getCorsoById(id);
    }

    @GetMapping("")
    public List<CorsoDTO> getAllCorsi() {
        return corsoService.getAllCorsi();
    }

    @PostMapping("/saveCorso")
    public CorsoDTO saveCorso(@RequestBody CorsoDTO corsoDTO) {
        return corsoService.saveCorso(corsoDTO);
    }

    @DeleteMapping("/deleteCorso/{idCorso}")
    public CorsoDTO deleteCorso(@PathVariable("idCorso") Integer id) {
        return corsoService.deleteCorso(id);
    }

    @PutMapping("/updateCorso/{idCorso}")
    public CorsoDTO updateCorso(@PathVariable("idCorso") Integer id, @RequestBody CorsoDTO corsoDTO) {
        return corsoService.updateCorso(id, corsoDTO);
    }

    @GetMapping("/getCorsoByDurata")
    public List<CorsoDTO> getCorsoByDurata(@RequestParam("durata") String durata) {
        return corsoService.getCorsoByDurata(durata);
    }

    @PostMapping("{idCorso}/addStudente/{idDiscente}")
    public CorsoDTO addStudente(@PathVariable("idDiscente") Integer idDiscente, @PathVariable("idCorso") Integer idCorso) throws Exception{
        return corsoService.addStudente(idDiscente,idCorso);
    }

    @PostMapping("{idCorso}/removeStudente/{idDiscente}")
    public CorsoDTO removeStudente(@PathVariable("idDiscente") Integer idDiscente, @PathVariable("idCorso") Integer idCorso) {
        return corsoService.removeStudente(idDiscente,idCorso);
    }

    @GetMapping("/ricerca")
    public List<CorsoDTO> ricerca(
            @RequestParam(required = false) String nome,@RequestParam(required = false) String data,
            @RequestParam(required = false) String durata,@RequestParam(required = false) String docente){
        return corsoService.ricerca(nome, data, durata, docente);

    }
}
