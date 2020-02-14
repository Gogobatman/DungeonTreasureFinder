package dtf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * @author Mateusz Figoń
 * @version 2.0
 */
public class Main extends JFrame implements KeyListener {
    private int width, height;
    private Graph graph = new Graph();
    private int score =0;
    private Random rand = new Random();
    private JPanel counterPanel = new JPanel();
    private JTextArea jTextField = new JTextArea(" Score: " +Integer.toString(score)+" ");
    private int posX[]={-100,-100,-100,-100,-100};
    private int posY[]={-100,-100,-100,-100,-100};
    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code==65 && posX[0]>=1){
            posX[0]--;
        }else if(code==68 && posX[0]<53){
            posX[0]++;
        }else if(code==87 && posY[0]>=1){
            posY[0]--;
        }else if(code==83 && posY[0]<48){
            posY[0]++;
        }
        if(samePosition()){
            generatePosition();
        }
    }
    private void updateGraphVariables(){
        for(int i=0; i<5; i++){
            graph.posX2[i]=posX[i];
            graph.posY2[i]=posY[i];
        }
    }
    @Override
    public void keyTyped(KeyEvent e){ }
    @Override
    public void keyReleased(KeyEvent e){ }
    private void generatePosition(){
        int j=1;
        if(score >=3){
            j=2;
        }
        if(score >=6){
            j=3;
        }
        if(score >=9){
            j=4;
        }
        for(int i=0; i<=j; i++){
            posX[i]=rand.nextInt(40);
            posY[i]=rand.nextInt(40);
        }
    }
    private boolean samePosition(){
        if(posX[0]==posX[1] && posY[0]==posY[1]){
            score++;
            jTextField.setText(" Score: " +Integer.toString(score)+" ");
            return true;
        }
        if((posX[0]==posX[2] && posY[0]==posY[2])||(posX[0]==posX[3] && posY[0]==posY[3])||(posX[0]==posX[4] && posY[0]==posY[4])){
            score--;
            if(score <3){
                posX[2]=-100;
                posY[2]=-100;
            }
            if(score <6){
                posX[3]=-100;
                posY[3]=-100;
            }
            if(score <9){
                posX[4]=-100;
                posY[4]=-100;
            }
            jTextField.setText(" Score: " +Integer.toString(score)+" ");
            generatePosition();
            return true;
        }
        else{
            return false;
        }
    }
    private void frameSettings(){
        setLocation(0,0);
        setSize(600,600);
        setLayout(null);
        setFocusable(true);
        setResizable(false);
        add(counterPanel);
        add(graph);
        addKeyListener(this);
        setVisible(true);
    }
    private void otherPanelsSettings(){
        counterPanel.setSize(600,25);
        counterPanel.setBackground(Color.gray);
        counterPanel.setLocation(0,0);
        jTextField.setEditable(false);
        counterPanel.add(jTextField);
        graph.setLocation(0,25);
        graph.setSize(600,570);
    }
    private Main(String name, int width, int height){
        super(name);
        this.width=width;
        this.height=height;
        generatePosition();
        otherPanelsSettings();
        frameSettings();
    }
    public static void main(String[] args) {
        Main m = new Main("Treasure finder!",600,600);
        while(true)
        {
            try{
                TimeUnit.MILLISECONDS.sleep(100);
                int j=4;
                if(m.score>=3){
                    j=2;
                }
                if(m.score>=6){
                    j=3;
                }
                if(m.score>=9){
                    j=4;
                }
                for(int i=2; i<=j; i++){
                    int number = m.rand.nextInt(4);
                    if(number==0 && m.posX[i]>=1){
                        m.posX[i]--;
                    }else if(number==1 && m.posX[i]<53){
                        m.posX[i]++;
                    }else if(number==2 && m.posY[i]>=1){
                        m.posY[i]--;
                    }else if(number==3 && m.posY[i]<48){
                        m.posY[i]++;
                    }
                }
            }
            catch(Exception e){ }
            m.samePosition();
            m.updateGraphVariables();
            m.graph.repaint();
        }
    }
}

