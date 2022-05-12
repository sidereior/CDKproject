import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.sound.sampled.*;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.smiles.SmilesParser;
import javax.swing.*;
import java.util.Scanner;

public class World
{
  private boolean paused = false;
  private boolean started = false;
  private static ArrayList<File> soundtrack = new ArrayList<File>();


  public static void main(String[] args)
  {
    run();
  }


  public static void run()
  {
    Scanner reader = new Scanner(System.in);
    System.out.println("Enter an element: ");
    String n = reader.next(); // Scans the next token of the input as an int.
    reader.close();
    Display display = new Display(500, 500, n);
    display.run();
  }

  private ArrayList<Sprite> sprites;
  private int width;
  private int height;
  private String title;
  private String elementToDisplay;
  public World(int w, int h, String name, String element)
  {
    title=name;
    width = w;
    height = h;
    sprites = new ArrayList<Sprite>();
    elementToDisplay=element;

  }
  //(double theLeft, double theTop, int theWidth, int theHeight, String element

  public void stepAll()
  {
    for (int i = 0; i < sprites.size(); i++)
    {
      Sprite s = sprites.get(i);
      //TODO: NEED TO DO THIS SOME OTHER WAY, STEP ALL IS NOT THE WAY TO GO!
      //TODO: maybe within run this can be fixed??
      if(sprites.get(i).getType()=="VisualizeElement")
      {
        try {
          System.out.println("running step all");
          generatePng(elementToDisplay);
          sprites.add(new VisualizeElement(0,0,400,400,elementToDisplay + ".png"));
          System.out.println("Size of sprites: " + sprites.size());
        }
        catch(Exception e)
        {
          throw new RuntimeException("The element you entered, " + elementToDisplay + "was not valid. Your structure is likely wrong.");
        }

      }
      s.step(this);
    }
  }

  public void generatePng(String visElement) throws Exception
  {
    try {
      IChemObjectBuilder bldr = SilentChemObjectBuilder.getInstance();
      SmilesParser smipar = new SmilesParser(bldr);
      IAtomContainer mol = smipar.parseSmiles(elementToDisplay);
      mol.setProperty(CDKConstants.TITLE, elementToDisplay);
      DepictionGenerator dptgen = new DepictionGenerator();
      dptgen.withSize(200, 250).withMolTitle().withTitleColor(Color.DARK_GRAY);
      BufferedImage visual = dptgen.depict(mol).toImg();
      File outputfile = new File(elementToDisplay + ".png");
      ImageIO.write(visual, "png", outputfile);
    }
    catch(Exception e)
    {
      throw new RuntimeException("Invalid Depiction of Element " + elementToDisplay + ", try altering strucutre because structure is not supported");
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