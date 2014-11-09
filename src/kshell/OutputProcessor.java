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
public class OutputProcessor
{

    OperatingMode opMode = null;
    UI ui = null;
    /**
     * Makes decisions about what to do with the output object given to it.
     * <p>
     * This may be to execute a brand new command, or to pass the output to an
     * already executing command.</p>
     *
     *
     */
    public OutputProcessor(UI ui)
    {
        opMode = OperatingMode.COMMAND;
        this.ui = ui;
        this.ui.updateLine(new Output().toLine());
    }

    /**
     * Processes the Output object and handles it as needed.
     *
     * @param o
     * @return a modified output object.
     */
    public Output process(Output o)
    {
        System.out.println(o);
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
    private Output processCommand(Output o)
    {   
        if(o.isEnterHit())
        {
            System.out.println("enter hit");
            this.ui.appendText("\n");
            this.ui.appendText("This area is for the start of the command.\n");
            this.opMode = OperatingMode.INPUT;
            return new Output();
            
        }
        else//just update the line per ususal
        {
            this.ui.updateLine(o.toLine());
            return o;
        }
    }
    
    /**
     * Parses the Output object for a command.
     * @param o 
     */
    private void parseOutputForCommand(Output o)
    {
        
    }
    
    /**
     * Process as if the Output is input for a running command.     
     * @param o
     * @return a modified output object.
     */
    private Output processInput(Output o)
    {
        this.opMode = OperatingMode.COMMAND;
        this.ui.updateLine(o);
        return new Output();
    }

}

enum OperatingMode
{
    COMMAND, INPUT
}
