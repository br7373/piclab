import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    /** Method to set red and green to 0 */
    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }

    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(225-pixelObj.getBlue());
                pixelObj.setBlue(225-pixelObj.getRed());
                pixelObj.setBlue(225-pixelObj.getGreen());
            }
        }
    }

    public void noBavgRG(){
        Pixel[][] pixels = this.getPixels2D();
        for(int r=0;r<pixels.length;r++){
            for(int c=0; c<pixels[0].length;c++){
                pixels[r][c].setBlue(0);
                pixels[r][c].setRed(pixels[r][c].getRed()+pixels[r][c].getGreen()/2);
            }
        }
    }

    public void fixWater(){
        Pixel[][] pixels = this.getPixels2D();
        
        for(int r=0;r<pixels.length;r++){
            for(int c=0; c<pixels[0].length;c++){
                int red = Math.abs(pixels[r][c].getRed()-18);
                int blue = Math.abs(pixels[r][c].getBlue()-173);
                int green = Math.abs(pixels[r][c].getGreen()-162);
                int dif = red+green+blue;
                if(dif>20){
                    pixels[r][c].setRed(0);
                    pixels[r][c].setBlue(0);
                    pixels[r][c].setGreen(0);
                }
            }
        }
    }

    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed((pixelObj.getBlue()+pixelObj.getGreen()+pixelObj.getRed())/3);
                pixelObj.setBlue((pixelObj.getBlue()+pixelObj.getGreen()+pixelObj.getRed())/3);
                pixelObj.setGreen((pixelObj.getBlue()+pixelObj.getGreen()+pixelObj.getRed())/3);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();

        int x = pixels[0].length/2;

        for (int r = 0; r < pixels.length; r++)
        {
            for (int c = x; c < pixels[0].length; c++)
            {
                pixels[r][c].setColor(pixels[r][x-(c-x)].getColor());
            }
        } 
    }

    public void mirrorHorizontal(){
        Pixel[][] pixels = this.getPixels2D();

        int x = pixels.length/2;

        for (int r = x; r < pixels.length; r++)
        {
            for (int c = 0; c < pixels[0].length; c++)
            {
                pixels[r][c].setColor(pixels[x-(r-x)][c].getColor());
            }
        }
    }

    public void mirrorDiagonal(){
        Pixel[][] pixels = this.getPixels2D();

        int x = pixels.length;
        int y=pixels[0].length;
        if(y>x)
            for (int r = 0; r < pixels.length; r++)
            {
                for (int c = 0; c < pixels.length; c++)
                {
                    pixels[r][c].setColor(pixels[c][r].getColor());
                }
            } else{
            for (int r = 0; r < pixels[0].length; r++)
            {
                for (int c = 0; c < pixels[0].length; c++)
                {
                    pixels[r][c].setColor(pixels[c][r].getColor());
                }
            }
        }
    }

    public void mirrorVerticalOther(){
        Pixel[][] pixels = this.getPixels2D();

        int x = pixels[0].length/2;

        for (int r = 0; r < pixels.length; r++)
        {
            for (int c = 0; c < x; c++)
            {
                pixels[r][c].setColor(pixels[r][x-(c-x)].getColor());
            }
        } 
    }

    public void mirrorHorizontalOther(){
        Pixel[][] pixels = this.getPixels2D();

        int x = pixels.length/2;

        for (int r = x; r > 0; r--)
        {
            for (int c = 0; c < pixels[0].length; c++)
            {
                pixels[r][c].setColor(pixels[x-(r-x)-1][c].getColor());
            }
        } 
    }

    public void mirrorDiagonalOther(){
        Pixel[][] pixels = this.getPixels2D();

        int x = pixels.length;
        int y=pixels[0].length;
        if(y>x)
            for (int r = 0; r < pixels.length; r++)
            {
                for (int c = 0; c < pixels.length; c++)
                {
                    pixels[r][c].setColor(pixels[c][r].getColor());
                }
            } else{
            for (int r = 0; r < pixels[0].length; r++)
            {
                for (int c = 0; c < pixels[0].length; c++)
                {
                    pixels[r][c].setColor(pixels[c][r].getColor());
                }
            }
        }
    }

    public void mirrorArms(){
        Pixel[][] pixels = this.getPixels2D();

        int x = 190;

        for (int r = 190; r < 220; r++)
        {
            for (int c = 100; c < 170; c++)
            {
                pixels[r][c].setColor(pixels[x-(r-x)][c].getColor());
            }
        } 
        for (int r = 190; r < 210; r++)
        {
            for (int c = 240; c < 290; c++)
            {
                pixels[r][c].setColor(pixels[x-(r-x)][c].getColor());
            }
        }
    }

    public void mirrorGull(){
        Pixel[][] pixels = this.getPixels2D();

        int x = 342;

        for (int r = 235; r < 330; r++)
        {
            for (int c = 335; c < 450; c++)
            {
                pixels[r][c].setColor(pixels[r][x-(c-x)].getColor());
            }
        } 
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }
    
    public void createCollage2(){
        Picture cata = new Picture("cat1.jpg");
        Picture catb = new Picture("cat2.jpg");
        this.copy(cata,0,50);
        this.copy(catb,100,0);
        this.copy(cata,200,0);
        this.mirrorHorizontal();
        Picture catNoBlue = new Picture(catb);
        this.copy(catNoBlue,300,0);
        this.copy(catb,500,0);
        this.copy(cata,400,50);
        
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Picture copy = new Picture(this);
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
            
        }
        
        Pixel top = null;
        Pixel bottom = null;
        Pixel[][] pixels2 = copy.getPixels2D();
        Color bottomColor = null;
        //x-(c-x)s
        int x = pixels[0].length/2;
        for (int row = 0; row < pixels2.length-1; row++)
        {
            for (int col = 0; col < pixels2[0].length; col++)
            {
                top = pixels2[row+1][col];
                bottom = pixels2[row][col];
                bottomColor = bottom.getColor();
                if (top.colorDistance(bottomColor) > edgeDist)
                    pixels[row][col].setColor(Color.BLACK);
                    
            }
        }
    }
    
    public void myMethod(){
        Pixel[][] pixels = this.getPixels2D();

        for(int r=0;r<pixels.length;r++){
            for(int c=0;c<pixels[0].length;c++){
                // int red = Math.abs(pixels[r][c].getRed()-196);
                // int blue = Math.abs(pixels[r][c].getBlue()-209);
                // int green = Math.abs(pixels[r][c].getGreen()-203);
                // int dif = red+green+blue;
                
                // if(dif>250){
                    // pixels[r][c].setRed(200);
                    // pixels[r][c].setGreen(200);
                    // pixels[r][c].setBlue(200);
                // }
                
                
            }
        }
    }


    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
