#include <pthread.h> 
#include <stdio.h> 
#include <math.h> 
#include <stdlib.h> 
#include <semaphore.h> 
#include <unistd.h>
#define M 4
#define N 3
unsigned int iter;
sem_t accessM, readresM, orderM;
unsigned int readers = 0;

void * reader(void * prm) {
 int num1 = * (int * ) prm;
 int i = 0, r;
 for (i; i < iter; i++) {

  if (sem_wait( & orderM) == 0) printf("%d Lecteur %d est dans la queue __________ L%d\n", i, num1, num1);
  sem_wait( & readresM);
  if (readers == 0)
   sem_wait( & accessM);
  readers++;
  sem_post( & orderM);
  sem_post( & readresM);

  printf("%d Lecteur fonctionne %d________________L%d\n", i, num1, num1);
  r = 1 + rand() % 4;
  sleep(r);
  sem_wait( & readresM);
  readers--;
  if (readers == 0)
   sem_post( & accessM);
  sem_post( & readresM);
 }
}

void * writer(void * prm) {
 int num2 = * (int * ) prm;
 int j = 0, r;
 for (j; j < iter; j++) {
  if (sem_wait( & orderM) == 0) printf("%d Redacteur est dans la queue%d__________R%d\n", j, num2, num2);
  sem_wait( & accessM);
  sem_post( & orderM);

  printf("%d Redacteur fonctionne%d__________R%d\n", j, num2, num2);
  r = 1 + rand() % 4;
  sleep(r);
  sem_post( & accessM);
 }
}

void main() {
 pthread_t threadRE[N];
 pthread_t threadWR[M];
 sem_init( & accessM, 0, 1);
 sem_init( & readresM, 0, 1);
 sem_init( & orderM, 0, 1);

 printf("Entrez le nombre d'iterations: ");
 scanf("%d", & iter);
 printf("Iter                         QUEUE / PERFORMANCE\n");
 int i;
 for (i = 0; i < M; i++) {
  pthread_create( & (threadWR[i]), NULL, writer, (void * ) & i);
 }
 for (i = 0; i < N; i++) {
  pthread_create( & (threadRE[i]), NULL, reader, (void * ) & i);
 }


 for (i = 0; i < N; i++) {
  pthread_join(threadRE[i], NULL);
 }
 for (i = 0; i < M; i++) {
  pthread_join(threadWR[i], NULL);
 }

 sem_destroy( & accessM);
 sem_destroy( & readresM);
 sem_destroy( & orderM);
}