import javax.swing.*;

public class Main extends JFrame {

    public Main (){
            setTitle("Snake");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(350, 375);
            setLocation(650, 300);
            add(new GameField());
            setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
