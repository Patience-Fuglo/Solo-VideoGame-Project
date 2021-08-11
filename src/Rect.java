

import java.awt.*;

public class Rect
{
	public double px;
	public double py;
	
	double vx = 0;
	double vy = 0;
	
	double ax = 0;
	double ay = 0;

	int w;
	int h;
	
	Color c;
	
	public Rect(int x, int y, int w, int h, Color c)
	{
		this.px = x;
		this.py = y;
		
		this.w = w;
		this.h = h;
		
		this.c = c;
	}
	
	public void draw(Graphics pen)
	{
		pen.setColor(c);
		
		pen.drawRect((int)px - Camera.dx(), (int)py - Camera.dy(), w, h);
		

		pen.setColor(Color.green);
		pen.drawRect((int)px + w - Camera.dx(), (int)py + h - 1 - Camera.dy(), 5, 5);
	}
	

	public void drawHighlight(Graphics pen, Color highlightColor)
	{
		pen.setColor(highlightColor);
		
		pen.drawRect((int)px - Camera.dx(), (int)py - Camera.dy(), w, h);
	}
	
	public void setColor(Color c)
	{
		this.c = c;
	}
	
	
	public void setVelocity(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
	}
	
	public void setAcceleration(double ax, double ay)
	{
		this.ax = ax;
		this.ay = ay;
	}
	
	public void move()
	{
		px += vx;
		py += vy;
		
		vx += ax;
		vy += ay;
	}
	
	public void moveBy(int dx, int dy)
	{
		px += dx;
		py += dy;
	}
	
	public void moveUp(int dy)
	{
		py -= dy;
	}
	
	public void moveDown(int dy)
	{
		py += dy;
	}
	
	public void moveLeft(int dx)
	{
		px -= dx;
	}
	
	public void moveRight(int dx)
	{
		px += dx;
	}
	
	//-------------------------------------------------------------------------
	// Is point (mx, my) contained within this Axis Aligned Rect (x, y, w, h)? 
	//-------------------------------------------------------------------------
	
	public boolean contains(int mx, int my)
	{
		return (mx >= px) && (mx <= px+w) && (my >= py) && (my <= py+h);
	}
	

	
	public boolean overlaps(Rect r)
	{
		return (r.px + r.w >=   px)  &&
			   (  px +   w >= r.px)  &&
			   (  py +   h >= r.py)  &&
			   (r.py + r.h >=   py);
	}
	
	public boolean bottom_overlaps(Rect r)
	{
		return (r.px + r.w     >=   px + 5)  &&
			   (  px +   w - 5     >= r.px)      &&
			   (  py +   h     >= r.py)      &&
			   (r.py + r.h     >=   py + h);
	}
	

	public boolean bottomRight_overlaps(Rect r)
	{
		return (r.px + r.w     >=   px + w)  &&
				   (  px +   w   >= r.px)      &&
				   (  py +   h -1    >= r.py)      &&
				   (r.py + r.h       >=   py + 1);
	}

	public boolean bottomLeft_overlaps(Rect r)
	{
		return (r.px + r.w     >=   px)  &&
				   (  px   >= r.px)      &&
				   (  py +   h -1    >= r.py)      &&
				   (r.py + r.h       >=   py + 1);
	}
}
