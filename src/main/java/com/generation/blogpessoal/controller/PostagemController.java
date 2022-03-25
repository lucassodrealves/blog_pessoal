package com.generation.blogpessoal.controller;


import java.util.List;
import java.util.Optional;

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

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;



@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins="*",allowedHeaders="*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> pegaTudo(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	//Optional<Postagem> resposta=postagemRepository.findById(id);
			/*if(resposta.isPresent())
			 return ResponseEntity.ok(resposta)
			 else
			 return ResponseEntity.notFound().build();
			 */
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		
		
		 
		
		
				
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
				
		
		
		 
		
		
				
	}
	@PostMapping
	public ResponseEntity<Postagem> postItem(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
		
	}
	
	
	/*@PutMapping
	public ResponseEntity<Postagem> putItem(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(respo -> ResponseEntity.ok(respo))
				.orElse(ResponseEntity.notFound().build());}*/
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Postagem> putItem(@Valid @RequestBody Postagem postagem, @PathVariable Long id){
		Optional<Postagem>resposta=postagemRepository.findById(id);
		if(resposta.isPresent()) {
			
			return ResponseEntity.ok(postagemRepository.save(postagem));
			
			
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		
		
	}}
				
		

	/*@PutMapping
	public ResponseEntity<Postagem> putItem(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.ok(postagemRepository.save(postagem));}*/
		
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Postagem> deletarPostagem(@PathVariable Long id) {
		Optional<Postagem> resposta=postagemRepository.findById(id);
		if(resposta.isPresent()) {
			
    postagemRepository.deleteById(id);
	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	
		}
		else {
			
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
			
			
		}
		
		

	}
	
}
