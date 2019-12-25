/*
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FieldKeyListener extends KeyAdapter {


    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT && !right){
            left = true;
            right = false;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_RIGHT && !left){
            left = false;
            right = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_UP && !down){
            left = false;
            right = false;
            up = true;
            down = false;
        }
        if(key == KeyEvent.VK_DOWN && !up){
            left = false;
            right = false;
            up = false;
            down = true;
        }
    }
}
 */