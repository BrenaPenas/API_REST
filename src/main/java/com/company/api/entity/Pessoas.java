package com.company.api.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "pessoas")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pessoas{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "iddepartamento")
    private Departamento departamento;

    @OneToMany(mappedBy = "pessoaAlocada", cascade = CascadeType.ALL)
    private List<Tarefa> listaDeTarefas;
    
    
	public List<Tarefa> getListaDeTarefas() {
		return listaDeTarefas;
	}
	public void setListaDeTarefas(List<Tarefa> listaDeTarefas) {
		this.listaDeTarefas = listaDeTarefas;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(departamento, id, nome);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoas other = (Pessoas) obj;
		return Objects.equals(departamento, other.departamento) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
	

}
