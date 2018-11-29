#include <sys/types.h> 
#include <sys/sem.h> 
#include <sys/ipc.h> 
#include <sys/shm.h>

 #include <unistd.h> 
 #include <pthread.h> 
 #include <semaphore.h> 
 #include <stdio.h>

 #define N 5
 #define PENSER 2
 #define AFFAME 1
 #define MANGER 0
 #define GAUCHE (phnum + 4) % N
 #define DROITE (phnum + 1) % N

int state[N];
int phil[N] = {
 0,
 1,
 2,
 3,
 4
};

sem_t mutex;
sem_t S[N];

void test(int phnum) {
 if (state[phnum] == AFFAME &&
  state[GAUCHE] != MANGER &&
  state[DROITE] != MANGER) {
  state[phnum] = MANGER;

  sleep(2);

  printf("Philosophe %d prend la fourche %d and %d reporte\n",
   phnum + 1, GAUCHE + 1, phnum + 1);

  printf("Philosophe %d est en train de manger\n", phnum + 1);
  sem_post(&S[phnum]);
 }
}

void take_fork(int phnum) {

 sem_wait( &mutex);

 state[phnum] = AFFAME;

 printf("Philosophe %d a faim\n", phnum + 1);

 test(phnum);

 sem_post(&mutex);

 sem_wait(&S[phnum]);

 sleep(1);
}

void put_fork(int phnum) {

 sem_wait(&mutex);

 state[phnum] = PENSER;

 printf("Philosophe %d prend la fourche %d and %d reporte\n",  phnum + 1, GAUCHE + 1, phnum + 1);
 printf("Philosophe %d pense\n", phnum + 1);

 test(GAUCHE);
 test(DROITE);

 sem_post(&mutex);
}

void *philospher(void *num) {

 while (1) {

  int *i = num;

  sleep(1);

  take_fork(*i);

  sleep(0);

  put_fork(*i);
 }
}

int main() {

 int i;
 pthread_t thread_id[N];

 sem_init(&mutex, 0, 1);

 for (i = 0; i < N; i++)

  sem_init(&S[i], 0, 0);

 for (i = 0; i < N; i++) {

  pthread_create(&thread_id[i], NULL,   philospher, &phil[i]);

  printf("Philosophe %d pense\n", i + 1);
 }

 for (i = 0; i < N; i++)

  pthread_join(thread_id[i], NULL);
}