import javax.swing.*;


public class MainWindow extends JFrame {

    private MainWindow(){
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GameField gf = new GameField();

        setSize(800, 600);
        setLocationRelativeTo(null);
        add(new GameField());
        setVisible(true);

    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();

    }
}
