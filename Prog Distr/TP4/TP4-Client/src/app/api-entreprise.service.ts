import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Entreprise } from './entities/entreprise';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class ApiEntrepriseService {
  urlAPI = 'http://localhost:8080/EntrepriseWebService/api/entreprise';

  constructor(private http: HttpClient) { }

  getEntreprises(): Observable<Entreprise[]> {
    return this.http.get<Entreprise[]>(this.urlAPI, httpOptions);
  }

  getEntreprise(id: string): Observable<Entreprise> {
    const url = `${this.urlAPI}/${id}`;
    return this.http.get<Entreprise>(url, httpOptions);
  }

  createEntreprise(entreprise: Entreprise): Observable<Entreprise> {
    return this.http.post<Entreprise>(this.urlAPI, entreprise, httpOptions);
  }

  updateEntreprise(entreprise: Entreprise): Observable<Entreprise> {
    const url = `${this.urlAPI}/${entreprise.id}`;
    return this.http.put<Entreprise>(url, entreprise, httpOptions);
  }

  deleteEntreprise(id: number): Observable<{}> {
    const url = `${this.urlAPI}/${id}`;
    return this.http.delete(url, httpOptions);
  }

}
