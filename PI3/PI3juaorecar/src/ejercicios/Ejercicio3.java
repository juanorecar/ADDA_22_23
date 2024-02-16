package ejercicios;

import java.util.Map;
import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.graph.DefaultEdge;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Style;

public class Ejercicio3 {

	public static void apartadoA(Graph<String, DefaultEdge> gf, String file) {
		var c = new GreedyColoring<>(gf);
		Coloring<String> res = c.getColoring();
		System.out.println("Franjas horarias necesarias: " + res.getNumberColors());
		
		System.out.println("Composicion de las actividades");
		var actividades = res.getColorClasses();
		for(int i=0; i<actividades.size(); i++) {
			System.out.println("Franja horaria numero "+(i+1)+": " + actividades.get(i));
		}
	}
	
	
	public static void apartadoB(Graph<String, DefaultEdge> gf, String file, String nombreVista){
		
		var c = new GreedyColoring<>(gf);
		Coloring<String> res = c.getColoring();
		Map<String, Integer> map = res.getColors(); 
		String fileRes = "resultados/ejercicio3/" + file + nombreVista + ".gv";
		GraphColors.toDot(gf, fileRes, 
				v->v.toString(), 
				e->"",
				v -> GraphColors.color(map.get(v)),
				e -> GraphColors.style(Style.solid));
		
		System.out.println(file + "C.gv generado en " + "resultados/ejercicio3");
	}
	
}
