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
public class UserInputProcessor
{

    private CommandExecuteQueue ceq = null;
    private OperatingMode opMode = null;
    private UI ui = null;
    /**
     * Makes decisions about what to do with the output object given to it.
     * <p>
     * This may be to execute a brand new command, or to pass the output to an
     * already executing command.</p>
     *
     *
     */
    public UserInputProcessor(UI ui)
    {
        opMode = OperatingMode.COMMAND;
        ceq = CommandExecuteQueue.getInstance();
        this.ui = ui;
        this.ui.updateLine(new UserInput().toLine());
    }

    /**
     * Processes the Output object and handles it as needed.
     *
     * @param usrIn
     * @return a modified output object.
     */
    public UserInput process(UserInput usrIn)
    {        
        if(opMode == OperatingMode.COMMAND)
            return processCommand(usrIn);
        else if(opMode == OperatingMode.INPUT)
            return processInput(usrIn);
        else
            return null;
    }
    
    /**
     * Process as if the Output is a command.
     * @return a modified output object.
     */
    private UserInput processCommand(UserInput usrIn)
    {   
        System.out.print("Command: ");
        System.out.println(usrIn);
        if(usrIn.isEnterHit())
        {
            this.ui.appendText("\n");
            System.out.println("Enter is hit!");
            this.opMode = OperatingMode.INPUT;
            ceq.addFunction(usrIn);
            return new UserInput();
            
        }
        else//just update the line per ususal
        {
            this.ui.updateLine(usrIn.toLine());
            return usrIn;
        }
    }
    
    /**
     * Process as if the Output is input for a running command.     
     * @param usrIn
     * @return a modified output object.
     */
    private UserInput processInput(UserInput usrIn)
    {
        System.out.print("Input: ");
        System.out.println(usrIn);
        ceq.addInput(usrIn);
        this.ui.updateLine(usrIn);
        return new UserInput();
    }          
}

enum OperatingMode
{
    COMMAND, INPUT
}
