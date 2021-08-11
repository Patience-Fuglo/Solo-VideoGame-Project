

import java.awt.*;

public class Line
{
	
	double Ax;
	double Ay;
	
	double Bx;
	double By;
	
	double Ux;
	double Uy;
	
	double Nx;
	double Ny;
	
	public Line(double x0, double y0, double x1, double y1)
	{
		Ax = x0;
		Ay = y0;
		
		Bx = x1;
		By = y1;
		
		
		double vx = Ax - Bx;
	    double vy = Ay - By;
	    
	    double mag_v = Math.sqrt(vx*vx + vy*vy);
	    
	    Ux = vx / mag_v;
	    Uy = vy / mag_v;
	        
	    Nx =  Uy;
	    Ny = -Ux;				
	}
	
	public double distanceTo(double Px, double Py)
	{
		return (Px - Bx) * Uy - (Py - By) * Ux;
	}
	
	public void draw(Graphics pen)
	{
		pen.drawLine((int)Ax, (int)Ay, (int)Bx, (int)By);
	}

}
