#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#define NB_EXPR 13
#define VRAI 1
#define FAUX 0
#define DEBOG 0

void attendre();
void tirage_aleatoire(int* a, int* b, int* c);
void expert(int a, int b, int c, int pion1, int pion2);
void maj_modele_apprenant(int choix, int pion_joueur);
void afficher_modele_apprenant();
int conseil(int choix);
int saisie_choix_joueur_avec_total(int a, int b, int c);
int saisie_choix_joueur_sans_total(int a, int b, int c);
void tour_joueur(int* pion_joueur, int* pion_west);
void tour_west(int* pion_joueur, int* pion_west);

/* Jeu WEST */

/* Caract�ristiques d'un d�placement possible */
typedef struct {
	int val_expr;		/* Valeur de l'expression */
	int nouv_posit;		/* Nouvelle position joueur */
	int nouv_posit_adv;	/* Nouvelle position adversaire */
	int ville;			/* Usage d'une ville */
	int raccourci;		/* Usage d'un raccourci */
	int collision;		/* Usage d'une collision */
} t_deplact;

/* Tableau des d�placements possibles */
t_deplact table[NB_EXPR];

/* Indice du meilleur d�placement */
int meilleur;

/* Maximums atteignables (plus grand nombre, plus grande
distance et plus grand delta) */
int max_nombre, max_distance, max_delta;

/*VARIABLES FOR ADVISING AND ACCUSTOMISAION TO GAME*/
int without_advise = 0, firstgame = 1;
/* Modele de l'apprenant A COMPLETER */
typedef struct {
	int choices, bad_choices, good_choices, collisions, villes, shorcuts; //VARIABLES FOR CALCULATIONG LUCK AND SKILL
	float luck, skill;
} t_modele;

t_modele modele_appr;


/*--------------------------------------------------------*/

void attendre() {
	/* Pause entre deux tours de jeu */
	char rc;
	printf("Tapez entree pour continuer ");
	scanf_s("%c", &rc);
}

void tirage_aleatoire(int* a, int* b, int* c) {
	/* Tire au hasard trois nombres:
		a compris entre 1 et 3
		b compris entre 0 et 4
		c compris entre 1 et 7
	*/
	*a = rand() % 3 + 1;
	*b = rand() % 5;
	*c = rand() % 7 + 1;
}

void expert(int a, int b, int c, int pion1, int pion2) {
	/* Calcule tous les d�placements possibles de pion1
		en fonction des trois chiffres tir�s au hasard */

	int i;
	int nouv_posit_pion2;

	table[1].val_expr = a * b + c;
	table[2].val_expr = a * c + b;
	table[3].val_expr = c * b + a;
	table[4].val_expr = (a + b)*c;
	table[5].val_expr = (a + c)*b;
	table[6].val_expr = (c + b)*a;
	table[7].val_expr = a * b - c;
	table[8].val_expr = a * c - b;
	table[9].val_expr = c * b - a;
	table[10].val_expr = a + b - c;
	table[11].val_expr = a + c - b;
	table[12].val_expr = c + b - a;

	for (i = 1; i < NB_EXPR; i++) {
		/* Le pion avance du nombre de cases donn� par
		l'expression form�e avec les trois chiffres,
		sans reculer au-del� de la case de d�part	*/
		if (pion1 + table[i].val_expr > 0)
			table[i].nouv_posit = pion1 + table[i].val_expr;
		else
			table[i].nouv_posit = 0;

		/* Cas de d�passement de l'arriv�e : retour arri�re */
		if (table[i].nouv_posit > 70)
			table[i].nouv_posit = 140 - table[i].nouv_posit;

		/* Cas des raccourcis en cases 5, 25 et 44*/
		if (table[i].nouv_posit == 5) {
			table[i].nouv_posit = 13;
			table[i].raccourci = VRAI;
		}
		else if (table[i].nouv_posit == 25) {
			table[i].nouv_posit = 36;
			table[i].raccourci = VRAI;
		}
		else if (table[i].nouv_posit == 44) {
			table[i].nouv_posit = 54;
			table[i].raccourci = VRAI;
		}
		else
			table[i].raccourci = FAUX;

		/* Cas des villes: on passe � la suivante*/
		if (table[i].nouv_posit % 10 == 0 && table[i].val_expr != 0 && table[i].nouv_posit != 70 && table[i].nouv_posit != 0) {
			table[i].nouv_posit += 10;
			table[i].ville = VRAI;
		}
		else
			table[i].ville = FAUX;


		/* Cas de collision avec l'autre pion :
		   il est renvoy� deux villes en arri�re */
		if (table[i].nouv_posit == pion2 && !table[i].ville && table[i].val_expr != 0) {
			nouv_posit_pion2 = pion2 - pion2 % 10 - 10;
			if (nouv_posit_pion2 < 0)
				nouv_posit_pion2 = 0;
			table[i].nouv_posit_adv = nouv_posit_pion2;
			table[i].collision = VRAI;
		}
		else {
			table[i].nouv_posit_adv = pion2;
			table[i].collision = FAUX;
		}
	}

	/* Calcul du maximum possible
		- pour la valeur de l'expression (max_nombre)
		- pour la distance parcourue (max_distance)
		- pour la distance entre les deux pions (max_delta)
	*/
	max_nombre = table[1].val_expr;
	max_distance = table[1].nouv_posit - pion1;
	max_delta = table[1].nouv_posit - table[1].nouv_posit_adv;
	meilleur = 1;

	for (i = 2; i < NB_EXPR; i++) {
		if (table[i].val_expr > max_nombre)
			max_nombre = table[i].val_expr;
		if (table[i].nouv_posit - pion1 > max_distance)
			max_distance = table[i].nouv_posit - pion1;
		if (table[i].nouv_posit - table[i].nouv_posit_adv > max_delta) {
			max_delta = table[i].nouv_posit - table[i].nouv_posit_adv;
			if (table[meilleur].nouv_posit != 70)
				meilleur = i;
		}
		/* Un coup gagnant est toujours le meilleur */
		if (table[i].nouv_posit == 70)
			meilleur = i;
	}
}

