import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Entreprise } from '../entities/entreprise';
import { ApiEntrepriseService } from '../api-entreprise.service';

@Component({
  selector: 'app-entreprises',
  templateUrl: './entreprises.component.html',
  styleUrls: ['./entreprises.component.css']
})
export class EntreprisesComponent implements OnInit {

  doNew: boolean;
  entreprises$: Observable<Entreprise[]>;

  constructor(private serviceEntreprise: ApiEntrepriseService) { }

  ngOnInit() {
    this.doNew = false;
    this.getEntreprises();
  }

  doDelete(entreprise: Entreprise) {
    this.serviceEntreprise
        .deleteEntreprise(entreprise.id)
        .subscribe(() => this.getEntreprises());
  }

  private getEntreprises() {
    this.entreprises$ = this.serviceEntreprise.getEntreprises();
  }

}
