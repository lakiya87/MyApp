/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carpark.ifs;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author LaKaLK
 */
public class Main {
    
    
    public static void main(String args[]) throws TransformerException
    {
    
    
        try {
            CarParkServices cs = new CarParkServices();
            String x ="";
            
            String [] arr;
            cs.setAvailableValue("02", -1);
            cs.setAvailableValue("01", -1);
            cs.setAvailableValue("03", -1);
            cs.setAvailableValue("04", -1);
            //arr = cs.readAll();
            Logger.getLogger(Main.class.getName()).log(Level.INFO,x);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
}
