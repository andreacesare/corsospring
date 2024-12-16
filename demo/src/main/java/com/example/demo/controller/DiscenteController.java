package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("discente")
public class DiscenteController {
    private final DiscenteService discenteService;
    public DiscenteController(DiscenteService discenteService) {
        this.discenteService = discenteService;
    }

    @GetMapping("/getDiscenteById/{IdDiscente}")
    public DiscenteDTO getDiscenteById(@PathVariable("IdDiscente") Integer id) {
        return discenteService.getDiscenteById(id);
    }

    @GetMapping("")
    public List<DiscenteDTO> getAllDiscenti() {
        return discenteService.getAllDiscente();
    }

    @PostMapping("/saveDiscente")
    public DiscenteDTO saveDiscente(@RequestBody DiscenteDTO discenteDTO) {
        return discenteService.saveDiscente(discenteDTO);
    }

    @DeleteMapping("/deleteDiscente/{idDiscente}")
    public DiscenteDTO deleteDiscente(@PathVariable("idDiscente") Integer id) {
         return discenteService.deleteDiscente(id);
    }

    @PutMapping("/updateDiscente/{idDiscente}")
    public DiscenteDTO updateDiscente(@PathVariable("idDiscente") Integer id, @RequestBody DiscenteDTO discente) {
        return discenteService.updateDiscente(id,discente);
    }

    @PostMapping("{idDiscente}/addCorso/{idCorso}")
    public DiscenteDTO addCorso(@PathVariable("idDiscente") Integer idDiscente,@PathVariable("idCorso") Integer idCorso) throws Exception{
        return discenteService.addCorso(idDiscente,idCorso);
    }

}
