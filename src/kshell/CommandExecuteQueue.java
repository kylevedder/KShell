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
    private UserInput userInput;
    private SyncdList syncdList;
    private OperatingMode opMode = null;
    public Lock chLock = null;

    /**
     * Holds the current running command and any user input.
     */
    public CommandExecuteQueue()
    {
        userInput = null;
        opMode = OperatingMode.COMMAND;
        chLock = new Lock();
        syncdList = new SyncdList();
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
            return true;
        }
        //in another mode
        return false;
    }
    
    

    
    
    
    
    
    
}
