//-----------------------------------------------------------------------------
// The Sprite class implements an object that can move around the screen
// while displaying animations appropriate to the circumstances  
//-----------------------------------------------------------------------------



import java.awt.*;
//-----------------------------------------------------------------------------

public class Sprite extends Rect
{
        //new String[]{"Jump", "Slide", "Walk", "Walk", "Run", "Run", "Sit-down00", "Idle", "Idle", "Death", "Sneaking"}, 
	//-------------------------------------------------------------------------
		
	Animation[] animation;  // an array of Animation objects to select from
	boolean animationsLoaded = false;
        
        final int UP    = -1; 
        final int CENTERED  = 0; 
        final int DOWN  = 1; 
        final int LEFT  = -1; 
        final int RIGHT = 1;  
		
        final int UP_JUMP    = 0;    // Convenience constants
        final int DOWN_FALL  = 1;    // indexing the array of
        final int LEFT_WALK  = 2;    // Animations according to
        final int RIGHT_WALK = 3;    // the type of motion
        final int RIGHT_RUN = 4;
        final int LEFT_RUN = 5;
        final int DOWN_SIT = 6;
        final int LEFT_IDLE = 7;
        final int RIGHT_IDLE = 8;
        final int DEATH = 9;
        final int SNEAKING = 10;
        

    
	boolean moving  = false; // the Sprite can be moving or still
	
	int motion_horizontal = RIGHT;        // index indicating the type of motion
	int motion_vertical = CENTERED;        // index indicating the type of motion
	int animation_state = RIGHT_IDLE;        // index indicating the type of motion
	
	boolean alive = true;

	int pushGroundTimer = 0;
	int jumpForce = 10;
	int jumpTimer = 0;
	int jumpMax = 50;
	boolean isGrounded = true;
	boolean jumping = false;
	int jumpHeight;
	
	public Tile bottomTile;
	public Tile bottomRightTile;
	public Tile bottomLeftTile;
	
	
	//-------------------------------------------------------------------------
	// Construct the Sprite
	//-------------------------------------------------------------------------
	
	public Sprite(int x, int y, int w, int h, String name, String[] pose, int count, String filetype)
	{
		super(x, y, w, h, Color.RED);
		 
		// Set length of array to match the number of Animations
		animation = new Animation[pose.length];
		 
		// Load the Animations
		for(int i = 0; i < pose.length; i++)
		{
			animation[i] = new Animation(name + "_" + pose[i] + "_", count, filetype);
		}
	}
	
	//-------------------------------------------------------------------------
	// Construct the Sprite
	//-------------------------------------------------------------------------
	
	public Sprite(int x, int y, int w, int h, String name, String[] pose, int[] count, String filetype)
	{
		super(x, y, w, h, Color.RED);
		 
		// Set length of array to match the number of Animations
		animation = new Animation[pose.length];
		 
		// Load the Animations
		for(int i = 0; i < pose.length; i++)
		{
			animation[i] = new Animation(name + "_" + pose[i] + "_", count[i], filetype);
		}
	}
        
        // Explicitly loads the player
	/*public Sprite(int x, int y, int w, int h, String name, String[] pose, int[] count, String filetype, String delimiter, int leadingZeroes, String folder)
	{
		super(x, y, w, h, Color.RED);
		 
		// Set length of array to match the number of Animations
		animation = new Animation[pose.length];
		 
		// Load the Animations
		for(int i = 0; i < pose.length; i++)
		{
			//animation[i] = new Animation(pose[i] + delimiter + name + "_", count[i], filetype, leadingZeroes);
            //System.out.println(folder + "/" + pose[i] + delimiter + name + "_" + pose[i] + ", " + count[i]);
			animation[i] = new Animation(folder + "/" + pose[i] + delimiter + name + "_", count[i], filetype, leadingZeroes);
		}
	}*/
	
	public Sprite(int x, int y, int w, int h, String name, String[] pose, int[] count, String filetype, String delimiter, int leadingZeroes, String folder)
	{
		super(x, y, w, h, Color.RED);
		 
		// Set length of array to match the number of Animations
		animation = new Animation[pose.length];
		 
		// Load the Animations
		for(int i = 0; i < pose.length; i++)
		{
			//animation[i] = new Animation(pose[i] + delimiter + name + "_", count[i], filetype, leadingZeroes);
            //System.out.println(folder + "/" + pose[i] + delimiter + name + "_" + pose[i] + ", " + count[i]);
			animation[i] = new Animation(folder + "/" + name + delimiter + pose[i] + "_", count[i], filetype, leadingZeroes);
		}
  ///Set animation delay
		animation[LEFT_IDLE].setDelay(10);
		animation[RIGHT_IDLE].setDelay(10);
		animation[UP_JUMP].setDelay(3);
		animation[LEFT_WALK].setDelay(2);
		animation[RIGHT_WALK].setDelay(2);
	}
	
	
	
	//-------------------------------------------------------------------------
	
	public void jump(int dy)
	{
		if(isGrounded) {
			jumpHeight = dy;
			
			moving = true;   // Cause paint method to use Animation's current image
			
			animation_state = UP_JUMP;     // Set Animation array index according to this action
		}
	}


