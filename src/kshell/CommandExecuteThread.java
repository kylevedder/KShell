/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import kshell.commands.LookupList;
import kshell.comms.StreamsHandler;
import kshell.comms.jario.JarIOTypesEnum;
import kshell.comms.jario.JarIOInterpreter;
import kshell.comms.jario.JarIOPrintText;
import kshell.file.FileWrapper;

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
    private LookupList ll;

    @Override
    public void run()
    {
        ceq = CommandExecuteQueueNew.getInstance();
        ll = new LookupList(new FileWrapper(Main.commandLookupFileLocation));
        execute();
    }

    /**
     * Executes the functionality of the thread.
     */
    private void execute()
    {
        UserInput functionName = null;
        while (isRunning)
        {
            try
            {
                System.out.println("Waiting For New Function...");
                //await an added function
                ceq.awaitFunction();
                //get the function name
                functionName = ceq.getFunction();
                System.out.println("Executing: " + functionName);

                //look up function name
                FileWrapper jarLocation = ll.getJarFromFunctionName(functionName.toString());

                //if lookedup name not null
                if (jarLocation != null)
                {
                    ProcessBuilder builder = new ProcessBuilder("java", "-jar", jarLocation.getCanonicalPath());
                    Process process = builder.start();

                    OutputStream stdin = process.getOutputStream();
                    InputStream stdout = process.getInputStream();

                    StreamsHandler io = new StreamsHandler(stdin, stdout);
                    JarIOInterpreter jarIOInterpreter = null;
                    
                    //while has an open stream
                    while (io.hasNext())
                    {
                        String read = io.read();
                        jarIOInterpreter = new JarIOInterpreter(read);

                        System.out.println(jarIOInterpreter.getType());
                        if (jarIOInterpreter.getType() == JarIOTypesEnum.PRINT_TEXT)
                        {
                            JarIOPrintText pt = (JarIOPrintText) jarIOInterpreter.getObject();
                            System.out.println(">>>>>> " + pt.getMessage());
                        }
                        else if (jarIOInterpreter.getType() == JarIOTypesEnum.GET_INFO)
                        {
                            JarIOPrintText pt = (JarIOPrintText) jarIOInterpreter.getObject();
                            io.write("Hello");
                        }
                    }                    
                }
                else
                {
                    System.out.println(functionName + " - Command Not Found!");
                }
                ceq.reset();
                System.out.println("execution done...");
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
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
