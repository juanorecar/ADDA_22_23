package datos;

public record Persona(Integer id, String nombre, Integer anyo_nacimiento, String ciudad_nacimiento) {
	
	private static int num =0;
	
	public static Persona ofFormat(String[] formato) {
		Integer id = num;
		String nombre = formato[1];
		Integer anyo_nacimiento = Integer.parseInt(formato[2]);
		String ciudad_nacimiento = formato[3];
		return new Persona(id, nombre, anyo_nacimiento, ciudad_nacimiento);
	}
	
	public static Persona of(Integer id, String nombre, Integer anyo_nacimiento, String ciudad_nacimiento) {
		return new Persona(id, nombre, anyo_nacimiento, ciudad_nacimiento);
	}
	
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", anyo_nacimiento=" + anyo_nacimiento
				+ ", ciudad_nacimiento=" + ciudad_nacimiento + "]";
	}
	
}
