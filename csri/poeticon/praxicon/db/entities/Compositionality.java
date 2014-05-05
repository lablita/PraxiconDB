/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csri.poeticon.praxicon.db.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@XmlType(name = "compositionality",
        namespace = "http://www.csri.gr/compositionality")
@Entity
@Table(name = "Compositionality")
public class Compositionality implements Serializable {

    public static enum CompositionalityType {

        MULTIWORD, COMPOSITE_WORD, UNKNOWN;

        @Override
        public String toString() {
            return this.name();
        }
    }

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "CUST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUST_SEQ")
    @Column(name = "CompositionalityId")
    private Long Id;

    @Column(name = "CompositionalityType")
    @Enumerated(EnumType.STRING)
    private CompositionalityType CompositionalityType;

    // Foreign keys
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Compositionality")
    private List<LanguageRepresentation> LanguageRepresentation;

    @XmlAttribute
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    //@XmlElement(name="compositionality_type")
    public CompositionalityType getCompositionalityType() {
        return CompositionalityType;
    }

    public void setCompositionalityType(
            CompositionalityType compositionality_type) {
        this.CompositionalityType = compositionality_type;
    }

    //@XmlElement(name="language_representation")
    public List<LanguageRepresentation> getLanguageRepresentation() {
        return LanguageRepresentation;
    }

    public void setLanguageRepresentation(
            List<LanguageRepresentation> language_representation) {
        this.LanguageRepresentation = language_representation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - method won't work in case the id fields are not set
        if (!(object instanceof Compositionality)) {
            return false;
        }
        Compositionality other = (Compositionality)object;
        if ((this.Id == null && other.Id != null) ||
                (this.Id != null && !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "csri.poeticon.praxicon.db.entities.Compositionality[id=" +
                Id + "]";
    }
}
