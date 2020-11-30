package juego;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Conejo conejo;
	private Calle calles[] = new Calle[3];
	private Color colors[] = {Color.BLUE,Color.GREEN,Color.YELLOW};
	private Kamehameha Kame[] = new Kamehameha[3];
	
	// Variables y métodos propios de cada grupo
	// ...
	

	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo 3 - v1", 900, 600);
		
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
		this.conejo.Dibujarse(this.entorno);
		
		for (int i = 0; i < calles.length; i++) {
			if(colisionConejoVehiculo(this.conejo, this.calles[i].getVehiculos())) {
				
				this.conejo.setY(600);
				entorno.dispose();
				
				
			}
			
			
		//	if(colisionKameVehiculo(this.Kame,this.calles[i].getVehiculos()) != 3) {
		//		   int a =colisionKameVehiculo(this.Kame,this.calles[i].getVehiculos());
		//		   Kame[a]=null;
		//		}
		}
		
		
		
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
       
        if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
            for (int i = 0; i < Kame.length; i++) {
				if(Kame[i]==null) {
					Kame[i] = this.conejo.kamehameha();
				}
			} 
        }
        dibujarKames();

	}
		
	
	//colision entre conejo y Auto
	public static boolean colisionConejoVehiculo(Conejo conejo, Vehiculo[] vehiculos ) {
		double posicionSupVehiculo;
		double posicionInfVehiculo;
		double posicionIzqVehiculo;
		double posicionDerVehiculo;
		
		double posicionSupConejo;
		double posicionInfConejo;
		double posicionIzqConejo;
		double posicionDerConejo;
		
		boolean colisionSupAutoConejo;
		boolean colisionInfAutoConejo;
		boolean colisionIzqAutoConejo;
		boolean colisionDerAutoConejo;
		 
		
		
		for (int i = 0; i < vehiculos.length; i++) {
			posicionSupVehiculo = vehiculos[i].getY() - vehiculos[i].getAlto()/2;
			posicionInfVehiculo = vehiculos[i].getY() + vehiculos[i].getAlto()/2;
			posicionIzqVehiculo = vehiculos[i].getX() - vehiculos[i].getAncho()/2;
			posicionDerVehiculo = vehiculos[i].getX() + vehiculos[i].getAncho()/2;
			
			posicionSupConejo = conejo.getY() - conejo.getAlto()/2;
			posicionInfConejo = conejo.getY() + conejo.getAlto()/2;
			posicionIzqConejo = conejo.getX() - conejo.getAncho()/2;
			posicionDerConejo = conejo.getX() + conejo.getAncho()/2;
						
			colisionSupAutoConejo = (posicionSupVehiculo > posicionSupConejo) && (posicionSupVehiculo < posicionInfConejo);
			colisionInfAutoConejo = (posicionInfVehiculo > posicionSupConejo) && (posicionInfVehiculo < posicionInfConejo);
			colisionIzqAutoConejo = (posicionIzqVehiculo > posicionIzqConejo) && (posicionIzqVehiculo < posicionDerConejo);
			colisionDerAutoConejo = (posicionDerVehiculo > posicionIzqConejo) && (posicionDerVehiculo < posicionDerConejo);
			
			if((colisionSupAutoConejo || colisionInfAutoConejo) && (colisionIzqAutoConejo || colisionDerAutoConejo)) {
				
				return true;
				
			}
			
			
		}
		return false;		
	}
	public void dibujarKames() {
        for (Kamehameha kamehameha : Kame) {
            kamehameha.mover();
        	kamehameha.dibujarKame(entorno);
        }
    }
	
	public static int colisionKameVehiculo(Kamehameha[] kamehamehas2, Vehiculo[] vehiculos ) {
		double posicionSupVehiculo2;
		double posicionInfVehiculo2;
		double posicionIzqVehiculo2;
		double posicionDerVehiculo2;
		
		double posicionSupkamehameha;
		double posicionInfkamehameha;
		double posicionIzqkamehameha;
		double posicionDerkamehameha;
		
		boolean colisionSupAutoKame;
		boolean colisionInfAutoKame;
		boolean colisionIzqAutoKame;
		boolean colisionDerAutoKame;
		
		for (int i = 0; i < vehiculos.length; i++) {
			posicionSupVehiculo2 = vehiculos[i].getY() - vehiculos[i].getAlto()/2;
			posicionInfVehiculo2 = vehiculos[i].getY() + vehiculos[i].getAlto()/2;
			posicionIzqVehiculo2 = vehiculos[i].getX() - vehiculos[i].getAncho()/2;
			posicionDerVehiculo2 = vehiculos[i].getX() + vehiculos[i].getAncho()/2;
			for (int j = 0; j < kamehamehas2.length; j++) {
			posicionSupkamehameha = kamehamehas2[j].getY() - kamehamehas2[j].getRadio()/2;
			posicionInfkamehameha = kamehamehas2[j].getY() + kamehamehas2[j].getRadio()/2;
			posicionIzqkamehameha = kamehamehas2[j].getX() - kamehamehas2[j].getRadio()/2;
			posicionDerkamehameha = kamehamehas2[j].getX() + kamehamehas2[j].getRadio()/2;
			
			colisionSupAutoKame = (posicionSupVehiculo2 > posicionSupkamehameha) && (posicionSupVehiculo2 < posicionInfkamehameha);
			colisionInfAutoKame = (posicionInfVehiculo2 > posicionSupkamehameha) && (posicionInfVehiculo2 < posicionInfkamehameha);
			colisionIzqAutoKame = (posicionIzqVehiculo2 > posicionIzqkamehameha) && (posicionIzqVehiculo2 < posicionDerkamehameha);
			colisionDerAutoKame = (posicionDerVehiculo2> posicionIzqkamehameha) && (posicionDerVehiculo2 < posicionDerkamehameha);
         if((colisionSupAutoKame || colisionInfAutoKame) && (colisionIzqAutoKame || colisionDerAutoKame)) {
				
				return j;
				
			}
		  }
		}
			
		return 3;
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
