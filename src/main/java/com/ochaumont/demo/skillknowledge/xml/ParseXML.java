package com.ochaumont.demo.skillknowledge.xml;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseXML {

   public static final Logger log = LoggerFactory.getLogger(ParseXML.class);
	
	private static String[] XML_FILES = {"knowledgeBase-skills.xml",
		                                 "knowledgeBase-expertises.xml",
		                                 "knowledgeBase-consultants.xml",
		                                 "knowledgeBase-skillTypes.xml"};
	
	public static Knowledge parseInputStream() {
		try {
			Knowledge k = new Knowledge();
			for(int i=0; i<XML_FILES.length; i++) {
				InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(XML_FILES[i]);		
				JAXBContext jaxbContext;			
				jaxbContext = JAXBContext.newInstance(Knowledge.class);		
				Unmarshaller u = jaxbContext.createUnmarshaller();
				Knowledge ki = (Knowledge)u.unmarshal(input);
				k.getSkill().addAll(ki.getSkill());
				k.getExpertise().addAll(ki.getExpertise());
				k.getConsultant().addAll(ki.getConsultant());
				k.getSkillType().addAll(ki.getSkillType());
			}		
		    return k;
		
		} catch (JAXBException e) {
			log.error("ParseXML error",e);
			throw new RuntimeException(e);			
		}
	  
	}
	
}
