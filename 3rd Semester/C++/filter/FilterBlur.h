#ifndef FILTERB
#define FILTERB

#include "Filter.h"
#include "array2d.h"

using namespace std;

class FilterBlur : public Filter, public Array2D<float>
{
	public :
		int N;
		vector<float> area;

		FilterBlur(int n)
		{
			N = n;
			area.resize(N * N);
			for (int i = 0; i < N*N; i++)
			{
				area.push_back(1.0 / (N * N));
			}
		}

		Image operator << (const Image& image)
		{
			Image newImage(image);
			int m = 0;
			int n = 0;
			for (int i = 0; i < newImage.getHeight(); i++)
			{
				for (int j = 0; j < newImage.getWidth(); j++)
				{
					Vec3<float> pixel = Vec3<float>(0, 0, 0);

					for (int m = -N/2; m <= N/2; m++)
					{
						for (int n = -N/2; n <= N/2; n++)
						{
							if (((i + m >= 0) && (i + m < newImage.getWidth())) && ((j + n >= 0) && (j + n < newImage.getHeight())))
							{
								pixel += newImage(i + m, j + n) * area.at(n + (N/2) + N*(m + (N/2)));
							}
						}
					}

					newImage(i + m, j + n) = pixel;
				}
			}

			return newImage;
		}
};
#endif // !FILTERB
