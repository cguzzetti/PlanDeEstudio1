package BackEnd;

import java.util.*;

public class PlanAcademico extends Plan {

	public PlanAcademico (int creditosMax, List<Cuatrimestre> carrera, Set<Materia> materiasPorAprobar,int creditosAprobados) {
		super(creditosMax, carrera, materiasPorAprobar, creditosAprobados);
	}

	public void construirPlan () throws NoTimeException{

		System.out.println("____________________PLAN ACADEMICO___________________");

		TreeSet<Materia> set = new TreeSet<Materia>( new Comparator<Materia>() {
			@Override
			public int compare (Materia m1, Materia m2) {
				
				if (m1.equals(m2))
					return 0;

				int difCreditosRequeridos = m1.obtenerCreditosRequeridos() - m2.obtenerCreditosRequeridos();

				if (difCreditosRequeridos == 0){

					int difCuatrimestres = m1.obtenerCuatrimestre().compareTo(m2.obtenerCuatrimestre());

					if (difCuatrimestres == 0) {
						int difPrioridad = m2.obtenerPrioridad() - m1.obtenerPrioridad();
						if ( difPrioridad == 0) {
							List<Materia> array = new ArrayList<>();
							array.add(m1);
							array.add(m2);
							Collections.shuffle(array);
							if (array.remove(0).equals(m1)) {
								return -1;
							}
							return 1;
						}
						return difPrioridad;
					}

					return difCuatrimestres;
				}

				return difCreditosRequeridos;
			}
		});
		
		super.construirPlan(set);
	}
}