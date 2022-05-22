import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.smiles.SmilesParser;


import java.awt.Color;

public class World
{
  private boolean paused = false;
  private boolean started = false;
  private String elementName = "NULL";
  public String imageName="";
  public String smiles="";
  boolean wait=false;
  boolean updated=false;
  boolean graphUpdate=false;
  private int numSteps=0;
  public BufferedImage visual;

  public static void main(String[] args)
  {
    run();
  }

  public static void run()
  {
    Display display = new Display(600, 600);
    display.run();

  }



  private ArrayList<Sprite> sprites;
  private int width;
  private int height;

  public World(int w, int h)
  {
    width = w;
    height = h;
    sprites = new ArrayList<Sprite>();

   //add sprites here?
  }

  public void stepAll()
  {
    numSteps++;
    //System.out.println(numSteps);
    if(graphUpdate) {
      //System.out.println("is here: " + numSteps);
      sprites.clear();
      try {
       // System.out.println("tried again: " + numSteps);
        sprites.add(new Sprite(10, 10, 500, 500, visual));

        graphUpdate = false;
        wait = false;
        return;
      }
      catch (RuntimeException e)
      {
        return;
      }
    }
    else if(result!=null && !result.equals("NULL") && !wait && updated)
    {
      try {

       // System.out.println("result: " + result);
        //System.out.println("getData: " + getData());
        smiles = solveString(getData());
        IChemObjectBuilder bldr = SilentChemObjectBuilder.getInstance();
        SmilesParser smipar = new SmilesParser(bldr);
        IAtomContainer mol = smipar.parseSmiles(smiles);
        mol.setProperty(CDKConstants.TITLE, result);
        DepictionGenerator dptgen = new DepictionGenerator();
        dptgen.withSize(200, 250).withMolTitle().withTitleColor(Color.DARK_GRAY);
        visual = dptgen.depict(mol).toImg();
        //File outputfile = new File("C:\\Users\\alexa\\IdeaProjects\\CDKChemFinalLab\\src\\main\\resources\\" + result + ".png");
        //ImageIO.write(visual, "png", outputfile);
        //imageName= result + ".png";
        wait=true;
        updated=false;
        //System.out.println("is in try: " + numSteps);
        return;
        //System.out.println("smiles: " + smiles);
      }
      catch (IOException | CDKException e){
        throw new RuntimeException("Given compound does not exist, you entered: " + result);
      }


      //double theLeft, double theTop, int theWidth, int theHeight, String image

    }

  }

  public String getData() throws IOException {
    // formatting target is awlays cpatial first leet of words
    URL url = new URL("https://en.wikipedia.org/wiki/" + result);
    URLConnection urlConn = url.openConnection();
    InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
    BufferedReader buff = new BufferedReader(inStream);
    String line = buff.readLine();
    String b = "didn't work";
    while (line != null) {
      if (line.contains("SMILES")) {
        b = buff.readLine();//it is always the next line that the SMILES representation is on
        break;
      }
      line = buff.readLine();
    }
    inStream.close();
    buff.close();
    // System.out.println(result);
    return b;
    // System.out.println("work");

  }

  public static String solveString(String result) {
    String value = "didn't work";
    String last = "no work";
    if (result.contains("<ul class=\"mw-collapsible-content\" style=\"font-size: 105%; margin-top: 0; margin-bottom: 0; line-height: inherit; text-align: left; list-style: none none; margin-left: 0; word-break:break-all;\"><li style=\"line-height: inherit; margin: 0\"><div style=\"border-top:1px solid #ccc; padding:0.2em 0 0.2em 1.6em; word-wrap:break-word; text-indent:-1.5em; text-align:left; font-size:97%; line-height:120%;\">")) {
      value = result.substring(399);
      //result.indexOf("text-indent:-1.5em; text-align:left; font-size:97%; line-height:120%;\">")
      last = value;
      for (int i = 0; i<value.length(); i++) {
        if ((Character.toString(value.charAt(i))).equals("<")) {
          last = value.substring(0, i);
          break;
        }
      }
      //System.out.println("last" + last);

    }
    // do it here:
    //System.out.println(last);

    return last;
  }


  public int getWidth()
  {
    return width;
  }

  public String getResult(){
    return result;
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
    if(225<=x && x<=437 && y<=555 && y>=530)
    {
      createWindow();
    }

  }

  public void keyPressed(int key)
  {

  }

  public void keyReleased(int key)
  {

  }

  public String getTitle()
  {
    return "Chemistry Visualization";
  }

  public void paintComponent(Graphics g)
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

  }
  public String result;
  public void createWindow() {
    JFrame frame = new JFrame("Enter an element");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createUI(frame);
    frame.setSize(560, 200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    //System.out.println(elementName);
    //System.out.println("ret:" + result);
  }

  public void createUI(final JFrame frame){

    JPanel panel = new JPanel();
    LayoutManager layout = new FlowLayout();
    panel.setLayout(layout);
    JButton button = new JButton("Click to enter an element");
    final JLabel label = new JLabel();
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String theVal= (String)JOptionPane.showInputDialog(
                frame,
                "Type your element",
                "Enter an element",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null
        );
        if(result==null || !result.equals(theVal))
        {
          updated=true;
          if(theVal.contains(" "))
          {
            theVal=theVal.replaceAll(" ", "_");
            theVal=theVal.toLowerCase();
          }
          result=theVal;
        }
        else
        {
          updated=false;
          //result=theVal;
        }
        if(result != null && result.length() > 0){
          label.setText("You entered:" + result);
          //System.out.println("result the: " + result);
          //System.out.println("theVal " + theVal);
        }else {
          label.setText("None enetered:");
          theVal ="NULL";
          //System.out.println("nulltheVal " + theVal);
        }

      }
    });
    //System.out.println("result: " + result);
    JButton graph = new JButton("Click to graph element");
    graph.addActionListener(new ActionListener() {
      @Override

      public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Click to graph element")){
          graphUpdate=true;
        }

      }
    });
    //System.out.println("result: " + result);
    panel.add(button);
    panel.add(graph);
    panel.add(label);
    frame.getContentPane().add(panel, BorderLayout.CENTER);

  }


}