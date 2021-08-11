


public  class Camera
{
	static int offSetX;
	static int offSetY;
	
	static int initialX;
	static int initialY;
	
	static int x;
	static int y;
	
	public static void intialize(int x, int y, int offsetX, int offsetY)
	{
		Camera.x = x;
		Camera.y = y;
		
		initialX = x;
		initialY = y;
		
		Camera.offSetX = offsetX;
		Camera.offSetY = offsetY;
	}
	
	public static int dx() {
		return x - initialX;
	}
	public static int dy() {
		return y - initialY;
	}
	
	public static void track(Rect rect) {
		x = (int)(rect.px + offSetX);
		y = (int)(rect.py + offSetY);
	}
	
	
	public static void moveRight(int dx)
	{
		x += dx;
	}

	public static void moveLeft(int dx)
	{
		x -= dx;
	}

	public static void moveUp(int dy)
	{
		y -= dy;
	}

	public static void moveDown(int dy)
	{
		y += dy;
	}
}
