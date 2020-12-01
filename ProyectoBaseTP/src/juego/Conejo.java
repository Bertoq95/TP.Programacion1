package juego;

import java.awt.Color;

import entorno.Entorno;


public class Conejo {
    private double x;
    private double y;
    private double alto;
    private double ancho;
    private double velocidad;
   
    
    Conejo(double x, double y, double alto, double ancho){
    	this.x = x;
    	this.y = y;
    	this.alto = alto;
    	this.ancho = ancho;
    	this.velocidad = 0.5;
    
    }
    
    public void Dibujarse(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.red);
		
	}

	public void moverDown() {
    	this.y = this.y + this.velocidad;
    }
    
	void moverRight() {
    	this.x = this.x + 20 ;
    }
	
    void moverLeft() {
    	this.x = this.x - 20;
    }
    
    void moverUp() {
    	this.y = this.y - 20;
    }
    

    public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public void setY(double y) {
		this.y = y;
	}
    
    public double getX() {
		return this.x;
	}
    
    public double getY() {
		return this.y;
	}  	
}
