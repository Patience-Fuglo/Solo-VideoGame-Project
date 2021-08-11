//-----------------------------------------------------------------------------



import java.awt.*;
//-----------------------------------------------------------------------------

public class Animation
{
	//-------------------------------------------------------------------------

	// The sequence of images in the Animation
	Image[] image;
	
	// Index into the array of images
	int current = 1;
	
	// The first image is a still pose
	final int still = 0;
	
	// count down from 10
	int delay = 10;
	int delayMax = 10;
	
	//-------------------------------------------------------------------------
	// Construct the Animation object:
	//-------------------------------------------------------------------------
	// name     : leading substring shared by all image files in the Animation
	// count    : number of images in the Animation
	// filetype : filetype shared by all image files in the Animation  
	//-------------------------------------------------------------------------
	// filepath = "./image/[shared name][index#].[filetype]"
	//-------------------------------------------------------------------------

	public Animation(String name, int count, String filetype)
	{
		// Create array to store images   
		image = new Image[count];
		
		// Load all the images from files into the array
		for(int i = 0; i < count; i++)
		{  
			image[i] = Toolkit.getDefaultToolkit().getImage("./image/" + name + i + "." + filetype);
		}
	}
	
	public void setDelay(int value) {
		delay = value;
		delayMax = value;
		System.out.println(delay + ", " + delayMax);
	}
	
	public void reset() {
		current = 1;
	}
        
        
	/*public Animation(String name, int count, String filetype, int leadingZeroes)
	{
            String format = "%0" + leadingZeroes + "d";
            // Create array to store images   
            image = new Image[count];

            // Load all the images from files into the array
            for(int i = 0; i < count; i++)
            {
                String filepath = "./image/" + name + (String.format(format, i + 1)) + "." + filetype;
                //System.out.println(filepath);
                    image[i] = Toolkit.getDefaultToolkit().getImage(filepath);
            }
	}*/
	

	public Animation(String name, int count, String filetype, int leadingZeroes)
	{
            String format = "%0" + leadingZeroes + "d";
            // Create array to store images   
            image = new Image[count];

            
            // FOR FOXY
            System.out.println(name);
            
            // Load all the images from files into the array
            for(int i = 0; i < count; i++)
            {
            	// NOTE INCREMENT WITH i + 1 IF YOU WANT TO INCLUDE hero01
                String filepath = "./image/" + name + (String.format(format, i)) + "." + filetype;
                //System.out.println(filepath);
                    image[i] = Toolkit.getDefaultToolkit().getImage(filepath);
            }
	}
	
	//-------------------------------------------------------------------------
	// draw the current image as reported by the getCurrentImage method
	//-------------------------------------------------------------------------

	public void draw(Graphics pen)
	{
		pen.drawImage(getCurrentImage(), 200, 200, null);
		//pen.drawImage(getCurrentImage(), getCurrentImage().getWidth(null), getCurrentImage().getHeight(null), null);
	}
	
	//-------------------------------------------------------------------------
	// return one image from the array as indicated by the index current
	//-------------------------------------------------------------------------
	// The index current is updated when the delay count down reaches zero
	// at which point the delay is reset to its maximum value
	//-------------------------------------------------------------------------

	public Image getCurrentImage()
	{
		// when count down complete
		if(delay == 0)
		{
			// index next image
			current++;

			// cycle back to first image after last image
			if (current == image.length)  current = 1; 
				
			// reset count down
			delay = delayMax;
		}
		
		// count down
		delay--;
		
                //System.out.println("Get Current Image: " + current);
		return image[current];
	}
	
	//-------------------------------------------------------------------------
	// return the still image associated with the Animation
	//-------------------------------------------------------------------------

	public Image getStillImage()
	{   
                //System.out.println("Get Still Image: " + still);
		return image[still];
	}
	
	//-------------------------------------------------------------------------
	
}
