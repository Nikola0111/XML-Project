package com.projekat.XML.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.logging.*; 

@Service
public class LoggerService {

    @Autowired
    SessionService sessionService;


    public void doLog(String function, String result, String nature){
        Logger logger = Logger.getLogger("MyLog");  
        FileHandler fh;  
    
        String user = sessionService.getLoggedUsername();

        try {  
            fh = new FileHandler("C:/Temp/RentACarLogFile.log", 1000000, 5, true);  
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