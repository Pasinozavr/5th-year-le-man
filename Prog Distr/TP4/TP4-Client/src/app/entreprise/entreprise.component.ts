import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { Entreprise } from '../entities/entreprise';
import { ApiEntrepriseService } from '../api-entreprise.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-entreprise',
  templateUrl: './entreprise.component.html',
  styleUrls: ['./entreprise.component.css']
})
export class EntrepriseComponent implements OnInit {
  @Output() creationDone = new EventEmitter<boolean>();
  entreprise: Entreprise;

  constructor(private entrepriseService: ApiEntrepriseService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.entrepriseService
          .getEntreprise(id)
          .subscribe(entreprise => this.entreprise = entreprise);
    } else {
      this.entreprise = new Entreprise();
      this.entreprise.id = 0;
    }
  }

  valider() {
    let obs: Observable<Entreprise>;
    if (this.entreprise.id === 0) {
      obs = this.entrepriseService.createEntreprise(this.entreprise);
    } else {
      obs = this.entrepriseService.updateEntreprise(this.entreprise);
    }
    obs.subscribe(entreprise => this.creationDone.emit(true));
  }

  annuler() {
    this.creationDone.emit(true);
  }
}
