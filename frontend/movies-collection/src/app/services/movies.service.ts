import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MoviesList } from '../models/moviesList';
import { Observable } from 'rxjs/Observable';
import { MovieListComponent } from '../components/movies-list/movies-list.component';
import { Router } from '@angular/router';


@Injectable()
export class MovieService {

  private baseUrl = 'http://localhost:9095';

  constructor(private http: HttpClient, private router: Router) { }

  createMovieData(movieData: MoviesList) {
    console.log('saving movie data:' + movieData);
    return this.http.post(this.baseUrl+'/api/createMovieData', movieData);
  }

  getMoviesList(): Observable<MoviesList[]> {

    return this.http.get<MoviesList[]>(this.baseUrl+'/api/moviesList');

  }

  deleteMovieData(movieId: string): Observable<MoviesList> {
    const path = this.baseUrl+'/api/deleteMovieData/'.concat(movieId);
    return this.http.delete<MoviesList>(path);

  }

}
