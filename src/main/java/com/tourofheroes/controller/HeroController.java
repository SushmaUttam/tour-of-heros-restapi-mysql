package com.tourofheroes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourofheroes.entity.Hero;
import com.tourofheroes.exception.ResourceNotFoundException;
import com.tourofheroes.repository.HeroRepository;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

	@Autowired
	private HeroRepository heroRepository;
	
	//get all heroes
	@GetMapping
	public List<Hero> getAllHeroes() {
		return this.heroRepository.findAll();
	}
	
	//get hero by id
	@GetMapping("/{id}")
	public Hero getHeroById(@PathVariable ("id") int heroId) {
		return this.heroRepository.findById(heroId)
				.orElseThrow(() -> new ResourceNotFoundException("Hero not found by id: " + heroId));
	}
	
	//create hero
	@PostMapping
	public Hero addHero(@RequestBody Hero hero) {
		return this.heroRepository.save(hero);
	}
	
	//update hero
	@PutMapping("/{id}")
	public Hero updateHero(@RequestBody Hero hero, @PathVariable ("id") int heroId) {
		Hero existingHero = this.heroRepository.findById(heroId)
				.orElseThrow(() -> new ResourceNotFoundException("Hero not found by id: " + heroId));
		existingHero.setName(hero.getName());
		return this.heroRepository.save(existingHero);
	}
	
	//delete hero
	@DeleteMapping("/{id}")
	public ResponseEntity<Hero> deleteHero(@PathVariable ("id") int heroId) {
		Hero existingHero = this.heroRepository.findById(heroId)
				.orElseThrow(() -> new ResourceNotFoundException("Hero not found by id: " + heroId));
		this.heroRepository.delete(existingHero);
		return ResponseEntity.ok().build();
	}
	
}
