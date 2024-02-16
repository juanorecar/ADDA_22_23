package ejercicio2.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import _datos.DatosEjercicioCursos;
import _soluciones.SolucionCursos;
import us.lsi.common.List2;

public class CursoPDR {

	public static record Spm(Integer a, Double weight) implements Comparable<Spm> {
	
		public static Spm of(Integer a, Double weight) {
		return new Spm(a, weight);
		}
	
		public int compareTo(Spm sp) {
	
			return this.weight.compareTo(sp.weight);
		}
	
	}
	
	public static Double minValue = Double.MAX_VALUE;
	public static Map<CursoProblem,Spm> memory;
	public static CursoProblem start;
	
	public static SolucionCursos search() {
	CursoPDR.minValue = Double.MAX_VALUE;
	Set<Integer> initialThemes =
	CursoProblem.initial().remaining();
	Set<Integer> initialCentres =
			CursoProblem.initial().centros();
	CursoPDR.start = CursoProblem.of(0, initialThemes,
	initialCentres);
	CursoPDR.memory = new HashMap<>();
	pdr_search(start,0.,memory);
	return CursoPDR.getSol();
	}
	
	private static Spm pdr_search(CursoProblem prob, Double
	acumulado, Map<CursoProblem, Spm> memoria) {
		Spm r;
	
	Boolean esTerminal= prob.index()==DatosEjercicioCursos.getNumeroCursos();
	Boolean esSolucion= prob.remaining().isEmpty();
	
	if(memoria.containsKey(prob)) {
	r = memoria.get(prob);
	
	}else if( esSolucion && esTerminal) {
	 r = Spm.of(null, 0.);
	 memoria.put(prob, r);
	 	if(acumulado < minValue) {
	 		minValue = acumulado;
	}
	
	
	} else {
	List<Spm> soluciones = new ArrayList<>();
	for(Integer a:prob.actions()) {
		CursoProblem vecino = prob.neighbor(a);
	Double ac = acumulado+a*DatosEjercicioCursos.getPrecioCurso(prob.index());
	Spm s = pdr_search(vecino,ac,memoria);
	if(s!=null) {
	Spm sp =
	Spm.of(a,s.weight()+a*DatosEjercicioCursos.getPrecioCurso(prob.index()
	));
	soluciones.add(sp);
	}
	}
	r = soluciones.stream().filter(s->s !=
	null).min(Comparator.naturalOrder()).orElse(null);
	memory.put(prob,r);
	}
	return r;
	}
	
	private static SolucionCursos getSol() {
	List<Integer> acciones = List2.empty();
	CursoProblem v = CursoPDR.start;
	Spm s = CursoPDR.memory.get(v);
	while(s.a() != null) {
	acciones.add(s.a());
	v = v.neighbor(s.a());
	s = CursoPDR.memory.get(v);
	}
	return SolucionCursos.of(acciones);
	
	
	}
	
	
}
