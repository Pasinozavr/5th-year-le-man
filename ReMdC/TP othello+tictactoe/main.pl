%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : afficheLigne/1
% Usage : affigheLigne(Ligne)est satisfait si Ligne est une ligne d une grille de
%         morpion et qu elle l affiche proprement a l ecran.
afficheLigne([Tete]):-
	tab(1),write('| '),write(Tete),writeln(' |'),!.
afficheLigne([Tete|Suite]):-
	tab(1),write('| '),write(Tete),
	afficheLigne(Suite).
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : afficheGrille/1
% recursive avec les succNum et succApha
% Se décompose en plusieurs étapes
% -> affichePremiereLigne :
%    afficher la premiere ligne en fonction de la longueur de la première ligne de la grille :
%    la condition terminale permet d afficher la valeur de la derniere colonne, quelque
%    soit le dernier element qui reste dans le tableau;
%    On parcourt la ligne mais le contenu de cette ligne ne nous interesse pas.
% -> afficheGrilleSimple : 
%    afficher la grille en gérant les numéros de ligne avec le succNum;
% -> afficheGrille :
%    appelle les deux predicats precedants;
%

affichePremiereLigne(Val,[_]):- tab(1),write('| '),write(Val),writeln(' |').
affichePremiereLigne(Val,[_|Suite]):-
	tab(1),write('| '),write(Val),succAlpha(Val,Val2),
	affichePremiereLigne(Val2,Suite).

afficheGrilleSimple(_,[X]) :- tab(1), afficheLigne(X).
afficheGrilleSimple(N,[Tete|Reste]):-
	tab(1),afficheLigne(Tete),
	succNum(N,N1),write(N1),
	afficheGrilleSimple(N1,Reste).
	
afficheGrille([Tete|Suite]):-
	tab(2),affichePremiereLigne(a,Tete),
	write('1'),
	afficheGrilleSimple(1,[Tete|Suite]).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : succNum/2
% Usage : succNum(X,Y) est satisfait si Y est le successeur de X
succNum(1,2).
succNum(2,3).
succNum(3,4).
succNum(4,5).
succNum(5,6).
succNum(6,7).
succNum(7,8).
	

% Predicat : succAlpha/2
% Usage : succAlpha(X,Y) est satisfait si Y est le successeur de X 
%	 (ordre lexico-graphique)
succAlpha(a,b).
succAlpha(b,c).
succAlpha(c,d).
succAlpha(d,e).
succAlpha(e,f).
succAlpha(f,g).
succAlpha(g,h).


% Predicat : ligneDeGrille(NumLigne, Grille, Ligne).
% Satisfait si Ligne est la ligne numero NumLigne dans la Grille
ligneDeGrille(1, [Test |_], Test).
ligneDeGrille(NumLigne, [_|Reste],Test) :- succNum(I, NumLigne),
		ligneDeGrille(I,Reste,Test).

% Predicat : caseDeLigne(Col, Liste, Valeur).
% Satisfait si Valeur est dans la colonne Col de la Liste
caseDeLigne(a, [A|_],A).
caseDeLigne(Col, [_|Reste],Test) :- succAlpha(I, Col),caseDeLigne(I,Reste, Test).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat caseDeGrille/4
% usage : caseDeGrille(NumColonne,NumLigne,Grille,Case) est satisfait si la
%         Case correspond bien a l intersection de la de la colonne NumColonne
%	  et de la ligne NumLigne dans le Grille

caseDeGrille(NumColonne,NumLigne,Grille,Case):-
	ligneDansGrille(NumLigne,Grille,Ligne),
	caseDansLigne(NumColonne,Ligne,Case),!.
% coordonneesOuListe(NomCol, NumLigne, Liste).
% ?- coordonneesOuListe(a, 2, [a,2]). vrai.
coordonneesOuListe(NomCol, NumLigne, [NomCol, NumLigne]).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% ligneDansGrille : verifier qu une ligne existe dans une grille;
% caseDansLigne : verifier qu une case existe dans une ligne;
% La methode consiste a tronquer la liste jusqu a arriver a la position qui nous interesse;
% on decremente les index a chaque recursion, 
% la position qui nous interesse est en position 1 ou a.

ligneDansGrille(1,[Tete|_],Tete).
ligneDansGrille(NumLig,[_|Reste],Lig):-
	succNum(I,NumLig),
	ligneDansGrille(I,Reste,Lig).
	
caseDansLigne(a,[Tete|_],Tete).
caseDansLigne(Col,[_|Reste],Case):-
	succAlpha(I,Col),
	caseDansLigne(I,Reste,Case).
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : duneListeALautre/3
% Usage  : duneListeALautre(LC1,C,LC2) est satisfait si la liste LC1 est composee
%          de toutes le cases de la liste LC2 plus la case C

duneListeALautre([Tete|Suite],Tete,Suite):-!.

duneListeALautre([Tete|Suite],C,[Tete|Suite2]):-
	duneListeALautre(Suite,C,Suite2).
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : campAdverse/2
% Usage : campAdverse(Camp,campAdverse) permet de trouver le camp advairse d un camp

campAdverse(x,o).
campAdverse(o,x).


games(t):-
consult(tictactoe),
lanceTic.

games(o):-
consult(othello),
lanceOthello.

lance:-
	writeln('Choisissez une option (o ohello t tictactoe) : '),
	choose(Choix),
	games(Choix),
	!.
	
choose(Choix):-
	writeln('Press o or t : '),
	read(Choix).