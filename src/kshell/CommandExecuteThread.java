/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 */
public class CommandExecuteThread extends Thread
{

    private volatile boolean isRunning = true;
    private volatile InputStream is = null;
    private volatile InputStreamReader isr = null;
    private volatile BufferedReader br = null;
    private volatile OutputStream os = null;
    private volatile OutputStreamWriter osw = null;
    private CommandExecuteQueueNew ceq;

    @Override
    public void run()
    {
        ceq = CommandExecuteQueueNew.getInstance();
        execute();
    }

    /**
     * Executes the functionality of the thread.
     */
    private void execute()
    {
        UserInput usrIn = null;
        while (isRunning)
        {
            System.out.println("Waiting For New Function...");
            //await an added function
            ceq.awaitFunction();
            usrIn = ceq.getFunction();
            System.out.println("Executing: " + usrIn);
            
            ceq.reset();
            System.out.println("execution done...");           
        }
    }

    /**
     * Parses the UserInput object into a Command object.
     *
     * @param usrIn
     * @return Command object of the function, returns null if usrIn is null
     */
    private Command parseFunction(UserInput usrIn)
    {
        if (usrIn != null)
        {
            String input = usrIn.toString().trim();

//            (input.indexOf(" "));
        }
        else
        {
            return null;
        }
        return null;
    }

    /**
     * Execute the jar file at the given path.
     *
     * @param path file system path to each jar.
     * @param args String containing the arguments for the jar to execute.
     */
    private void executeJarFile(String path, String args)
    {
        try
        {
            Process process = Runtime.getRuntime().exec(
                    "java -jar \"" + path + "\" " + args);
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
            Logger.getLogger(CommandExecuteThread.class.getName()).log(Level.SEVERE, null, ex);
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
