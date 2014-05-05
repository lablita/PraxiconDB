package csri.poeticon.praxicon;

import csri.poeticon.praxicon.db.entities.Concept;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Erevodifwntas
 *
 * This is used to load data from an xml. The path to the xml is given as an
 * argument "-f <pathToXML>"
 */
public class LoadXML 
{
    public static void main(String args[]) throws JAXBException, FileNotFoundException{
        Concept c = null;
        JAXBContext context;
        context = JAXBContext.newInstance("csri.poeticon.praxicon.db.entities");
        File f;  
        f = new File("/home/dmavroeidis/work/praxicondb/sample_xml_20140502.xml");
        Reader reader = new InputStreamReader(new FileInputStream(f));
        Unmarshaller u = context.createUnmarshaller();
        c =(Concept)u.unmarshal(reader);
        
        //c.storeEverything();
        
        
        
//        CollectionOfConcepts c = null;
//
//        
//        //args[0] = "-f";
//        args[0] = "/home/dmavroeidis/instance1.xml";
//        
//        if(args.length == 2 && args[0].equalsIgnoreCase("-f"))
//        {
//            File dir = new File(args[1]);
//            String[] files;  // The names of the files in the directory.
//            files = dir.list();
//            for (int i = 0; i < files.length; i++)
//            {
//                File f;  // One of the files in the directory.
//                f = new File(dir, files[i]);
//                Reader reader = new InputStreamReader(new FileInputStream(f));
//                Unmarshaller u = context.createUnmarshaller();
//                c =(CollectionOfConcepts)u.unmarshal(reader);
//                System.out.println("Finished " + i + " of " +files.length +"("+files[i]+")");
//            }
//            c.storeEverything();
//        }
//        else
//        {
//            if(args.length == 1)
//            {
//                Reader reader =
//                      new InputStreamReader(
//                      new FileInputStream(new File(args[0])));
//                Unmarshaller u = context.createUnmarshaller();
//                c =(CollectionOfConcepts)u.unmarshal(reader);
//                c.storeEverything();
//            }
//            else
//            {
//                System.out.println("Usage: Give as an argument the xml file with the concepts (for example: java -jar PraxiconDB.jar concepts.xml");
//                RelationDao rDao = new RelationDaoImpl();
//                List <Relation> tmp = rDao.findAll();
//                for (Relation tmp1 : tmp) 
//                {
//                    List<Concept> owners = rDao.getOwners(tmp1);
//                    System.out.println(tmp1);
//                    for (Concept owner : owners) 
//                    {
//                        System.out.println(owner);
//                    }
//                }
//            }
//        }
    }

}