void maj_modele_apprenant(int choix, int pion_joueur) {
	modele_appr.choices++;
	if (table[choix].collision) {
		modele_appr.collisions++;
	}
	if (table[choix].ville) {
		modele_appr.villes++;
	}
	if (table[choix].raccourci) {
		modele_appr.shorcuts++;
	}
}

void afficher_modele_apprenant() {
	//CALCULATING
	modele_appr.luck = (float)((modele_appr.villes + modele_appr.shorcuts)) / (float)(modele_appr.collisions + modele_appr.villes + modele_appr.shorcuts);
	//PERCENTAGES
	modele_appr.skill = (float) modele_appr.good_choices / (float)modele_appr.choices;
	
	printf("So, at the end\nYou did %i bad decisions and %i - good.\nYou visited city %i time, used shortcuts %i times\nand beat you enemy in face by collision %i times.\nYour luck is %g percents\nand skill is %g percents\n", modele_appr.bad_choices, modele_appr.good_choices, modele_appr.villes, modele_appr.shorcuts, modele_appr.collisions, modele_appr.luck * 100, modele_appr.skill * 100);
	/* Affichage du mod�le du joueur en fin de partie*/
}

int conseil(int choix) {

	int choix_max = choix;

	for (int i = 1; i < 13; i++)
	{
		//IS PLAYER'S DECISION THE MEST?
		if (table[i].nouv_posit > table[choix].nouv_posit) choix_max = i;
	}

	return choix_max;
	/* Affiche un conseil si le joueur a fait un mauvais choix */

}

int saisie_choix_joueur_avec_total(int a, int b, int c) {
	/* Saisie de l'expression choisie par le joueur
		� partir des trois nombres tir�s au hasard */

	int choix;

	do
	{
		printf(" 1: %i*%i+%i (%i)  7: %i*%i-%i (%i)\n", a, b, c, a*b + c, a, b, c, a*b - c);
		printf(" 2: %i*%i+%i (%i)  8: %i*%i-%i (%i)\n", a, c, b, a*c + b, a, c, b, a*c - b);
		printf(" 3: %i*%i+%i (%i)  9: %i*%i-%i (%i)\n", c, b, a, c*b + a, c, b, a, c*b - a);
		printf(" 4: (%i+%i)*%i (%i)  10: %i+%i-%i (%i)\n", a, b, c, (a + b)*c, a, b, c, a + b - c);
		printf(" 5: (%i+%i)*%i (%i)  11: %i+%i-%i (%i)\n", a, c, b, (a + c)*b, a, c, b, a + c - b);
		printf(" 6: (%i+%i)*%i (%i)  12: %i+%i-%i (%i)\n", c, b, a, (c + b)*a, c, b, a, c + b - a);
		printf("Choisis une expression (entre 1 et 12): ");
		scanf_s("%i%*c", &choix);

	} while (choix<1 || choix>NB_EXPR - 1);

	return choix;
}

