package com.gerenmedicamentos.controlemedicamentos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // <-- ADICIONE ESTA LINHA
import jakarta.validation.Valid; // <-- E ESTA LINHA

import com.gerenmedicamentos.controlemedicamentos.dto.MedicamentoDTO;
import com.gerenmedicamentos.controlemedicamentos.service.MedicamentoService;
import com.gerenmedicamentos.controlemedicamentos.model.Medicamento; // <-- E ESTA (para o Controller achar a classe)
import java.util.List;

@RestController
@RequestMapping("api/medicamentos")
public class MedicamentoController{
    
    private final MedicamentoService service;

    // Injeção de dependência via construtor
    public MedicamentoController(MedicamentoService service){
        this.service = service;
    }

    // POST - criar
    @PostMapping
    public ResponseEntity<Medicamento> criar(@RequestBody @Valid MedicamentoDTO dto){
        Medicamento novoMedicamento = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMedicamento);
    }

    // GET - listar todos
    @GetMapping
    public ResponseEntity<List<Medicamento>> listar(){
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET - buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> buscar(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> atualizar (@PathVariable Long id,
         @RequestBody @Valid MedicamentoDTO dto){
            try{
            Medicamento atualizado = service.atualizar(id, dto);
                return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Deletar (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}