package juego;

import java.awt.Color;

import entorno.Entorno;

public class Kamehameha {
	private double x;
	private double y;
	private double radio;
	private double velocidad;
	
	public Kamehameha(double x, double y, double radio, double velocidad) {
		super();
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.velocidad = velocidad;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}
	
	 public void mover() {
    	this.y = this.y - this.velocidad ;
    }
	
	public void Dibujar(Entorno entorno) {
		entorno.dibujarCirculo(x, y, radio, Color.WHITE);
	}
}
