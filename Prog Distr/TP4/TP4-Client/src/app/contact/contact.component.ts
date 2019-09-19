import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { Contact } from '../entities/contact';
import { Entreprise } from '../entities/entreprise';
import { ApiEntrepriseService } from '../api-entreprise.service';
import { ApiContactService } from '../api-contact.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  @Output() creationDone = new EventEmitter<boolean>();
  contact: Contact;
  entreprises$: Observable<Entreprise[]>;

  constructor(private contactService: ApiContactService,
              private serviceEntreprise: ApiEntrepriseService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.entreprises$ = this.serviceEntreprise.getEntreprises();

    if (id) {
      this.contactService
          .getContact(id)
          .subscribe(contact => this.contact = contact);
    } else {
      this.contact = new Contact();
      this.contact.id = 0;
    }
  }

  valider() {
    let obs: Observable<Contact>;
    if (this.contact.id === 0) {
      obs = this.contactService.createContact(this.contact);
    } else {
      obs = this.contactService.updateContact(this.contact);
    }
    obs.subscribe(contact => this.creationDone.emit(true));
  }

  annuler() {
    this.creationDone.emit(true);
  }
}
