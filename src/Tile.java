import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Tile extends Rect {
	Tileset tileset;
	int index;
	int tx, ty, w, h;
	Image image;
	
	public Tile(int x, int y, int w, int h, Tileset tileset, int index) {
		super(x, y, w, h, Color.white);
		// TODO Auto-generated constructor stub
		this.tileset = tileset;
		this.index = index;
		this.tx = x;
		this.ty = y;
		this.w = w;
		this.h = h;
	}

	public void draw(Graphics pen)
	{
        super.draw(pen);
        
        if(index >= 0 && index < tileset.size()) {
        	pen.drawImage(tileset.getTileImage(index), tx - Camera.dx(), ty - Camera.dy(), w, h, null);
		}
	}
}