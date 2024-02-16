package records;

import java.util.*;
import java.util.stream.Collectors;

public record Tupla4(Integer a, Integer b, Integer c) {
    
    public static Tupla4 of(Integer a, Integer b, Integer c) {
        return new Tupla4(a,b,c);
    }
    
    public static Tupla4 of2(String s0, String s1, String s2) {
    	return new Tupla4(Integer.parseInt(s0),
    					  Integer.parseInt(s1),
    					  Integer.parseInt(s2)
    					  );
    }
    
    public static Tupla4 parse(String linea) {
    	List<String> aux = Arrays.stream(linea.split(",")).collect(Collectors.toList());
    	return Tupla4.of2(aux.get(0), aux.get(1), aux.get(2));
    }
    
}
