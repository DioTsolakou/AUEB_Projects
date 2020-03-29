#include <iostream>
#include <string>
#include "Image.h"
#include "Filter.h"
#include "FilterGamma.h"
#include "FilterLinear.h"
#include "FilterBlur.h"
#include "FilterLaplace.h"

using namespace image;
using namespace std;

int main(int argc, char *argv[])
{

	string fileName;
	string format;
	string editedFilename;
	float gamma;
	int blurArea;
	math::Vec3<float> a, c;
	float ax, ay, az, cx, cy, cz;

	Image picture;

	//for (int j = 0; j < argc; j++)
	//{
	//	cout << argv[j] << "\n" << endl;
	//}
	
	if (argc > 3 && (string) argv[1] == "filter")
	{
		fileName = argv[argc - 1];
		format = fileName.substr(fileName.length() - 3);

		if (picture.load(fileName, format) == false)
		{
			return -1;
		}

		for (int i = 2; i < argc; i++)
		{
			if ((string)argv[i] == "-f")
			{
				if ((string) argv[i+1] == "gamma")
				{
					gamma = stof(argv[i + 2]);
					FilterGamma fg = FilterGamma(gamma);
					picture = fg << picture;
				}
				else if ((string) argv[i + 1] == "linear")
				{
					ax = stof(argv[i + 2]);
					ay = stof(argv[i + 3]);
					az = stof(argv[i + 4]);
					cx = stof(argv[i + 5]);
					cy = stof(argv[i + 6]);
					cz = stof(argv[i + 7]);

					a = Vec3<float>(ax, ay, az);
					c = Vec3<float>(cx, cy, cz);

					FilterLinear fl = FilterLinear(a, c);

					picture = fl << picture;
				}
				else if ((string) argv[i + 1] == "blur")
				{
					blurArea = stoi(argv[i + 2]);
					FilterBlur fb = FilterBlur(blurArea);
					picture = fb << picture;
				}
				else if ((string)argv[i + 1] == "laplace")
				{
					FilterLaplace fLa = FilterLaplace();
					picture = fLa << picture;
				}
				else
				{
					cout << "No filter was given." << endl;
					return -1;
				}
			}
		}

		int pos = (int) fileName.find_last_of('\\');
		editedFilename = fileName.substr(0, pos + 1) + "filtered_" + fileName.substr(pos + 1);
		picture.save(editedFilename, format);

		return 1;
	}
	else
	{
		cout << "The parameters given were incorrect." << endl;
		system("pause");
		return -1;
	}

	return 0;
}