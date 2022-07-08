import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;

    private Image apple;
    private Image dot;
    private Image startScreen;

    private int appleX;
    private int appleY;

    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];

    private int dots;
    private Timer timer;

    private boolean inGame = false;
    private boolean start = true;

    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;




    public void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }

    public void loadImage(){
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
        ImageIcon iis = new ImageIcon("startScreen.png");
        startScreen = iis.getImage();

    }
    public void checkApple(){
        if (appleX == x[0] &&appleY == y[0]) {
            dots++;
            createApple();
        }
    }
    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            y[i] = 3 * DOT_SIZE;
            x[i] = DOT_SIZE * 4 - i * DOT_SIZE;
        }
        timer = new Timer(100, this);
        timer.start();
        createApple();
    }
    public void checkCollision(){
        for (int i = dots; i > 3 ; i--) {
            if (x[0]==x[i]&&y[0]==y[i]){
                inGame = false;
                start = true;
            }
        }
        if (x[0]>SIZE){
            x[0] = 0;
        }
        if (x[0]<0){
            x[0] = SIZE;
        }
        if (y[0]>SIZE){
            y[0] = 0;
        }
        if (y[0]<0){
            y[0] = SIZE;
        }
    }
    public void move(){
        for (int i = dots; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if (left){
            x[0] -= DOT_SIZE;
        }
        if (right){
            x[0] += DOT_SIZE;
        }
        if (up){
            y[0] -= DOT_SIZE;
        }
        if (down){
            y[0]+= DOT_SIZE;
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.green);
        g.drawString("Score: "+String.valueOf(dots), 250, 30);
        if(start){
            g.drawImage(startScreen, 0, 0, this);
        }
        if (inGame){
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (start){
            dots = 3;
            for (int i = 0; i < dots; i++) {
                y[i] = 3 * DOT_SIZE;
                x[i] = DOT_SIZE * 4 - i * DOT_SIZE;
            }
        }
        if (inGame){
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }
    public GameField(){
        setBackground(Color.BLACK);
        loadImage();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }
    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_ESCAPE&&start){

            }
            if (key==KeyEvent.VK_ENTER&&start){
                start = false;
                inGame = true;
                right = true;
                left = false;
                up = false;
                down = false;
            }
            if (key==KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if (key==KeyEvent.VK_UP&&!down){
                up = true;
                left = false;
                right = false;
            }
            if (key==KeyEvent.VK_DOWN&&!up){
                down = true;
                left = false;
                right = false;
            }
            if (key==KeyEvent.VK_RIGHT&&!left){
                right = true;
                up = false;
                down = false;
            }
        }
    }
}
