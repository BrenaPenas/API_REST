package com.company.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.api.entity.Departamento;
import com.company.api.entity.Pessoas;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoas, Long>{
		
	//A consulta seleciona todas as entidades Pessoas que possuem departamento.
	    @Query("SELECT p FROM Pessoas p WHERE p.departamento = :departamento")
	    List<Pessoas> findByDepartamento(Departamento departamento);

}
