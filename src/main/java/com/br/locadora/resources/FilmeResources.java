package com.br.locadora.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.locadora.model.Ator;
import com.br.locadora.model.Avaliacao;
import com.br.locadora.model.Filme;
import com.br.locadora.service.FilmeService;

@RestController
@RequestMapping(value = "/filme")
public class FilmeResources {

	@Autowired
	private FilmeService filmeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Filme>> listar() {

		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.MINUTES);

		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(filmeService.listar());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Filme filme) {
		filme = filmeService.salvar(filme);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(filme.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(filmeService.buscar(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		filmeService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Filme filme, @PathVariable("id") Long id) {
		filme.setId(id);
		filmeService.atualizar(filme);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/avaliacao", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarAvaliacao(@PathVariable("id") Long idFilme, @RequestBody Avaliacao avaliacao) {
		filmeService.salvarAvaliacao(idFilme, avaliacao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}/ator", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarAtor(@PathVariable("id") Long idFilme, @RequestBody Ator ator) {
		filmeService.salvarAtor(idFilme, ator);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}

}
