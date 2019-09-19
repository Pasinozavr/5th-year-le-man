import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Contact } from '../entities/contact';
import { ApiContactService } from '../api-contact.service';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {

  doNew: boolean;
  contacts$: Observable<Contact[]>;

  constructor(private serviceContact: ApiContactService) { }

  ngOnInit() {
    this.doNew = false;
    this.getContacts();
  }

  doDelete(contact: Contact) {
    this.serviceContact
        .deleteContact(contact.id)
        .subscribe(() => this.getContacts());
  }

  private getContacts() {
    this.contacts$ = this.serviceContact.getContacts();
  }

}
