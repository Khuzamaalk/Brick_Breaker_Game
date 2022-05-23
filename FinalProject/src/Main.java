import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {


        JFrame obj = new JFrame(); //  object for JFrame class
        StartGame  StartGame  = new StartGame ();  // object for GamePlay class
        obj. setBounds(10,10,700,600);// the background for the frame
        obj.setTitle(" Brick Breaker Game ");// the title of the game
        obj.setResizable(false);// عشان ما اقرى اعرل القيساتا
        obj.setVisible(true); // ??
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to clse the frame
        obj.add(StartGame );

    }
}