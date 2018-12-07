(deffacts prop
(pr poilu Bozo)
(pr poilu Bobby)
(pr poilu Betty)
(pr poilu Baloo)
(pr poche_ventrale Bozo)
(pr poche_ventrale Bobby)
(pr fait_grands_bonds Bozo)
(pr grimpe_aux_arbres Bobby)
(pr carnivore Betty)
(pr carnivore Baloo)
(pr vole Betty)
(pr pond_des_oeufs Baloo)
(pr mange_eucalyptus Kolo)
(pr mange_bambou Koko)
(sex Bozo "un")
(sex Bobby "un")
(sex Betty "une")
(sex Baloo "une")
(sex Kolo "un")
(status Bozo undef)
(status Bobby undef)
(status Betty undef)
(status Baloo undef)
(status Koko undef))

(deffacts questions
(question phalanges_longues " possede des phalanges longues (oui/non) ? ")
(question poilu " est poilu (oui/non) ? ")
(question carnivore " est carnivoque (oui/non) ? ")
(question poche_ventrale " a poche ventrale (oui/non) ? ")
(question fait_grands_bonds " est qui fait des grands bonds (oui/non) ? ")
(question grimpe_aux_arbres " grimpe aux arbres (oui/non) ? ")
(question vole " est vole (oui/non) ? ")
(question pond_des_oeuf " pond des oeuf (oui/non) ? ")
(question mange_eucalyptus " mange eucalyptus (oui/non) ? ")
(question mange_bambou " mange bambou (oui/non) ? "))

(defrule rule1
(declare (salience 4))
(pr poilu ?nom)=>
(assert (pr donne_du_lait_a_ses_petits ?nom)))

(defrule rule2
(declare (salience 3))
(pr poche_ventrale ?nom)
(pr phalanges_longues ?nom)=>
(assert (identification ?nom "phalanger")))

(defrule rule3
(declare (salience 3))
(pr marsupial ?nom)
(pr grimpe_aux_arbres ?nom)=>
(assert (identification ?nom "koala")))

(defrule rule4
(declare (salience 4))
(pr donne_du_lait_a_ses_petits ?nom)
(pr poche_ventrale ?nom)=>
(assert (pr marsupial ?nom)))

(defrule rule5
(declare (salience 3))
(pr marsupial ?nom)
(pr fait_grands_bonds ?nom)=>
(assert (identification ?nom "kangourou")))

(defrule rule6
(declare (salience 4))
(pr donne_du_lait_a_ses_petits ?nom)=>
(assert (pr mammiferes ?nom)))

(defrule rule7
(declare (salience 3))
(pr mange_eucalyptus ?nom)
(not (pr mange_bambou ?nom))=>
(assert (identification ?nom "koala")))

(defrule rule8
(declare (salience 3))
(pr vole ?nom)
(pr carnivore ?nom)
(pr donne_du_lait_a_ses_petits ?nom)=>
(assert (identification ?nom "chauve-souris")))

(defrule rule9
(declare (salience 3))
(pr oiseau ?nom)
(pr carnivore ?nom)=>
(assert (identification ?nom "faucon")))

(defrule rule10
(declare (salience 3))
(pr marsupial ?nom)
(pr carnivore ?nom)
(pr queue_pregensile ?nom)=>
(assert (identification ?nom "opossom")))

(defrule rule11
(declare (salience 4))
(pr marsupial ?nom)=>
(assert (pr provient_de_lhemishpere ?nom)))

(defrule rule12
(declare (salience 4))
(pr vole ?nom)
(pr pond_des_oeufs ?nom)=>
(assert (pr oiseau ?nom)))

(defrule rule13
(declare (salience 3))
(pr mammifere ?nom)
(pr pond_des_oeufs ?nom)=>
(assert (identification ?nom "ornithrynque")))

(defrule rule14
(declare (salience 3))
(pr mammifere ?nom)
(pr petit ?nom)
(pr queue_longue ?nom)=>
(assert (identification ?nom "soiris")))

(defrule afficheDef
(declare (salience 2))
(identification ?nom ?text)
(sex ?nom ?pref)
?f2<-(status ?nom undef)=>
(retract ?f2)
(assert (status ?nom def))
(printout t ?nom " est " ?pref " " ?text crlf))

(defrule afficheUndef
(declare (salience 2))
(status ?nom undef)=>
(printout t "Je n'ai pas pu identifier " ?nom crlf))

(defrule question-hypothese
(declare (salience 1))
(status ?nom undef)
(question ?about ?text)
(not (pr ?about ?nom))
=>
(printout t ?nom ?text crlf)
(if (eq (readline) "oui") then
(assert (pr ?about ?nom))))

(defrule afficheDefNew
(identification ?nom ?text)
(sex ?nom ?pref)
?f2<-(status ?nom undef)=>
(retract ?f2)
(assert (status ?nom def))
(printout t ?nom " est " ?pref " " ?text crlf))