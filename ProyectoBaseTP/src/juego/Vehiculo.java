package juego;

import entorno.Entorno;

import java.awt.Color;

public class Vehiculo {
	private double x;
    private double y;
    private double alto;
    private double ancho;
    private double velocidad;
    private double xInicial;
    
    public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	Vehiculo(double x, double y, double ancho, double alto, double velocidad){
    	this.x = x;
    	this.y = y;
    	this.alto = alto;
    	this.ancho = ancho;
    	this.velocidad = velocidad;
    	this.xInicial = x;
    }
    
    public double getVelocidad() {
		return this.velocidad;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	//Mueve el vehiculo
	public void mover() {
		if(this.xInicial == 0) {
			moverDerecha();
		}else {
			moverIzquierda();
		}
	}
	//Mueve el vehiculo hacia la derecha en caso que corresponda
	private void moverDerecha() {
		if(this.x >= 800) {
			this.x = 0;
		}
		this.x = this.x + this.velocidad;
	}
	//Mueve el vehiculo hacia la izquierda enl caso que corresponda
	private void moverIzquierda() {
		if(this.x <= 0) {
			this.x = 800;
		}
		this.x = this.x - this.velocidad;
	}
	//Baja el vehiculo a la par de la pantalla
	public void moverAbajo() {
		this.y = this.y + 0.5;
	}
    //Dibuja el vehiculo
 	public void Dibujarse(Entorno entorno) {
    	entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto,0.0,Color.RED);
	}

	
}