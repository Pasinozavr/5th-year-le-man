won(Table,X):-Table=[X,X,X,_,_,_,_,_,_].
won(Table,X):-Table=[_,_,_,X,X,X,_,_,_].
won(Table,X):-Table=[_,_,_,_,_,_,X,X,X].
won(Table,X):-Table=[X,_,_,X,_,_,X,_,_].
won(Table,X):-Table=[_,X,_,_,X,_,_,X,_].
won(Table,X):-Table=[_,_,X,_,_,X,_,_,X].
won(Table,X):-Table=[X,_,_,_,X,_,_,_,X].
won(Table,X):-Table=[_,_,X,_,X,_,X,_,_].

play(Table):-won(Table,x),write('X won').
play(Table):-won(Table,o),write('O won').
play(Table):-read(M),move(Table,M,Tablea),show(Tablea),iimove(Tablea,Tableb),show(Tableb),play(Tableb).

show([A,B,C,D,E,F,G,H,I]) :- write([A,B,C]),nl,write([D,E,F]),nl,write([G,H,I]),nl,nl.

playgame:-show([-,-,-,-,-,-,-,-,-]),play([-,-,-,-,-,-,-,-,-]).

iimove(Tablea,Tableb):-moves(o,Tablea,Tableb),won(Tableb,o),!.

iimove(Tablea,Tableb):-moves(x,Tablea,Tableb),won(Tableb, x),moves(o,Tablea,Tableb),not(won(Tableb, x)).

iimove(Tablea,Tableb):-moves(o,Tablea,Tableb),not(won(Tableb, x)),
score1(Tableb,Q,o,x), score2(Tableb,W,o,x), score3(Tableb,E,o,x), R is Q+W, T is R+E,
score1(Tableb,Y,x,o), score2(Tableb,H,x,o), score3(Tableb,N,x,o), M is Y+H, J is M+N,
R>M+1,
!.

iimove(Tablea,Tableb):- not(member(-,Tablea)),!,write('X=O'), nl,Tableb=Tablea.

score1([A,B,C,D,E,F,G,H,I],S,X,Y):- equal(A,B,C,X), S is 10.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):- equal(D,E,F,X), S is 10.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):- equal(F,G,I,X), S is 10.

score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(B,C,X), not(equal(A,Y)), S is 4.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,C,X), not(equal(B,Y)), S is 4.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,B,X), not(equal(C,Y)), S is 4.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(E,F,X), not(equal(D,Y)), S is 4.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(D,F,X), not(equal(E,Y)), S is 4.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(D,E,X), not(equal(F,Y)), S is 4.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(H,I,X), not(equal(G,Y)), S is 4.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(G,I,X), not(equal(H,Y)), S is 4.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(G,H,X), not(equal(I,Y)), S is 4.

score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,X), not(equals(B,C,Y)), S is 1.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(B,X), not(equals(A,C,Y)), S is 1.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(C,X), not(equals(A,B,Y)), S is 1.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(D,X), not(equals(E,F,Y)), S is 1.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(E,X), not(equals(D,F,Y)), S is 1.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(F,X), not(equals(D,E,Y)), S is 1.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(G,X), not(equals(H,I,Y)), S is 1.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(H,X), not(equals(G,I,Y)), S is 1.
score1([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(I,X), not(equals(G,H,Y)), S is 1.

score1([A,B,C,D,E,F,G,H,I],S,X,Y):- S is 0.

score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,D,F,X), S is 10.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(B,E,H,X), S is 10.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(C,F,I,X), S is 10.

score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(D,G,X), not(equal(A,Y)), S is 4.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(E,H,X), not(equal(B,Y)), S is 4.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(F,I,X), not(equal(C,Y)), S is 4.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,G,X), not(equal(D,Y)), S is 4.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(B,H,X), not(equal(E,Y)), S is 4.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(C,I,X), not(equal(F,Y)), S is 4.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,D,X), not(equal(G,Y)), S is 4.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(B,E,X), not(equal(H,Y)), S is 4.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(C,F,X), not(equal(I,Y)), S is 4.

