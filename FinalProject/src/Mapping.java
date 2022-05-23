
// رسم البلوك داخل الفريم


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


class mapping {

    public int map[][];
    public int brickWidth;
    public int brickHeight;
    // constructor
    public mapping (){
        map = new int [][]{

                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
                {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
                {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
                {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
                {0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0}};

        brickWidth = 540/map[0].length;//length of column
        brickHeight = 300/ map.length; //length of row
    }
    public void draw(Graphics2D g) {
        for(int i=0;i<map.length;i++){
            for (int j=0;j<map[0].length;j++) {
                if( map [i][j] > 0){
                    if (i%2==0)
                        g.setColor(new Color(236, 190, 180));
                    else
                        g.setColor(new Color(203, 153, 126));
                    g.fillOval(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight); // (x,y,Width,Height)

                    // create a border around bricks
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.white);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }//end if
            } //end inner for
        }//end for
    } //end "draw(Graphics2D g)" method

    public void setBrickValue(int value,int row, int col){
        map[row][col] = value;

    } // end setBrickValue method
}// end class mapping