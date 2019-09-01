#include <iostream>
#include <string>
#include "Color.h"
#include "Image.h"
#include "ppm.h"

using namespace imaging;
using namespace std;

int main(int argc, char *argv[])
{

	std::string fileName;
	std::string format;

	Image picture;

	for (int i = 0; i < argc; ++i) {
		if (argv[i] != NULL)
		{
			printf("CMD Line Argument %d is :: %s\n", i, argv[i]);
			fileName = argv[1];
			format = fileName.substr(fileName.length() - 3);

			picture.load(fileName, format);

			Color *p = picture.getRawDataPtr();
			Color maxColor(1, 1, 1);
			for (int i = 0; i < picture.getWidth()*picture.getHeight(); i++)
			{
				p[i] = maxColor - p[i];
			}

			picture.save(fileName, format);
		}
		else
		{
			std::cout << "No file was specified in the command line arguments." << std::endl << "Please give the path to the file." << std::endl;
			std::cin >> fileName;
			format = fileName.substr(fileName.length() - 3);

			picture.load(fileName, format);

			Color *p = picture.getRawDataPtr();
			Color maxColor(1, 1, 1);
			for (int i = 0; i < picture.getWidth()*picture.getHeight(); i++)
			{
				p[i] = maxColor - p[i];
			}

			picture.save(fileName, format);
		}
	}
	system("pause");
}