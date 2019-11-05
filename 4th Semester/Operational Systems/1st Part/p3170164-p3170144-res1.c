#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include "p3170164-p3170144-res1.h"
#include <unistd.h>

#define length(x)  (sizeof(x) / sizeof(int))
#define max_cust 1000

pthread_mutex_t mutexPrint;
pthread_mutex_t mutexWaitTime;
pthread_mutex_t mutexServeTime;
pthread_mutex_t mutexTID;
pthread_mutex_t mutexTel;
pthread_mutex_t mutexSeat;
pthread_mutex_t mutexBank;
pthread_mutex_t mutexAvailableSeats;
pthread_mutex_t mutexArray;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER; 

int nCust;
int tID = 0;
int profit = 0;
int varnTel = nTel;
int Array[nSeat];
unsigned int seed;
struct timespec waitTimeStart;
struct timespec waitTimeEnd;
struct timespec serveTimeStart;
struct timespec serveTimeEnd;
struct timepsec waitTimeTotal;
struct timespec serveTimeTotal;

typedef struct info
{
	int threadID;
	int numberOfSeats;
} INFO;

int printRand(int high, int low)
{
	int random = ((rand_r(&seed) % high) + low);
	return random;
}

void *bookSeat(void *x)
{
	INFO *customerInfo;
	customerInfo = (INFO *) x;

	int rc;
	int availableSeats = length(Array);

	rc = pthread_mutex_lock(&mutexTel);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}

	while (varnTel == 0)
	{
		printf("Our call lines are busy. Please try later.\n");
		rc = pthread_cond_wait(&cond, &mutexTel);
	}

	printf("Customer %d is currently being served.\n", customerInfo->threadID);
	varnTel--;
	rc = pthread_mutex_unlock(&mutexTel);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	
	customerInfo->numberOfSeats = printRand(nSeatHigh, nSeatLow);
	printf("Number of seats : %d\n", customerInfo->numberOfSeats);

	if (availableSeats == 0)
	{
		rc = pthread_mutex_lock(&mutexPrint);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}
		printf("We are sorry to inform you that the theatre is full.\n");
		rc = pthread_mutex_unlock(&mutexPrint);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}

		rc = pthread_mutex_lock(&mutexTel);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}
		varnTel++;
		rc = pthread_mutex_unlock(&mutexTel);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}

		rc = pthread_cond_signal(&cond);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}

		pthread_exit((int *) customerInfo->threadID);
	}
	else if (availableSeats >= customerInfo->numberOfSeats)
	{
		int wait = printRand(tSeatHigh, tSeatLow);
		clock_gettime(CLOCK_REALTIME, &waitTimeStart);
		sleep(wait);
		clock_gettime(CLOCK_REALTIME, &waitTimeEnd);
		waitTimeTotal = waitTimeEnd - waitTimeStart;

		clock_gettime(CLOCK_REALTIME, &serveTimeStart);
		
		int i;
		int j;
		int counter = 0;
		for (i = 0; i < customerInfo->numberOfSeats; i++)
		{
			for (j = 0; j < length(Array); j++)
			{	
				if (Array[j] == 0 && counter < customerInfo->numberOfSeats)
				{
					rc = pthread_mutex_lock(&mutexArray);
					if (rc != 0)
					{
						printf("ERROR! rc isn't 0. %d\n", rc);
						exit(-1);
					}
					Array[j] = customerInfo->threadID;
					counter++;
					rc = pthread_mutex_unlock(&mutexArray);
					if (rc != 0)
					{
						printf("ERROR! rc isn't 0. %d\n", rc);
						exit(-1);
					}	
					rc = pthread_mutex_lock(&mutexAvailableSeats);
					if (rc != 0)
					{
						printf("ERROR! rc isn't 0. %d\n", rc);
						exit(-1);
					}
					availableSeats--;
					rc = pthread_mutex_unlock(&mutexAvailableSeats);
					if (rc != 0)
					{
						printf("ERROR! rc isn't 0. %d\n", rc);
						exit(-1);
					}
				}
			}	
		}
		counter = 0;

		int paymentAccepted = printRand(10, 1);
		if (paymentAccepted > 1)
		{
			rc = pthread_mutex_lock(&mutexPrint);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			printf("Payment was successful! ");
			rc = pthread_mutex_unlock(&mutexPrint);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			rc = pthread_mutex_lock(&mutexBank);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			profit += cSeat*customerInfo->numberOfSeats;
			rc = pthread_mutex_unlock(&mutexBank);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}

			/*rc = pthread_mutex_lock(&mutexTel);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			varnTel++;
			rc = pthread_mutex_unlock(&mutexTel);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}

			rc = pthread_cond_signal(&cond);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}*/
			rc = pthread_mutex_lock(&mutexPrint);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			printf("Reservation was completed successfully.\n Transaction number is : %d,", tID);
			printf(" your seats are ");
			
			int g;
			for(g = 0; g < length(Array); g++)
			{
				if (Array[g] == customerInfo->threadID)
				{
					printf("%d ", g + 1);
				}
			}
			printf("and the transaction cost is : %d\n", cSeat * customerInfo->numberOfSeats);
			rc = pthread_mutex_unlock(&mutexPrint);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}

			rc = pthread_mutex_lock(&mutexTID);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			tID++;
			rc = pthread_mutex_unlock(&mutexTID);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}

			rc = pthread_mutex_lock(&mutexTel);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			varnTel++;
			rc = pthread_mutex_unlock(&mutexTel);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}

			rc = pthread_cond_signal(&cond);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			clock_gettime(CLOCK_REALTIME, &serveTimeEnd);
			serveTimeTotal = serveTimeEnd - serveTimeStart;
		}
		else
		{
			rc = pthread_mutex_lock(&mutexPrint);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			printf("Reservastion Failed. Transaction with credit card was not accepted.\n");
			rc = pthread_mutex_unlock(&mutexPrint);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			int f;
			for(f = 0; f < length(Array); f++)
			{
				if (Array[f] == customerInfo->threadID)
				{
					rc = pthread_mutex_lock(&mutexArray);
					if (rc != 0)
					{
						printf("ERROR! rc isn't 0. %d\n", rc);
						exit(-1);
					}
					Array[f] = 0;
					rc = pthread_mutex_unlock(&mutexArray);
					if (rc != 0)
					{
						printf("ERROR! rc isn't 0. %d\n", rc);
						exit(-1);
					}
					rc = pthread_mutex_lock(&mutexAvailableSeats);
					if (rc != 0)
					{
						printf("ERROR! rc isn't 0. %d\n", rc);
						exit(-1);
					}
					availableSeats++;
					rc = pthread_mutex_unlock(&mutexAvailableSeats);
					if (rc != 0)
					{
						printf("ERROR! rc isn't 0. %d\n", rc);
						exit(-1);
					}
				}
			}
			rc = pthread_mutex_lock(&mutexTel);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			varnTel++;
			rc = pthread_mutex_unlock(&mutexTel);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}

			rc = pthread_cond_signal(&cond);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}

			clock_gettime(CLOCK_REALTIME, &serveTimeEnd);
			serveTimeTotal = serveTimeEnd - serveTimeStart;

			pthread_exit((int *) customerInfo->threadID);
		}
	}
	else
	{
		rc = pthread_mutex_lock(&mutexPrint);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}
		printf("We are sorry to inform you that there aren't enough seats.\n");
		rc = pthread_mutex_unlock(&mutexPrint);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}

		rc = pthread_mutex_lock(&mutexTel);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}
		varnTel++;
		rc = pthread_mutex_unlock(&mutexTel);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}
	
		rc = pthread_cond_signal(&cond);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}
		pthread_exit((int *) customerInfo->threadID);
	}

	/*rc = pthread_mutex_lock(&mutexTel);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	varnTel++;
	rc = pthread_mutex_unlock(&mutexTel);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}

	rc = pthread_cond_signal(&cond);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}

	pthread_exit((int *) customerInfo->threadID);*/
}

