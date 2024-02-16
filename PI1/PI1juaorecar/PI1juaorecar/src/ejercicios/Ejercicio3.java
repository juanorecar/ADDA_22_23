package ejercicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;
import us.lsi.iterables.IteratorFile;



public class Ejercicio3 {
	
	
	private static Punto2D parsePunto(String s) {
		String[] v = s.split(",");
		Double x = Double.valueOf(v[0]);
		Double y = Double.valueOf(v[1]);
		return Punto2D.of(y,x);
	}
	
	public static List<Punto2D> ejercicio3Iterativo(String file1, 
			String file2, Comparator<Punto2D> comp){
		
			List<Punto2D> ls = new ArrayList<>();
			
			Iterator<String> it1 = new IteratorFile(file1);
			Iterator<String> it2 = new IteratorFile(file2);

			Punto2D e1 = (it1.hasNext()?parsePunto(it1.next()):null);
			Punto2D e2 = (it2.hasNext()?parsePunto(it2.next()):null);
			
			while(it1.hasNext()||it2.hasNext()) {
				if(e1 != null && e2 != null) {
					if(e1.compareTo(e2) < 0) {
						if(e1.x()>e1.y()-10) {
							ls.add(e1);
						}
						e1 = it1.hasNext()?parsePunto(it1.next()):null;
						
					} else {
						if(e2.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || e2.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
							ls.add(e2);
						}
						e2 = it2.hasNext()?parsePunto(it2.next()):null;
		
					}

				}else if(e1==null) {
					if((e2.getCuadrante()==Cuadrante.PRIMER_CUADRANTE||
							e2.getCuadrante()==Cuadrante.TERCER_CUADRANTE)) {
						ls.add(e2);
					}
					e2 = it2.hasNext()?parsePunto(it2.next()):null;
					
			}else {
				if((e1.getCuadrante()==Cuadrante.PRIMER_CUADRANTE||
						e1.getCuadrante()==Cuadrante.TERCER_CUADRANTE)) {
				ls.add(e1);
				}
				e1 = (it1.hasNext()?parsePunto(it1.next()):null);	
			
			}
			
		}
			return ls;
	}
	
	
	
	public static List<Punto2D> ejercicio3RecursivoFinal(String file1, 
			String file2, Comparator<Punto2D> comp){
	
		List<Punto2D> ls = new ArrayList<>();
		Iterator<String> it1 = new IteratorFile(file1);
		Iterator<String> it2 = new IteratorFile(file2);

		Punto2D e1 = (it1.hasNext()?parsePunto(it1.next()):null);
		Punto2D e2 = (it2.hasNext()?parsePunto(it2.next()):null);
	
		return ejercicio3RecursivoFinalAux(it1, it2, comp, ls, e1 ,e2);
	}


	private static List<Punto2D> ejercicio3RecursivoFinalAux(Iterator<String> it1, Iterator<String> it2, Comparator<Punto2D> comp,
			List<Punto2D> ls, Punto2D e1, Punto2D e2) {
		if(it1.hasNext()||it2.hasNext()) {
			if(e1 != null && e2 != null) {
				if(e1.compareTo(e2) < 0) {
					if(e1.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || e1.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
						ls.add(e1);
					}
					e1 = it1.hasNext()?parsePunto(it1.next()):null;
					ls = ejercicio3RecursivoFinalAux(it1, it2, comp, ls, e1, e2);
					
				} else {
					if(e2.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || e2.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
						ls.add(e2);
					}
					e2 = it2.hasNext()?parsePunto(it2.next()):null;
					ls = ejercicio3RecursivoFinalAux(it1, it2, comp, ls, e1, e2);
	
				}
		
			}
			else if(e1==null) {
				if((e2.getCuadrante()==Cuadrante.PRIMER_CUADRANTE||
						e2.getCuadrante()==Cuadrante.TERCER_CUADRANTE)) {
					ls.add(e2);
				}
				e2 = it2.hasNext()?parsePunto(it2.next()):null;
				ls = ejercicio3RecursivoFinalAux(it1, it2, comp, ls, e1, e2);
				
			}else {
				if((e1.getCuadrante()==Cuadrante.PRIMER_CUADRANTE||
						e1.getCuadrante()==Cuadrante.TERCER_CUADRANTE)) {
				ls.add(e1);
				}
				e1 = (it1.hasNext()?parsePunto(it1.next()):null);	
				ls = ejercicio3RecursivoFinalAux(it1, it2, comp, ls, e1, e2);
			}
		}
		
		
		return ls;
		
	}
	
	public static record Tupla(List<Punto2D> ac,Iterator<String> it1, 
			Iterator<String> it2, Comparator<Punto2D> comp, Punto2D e1, Punto2D e2) {
		
		public static Tupla of(List<Punto2D> ac,Iterator<String> it1, 
				Iterator<String> it2, Comparator<Punto2D> comp, Punto2D e1, Punto2D e2) {
			return new Tupla(ac, it1, it2, comp, e1, e2);
		}
	
		
		public Tupla next() {
			Tupla res = null;
			Punto2D e = null;
			if(e1 != null && e2 != null) {
				if(e1.compareTo(e2) < 0) {
					if(e1.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || e1.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
						ac.add(e1);
					}
					e = it1.hasNext()?parsePunto(it1.next()):null;
					res = of(ac, it1, it2, comp, e, e2);
					
				} else {
					if(e2.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || e2.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
						ac.add(e2);
					}
					e = it2.hasNext()?parsePunto(it2.next()):null;
					res = of(ac, it1, it2, comp, e1, e);
	
				}

		}else if(e1==null) {
				if((e2.getCuadrante()==Cuadrante.PRIMER_CUADRANTE||
						e2.getCuadrante()==Cuadrante.TERCER_CUADRANTE)) {
					ac.add(e2);
				}else {
				e = it2.hasNext()?parsePunto(it2.next()):null;
				}
				res = of(ac, it1, it2, comp, e1, e);
		}else {
			if((e1.getCuadrante()==Cuadrante.PRIMER_CUADRANTE||
					e1.getCuadrante()==Cuadrante.TERCER_CUADRANTE)) {
			ac.add(e1);
			}else {
			e = (it1.hasNext()?parsePunto(it1.next()):null);	
		}
		res = of(ac, it1, it2, comp, e, e2);
		}
			return res;
		}
	}
	
	
	public static List<Punto2D> ejercicio3Funcional(String file1, String file2, Comparator<Punto2D> comp) {
		
		Iterator<String> it1 = new IteratorFile(file1);
		Iterator<String> it2 = new IteratorFile(file2);

		Punto2D e1 = (it1.hasNext()?parsePunto(it1.next()):null);
		Punto2D e2 = (it2.hasNext()?parsePunto(it2.next()):null);
		
		List<Punto2D> ls = Stream.iterate(Tupla.of(null, it1, it2, comp, e1, e2), e->e.next())
				.filter(e -> (e.e1() == null && e.e2() == null))
				.findFirst()
				.get().ac();
		return ls;
		
	}	
}
