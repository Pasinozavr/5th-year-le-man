/** Préparation à la construction d un arbre de coup **/

/*** question 1 ***/

maxDeListe([Val], Val).
maxDeListe([Val|Reste], Val) :- 
    maxDeListe(Reste, MaxDuReste),
    Val > MaxDuReste.
maxDeListe([Val|Reste], MaxDuReste) :- 
    maxDeListe(Reste, MaxDuReste),
    Val =< MaxDuReste.


/*** question 2 ***/

meilleurEtudiant([Note],[Nom], Note, Nom).
meilleurEtudiant([Note|AutresNotes],[Nom|AutresNoms], Note, Nom):-
    meilleurEtudiant(AutresNotes, AutresNoms, MaxDesAutresNotes,_),
    Note > MaxDesAutresNotes.
meilleurEtudiant([Note|AutresNotes],[_|AutresNoms], MaxDesAutresNotes,NomDuMaxDesAutres):-
    meilleurEtudiant(AutresNotes, AutresNoms, MaxDesAutresNotes,NomDuMaxDesAutres),
    Note =< MaxDesAutresNotes.


/*** question 3 ***/

meilleurePaire([[Note,Nom]], [Note, Nom]).
meilleurePaire([[Note, Nom]|Autres],[Note, Nom]):-
    meilleurePaire(Autres, [MaxDesAutres,_]),
    Note > MaxDesAutres.
meilleurePaire([[Note,_]|Autres], [MaxDesAutres,LesAutres]):-
    meilleurePaire(Autres, [MaxDesAutres,LesAutres]),
    Note =< MaxDesAutres.


/*** question 4 ***/

carre(Nb,Carre):- Carre is Nb*Nb.

construitCarre([Nb],[Nb,C]) :- C is Nb*Nb.
construitCarre([Nb|Reste1],[[Nb,C]|Reste2]):-
    construitCarre(Reste1, Reste2),
    C is Nb*Nb.


/*** question 5 ***/

eleve(X,1,X):- ! .
eleve(X,N,XpuissN) :-
    Nmoins1 is N-1,
    eleve(X, Nmoins1, XpuissNmoins1),
    XpuissN is X*XpuissNmoins1.

/*** question 6 ***/

eleveListe([],_,[]):- !.
eleveListe([X|ResteX],N,[[X,XpuissN]|ResteEtudiant]) :-
  eleve(X,N,XpuissN),
  eleveListe(ResteX,N,ResteEtudiant).


/*** question 7 ***/

donneLePlus([[Nom,Note]],[Nom,Note]).
donneLePlus([[Nom,Note]|Autres],[Nom,Note]) :-
    donneLePlus(Autres,[_,SaNote]),
    Note > SaNote.
donneLePlus([[_, Note]|Autres],[SonNom,SaNote]):-
    donneLePlus(Autres,[SonNom,SaNote]),
    Note < SaNote.

donneLeMoins([[Nom,Note]],[Nom,Note]).
donneLeMoins([[Nom,Note]|Autres],[Nom,Note]) :-
    donneLeMoins(Autres,[_,SaNote]),
    Note < SaNote.
donneLeMoins([[_, Note]|Autres],[SonNom,SaNote]):-
    donneLeMoins(Autres,[SonNom,SaNote]),
    Note > SaNote.

donneLesBornes(Liste, Puissance, LePlus, LeMoins) :-
    eleveListe(Liste, Puissance,Temp),
    donneLePlus(Temp,LePlus),
    donneLeMoins(Temp, LeMoins).



/*** question 8 ***/

donnePuissances(X,1,[X]):- !.
donnePuissances(X,N,[XpuissN|Reste]):-
    Nmoins1 is N-1,
    donnePuissances(X,Nmoins1, Reste),
    eleve(X,N,XpuissN).



/*** question 9 ***/

fusionne(Liste,[],Liste).
fusionne([],Liste,Liste).
fusionne([X|Reste],Liste,[X|DejaFait]):-
    fusionne(Reste,Liste,DejaFait).


developpePuissance(L,_,0,L) :- !.
developpePuissance([],_,_,[]).
developpePuissance([X|Reste],N,Prof,LeTotal) :-
    donnePuissances(X,N,LesPuissX),
    ProfMoins1 is Prof-1,
    developpePuissance(LesPuissX,N,ProfMoins1, ListePuissX),
    developpePuissance(Reste,N,Prof, ListePourReste),
    fusionne(ListePuissX,ListePourReste,LeTotal),!.
