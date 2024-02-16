package records;

import java.util.Iterator;
import java.util.List;

import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;

public record Tupla3(Iterator<String> it1, Punto2D p1,
					 Iterator<String> it2, Punto2D p2,
					 List<Punto2D> res) {
	
	public static Tupla3 of(Iterator<String> it1, Punto2D p1, 
							Iterator<String> it2, Punto2D p2,
							List<Punto2D> res) {
		
		return new Tupla3(it1, p1, it2, p2, res);
	}
	
	public Tupla3 next() {
		
		Tupla3 r = null;
		Punto2D p = null;
		
		if(p1 != null && p2 != null) {
			if(p1.compareTo(p2) < 0) {
				if(p1.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p1.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
					res.add(p1);
				}
				p = it1.hasNext()?Punto2D.parse(it1.next()):null;
				r = of(it1, p, it2, p2, res);	
			} else {
				if(p2.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p2.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
					res.add(p2);
				}
				p = it2.hasNext()?Punto2D.parse(it2.next()):null;
				r = of(it1, p1, it2, p, res);
			}
		} else if(p1 != null) {
			if(p1.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p1.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
				res.add(p1);
			}
			p = it1.hasNext()?Punto2D.parse(it1.next()):null;
			r = of(it1, p, it2, p2, res);
		}else if(p2 != null) {
			if(p2.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p2.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
				res.add(p2);
			}
			p = it2.hasNext()?Punto2D.parse(it2.next()):null;
			r = of(it1, p1, it2, p, res);
		}
		
		return r;
	}

}
