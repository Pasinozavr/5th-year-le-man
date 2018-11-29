#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <unistd.h>
#include <signal.h>
#include <ecran.h>
#include <pthread.h>

booleen_t fini = FAUX ; 
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER ;
static aire_t * A = NULL ;
static ecran_t * ecran = NULL ;
static liste_t * liste_billes = LISTE_NULL ;
static err_t cr = OK ;

char mess[ECRAN_LG_MESS] ;
int b ;
int NbBilles ;
  
static
void arret( int sig ) 
{
  static int cpt = 0 ; 
  
  cpt++ ; 

  switch( cpt ) 
    {
    case 1 :
      break ; 
    case 2 :
      break ;
    case 3 :
      fini = VRAI ; 
      break ;
    default :
      break  ; 
    }
  return ; 
}



void fonc (int i)
{
  bille_t * bille = liste_elem_lire( liste_billes , i) ; 
  
  while( ! aire_vide(A) )
    { 
      pthread_mutex_lock(&mutex);

	  if( ecran_bille_deplacer( ecran , A, bille ) ) 
	    {
	      sprintf( mess ,  "Pb lors du deplacement de la bille %d", b ) ;
	      ecran_message_pause_afficher( ecran , mess ) ; 
	      ecran_stderr_afficher( ecran ) ;
        pthread_mutex_unlock(&mutex) ;
	    }	
	  
	  if( bille_active(bille) )
	    {
        pthread_mutex_unlock(&mutex) ;
	      /* Arret sur image de ce mouvement pendant une duree inversement proportionnelle a sa vitesse */
	      ecran_bille_temporiser(bille) ; 
	    }
	  else
	    {
	      /* Desintegration de la bille */
	      sprintf( mess , "Desintegration de la bille %d" , b ) ;
	      ecran_message_afficher( ecran , mess ) ; 
	      ecran_bille_desintegrer( ecran , bille ) ;

	      /* On enleve cete bille de l'aire */
		  if( aire_bille_enlever( A , bille ) )
		{
		  pthread_mutex_unlock(&mutex) ;
		  sprintf( mess , "Pb lors de l'enlevement de la bille %d sur l'aire", b ) ; 
		  ecran_message_pause_afficher( ecran , mess ) ; 
		  ecran_stderr_afficher( ecran ) ;
		}
	      if( aire_bille_detruire( A , &bille ) )
		{
			pthread_mutex_unlock(&mutex) ;
			sprintf( mess , "Pb lors de la destruction de la bille %d", b ) ; 
			ecran_message_pause_afficher( ecran , mess ) ; 
			ecran_stderr_afficher( ecran ) ;
		}
          pthread_mutex_unlock(&mutex);
	    }
    }
}


