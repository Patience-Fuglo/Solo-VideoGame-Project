//-----------------------------------------------------------------------------
// This class initializes a JFrame with GamePanel as its ContentPane
//-----------------------------------------------------------------------------
// Once initialized all action in the game initiates in the GamePanel class
//-----------------------------------------------------------------------------



import javax.swing.*;

//-----------------------------------------------------------------------------

public class GameStart extends JFrame
{
	//-------------------------------------------------------------------------
	
	static MyGame game_panel = new MyGame();
	
	//-------------------------------------------------------------------------

	public static void main(String[] args)
	{
		new GameStart();     // Instantiate the JFrame 
		   
		game_panel.init();   // Hand off to the GamePanel
	}
	
	//-------------------------------------------------------------------------
    // GameStart constructor sets up the JFrame for hand-off to the GamePanel
	//-------------------------------------------------------------------------

	public GameStart()
	{   
	   setTitle("GameStart");
	   
	   setSize(1900, 1070);
	   
	   //setLocation(-1910, 10);
	   
	   getContentPane().add(game_panel);
	   
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	   

	   setVisible(true);    
	}
	
	//-------------------------------------------------------------------------

}
