package beanDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeanGraficoSalarioUser implements Serializable {

	private static final long serialVersionUID = -2603624244595192536L;
	
	private List<String> perfils = new ArrayList<String>();
	private List<Double> salarios = new ArrayList<Double>();
	
	public List<String> getPerfils() {
		return perfils;
	}
	public void setPerfils(List<String> perfils) {
		this.perfils = perfils;
	}
	public List<Double> getSalarios() {
		return salarios;
	}
	public void setSalarios(List<Double> salarios) {
		this.salarios = salarios;
	}

}
