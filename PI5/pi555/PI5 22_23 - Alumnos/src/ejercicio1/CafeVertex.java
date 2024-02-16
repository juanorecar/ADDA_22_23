package ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import _datos.DatosEjercicioCafes;
import _datos.DatosEjercicioCafes.Variedad;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record CafeVertex(Integer index, List<Double> remaining)
		implements VirtualVertex<CafeVertex, CafeEdge, Integer>{
		
	public static CafeVertex of(Integer i, List<Double> rest) {
		return new CafeVertex(i, rest);
	}
	
	public static CafeVertex initial() {
		List<Double> res = new ArrayList<>();
		for(int i = 0; i<DatosEjercicioCafes.getNumeroTipos(); i++) {
			res.add(DatosEjercicioCafes.getCantidad(i) + 0.);
		}
		return of(0, res);
	}
	
	public static Predicate<CafeVertex> goal(){
		return v -> v.index() == DatosEjercicioCafes.getNumeroVariedades();
	}
	
	public static Predicate<CafeVertex> goalHasSolution(){
		return v -> v.remaining().stream().allMatch(e -> e.equals(0.));
	}
	
	public List<Integer> actions() {
		List<Integer> alternativas = List2.empty();
		
		if(index < DatosEjercicioCafes.getNumeroVariedades()) {
			
			if(index == DatosEjercicioCafes.getNumeroVariedades()) {
				alternativas = List2.of(DatosEjercicioCafes.getKilosMaximosVariedad(index, remaining) + 1);
			}else {
				alternativas = List2.rangeList(0, DatosEjercicioCafes.getKilosMaximosVariedad(index, remaining) + 1);
			}
		}
		
		return alternativas;
	}
			
	
			
public CafeVertex neighbor(Integer a) {
		
		List<Double> res = List2.empty();
		Variedad v = DatosEjercicioCafes.getVariedades().get(index);

		for(int i = 0; i < DatosEjercicioCafes.getNumeroTipos(); i++) {
			if( (remaining().get(i) - v.porcentaje(i) * a) > 0 ) {
				Double aux = remaining().get(i) - v.porcentaje(i) * a;
				res.add(i, aux);
			}else {
				res.add(i, 0.);
			}

		}
		
//		System.out.println(res);
		
		return of(index + 1, res);
		
	}
	
	public CafeEdge edge(Integer a) {
		return CafeEdge.of(this, neighbor(a), a);
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