int saisie_choix_joueur_sans_total(int a, int b, int c) {
	/* Saisie de l'expression choisie par le joueur
		� partir des trois nombres tir�s au hasard */

	int choix;

	do
	{
		printf(" 1: %i*%i+%i  7: %i*%i-%i\n", a, b, c, a, b, c);
		printf(" 2: %i*%i+%i  8: %i*%i-%i\n", a, c, b, a, c, b);
		printf(" 3: %i*%i+%i  9: %i*%i-%i\n", c, b, a, c, b, a);
		printf(" 4: (%i+%i)*%i  10: %i+%i-%i\n", a, b, c, a, b, c);
		printf(" 5: (%i+%i)*%i  11: %i+%i-%i\n", a, c, b, a, c, b);
		printf(" 6: (%i+%i)*%i  12: %i+%i-%i\n", c, b, a, c, b, a);
		printf("Choisis une expression (entre 1 et 12): ");
		scanf_s("%i%*c", &choix);

	
	} while (choix<1 || choix>NB_EXPR - 1);

	return choix;
}

void tour_joueur(int* pion_joueur, int* pion_west) {
	/* Tour de jeu du joueur */

	int a, b, c;
	int choix;

	tirage_aleatoire(&a, &b, &c);
	printf("Nombres : %i %i %i\n", a, b, c);

	/* Saisie de l'expression choisie par le joueur */
	
	
	if (DEBOG)
		choix = saisie_choix_joueur_avec_total(a, b, c);
	else
		choix = saisie_choix_joueur_sans_total(a, b, c);
	
	/* Calcul de tous les d�placements possibles
	pour comparaison avec le choix du joueur */
	expert(a, b, c, *pion_joueur, *pion_west);

	/* Mise � jour du mod�le de l'apprenant */
	//WE ALWAYS CHECKING FOR BEST POSSIBILITY, BUT NOT ALWAYS SHOW IT TO PLAYER
	int best = conseil(choix), undo = 0;
	if (firstgame)
	{
		//WAIT UNTIL PLAYER WIN OR LOSE FIRST TIME
		printf("Yes, go on, end this first game and I'll show you the truth\n");
	}
	else
	{
		//WE DON'T ASK PLAYER ABOUT REDO SMTH IF IT'S JUST AFTER REDOING
		if (without_advise)
		{
			if (best != choix)
			{
				modele_appr.bad_choices++;
				//JOKE
				printf("Also, you may did better choice\nBut today's without advices, ha-ha\n");
			}
			else
			{
				//CHEER UP PLAYER
				printf("Also, you decision was the best\nGood job, mate!\n\n");
				modele_appr.good_choices++;
			}
			without_advise = 0;
		}
		else
		{
			//IF IT'S TIME, PLAYER HAS POSSIBILITY TO CHOOSE AGAIN AND DO BETTER DECISION
			if (best != choix)
			{
				modele_appr.bad_choices++;
				//HERE IT IS
				printf("Also, you may did better choice\nDo you want undo it and try one more time? (y/n)");
				char c;
				scanf("%c%*c", &c);
				if (c == 'y')undo = 1;
			}
			else
			{
				printf("Also, you decision was the best\nGood job, mate!\n\n");
				modele_appr.good_choices++;
			}
			//PROPERLY HERE'S NEW DECISION INSTEAD OF OLD
			if (undo)
			{
				if (DEBOG)
					choix = saisie_choix_joueur_avec_total(a, b, c);
				else
					choix = saisie_choix_joueur_sans_total(a, b, c);
				without_advise = 1;
			}
		}
	}
	

	//choix = saisie_choix_joueur_sans_total(a, b, c);
	maj_modele_apprenant(choix, *pion_joueur);

/* Donner un conseil au joueur s'il a mal jou� */

	

	/* D�placement du pion du joueur*/
	printf("Ton expression vaut %i\n", table[choix].val_expr);
	*pion_joueur += table[choix].val_expr;
	if (*pion_joueur < 0)
		*pion_joueur = 0;
	if (*pion_joueur > 70)
		*pion_joueur = 140 - *pion_joueur;
	printf("Ton pion avance en %i\n", *pion_joueur);

	if (table[choix].raccourci) {
		printf("Tu tombes sur un raccourci\n");

		if (*pion_joueur == 5)
			*pion_joueur = 13;
		else if (*pion_joueur == 25)
			*pion_joueur = 36;
		else if (*pion_joueur == 44)
			*pion_joueur = 54;

		printf("Ton pion avance en %i\n", *pion_joueur);
	}

	if (table[choix].ville) {
		printf("Tu tombes sur une ville et tu passes a la suivante\n");
		*pion_joueur += 10;
		printf("Ton pion avance en %i\n", *pion_joueur);
	}

	if (table[choix].collision) {
		printf("Ton pion arrive sur la meme case que moi\n");
		*pion_west = table[choix].nouv_posit_adv;
		printf("Mon pion recule en %i\n", *pion_west);
	}

	
}

