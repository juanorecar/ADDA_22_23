package ejercicios;

import java.util.HashMap;
import java.util.Map;
import us.lsi.common.IntTrio;

public class Ejercicio4 {
	
	public static String ejercicio4RecSinMemoria(Integer a, Integer b, Integer c) {
		String r = null;
		if(a<2 && b<=2 || c<2) {
			r = String.format("(%d+%d+%d)",a,b,c );
		}else if(a<3 || b<3 && c<=3) {
			r = String.format("(%d-%d-%d)", c,b,a);
		}else if(b%a == 0 && (a%2==0 || b%3==0)) {
			r = "(" + ejercicio4RecSinMemoria(a-1, b/a, c-1) + "*" + ejercicio4RecSinMemoria(a/3, b/2, c/2) + ")";
		}else {
			r = "(" + ejercicio4RecSinMemoria(a/2, b-2, c/2) + "/" + ejercicio4RecSinMemoria(a/3, b-1, c/3) + ")";
		}
		return r;
	}
	
	
	
	public static String ejercicio4ConMemoria(Integer a, Integer b, Integer c) {
		return rec4Mem(a, b, c, new HashMap<>());
	}
	
	private static String rec4Mem(Integer a, Integer b, Integer c, Map<IntTrio, String> m) {
		String r = null;
		IntTrio key = IntTrio.of(a, b, c);
		if(m.containsKey(key)) {
			r = m.get(key);
		}else {
			if(a<2 && b<=2 || c<2) {
				r = String.format("(%d+%d+%d)",a,b,c );
			}else if(a<3 || b<3 && c<=3) {
				r = String.format("(%d-%d-%d)", c,b,a);
			}else if(b%a == 0 && (a%2==0 || b%3==0)) {
				r = "(" + ejercicio4RecSinMemoria(a-1, b/a, c-1) + "*" + ejercicio4RecSinMemoria(a/3, b/2, c/2) + ")";
			}else {
				r = "(" + ejercicio4RecSinMemoria(a/2, b-2, c/2) + "/" + ejercicio4RecSinMemoria(a/3, b-1, c/3) + ")";
			}
			m.put(key, r);
		}
		return r;
	}
	
	
	public static String ejercicio4Iterativo(Integer a, Integer b, Integer c) {
		Map<IntTrio, String> m = new HashMap<>();
		String r = "";
		for(int i=0; i<=a; i++) {
			for(int j=0; j<=b; j++) {
				for(int k=0; k<=c; k++) {
					if(i<2 && j<=2 || k<2) {
						r = String.format("(%d+%d+%d)",i,j,k );
					}else if(i<3 || j<3 && k<=3) {
						r = String.format("(%d-%d-%d)", k,j,i);
					}else if(j%i == 0 && (i%2==0 || j%3==0)) {
						r = "(" + m.get(IntTrio.of(i-1, j/i, k-1)) + "*" + m.get(IntTrio.of(i/3, j/2, k/2)) + ")";
					}else {
						r = "(" + m.get(IntTrio.of(i/2, j-2, k/2)) + "/" + m.get(IntTrio.of(i/3, j-1, k/3)) + ")";
					}
					m.put(IntTrio.of(i,j,k), r);
				}
			}
		}
		return m.get(IntTrio.of(a,b,c));

	}
	
}
