package com.example.demo.controller;

import com.example.demo.DTO.DocenteCorsiDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.service.DocenteService;
import jakarta.persistence.Entity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("docente")
public class DocenteController {
    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping("/getDocenteById/{idDocente}")
    public DocenteDTO getDocenteById(@PathVariable("idDocente") Integer id) {
        return docenteService.getDocenteById(id);
    }

    @GetMapping("")
    public List<DocenteDTO> getAllDocenti() {
        return docenteService.getAllDocente();
    }

    @GetMapping("/docentiCorsi")
    public List<DocenteCorsiDTO> getDocentiCorsi() {
        return docenteService.getAllDocenteCorso();
    }

    @PostMapping("/saveDocente")
    public DocenteDTO saveDocente(@RequestBody DocenteDTO docenteDTO) {
        return docenteService.saveDocente(docenteDTO);
    }

    @DeleteMapping("/deleteDocente/{idDocente}")
    public DocenteDTO deleteDocente(@PathVariable("idDocente") Integer id ) {
        return docenteService.deleteDocente(id);
    }

    @PutMapping("/updateDocente/{idDocente}")
    public DocenteDTO updateDocente(@PathVariable("idDocente") Integer id, @RequestBody DocenteDTO docenteDTO) {
        return docenteService.updateDocente(id,docenteDTO);
    }
}
