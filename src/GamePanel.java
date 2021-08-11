//-----------------------------------------------------------------------------
// The GamePanel class implements the game loop and asynchronous input system
//-----------------------------------------------------------------------------
// At the moment this is also where details for specific games are coded 
//-----------------------------------------------------------------------------





import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument.LeafElement;

//-----------------------------------------------------------------------------

public abstract class GamePanel extends    JPanel 
                                implements Runnable, 
                                           KeyListener, 
                                           MouseListener, 
                                           MouseMotionListener
{
   
    //-------------------------------------------------------------------------
    // These are the mouse inputs that will be recognized
    //-------------------------------------------------------------------------
	
	boolean mousePressed = false;

	
	//-------------------------------------------------------------------------
    // These are the keyboard inputs that will be recognized
    //-------------------------------------------------------------------------
         
	boolean[] pressing = new boolean[1024];
    
   public final static int UP = KeyEvent.VK_UP;
   public final static int DN = KeyEvent.VK_DOWN;
   public final static int LT = KeyEvent.VK_LEFT;
   public final static int RT = KeyEvent.VK_RIGHT;

   public final static int CTRL   = KeyEvent.VK_CONTROL;
   public final static int SHIFT  = KeyEvent.VK_SHIFT;
   public final static int COMMA  = KeyEvent.VK_COMMA;
   public final static int PERIOD = KeyEvent.VK_PERIOD;

   public final static int SPACE = KeyEvent.VK_SPACE;

   public final static int A = KeyEvent.VK_A;
   public final static int B = KeyEvent.VK_B;
   public final static int C = KeyEvent.VK_C;
   public final static int D = KeyEvent.VK_D;
   public final static int E = KeyEvent.VK_E;
   public final static int F = KeyEvent.VK_F;
   public final static int G = KeyEvent.VK_G;
   public final static int H = KeyEvent.VK_H;
   public final static int I = KeyEvent.VK_I;
   public final static int J = KeyEvent.VK_J;
   public final static int K = KeyEvent.VK_K;
   public final static int L = KeyEvent.VK_L;
   public final static int M = KeyEvent.VK_M;
   public final static int N = KeyEvent.VK_N;
   public final static int O = KeyEvent.VK_O;
   public final static int P = KeyEvent.VK_P;
   public final static int Q = KeyEvent.VK_Q;
   public final static int R = KeyEvent.VK_R;
   public final static int S = KeyEvent.VK_S;
   public final static int T = KeyEvent.VK_T;
   public final static int U = KeyEvent.VK_U;
   public final static int V = KeyEvent.VK_V;
   public final static int W = KeyEvent.VK_W;
   public final static int X = KeyEvent.VK_X;
   public final static int Y = KeyEvent.VK_Y;
   public final static int Z = KeyEvent.VK_Z;

   public final static int _0 = KeyEvent.VK_0;
   public final static int _1 = KeyEvent.VK_1;
   public final static int _2 = KeyEvent.VK_2;
   public final static int _3 = KeyEvent.VK_3;
   public final static int _4 = KeyEvent.VK_4;
   public final static int _5 = KeyEvent.VK_5;
   public final static int _6 = KeyEvent.VK_6;
   public final static int _7 = KeyEvent.VK_7;
   public final static int _8 = KeyEvent.VK_8;
   public final static int _9 = KeyEvent.VK_9;

   public final static double PIE = Math.PI;

	//-------------------------------------------------------------------------
    // The Game Loop will run in a Thread  
    // This will be the reference to that Thread
    //-------------------------------------------------------------------------
    
    Thread t;
    
	//-------------------------------------------------------------------------
    // Initialize GamePanel by setting up the Listeners, Thread, & BG Color
    //-------------------------------------------------------------------------

    public void init()
    {  
    	addKeyListener(this);   // Tell the OS I want to know about KeyEvents
    	
    	addMouseListener(this); // Tell the OS I want to know about MouseEvents
    	
    	addMouseMotionListener(this); // Tell the OS I want to know about MouseEvents
    	
    	initialize();
    	
    	t = new Thread(this);   // Create the Thread for the Game Loop
    	
    	t.start();              // Start running the Game Loop
        
    	requestFocus();         // Accept keystrokes in the GamePanel
    }
    
	//-------------------------------------------------------------------------

    public abstract void initialize();
    
    //-------------------------------------------------------------------------
    // GameLoop - run in a Thread independent of the rest of the program
    //-------------------------------------------------------------------------
    // The while loop in the Game Loop executes once every 60th of a second
    //
    // Each time through the loop:
    //
    //   Respond to the user's keyboard input
    //
    //   The O.S. is asked to repaint the screen
    //
    //   The Thread goes to sleep for 15 milliseconds (i.e. < 1 / 60 second)
    //-------------------------------------------------------------------------
   
    public void run()
    {   	
        // images should draw every 60 seconds
        int counter = 4;
    	while(true)   // Game Loop
    	{	
    		respond_To_User_Input();
    		
    		move_Computer_Controlled_Entities();
    		
    		resolve_Collisions();
 		
        	// Ask the O.S. to call paint
                if (counter == 4){
                    repaint();
                    counter = 0;
                }
    		
     		// Game Loop Thread sleeps for a bit less than 1/60 second
    		try
    		{
    			t.sleep(15);
                        counter++;
    		}
    		catch(Exception x) {};    		
    	}
    }

	//-------------------------------------------------------------------------

    public abstract void respond_To_User_Input();

    //-------------------------------------------------------------------------
    
	public abstract void move_Computer_Controlled_Entities();
	
    //-------------------------------------------------------------------------

	public abstract void resolve_Collisions();
	
    //-------------------------------------------------------------------------

    public void mousePressed(MouseEvent e)
    {
    }
    
	//-------------------------------------------------------------------------
    // Not used yet
	//-------------------------------------------------------------------------
    
    public void mouseReleased(MouseEvent e)
    {
    	
    }
    
	//-------------------------------------------------------------------------

    public void mouseMoved(MouseEvent e)
    {
    	
    }
    
	//-------------------------------------------------------------------------

    public void mouseDragged(MouseEvent e)
    {
    	
    }
    
    //-------------------------------------------------------------------------
	// When a key is pressed record which key for later query in the Game Loop
	//-------------------------------------------------------------------------

	public void keyPressed(KeyEvent e)
	{
		pressing[e.getKeyCode()] = true;
	}
	
	//-------------------------------------------------------------------------
	// When a key is released mark all key released
	//-------------------------------------------------------------------------

	public void keyReleased(KeyEvent e)
	{
		pressing[e.getKeyCode()] = false;
	}
	
	//-------------------------------------------------------------------------
	// These methods are not needed for the game, but must be defined to
	// satisfy the contracts to implement MouseListener and KeyListener
	//-------------------------------------------------------------------------
    public void mouseClicked(MouseEvent e) {}
	//-------------------------------------------------------------------------
    public void mouseEntered(MouseEvent e) {}
	//-------------------------------------------------------------------------
    public void mouseExited(MouseEvent e)  {}    
	//-------------------------------------------------------------------------
	public void keyTyped(KeyEvent e) {}	
	//-------------------------------------------------------------------------
	
}
