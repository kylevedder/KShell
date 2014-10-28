/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 */
public class CommandProcessorThread extends Thread
{

    private volatile boolean isRunning = true;

    @Override
    public void run()
    {
        execute();
    }

    /**
     * Executes the functionality of the thread.
     */
    private void execute()
    {
        Command c = null;
        while (isRunning)
        {
            Main.chLock.await();//wait for command to be added
            synchronized (Main.ch)
            {
                c = Main.ch.getNextCommand();
                if (Main.ch.isEmpty())
                {
                    Main.chLock.lock();
                }
            }
            System.out.println(c.command);
            //TODO: Execute command
        }
    }

    /**
     * Execute the jar file at the given path.
     * @param path 
     * @param cmd
     */
    private void executeJarFile(String path, Command cmd)
    {
        try
        {
            Process process = Runtime.getRuntime().exec(
                    "java -jar \"" + path + "\" Hello World Two THree");
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            
//        System.out.printf("Output of running %s is:", Arrays.toString(args));
            
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(CommandProcessorThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ends the thread.
     */
    public void kill()
    {
        this.isRunning = false;
    }
}
