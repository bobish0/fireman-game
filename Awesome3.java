
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;  // import
import java.io.File;
import java.io.IOException; // import
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;   // import
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent; // didnt tell me about this either, for making mouse things work.
import java.applet.AudioClip;
import java.awt.event.ActionEvent;		// import flow of time
import java.awt.event.ActionListener;    //  import object movement
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;



class Awesome3 extends JComponent implements ActionListener, KeyListener  {

private int spelBredd = 1600;
private int spelHojd  = 1200;
private int markHojd	= 80;
private int byggnadBredd= 157;
private int markY = spelHojd - markHojd;
private int brandmanX =  400;
private int eldX = 100;
private int eldY = 100;



// starting position of fruit. IMPORTANT IMAGES ARE NOT USED. WILL BE REPLACED IN LATER UPDATE. 
private int fruktX = 100;
private double fruktY = 200;

BufferedImage fruitImage;
BufferedImage car;
BufferedImage byggnad;
BufferedImage brandMan;
BufferedImage fire;


private double fruktYHastighet = 0;  // frukternas fallhastighet
private int gravitation = 1; 		//


public Awesome3() throws IOException {  // constructor, the images are called here.

	fruitImage = ImageIO.read(getClass().getResource("pineApple.png"));
	car = ImageIO.read(getClass().getResource("policeCar.png"));
	brandMan = ImageIO.read(getClass().getResource("hitMan.png"));
	byggnad = ImageIO.read(getClass().getResource("byggnad.png"));
	fire = ImageIO.read(getClass().getResource("rsz_fire.png"));

}

public static void main (String[] args) throws IOException{
	JFrame fonster = new JFrame("R�dda frukterna");
	Awesome3 spel = new Awesome3();
	fonster.add(spel);
	fonster.pack();  // makes the window resize itself so the game fits inside.
	fonster.setLocationRelativeTo(null);   //centers the window on the screen when starting.
	fonster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // end the program when closing the window.
	fonster.setVisible(true); // see the window.

	Timer t = new Timer(20, spel);
	t.start();
	fonster.addKeyListener(spel);

	}  // end of main-method.


	// override
	public Dimension getPreferredSize(){   // skapar rutan.
		return new Dimension(spelBredd,spelHojd);

	}


// override
protected void paintComponent(Graphics m) {
	super.paintComponent(m);

		// bakgrund
		m.setColor(Color.blue);
		m.fillRect(0,0,spelBredd,spelHojd);

		// marken
		m.setColor(Color.gray);
		m.fillRect(0,1200- markHojd,spelBredd, markHojd);

		// byggnaden
		m.setColor(Color.orange);
		//m.fillRect(0,120, byggnadBredd, spelHojd - 120);
		m.drawImage(byggnad, 0, 0, null);

		// elden
		m.setColor(Color.orange);
		m.drawImage(fire,eldX,eldY,null);


		// ambulans
		m.setColor(Color.green);
        m.drawImage(car, 1360, 1000, null);
				    //    x    y

		// brandm�nnen
		m.setColor(Color.red);
		m.drawImage(brandMan, brandmanX, spelHojd-markHojd-120,null );
		m.drawImage(brandMan, brandmanX + 300, spelHojd-markHojd-120,null );

		// trampolin
		m.setColor(Color.pink);
		m.fillRect(brandmanX + 60,spelHojd-120,240,20);

		// fruit
		m.setColor(Color.yellow);
		m.drawImage(fruitImage, fruktX, (int)fruktY, null);

		}

	//override
	public void actionPerformed(ActionEvent h){  // boilerplate/ obligatoriskt f�r funktionen action event. everything in the method explains what happens every repaint.
		fruktX = fruktX + 4;   // hastighet, statisk per uppdatering.
		fruktY = fruktY + fruktYHastighet;  // hastighet, acceleration.
		fruktYHastighet = fruktYHastighet + gravitation; // for every repaint gravitation makes fruktYHastighet speed increase by 1.


				// kolla om frukten �r n�ra marken.
		if (fruktY >= markY){

		if (fruktX + 50 >= brandmanX && brandmanX + 300 >= fruktX){
			fruktYHastighet = -0.9 * fruktYHastighet; //speed will turn negative and bounce up and gravity will always add a positive value 1 on every repaint();.
				}


			}


		if (fruktY > spelHojd || fruktX > spelBredd ){
			fruktY = 200;
			fruktX = 100;
			fruktYHastighet = 0;
		}



		Random eldSlump = new Random();

	         eldX = eldSlump.nextInt(157);
			 eldY = eldSlump.nextInt(300);


		repaint(); // whenever using actionPerformed, repaint(); is needed ( to refresh);

	}

	// override;
	public void keyTyped(KeyEvent h) {


	}

	//override;
	public void keyPressed(KeyEvent h){


		if(h.getKeyCode() == KeyEvent.VK_LEFT){
			brandmanX = brandmanX - 80;  // =+ not working.
			}


		if(h.getKeyCode() == KeyEvent.VK_RIGHT){
		brandmanX = brandmanX + 80;
		}


		repaint();
	}

	//override;
	public void keyReleased(KeyEvent h){

		}




}