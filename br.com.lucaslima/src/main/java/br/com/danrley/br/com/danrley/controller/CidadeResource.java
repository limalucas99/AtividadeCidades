package br.com.danrley.br.com.danrley.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.danrley.br.com.danrley.model.bean.Cidade;
import br.com.danrley.br.com.danrley.model.repository.CidadeRepository;

@RestController
@RequestMapping ("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@GetMapping ("/lista")
	public List <Cidade> obterCidades (){
		return cidadeRepo.findAll();
	}

	
	@PostMapping ("/salvar")
	//@ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<Cidade> salvar (@RequestBody Cidade cidade, 
								HttpServletResponse response) {
		System.out.println("passou aqui...");
		Cidade c = cidadeRepo.save(cidade);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentServletMapping().
				path("/{id}").
				buildAndExpand(c.getId()).toUri();
		//response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(c);
	}
	
	@GetMapping ("/{id}")
	public Cidade obterCidade (@PathVariable Long id) {
		return cidadeRepo.findById(id).get();
	}
	
	@GetMapping ("/busca/{i}")
	public List <Cidade> obterCidadesPorInicio (@PathVariable String i){
		List<Cidade> lista = cidadeRepo.findAll();
		ArrayList<Cidade> listafiltrada = new ArrayList<Cidade>();
		for(Cidade c: lista) {
			if((c.getNome().toUpperCase().startsWith(i.toUpperCase()))) {
				//dando bug pq o S fica minusculo quando digito na URL
				listafiltrada.add(c);
			}
		}
		return listafiltrada;
	}
	
	@GetMapping ("/buscando/{lat}&{longi}")
	public List <Cidade> obterCidadesPorLatAndLong (@PathVariable Integer lat,@PathVariable Integer longi){
		List<Cidade> lista = cidadeRepo.findAll();
		ArrayList<Cidade> listafiltrada = new ArrayList<Cidade>();
		for(Cidade c: lista) {
			if(c.getLatitude().equals(lat) && c.getLongitude().equals(longi)) {
				
				//dando bug pq o S fica minusculo quando digito na URL
				listafiltrada.add(c);
			}
		}
		return listafiltrada;
	}
	
}
