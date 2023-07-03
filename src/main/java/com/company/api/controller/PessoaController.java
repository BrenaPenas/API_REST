package com.company.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.api.DTOs.PessoaDTO;
import com.company.api.entity.Pessoas;
import com.company.api.entity.Tarefa;
import com.company.api.repository.PessoaRepository;
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
    @Autowired
    private PessoaRepository pessoaRepository; 
    
    @PostMapping
    public Pessoas adicionarPessoa(@RequestBody Pessoas pessoas) {
        return pessoaRepository.save(pessoas);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Pessoas> alterarPessoa(@PathVariable Long id, @RequestBody Pessoas pessoa) {
        Optional<Pessoas> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            Pessoas pessoaAtualizada = pessoaExistente.get();
            pessoaAtualizada.setNome(pessoa.getNome());
            pessoaAtualizada.setDepartamento(pessoa.getDepartamento());
            pessoaRepository.save(pessoaAtualizada);
            return ResponseEntity.ok(pessoaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPessoa(@PathVariable Long id) {
        Optional<Pessoas> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            pessoaRepository.delete(pessoaExistente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public List<PessoaDTO> listarPessoas() {
        List<Pessoas> pessoas = pessoaRepository.findAll();
        List<PessoaDTO> pessoaDTOs = new ArrayList<>();

        for (Pessoas pessoa : pessoas) {
            PessoaDTO pessoaDTO = new PessoaDTO();
            pessoaDTO.setId(pessoa.getId());
            pessoaDTO.setNome(pessoa.getNome());
            pessoaDTO.setDepartamento(pessoa.getDepartamento().getTitulo());

            int totalHorasGastas = calcularTotalHorasGastas(pessoa);
            pessoaDTO.setTotalHorasGastas(totalHorasGastas);

            pessoaDTOs.add(pessoaDTO);
        }

        return pessoaDTOs;
    }
    
    private int calcularTotalHorasGastas(Pessoas pessoa) {
        List<Tarefa> tarefas = pessoa.getListaDeTarefas();
        int totalHorasGastas = 0;

        for (Tarefa tarefa : tarefas) {
            if (tarefa.isFinalizado()) {
                totalHorasGastas += tarefa.getDuracao();
            }
        }

        return totalHorasGastas;
    }
    
    
}
