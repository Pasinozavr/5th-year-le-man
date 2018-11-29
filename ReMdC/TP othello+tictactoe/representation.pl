%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Le jeu d othello
%   -> representation.pl : module de représentation du jeu (affichage, manipulation de la grille, etc).
%
% Dependances :
%   -> othello.pl : module principal qui charge tous les modules necessaires au deroulement du jeu. 
%   -> regles.pl : module de modelisation des regles du jeu.
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Definition de la grille de depart de jeu
% il n y a pas besoin de guillemets pour chaque element de la liste
grilleDeDepartO([[-,-,-,-,-,-,-,-],
[-,-,-,-,-,-,-,-],
[-,-,-,-,-,-,-,-],
[-,-,-,o,x,-,-,-],
[-,-,-,x,o,-,-,-],
[-,-,-,-,-,-,-,-],
[-,-,-,-,-,-,-,-],
[-,-,-,-,-,-,-,-]]).

% Garder la possibilite de faire des tests sur une grille plus petite 
grilleTest([[-,-,-],[x,o,-],[-,-,-]]).


% lister toutes les cases disponibles pour jouer
toutesLesCasesDepartO([[a,1],[b,1],[c,1],[d,1],[e,1],[f,1],[g,1],[h,1],
		      [a,2],[b,2],[c,2],[d,2],[e,2],[f,2],[g,2],[h,2],
		      [a,3],[b,3],[c,3],[d,3],[e,3],[f,3],[g,3],[h,3],
		      [a,4],[b,4],[c,4],            [f,4],[g,4],[h,4],
		      [a,5],[b,5],[c,5],	        [f,5],[g,5],[h,5],
		      [a,6],[b,6],[c,6],[d,6],[e,6],[f,6],[g,6],[h,6],
		      [a,7],[b,7],[c,7],[d,7],[e,7],[f,7],[g,7],[h,7],
		      [a,8],[b,8],[c,8],[d,8],[e,8],[f,8],[g,8],[h,8]]).



% lister toutes les cases du jeu
toutesLesCases([[a,1],[b,1],[c,1],[d,1],[e,1],[f,1],[g,1],[h,1],
		[a,2],[b,2],[c,2],[d,2],[e,2],[f,2],[g,2],[h,2],
	    [a,3],[b,3],[c,3],[d,3],[e,3],[f,3],[g,3],[h,3],
	    [a,4],[b,4],[c,4],[d,4],[e,4],[f,4],[g,4],[h,4],
	    [a,5],[b,5],[c,5],[d,5],[e,5],[f,5],[g,5],[h,5],
	    [a,6],[b,6],[c,6],[d,6],[e,6],[f,6],[g,6],[h,6],
		[a,7],[b,7],[c,7],[d,7],[e,7],[f,7],[g,7],[h,7],
	    [a,8],[b,8],[c,8],[d,8],[e,8],[f,8],[g,8],[h,8]]).


%%% Tests du predicat
% ?- afficheGrille([[-,-,-,-,-,-,-,-],[-,-,-,-,-,-,-,-],[-,-,-,-,-,-,-,-],[-,-,-,o,x,-,-,-],[-,-,-,x,o,-,-,-],[-,-,-,-,-,-,-,-],[-,-,-,-,-,-,-,-],[-,-,-,-,-,-,-,-]]).
% ?- grilleDeDepartO(X), afficheGrille(X).
% ?- grilleTest(X), afficheGrille(X).
% ?- afficheGrille([[x],[x]]).
% ?- afficheGrille([[x]]).


%%% Permet d afficher la grille au demarrage du jeu
% Predicat : afficheGrilleDep/0
% Usage : afficheGrilleDep affiche la grille de depart du jeu d othello,
%         sous la forme d une grille

afficheGrilleDep:-
	grilleDeDepartO(G),
	afficheGrille(G),!.







%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% caseVide(Grille, NumLigne, NumColonne)
% --> vérifier qu une case est vide, cad qu elle contient '-'

caseVide(Grille, NumLigne, NumColonne):-donneValeurDeCase(Grille,NumLigne,NumColonne,-).




%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : scoreLigne/3
% Usage : scoreLigne(Ligne,Camp,Score) donne le nombre de pion Camp dans
%	  la ligne de grille Ligne

scoreLigne([],_Camp,0):-!.

scoreLigne([Camp|Suite],Camp,Score):-
	scoreLigne(Suite,Camp,Score1),
	Score is Score1 +1.

scoreLigne([Tete|Suite],Camp,Score):-
	Tete \== Camp,
	scoreLigne(Suite,Camp,Score).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : score/3
