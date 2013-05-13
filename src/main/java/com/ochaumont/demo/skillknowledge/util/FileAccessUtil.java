/* ------------------------------------
 * SweetDEV III
 * Copyright Ideo Technologies - 2010
 * Document strictement confidentiel
 * Tous droits de propriete reserves
 * @version 3.0-SNAPSHOT
 * @author Ideo Technologies
 * ------------------------------------
 */
package com.ochaumont.demo.skillknowledge.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FileAccessUtil {

	
    public static void copyInputStreamtoFile(InputStream stream, File file)  {
    	try {
    		file.createNewFile();
    		FileWriter out = new FileWriter(file,false);
            out.write(getInputStreamContent(stream));                
            out.close();
    	}catch(IOException ex) {
    		throw new RuntimeException("Error read SQL script",ex);
    	}
	    	
	}
    
    public static String getInputStreamContent(InputStream stream) throws IOException {
        InputStreamReader in = null;
        StringBuffer buf = null;
        try {
            buf = new StringBuffer();
            in = new InputStreamReader(stream);
            int c;
            while ((c = in.read()) != -1){
              buf.append((char)c);
            }                       
        }catch(FileNotFoundException fileNotFounfEx) {
            throw fileNotFounfEx;
        }catch(IOException ioEx) {
            throw ioEx;
        }finally{
            in.close();
        }
        
        return buf.toString();
    }
    
    public static boolean deleteDirectory(File path) {
        if( path.exists() ) {
          File[] files = path.listFiles();
          for(int i=0; i<files.length; i++) {
             if(files[i].isDirectory()) {
               deleteDirectory(files[i]);
             }
             else {
               files[i].delete();
             }
          }
        }
        return( path.delete() );
      }
	
	public static String getClassDirectory(Class testClass) {    
    	String resource = testClass.getSimpleName()+".class";    	
        URL ressourceURL = testClass.getResource(resource);
        String absolutPath = ressourceURL.getPath();
        absolutPath = absolutPath.replace('/', File.separatorChar);
        absolutPath = absolutPath.replace('\\', File.separatorChar);
        String className = testClass.getName();
        String classNamePath = className.replace('.', File.separatorChar);
        int index = absolutPath.indexOf(classNamePath);
        String classDir = absolutPath.substring(0, index);             
        return classDir;
     }
	
	
	public static String getTargetDirectory(Class testClass) {    
		String classDir = getClassDirectory(testClass);
        String targetDir = classDir.replaceAll("bin", "target");
        targetDir = targetDir.replaceAll("test-classes", "");        
        return targetDir;
     }
	
	public static String getRessourceDirectory(Class testClass) { 		
        String targetDir = getTargetDirectory(testClass);
        String resourceDir = targetDir.replaceAll("target", "src/main/resources") ;  
        resourceDir = replaceFileSeparator(resourceDir);
        return resourceDir;
     }
	
	public static String getTestRessourceDirectory(Class testClass) {    
		 String targetDir = getTargetDirectory(testClass);
		 String testResourceDir = targetDir.replaceAll("target", "src/test/resources") ;   
		 testResourceDir = replaceFileSeparator(testResourceDir);
	     return testResourceDir;
     }
	
	public static String getSrcDirectory(Class testClass) { 		
        String targetDir = getTargetDirectory(testClass);
        String srcDir = targetDir.replaceAll("target", "src/main/java") ; 
        srcDir = replaceFileSeparator(srcDir);
        return targetDir;
     }
	
	public static String getTestSrcDirectory(Class testClass) {    
		 String targetDir = getTargetDirectory(testClass);
	     String testSrcDir = targetDir.replaceAll("target", "src/test/java") ;  
	     testSrcDir = replaceFileSeparator(testSrcDir);
	     return testSrcDir;
     }
	
	public static String replaceFileSeparator(String value) {
		value = value.replace('/', File.separatorChar);
		value = value.replace('\\', File.separatorChar);
		return value;
		
	}
	
}
