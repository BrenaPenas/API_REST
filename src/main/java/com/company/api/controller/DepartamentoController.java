package com.company.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.api.DTOs.DepartamentoDTO;
import com.company.api.entity.Departamento;
import com.company.api.entity.Pessoas;
import com.company.api.entity.Tarefa;
import com.company.api.repository.DepartamentoRepository;
import com.company.api.repository.PessoaRepository;
import com.company.api.repository.TarefaRepository;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

	@Autowired
    private DepartamentoRepository departamentoRepository;
    
    @Autowired
	private TarefaRepository tarefaRepository;
    
    @Autowired
    private PessoaRepository pessoaRepository;

    public DepartamentoController(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }
    
    @PostMapping
    public ResponseEntity<Departamento> adicionarDepartamento(@RequestBody Departamento departamento) {
        Departamento novoDepartamento = departamentoRepository.save(departamento);
        return ResponseEntity.ok(novoDepartamento);
    }
    
    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> listarDepartamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        List<DepartamentoDTO> departamentosDTO = new ArrayList<>();

        for (Departamento departamento : departamentos) {
            List<Pessoas> pessoas = pessoaRepository.findByDepartamento(departamento);
            List<Tarefa> tarefas = tarefaRepository.findByDepartamento(departamento);

            DepartamentoDTO departamentoDTO = new DepartamentoDTO();
            departamentoDTO.setId(departamento.getId());
            departamentoDTO.setTitulo(departamento.getTitulo());
            departamentoDTO.setQuantidadePessoas(pessoas.size());
            departamentoDTO.setQuantidadeTarefas(tarefas.size());

            departamentosDTO.add(departamentoDTO);
        }

        return ResponseEntity.ok(departamentosDTO);
    }


}