	public void handlePhysics() {		
		if(pushGroundTimer < jumpForce && animation_state == UP_JUMP) {
			pushGroundTimer++;
		}
		else if (pushGroundTimer < jumpForce && pushGroundTimer != 0 && animation_state != UP_JUMP &&  jumpTimer < jumpMax){
			// Player moved or canceled jump
			pushGroundTimer = 0;
			moving = false;
            animation[UP_JUMP].reset();
		}
		else if(pushGroundTimer >= jumpForce && jumpTimer < jumpMax) {
			isGrounded = false;
			
			if(vy == 0) {
				vy -= jumpHeight;         // moving up on the screen corresponds to y going down
			}
			
			move();
			
			// listener
			onJump();
			
			jumping = true;
			
			jumpTimer++;
		}
		else if (((pushGroundTimer >= jumpForce && jumpTimer >= jumpMax) || !jumping) && !isGrounded) {
			// Player should be falling
			fall(10);
			
			move();
			
			animation_state = DOWN_FALL;
		}
		else {
			// Reset jump ability
			if(jumping && jumpTimer >= jumpMax) {
				jumping = false;
				jumpTimer = 0;
				pushGroundTimer = 0;
	            animation[UP_JUMP].reset();
	            moving = false;
			}

			// Player is grounded and can either stay in motion or remain idle.

				if(vx != 0) {
					move();
				}

				
			if(vx < 0) {
				motion_horizontal = LEFT;
                animation_state = LEFT_WALK;
                animation[UP_JUMP].reset();
			}
			else if(vx > 0) {
				motion_horizontal = RIGHT;
                animation_state = RIGHT_WALK;
                animation[UP_JUMP].reset();
			}
			else {
				moving = false;
			}
			
	        if(!moving && motion_horizontal == LEFT)
			{
	            animation_state = LEFT_IDLE;
			}
	        else if(!moving && motion_horizontal == RIGHT){
	            animation_state = RIGHT_IDLE;
	        }
		}
	}
	
	public void onJump() {
		// Place code here, or subclasses should
	}
	
	public void fall(int dy) {
		if(vy <= 0) {
			vy = 0;
			vy += dy;
		}
		
		moving = true;
	}
	
	public void setGrounded(boolean value) {
		if(value)
			vy = 0;
		isGrounded = value;
	}
	
	//-------------------------------------------------------------------------
		
	public void moveUp(int dy)
	{
		//vy -= dy;         // moving up on the screen corresponds to y going down
		
		//moving = true;   // Cause paint method to use Animation's current image
		
		//animation_state = UP_JUMP;     // Set Animation array index according to this action
	}
	
	//-------------------------------------------------------------------------

	public void moveDown(int dy)
	{
		//vy += dy;
		
		//moving = true;

		if(animation_state != UP_JUMP) {
			animation_state = DOWN_SIT;
            animation[UP_JUMP].reset();
		}
	}
	
	//-------------------------------------------------------------------------

	public void moveLeft(int dx)
	{
		if((motion_horizontal != LEFT || vx >= 0)) {
			vx = 0;
			vx -= dx;
		}
		moving = true;
	}
	
	//-------------------------------------------------------------------------

	public void moveRight(int dx)
	{
		if((motion_horizontal != RIGHT || vx <= 0)) {
			vx = 0;
			vx += dx;
		}
		moving = true;
	}
	
	//-------------------------------------------------------------------------

	public void stopHorizontalMovement(int dx)
	{
		vx = 0;
	}
	
	//-------------------------------------------------------------------------

	public void dies()
	{
		alive = false;
		
		py = -100000;
	}
	
	//-------------------------------------------------------------------------
	// Draws the Sprite on the screen according to its state:
	//   moving indicated whether to use the Animation's current or still image
	//   motion is an index into the array of Animations to pick the right pose    
	//-------------------------------------------------------------------------

        @Override
	public void draw(Graphics pen)
	{
        super.draw(pen);
	    if(alive)
		{
            if(motion_horizontal == LEFT)
                pen.drawImage(animation[animation_state].getCurrentImage(),
                        (int)(px + (w * 3.5) - Camera.dx()),
                        (int)(py - (h / 1.65) - Camera.dy()),
                        (int)(motion_horizontal * animation[animation_state].getCurrentImage().getWidth(null) * .5),
                        (int)(animation[animation_state].getCurrentImage().getHeight(null) * .5), null);
            else if (motion_horizontal == RIGHT){
                pen.drawImage(animation[animation_state].getCurrentImage(),
                        (int)(px - (w * 2.5) - Camera.dx()),
                        (int)(py - (h / 1.65) - Camera.dy()),
                        (int)(motion_horizontal * animation[animation_state].getCurrentImage().getWidth(null) * .5),
                        (int)(animation[animation_state].getCurrentImage().getHeight(null) * .5), null);
            }
			
			//super.draw(pen);
		}
	}
        
        public void loadImages(Graphics pen) {
        	if(!animationsLoaded) {
            	for(int i = 0; i < animation.length; i++) {
                    pen.drawImage(animation[animation_state].getCurrentImage(),
                            (int)px + w - Camera.dx(),
                            (int)py - Camera.dy(),
                            (int)(motion_horizontal * animation[animation_state].getCurrentImage().getWidth(null)  * .25),
                            (int)(animation[animation_state].getCurrentImage().getHeight(null)  * .25), null);
            	}
            	animationsLoaded = true;
        	}
        }
	
	//-------------------------------------------------------------------------

}
