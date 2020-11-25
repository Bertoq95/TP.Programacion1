package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Conejo conejo;
	private Calle calles[] = new Calle[3];
	private Color colors[] = {Color.BLUE,Color.GREEN,Color.YELLOW};
		
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo 3 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.conejo = new Conejo(400,550,30,30);
		double posicionInicY ;
		for (int i = 0; i < calles.length; i++) {
			posicionInicY = 150 - (i*240);
			this.calles[i] = new Calle(405,posicionInicY,800,200);
		}		
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		
		for (int i = 0; i < calles.length; i++) {
			this.calles[i].Dibujarse(this.entorno,this.colors[i]);	
			this.calles[i].mover();
			if(this.calles[i].getY() >= 600 + (this.calles[i].getAlto()/2)) {
					this.calles[i].volver();
			}			
		}
		
		//conejo
		conejo.Dibujarse(this.entorno);
		this.conejo.moverDown();
		if (this.entorno.sePresiono(this.entorno.TECLA_ARRIBA)) {
			this.conejo.moverUp();
		}
		
		if (this.entorno.sePresiono(this.entorno.TECLA_IZQUIERDA) && this.conejo.getX()>20) {
			this.conejo.moverLeft();

		}
		
		if (this.entorno.sePresiono(this.entorno.TECLA_DERECHA)&&this.conejo.getX()< this.entorno.getWidth()-20) {
			this.conejo.moverRight();
		}
		
	}	
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
