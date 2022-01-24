import java.awt.*;

public class Obstacle {
    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rect;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.
    public void printInfo() {
        System.out.println("OBSTACLE INFORMATION");
        System.out.println(xpos + " ,  " + ypos);


    }

    //This is a SECOND constructor that takes 3 parameters. This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Obstacle(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 0;
        dy = 0;
        width = 150;
        height = 150;
        isAlive = true;
        rect = new Rectangle(xpos, ypos, width, height);

    } // constructor

}
