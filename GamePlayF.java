package flppy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlayF extends JPanel implements ActionListener, KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private boolean play=false;
	public int delay = 12;
	public int ticks,Ymotion,score;
	public Rectangle bird;
	public int birdX=200;
	public int birdY=290;
	public int HEIGHT=600;
	public int WIDTH=800;
	public Random rand;
	public boolean gameOver;
	public boolean started;
	public ArrayList<Rectangle> columns;
	
	
	public GamePlayF(){
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		
		bird=new Rectangle(WIDTH/2,HEIGHT/2,20,20);
		
		rand = new Random();
		
		columns = new ArrayList<Rectangle>();
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		
		
		
		
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void addColumn(boolean start){
		
		
		int space=280;
		int width =100;
		int height = 50+ rand.nextInt(300);
		if(start){
		columns.add(new Rectangle(WIDTH + width + columns.size()*200,HEIGHT-height-120,width,height));
		
		columns.add(new Rectangle(WIDTH + width + (columns.size() - 1)*200,0,width,HEIGHT-height-space));
	}
		else{
			columns.add(new Rectangle(columns.get(columns.size() -1 ).x+500,HEIGHT-height-120,width,height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x,0,width,HEIGHT-height-space));
		}
	}
	
	public void paintColumn(Graphics g,Rectangle column){
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
		
		
	}
	
	public void paint(Graphics g){
		g.setColor(Color.cyan);
		g.fillRect(0, 0, 800, 600);
		
		g.setColor(Color.darkGray);
		g.fillRect(0, HEIGHT-120, WIDTH, 150);
		
		g.setColor(Color.green);
		g.fillRect(0, HEIGHT-120, WIDTH, 20);
		
		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, 20, 20);
		
		for(Rectangle column : columns){
			paintColumn(g,column);
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",1,70));
		if(gameOver){
			g.drawString("GameOver!", 150, HEIGHT/2-50);
			g.drawString(String.valueOf(score), WIDTH/2-25,100);
		}

		
if(!gameOver){
	g.drawString(String.valueOf(score), WIDTH/2-25,100);
		}
		
		
		g.dispose();
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			jump();
		}
	

	}
	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {}

public void jump(){
	if(gameOver){
bird=new Rectangle(WIDTH/2,HEIGHT/2,20,20);
		
		
		
		columns.clear();
		Ymotion=0;
		score=0;
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		
		gameOver=false;
		
	}
	if(!started){
		started=true;
	}
	else if(!gameOver){
		if(Ymotion>0){
			Ymotion=0;
		}
		Ymotion-=7;
	}
}

	public void actionPerformed(ActionEvent e) {
		int speed=3;
		
		
		if(started){
			ticks++;
		for(int i=0;i<columns.size();i++){
			Rectangle column=columns.get(i);
			column.x-=speed;
		}
		if(ticks%2==0 && Ymotion<15){
			Ymotion+=1;
			
		}
		for(int i=0;i<columns.size();i++){
			Rectangle column=columns.get(i);
			if(column.x +column.width<0){
				columns.remove(column);
				if(column.y==0){
					addColumn(false);
				}
			}
			
		}
	
		bird.y += Ymotion;
		
		for(Rectangle column :columns){
			if(column.y==0 && bird.x + bird.width/2>column.x+column.width/2-10 && bird.x+bird.width/2<column.x+column.width/2+10){
				score++;
			}
			if(column.intersects(bird)){
			
				gameOver = true;
				bird.x=column.x-bird.width;
				started=false;
			}
		}
		if(bird.y>HEIGHT-120||bird.y<0){
			gameOver=true;
			started=false;
		}
		
		if(bird.y+Ymotion >=HEIGHT-120){
			bird.y=HEIGHT-120-bird.height;
		}
		}
		repaint();
		

	}

}
