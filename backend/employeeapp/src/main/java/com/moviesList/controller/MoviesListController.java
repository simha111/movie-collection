package com.moviesList.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviesList.model.MoviesList;
import com.moviesList.repository.MoviesRepository;

/**
 * @author Bodicherla Narasimha Rao
 * This Class is a controller which consists of methods for Creating/Deleting movie data
 * and getting the list of movie data available.
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})

public class MoviesListController {
	
	@Autowired
	MoviesRepository moviesRepository;
	private Long deleteId;

	
	/**
	 * @returns the list of movies data available in the system.
	 */
	@GetMapping("/moviesList")
	public List<MoviesList> getMoviesList() {
		return moviesRepository.findAll();
	}

	/**
	 * @param employee
	 * @saves the movie data created by the end user.
	 */
	@PostMapping("/createMovieData")
	public MoviesList createMovieInfo(@Valid @RequestBody MoviesList employee) {
		return moviesRepository.save(employee);
	}


	/**
	 * @param movieId
	 * @deletes the selected movies list from the database.
	 */
	@DeleteMapping("/deleteMovieData/{id}")
	public ResponseEntity<MoviesList> deleteTodoItem(@PathVariable(value = "id") String movieId) {
		if(movieId.contains(",")){
		  String id[]=movieId.split(",");
		  for (int i = 0; i < id.length; i++) { 
			 deleteId=Long.parseLong(id[i]);
			 MoviesList moviesList = moviesRepository.findOne(deleteId);
				if (moviesList == null) {
					return ResponseEntity.notFound().build();
				}
				moviesRepository.delete(moviesList);
		  }
		}else{
			 deleteId=Long.parseLong(movieId);
			 MoviesList moviesList = moviesRepository.findOne(deleteId);
				if (moviesList == null) {
					return ResponseEntity.notFound().build();
				}
				moviesRepository.delete(moviesList);
		}
		return ResponseEntity.ok().build();
	}


}
