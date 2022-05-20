import java.awt.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class World
{
  private boolean paused = false;
  private boolean started = false;
  private String elementName = "NULL";

  public static void main(String[] args)
  {
    run();
  }

  public static void run()
  {
    Display display = new Display(700, 600);
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
    for (int i = 0; i < sprites.size(); i++)
    {
      Sprite s = sprites.get(i);
      System.out.println(elementName);
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

  public void createWindow() {
    JFrame frame = new JFrame("Enter an element");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ret=createUI(frame);
    frame.setSize(560, 200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    System.out.println(elementName);
    System.out.println("ret:" + ret);
  }
  public String ret="";
  public String  createUI(final JFrame frame){

    JPanel panel = new JPanel();
    LayoutManager layout = new FlowLayout();
    panel.setLayout(layout);
    JButton button = new JButton("Click to enter an element");
    final JLabel label = new JLabel();
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String theVal="";
        String result = (String)JOptionPane.showInputDialog(
                frame,
                "Type your element",
                "Enter an element",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null
        );
        if(result != null && result.length() > 0){
          label.setText("You entered:" + result);
          theVal =result;
          System.out.println("theVal " + theVal);

        }else {
          label.setText("None enetered:");
          theVal ="NULL";
          System.out.println("nulltheVal " + theVal);
        }

      }
    });
    System.out.println("ret: " + ret);
    panel.add(button);
    panel.add(label);
    frame.getContentPane().add(panel, BorderLayout.CENTER);
    return ret;
  }


}