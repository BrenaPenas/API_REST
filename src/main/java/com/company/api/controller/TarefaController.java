package com.company.api.controller;

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

import com.company.api.entity.Pessoas;
import com.company.api.entity.Tarefa;
import com.company.api.repository.PessoaRepository;
import com.company.api.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

		@Autowired
		private TarefaRepository tarefaRepository;
				
		@Autowired
	    private PessoaRepository pessoaRepository;
		
		@GetMapping
		public List<Tarefa> listar(){
			return tarefaRepository.findAll();
		}
		
		@PostMapping
		public Tarefa adicionarTarefa(@RequestBody Tarefa tarefa) {
			return tarefaRepository.save(tarefa); 
		}
		
		@PutMapping("/alocar/{id}")
		public ResponseEntity<Tarefa> alocarPessoa(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {
		    Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
		    Optional<Pessoas> pessoaExistente = pessoaRepository.findById(novaTarefa.getPessoaAlocada().getId());

		    if (tarefaExistente.isPresent() && pessoaExistente.isPresent()) {
		        Tarefa tarefa = tarefaExistente.get();
		        Pessoas pessoa = pessoaExistente.get();

		        // Verificar se a pessoa e a tarefa pertencem ao mesmo departamento
		        if (tarefa.getDepartamento().equals(pessoa.getDepartamento())) {
		            tarefa.setPessoaAlocada(pessoa);
		            Tarefa tarefaAlocada = tarefaRepository.save(tarefa);
		            return ResponseEntity.ok(tarefaAlocada);
		        } else {
		            return ResponseEntity.badRequest().build();
		        }
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
		
		@PutMapping("/finalizar/{id}")
	    public ResponseEntity<Tarefa> finalizarTarefa(@PathVariable Long id) {
	        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
	        if (tarefaExistente.isPresent()) {
	            Tarefa tarefa = tarefaExistente.get();
	            tarefa.setFinalizado(true);
	            tarefaRepository.save(tarefa);
	            return ResponseEntity.ok(tarefa);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
		
		//Atualiza qualquer outro atributo, basta seta abaixo.
		@PutMapping("/{id}")
		public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
		    Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);

		    if (tarefaExistente.isPresent()) {
		        Tarefa tarefaAtualizada = tarefaExistente.get();
		        tarefaAtualizada.setDepartamento(tarefa.getDepartamento());
		        tarefaRepository.save(tarefaAtualizada);
		        return ResponseEntity.ok(tarefaAtualizada);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}

		//para futuras exclusões
		@DeleteMapping("/{id}")
	    public ResponseEntity<Void> removerTarefa(@PathVariable Long id) {
	        Optional<Tarefa> pessoaExistente = tarefaRepository.findById(id);
	        if (pessoaExistente.isPresent()) {
	            tarefaRepository.delete(pessoaExistente.get());
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
		
		@GetMapping("/pendentes")
		public List<Tarefa> listarTarefasPendentes() {
		    return tarefaRepository.findAllByPessoaAlocadaIsNullOrderByPrazoAsc();
		}

		
		//Criação do delete para remover pessoas da  tarefas, inseridas por engano.
		@DeleteMapping("/{id}/pessoas")
		public ResponseEntity<String> removerPessoaDaTarefa(@PathVariable Long id) {
		    Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);

		    if (tarefaOptional.isPresent()) {
		        Tarefa tarefa = tarefaOptional.get();

		        // Remover a pessoa alocada da tarefa
		        tarefa.setPessoaAlocada(null);
		        tarefaRepository.save(tarefa);

		        return ResponseEntity.ok("Pessoa removida da tarefa com sucesso.");
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}

}

