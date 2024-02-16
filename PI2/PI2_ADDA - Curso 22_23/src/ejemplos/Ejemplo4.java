package ejemplos;

import java.util.List;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;

public class Ejemplo4 {
	
	public static Boolean solucion_recursiva(BinaryTree<Character> tree,
			List<Character> ls) {
		return recursivo(tree, ls, 0);
	}
	
	public static Boolean recursivo(BinaryTree<Character> tree, List<Character> ls, Integer i) {
		Integer n = ls.size();
		return switch(tree) {
		case BEmpty<Character> t-> false;
		case BLeaf<Character> t-> 
		n - i == 1 && ls.get(i).equals(t.label());
		case BTree<Character> t->
			ls.get(i).equals(t.label()) 
			&& (recursivo(t.left(), ls, i+1)
					|| recursivo(t.right(), ls, i+1));
		};
	}
}
