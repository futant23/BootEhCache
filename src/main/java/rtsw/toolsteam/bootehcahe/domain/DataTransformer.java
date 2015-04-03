/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootehcahe.domain;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author b1050502
 */
public class DataTransformer {
    public synchronized String toXml(final Data data){
		 final StringWriter stringWriter = new StringWriter();
	        try {
	        	JAXBContext context = JAXBContext.newInstance(Data.class);
	        	Marshaller marshaller =  context.createMarshaller();
	        	
	        	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        	marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
	   		    if(data==null) {
	   		    	Data bbean =new Data();
	   		    	marshaller.marshal(bbean, stringWriter);	
	   		    }	
	   		    else{
	   		    	marshaller.marshal(data, stringWriter);
	   		    }
			} catch (Exception e) {
				e.printStackTrace();
			}
	        finally{	
	        }
	        return stringWriter.toString();
	}

	public synchronized Data fromXml(String xml) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
		StringReader reader = new StringReader(xml);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Data bean= (Data) jaxbUnmarshaller.unmarshal(reader);
	    return bean;
	}

}
