/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 */
public class CommandExecuteQueueNew
{

    private static CommandExecuteQueueNew cmq = null;
    private UserInput function;
    private volatile OutputStreamWriter osw = null;
    private volatile BufferedReader br = null;
    private OperatingMode opMode = null;
    public Lock functionLock = null;

    /**
     * Returns the singleton instance of the queue.
     *
     * @return
     */
    public static CommandExecuteQueueNew getInstance()
    {
        if (cmq == null)
        {
            cmq = new CommandExecuteQueueNew();
        }
        return cmq;
    }

    /**
     * Holds the current running command and any user input.
     */
    private CommandExecuteQueueNew()
    {
        init();
    }

    /**
     * Sets/Resets the function
     */
    private void init()
    {
        function = null;
        opMode = OperatingMode.COMMAND;
        functionLock = new Lock();
    }

    /**
     * Adds a stream to write with.
     *
     * @param osw
     */
    public void addCommandIn(OutputStreamWriter osw)
    {
        this.osw = osw;
    }

    /**
     * Clears the stream to write with.
     */
    public void clearCommandIn()
    {
        this.osw = null;
    }

    /**
     * Write the input to the function.
     *
     * @param s
     */
    public void writeCommandIn(String s)
    {
        s += "\n";
        try
        {
            this.osw.write(s, 0, s.length());
        }
        catch (IOException ex)
        {
            Logger.getLogger(CommandExecuteQueueNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds a stream to read with.
     *
     * @param osw
     */
    public void addCommandOut(BufferedReader br)
    {
        this.br = br;
    }

    /**
     * Clears the stream to read with.
     */
    public void clearCommandOut()
    {
        this.br = null;
    }

    /**
     * Read the output of the command.
     *
     * @return
     */
    public String readCommandOut()
    {
        try
        {
            return this.br.readLine();
        }
        catch (IOException ex)
        {
            Logger.getLogger(CommandExecuteQueueNew.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Blocks until a function is avalible.
     */
    public void awaitFunction()
    {
        functionLock.await();
    }

    /**
     * Add the the function to execute it.
     *
     * @param uin UserInput to execute
     *
     * @return success of adding the function
     */
    public boolean addFunction(UserInput uin)
    {
        if (this.getOpMode() == OperatingMode.COMMAND)
        {
            this.function = uin;
            functionLock.unlock();
            return true;
        }
        //in another mode
        return false;
    }

    /**
     * Gets the UserInput object of the function.
     *
     * @return
     */
    public UserInput getFunction()
    {
        return function;
    }

    /**
     * Clears the function.
     */
    public void clearFunction()
    {
        function = null;
    }

    /**
     * Adds user input to feed into the execute thread. Will be use IFF the
     * thread wants to keep the info.
     *
     * @param uin
     */
    public void addInput(UserInput uin)
    {

    }

    /**
     * Completely resets the queue.
     */
    public void reset()
    {
        init();
    }

    /**
     * Gets the operating mode currently
     *
     * @return Command - waiting for a command mode Input - waiting for input
     */
    public OperatingMode getOpMode()
    {
        return opMode;
    }
}
