package uo.ri.cws.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

@Entity
@Table(name = "TSubtitutions",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "SPAREPART_ID", "INTERVENTION_ID"
                })
        }

)
public class Substitution extends BaseEntity {
    // natural attributes
    @Basic(optional = false)
    private int quantity;

    // accidental attributes
    @ManyToOne
    private SparePart sparePart;
    @ManyToOne
    private Intervention intervention;

    Substitution() {
    }

    public Substitution(SparePart sparePart, Intervention intervention, int quantity) {
        ArgumentChecks.isNotNull(sparePart);
        ArgumentChecks.isNotNull(intervention);
        ArgumentChecks.isTrue(quantity >= 0);

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
