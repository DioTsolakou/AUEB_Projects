#ifndef ARRAYHPP
#define ARRAYHPP

#include "array2d.h"

using namespace math;

template<typename T>
const unsigned int Array2D<T>::getWidth() const
{
	return width;
}

template<typename T>
const unsigned int Array2D<T>::getHeight() const
{
	return height;
}

template<typename T>
T* Array2D<T>::getRawDataPtr()
{
	return buffer.data();
}

template<typename T>
void Array2D<T>::setData(const T* const& data_ptr)
{
	buffer.clear();
	for (unsigned int i = 0; i < width*height; i++)
	{
		buffer.push_back(*(data_ptr + i));
	}
}

template<typename T>
T& Array2D<T>::operator () (unsigned int x, unsigned int y)
{
	if ((x >= 0 && x <= width) && (y >= 0 && y <= height))
	{
		return buffer.at(x + y*width);
	}

	return buffer.at(0);
}

template<typename T>
Array2D<T>::Array2D(unsigned int width, unsigned int height, const T* data_ptr)
{
	this->width = width;
	this->height = height;
	setData(data_ptr);
}

template<typename T>
Array2D<T>::Array2D(const Array2D& src)
{
	Array2D(src.getWidth(), src.getHeight());
	const T* temp = src.buffer.data();
	setData(temp);
}

template<typename T>
Array2D<T>::~Array2D()
{
	buffer.clear();
}

template<typename T>
Array2D<T> & Array2D<T>::operator = (const Array2D<T> & right)
{
	if (this == &right)
	{
		return *this;
	}

	int width = right.getWidth();
	int height = right.getHeight();
	Array2D(width, height);
	setData(right.buffer.data());

	return *this;
}
#endif // !ARRAYHPP