package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Conejo conejo;
	private Calle calles[]; //// = new Calle[3]; ///LAS VARIABLES SE INICIALIZAN EN EL CONSTRUCTOR!!!
	private Color colors[] = { Color.BLUE, Color.GREEN, Color.YELLOW };
	private Kamehameha kames[]; /// = new Kamehameha[3]; ///LAS VARIABLES SE INICIALIZAN EN EL CONSTRUCTOR!!!
	private int cont = 0;
	
	


	// Variables y  propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo 3 - v1", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...
		
		calles = new Calle[3];
		kames = new Kamehameha[3];

		this.conejo = new Conejo(400, 550, 30, 30);
		double posicionInicY;
		for (int i = 0; i < calles.length; i++) {
			posicionInicY = 150 - (i * 240);
			this.calles[i] = new Calle(405, posicionInicY, 800, 200);
		}
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el mtodo tick() sera ejecutado en cada instante y por lo
	 * tanto es el mtodo ms importante de esta clase. Aqu se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...

		for (int i = 0; i < calles.length; i++) {
			this.calles[i].Dibujarse(this.entorno, this.colors[i]);
			this.calles[i].mover();
			if (this.calles[i].getY() >= 600 + (this.calles[i].getAlto() / 2)) {
				this.calles[i].volver();
			}
		}

		// dibuja conejo
		this.conejo.Dibujarse(this.entorno);

		
		///verifica el choque entre conejo y vehiculo y apaga el entorno si choca
		for (int i = 0; i < calles.length; i++) {
			for (int j = 0; j < calles[i].getVehiculos().length; j++) {
				if(this.calles[i].getVehiculos()[j] != null)
				{
				if (colisionConejoVehiculo(this.calles[i].getVehiculos()[j])) {
					this.conejo.setY(600);
					entorno.dispose();
				}
				}
			}
		}
		
		///Aca verifica colision entre kamehameha y vehiculo, y desaparece ambos si hay colision
		for (int i = 0; i < calles.length; i++) {
			for (int j = 0; j < calles[i].getVehiculos().length; j++) {
				for (int k = 0; k < kames.length; k++) {
					if(kames[k] != null && calles[i].getVehiculos()[j] != null)
					{						
						if(colisionKamehamehaVehiculo(kames[k], calles[i].getVehiculos()[j]))
						{
							kames[k] = null;
							calles[i].getVehiculos()[j] = null;
						}
					}									
				}
			}			
		}
        //mueve conejo hacia abajo si se presiono abajo
		this.conejo.moverDown();
		if (this.entorno.sePresiono(this.entorno.TECLA_ARRIBA)) {
			this.conejo.moverUp();
		}
        //mueve hacia la izquierda al conejo si se presiono izquierda
		if (this.entorno.sePresiono(this.entorno.TECLA_IZQUIERDA) && this.conejo.getX() > 20) {
			this.conejo.moverLeft();

		}
		//mueve hacia la derecha al conejo si se presiono derecha
		if (this.entorno.sePresiono(this.entorno.TECLA_DERECHA) && this.conejo.getX() < this.entorno.getWidth() - 20) {
			this.conejo.moverRight();
		}
		//Dispara el kamehameha enl caso de tocar la barra espaciadora
		if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)) {

			boolean inserteUnKame = false;
			for (int i = 0; i < kames.length && !inserteUnKame; i++) {
				if (kames[i] == null) 
				{
					kames[i] = this.conejo.disparar();
					inserteUnKame = true;
				}
			}
		}
		//dibuja el kamehameha y desaparece por arriba
		for (int i = 0; i < kames.length; i++) {
			if (kames[i] != null) {
				kames[i].mover();
				kames[i].Dibujar(entorno);
				
				if(kames[i].getY() <= -10) 
				{
					kames[i] = null;
				}
			}
		}
	}

	

	//metodo de colision del conejo con vehiculo
	private boolean colisionConejoVehiculo(Vehiculo vehiculo) {
		double posicionSupVehiculo;
		double posicionInfVehiculo;
		double posicionIzqVehiculo;
		double posicionDerVehiculo;
		
		double posicionSupConejo;
		double posicionInfConejo;
		double posicionIzqConejo;
		double posicionDerConejo;			
		
		posicionSupVehiculo = vehiculo.getY() - vehiculo.getAlto()/2;
		posicionInfVehiculo = vehiculo.getY() + vehiculo.getAlto()/2;
		posicionIzqVehiculo = vehiculo.getX() - vehiculo.getAncho()/2;
		posicionDerVehiculo = vehiculo.getX() + vehiculo.getAncho()/2;
			
		posicionSupConejo = conejo.getY() - conejo.getAlto()/2;
		posicionInfConejo = conejo.getY() + conejo.getAlto()/2;
		posicionIzqConejo = conejo.getX() - conejo.getAncho()/2;
		posicionDerConejo = conejo.getX() + conejo.getAncho()/2;
											
		return !(posicionSupVehiculo > posicionInfConejo || posicionInfVehiculo < posicionSupConejo || posicionIzqVehiculo > posicionDerConejo || posicionDerVehiculo < posicionIzqConejo);		
	}
   //metodo de colision kamehameha con vehiculo
	private boolean colisionKamehamehaVehiculo(Kamehameha kamehameha, Vehiculo vehiculo ) {
		double posicionSupVehiculo;
		double posicionInfVehiculo;
		double posicionIzqVehiculo;
		double posicionDerVehiculo;
		
		double posicionSupKame;
		double posicionInfKame;
		double posicionIzqKame;
		double posicionDerKame;
				
		
		posicionSupVehiculo = vehiculo.getY() - vehiculo.getAlto()/2;
		posicionInfVehiculo = vehiculo.getY() + vehiculo.getAlto()/2;
		posicionIzqVehiculo = vehiculo.getX() - vehiculo.getAncho()/2;
		posicionDerVehiculo = vehiculo.getX() + vehiculo.getAncho()/2;
			
		posicionSupKame = kamehameha.getY() - kamehameha.getRadio();
		posicionInfKame = kamehameha.getY() + kamehameha.getRadio();
		posicionIzqKame = kamehameha.getX() - kamehameha.getRadio();
		posicionDerKame = kamehameha.getX() + kamehameha.getRadio();
											
		return !(posicionSupVehiculo > posicionInfKame || posicionInfVehiculo < posicionSupKame || posicionIzqVehiculo > posicionDerKame || posicionDerVehiculo < posicionIzqKame);		
	}
	
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
