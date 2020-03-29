#include <iostream>
#include <fstream>
#include <string>
#include "ppm.h"

using namespace std;

float * image::ReadPPM(const char * filename, int * w, int * h)
{
	string line;
	int intensity;
	ifstream iFile;
	float* buffer;
	int size;


	iFile.open(filename, ios::in | ios::binary);
	iFile >> line;

	if (!iFile.is_open())
	{
		cerr << "Could not open file : " << filename << endl;
		return nullptr;
	}

	if (line == "P6")
	{
		iFile >> *(w) >> *(h) >> intensity;

		if ((w) == NULL || (h) == NULL)
		{
			printf("Horizontal/Vertical dimension of the image is missing.\n");
			return nullptr;
		}

		if (intensity > 255 || intensity == NULL)
		{
			printf("Failure to read the color intensity./Color intensity is greater than 255.\n");
			return nullptr;
		}


		size = 3 * (*(w)) * (*(h));
		buffer = new float[size];
		
		int i = 0;
		while (!iFile.eof())
		{
			buffer[i] = (float) (((unsigned char) iFile.get()) / (float) intensity);
			i++;
		}

		cout << "Image dimensions are : " << (*(w)) << "X" << (*(h)) << endl;

		iFile.close();

		return buffer;
	}
	else
	{
		cout << "File is not of P6 format" << endl;
		return nullptr;
	}
}

bool image::WritePPM(const float * data, int w, int h, const char * filename)
{
	ofstream oFile;
	oFile.open(filename, ios::out | ios::binary);


	if (data == nullptr)
	{
		return false;
	}

	oFile << "P6\n" << w << "\n" << h << "\n" << "255\n";

	int size = 3*w*h;
	char * buffer = new char[size];
	for (int i = 0; i < size; i++)
	{
		buffer[i] = (unsigned char)((data[i]) * (float) 255.0);
	}

	oFile.write(buffer, size);
	oFile.close();
	delete[] buffer;
	return true;
}