score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,X), not(equals(D,G,Y)), S is 1.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(B,X), not(equals(E,H,Y)), S is 1.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(C,X), not(equals(F,I,Y)), S is 1.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(D,X), not(equals(A,G,Y)), S is 1.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(E,X), not(equals(B,H,Y)), S is 1.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(F,X), not(equals(C,I,Y)), S is 1.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(G,X), not(equals(A,D,Y)), S is 1.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(H,X), not(equals(B,E,Y)), S is 1.
score2([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(I,X), not(equals(C,F,Y)), S is 1.

score2([A,B,C,D,E,F,G,H,I],S,X,Y):- S is 0.

score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,E,I,X), S is 10.
score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(C,E,G,X), S is 10.

score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,E,X), not(equal(I,Y)), S is 4.
score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(E,I,X), not(equal(A,Y)), S is 4.
score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,I,X), not(equal(E,Y)), S is 4.
score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(C,E,X), not(equal(G,Y)), S is 4.
score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(C,G,X), not(equal(E,Y)), S is 4.
score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(G,E,X), not(equal(C,Y)), S is 4.

score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(A,X), not(equals(E,I,Y)), S is 1.
score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(E,X), not(equals(A,I,Y)), S is 1.
score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(I,X), not(equals(A,E,Y)), S is 1.
score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(C,X), not(equals(G,E,Y)), S is 1.
score3([A,B,C,D,E,F,G,H,I],S,X,Y):-equal(G,X), not(equals(C,E,Y)), S is 1.

score3([A,B,C,D,E,F,G,H,I],S,X,Y):- S is 0.

equal(A, B, C, O):- A==B, B==C, C==O.
equal(A, B, O):- A==B, B==O.
equal(A, O):- A==O.

equals(A,B,O):- A==O.
equals(A,B,O):- B==O.

move([-,B,C,D,E,F,G,H,I], 1, [x,B,C,D,E,F,G,H,I]).
move([A,-,C,D,E,F,G,H,I], 2, [A,x,C,D,E,F,G,H,I]).
move([A,B,-,D,E,F,G,H,I], 3, [A,B,x,D,E,F,G,H,I]).
move([A,B,C,-,E,F,G,H,I], 4, [A,B,C,x,E,F,G,H,I]).
move([A,B,C,D,-,F,G,H,I], 5, [A,B,C,D,x,F,G,H,I]).
move([A,B,C,D,E,-,G,H,I], 6, [A,B,C,D,E,x,G,H,I]).
move([A,B,C,D,E,F,-,H,I], 7, [A,B,C,D,E,F,x,H,I]).
move([A,B,C,D,E,F,G,-,I], 8, [A,B,C,D,E,F,G,x,I]).
move([A,B,C,D,E,F,G,H,-], 9, [A,B,C,D,E,F,G,H,x]).

moves(O, [-,B,C,D,E,F,G,H,I], [O,B,C,D,E,F,G,H,I]).
moves(O, [A,-,C,D,E,F,G,H,I], [A,O,C,D,E,F,G,H,I]).
moves(O, [A,B,-,D,E,F,G,H,I], [A,B,O,D,E,F,G,H,I]).
moves(O, [A,B,C,-,E,F,G,H,I], [A,B,C,O,E,F,G,H,I]).
moves(O, [A,B,C,D,-,F,G,H,I], [A,B,C,D,O,F,G,H,I]).
moves(O, [A,B,C,D,E,-,G,H,I], [A,B,C,D,E,O,G,H,I]).
moves(O, [A,B,C,D,E,F,-,H,I], [A,B,C,D,E,F,O,H,I]).
moves(O, [A,B,C,D,E,F,G,-,I], [A,B,C,D,E,F,G,O,I]).
moves(O, [A,B,C,D,E,F,G,H,-], [A,B,C,D,E,F,G,H,O]).