% Usage : score(Grille,Camp,Score) donne le nombre de pion Camp dans la
%         grille Grille

score([],_Camp,0):-!.

score([Ligne1|Suite],Camp,Score):-
	scoreLigne(Ligne1,Camp,Score1),
	score(Suite,Camp,Score2),
	Score is Score1 + Score2.



%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : listeCasesVides/2
% Usage : listeCasesVides(Grille,ListesCasesLibres) donne a partir
%         d une grille la liste des cases libres

listeCasesVides(Grille,ListeCasesLibres):-
	toutesLesCasesDepartO(ListesCases),
	listeCasesLibres(Grille,ListesCases,ListeCasesLibres),!.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : listeCasesLibres/3
% Usage : listeCasesLibres(Grille,ListeCases,ListeCasesLibres)

listeCasesLibres(_Grille,[],[]):-!.

listeCasesLibres(Grille,[[Colonne,Ligne]|SuiteCases],[[Colonne,Ligne]|SuiteCasesLibres]):-
	caseDeGrille(Colonne,Ligne,Grille,-),
	listeCasesLibres(Grille,SuiteCases,SuiteCasesLibres).

listeCasesLibres(Grille,[[Colonne,Ligne]|SuiteCases],SuiteCasesLibres):-
	not(caseDeGrille(Colonne,Ligne,Grille,-)),
	listeCasesLibres(Grille,SuiteCases,SuiteCasesLibres).




%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Predicat : listeCoupsValides/4
% Usage : listeCoupsValides(ListeCoups,Camp,Grille,ListeCoupsValides) pour un camp

listeCoupsValides([],_Camp,_Grille,[]):-!.

listeCoupsValides([[Colonne,Ligne]|Suite],Camp,Grille,[[Colonne,Ligne]|Suite2]):-
	leCoupEstValide(Camp,Grille,[Colonne,Ligne]),
	listeCoupsValides(Suite,Camp,Grille,Suite2).

listeCoupsValides([Case|Suite],Camp,Grille,Suite2):-
	not(leCoupEstValide(Camp,Grille,Case)),
	listeCoupsValides(Suite,Camp,Grille,Suite2).



%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : testerCoup/3
% Usage : testerCoup(ListeCasesVides,Camp,Grille) verifie s il existe
%	  des coups possibles pour le camp Camp dans la Grille, a partir
%	  de la liste des cases vides

testerCoup([[Colonne,Ligne]|Suite],Camp,Grille):-
	leCoupEstValide(Camp,Grille,[Colonne,Ligne]);
	testerCoup(Suite,Camp,Grille).







% Predicat : campJoueur2/1
% Usage : campJoueur2(CampJ2) est satisfait si CampJ2 est le camp du joueur 2
% Le campJoueur1 est defini dynamiquement en debut de jeu et peut etre modifie en fonction
% du choix des joueurs.
% Permet d associer le nom d un joueur, avec son numero et sa couleur
campJoueur2(CampJ2):-
	campJoueur1(CampJ1),
	campAdverse(CampJ1,CampJ2),!.



%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicat : saisieUnCoup/2
% Usage : saisieUnCoup(NomCol,NumLig) permet de saisir un coup a jouer

saisieUnCoup(NomCol,NumLig):-
	writeln('Entrez votre coup (sans oublier le point) :'),
 	writeln('Colonne (a, b, c, d, e, f, g ou h) : '),
	saisieColonne(NomCol),
	writeln('Ligne (1, 2, 3, 4, 5, 6, 7 ou 8) : '),
	saisieLigne(NumLig).

% on peut affiner le predicat en testant les valeurs donnees par l utilisateur avec
% saisieColonne et saisieLigne.

% Predicat : saisieColonne/1
% Usage : saisieColonne(NomCol) permet d eviter les erreurs de saisie de la colonne
saisieColonne(NomCol):-
	read(NomCol),
	member(NomCol,[a,b,c,d,e,f,g,h]),!.

saisieColonne(NomCol):-
	writeln('Nom de colonne incorrect'),
	writeln('Colonne (a, b, c, d, e, f, g ou h) : '),
	saisieColonne(NomCol).


% Predicat : saisieLigne/1
% Usage : saisieLigne(NumLig) permet d eviter les erreurs de saisie de la ligne

saisieLigne(NumLig):-
	read(NumLig),
	member(NumLig,[1,2,3,4,5,6,7,8]),!.

saisieLigne(NumLig):-
	writeln('Coup invalide'),
	saisieUnCoup(_NomCol,NumLig).






	




