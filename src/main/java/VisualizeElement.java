import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.smiles.SmilesParser;
import javax.swing.*;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.lang.StringBuilder;

public class VisualizeElement extends Sprite
{
    private double left;  //the x-coordinate of the left edge of the sprite
    private double top;   //the y-coordinate of the top edge of the sprite
    private int width;
    private int height;
    private String elementToDisplay;
    private String image;

    public VisualizeElement(double theLeft, double theTop, int theWidth, int theHeight, String image)
    {
        super(theLeft, theTop, theWidth, theHeight,image);
        left = theLeft;
        top = theTop;
        width = theWidth;
        height = theHeight;
        elementToDisplay=image;
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

    public void setImage()
    {
        image="";

    }


    public void setElementToDisplay(String i)
    {
        elementToDisplay = i;
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

    public String getType()
    {
        return "VisualizeElement";
    }

    public void step(World world)
    {
        this.setImage();
        //do NOT insert any code here
    }

    public static String getData(String quote, String target) throws IOException {
        // formatting target is awlays cpatial first leet of words
        URL url = new URL("https://finviz.com/quote.ashx?t=" + quote);
        URLConnection urlConn = url.openConnection();
        InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
        BufferedReader buff = new BufferedReader(inStream);
        String line = buff.readLine();
        String result = "didn't work";
        while (line != null) {
            if (line.contains("SMILES")) {
                result = line;
                break;
            }
            line = buff.readLine();
        }
        inStream.close();
        buff.close();
        // System.out.println(result);
        return result;
        // System.out.println("work");

    }

    public static String solveString(String result) {
        String value = "didn't work";
        String last = "no work";
        if (result.contains("</small></b></td>")) {
            value = result.substring(0, result.indexOf("</small></b></td>"));
            last = value;
            for (int i = value.length() - 1; i >= 0; i--) {
                if ((Character.toString(value.charAt(i))).equals(">")) {
                    last = value.substring(i + 1, value.length());
                    break;
                }
            }
            // System.out.println(last);

        }
        // do it here:
        // System.out.println(last);

         return null;
    }
}