int main(int argc, char *argv[])
{
	INFO customerInfo;
	INFO countArray[max_cust];
	int rc;

	if (argc != 3)
	{
		printf("Error! Incorrect number of arguments!\n");
		exit(-1);
	}

	nCust = atoi(argv[1]);
	seed = atoi(argv[2]);

	if (nCust <= 0)
	{
		printf("Error! Number of customers can't be negative!\n");
		exit(-1);
	}
	else if(seed < 0)
	{
		printf("Error! Seed can't be negative!\n");
		exit(-1);
	}

	rc = pthread_mutex_init(&mutexPrint, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexWaitTime, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexServeTime, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexTID, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexTel, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexSeat, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexBank, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexAvailableSeats, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexArray, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}

	int i;
	for (i = 0; i < length(Array); i++)
	{
		Array[i] = 0;
	}

	pthread_t *thread;
	thread = malloc(nCust * sizeof(pthread_t));

	if (thread == NULL)
	{
		printf("NOT ENOUGH MEMORY!\n");
		return -1;
	}

	int threadCount;
   	for(threadCount = 0; threadCount < nCust; threadCount++)
	{
    	printf("Main: creating thread %d\n", threadCount);
		countArray[threadCount] = customerInfo;
		countArray[threadCount].threadID = threadCount + 1;
    	rc = pthread_create(&thread[threadCount], NULL, bookSeat, &countArray[threadCount]);
   		if (rc != 0)
		{
   			printf("ERROR: return code from pthread_create() is %d\n", rc);
    		exit(-1);
       	}
    }

	void *status;
	for (threadCount = 0; threadCount < nCust; threadCount++)
	{
		rc = pthread_join(thread[threadCount], &status);
	
		if (rc != 0)
		{
			printf("ERROR: return code from pthread_join() is %d\n", rc);
			exit(-1);		
		}

		printf("Main: Thread %d returned %d as status code.\n", threadCount, (*(int *)status));
	}

	free(thread);

	printf("Seat plan\n");
	int k;
	for (k = 0; k < length(Array); k++)
	{
		printf("Seat %d / Customer %d\n", k + 1, Array[k]);
	}

	printf("\n");

	pthread_cond_destroy(&cond);
	pthread_mutex_destroy(&mutexPrint);
	pthread_mutex_destroy(&mutexWaitTime);
	pthread_mutex_destroy(&mutexServeTime);
	pthread_mutex_destroy(&mutexTID);
	pthread_mutex_destroy(&mutexTel);
	pthread_mutex_destroy(&mutexSeat);
	pthread_mutex_destroy(&mutexBank);
	pthread_mutex_destroy(&mutexAvailableSeats);
	pthread_mutex_destroy(&mutexArray);
 
	/*clock_gettime(CLOCK_REALTIME, &waitTime);
	clock_gettime(CLOCK_REALTIME, &serveTime);*/

	float floatWaitTimeTotal = (float) waitTimeTotal.tv_sec;
	float floatServeTimeTotal = (float) serveTimeTotal.tv_sec;

	printf("Total profit : %d\n", profit);
	printf("Average waiting time : %f\n", floatWaitTimeTotal/nCust);
	printf("Average serve time : %f\n", floatServeTimeTotal/nCust);*/

	return 1;
}
