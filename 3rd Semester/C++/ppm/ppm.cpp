#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include "ppm.h"

#ifndef _COLOR
#define _COLOR

using namespace std;
using namespace imaging;

std::string& ltrim(std::string& str, const std::string& chars = "\t\n\v\f\r ")
{
	str.erase(0, str.find_first_not_of(chars));
	return str;
}

std::string& rtrim(std::string& str, const std::string& chars = "\t\n\v\f\r ")
{
	str.erase(str.find_last_not_of(chars) + 1);
	return str;
}

std::string& trim(std::string& str, const std::string& chars = "\t\n\v\f\r ")
{
	return ltrim(rtrim(str, chars), chars);
}

float * imaging::ReadPPM(const char * filename, int * w, int * h)
{
	fstream iFile;
	FILE * fileptr = nullptr;
	iFile.open(filename, ios::in | ios::binary);

	if (!iFile.is_open())
	{
		cerr << "Could not open file : " << filename << endl;
		return nullptr;
	}
	else
	{
		string type;
		int width = NULL;
		int height = NULL;
		int intensity = NULL;
		int linecounter = 0;
		int end = 0;
		int start = 0;
		string s;
		string tmp;

		char spaceCheck;

		while (linecounter < 4)
		{
			spaceCheck = (char)iFile.get();
			s += spaceCheck;
			if (spaceCheck == ' ' || spaceCheck == '\n')
			{
				linecounter++;
				
				if (linecounter == 1)
				{
					tmp = s.substr(start, end);
					trim(tmp);
					if (tmp == "P6")
					{
						//cout << tmp << endl;
						//cout << start << " " << end << endl;
						type = tmp;
					}
					start = end + 1;
				}
				else if (linecounter == 2)
				{
					tmp = s.substr(start, end);
					trim(tmp);
					//cout << tmp << endl;
					//cout << start << " " << end << endl;
					width = stoi(tmp);
					start = end + 1;
				}
				else if (linecounter == 3)
				{
					tmp = s.substr(start, end);
					trim(tmp);
					//cout << tmp << endl;
					//cout << start << " " << end << endl;
					height = stoi(tmp);
					start = end + 1;
				}
				else
				{
					tmp = s.substr(start, end);
					trim(tmp);
					//cout << tmp << endl;
					//cout << start << " " << end << endl;
					intensity = stoi(tmp);
					start = end + 1;
				}
			}

			end++;

			if (spaceCheck == iFile.eof())
			{
				cerr << "Reached end of file." << endl;
				return false;
			}
		}

		cout << s << endl;

		if (width == NULL || height == NULL)
		{
			printf("Horizontal/Vertical dimension of the image is missing.\n");
			exit(2);
		}

		if (intensity > 255 || intensity == NULL)
		{
			printf("Failure to read the color intensity./Color intensity is greater than 255.\n");
			exit(3);
		}

		*w = width;
		*h = height;

		int size = 3* *w* *h;
		unsigned char * buffer = new unsigned char[size];

		iFile.read((char *)buffer, size);

		float * floatBuffer = new float[sizeof(float)*size];

		for (int i = 0; i <= size; i++)
		{
			floatBuffer[i] = (int)buffer[i] / (float)intensity;
		}
		cout << "Image dimensions are: " << *w << " X " << *h << endl;

		iFile.flush();
		iFile.clear();
		iFile.close();

		return floatBuffer;
	}
}

bool imaging::WritePPM(const float * data, int w, int h, const char * filename)
{
	fstream oFile;
	oFile.open(filename, ios::out | ios::binary);


	if (data == nullptr)
	{
		return false;
	}

	oFile << "P6\n" << w << "\n" << h << "\n" << "255\n";

	int size = w*h;
	unsigned char * buffer = new unsigned char[size];
	for (int i = 0; i < size; i++)
	{
		oFile.write((char *)buffer, size);
	}


	oFile.flush();
	oFile.clear();
	oFile.close();

	return true;
}

#endif