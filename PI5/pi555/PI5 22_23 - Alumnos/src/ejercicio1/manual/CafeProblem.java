package ejercicio1.manual;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import _datos.DatosEjercicioCafes;
import _datos.DatosEjercicioCafes.Variedad;
import ejercicio1.CafeVertex;
import us.lsi.common.List2;

public record CafeProblem(Integer index, List<Double> remaining) {

	public static CafeProblem of(Integer i, List<Double> rest) {
		return new CafeProblem(i, rest);
	}
	
	public static CafeProblem initial() {
		List<Double> res = new ArrayList<>();
		for(int i = 0; i<DatosEjercicioCafes.getNumeroTipos(); i++) {
			res.add(DatosEjercicioCafes.getCantidad(i) + 0.);
		}
		return of(0, res);
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
			
	
			
	public CafeProblem neighbor(Integer action) {
			
			List<Double> res = List2.empty();
			Variedad v = DatosEjercicioCafes.getVariedades().get(index);
	
			for(int i = 0; i < DatosEjercicioCafes.getNumeroTipos(); i++) {
				if( (remaining().get(i) - v.porcentaje(i) * action) > 0 ) {
					Double aux = remaining().get(i) - v.porcentaje(i) * action;
					res.add(i, aux);
				}else {
					res.add(i, 0.);
				}
	
			}
			return of(index + 1, res);
			}

	public static Double heuristic(CafeVertex v1, Predicate<CafeVertex> goal,
			CafeVertex v2) {
	
		Double beneficioTotalEstimado = 0.;
		
		Integer indiceActual = v1.index(); // TENEMOS SELECCIONADA HASTA LA VARIEDAD i
		Integer indiceFinal = DatosEjercicioCafes.getNumeroVariedades();
		
		for(int i = indiceActual; i < indiceFinal; i++) {
			Variedad variedad = DatosEjercicioCafes.getVariedades().get(i);
			Integer kgMax = DatosEjercicioCafes.getMaxKgVariedad(i);
			v1.remaining().stream().map(e -> e / variedad.porcentaje(v1.remaining().indexOf(e)));
			Double beneficio = kgMax * DatosEjercicioCafes.getBeneficio(i);
			beneficioTotalEstimado += beneficio;
		}

		return beneficioTotalEstimado;
		
	}
	

	
	
}
