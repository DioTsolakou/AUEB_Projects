PImage earthTexture;
PImage moonTexture;
PImage sunTexture;
PShape earth;
PShape moon;
PShape sun;
float rotationIncrement;
float sphereSize;
boolean defaultMode = true;
PFont font;

void setup()
{
  size(1920, 1080, P3D);
  
  font = createFont("Calibri", 30, true);
  
  earthTexture = loadImage("data\\earth_map.jpg");
  moonTexture = loadImage("data\\moon_map.jpg");
  sunTexture = loadImage("data\\sun_map.jpg");
  noStroke();
  smooth(8);

  sphereSize = (float) height/6;
  earth = createShape(SPHERE, sphereSize);
  earth.setTexture(earthTexture);
  
  moon = createShape(SPHERE, sphereSize*0.27);
  moon.setTexture(moonTexture);
  
  sun = createShape(SPHERE, sphereSize*5);
  sun.setTexture(sunTexture); 
}

void draw()
{ 
  background(#1F1D1D);
  textFont(font);
  fill(255);
  text("Click the 'Left Mouse Button' to toggle between earth centered and sun centered", 20, 50);
  
  if (defaultMode == true)
  {
    defaultMode();
  }
  else sunlight();
}

void sunlight()
{
  //sun
  pointLight(255, 249, 224, width/2, height/2, -5000);
  lightFalloff(0, 0, 0.0000009);
  ambientLight(255, 249, 224, width/2, height/2, -5000);
  translate(width/2, height/2, -5000);
  rotateY(float(frameCount)/6000);
  shape(sun);
  
  //earth
  pushMatrix();
  //rotateX(-PI/2); for top view
  rotateY(rotationIncrement/10);
  translate(3000, 0);
  rotateX(0.40910518); //axial tilt
  rotateY(float(frameCount)/300); //revolution
  shape(earth);
  
  //moon
  rotateX(0.0261799); //axial tilt
  rotateY(rotationIncrement);
  translate(300, 0);
  //rotateX(-PI/2); for top view
  shape(moon);
  popMatrix();
  
  rotationIncrement += 0.01;
}

void defaultMode()
{
  pointLight(255, 249, 224, 0, height/4, 0);
  lightFalloff(0, 0, 0.01);
  ambientLight(255, 249, 224);
  
  //earth
  pushMatrix();
  translate(width/2, height/2, 0);
  rotateY(float(frameCount)/300);
  //rotateX(-PI/2); for top view  
  rotateX(0.40910518); //axial tilt
  shape(earth);
  popMatrix();
  
  //moon
  pushMatrix();
  translate(width/2, height/2, 0);
  rotateX(0.0261799); //axial tilt
  rotateY(rotationIncrement);
  translate(600, 0);
  //rotateX(-PI/2); for top view
  shape(moon);
  popMatrix();
  
  rotationIncrement += 0.01;
}

void mouseClicked()
{
  if (mouseButton == LEFT)
  {
    defaultMode = !defaultMode;
  }
}
