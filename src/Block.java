import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Block {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;                   //the width of the block
    public int height;                   //the height of the block
    public boolean isAlive = false;            //a boolean to denote if the hero is alive or dead.
    public Rectangle rect;                  //rectangles for intersections among the blocks
    public Rectangle upSide;                    //another rectangle for intersections (on the upper side)
    public Rectangle rightSide;                     //another rectangle for intersections (on the right side)
    public Rectangle leftSide;                     //another rectangle for intersections (on the left side)
    public Rectangle bottomSide;                    //another rectangle for intersections (on the bottom side)
    public int score;                  //for keeping score in the game
    public boolean left, right, up, down;                   //booleans for directions
    public int color;                       //the color index for each block

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters. This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Block(int pXpos, int pYpos, int pColor) {
        xpos = pXpos;
        ypos = pYpos;
        color = pColor;
        dx = 140;
        dy = 35;
        score = 0;
        width = 140;
        height = 140;
        isAlive = false;
        rect = new Rectangle(xpos, ypos, width, height);
        upSide = new Rectangle(xpos, ypos, width, height);
        rightSide = new Rectangle(xpos, ypos, width, height);
        leftSide = new Rectangle(xpos, ypos, width, height);
        bottomSide = new Rectangle(xpos, ypos, width, height);

    } // constructor


    public void printInfo() {
        System.out.println("BLOCK INFORMATION");
        System.out.println(xpos + " ,  " + ypos);
        System.out.print(score + "");

    }


    //The move method. Everytime this is run (or "called") the block's x position and y position change by dx and dy
    //The directions changes as well in this method when the block hits the sides of the screen.
    public void move() {

        if (isAlive = true) {
            //blocks keep moving down
            ypos = ypos + 4;
        }

        //left boundary
        if (xpos <= 0) {
            xpos = 0;
        }
        if (xpos + width <= 0) {
            xpos = 0 - width;
        }

        //right boundary
        if (xpos + width >= 700) {
            xpos = 700 - width;
        }

        //rectangles for each side
        rect = new Rectangle(xpos, ypos, width, height);
        upSide = new Rectangle(xpos, ypos, width, 1);
        rightSide = new Rectangle(xpos + 140, ypos, 2, height);
        leftSide = new Rectangle(xpos, ypos, 2, height);
        bottomSide = new Rectangle(xpos, ypos + 140, width, 1);

    }

}







