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

public class FractalTree extends PApplet {

private double fractionLength = .8f; 
private int smallestBranch = 12; 
private int threshold_len = 42;
private double branchAngle = .6f;  
private double branchAngle1 =.2f;  
private double branchAngle2 = Math.PI/2;  
private int TRUNK_LEN = 148;
private int TRUNK_ENDX;
private int TRUNK_ENDY;
public void setup() {   
	//size(640,480);    
	size(1024,600);    
  TRUNK_ENDX = width>>1;
  TRUNK_ENDY = height-(height>>3);
	noLoop(); 
} 
public void draw() {   
	background(0);   
	stroke(0,255,0);   
	line(TRUNK_ENDX,TRUNK_ENDY+TRUNK_LEN,TRUNK_ENDX,TRUNK_ENDY);   
	drawBranches(TRUNK_ENDX,TRUNK_ENDY,TRUNK_LEN,(5.5f*Math.PI/4));  
} 
public void drawBranches(int x,int y, double branchLength, double angle) {   
  //double angle_inc = (3*Math.PI/9);
  double angle_inc = (2*Math.PI/10);
  double angle_inc2 = 0.3f*angle_inc;
  double angle1 = angle + branchAngle;
  double angle2 = angle - branchAngle; 
  branchLength *= fractionLength; 
  int endX1 = (int)(branchLength*Math.cos(angle1) + x);
  int endY1 = (int)(branchLength*Math.sin(angle1) + y);
  int endX2 = (int)(branchLength*Math.cos(angle2) + x);
  int endY2 = (int)(branchLength*Math.sin(angle2) + y);
  line(x,y,endX1,endY1);
  line(x,y,endX2,endY2);
  if(branchLength>smallestBranch){
    drawBranches(endX1,endY1,branchLength,angle+angle_inc); 
    //drawBranches(endX2,endY2,branchLength,angle-(Math.PI/7));
    //drawBranches(endX2,endY2,branchLength,angle+(Math.PI/7)); 
    drawBranches(endX2,endY2,branchLength,
      angle+( (branchLength>threshold_len) ?  (0-angle_inc2):(angle_inc2) ) 
      ); 
  }
} 
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FractalTree" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
