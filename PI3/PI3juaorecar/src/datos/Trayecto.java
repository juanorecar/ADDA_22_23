package datos;

public record Trayecto(Integer id, Integer precio, Integer duracion) {
	
private static int num =0;
	
	public static Trayecto ofFormat(String[] formato) {
		String[] trozoP = formato[2].split("euros");
		String[] trozoD = formato[3].split("min");
		
		Integer precio = Integer.valueOf(trozoD[0]);
		Integer duracion = Integer.valueOf(trozoP[0]);	
		Integer id = num;
		num++;
		return new Trayecto(id, precio, duracion);
	}
	
	public static Trayecto of(Integer precio, Integer duracion) {
		Integer precio1 = precio;
		Integer duracion1 = duracion;		
		Integer id = num;
		num++;
		return new Trayecto(id, precio1, duracion1);
	}

	//@Override
//	public String toString() {
//		String nn = this.nombre==null?"":this.nombre+",";
//		return nn+this.km+")";
//	}
	
	
}
