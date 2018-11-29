#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/sem.h>

#define TANTQUE 2

int nourriture[5];
struct sembuf op;

int philosopher(int n)
{
    int premier, seconde;
    op.sem_num = 0;
    op.sem_flg = 0;

    if(n < 4)
    	{premier = n;}
    else premier=0;
    
    if(n< 4)
    	{seconde = n + 1;}
    else seconde = 4;


    for(int i = 0; i < TANTQUE; i++) {
        printf("Philosophe %d est en train de penser\n", n);
        op.sem_op = -1; 
        semop(nourriture[premier], &op, 1);
        op.sem_op = -1; 
        semop(nourriture[seconde], &op, 1);

        printf("Philosophe %d est en train de manger\n", n);
        op.sem_op = +1; 
        semop(nourriture[premier], &op, 1);
        op.sem_op = +1; 
        semop(nourriture[seconde], &op, 1);
    }

    exit(n);
}

int main()
{
    pid_t phil[5];

    for(int i = 0; i < 5; i++) {
        if((nourriture[i] = semget(IPC_PRIVATE, 1, IPC_CREAT | 0600)) < 0)
            return -1;
        if(semctl(nourriture[i], 0, SETVAL, 1) < 0)
            return -1;
    }

    for(int i = 0; i < 5; i++)
    {
    	int t = phil[i] = fork();
        if(t == 0) exit(philosopher(i));
    }

    for(int i = 0; i < 5; i++)
        semctl(nourriture[i], 0, IPC_RMID, 0);

    return 0;
}
