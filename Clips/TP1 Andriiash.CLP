(deffacts personne
(id "Eugenie" 1 f)
(id "Albert" 2 h)
(id "Martine" 3 h)
(id "Christiane" 4 f)
(id "Daniel" 5 h)
(id "Herve" 6 m)
(id "Laurent" 7 h)
(id "Nicolas" 8 h)
(id "Stephanie" 9 f)
(id "Thierry" 10 f)
(id "Sylvain" 11 f)
(id "Philippe" 12 f)
(id "Eric" 13 h))

(deffacts famille
(mere "Eugenie" "Martine")
(mere "Eugenie" "Christane")
(mere "Eugenie" "Daniel")
(pere "Albert" "Martine")
(pere "Albert" "Christane")
(pere "Albert" "Daniel")
(pere "Matrine" "Herve")
(pere "Matrine" "Laurent")
(pere "Matrine" "Nicolas")
(mere "Christane" "Stephanie")
(mere "Christane" "Thierry")
(mere "Christane" "Sylvain")
(pere "Daniel" "Philippe")
(pere "Daniel" "Eric"))

(deffacts texte
(pourpere " est le pere de ")
(pourmere " est le mere de ")
(pourfrere " est le frere de ")
(poursoeur " est la soueur de ")
(pourgrandpere " est le grand-pere de ")
(pourgrandmere " est la grand-mere de ")
(pouroncle " est le oncle de ")
(pourtante " est la tante de ")
(pourconsin " est le coisin de ")
(pourcoisine " est la coisine de ")
)

(defrule trouverFreresEtSoeurs
(or
(pere ?who ?whom1)
(mere ?who ?whom1))
(or
(pere ?who ?whom2)
(mere ?who ?whom2))
(test (neq ?whom1 ?whom2))
(id ?whom1 ?id ?sex)=>
(if (= (str-compare ?sex "h") 0)
then
(assert (frere ?whom1 ?whom2)))
(if (= (str-compare ?sex "f") 0)
then
(assert (soeur ?whom1 ?whom2)))
)


(defrule trouverGrandPeresEtGrandMeres
(or
(pere ?who ?temp)
(mere ?who ?whom))
(or
(pere ?temp ?whom)
(mere ?temp ?whom))
(id ?who ?id ?sex)
(pourgrandpere ?text)=>
(if (= (str-compare ?sex "h") 0)
then
(assert (grandpere ?who ?whom)))
(if (= (str-compare ?sex "f") 0)
then
(assert (grandmere ?who ?whom)))
)


(defrule trouverOnclesEtTantes
(or
(pere ?temp ?whom)
(mere ?temp ?whom))
(or
(frere ?who ?temp)
(soeur ?who ?temp)
(frere ?temp ?who)
(soeur ?temp ?who))
(id ?who ?id ?sex)=>
(if (= (str-compare ?sex "h") 0)
then
(assert (oncle ?who ?whom)))
(if (= (str-compare ?sex "f") 0)
then
(assert (tante ?who ?whom)))
)

(defrule trouverConsinsEtCousines
(or
(oncle ?temp ?whom)
(tante ?temp ?whom))
(or
(pere ?temp ?who)
(mere ?temp ?who))
(id ?who ?id ?sex)
(test (neq ?who ?whom))
=>
(if (= (str-compare ?sex "h") 0)
then
(assert (consin ?who ?whom)))
(if (= (str-compare ?sex "f") 0)
then
(assert (coisine ?who ?whom)))
)

(defrule afficherFamille
(or
(and
(pere ?who ?whom)
(pourpere ?text)
)
(and
(mere ?who ?whom)
(pourmere ?text)
)
(and
(frere ?who ?whom)
(pourfrere ?text)
)
(and
(soeur ?who ?whom)
(poursoeur ?text)
)
(and
(grandpere ?who ?whom)
(pourgrandpere ?text)
)
(and
(grandmere ?who ?whom)
(pourgrandmere ?text)
)
(and
(oncle ?who ?whom)
(pouroncle ?text)
)
(and
(tante ?who ?whom)
(pourtante ?text)
)
(and
(consin ?who ?whom)
(pourconsin ?text)
)
(and
(cousine ?who ?whom)
(pourcousine ?text)
)
)
=>
(printout t ?who ?text ?whom crlf))

(defrule fabriquerDeListeDesPeres
(pere ?who ?whom)=>
(assert (listeDesPeres ?who ?whom))
)
;et maintenant il y a la liste des peres 'listeDesPeres', oui?
:similaire peut etre fait pour d'autres relations
;peut etre fait en une seule regle