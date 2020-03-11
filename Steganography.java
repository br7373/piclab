import java.awt.Color;

public class Steganography
{
    public static void clearLow(Pixel p){
        Color oldColor = p.getColor();
        
        int red = (oldColor.getRed()/4)*4;
        int green = (oldColor.getGreen()/4)*4;
        int blue = (oldColor.getBlue()/4)*4;
        
        p.setColor(new Color(red, green, blue));
        
    }
    public static Picture testClearLow(Picture pic){
        Picture p = new Picture(pic);
        Pixel[][] pixels = p.getPixels2D();
        for(int r=0;r<pixels.length-1;r++){
            for(int c=0;c<pixels[0].length;c++){
                clearLow(pixels[r][c]);
            }
        }
        return p;
    }
    public static void setLow(Pixel p, Color c){
        clearLow(p);
        Color oldColor = p.getColor();
        int red = c.getRed()/64;
        int green = c.getRed()/64;
        int blue = c.getRed()/64;
        p.setColor(new Color(oldColor.getRed()+red, oldColor.getGreen()+green, oldColor.getBlue()+blue));
    }
    public static Picture testSetLow(Picture pic, Color col){
        Picture p = new Picture(pic);
        Pixel[][] pixels = p.getPixels2D();
        for(int r=0;r<pixels.length;r++){
            for(int c=0;c<pixels[0].length;c++){
                setLow(pixels[r][c], col);
            }
        }
        return p;
    }
    public static Picture revealPicture(Picture hidden){
        Picture copy = new Picture(hidden);
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] source = hidden.getPixels2D();
        for(int r=0;r<pixels.length;r++){
            for(int c=0;c<pixels[0].length;c++){
                Color col = source[r][c].getColor();
                Pixel p = pixels[r][c];
                p.setRed(col.getRed()%4*64);
                p.setGreen(col.getGreen()%4*64);
                p.setBlue(col.getBlue()%4*64);
            }
        }
        return copy;
    }
    public static void main(){
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        Picture copy = testClearLow(beach);
        copy.explore();
        
        Picture beach2 = new Picture("beach.jpg");
        beach2.explore();
        Picture copy2 = testSetLow(beach2, Color.PINK);
        copy2.explore();
        
        Picture copy3 = revealPicture(copy2);
        copy3.explore();
    }
}
