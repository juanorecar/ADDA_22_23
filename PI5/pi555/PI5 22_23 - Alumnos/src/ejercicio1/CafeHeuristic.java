package ejercicio1;

import java.util.function.Predicate;
import _datos.DatosEjercicioCafes;
import _datos.DatosEjercicioCafes.Variedad;


public class CafeHeuristic {

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
