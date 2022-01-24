//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import com.sun.source.doctree.AttributeTree;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*; //library for keyboard use
import java.awt.event.KeyEvent;


//*******************************************************************************
//Class Definition Section
//Changes by Melvin Joseph

public class BasicGameApp implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 700;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    //Declare the objects used in the program
    //These are things that are made up of more than a variable type

    public Image backdrop;      //background image
    public Integer score1;      //score integer

    public int x;       //current block variable
    public int nColor;      //color index variable

    public Block[] blocks;      //block array
    public Image[] colors;      //image array
    public int[][] grid = new int[5][5];        //2d array for the grid


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {
        setUpGraphics();
        canvas.addKeyListener(this);
        System.out.println("Setting up game");

        //variable and objects
        //create (construct) the objects needed for the game and load up
        backdrop = Toolkit.getDefaultToolkit().getImage("backdrop.jpeg");
        blocks = new Block[30];     //blocks array
        colors = new Image[3];      //colors array

        //makes the blocks and iterates through the color array for each new block
        for (int i = 0; i < blocks.length; i++) {

            nColor = (int) (Math.random() * colors.length);     //random integer that keeps track of the color index

            blocks[i] = new Block(280, -140, nColor);       //constructs the blocks

            //indexes for the color(image) array
            colors[0] = Toolkit.getDefaultToolkit().getImage("red1.png");
            colors[1] = Toolkit.getDefaultToolkit().getImage("blue1.png");
            colors[2] = Toolkit.getDefaultToolkit().getImage("green1.png");

        }


        render();

    }

// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up

    public void moveThings() {

        //this code makes sure that only one block is moving at a time
        blocks[x].move();   //calls the move() code in the objects
        if (blocks[x].ypos + blocks[x].height >= 700) {
            blocks[x].ypos = 700 - blocks[x].height;
            blocks[x].dx = 0;
            blocks[x].dy = 0;
        }
        if (blocks[x].ypos + blocks[x].height > 10 && blocks[x].dx == 0) {    //checks if the block is at the bottom
            x++;    //this activates the next block
            blocks[x].isAlive = true;
        }

    }

/*
    public void checkGrid() {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = nColor;
            }
        }

    }
*/


    public void run() {

        while (true) {
            moveThings();
            checkIntersections();
            render();
            pause(25);

        }
    }

    public void checkIntersections() {

        for (int i = 0; i < blocks.length; i++) {

        }
    }


    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //code to draw things
        g.drawImage(backdrop, -5, -5, 1100, 800, null);

        //draws the rectangles and blocks
        for (int i = 0; i < blocks.length; i++) {
            g.drawImage(colors[blocks[i].color], blocks[i].xpos, blocks[i].ypos, blocks[i].width, blocks[i].height, null);
            g.drawRect(blocks[i].xpos, blocks[i].ypos, blocks[i].width, blocks[i].height);
        }

        //text color and score text
        g.setColor(Color.WHITE);
        g.drawString("score: " + score1, 20, 20);
        g.dispose();


        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("My First Game");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DA graphic setup");

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode;
        keyCode = e.getKeyCode();
        char keyCharacter;
        keyCharacter = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode;
        keyCode = e.getKeyCode();

        //makes sure that the individual blocks move when the key is released, so that they don't move more than once accidentally
        //adds intersection codes so that the blocks don't move for some intersections and attach for others

        for (int i = 0; i < blocks.length; i++) {
            if (keyCode == KeyEvent.VK_S) {
                blocks[x].ypos += 35;
            }
            if (blocks[x].bottomSide.intersects(blocks[i].upSide) && i < x) {
                blocks[x].isAlive = false;
                blocks[x].dx = 0;
                blocks[x].dy = 0;
                blocks[x].xpos = blocks[i].xpos;
                blocks[x].ypos = blocks[i].ypos - 140;
            }
            if (keyCode == KeyEvent.VK_D) {
                if (blocks[x].rightSide.intersects(blocks[i].leftSide) && i < x) {

                } else {
                    blocks[x].xpos += 140;
                }
            }
            if (keyCode == KeyEvent.VK_A) {
                if (blocks[x].leftSide.intersects(blocks[i].rightSide) && i < x) {

                } else {
                    blocks[x].xpos -= 140;
                }
            }
        }
    }
}


//EXTRA CODE

/*
    colors[3] = Toolkit.getDefaultToolkit().getImage("gold1.png");
    colors[4] = Toolkit.getDefaultToolkit().getImage("purple1.png");
*/
/*
 if(blocks[x].ypos+blocks[x].height-14>blocks[i].ypos&&blocks[x].xpos+blocks[x].width-14<blocks[i].xpos){
        blocks[x].dx=0;
        blocks[x].dy=0;
        blocks[x].xpos=blocks[i].xpos-140;
        blocks[x].ypos=blocks[i].ypos;
        blocks[x].isAlive=false;
        }else if(blocks[x].ypos+blocks[x].height-14>blocks[i].ypos&&blocks[x].xpos+blocks[x].width+14>blocks[i].xpos){
        blocks[x].dx=0;
        blocks[x].dy=0;
        blocks[x].xpos=blocks[i].xpos+140;
        blocks[x].ypos=blocks[i].ypos;
        blocks[x].isAlive=false;
*/

/*
for (int i = 0; i < blocks.length; i++) {

            if (blocks[x].isAlive) {
                if ((blocks[x].rect.intersects(blocks[i].rect) && (i < x))) {
                    blocks[x].isAlive = false;
                    blocks[x].dx = 0;
                    blocks[x].dy = 0;
                    blocks[x].xpos = blocks[i].xpos;
                    blocks[x].ypos = blocks[i].ypos - 140;
                }
                if ((blocks[x].rect.contains(blocks[i].xpos - 10, blocks[i].ypos + 140) && (i < x))) {
                    blocks[x].isAlive = false;
                    blocks[x].dx = 0;
                    blocks[x].dy = 0;
                    blocks[x].xpos = blocks[i].xpos - 140;
                    blocks[x].ypos = blocks[i].ypos - 4;
                }
                if ((blocks[x].rect.contains(blocks[i].xpos + 150, blocks[i].ypos + 140) && (i < x))) {
                    blocks[x].isAlive = false;
                    blocks[x].dx = 0;
                    blocks[x].dy = 0;
                    blocks[x].xpos = blocks[i].xpos + 140;
                    blocks[x].ypos = blocks[i].ypos - 4;
                }
            }
        }
*/