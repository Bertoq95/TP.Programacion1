package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Calle {
	private double x;
    private double y;
    private double alto;
    private double ancho;
    private double velocidad;
    private Vehiculo vehiculos[] = new Vehiculo[5];
    
    Calle(double x, double y, double ancho, double alto){
    	this.x = x;
    	this.y = y;
    	this.alto = alto;
    	this.ancho = ancho;
    	this.velocidad = 0.5;
    	llenarArrayVehiculos(y);
    	
   }
    
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
   private void llenarArrayVehiculos(double yCalle) {
	   int randomIntVelocidad;
	   for (int i = 0; i <= 4; i++) {
		   randomIntVelocidad = getRandomNumber(1,5);
		   double posicionVehiculo = yCalle - 70 + 70*(i) ;
		   
		   if(i / 2 == 0) {
			   this.vehiculos[i] = new Vehiculo(0,posicionVehiculo,40,20,randomIntVelocidad*0.5);
		   } else {
			   this.vehiculos[i] = new Vehiculo(800,posicionVehiculo,40,20,randomIntVelocidad*0.5);
		   }   
	   }
   }
   
    
   public double getY() {
		return this.y;
	}

	public double getAlto() {
		return alto;
	}
	
	public double getVelocidad() {
		return this.velocidad;
	}
	
	public void mover() {
		this.y= this.y +  this.velocidad;
		for (int i = 0; i < vehiculos.length; i++) {
			vehiculos[i].moverAbajo();
			vehiculos[i].mover();
		}		
	}
	public void volver() {
		this.y = 0 - (this.alto/2);
		llenarArrayVehiculos(y);
	}

    public void Dibujarse(Entorno entorno, Color color) {
    	entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto,0.0,color);
    	for (int i = 0; i < vehiculos.length; i++) {
    		vehiculos[i].Dibujarse(entorno);
		}
   }
}