#ifndef FILTERG
#define FILTERG

#include "Filter.h"
#include <math.h>

using namespace std;
class FilterGamma : public Filter
{
	private:
		float gamma;

	public:
		FilterGamma(float givenGamma)
		{
			gamma = givenGamma;
		}

		FilterGamma(const FilterGamma& src)
		{
			gamma = src.gamma;
		}

		

		Image operator << (const Image& image)
		{
			Image newImage(image);

			for (int i = 0; i < newImage.getHeight(); i++)
			{
				for (int j = 0; i < newImage.getWidth(); i++)
				{
					Vec3<float> pixel = newImage(i, j);
					pixel.x = powf(pixel.x, gamma);
					pixel.y = powf(pixel.y, gamma);
					pixel.z = powf(pixel.z, gamma);
				}
			}
			return newImage;
		}
};

#endif // !FILTERG