void tour_west(int* pion_joueur, int* pion_west) {
	/* Tour de jeu de l'ordinateur */

	int a, b, c;

	tirage_aleatoire(&a, &b, &c);
	printf("Nombres : %i %i %i\n", a, b, c);

	/* Calcul de tous les d�placements possibles
	ainsi que du meilleur (strat�gie "delta maximum") */
	expert(a, b, c, *pion_west, *pion_joueur);

	/* Deplacement du pion de West */

	printf("Je choisis l'expression : ");
	switch (meilleur)
	{
	case 1: printf("%i*%i+%i\n", a, b, c); break;
	case 2: printf("%i*%i+%i\n", a, c, b); break;
	case 3: printf("%i*%i+%i\n", c, b, a); break;
	case 4: printf("(%i+%i)*%i\n", a, b, c); break;
	case 5: printf("(%i+%i)*%i\n", a, c, b); break;
	case 6: printf("(%i+%i)*%i\n", c, b, a); break;
	case 7: printf("%i*%i-%i\n", a, b, c); break;
	case 8: printf("%i*%i-%i\n", a, c, b); break;
	case 9: printf("%i*%i-%i\n", c, b, a); break;
	case 10: printf("%i+%i-%i\n", a, b, c); break;
	case 11: printf("%i+%i-%i\n", a, c, b); break;
	case 12: printf("%i+%i-%i\n", c, b, a); break;
	}
	printf("Mon expression vaut %i\n", table[meilleur].val_expr);







	*pion_west += table[meilleur].val_expr;
	if (*pion_west > 70)
		*pion_west = 140 - *pion_west;
	printf("Mon pion avance en %i\n", *pion_west);

	if (table[meilleur].raccourci) {
		printf("Je tombe sur un raccourci\n");

		if (*pion_west == 5)
			*pion_west = 13;
		else if (*pion_west == 25)
			*pion_west = 36;
		else if (*pion_west == 44)
			*pion_west = 54;

		printf("Mon pion avance en %i\n", *pion_west);
	}

	if (table[meilleur].ville) {
		printf("Je tombe sur une ville et je passe a la suivante\n");
		*pion_west += 10;
		printf("Mon pion avance en %i\n", *pion_west);
	}

	if (table[meilleur].collision) {
		printf("Mon pion arrive sur la meme case que toi\n");
		*pion_joueur = table[meilleur].nouv_posit_adv;
		printf("Ton pion recule en %i\n", *pion_joueur);
	}
}

/*--------------------------------------------------------*/

int main() {

	modele_appr.choices = modele_appr.bad_choices = modele_appr.good_choices = modele_appr.collisions = modele_appr.shorcuts = modele_appr.villes = modele_appr.luck = 0;

	/* Position des pions sur le parcours et scores*/
	int pion_joueur, score_joueur = 0;
	int pion_west, score_west = 0;

	/* Pour jouer une autre partie */
	char reponse;

	/* Initialisation du tirage pseudo-al�atoire */
	srand(time(0));

	/* D�but du jeu */
	do {
		pion_joueur = 0;
		pion_west = 0;
		do {
			printf("\nA ton tour de jouer! ");
			printf("Tu es en %i\n", pion_joueur);
			tour_joueur(&pion_joueur, &pion_west);
			attendre();

			if (pion_joueur < 70) {
				printf("\nA mon tour de jouer! ");
				printf("Je suis en %i\n", pion_west);
				tour_west(&pion_joueur, &pion_west);
				attendre();
			}
		} while (pion_joueur < 70 && pion_west < 70);

		
		/* Fin de partie */
		if (pion_joueur == 70) {
			score_joueur++;
			printf("\nBravo, tu as gagne!\n");
		}
		else {
			score_west++;
			printf("\nJ'ai gagne!\n");
		}
		if (!firstgame) afficher_modele_apprenant();
		firstgame = 0;
		printf("Joueur : %i, ", score_joueur);
		printf("West : %i\n", score_west);
		
		printf("Veux-tu jouer une autre partie (o/n) ? ");
		scanf("%c%*c", &reponse);
	} while (reponse != 'n');

}