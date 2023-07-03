package com.company.api.DTOs;

public class DepartamentoDTO {
	
	private Long id;
	private String titulo;
    private int quantidadePessoas;
    private int quantidadeTarefas;
    
    
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
	public int getQuantidadePessoas() {
		return quantidadePessoas;
	}
	public void setQuantidadePessoas(int quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}
	public int getQuantidadeTarefas() {
		return quantidadeTarefas;
	}
	public void setQuantidadeTarefas(int quantidadeTarefas) {
		this.quantidadeTarefas = quantidadeTarefas;
	}
}
