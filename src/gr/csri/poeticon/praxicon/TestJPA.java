package gr.csri.poeticon.praxicon;

import gr.csri.poeticon.praxicon.db.dao.ConceptDao;
import gr.csri.poeticon.praxicon.db.dao.LanguageRepresentationDao;
import gr.csri.poeticon.praxicon.db.dao.RelationDao;
import gr.csri.poeticon.praxicon.db.dao.RelationSetDao;
import gr.csri.poeticon.praxicon.db.dao.VisualRepresentationDao;
import gr.csri.poeticon.praxicon.db.dao.implSQL.ConceptDaoImpl;
import gr.csri.poeticon.praxicon.db.dao.implSQL.LanguageRepresentationDaoImpl;
import gr.csri.poeticon.praxicon.db.dao.implSQL.RelationDaoImpl;
import gr.csri.poeticon.praxicon.db.dao.implSQL.RelationSetDaoImpl;
import gr.csri.poeticon.praxicon.db.dao.implSQL.VisualRepresentationDaoImpl;
import gr.csri.poeticon.praxicon.db.entities.Compositionality;
import gr.csri.poeticon.praxicon.db.entities.Concept;
import gr.csri.poeticon.praxicon.db.entities.Constituent;
import gr.csri.poeticon.praxicon.db.entities.LanguageRepresentation;
import gr.csri.poeticon.praxicon.db.entities.Relation;
import gr.csri.poeticon.praxicon.db.entities.RelationArgument;
import gr.csri.poeticon.praxicon.db.entities.RelationSet;
import gr.csri.poeticon.praxicon.db.entities.RelationSet_Relation;
import gr.csri.poeticon.praxicon.db.entities.RelationType;
import gr.csri.poeticon.praxicon.db.entities.VisualRepresentation;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.BeforeClass;

/**
 *
 * @author dmavroeidis
 *
 * An XML of how we can create new concepts in the db, without using XMLs. Some
 * parts are obsolete
 */
public class TestJPA {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private static final String PERSISTENCE_UNIT_NAME = "PraxiconDBPU";
    private EntityManagerFactory factory;

