/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell.comms;

import kshell.comms.jario.JarIOTypesEnum;
import kshell.comms.jario.JarIOInterpreter;
import kshell.comms.jario.JarIOPrintText;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Kyle
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        ProcessBuilder builder = new ProcessBuilder("java", "-jar", "JarExecutorTestJar.jar");
        builder.directory(new File("D:\\Documents\\NetBeansProjects\\JarExecutorTestJar\\dist"));
        Process process = builder.start();

        OutputStream stdin = process.getOutputStream();
        InputStream stdout = process.getInputStream();

        StreamsHandler io = new StreamsHandler(stdin, stdout);                
        JarIOInterpreter jarIOInterpreter = null;        
        while (io.hasNext())
        {            
            String read = io.read();    
            jarIOInterpreter = new JarIOInterpreter(read);            
            
            
            if(jarIOInterpreter.getType() == JarIOTypesEnum.PRINT_TEXT)
            {                
                JarIOPrintText pt = (JarIOPrintText)jarIOInterpreter.getObject();
                System.out.println(pt.getMessage());                
            }
            else if(jarIOInterpreter.getType() == JarIOTypesEnum.GET_INFO)
            {                         
                io.write("Hello");
            }            
        }       
        System.out.println("<Done>");
    }
}