int
main( int argc , char * argv[] ) 
{

  signal( SIGINT , arret ) ; 

  printf("\n\n\n\t===========Debut %s==========\n\n" , argv[0] );

  if( argc != 4 ) 
    {
      printf( " Programme de test sur l'affichage de l'aire avec plusieurs billes\n" );
      printf( " usage : %s <Hauteur de l'aire> <Largeur de l'aire> <nb billes>\n" , argv[0] );
      exit(0); 
    }

  int Hauteur  = atoi( argv[1] ) ;
  int Largeur  = atoi( argv[2] ) ; 
  int NbBilles = atoi( argv[3] ) ; 
  
  if ( Hauteur * Largeur <= NbBilles )
  {
  	 NbBilles = Hauteur*Largeur;
  	 printf ("Vous pouvez creé billes = %d * %d = %d", Hauteur , Largeur , NbBilles);
  } 
  
  printf( " \nTest sur une aire de %dX%d et %d billes\n" , Hauteur , Largeur , NbBilles );

  srand( getpid() ) ;

  /* Creation et affichage de l'aire */
  printf("Creation de l'aire %dX%d\n" , Hauteur , Largeur ) ;
  if( ( A = aire_creer( Hauteur , Largeur ) ) == AIRE_NULL )
    {
      printf("Pb creation aire\n");
      exit(-1) ;
    }

  aire_afficher(A) ; 

  /* Creation et affichage de l'ecran */
  if( ( ecran = ecran_creer(A) ) == ECRAN_NULL )
    {
      printf("Pb creation ecran\n");
      exit(-1) ;
    }

  ecran_afficher(  ecran , A )  ;
  ecran_message_afficher( ecran , "Envoyez un signal pour continuer" ) ;
  pause() ;

  /* Creation des billes */
  sprintf( mess , "Creation des %d billes\n" , NbBilles) ;
  ecran_message_pause_afficher( ecran , mess ); 

  bille_t ** tab_billes = (bille_t **)malloc( sizeof(bille_t *) * NbBilles ) ; 
  int b ;
  for( b=0 ; b<NbBilles ; b++ )
    {
      if( ( tab_billes[b] = bille_creer( direction_random() ,
					 rand()%BILLE_VITESSE_MAX , 
					 COORD_NULL , 
					 '*' ) ) == BILLE_NULL )
	{
	  sprintf( mess , "Pb creation bille bille %d\n" , b );
	  ecran_message_pause_afficher( ecran , mess  ) ; 
	  exit(-1) ;
	}
    }

  /* Positionnements et affichages des billes */
  ecran_message_pause_afficher( ecran , "Positionnement des billes sur l'aire" ) ;
  for( b=0 ; b<NbBilles ; b++ ) 
    {
      sprintf( mess , "Positionnement de la bille %d\n" , b ) ; 
      ecran_message_afficher( ecran , mess ) ; 
      if( ( cr = aire_bille_positionner( A , tab_billes[b] ) ) )
	{
	  sprintf( mess , "Pb lors du positionnement de la bille %d" , b ) ; 
	  ecran_message_pause_afficher( ecran , mess ) ;
	  erreur_afficher(cr) ; 
	  ecran_stderr_afficher(ecran) ; 
	  exit(-1) ; 
	}
      sprintf( mess , "Positionnement de la bille numero %d en [%d,%d]"  , 
	       b , coord_lig_lire(bille_coord_lire(tab_billes[b])) , coord_col_lire(bille_coord_lire(tab_billes[b])) ) ; 
      ecran_message_pause_afficher( ecran ,  mess ) ;
    }

  ecran_message_pause_afficher( ecran , "Poses des billes sur l'ecran" ) ;
  for( b=0 ; b<NbBilles ; b++ ) 
    {
      sprintf( mess , "Pose de la bille %d\n" , b ) ; 
      ecran_message_afficher( ecran , mess ) ; 
      if( ( cr = ecran_bille_poser( ecran , A , tab_billes[b] ) ) )
	{
	  sprintf( mess , "Pb lors de la pose de la bille %d" , b ) ; 
	  ecran_message_pause_afficher( ecran , mess ) ;
	  erreur_afficher(cr) ; 
	  ecran_stderr_afficher(ecran) ; 
	  exit(-1) ; 
	}
    }

  ecran_message_afficher( ecran , "Envoyez un signal pour continuer" ) ;
  pause() ;

  /* Deplacements des billes l'une apres l'autre */
  ecran_message_pause_afficher( ecran , "Test deplacement des billes, (Deplacements jusqu'a un signal)" ) ;

   
  pthread_t thread_id[NbBilles];
  pthread_attr_t attr;
  pthreqd_attr_init(&attr);
  pthread_attr_setscope(&attr, PTHREAD_SCOPE_SYSTEM);
  pthread_setconcurrency(NbBilles);
  for (b = 0; b < NbBilles; b++)
    pthread_create(&thread_id[b], &attr, (void*)fonc, (void*)b);
  for (b = 0; b < NbBilles; b++)
  	thread_join(thread_id[b], NULL);

  pthread_mutex_destroy(&mutex) ;
  
  ecran_message_pause_afficher( ecran , "Arret" ) ; 

  ecran_message_pause_afficher( ecran , "Destruction de la structure ecran" ) ; 
  ecran_detruire(&ecran)  ; 

  printf("\nDestruction aire\n" ) ; 
  aire_detruire( &A)  ; 

  printf("\n\n\t===========Fin %s==========\n\n" , argv[0] );

  return(0) ;
}
