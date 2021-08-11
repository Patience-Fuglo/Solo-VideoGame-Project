

import java.awt.*;

public class ImageLayer
{
	int x;
	int y;
	int z;

	Image image;
	

	public ImageLayer(int x, int y, int z, String filename)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.image = Toolkit.getDefaultToolkit().getImage("./image/" + filename);
	}
	
	public void draw(Graphics pen)
	{
		int w = image.getWidth(null);
		
		for(int i = 0; i < 20; i++)
			
			pen.drawImage(image, x + i * w - Camera.x / z, y, null);
	}

}
