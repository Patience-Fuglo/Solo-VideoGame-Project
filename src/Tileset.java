import java.awt.Image;
import java.awt.Toolkit;

public class Tileset {
	Image[] images;
	
	public Tileset(String filepath, String[] name, String filetype) {
		images = new Image[name.length];
		for(int i = 0; i < name.length; i++) {
			System.out.println("./image/" + filepath + name[i] + "." + filetype);
	        images[i] = Toolkit.getDefaultToolkit().getImage("./image/" + filepath + name[i] + "." + filetype);
		}
	}
	
	public int size() {
		return images.length;
	}
	
	public Image getTileImage(int index)
	{
		return images[index];
	}
}
