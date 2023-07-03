package com.company.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.api.entity.Departamento;
import com.company.api.entity.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
	
	//Não está limitado a número de tarefas, retorna as mais atrasadas e sem alguém alocado. 
	List<Tarefa> findAllByPessoaAlocadaIsNullOrderByPrazoAsc();
	
	//A consulta seleciona todas as entidades Tarefa que possuem departamento.
	@Query("SELECT p FROM Tarefa p WHERE p.departamento = :departamento")
	 List<Tarefa> findByDepartamento(Departamento departamento);

}
