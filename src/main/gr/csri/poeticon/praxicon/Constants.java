package gr.csri.poeticon.praxicon;

import gr.csri.poeticon.praxicon.db.entities.Concept;
import gr.csri.poeticon.praxicon.db.entities.Concepts;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties; 

/**
 * All the constants and globals of the project. All properties taken from
 * "settings.properties" file.
 *
 * @author Dimitris Mavroeidis
 * @date 18/09/2013
 *
 */
public class Constants {

    // DB settings constants
    public static String dbHost;
    public static String dbPort;
    public static String dbName;
    public static String dbUser;
    public static String dbPass;

    // Path constants
    /**
     * The temporary path
     */
    public static String tmpPath;
    
    /**
     * The path to the image resources
     */
    public static String imagePath;

    /**
     * The path to the LabelMe image resources
     */
    public static String imagePathLabelMe;

    /**
     * The path to the ImageNet image resources
     */
    public static String imagePathImageNet;

    /**
     * The path to the video resources
     */
    public static String videoPath;

    /**
     * * The path to the sound resources
     */
    public static String soundPath;

    // URL constants
    /**
     * The URL string to labelMe
     */
    public static String LabelMeURL;

    /**
     * The URL string to labelMe actual images
     */
    public static String LabelMeImagesURL;

    /**
     * The URL string to ImageNet
     */
    public static String ImageNetURL;

    // Other constants
    /**
     * A weight that it is being used by the variable solver (the weight of an
     * inherent relation)
     */
    public static double weightForVariableSolver;

    /**
     * This is used by the similarity functions, to store which objects we have
     * already visited
     */
    public static Hashtable<String, Concept> conceptsVisited = new Hashtable();

    /**
     * A global variable that contains all the concepts that have been loaded
     * from an xml. It is used in the XML mode (where we do not have any db)
     */
    public static Hashtable globalConcepts = new Hashtable();
    public static Hashtable globalRelations = new Hashtable();
    public static Hashtable globalRelationSets = new Hashtable();

    /**
     * A global variable that contains all the concepts that have been loaded
     * from Wordnet. It is used in the Wordnet mode (where we do not have any
     * db)
     */
    public static Concepts wordNetConcepts;

    public static void Constants() throws FileNotFoundException, IOException {

        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("misc/settings.properties");
        props.load(fis);

        /**
         * Get settings.
         */
        dbHost = props.getProperty("db.host");
        System.out.println(dbHost);
        dbPort = props.getProperty("db.port");
        System.out.println(dbPort);
        dbName = props.getProperty("db.name");
        System.out.println(dbName);
        dbUser = props.getProperty("db.username");
        System.out.println(dbUser);
        dbPass = props.getProperty("db.password");
        System.out.println(dbPass);
        tmpPath = props.getProperty("path.tmp");
        imagePath = props.getProperty("path.images");
        imagePathLabelMe = props.getProperty("path.labelMe");
        imagePathImageNet = props.getProperty("path.imageNet");
        videoPath = props.getProperty("path.videos");
        soundPath = props.getProperty("path.sounds");
        LabelMeURL = props.getProperty("url.labelMe");
        LabelMeImagesURL = props.getProperty("url.labelMeImages");
        ImageNetURL = props.getProperty("url.imageNet");
        
        weightForVariableSolver = Double.parseDouble(props.getProperty(
                "const.variableSolverWeight"));
        System.out.println(weightForVariableSolver);
    }
}
