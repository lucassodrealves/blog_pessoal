package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.blogpessoal.model.Tema;

import com.generation.blogpessoal.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins="*",allowedHeaders="*")
public class TemaController extends Tema {
	
	@Autowired
	private TemaRepository repositoryTema;
	
	
@GetMapping
public ResponseEntity<List<Tema>> getAllTema(Long id){
	
	return  ResponseEntity.ok(repositoryTema.findAll());
	
}

@GetMapping("/{id}")
public ResponseEntity<Tema> getByIdTema(@PathVariable Long id){
	
	return repositoryTema.findById(id)
	.map(resposta -> ResponseEntity.ok(resposta))
	.orElse(ResponseEntity.notFound().build());
}

@GetMapping("/descricao/{descricao}")
public ResponseEntity<List<Tema>> getByDescricaoTema(@PathVariable String descricao){
	return ResponseEntity.ok(repositoryTema.findAllByDescricaoContainingIgnoreCase(descricao));
}

@PostMapping
public ResponseEntity<Tema> postTema(@Valid @RequestBody Tema tema){
	return ResponseEntity.status(HttpStatus.CREATED).body(repositoryTema.save(tema));
}

@PutMapping
public ResponseEntity<Tema> putTema(@Valid @RequestBody Tema tema){
	 return   repositoryTema.findById(tema.getId())
	         .map(resultado -> ResponseEntity.ok(repositoryTema.save(resultado)))
			 .orElse(ResponseEntity.notFound().build());
}

@DeleteMapping("/{id}")
public ResponseEntity<Tema> deleteTema (@PathVariable Long id) {
	repositoryTema.deleteById(id);
	return repositoryTema.findById(id)
	.map(resultado -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultado))
	.orElse(ResponseEntity.notFound().build());
	
	
	
	

	
	
	
	
}



	


}
