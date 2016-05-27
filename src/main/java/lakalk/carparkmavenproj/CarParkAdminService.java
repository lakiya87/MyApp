/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lakalk.carparkmavenproj;

//import com.carpark.ifs.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author LaKaLK
 */
@WebService(serviceName = "CarParkAdminService")
public class CarParkAdminService {
CarParkServices cs = new CarParkServices();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
     @WebMethod(operationName = "setAvailSlots",action = "http://ifs.carpark.com/setAvailSlots")
    public String setAvailSlots(@WebParam(name = "parkId") String parkId,@WebParam(name = "occupNum") String occupNum) {
        String occupiedCount ="";
    try {
      occupiedCount=  cs.setAvailableValue(parkId, Integer.parseInt(occupNum));
    } catch (ParserConfigurationException ex) {
        Logger.getLogger(CarParkAdminService.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SAXException ex) {
        Logger.getLogger(CarParkAdminService.class.getName()).log(Level.SEVERE, null, ex);
    } catch (XPathExpressionException ex) {
        Logger.getLogger(CarParkAdminService.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(CarParkAdminService.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TransformerException ex) {
        Logger.getLogger(CarParkAdminService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return occupiedCount;
    }
    
    
    @WebMethod(operationName = "refreshPark",action = "http://ifs.carpark.com/refreshPark")
   public String refreshPark(@WebParam(name = "parkId") String parkId) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException 
   {
       
       

         return  cs.readXMLbyParkId(parkId);

    
   
   }
   
    @WebMethod(operationName = "readAll",action = "http://ifs.carpark.com/readAll")
    public String[] readAll() {
        return cs.readAll();
    }
//   
//    @WebMethod(operationName = "test")
//     public HashMap<String,String> test() {
//       
//       HashMap<String,String> hs = new HashMap();
//    hs.put("1", "Val1");
//     hs.put("2", "Val2");
//   return hs;
//   }

  
}
