import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Collectible extends Rect {
	Image image;
	int score;
	int delay = 10;
	int delayMax = 10;
	boolean switchDirection = false;
	public boolean collected = false;
	public boolean collectedShownScore = false;

	public Collectible(int x, int y, int w, int h, Image image, int score) {
		super(x, y, w, h, Color.yellow);

		this.image = image;
		this.score = score;
	}

	public void draw(Graphics pen)
	{
        super.draw(pen);
        
		// when count down complete
		if(delay == 0)
		{
			if(switchDirection)
				switchDirection = false;
			else
				switchDirection = true;
			// reset count down
			delay = delayMax;
		}
		
		// count down
		delay--;


		if(switchDirection)
			moveDown(1);
		else
			moveUp(1);
		
		
    	pen.drawImage(image, (int)(px - Camera.dx()), (int)(py - Camera.dy()), w, h, null);
	}

	public void drawUI(Graphics pen, int x, int y, int w, int h)
	{
        super.draw(pen);
        
		// when count down complete
		if(delay == 0)
		{
			if(switchDirection)
				switchDirection = false;
			else
				switchDirection = true;
			// reset count down
			delay = delayMax;
		}
		
		// count down
		delay--;


		if(switchDirection)
			moveDown(1);
		else
			moveUp(1);
		
		
    	pen.drawImage(image, (int)(x), (int)(y), w, h, null);
	}
}
