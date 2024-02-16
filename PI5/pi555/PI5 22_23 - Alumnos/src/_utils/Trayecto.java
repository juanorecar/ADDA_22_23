package _utils;

public record Trayecto(int id, Double distancia) {
	
	public static int cont;
	
	public static Trayecto of(Double distancia) {
		Integer id = cont;
		cont++;
		return new Trayecto(id, distancia);
	}
	
	public static Trayecto ofFormat(String[] forma) {
		Double distancia = Double.valueOf(forma[2].trim());
		return of(distancia);
	}

	@Override
	public String toString() {
		return "Conexion [id=" + id + ", dist=" + distancia + "]";
	}
	
	

}
