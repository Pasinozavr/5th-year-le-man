import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Contact } from './entities/contact';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
providedIn: 'root'
})
export class ApiContactService {
  urlAPI = 'http://localhost:8080/EntrepriseWebService/api/contact';

  constructor(private http: HttpClient) { }

  getContacts(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.urlAPI, httpOptions);
  }

  getContact(id: string): Observable<Contact> {
    const url = `${this.urlAPI}/${id}`;
    return this.http.get<Contact>(url, httpOptions);
  }

  createContact(contact: Contact): Observable<Contact> {
    return this.http.post<Contact>(this.urlAPI, contact, httpOptions);
  }

  updateContact(contact: Contact): Observable<Contact> {
    const url = `${this.urlAPI}/${contact.id}`;
    return this.http.put<Contact>(url, contact, httpOptions);
  }

  deleteContact(id: number): Observable<{}> {
    const url = `${this.urlAPI}/${id}`;
    return this.http.delete(url, httpOptions);
  }

}
