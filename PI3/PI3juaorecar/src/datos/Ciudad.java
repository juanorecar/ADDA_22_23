package datos;

public record Ciudad(String nombre, Integer puntuacion) {
	public static Ciudad ofFormat(String[] formato) {
		String[] trozoP = formato[1].split("p");
		String nombre = formato[0];
		Integer puntuacion = Integer.valueOf(trozoP[0]);
		return new Ciudad(nombre,puntuacion);
	}
	
	public static Ciudad of(String nombre, Integer puntuacion) {
		return new Ciudad(nombre,puntuacion);
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
