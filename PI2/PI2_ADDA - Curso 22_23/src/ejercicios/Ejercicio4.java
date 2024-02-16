package ejercicios;


import java.util.List;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejercicio4 {
	
	
	public static record Tupla(Boolean b, Integer n) {
		public static Tupla of(Boolean b, Integer n) {
			return new Tupla(b,n);
		}
	}
	
	
	public static Boolean solucion_recursivaBinaria(BinaryTree<String> tree) {
		return recursivoBinario(tree).b();
	}
	
	private static Boolean esVocal(Character letra) {
	    return "aeiou".contains(String.valueOf(letra).toLowerCase());
	}
	
	private static Integer cuentaVocales(String s) {
		int vocales = 0;
		for (int i=0; i < s.length(); i++) {
		    char letraActual = s.charAt(i);
		    if (esVocal(letraActual)) vocales++;
		}
		return vocales;
	}
	
	private static Tupla recursivoBinario(BinaryTree<String> tree) {
		
		return switch(tree) {
		
		case BEmpty<String> t -> Tupla.of(true, 0);
		
		case BLeaf<String> t -> {
			Boolean res = true;
			Integer sumaP = cuentaVocales(t.label());
			yield Tupla.of(res, sumaP);
		}
		case BTree<String> t->{
			Boolean res;
			Tupla l = recursivoBinario(t.left());
			Tupla r = recursivoBinario(t.right());
			Integer sumaL = l.n();
			Integer sumaR = r.n();
			Integer sumaP = cuentaVocales(t.label());
			Integer sumaTotal = sumaL + sumaR + sumaP;
			if(sumaL==sumaR && l.b()==true && r.b()==true) {
				res = true;
			}else {
				res = false;
			}
			yield Tupla.of(res, sumaTotal);
		}
	};
}
	
	/*
	 * 
	 * Guardar en una lista de tuplas el resultado de todas las llamadas recursivas
	 * 
	 * comprobar si los primeros elementos son true y sumar los segundos
	 * 
	 */
	
	public static Boolean solucion_recursivaNaria(Tree<String> tree) {
		return recursivoNario(tree).b();
	}
	
	public static Tupla recursivoNario(Tree<String> tree) {
		
		return switch(tree) {
		
		case TEmpty<String> t -> Tupla.of(true, 0);
		
		case TLeaf<String> t -> {
			Boolean res = true;
			Integer sP = cuentaVocales(t.label());
			yield Tupla.of(res, sP);
		}
		
		case TNary<String> t -> {
			Boolean res = true;
			List<Tupla> ls = t.elements().stream().map(x->recursivoNario(x)).toList();
			Integer s = cuentaVocales(t.label());
			Integer numeroVocales  = ls.get(0).n();
			for (int i = 0; i<ls.size(); i++) {
				s = ls.get(i).n() + s;
				if(ls.get(i).b() == true && numeroVocales.equals(ls.get(i).n())) {
					res = true;
				}else {
					res = false;
				}
			}
			yield Tupla.of(res, s);
		}
		
		};
	}	
}
