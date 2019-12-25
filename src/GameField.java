import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;


public class GameField extends JPanel implements ActionListener{

    private boolean inGame;
    private int appleCounter   = 0;                 // Счетчик яблок
    private int hardLevel      = 1;
    private int speed          = 10;
    private final int DOT_SIZE = 16;                // размер клетки
    private final int WIDTH    = 30;                // кол-во клеток по ширине
    private final int HEIGHT   = 20;                // кол-во клеток по высоте
    private int delay          = 1000/speed;        // задержка в мс
    private final int SIZE_X   = DOT_SIZE*WIDTH;    // длина игрового поля в пикселях по X
    private final int SIZE_Y   = DOT_SIZE*HEIGHT;   // длина игрового поля в пикселях по Y
    private final int ALL_DOTS = WIDTH*HEIGHT;      // всего клеток
    private int dots;                               // Начальная длина змейки
    private int appleX;                             // координаты еды по X
    private int appleY;                             // координаты еды по Y
    private int[] x = new int[ALL_DOTS];            // Максимальный размер змейки
    private int[] y = new int[ALL_DOTS];

    private Timer timer = new Timer(delay,this);;

    private InitMove initMove = new InitMove(false,true,false,false);


    GameField(){
        setBackground(Color.black);
        initGame();

        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    private void initGame(){
        inGame = true;

        initMove.setRight(true);
        initMove.setLeft(false);
        initMove.setUp(false);
        initMove.setDown(false);
        appleCounter = 0;

        dots = 3;

        //Начальное положение змейки
        for (int i = 0; i < dots; i++) {
            x[i] = DOT_SIZE*2 - i*DOT_SIZE;
            y[i] = DOT_SIZE*5;
        }
        timer.start();
        createApple();

    }

    private void createApple(){
        appleX = new Random().nextInt(WIDTH)*DOT_SIZE;
        appleY = new Random().nextInt(HEIGHT)*DOT_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        // отрисовка вертикальной линии
        for (int x = 0; x < SIZE_X+DOT_SIZE; x+=DOT_SIZE) {
            g.drawLine(x,0, x ,SIZE_Y);
        }

        // отрисовка горизонтальной линии
        for (int y = 0; y < SIZE_Y+DOT_SIZE; y+=DOT_SIZE) {
            g.drawLine(0, y, SIZE_X, y);
        }


        if(inGame){
            Graphics2D g2 = (Graphics2D) g;

            //Рисуем яблоко
            g2.setPaint(Color.RED);
            g2.fill(new Rectangle2D.Double(appleX, appleY, DOT_SIZE, DOT_SIZE));

            // Рисуем змейку
            for (int i = 0; i < dots; i++) {
                g2.setPaint(Color.WHITE);
                g2.fill(new Rectangle2D.Float(x[i], y[i], DOT_SIZE, DOT_SIZE));
            }
        } else{
            String str = "Game Over";
            Font f = new Font("Arial",Font.BOLD, 14);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str,125,SIZE_Y/2);
        }
    }

    private void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(initMove.isLeft()){
            x[0] -= DOT_SIZE;
        }
        if(initMove.isRight()){
            x[0] += DOT_SIZE;
        } if(initMove.isUp()){
            y[0] -= DOT_SIZE;
        } if(initMove.isDown()){
            y[0] += DOT_SIZE;
        }
    }

    private void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            appleCounter++;
            createApple();
        }
    }

    private void checkCollisions(){
        for (int i = dots; i >0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
                break;
            }
        }

        if(x[0]>=SIZE_X){
            inGame = false;
            System.out.println("Score: " + appleCounter);
        }
        if(x[0]<0){
            inGame = false;
            System.out.println("Score: " + appleCounter);
        }
        if(y[0]>=SIZE_Y){
            inGame = false;
            System.out.println("Score: " + appleCounter);
        }
        if(y[0]<0){
            inGame = false;
            System.out.println("Score: " + appleCounter);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            move();

        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !initMove.isRight()){
                initMove.setLeft(true);
                initMove.setUp(false);
                initMove.setDown(false);
            }
            if(key == KeyEvent.VK_RIGHT && !initMove.isLeft()){
                initMove.setRight(true);
                initMove.setUp(false);
                initMove.setDown(false);
            }

            if(key == KeyEvent.VK_UP && !initMove.isDown()){
                initMove.setUp(true);
                initMove.setLeft(false);
                initMove.setRight(false);

            }
            if(key == KeyEvent.VK_DOWN && !initMove.isUp()){
                initMove.setDown(true);
                initMove.setLeft(false);
                initMove.setRight(false);
            }

            if(!inGame && key == KeyEvent.VK_ENTER){
                initGame();
            }

        }
    }


}