    @SuppressWarnings("empty-statement")
    public static void main(String args[]) {
        /*
         * Create Concept1
         */

        Concept concept1 = new Concept();
        concept1.setName("concept1");
        concept1.setConceptType(Concept.type.ABSTRACT);
        concept1.setStatus(Concept.status.CONSTANT);
        concept1.setUniqueInstance(Concept.unique_instance.YES);
        concept1.setSource("myMind");
        System.out.println(concept1.getName());

        /*
         * Create Concept2
         */
        Concept concept2 = new Concept();
        concept2.setName("concept2");
        concept2.setConceptType(Concept.type.MOVEMENT);
        concept2.setStatus(Concept.status.VARIABLE);
        concept2.setUniqueInstance(Concept.unique_instance.NO);
        concept2.setSource("myMind2");
        System.out.println(concept2.getName());

        /*
         * Add Language Representations to the concepts
         */
        List<LanguageRepresentation> languageRepresentations1;
        languageRepresentations1 = new ArrayList<>();

        LanguageRepresentation languageRepresentation1 =
                new LanguageRepresentation();
        languageRepresentation1.setLanguage(LanguageRepresentation.language.EN);
        languageRepresentation1.setText("LR1");
        languageRepresentation1.setPartOfSpeech(
                LanguageRepresentation.part_of_speech.NOUN);
        languageRepresentation1.setPragmaticStatus(
                LanguageRepresentation.pragmatic_status.FIGURATIVE);

        LanguageRepresentation languageRepresentation2 =
                new LanguageRepresentation();
        languageRepresentation2.setLanguage(LanguageRepresentation.language.EN);
        languageRepresentation2.setText("LR2");
        languageRepresentation2.setPartOfSpeech(
                LanguageRepresentation.part_of_speech.VERB);
        languageRepresentation2.setPragmaticStatus(
                LanguageRepresentation.pragmatic_status.FIGURATIVE);

        LanguageRepresentation languageRepresentation3 =
                new LanguageRepresentation();
        languageRepresentation3.setLanguage(LanguageRepresentation.language.EL);
        languageRepresentation3.setText("LR3");
        languageRepresentation3.setPartOfSpeech(
                LanguageRepresentation.part_of_speech.ADVERB);
        languageRepresentation3.setPragmaticStatus(
                LanguageRepresentation.pragmatic_status.LITERAL);

        concept1.addLanguageRepresentation(languageRepresentation1, false);
        concept1.addLanguageRepresentation(languageRepresentation2, true);
        concept2.addLanguageRepresentation(languageRepresentation3, true);

        /* 
         * Add Visual Representations to the concepts
         */
        VisualRepresentation visualRepresentation1 = new VisualRepresentation();
        visualRepresentation1.setName("VR1");
        visualRepresentation1.
                setMediaType(VisualRepresentation.media_type.IMAGE);
        URI new_uri = null;
        try {
            new_uri = new URI(
                    "http://iphonewallpaperhds.com/images/1810-lucky.jpg");
        } catch (URISyntaxException error_uri) {
            System.out.println("caught URI error");
            System.out.println(Arrays.toString(error_uri.getStackTrace()));
        };
        visualRepresentation1.setURI(new_uri);
        concept1.addVisualRepresentation(visualRepresentation1);

        VisualRepresentation visualRepresentation2 = new VisualRepresentation();
        visualRepresentation2.setName("VR2");
        visualRepresentation2.
                setMediaType(VisualRepresentation.media_type.IMAGE);
        try {
            new_uri = new URI(
                    "http://www.picgifs.com/clip-art/cartoons/lucky-luke/" +
                    "clip-art-lucky-luke-240603.jpg");
        } catch (URISyntaxException error_uri) {
            System.out.println("caught URI error");
            System.out.println(Arrays.toString(error_uri.getStackTrace()));
        };
        visualRepresentation2.setURI(new_uri);
        concept2.addVisualRepresentation(visualRepresentation2);

        /* 
         * Create a compositionality entry
         */
        Compositionality compositionality = new Compositionality();
        languageRepresentation1.setCompositional(
                LanguageRepresentation.is_compositional.YES);
        Constituent constituent1 = new Constituent();
        Constituent constituent2 = new Constituent();
        constituent1.setLanguageRepresentation(languageRepresentation1);
        constituent1.setOrder((short)0);
        constituent2.setLanguageRepresentation(languageRepresentation2);
        constituent2.setOrder((short)1);

        List<Constituent> constituents = new ArrayList();
        constituents.add(constituent2);
        constituents.add(constituent1);

        languageRepresentation1.setConstituents(constituents);

        /* 
         * Create a relation with concept1 as subject and concept2 as object.
         */
        Relation relation1 = new Relation();
        RelationType relationType1 = new RelationType();
        relationType1.
                setForwardName(RelationType.relation_name_forward.HAS_PART);
        relationType1.setBackwardName(
                RelationType.relation_name_backward.PART_OF);
        relation1.setType(relationType1);

        RelationArgument relationArgument1 = new RelationArgument(concept1);
        RelationArgument relationArgument2 = new RelationArgument(concept2);
        System.out.println("CONCEPT 1: " + concept1.toString());
        System.out.println("CONCEPT 2: " + concept2.toString());
        System.out.println("RELATION ARGUMENT 1: " + relationArgument1.
                toString());
        System.out.println("RELATION ARGUMENT 2: " + relationArgument2.
                toString());
        relation1.setObject(relationArgument1);
        relation1.setSubject(relationArgument2);
        relation1.setDerivation(Relation.derivation_supported.YES);

        /* 
         * Create a relation with concept2 as subject and concept1 as object.
         */
        Relation relation2 = new Relation();
        RelationType relationType2 = new RelationType();
        relationType2.setForwardName(
                RelationType.relation_name_forward.HAS_PARTIAL_INSTANCE);
        relationType2.setBackwardName(
                RelationType.relation_name_backward.PART_OF);
        relation2.setType(relationType2);

        relation2.setSubject(relationArgument2);
        relation2.setObject(relationArgument1);
        relation2.setDerivation(Relation.derivation_supported.NO);


        /* 
         * Create a RelationSet_Relation structure to store relations
         */
        List<RelationSet_Relation> relationSetRelationList = new ArrayList<>();
        RelationSet_Relation relationSetRelation1 = new RelationSet_Relation();
        RelationSet_Relation relationSetRelation2 = new RelationSet_Relation();

        /* 
         * Create an ordered relation set with relation1 and relation2 
         * as members.
         */
        // TODO: Must fix the way relations become part of relation sets!!!
        relationSetRelationList.add(relationSetRelation2);
        relationSetRelationList.add(relationSetRelation1);
        RelationSet relationSet1 = new RelationSet("relationSetName1",
                relationSetRelationList, RelationSet.inherent.YES,
                languageRepresentations1);

//        relationSet1.addRelation(relation1, 1);
//        relationSet1.addRelation(relation2, 2);

        /* 
         * Create an unordered relation set with relation1 and relation2 
         * as members.
         */
//        RelationSet relationSet2 = new RelationSet();
//        relationSet2.addRelation(relation2);
//        relationSet2.addRelation(relation1);
        ConceptDao newConceptDao = new ConceptDaoImpl();
        LanguageRepresentationDao newLanguageRepresentationDao =
                new LanguageRepresentationDaoImpl();
        VisualRepresentationDao newVisualRepresentationDao =
                new VisualRepresentationDaoImpl();
        RelationDao newRelationDao = new RelationDaoImpl();
        RelationSetDao newRelationSetDao = new RelationSetDaoImpl();

        try {
            newConceptDao.persist(concept1);
            newConceptDao.persist(concept2);
            newLanguageRepresentationDao.persist(languageRepresentation3);
            newLanguageRepresentationDao.persist(languageRepresentation2);
            newLanguageRepresentationDao.persist(languageRepresentation1);
            newVisualRepresentationDao.persist(visualRepresentation2);
            newVisualRepresentationDao.persist(visualRepresentation1);
            newRelationDao.persist(relation2);
            newRelationDao.persist(relation1);
            newRelationSetDao.persist(relationSet1);

        } catch (javax.validation.ConstraintViolationException ee) {
            System.out.println("Size constraint violated.");
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<Concept>> violations = validator.validate(
                concept1);
        Set<ConstraintViolation<Concept>> violation = validator.validate(
                concept2);

        System.out.println(concept2.getName());
//        constraintViolations = validator.validate( concept1 );
//        assertEquals( 1, constraintViolations.size() );
//        assertEquals( "may not be null", constraintViolations.iterator().next().getMessage() );

    }

    public void persist(Object object) {
        EntityManagerFactory emf = javax.persistence.Persistence.
                createEntityManagerFactory("PraxiconDBPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("transaction error");
        } finally {
            em.flush();
            em.close();

        }
    }

//    public void testViolations()
//    {
//        Concept con = new Concept();
//    }
}
