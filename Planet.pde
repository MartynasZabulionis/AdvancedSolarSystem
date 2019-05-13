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
        
        void Rotate()
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
