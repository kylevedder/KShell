/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kshell;

/**
 *
 * @author Kyle
 */
public class CommandExecuteQueue
{
    private static CommandExecuteQueue cmq = null;
    private UserInput userInput;
    private UserInputList userInputList;
    private OperatingMode opMode = null;
    public Lock functionLock = null;

    /**
     * Returns the singleton instance of the queue.
     * @return 
     */
    public static CommandExecuteQueue getInstance()
    {
        if(cmq == null)cmq = new CommandExecuteQueue();
        return cmq;
    }        
    
    /**
     * Holds the current running command and any user input.
     */
    private CommandExecuteQueue()
    {
        init();
    }
    
    /**
     * Sets/Resets the function
     */
    private void init()
    {
        userInput = null;
        opMode = OperatingMode.COMMAND;
        functionLock = new Lock();
        userInputList = new UserInputList();
    }

    /**
     * Blocks until a function is avalible.
     */
    public void awaitFunction()
    {
        functionLock.await();
    }
    
    /**
     * Resets the function and locks the lock.
     */
    public void clearFunction()
    {
        userInput = null;
        functionLock.lock();
    }
       

    /**
     * Gets the operating mode currently
     * @return Command - waiting for a command mode
     *         Input - waiting for input
     */
    public OperatingMode getOpMode()
    {
        return opMode;
    }
    
    /**
     * Add the the function to execute it.
     * @param uin UserInput to execute
     * 
     * @return success of adding the function
     */
    public boolean addFunction(UserInput uin)
    {
        if(this.getOpMode() == OperatingMode.COMMAND)
        {
            this.userInput = uin;
            functionLock.unlock();
            return true;
        }
        //in another mode
        return false;
    }

    /**
     * Gets the UserInput object of the function.
     * @return 
     */
    public UserInput getFunction()
    {
        return userInput;
    }
    
    
    
    /**
     * Adds user input to feed into the execute thread. Will be use IFF the thread wants to keep the info.
     * @param uin 
     */
    public void addInput(UserInput uin)
    {
        userInputList.addUserInput(uin);
    }
    
    /**
     * Clears the user input buffer.
     */
    public void clearInput()
    {
        userInputList.clear();
    }
    
    /**
     * Completely resets the queue.
     */
    public void reset()
    {
        init();
    }
}
