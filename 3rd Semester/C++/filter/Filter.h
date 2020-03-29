#ifndef FILTER
#define FILTER

#include "Image.h"

class Filter
{
	public:
		Filter() {}

		Filter(const Filter & src) {}

		virtual Image operator << (const Image & Image) = 0;
};

#endif // !FILTER