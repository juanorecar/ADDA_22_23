package _soluciones;

import java.util.List;
import org.jgrapht.GraphPath;
import _datos.DatosEjercicioCafes;
import ejercicio1.CafeEdge;
import ejercicio1.CafeVertex;

public class SolucionCafe{
	
	private double beneficioTotal;
	private List<Integer> solucion;
	
	public static SolucionCafe of(List<Integer> acciones) {
		return new SolucionCafe(acciones);
	}
	
	public static SolucionCafe of(GraphPath<CafeVertex, CafeEdge> path) {
		List<Integer> ls = path.getEdgeList().stream().map(e -> e.action() + 0).toList();
		SolucionCafe res = of(ls);
		res.solucion = ls;
		return res;
	}

	private SolucionCafe(List<Integer> value) {
		beneficioTotal = 0.0;
		solucion = value;
		for(int i = 0; i < value.size(); i++) {
			beneficioTotal += DatosEjercicioCafes.getBeneficio(i)*value.get(i);
		}
	}
	
	public String toString() {
		System.out.println("Variedades de cafes seleccionadas");
		for(int i = 0; i < solucion.size(); i++) {
			System.out.println(String.format("P%02d: %s Kgs", i+1, solucion.get(i)));
		}
		System.out.println(String.format("Beneficio: %s", beneficioTotal));
		return "------------------------------";
	}
}
