gagner(Bord, Joueur) :- rowgagner(Bord, Joueur).
gagner(Bord, Joueur) :- colgagner(Bord, Joueur).
gagner(Bord, Joueur) :- diaggagner(Bord, Joueur).

rowgagner(Bord, Joueur) :- Bord = [Joueur,Joueur,Joueur,_,_,_,_,_,_].
rowgagner(Bord, Joueur) :- Bord = [_,_,_,Joueur,Joueur,Joueur,_,_,_].
rowgagner(Bord, Joueur) :- Bord = [_,_,_,_,_,_,Joueur,Joueur,Joueur].

colgagner(Bord, Joueur) :- Bord = [Joueur,_,_,Joueur,_,_,Joueur,_,_].
colgagner(Bord, Joueur) :- Bord = [_,Joueur,_,_,Joueur,_,_,Joueur,_].
colgagner(Bord, Joueur) :- Bord = [_,_,Joueur,_,_,Joueur,_,_,Joueur].

diaggagner(Bord, Joueur) :- Bord = [Joueur,_,_,_,Joueur,_,_,_,Joueur].
diaggagner(Bord, Joueur) :- Bord = [_,_,Joueur,_,Joueur,_,Joueur,_,_].

autre(x,o).
autre(o,x).

jeu(Bord, Joueur) :- gagner(Bord, Joueur), !, write([joueur, Joueur, gagners]).
jeu(Bord, Joueur) :- 
  autre(Joueur,Autrejoueur), 
  move(Bord,Joueur,NouveauBord),
  !,
  display(NouveauBord),
  jeu(NouveauBord,Autrejoueur).

move([-,B,C,D,E,F,G,H,I], Joueur, [Joueur,B,C,D,E,F,G,H,I]).
move([A,-,C,D,E,F,G,H,I], Joueur, [A,Joueur,C,D,E,F,G,H,I]).
move([A,B,-,D,E,F,G,H,I], Joueur, [A,B,Joueur,D,E,F,G,H,I]).
move([A,B,C,-,E,F,G,H,I], Joueur, [A,B,C,Joueur,E,F,G,H,I]).
move([A,B,C,D,-,F,G,H,I], Joueur, [A,B,C,D,Joueur,F,G,H,I]).
move([A,B,C,D,E,-,G,H,I], Joueur, [A,B,C,D,E,Joueur,G,H,I]).
move([A,B,C,D,E,F,-,H,I], Joueur, [A,B,C,D,E,F,Joueur,H,I]).
move([A,B,C,D,E,F,G,-,I], Joueur, [A,B,C,D,E,F,G,Joueur,I]).
move([A,B,C,D,E,F,G,H,-], Joueur, [A,B,C,D,E,F,G,H,Joueur]).

display([A,B,C,D,E,F,G,H,I]) :- write([A,B,C]),nl,write([D,E,F]),nl,write([G,H,I]),nl,nl.

autojeu :- jeu([-,-,-,-,-,-,-,-,-],x).

x_peut_gagner_un(Bord) :- move(Bord, x, NouveauBord), gagner(NouveauBord, x).

orespond(Bord,NouveauBord) :- 
  move(Bord, o, NouveauBord),
  gagner(NouveauBord, o),
  !.
orespond(Bord,NouveauBord) :-
  move(Bord, o, NouveauBord), 
  not(x_peut_gagner_un(NouveauBord)).
orespond(Bord,NouveauBord) :-
  move(Bord, o, NouveauBord).
orespond(Bord,NouveauBord) :-
  not(member(b,Bord)),
  !, 
  write('Les chats ont gagne!'), nl,
  NouveauBord = Bord.


xmove([-,B,C,D,E,F,G,H,I], 1, [x,B,C,D,E,F,G,H,I]).
xmove([A,-,C,D,E,F,G,H,I], 2, [A,x,C,D,E,F,G,H,I]).
xmove([A,B,-,D,E,F,G,H,I], 3, [A,B,x,D,E,F,G,H,I]).
xmove([A,B,C,-,E,F,G,H,I], 4, [A,B,C,x,E,F,G,H,I]).
xmove([A,B,C,D,-,F,G,H,I], 5, [A,B,C,D,x,F,G,H,I]).
xmove([A,B,C,D,E,-,G,H,I], 6, [A,B,C,D,E,x,G,H,I]).
xmove([A,B,C,D,E,F,-,H,I], 7, [A,B,C,D,E,F,x,H,I]).
xmove([A,B,C,D,E,F,G,-,I], 8, [A,B,C,D,E,F,G,x,I]).
xmove([A,B,C,D,E,F,G,H,-], 9, [A,B,C,D,E,F,G,H,x]).
xmove(Bord, _, Bord) :- write('Remplacant illegal.'), nl.

jeuo :- explique, jouerde([-,-,-,-,-,-,-,-,-]).

explique :-
  write('Tu joue X en entrant des positions entieres suivie d une periode.'),
  nl,
  display([1,2,3,4,5,6,7,8,9]).

jouerde(Bord) :- gagner(Bord, x), write('Tu gagne!').
jouerde(Bord) :- gagner(Bord, o), write('Je gagne!').
jouerde(Bord) :- read(N),
  xmove(Bord, N, NouveauBord), 
  display(NouveauBord),
  orespond(NouveauBord, NouveauNouveauBord), 
  display(NouveauNouveauBord),
  jouerde(NouveauNouveauBord).