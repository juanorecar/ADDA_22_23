package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import _datos.DatosEjercicioInvestigadores;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record InvestigadorVertex(Integer z, List<Integer> days, List<List<Integer>> distribution) 
	implements VirtualVertex<InvestigadorVertex, InvestigadorEdge, Integer>{

	
	public static InvestigadorVertex of(Integer z, List<Integer> d, List<List<Integer>> dist) {
		return new InvestigadorVertex(z, d, dist);			
	}
	
	public static InvestigadorVertex initial() {
		List<Integer> dias = new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> reparto = new ArrayList<>();
		for(int i = 0; i < DatosEjercicioInvestigadores.getNumeroInvestigadores(); i++) {
			dias.add(DatosEjercicioInvestigadores.diasDisponibles(i));
		}
		for(int j = 0; j < DatosEjercicioInvestigadores.getNumeroTrabajos(); j++) {
			for(int k = 0; k < DatosEjercicioInvestigadores.getNumeroInvestigadores(); k++) {
				reparto.add(DatosEjercicioInvestigadores.diasNecesarios(j, k));
			}
		res.add(reparto);
		}
		
		return of(0, dias, res);
		}
	
	public static Predicate<InvestigadorVertex> goal(){
		return v -> v.z() == DatosEjercicioInvestigadores.getNumeroTrabajos();
	}
	
	@Override
	public List<Integer> actions() {
		List<Integer> alternativas = List2.empty();
		
		if(z < (DatosEjercicioInvestigadores.getNumeroTrabajos() * DatosEjercicioInvestigadores.getNumeroInvestigadores())) {
			
		}
		return alternativas;
	}

	@Override
	public InvestigadorVertex neighbor(Integer a) {
		
		Integer m = DatosEjercicioInvestigadores.getNumeroTrabajos();
		Integer i = z / m;
		Integer j = z % m;
		
		
		
		
		return of((m * i) + j, null, null);
	}

	@Override
	public InvestigadorEdge edge(Integer a) {
		return InvestigadorEdge.of(this, neighbor(a), a);
	}

	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
