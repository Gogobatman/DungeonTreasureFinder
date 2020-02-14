package dtf;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
class Graph extends JPanel {
    String src[]={"C:\\Users\\dellLat01\\Downloads\\tlo.png","C:\\Users\\dellLat01\\Downloads\\portret1.png",
            "C:\\Users\\dellLat01\\Downloads\\nagroda.png","C:\\Users\\dellLat01\\Downloads\\portret2.png",
            "C:\\Users\\dellLat01\\Downloads\\portret3.png", "C:\\Users\\dellLat01\\Downloads\\portret4.png"};
    int posX2[]=new int[10];
    int posY2[]=new int[10];
    public void paint(Graphics g){
        BufferedImage img[] = new BufferedImage[10];
        try {
            for(int i=0;i<6;i++){
                img[i] = ImageIO.read(new File(src[i]));
            }
        } catch (Exception e) {
        }
        g.drawImage(img[0],0,0,600,600,null);
        for(int i=1; i<6; i++){
            g.drawImage(img[i],posX2[i-1]*10,30+posY2[i-1]*10,50,50,null);
        }
    }
}