/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carpark.ifs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author LaKaLK
 */
public class CarParkServices {
    
    
    
    public String readXMLbyParkId(String parkId)
    {
        String output="";
        try {
            
            
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\lakalk\\Documents\\CarParkWebApp\\car_park_data.xml");
            
            XPath xpath = XPathFactory.newInstance().newXPath();//this returns a new XPath
            // XPath Query for showing all nodes value
            XPathExpression expr = null;
            String xPathExpr = "/CAR_PARK_DATA/CAR_PARKS/CAR_PARK[PARK_ID = "+ parkId +"]";
            expr = xpath.compile(xPathExpr);//Compile an XPath expression for later evaluation
            Object result = expr.evaluate(doc, XPathConstants.NODESET);//here evaluation takes place
            NodeList nodes = (NodeList) result;
            
            for (int i = 0; i < nodes.getLength(); i++) {
                // System.out.print(nodes.item(i).getNodeName());
                // System.out.print(" : ");
                // System.out.println(nodes.item(i).getTextContent());
                
                // Node nd = nodes.item(i).setNodeValue(output);
                output = nodes.item(i).getTextContent();
                // System.out.print(output);
            }
            
            return output;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CarParkServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CarParkServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CarParkServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(CarParkServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    
    }
           
    public String[] readAll()
    {
        String [] arr = null;
        try {
          
            
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\lakalk\\Documents\\CarParkWebApp\\car_park_data.xml");
            
            XPath xpath = XPathFactory.newInstance().newXPath();//this returns a new XPath
            // XPath Query for showing all nodes value
            XPathExpression expr = null;
            String xPathExpr = "/CAR_PARK_DATA/CAR_PARKS/CAR_PARK";
            expr = xpath.compile(xPathExpr);//Compile an XPath expression for later evaluation
            Object result = expr.evaluate(doc, XPathConstants.NODESET);//here evaluation takes place
            NodeList nodes = (NodeList) result;
            
            arr = new String[nodes.getLength()];
            
            for (int i = 0; i < nodes.getLength(); i++) {
                // System.out.print(nodes.item(i).getNodeName());
                // System.out.print(" : ");
                // System.out.println(nodes.item(i).getTextContent());
                
                // Node nd = nodes.item(i).setNodeValue(output);
                arr[i] = nodes.item(i).getTextContent();
                // System.out.print(output);
            }
            
            
            
            
            return arr;
        } catch (XPathExpressionException ex) {
            Logger.getLogger(CarParkServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CarParkServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CarParkServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CarParkServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    
    public String setAvailableValue(String carParkId, int occupNum) 
            throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, TransformerException
    {
    String occupiedCount = "";
    int maxCount = 0;
    int resCount = 0;
    int usedCount = 0;
    Object result;
    Transformer transformer;
    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		
        
        DocumentBuilder builder = domFactory.newDocumentBuilder();        
        Document doc = builder.parse("C:\\Users\\lakalk\\Documents\\CarParkWebApp\\car_park_data.xml");
        XPath xpath = XPathFactory.newInstance().newXPath();//this returns a new XPath
        // XPath Query for showing all nodes value
        XPathExpression expr = null;
        // XPathExpression expr1 = null;
        String xPathExpr = "/CAR_PARK_DATA/CAR_PARKS/CAR_PARK[PARK_ID = "+ carParkId +"]";
        domFactory.setNamespaceAware(true);
        expr = xpath.compile(xPathExpr);//Compile an XPath expression for later evaluation
        
               
        result = expr.evaluate(doc, XPathConstants.NODESET);//here evaluation takes place
        NodeList nodes = (NodeList) result;
        
         Element el = (Element)nodes.item(0);
         
         maxCount=  Integer.parseInt(el.getElementsByTagName("MAX_SLOTS").item(0).getTextContent());
         resCount = Integer.parseInt(el.getElementsByTagName("RES_SLOTS").item(0).getTextContent());
         usedCount = Integer.parseInt(el.getElementsByTagName("USED_SLOTS").item(0).getTextContent());
         
         if(((maxCount-resCount)> usedCount & usedCount >= 0 & !(occupNum < 0)) || (occupNum < 0 & usedCount > 0))
         {
         int setCount = usedCount + occupNum;
         
         el.getElementsByTagName("USED_SLOTS").item(0).setTextContent(String.valueOf(setCount));
         occupiedCount = el.getElementsByTagName("USED_SLOTS").item(0).getTextContent();
         try {
            transformer = transformerFactory.newTransformer();
       
	DOMSource source = new DOMSource(doc);
	StreamResult result2;
        result2 = new StreamResult(new File("C:\\Users\\lakalk\\Documents\\CarParkWebApp\\car_park_data.xml"));
		transformer.transform(source, result2);
                 } catch (TransformerConfigurationException ex) {
                     occupiedCount = "-2";
            Logger.getLogger(CarParkServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         }else
             occupiedCount = "-1";
         
         
         
              
       
        
        return occupiedCount;
   
    }
    
    
}
