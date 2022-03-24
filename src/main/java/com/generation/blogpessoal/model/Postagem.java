package com.generation.blogpessoal.model;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tb_postagens") //Crio tabela de nome tb_postagens
public class Postagem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Preencha isso aqui caramba:")
	@Size(min=5,max=99,message="O atributo título e seu tamanho")
	private String titulo;
	
	@NotNull(message="Preencha isso aqui caramba:")
	@Size(min=5,max=999,message="O atributo texto e seu tamanho")
	private String texto;
	
	@UpdateTimestamp
	private LocalDate data;

	public Long pegaId() {
		return id;
	}

	public void colocaId(Long id) {
		this.id = id;
	}

	public String pegaTitulo() {
		return titulo;
	}

	public void colocaTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String pegaTexto() {
		return texto;
	}

	public void colocaTexto(String texto) {
		this.texto = texto;
	}

	public LocalDate pegaData() {
		return data;
	}

	public void colocaData(LocalDate data) {
		this.data = data;
	}
	
	

}
