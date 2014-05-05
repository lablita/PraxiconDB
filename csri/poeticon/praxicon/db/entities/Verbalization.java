/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csri.poeticon.praxicon.db.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author dmavroeidis
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verbalization", namespace = "http://www.csri.gr/verbalization")
@Entity
@Table(name = "Verbalizations")
public class Verbalization implements Serializable {

    public static enum allowed {

        YES, NO, UNKNOWN;

        @Override
        public String toString() {
            return this.name();
        }
    }

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "CUST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUST_SEQ")
    @Column(name = "VerbalizationId")
    private Long Id;

    @Column(name = "Allowed")
    @Enumerated(EnumType.STRING)
    private allowed Allowed;

    @OneToOne(cascade = CascadeType.ALL)
    private Concept Concept;

    @OneToOne(cascade = CascadeType.ALL)
    private LanguageRepresentation LanguageRepresentation;

    @OneToOne(cascade = CascadeType.ALL)
    private Relation Relation;

    @XmlAttribute
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    //@XmlElement(name="verbalization_allowed")
    public allowed getVerbalizationAllowed() {
        return Allowed;
    }

    public void setAllowed(allowed allowed) {
        this.Allowed = allowed;
    }

    //@XmlElement(name="concept")
    public Concept getConcept() {
        return Concept;
    }

    public void setConcept(Concept concept) {
        this.Concept = concept;
    }

    //@XmlElement(name="language_representation")
    public LanguageRepresentation getLanguageRepresentation() {
        return LanguageRepresentation;
    }

    public void setLanguageRepresentation(
            LanguageRepresentation language_representation) {
        this.LanguageRepresentation = language_representation;
    }

    //@XmlElement(name="relation")
    public Relation getRelation() {
        return Relation;
    }

    public void setRelation(Relation relation) {
        this.Relation = relation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Verbalization)) {
            return false;
        }
        Verbalization other = (Verbalization)object;
        if ((this.Id == null && other.Id != null) ||
                (this.Id != null && !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "csri.poeticon.praxicon.db.entities.Verbalization[id=" + Id +
                "]";
    }
}
