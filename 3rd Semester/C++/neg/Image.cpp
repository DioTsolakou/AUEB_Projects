#include "ppm.h"
#include "Color.h"
#include "Image.h"

using namespace imaging;
using namespace std;

Color * Image::getRawDataPtr()
{
	imaging::Color * ptr = buffer;
	return ptr;
}

Color Image::getPixel(unsigned int x, unsigned int y) const
{
	if (x >= width || y >= height)
	{
		imaging::Color* newColor = nullptr;
		return imaging::Color();
	}
	else
	{
		return buffer[x*width + y];
	}
}

void Image::setPixel(unsigned int x, unsigned int y, Color & value)
{
	if (x <= width && y <= height)
	{
		buffer[y + x*width] = value;
	}
}

void Image::setData(const Color *& data_ptr)
{
	if (data_ptr == nullptr || width == 0 || height == 0)
	{
		return;
	}
	else
	{
		memcpy(buffer, data_ptr, width*height * sizeof(Color));
	}
}

Image::Image()
{
	Image::width = 0;
	Image::height = 0;
	Image::buffer = nullptr;
}

Image::Image(unsigned int width, unsigned int height)
{
	this->width = width;
	this->height = height;
}

Image::Image(unsigned int width, unsigned int height, const Color * data_ptr)
{
	this->width = width;
	this->height = height;
	data_ptr = buffer;
}

Image::Image(const Image & src)
{
	width = src.width;
	height = src.height;
	buffer = new Color[width*height];
	memcpy(buffer, src.buffer, width*height * sizeof(Color));
}

Image & Image::operator=(const Image & right)
{
	if (&right == this)
	{
		return *this;
	}
	if (buffer != nullptr)
	{
		delete[] buffer;
	}
	float size;
	size = width * height;
	size = right.width*height;
	buffer = new Color[width*height];
	memcpy(buffer, right.buffer, size * sizeof(Color));
	return *this;
}

Image::~Image()
{
	delete[] buffer;
}

bool Image::load(const std::string & filename, const std::string & format)
{
	std::string file = filename;
	std::string fileFormat = file.substr(file.length() - 3);
	//std::string fileFormat;

	int width;
	int height;
	bool check = false;

	if (buffer != NULL)
	{
		delete[] buffer;
	}

	/*for (char & ch : temp)
	{
		ch = toupper(ch);
		fileFormat += ch;
	}*/

	if (fileFormat != "ppm")
	{
		printf("Wrong file format\n");
		return check;
	}
	else
	{
		imaging::ReadPPM(file.c_str(), &width, &height);
		this->width = (unsigned int)width;
		this->height = (unsigned int)height;
		check = true;
		printf("Loading was successful.\n");
		return check;
	}
}

bool Image::save(const std::string & filename, const std::string & format)
{
	std::string file = filename;
	std::string fileFormat = file.substr(file.length() - 3);
	//std::string fileFormat;

	bool check = false;

	if (buffer == NULL)
	{
		return false;
	}

	/*for (char & ch : temp)
	{
		ch = toupper(ch);
		fileFormat += ch;
	}*/

	if (fileFormat != "ppm")
	{
		printf("Wrong file format\n");
		return check;
	}
	else
	{
		imaging::WritePPM((float *)buffer, width, height, file.c_str());
		check = true;
		printf("Saving was successful.\n");
		return check;
	}
}