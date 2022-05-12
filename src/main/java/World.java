import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import javax.swing.*;
import javax.sound.sampled.*;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.smiles.SmilesParser;
import javax.swing.*;

public class World
{
  private boolean paused = false;
  private boolean started = false;
  private static ArrayList<File> soundtrack = new ArrayList<File>();

  public static void run()
  {
    Display display = new Display(500, 500, "CN1C=NC2=C1C(=O)N(C(=O)N2C)C");
    display.run();
  }

  private ArrayList<Sprite> sprites;
  private int width;
  private int height;
  private String title;
  private String visualizeElement;

  public World(int w, int h, String name, String element)
  {
    title=name;
    width = w;
    height = h;
    sprites = new ArrayList<Sprite>();
    visualizeElement=element;
  }
  
  public void stepAll()
  {
    for (int i = 0; i < sprites.size(); i++)
    {
      Sprite s = sprites.get(i);
      s.step(this);
    }
  }

  public int getWidth()
  {
    return width;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public int getNumSprites()
  {
    return sprites.size();
  }
  
  public Sprite getSprite(int index)
  {
    return sprites.get(index);
  }
  
  public void mouseClicked(int x, int y)
  {
   /* if(pause.isClicked(x,y))
    {
      if(!paused)
      {
        vex = ball.getVX();
        vey = ball.getVY();
        ball.setVX(0);
        ball.setVY(0);
        paused = true;
        pause.setImage("resume.png");
      }
      else
      {
        ball.setVX(vex);
        ball.setVY(vey);
        paused = false;
        pause.setImage("pause.png");
      }
    }
    */

    
  }
  
  public void keyPressed(int key)
  {


  }
  
  public void keyReleased(int key)
  {

  }
  
  public String getTitle()
  {
    return title;
  }
  
  public void paintComponent(Graphics g) throws Exception
  {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
    for (int i = 0; i < sprites.size(); i++)
    {
      Sprite sprite = sprites.get(i);
      g.drawImage(Display.getImage(sprite.getImage()),
                  (int)sprite.getLeft(), (int)sprite.getTop(),
                  sprite.getWidth(), sprite.getHeight(), null);
    }
    g.setColor(Color.WHITE);


  }
}