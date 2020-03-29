#ifndef IMAGE
#define IMAGE

#include "array2d.h"
#include "imageio.h"
#include "vec3.h"
#include "ppm.h"

class Image : public image::ImageIO, public math::Array2D<math::Vec3<float>>
{
	public:
	
		Image();
	
		Image(unsigned int width, unsigned int height);

		Image(const Image& src);
	
		~Image();

		virtual bool image::ImageIO::load(const std::string& filename, const std::string& format);
		virtual bool image::ImageIO::save(const std::string& filename, const std::string& format);
};

#endif // !IMAGE