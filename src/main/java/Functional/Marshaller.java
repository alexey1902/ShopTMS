package Functional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Marshaller {
    private final static String fileName = "src/main/java/Files/shop.xml";
    public void marshall(Shop shop){
        try{
            JAXBContext context = JAXBContext.newInstance(Shop.class);
            javax.xml.bind.Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(shop, new FileWriter(fileName));
        } catch (JAXBException | IOException exception){
            Logger.getLogger(Marshaller.class.getName()).
                    log(Level.SEVERE, "marshallExample throws exception", exception);
        }
    }
    public static Shop unmarshall(){
        Shop unmarshal = new Shop();
        try{
            File file = new File(fileName);
            JAXBContext context = JAXBContext.newInstance(Shop.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshal = (Shop)unmarshaller.unmarshal(file);
            System.out.println(unmarshal);
        }catch(JAXBException exception){
            exception.printStackTrace();
        }
        return  unmarshal;
    }
}
