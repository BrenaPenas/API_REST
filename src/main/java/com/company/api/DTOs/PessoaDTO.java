package com.company.api.DTOs;

public class PessoaDTO {
	private Long id;
    private String nome;
    private String departamento;
    private int totalHorasGastas;
    
    
	
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
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public int getTotalHorasGastas() {
		return totalHorasGastas;
	}
	public void setTotalHorasGastas(int totalHorasGastas) {
		this.totalHorasGastas = totalHorasGastas;
	}
}
