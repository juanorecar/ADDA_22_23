package ejemplo1;

import java.util.List;

import _datos.DatosMulticonjunto;
import _soluciones.SolucionMulticonjunto;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeMulticonjuntoAG 
implements ValuesInRangeData<Integer, SolucionMulticonjunto>{

	
	public InRangeMulticonjuntoAG(String linea) {
		DatosMulticonjunto.iniDatos(linea);
	}
	
	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return DatosMulticonjunto.getNumElementos();
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		// TODO Auto-generated method stub
		double goal = 0;
		double sum = 0;
		double error = 0;
		for(int i = 0; i<size(); i++) {
			if(value.get(i)>0) {
				goal += value.get(i);
				sum += DatosMulticonjunto.getElemento(i)*value.get(i);
			}
		}
		error = Math.abs(sum - DatosMulticonjunto.getSuma());
		return -goal-10000*error;
	}

	@Override
	public SolucionMulticonjunto solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return SolucionMulticonjunto.of_Range(value);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosMulticonjunto.getMultiplicidad(i) + 1;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
