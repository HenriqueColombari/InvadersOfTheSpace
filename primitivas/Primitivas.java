package primitivas;

import util.JPanelGraphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Primitivas extends JPanelGraphics {
    
    public Primitivas(Dimension d) {
        super(d);
    }

    public void DrawImageEnimies(ImageIcon iconEnimies, ArrayList<ArrayList<int[]>> enimiesList) {
        try{
            Graphics2D g = (Graphics2D) image.getGraphics();
            //super.paintComponent(g);
            for(int i = 0; i < enimiesList.size(); i++){
                for(int k = 0; k < enimiesList.get(i).size(); k++){
                    iconEnimies.paintIcon(this, g, enimiesList.get(i).get(k)[0]-20, enimiesList.get(i).get(k)[1]-40);
                }
            }
            
            repaint();
        }
        catch(Exception e){
            
        }
        
    }
    
    public void drawExplosion(ImageIcon iconEnimies,int x, int y) {
        try{
            Graphics2D g = (Graphics2D) image.getGraphics();
            //super.paintComponent(g);
            iconEnimies.paintIcon(this, g, x-20, y-40);
            //repaint();
        }
        catch(Exception e){
            
        }
        
    }
    
    public void DrawImageBarricade(ImageIcon iconBarricade, ArrayList<int[]> barricadeList){
        try{
            Graphics2D g = (Graphics2D) image.getGraphics();
            //super.paintComponent(g);
            for(int i = 0; i < barricadeList.size(); i++){
                iconBarricade.paintIcon(this, g, barricadeList.get(i)[0]-60, barricadeList.get(i)[1]-20);
            }
            
            //repaint();
        }
        catch(Exception e){
            
        }
    }
    
    public void DrawImageTank(ImageIcon iconTank, int x, int y) {
        try{
            Graphics2D g = (Graphics2D) image.getGraphics();
            //super.paintComponent(g);
            iconTank.paintIcon(this, g, x-17, y-20);
            //repaint();
        }
        catch(Exception e){
            
        }
        
    }
    
    public void desenharSegmentoReta(int x, int y, int x2, int y2, Color rgb) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(rgb);
        g.drawLine(x, y, x2 , y2);
        repaint();
    }
    
    public void limpar() {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, image.getWidth()-1, image.getHeight()-1);
        repaint();
    }    
}
