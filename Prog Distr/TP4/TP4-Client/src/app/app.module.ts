import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { EntreprisesComponent } from './entreprises/entreprises.component';
import { EntrepriseComponent } from './entreprise/entreprise.component';
import { ContactsComponent } from './contacts/contacts.component';

import { ApiEntrepriseService } from './api-entreprise.service';
import { ContactComponent } from './contact/contact.component';

const appRoutes: Routes = [
  { path: 'entreprises', component: EntreprisesComponent },
  { path: 'entreprises/:id', component: EntrepriseComponent },
  { path: 'contacts', component: ContactsComponent },
  { path: '**', component: EntreprisesComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    EntreprisesComponent,
    ContactsComponent,
    EntrepriseComponent,
    ContactComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule
  ],
  providers: [ApiEntrepriseService],
  bootstrap: [AppComponent]
})
export class AppModule { }
