#include "ppm.h"
#include "Image.h"

using namespace std;

Image::Image(){}

Image::Image(unsigned int width, unsigned int height)
{
	this->width = width;
	this->height = height;
	setData(buffer.data());
}

Image::Image(const Image& src)
{
	width = src.getWidth();
	height = src.getHeight();
	Vec3<float>* data = const_cast<Image&>(src).getRawDataPtr();
	setData(data);
}

Image::~Image()
{
	delete[] &buffer;
}


bool Image::load(const std::string& filename, const std::string& format)
{
	std::string file = filename;
	int w;
	int h;

	if (format != "ppm")
	{
		printf("Wrong file format\n");
		return false;
	}
				
	float* arrayOfFloats = image::ReadPPM(file.c_str(), &w, &h);

	if (arrayOfFloats != nullptr)
	{
		width = (unsigned int)w;
		height = (unsigned int)h;
		buffer.clear();

		for (unsigned int i = 0; i < 3 * getWidth() * getHeight(); i = i + 3)
		{
			buffer.push_back(Vec3<float>(arrayOfFloats[i], arrayOfFloats[i + 1], arrayOfFloats[i + 2]));
		}
		
		//delete[] arrayOfFloats;
		printf("Loading was successful.\n");
		return true;
	}
	else
	{
		printf("Loading was not succseful.\n");
		return false;
	}
}

bool Image::save(const std::string& filename, const std::string& format)
{
	string file = filename;

	if (format != "ppm")
	{
		printf("Wrong file format\n");
		return false;
	}
	else
	{
		image::WritePPM((float *) buffer.data(), getWidth(), getHeight(), file.c_str());
		printf("Saving was successful.\n");
		return true;
	}
}