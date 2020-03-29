#ifndef FILTERL
#define FILTERL

#include "Filter.h"

using namespace std;
class FilterLinear : public Filter
{
	private:
		math::Vec3<float> a, c;

	public:
		FilterLinear(const math::Vec3<float> givenA, const math::Vec3<float> givenC)
		{
			math::Vec3<float> a = math::Vec3<float>(givenA);
			math::Vec3<float> c = math::Vec3<float>(givenC);
		}

		FilterLinear(const FilterLinear& src)
		{
			a = src.a;
			c = src.c;
		}

		Image operator << (const Image& image)
		{
			cout << "start of linear" << endl;

			Image newImage(image);

			for (int i = 0; i < newImage.getHeight(); i++)
			{
				for (int j = 0; j < newImage.getWidth(); j++)
				{
					Vec3<float> pixel = newImage(i, j);
					pixel = a * pixel + c;
					pixel = pixel.clampToLowerBound(0);
					pixel = pixel.clampToUpperBound(1);
				}
			}
			return newImage;
		}
};

#endif // !FILTERL