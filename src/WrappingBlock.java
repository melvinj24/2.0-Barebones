import java.awt.*;

public class WrappingBlock {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle rect;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public WrappingBlock(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 5;
        dy = 5;
        width = 30;
        height = 30;
        isAlive = true;
        rect = new Rectangle(xpos, ypos, width, height);

    } // constructor


    public void printInfo() {
        System.out.println("WRAPPINGBLOCK INFORMATION");
        System.out.println(xpos + " ,  " + ypos);
    }


    //The move method. Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    //Because it's a wrapping block, this method also lets the object wrap and come out of the other side
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos + width > 1000) {
            xpos = 0 - width;
        }
        if (ypos + height > 700) {
            ypos = 0 - height;
        }
        rect = new Rectangle(xpos, ypos, width, height);
    }

    //This method makes the block seem as if it has disappeared, but it actually just takes the block past the visible area and makes it stop moving.
    public void disappear() {
        xpos = -100;
        dx = 0;
        dy = 0;
    }
}

