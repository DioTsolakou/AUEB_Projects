#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include "p3170164-p3170144-res2.h"
#include <unistd.h>
#include <string.h>

#define length(x)  (sizeof(x) / sizeof(int))
#define max_cust 1000

pthread_mutex_t mutexPrint;
pthread_mutex_t mutexWaitTime;
pthread_mutex_t mutexServeTime;
pthread_mutex_t mutexTID;
pthread_mutex_t mutexTel;
pthread_mutex_t mutexSeat;
pthread_mutex_t mutexBank;
pthread_mutex_t mutexAvailableTotalSeats;
pthread_mutex_t mutexAvailableSeatsA;
pthread_mutex_t mutexAvailableSeatsB;
pthread_mutex_t mutexAvailableSeatsC;
pthread_mutex_t mutexArray;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
pthread_mutex_t mutexCash;

int nCust;
int tID = 0;
int profit = 0;
int varnTel = nTel;
int Array[nSeatPerRow*(nZoneA+nZoneB+nZoneC)];
unsigned int seed;
struct timespec waitTimeStart;
struct timespec waitTimeEnd;
struct timespec serveTimeStart;
struct timespec serveTimeEnd;
float waitTimeTotal;
float serveTimeTotal;
int varnCash = nCash;
struct timespec waitCashStart;
struct timespec waitCashEnd;
float waitCashTotal;

