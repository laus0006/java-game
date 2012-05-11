import java.awt.Canvas;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;


public class JavaGame {
	
	public static final int FRAME_DELAY = 20;	// frame delay in milliseconds (i.e. 1000/20 = 50 FPS)
	public static final int KEY_DELAY = 75; 	// set delay in milliseconds between key strokes
	
	public static String nextThread;
	
	// tile level identifiers
	public static final int LEVEL_FLOOR = 0;
	public static final int LEVEL_WALL = 1;
	
	//animation state identifiers
	public static final int STATE_NORMAL = 0;
	public static final int STATE_INJURED = 1;
	public static final int ANIM_STILL = 1;
	public static final int ANIM_WALK_LEFT = 2;
	public static final int ANIM_WALK_RIGHT = 3;
	public static final int ANIM_WALK_UP = 4;
	public static final int ANIM_WALK_DOWN = 5;
	
	//HUD icon ID's
	public static final int HUD_HEART = 0;
	public static final int HUD_SWORD = 1;

	public static World world;
	public static GameTime gametime;
	public static SkyFade fadeSky;
	public static BufferedImage[] tileSkins;
	public static BufferedImage[] enemySkins;
	public static BufferedImage[] entitySkins;
	public static BufferedImage[] skySkins;
	public static BufferedImage[] entityFriendlySkins;
	public static BufferedImage[] HUDIcons;
	
	public static CollisionDetection collisionDetection = new CollisionDetection();

	// tile offset for scrolling
	public static int xOffset = 0;
	public static int yOffset = 0;
	public static int prevXOffset = 0;
	public static int prevYOffset = 0;

	// screen size info to aide scrolling
	public static int screenWidth = 0;
	public static int screenHeight = 0;
	public static int screenTilesWide = 0;
	public static int screenTilesHigh = 0;
	public static int tileWidth = 32;
	public static int tileHeight = 32;
	public static int guiWidth = 0;
	public static int guiHeight = 0;

	public static void main(String[] args) {
	//	/*
		
		if(System.getProperty("os.name").startsWith("Win"))
            System.setProperty("sun.java2d.d3d","true");
		else
            System.setProperty("sun.java2d.opengl", "true");
		
		JFrame frame = new JFrame();
		Canvas gui = new Canvas();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(gui);
		
		frame.setTitle("Java-Game V0.1 - Map Editor");
		frame.setVisible(true); // start AWT painting.
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setMinimumSize(frame.getSize());			//prevents window shrinking when moved
		frame.setResizable(true);
		
		//load resources in to memory
		Thread gThread = new Thread(new LoadResources());
		frame.setTitle("Loading");
		gThread.start();
		do{}while(gThread.isAlive());	//wait until resources are loaded to continue
		
		gThread = new Thread(new StartScreen(gui));
		
		fadeSky = new SkyFade();
		
		/* GameTime Section */
		/* new GameTime set to 2000 so that the game starts in the day, while testing */
		gametime = new GameTime(2000);
		Thread pThread = new Thread(new GameTimeUpdater(gametime));		
		
		
		gThread.start();
		
		
		Boolean isRunning = true;
		do{
			if(!gThread.isAlive()){
				String[] command = nextThread.split("[:]");
				switch(command[0]){
				case "MENU":
					gThread = new Thread(new StartScreen(gui));
					break;
				case "EDIT":
					gThread = new Thread(new EditorLoop(gui));
					
					break;
				case "GAME":
					gThread = new Thread(new GameLoop(gui));
					pThread = new Thread(new GameTimeUpdater(gametime)); // Start the Game Time when game selected
					pThread.start();	//Starts the game Time thread
					break;
				}
				gThread.start();
			}
		}while(isRunning);
	}

	//Used to return the objects (Games) Time
	
	public static GameTime returnTime(){ 
		return gametime;
	}
}
