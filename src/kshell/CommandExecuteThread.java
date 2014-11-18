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
            System.out.println("Waiting...");
            //await an added function
            ceq.awaitFunction();
            System.out.println("Executing...");

            //accept function
            //begin execution
            //while executing, check for input for function
            //while executing, check for output from the function
            if ((usrIn = ceq.getFunction()) != null)//not null input, function needs to be processed
            {
                //parse function
                Command c = parseFunction(usrIn);

                
                try
                {
                    Process process = Runtime.getRuntime().exec(
                            "java -jar \"" + "D:\\Documents\\NetBeansProjects\\JShellPrograms\\Whoami\\dist\\Whoami.jar" + "\" " + "");
                    
                    //ins
                    is = process.getInputStream();                    
                    isr = new InputStreamReader(is);                    
                    br = new BufferedReader(isr);
                    
                    //outs
                    os = process.getOutputStream();
                    osw = new OutputStreamWriter(os);                       
                    os.write("hello".getBytes());
                    //add ins and outs
                    ceq.addCommandIn(osw);
                    ceq.addCommandOut(br);
                    String line;
                                        
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
            ceq.reset();
            System.out.println("execution done...");
            
            //done executing
            

//            if ((ceq.getFunction()) != null)//not null input, function needs to be processed
//            {
//                //grab the function for processing
//                usrIn = ceq.getFunction();
//                //clear queue's function
//                ceq.clearFunction();
//                System.out.println("FN: " + usrIn.getString());
//            }
//            else//process input
//            {
//                
//            }
//            System.out.println("Loop Done...");
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
