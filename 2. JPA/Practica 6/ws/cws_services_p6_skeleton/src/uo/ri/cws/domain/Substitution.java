package uo.ri.cws.domain;

import uo.ri.util.assertion.ArgumentChecks;

public class Substitution {
	// natural attributes
	private int quantity;

	// accidental attributes
	private SparePart sparePart;
	private Intervention intervention;

	

	public Substitution(SparePart sparePart, Intervention intervention, int quantity) {
		ArgumentChecks.isNotNull(sparePart);
		ArgumentChecks.isNotNull(intervention);
		ArgumentChecks.isTrue(quantity>=0);
		
		this.sparePart = sparePart;
		this.intervention = intervention;
		this.quantity = quantity;	
		Associations.Substitute.link(sparePart, this, intervention);
	}

	void _setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}

	void _setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	public SparePart getSparePart() {
		return sparePart;
	}

	public void setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	
}
