import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AdvancedSolarSystem extends PApplet {

PShape ring, ring2, ring3, spaceship; //<>//
PImage background;

Star sun;
Planet[] planet;
Satelite moon;

int z = 1000;

/*final float[] ANGLES_AXIS = {0.25, 0.25, 0.25, 0.36, 0.25, 0.25};
final float[] ANGLES_AROUND = {3, 2.5, 2, 1.5, 1, 0.5};
final float[] RADIUS = {width / 45.5, width / 39, width / 27.32, width / 34.15, width / width / 15.17, width / 19};
final int[] ROTATED_ANGLE = {0, 0, 30, 0, 0, 45};
final float[] DISTANCE = {width / 4, width / 2.8, width / 2.1, width / 1.6, width / 1.25, width / 0.9};
final String[] TEXTURE = {"mercury.jpg", "venus.jpg", "earth.jpg", "mars.jpg", "jupiter.jpg", "saturn.jpg"};*/

public void setup()
{
        //size(1200, 700, P3D);
        
        background = loadImage("cosmos.jpg");
        background.resize(width, height);
  
        noStroke();
        
  
        ring = createShape();
        ring.beginShape(TRIANGLE_STRIP);
        ring.fill(149, 124, 108);
        ring.noStroke();
  
        ring2 = createShape();
        ring2.beginShape(TRIANGLE_STRIP);
        ring2.fill(122, 96, 77);
        ring2.noStroke();
        
        ring3 = createShape();
        ring3.beginShape(TRIANGLE_STRIP);
        ring3.fill(150);
        ring3.noStroke();
  
        float radian;
        for (int i = 0; i < 37; ++i)
        {
                radian = radians(i * 10);
                ring.vertex(width / 13.66f * cos(radian), 0, width / 13.66f * sin(radian));
                ring.vertex(width / 9.1f * cos(radian), 0, width / 9.1f * sin(radian));
                
                ring2.vertex(width / 8.53f * cos(radian), 0, width / 8.53f * sin(radian));
                ring2.vertex(width / 6.5f * cos(radian), 0, width / 6.5f * sin(radian));
                
                ring3.vertex(width / 11.53f * cos(radian), 0, width / 11.53f * sin(radian));
                ring3.vertex(width / 10.5f * cos(radian), 0, width / 10.5f * sin(radian));
        }
        ring.endShape();
        ring2.endShape();
        ring3.endShape();
  
        spaceship = createShape();
        spaceship.beginShape(TRIANGLE_STRIP);
        spaceship.fill(255, 120, 0);
        spaceship.noStroke();
  
        for (int i = 0; i < 37; ++i)
        {
                radian = radians(i * 10);
                spaceship.vertex(0, -30, 0);
                spaceship.vertex(13 * cos(radian), 10, 13 * sin(radian));
        }
        spaceship.fill(255);
        for (int i = 0; i < 37; ++i)
        {
                radian = radians(i * 10);
                spaceship.vertex(13 * cos(radian), 10, 13 * sin(radian));
                spaceship.vertex(30 * cos(radian), 0, 30 * sin(radian));
        }
        for (int i = 0; i < 37; ++i)
        {
                radian = radians(i * 10);
                spaceship.vertex(30 * cos(radian), 0, 30 * sin(radian));
                spaceship.vertex(15 * cos(radian), 15, 15 * sin(radian));
        }
        for (int i = 0; i < 37; ++i)
        {
                radian = radians(i * 10);
                spaceship.vertex(15 * cos(radian), 15, 15 * sin(radian));
                spaceship.vertex(20 * cos(radian), 70, 20 * sin(radian));
      
        }
        spaceship.fill(100, 100, 100);
        for (int i = 0; i < 37; ++i)
        {
                radian = radians(i * 10);
                spaceship.vertex(20 * cos(radian), 70, 20 * sin(radian));
                spaceship.vertex(0, 100, 0);
        }
        spaceship.endShape();
  
        textAlign(CENTER, CENTER);
  
        sun = new Star(0, 0, 0, -0.25f, width / 6.83f, "sun.jpg");
        planet = new Planet[8];
  
  /*for (int i = 0; i < 6; ++i)
  {
          planet[i] = new Planet(ANGLES_AXIS[i], ANGLES_AROUND[i], RADIUS[i], ROTATED_ANGLE[i], DISTANCE[i], TEXTURE[i], sun);
  }*/
        planet[0] = new Planet(0.25f, 1.5f, width / 45.5f, 0, width / 4, "mercury.jpg", sun);
        planet[1] = new Planet(0.25f, 1.25f, width / 39, 0, width / 2.8f, "venus.jpg", sun);
        planet[2] = new Planet(-4.25f, 1, width / 27.32f, 30, width / 2.1f, "earth.jpg", sun);
        planet[3] = new Planet(0.36f, 0.75f, width / 34.15f, 0, width / 1.6f, "mars.jpg", sun);
        planet[4] = new Planet(0.25f, 0.5f, width / 15.17f, 0, width / 1.25f, "jupiter.jpg", sun);
        planet[5] = new Planet(0.25f, 0.25f, width / 19, 30, width, "saturn.jpg", sun);
        planet[6] = new Planet(0, 0.125f, width / 22, 80, width / 0.8f, "uranus.jpg", sun);
        planet[7] = new Planet(0.25f, 0.0625f, width / 21, 0, width / 0.7f, "neptune.jpg", sun);
  
        moon = new Satelite(0, 4, width / 68.3f, 0, width / 15.17f, "moon.jpg", planet[2]);
}
public void draw()
{
        background(background);
        
        
        
        translate(width / 2, height / 2, -800);
        //rotateX(radians(z));
        
        pushMatrix();
  
        //lightSpecular(204, 204, 204);
        // Rotate the sun
        
        sun.Rotate();
        lightFalloff(0.2f, 0/*0.002*/, 0.000001f);
        pointLight(255, 255, 255, 0, 0, 0);
        //ambientLight(0, 0, 0);
        
        
        // Rotate the planets
        for (int i = 0; i < 8; ++i)
        {
                
                planet[i].Rotate();
        }
        
        // Add rings to the Saturn
        setMatrix(planet[5].getMatrix2());
        rotateY(radians(360 - planet[5].getAngleAround()));  
        rotateZ(radians(30));
        shape(ring);
        shape(ring2);
        
        // Add rings to the Uran
        setMatrix(planet[6].getMatrix2());
        rotateY(radians(360 - planet[6].getAngleAround()));  
        rotateZ(radians(80));
        shape(ring3);

        // Rotate the moon
        moon.Rotate();
        popMatrix();
        
        camera(mouseX + (mouseX - width/2), mouseY + (mouseY - height / 2), z + 50,
        mouseX + (mouseX - width/2), mouseY + (mouseY - height / 2), z, 0, 1, 0);
}
public void mouseWheel(MouseEvent event)
{
        z += 50 * event.getCount();
}
class Planet
{
        float angle;
        float angPerFrame;
        float angleAround;
        float angAroundPerFrame;
        float radius;
        float distance;
        int rotatedAng;
        PImage texture;
        PShape shape;
        Star star;
        PMatrix3D matrix;
        
        
        Planet(float ang, float angAr, float rad, int rotatedAng, float distance, String textureStr, Star star)
        {
                angle = angleAround = 0;
                angPerFrame = ang;
                angAroundPerFrame = angAr;
                radius = rad;
                this.rotatedAng = rotatedAng;
                this.distance = distance;
                texture = loadImage(textureStr);
                this.star = star;
                
                shape = createShape(SPHERE, rad);

                beginShape();
                shape.setTexture(texture);
                endShape();
        }
        
        public void Rotate()
        {
                setMatrix(star.getMatrix2());
                
                //float strength = 200 / ((distance / (width/4)) * (distance / (width/4)));

                rotateY(radians(angleAround));
                translate(distance, 0, 0);
                //pointLight(strength, strength, strength, 0, 0, 0);
                
                
                matrix = getMatrix(matrix);
                
                if (rotatedAng != 0)
                {
                        rotateY(radians(360 - angleAround));  
                        rotateZ(radians(rotatedAng));
                }
                rotateY(radians(angle));
                
                //pointLight(100, 100, 100, -1, 0, 0);
                //ambient(255, 0, 0);
                shape(shape);
                
                
                angle = angle + angPerFrame;
                if (angle > 360)
                        angle = 0;
                else if (angle < 0)
                        angle = 360;
                        
                angleAround = angleAround + angAroundPerFrame;
                if (angleAround > 360)
                        angleAround = 0;
                else if (angleAround < 0)
                        angleAround = 360;
        }
        public PMatrix3D getMatrix2()
        {
                return matrix;
        }
        public float getAngleAround()
        {
                return angleAround;
        }
}
class Satelite
{
        float angle;
        float angPerFrame;
        float angleAround;
        float angAroundPerFrame;
        float radius;
        float distance;
        int rotatedAng;
        PImage texture;
        PShape shape;
        Planet planet;
        
        
        Satelite(float ang, float angAr, float rad, int rotatedAng, float distance, String textureStr, Planet planet)
        {
                angle = angleAround = 0;
                angPerFrame = ang;
                angAroundPerFrame = angAr;
                radius = rad;
                this.rotatedAng = rotatedAng;
                this.distance = distance;
                texture = loadImage(textureStr);
                this.planet = planet;
                
                shape = createShape(SPHERE, rad);
                beginShape();
                shape.setTexture(texture);
                endShape();
        }
        
        public void Rotate()
        {
                setMatrix(planet.getMatrix2());

                rotateY(radians(angleAround));
                translate(distance, 0, 0);
                
                if (rotatedAng != 0)
                {
                        rotateY(radians(360 - angleAround));  
                        rotateZ(radians(rotatedAng));
                }
                rotateY(radians(angle));
                shape(shape);
                
                angle = angle + angPerFrame;
                if (angle > 360)
                        angle = 0;
                else if (angle < 0)
                        angle = 360;
                        
                angleAround = angleAround + angAroundPerFrame;
                if (angleAround > 360)
                        angleAround = 0;
                else if (angleAround < 0)
                        angleAround = 360;
        }
}
class Star
{
        int x, y, z;
        float angle;
        float angPerFrame;
        float radius;
        PImage texture;
        PShape shape;
        PMatrix3D matrix = null;
        
        Star(int x, int y, int z, float ang, float rad, String textureStr)
        {
                this.x = x;
                this.y = y;
                this.z = z;
                
                angle = 0;
                angPerFrame = ang;
                radius = rad;
                texture = loadImage(textureStr);
                
                shape = createShape(SPHERE, rad);
                beginShape();
                shape.setTexture(texture); 
                endShape();
        }
        
        public void Rotate()
        {
                translate(x, y, z);
                matrix = getMatrix(matrix);
                
                rotateY(radians(angle));
                shape(shape);
                
                angle = angle + angPerFrame;
                if (angle > 360)
                        angle = 0;
                else if (angle < 0)
                        angle = 360;
        }
        public PMatrix3D getMatrix2()
        {
                return matrix;
        }
}
        public void settings() {  fullScreen(P3D); }
        static public void main(String[] passedArgs) {
                String[] appletArgs = new String[] { "AdvancedSolarSystem" };
                if (passedArgs != null) {
                  PApplet.main(concat(appletArgs, passedArgs));
                } else {
                  PApplet.main(appletArgs);
                }
        }
}
