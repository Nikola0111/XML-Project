package com.projekat.XML.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.*; 

@Service
public class LoggerService {


    public void doLog(String user, String function, String result, String nature){
        Logger logger = Logger.getLogger("MyLog");  
        FileHandler fh;  
    
        try {  
            fh = new FileHandler("C:/Users/Korisnik/Desktop/RentACarLogFile.log", 1000000, 5, true);  
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  
    
            if(nature.equals("INFO")){
                logger.info(" | " + user + " | " + function + " | " + result + ";");  
            }
            else if(nature.equals("WARNING")){
                logger.warning(" | " + user + " | " + function + " | " + result + ";");
            }
            else if(nature.equals("ERROR")){
                logger.severe(" | " + user + " | " + function + " | " + result + ";");
            }
        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
    }
    
}