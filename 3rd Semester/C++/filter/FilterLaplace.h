#ifndef FILTERLA
#define FILTERLA

#include "Filter.h"
#include "FilterBlur.h"

class FilterLaplace
{
	public:
		vector<int> matrix;

		FilterLaplace()
		{
			matrix.resize(9);
			matrix[0] = 0; matrix[1] = 1; matrix[2] = 0;
			matrix[3] = 1; matrix[4] = -4; matrix[5] = 1;
			matrix[6] = 0; matrix[7] = 1; matrix[8] = 0;
		}

		Image operator << (Image& image)
		{
			Image newImage(image);

			for (int i = 0; i < newImage.getHeight(); i++)
			{
				for (int j = 0; j < newImage.getWidth(); j++)
				{
					Vec3<float> pixel = Vec3<float>(0, 0, 0);

					for (int m = -1; m <= 1; m++)
					{
						for (int n = -1; n <= 1; n++)
						{
							if (((i + m >= 0) && (i + m < newImage.getWidth())) && ((j + n >= 0) && (j + n < newImage.getHeight())))
							{
								pixel += newImage(i + m, j + n) * matrix.at(n + 1 + 3 * (m + 1));
							}
						}
					}

					pixel.clampToLowerBound(0);
					pixel.clampToUpperBound(1);
					newImage(i, j) = pixel;
				}
			}

			return newImage;
		}
	
};
#endif // !FILTERLA