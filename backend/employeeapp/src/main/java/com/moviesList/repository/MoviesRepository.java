package com.moviesList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviesList.model.MoviesList;

/**
 * @author Bodicherla Narasimha Rao
 * This interface is used to interact with the database table.
 *
 */
public interface MoviesRepository extends JpaRepository<MoviesList, Long> {

}
