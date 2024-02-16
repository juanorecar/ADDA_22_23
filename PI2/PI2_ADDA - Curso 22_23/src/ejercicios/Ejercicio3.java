package ejercicios;
import java.util.ArrayList;
import java.util.List;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejercicio3 {
	
	public static List<String> solucion_recursivaBinario(BinaryTree<Character> tree,
			Character c) {
		return recursivoBinario(tree, c, new ArrayList<>(), "");
	}
	public static List<String> recursivoBinario(BinaryTree<Character> tree, Character c, List<String> res, String cadena){

		return switch(tree) {
		case BEmpty<Character> t ->{yield res;}
		
		case BLeaf<Character> t ->{
			
			String s = cadena + t.label();
			
			if(!s.contains(String.valueOf(c))) {
				res.add(s);
			}
			yield res;
			}
		case BTree<Character> t -> {
			
			String s = cadena + t.label();
			
			if(!s.contains(String.valueOf(c))) {
				res = recursivoBinario(t.left(), c, res, s);
				res = recursivoBinario(t.right(), c, res, s);
			}
			yield res;
			}		
	};
		
  }
	
	public static List<String> solucion_recursivaNario(Tree<Character> tree,
			Character c) {
		return recursivoNario(tree, c,"", new ArrayList<>());
	}
	
	public static List<String> recursivoNario(Tree<Character> tree, Character c,String cadena, List<String> res){
		return switch(tree) {
		case TEmpty<Character> t-> res;
		
		case TLeaf<Character> t->{
			String ac = cadena + t.label();
			if(!ac.contains(String.valueOf(c))) {
				res.add(ac);
			}
			yield res;
		}
		
		case TNary<Character> t->{
			String ac = cadena + t.label();
			if(!ac.contains(String.valueOf(c))) {
				for(Tree<Character> child:t.elements()) {
					res = recursivoNario(child, c, ac, res);
				}
			}
			yield res;
		}
		};
	}
	
}
