package com.company.api.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "tarefa")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tarefa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarefa_generator")
    @SequenceGenerator(name = "tarefa_generator", sequenceName = "tarefa_sequence", initialValue = 1001, allocationSize = 1)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "titulo")
	private String titulo;
	
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "prazo")
    @JsonFormat(pattern = "dd/MM/yyyy")
	private Date prazo;

	@ManyToOne
	@JoinColumn(name = "iddepartamento")
	private Departamento departamento;
	
	@Column(name = "duracao")
	private Integer duracao;

	@ManyToOne
	@JoinColumn(name = "idpessoa")
	private Pessoas pessoaAlocada;
	
	@Column(name = "finalizado")
	private boolean finalizado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getPrazo() {
		return prazo;
	}
	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}
	public Pessoas getPessoaAlocada() {
		return pessoaAlocada;
	}
	public void setPessoaAlocada(Pessoas pessoaAlocada) {
		this.pessoaAlocada = pessoaAlocada;
	}

	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Integer getDuracao() {
		return duracao;
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	

	
}
