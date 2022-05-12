import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;

public class Sprite
{
  private double left;  //the x-coordinate of the left edge of the sprite
  private double top;   //the y-coordinate of the top edge of the sprite
  private int width;
  private int height;
  private String image;
  
  public Sprite(double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    left = theLeft;
    top = theTop;
    width = theWidth;
    height = theHeight;
    setImage(theImage);
  }
  
  public double getLeft()
  {
    return left;
  }
  
  public void setLeft(double l)
  {
    left = l;
  }
  
  public double getTop()
  {
    return top;
  }
  
  public void setTop(double t)
  {
    top = t;
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public void setWidth(int w)
  {
    width = w;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public void setHeight(int h)
  {
    height = h;
  }
  
  public String getImage()
  {
    return image;
  }
  
  public void setImage(String i)
  {
    image = i;
  }
  
  public boolean overlap(Sprite other)
  {
    if(this.getLeft() < other.getLeft() + other.getWidth())
    {
      if(this.getLeft() + this.getWidth() > other.getLeft())
      {
        if(this.getTop() < other.getTop() + other.getHeight())
        {
          if(this.getTop() + this.getHeight() > other.getTop())
          {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public boolean isClicked(int x, int y)
  {
    if(x > getLeft() && x < getLeft() + getWidth() && y < getTop() + getHeight() && y > getTop())
      return true;
    return false;
  }
  
  public void step(World world)
  {
    //do NOT insert any code here
  }
}