typedef struct info
{
	int threadID;
	int numberOfSeats;
	char zone[2];
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
	int availableTotalSeats = length(Array);
	int availableSeatsA = nSeatPerRow*nZoneA;
	int availableSeatsB = nSeatPerRow*nZoneB;
	int availableSeatsC = nSeatPerRow*nZoneC;

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
	
	int zoneDrawn = printRand(10, 1);
	if (zoneDrawn <= 2)
	{
		strcpy(customerInfo->zone, "A");
	}
	else if (zoneDrawn > 2 && zoneDrawn <= 6)
	{
		strcpy(customerInfo->zone, "B");
	}
	else
	{
		strcpy(customerInfo->zone, "C");
	}

	customerInfo->numberOfSeats = printRand(nSeatHigh, nSeatLow);
	printf("Number of seats : %d\n", customerInfo->numberOfSeats);

	if (availableTotalSeats == 0)
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
	else if ((availableTotalSeats >= customerInfo->numberOfSeats && strcmp(customerInfo->zone, "A") == 0 && availableSeatsA >= customerInfo->numberOfSeats) || (availableTotalSeats >= customerInfo->numberOfSeats && strcmp(customerInfo->zone, "B") == 0 && availableSeatsB >= customerInfo->numberOfSeats) || (availableTotalSeats >= customerInfo->numberOfSeats && strcmp(customerInfo->zone, "C") == 0 && availableSeatsC >= customerInfo->numberOfSeats))
	{
		int wait = printRand(tSeatHigh, tSeatLow);
		clock_gettime(CLOCK_REALTIME, &waitTimeStart);
		sleep(wait);
		clock_gettime(CLOCK_REALTIME, &waitTimeEnd);
		waitTimeTotal += (float) waitTimeEnd.tv_sec - (float) waitTimeStart.tv_sec;

		clock_gettime(CLOCK_REALTIME, &serveTimeStart);
		
		int i;
		int j;
		int counter = 0;

		if (strcmp(customerInfo->zone, "A") == 0)
		{
			for (i = 0; i < customerInfo->numberOfSeats; i++)
			{
				for (j = 0; j < nSeatPerRow*nZoneA; j++)
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
						rc = pthread_mutex_lock(&mutexAvailableTotalSeats);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						availableTotalSeats--;
						rc = pthread_mutex_unlock(&mutexAvailableTotalSeats);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						rc = pthread_mutex_lock(&mutexAvailableSeatsA);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						availableSeatsA--;
						rc = pthread_mutex_unlock(&mutexAvailableSeatsA);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
					}
				}	
			}	
		}
		if (strcmp(customerInfo->zone, "B") == 0)
		{
			for (i = 0; i < customerInfo->numberOfSeats; i++)
			{
				for (j = nSeatPerRow*nZoneA; j < nSeatPerRow*nZoneB; j++)
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
						rc = pthread_mutex_lock(&mutexAvailableTotalSeats);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						availableTotalSeats--;
						rc = pthread_mutex_unlock(&mutexAvailableTotalSeats);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						rc = pthread_mutex_lock(&mutexAvailableSeatsB);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						availableSeatsB--;
						rc = pthread_mutex_unlock(&mutexAvailableSeatsB);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
					}
				}	
			}	
		}
		if (strcmp(customerInfo->zone, "C") == 0)
		{
			for (i = 0; i < customerInfo->numberOfSeats; i++)
			{
				for (j = nSeatPerRow*nZoneB; j < length(Array); j++)
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
						rc = pthread_mutex_lock(&mutexAvailableTotalSeats);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						availableTotalSeats--;
						rc = pthread_mutex_unlock(&mutexAvailableTotalSeats);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						rc = pthread_mutex_lock(&mutexAvailableSeatsC);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						availableSeatsC--;
						rc = pthread_mutex_unlock(&mutexAvailableSeatsC);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
					}
				}	
			}	
		}
		counter = 0;


		rc = pthread_mutex_lock(&mutexCash);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}

		while (varnCash == 0)
		{
			printf("Our cashiers are busy. Please try later.\n");
			rc = pthread_cond_wait(&cond, &mutexCash);
		}

		varnCash--;
		rc = pthread_mutex_unlock(&mutexCash);
		if (rc != 0)
		{
			printf("ERROR! rc isn't 0. %d\n", rc);
			exit(-1);
		}

		int waitCash = printRand(tCashHigh, tCashLow);
		clock_gettime(CLOCK_REALTIME, &waitCashStart);
		sleep(waitCash);
		clock_gettime(CLOCK_REALTIME, &waitCashEnd);
		waitCashTotal += (float) waitCashEnd.tv_sec - (float) waitCashStart.tv_sec;



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
			printf("in Zone %s ", customerInfo->zone);
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

			rc = pthread_mutex_lock(&mutexCash);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			varnCash++;
			rc = pthread_mutex_unlock(&mutexCash);
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
			serveTimeTotal = (float) serveTimeEnd.tv_sec - (float) serveTimeStart.tv_sec;
		}
		else
		{
			rc = pthread_mutex_lock(&mutexPrint);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			printf("Reservation Failed. Transaction with credit card was not accepted.\n");
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
					rc = pthread_mutex_lock(&mutexAvailableTotalSeats);
					if (rc != 0)
					{
						printf("ERROR! rc isn't 0. %d\n", rc);
						exit(-1);
					}
					availableTotalSeats++;
					rc = pthread_mutex_unlock(&mutexAvailableTotalSeats);
					if (rc != 0)
					{
						printf("ERROR! rc isn't 0. %d\n", rc);
						exit(-1);
					}

					if (strcmp(customerInfo->zone, "A") == 0)
					{
						rc = pthread_mutex_lock(&mutexAvailableSeatsA);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						availableSeatsA++;
						rc = pthread_mutex_unlock(&mutexAvailableSeatsA);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
					}

					if (strcmp(customerInfo->zone, "B") == 0)
					{
						rc = pthread_mutex_lock(&mutexAvailableSeatsB);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						availableSeatsB++;
						rc = pthread_mutex_unlock(&mutexAvailableSeatsB);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
					}

					if (strcmp(customerInfo->zone, "C") == 0)
					{
						rc = pthread_mutex_lock(&mutexAvailableSeatsC);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
						availableSeatsC++;
						rc = pthread_mutex_unlock(&mutexAvailableSeatsC);
						if (rc != 0)
						{
							printf("ERROR! rc isn't 0. %d\n", rc);
							exit(-1);
						}
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

			rc = pthread_mutex_lock(&mutexCash);
			if (rc != 0)
			{
				printf("ERROR! rc isn't 0. %d\n", rc);
				exit(-1);
			}
			varnCash++;
			rc = pthread_mutex_unlock(&mutexCash);
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
			serveTimeTotal += (float) serveTimeEnd.tv_sec - (float) serveTimeStart.tv_sec;

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
	rc = pthread_mutex_init(&mutexAvailableTotalSeats, NULL);
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
	rc = pthread_mutex_init(&mutexAvailableSeatsA, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexAvailableSeatsB, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexAvailableSeatsC, NULL);
	if (rc != 0)
	{
		printf("ERROR! rc isn't 0. %d\n", rc);
		exit(-1);
	}
	rc = pthread_mutex_init(&mutexCash, NULL);
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
		if (k < 50)
		{
			printf("Zone A / Seat %d / Customer %d\n", k + 1, Array[k]);
		}
		else if (k >= 50 && k < 150)
		{
			printf("Zone B / Seat %d / Customer %d\n", k + 1, Array[k]);
		}
		else
		{
			printf("Zone C / Seat %d / Customer %d\n", k + 1, Array[k]);
		}
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
	pthread_mutex_destroy(&mutexAvailableTotalSeats);
	pthread_mutex_destroy(&mutexArray);
	pthread_mutex_destroy(&mutexAvailableSeatsΑ);
	pthread_mutex_destroy(&mutexAvailableSeatsB);
	pthread_mutex_destroy(&mutexAvailableSeatsC);
	pthread_mutex_destroy(&mutexCash);

	printf("Total profit : %d\n", profit);
	printf("Average waiting time : %f\n", waitTimeTotal/nCust);
	printf("Average serve time : %f\n", serveTimeTotal/nCust);
	printf("Average cashier serve time : %f\n", waitCashTotal/nCust);

	return 1;
}
