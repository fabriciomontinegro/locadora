package com.br.locadora.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.locadora.exception.FilmeNotFoundException;
import com.br.locadora.exception.NaoAfetadoException;
import com.br.locadora.model.Ator;
import com.br.locadora.model.Avaliacao;
import com.br.locadora.model.Filme;
import com.br.locadora.repository.AtorRepository;
import com.br.locadora.repository.AvaliacaoRepository;
import com.br.locadora.repository.FilmeRepository;

@Service
public class FilmeService {
	
	@Autowired
    private FilmeRepository filmeRepository;
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	private AtorRepository atorRepository;

    public List<Filme> listar() {
        return filmeRepository.findAll();
    }

    public Filme buscar(Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if(filme.isPresent()){
            return filme.get();
        }
        throw new FilmeNotFoundException("Filme Nao Enconrtado");
    }

    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }
    
    public void deletar(Long id) {
        try {
        	filmeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new FilmeNotFoundException("Filme Nao Encontrado");
        }
    }

    public void atualizar(Filme filme) {
        verificarExistencia(filme);
        filmeRepository.save(filme);
    }
    
    public Avaliacao salvarAvaliacao(Long idFilme, Avaliacao avaliacao) {
    	if (avaliacao.getNota() < 0 || avaliacao.getNota() > 4) {
    		throw new NaoAfetadoException("Nota da Avaliação invalida!");
    	}
    	Filme filme = buscar(idFilme);
    	avaliacao.setFilme(filme);
    	avaliacao.setData(LocalDateTime.now());
    	return avaliacaoRepository.save(avaliacao);
    }
    
    public Ator salvarAtor(Long idFilme, Ator ator) {
    	Filme filme = buscar(idFilme);
    	ator.setFilme(filme);
    	return atorRepository.save(ator);
    }


    private void verificarExistencia(Filme filme) {
        buscar(filme.getId());
    }
}
