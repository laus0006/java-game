
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Entity {

    private String entity = "Images/entity.png";

    private int dx;
    private int dy;
    private int x;
    private int y;
    private int height;
    private int width;
    private Image image;
    
    Enemy en;
    

    public Entity() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(entity));
        image = ii.getImage();
        //width = image.getWidth(null); //dont think needed
        //height = image.getHeight(null);
        x = 40;
        y = 60;
    }


    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    
    public Rectangle getBounds(){	//Get bounds for collision detection
    	return new Rectangle (x,y,32,32); //32, 32 size of tile
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    
    /*public void checkCollisions(){
    	//Rectangle r1 = Level1.getBounds();
    	Rectangle r2 = getBounds();
    	//Rectangle r3 = en.getBounds();
    	
    	if (r2.intersects()){
    		System.out.println("COLLISION!");
    	}
    	}
    	*/
}