package utiles;

import us.lsi.common.Preconditions;
import us.lsi.geometria.ObjetoGeometrico2D;
import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Vector2D;

public record PuntoAux(Double x,Double y) implements Comparable<PuntoAux>, ObjetoGeometrico2D {	

	private static PuntoAux cero = PuntoAux.of(0.,0.);
	
	public static PuntoAux getOrigen(){
		return cero;
	}
	
	public static PuntoAux of(Double x, Double y) {
		return new PuntoAux(x, y);
	}

	public static PuntoAux of(PuntoAux p) {
		return new PuntoAux(p.x(), p.y());
	}
	
	
	public static PuntoAux of(Vector2D v){
		return PuntoAux.of(v.getX(),v.getY());
	}
	
	public enum Cuadrante{PRIMER_CUADRANTE, SEGUNDO_CUADRANTE, TERCER_CUADRANTE, CUARTO_CUADRANTE};
	
	
	
    
    public Cuadrante getCuadrante(){
		Cuadrante c;
		if(this.x() >=0 && this.y() >=0){
			c = Cuadrante.PRIMER_CUADRANTE;
		} else if(this.x() <=0 && this.y() >=0){
			c = Cuadrante.SEGUNDO_CUADRANTE;
		} else if(this.x() <=0 && this.y() <=0){
			c = Cuadrante.TERCER_CUADRANTE;
		} else {
			c = Cuadrante.CUARTO_CUADRANTE;
		}
		return c;
	}
    public PuntoAux add(Vector2D v){
    	return PuntoAux.of(this.x+v.getX(),this.y+v.getY());
    }
    
    public Vector2D minus(PuntoAux p){
    	return Vector2D.of(this.x-p.x(),this.y-p.y());
    }
    
    public Double getDistanciaA(PuntoAux p) {
		return minus(p).getModulo();
	}
    
	
	public PuntoAux traslada(Vector2D v){
		return add(v);
	}
    
	public PuntoAux rota(PuntoAux p, Double angulo){
		Vector2D v = minus(p).rota(angulo);
		return p.add(v);
	}
	
	
	public boolean dominaA(PuntoAux p){
		return !this.equals(p) && this.x() >= p.x() && this.y() >= p.y();
	}
	
	public String toString() {
    	String s="("+x()+","+ y()+")";
    	return s;
    }

	
	@Override
	public int compareTo(PuntoAux p) {
		Preconditions.checkNotNull(p,"El punto no puede ser null");
		int r = x().compareTo(p.x());
		if(r==0){
			r = y().compareTo(p.y());
		}
		return r;
	}

	@Override
	public ObjetoGeometrico2D rota(Punto2D p, Double angulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

		
	
}
