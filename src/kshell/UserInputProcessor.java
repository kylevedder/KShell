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

    private UserInput commandToExecute = new UserInput();
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
        this.ui = ui;
        this.ui.updateLine(new UserInput().toLine());
    }

    /**
     * Processes the Output object and handles it as needed.
     *
     * @param o
     * @return a modified output object.
     */
    public UserInput process(UserInput o)
    {        
        if(opMode == OperatingMode.COMMAND)
            return processCommand(o);
        else if(opMode == OperatingMode.INPUT)
            return processInput(o);
        else
            return null;
    }
    
    /**
     * Process as if the Output is a command.
     * @return a modified output object.
     */
    private UserInput processCommand(UserInput o)
    {   
        System.out.print("Command: ");
        System.out.println(o);
        if(o.isEnterHit())
        {
            System.out.println("Enter is hit!");
            this.opMode = OperatingMode.INPUT;
//            this.ui.appendText("\n");
//            this.ui.appendText("This area is for the start of the command.\n");    
            executeCommand(o);
            return new UserInput();
            
        }
        else//just update the line per ususal
        {
            this.ui.updateLine(o.toLine());
            return o;
        }
    }
    
    /**
     * Process as if the Output is input for a running command.     
     * @param o
     * @return a modified output object.
     */
    private UserInput processInput(UserInput o)
    {
        System.out.print("Input: ");
        System.out.println(o);
//        this.opMode = OperatingMode.COMMAND;
        this.ui.updateLine(o);
        return new UserInput();
    }
    
    /**
     * Parses and readies the command for execution.
     * @param o 
     */
    private void executeCommand(UserInput o)
    {
        commandToExecute = new UserInput(o);
    } 
    
    /**
     * Parses the Output object for a command.
     * @param o 
     */
    private void parseOutputForCommand(UserInput o)
    {
        
    }        

}

enum OperatingMode
{
    COMMAND, INPUT
}
