package ejemplos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;
import us.lsi.tiposrecursivos.Tree.TEmpty;

public class Ejemplo5 {
	
	/*	ENUNCIADO
	 * 
	 */
	
	public static <E> List<Boolean> solucion_recursiva(Tree<E> tree, Predicate<E> pred){
		return recursivo(tree, pred,0, new ArrayList<>());
	}
	
	public static <E> List<Boolean> recursivo(Tree<E> tree, Predicate<E> pred, Integer nivel,
			List<Boolean>res){
		if(res.size() <= nivel)res.add(true);
		return switch(tree) {
		case TEmpty<E> t -> res;
		case TLeaf<E> t-> {
		Boolean r = pred.test(t.label()) && res.get(nivel);
		  res.set(nivel, r);
		  yield res;
		}
		case TNary<E> t ->{
		  Boolean r = pred.test(t.label()) && res.get(nivel);
		  res.set(nivel, r);
		  t.elements().forEach(child -> recursivo(child, pred, nivel+1, res));
		  yield res;
		}
		};
	}
	
}

