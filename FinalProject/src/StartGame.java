/*run our game here (palne)*/



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


// KeyListener for arrow keys to moving the slaider يعني لتحريلك الاسهم
//ActionListener for moving the ball لتحريك الكوره
public class StartGame extends JPanel implements KeyListener , ActionListener  {

    private boolean play = false ; // the start is false so the game wont start until the user press enter
    private int score =0 ;

    private int totalBricks = 121;//total breaks for the ball to end the game

    private Timer timer; // Timer class for seting the time of ball that how fast moving
    private int delay = 5 ;  // سرعة تحريك الكرة

    // properties for the x-axis and y-axis of the slider and a ball
    private int playerX = 310; // 310 is starting position of slider

    private int ballposX =120;  // 120 is starting position of ball for x-axis
    private int ballposY =350;   // 350 is starting position of ball for y-axis
    private int ballXdir = -1;   // X direction of the ball
    private int ballYdir = -2 ;   //Y  direction of the ball

    private mapping map ; // object for MapGenerator class

    // constructor
    public StartGame(){
        map = new mapping(); //mapping(row,column)
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();

    } //end GamePlay constructor

    // draw different shapes like slider and balland the tiles
    public void paint(Graphics g){
        // background
        g.setColor(Color.white);  //background Color
        g.fillRect(1, 1, 692, 592);  // rectangle for the background, (position,position,size,size)

        // drawing map
        map.draw((Graphics2D)g);

        // borders
        g.setColor(Color.white);
        g.fillRect(0, 0, 3, 592); // rectangle for the border
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        // we do not add border at the down side because we want a ball to move down for end the game

        // scores
        g.setColor(Color.gray);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score, 10, 30);  //score location

        // paddle
        g.setColor(new Color(197, 201, 164));
        g.fillRect(playerX , 530, 100, 12);

        //  ball
        g.setColor(Color.gray);
        g.fillOval(ballposX , ballposY, 20, 20); //y, width, height

        //if we finish our game
        if (totalBricks <= 0){
            play =false;
            ballXdir = 0 ;
            ballYdir = 0 ;
            g.setColor(new Color(126, 176, 155));
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Congrats! You Won :D ",200, 300);
            g.setFont(new Font("serif", Font.BOLD, 20 ));
            g.drawString("Total Scores : "+score,270, 400);
            g.drawString("Press Enter to play again", 245, 450);

        }

        if (ballposY > 570){
            play =false;
            ballXdir = 0 ;
            ballYdir = 0 ;

            g.setColor(new Color(126, 176, 155));
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Hard Luck :( You Lose",200, 300);
            g.setFont(new Font("serif", Font.BOLD, 20 ));
            g.drawString("Total Scores : "+score,270, 400);
            g.drawString("Press Enter to play again", 245, 450);
        }
        g.dispose(); // stop function

    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        timer.start();  // started the timer
        if(play){
            if(new Rectangle(ballposX , ballposY, 20, 20).intersects(new Rectangle(playerX , 530, 100, 8))){ //y, width, height
                ballYdir = - ballYdir ; // to go back to the same direction
            }// end inner 1 if  the ball will go back when it tuch the slider

            // when the ball hit a brick its value will be 0 and it will disaper
            // A is label
            A: for(int i=0;i<map.map.length;i++){
                for (int j=0;j<map.map[0].length;j++){
                    if (map.map[i][j] > 0){
                        int brickX = j * map.brickWidth+80; // لقياس مكان البريكس
                        int brickY = i * map.brickHeight+50;
                        int brickWidth =map.brickWidth;
                        int brickHeight =map.brickHeight;

                        Rectangle rect =new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect =new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickRect = rect;

                        if (ballRect.intersects(brickRect)){ //check if there is intersection
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score ++ ;
                            if (ballposX +19 <=brickRect.x || ballposX +1 >=brickRect.x+brickRect.width){
                                ballXdir = -ballXdir;
                            }// end iner if
                            else{
                                ballYdir = -ballYdir;
                            }//end else
                            break A;

                        }//end outer if


                    }//end last outer if
                }//end iner for
            }//end outer for

            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX <0){
                ballXdir = -ballXdir ;
            } // end iner 2 if  , if its touch the left border
            if(ballposY <0){
                ballYdir = -ballYdir ;
            }// end iner 3 if  , if its touch the upper border
            if(ballposX > 670){
                ballXdir = -ballXdir ;
            }// end iner 4 if  , if its touch the right border
        } // end outer if
        repaint();  // recall " paint(Graphics g)" method
    }


    @Override
    public void keyTyped(KeyEvent ke) { }
    @Override
    public void keyReleased(KeyEvent ke) {  }


    // to detect the arrow keyskey from the key boards

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()== KeyEvent.VK_RIGHT){  // detect the RIGHT key
            if (playerX >= 600){
                playerX = 600;
            }// end iner if
            else {
                moveRight();
            } // end else
        }// end outer if

        if(ke.getKeyCode()== KeyEvent.VK_LEFT){   // detect the LEFT key
            if (playerX <10){
                playerX = 10;
            }// end iner if
            else {
                moveLeft();
            } // end else
        }// end outer if
        //detect Enter event
        if(ke.getKeyCode()== KeyEvent.VK_ENTER){
            // if Press Enter it will be Restart
            if (!play){
                play = true;
                score =0 ;
                playerX = 310; // 310 is starting position of slider

                ballposX =120;  // 120 is starting position of ball for x-axis
                ballposY =350;   // 350 is starting position of ball for y-axis
                ballXdir = -1;   // X direction of the ball
                ballYdir = -2 ;   //Y  direction of the ball
                totalBricks = 121 ;
                map = new mapping(); //mapping(row,column)

                repaint();
            }//end inner if
        }//end outer if
    }//end keyPressed method
    public void moveRight(){
        play = true;
        playerX += 50;
    }

    public void moveLeft(){
        play = true;
        playerX -= 50;
    }



}  // end Start class