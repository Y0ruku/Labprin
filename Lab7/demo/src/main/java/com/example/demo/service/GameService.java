package com.example.demo.service;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;

	public List<Game> listAll() {
		return repository.findAll();
	}

	public Optional<Game> get(Long id) {
		return repository.findById(id);
	}

	public Game save(Game game) {
		return repository.save(game);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
