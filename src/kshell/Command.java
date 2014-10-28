/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

/**
 *
 * @author kyle
 */
public class Command
{

    String command;
    String args;
    String param;

    public Command(String command, String args, String param)
    {
        this.command = command;
        this.args = args;
        this.param = param;
    }

    /**
     * Converts a String into a Command object.
     * @param s String to convert.
     * @return Command object of the String.
     */
    public static Command parseIntoCommand(String s)
    {
        s = s.trim();
        if (!s.contains(" "))//simply a command
        {
            return new Command(s, null, null);
        }
        else
        {
            int firstSpace = s.indexOf(" ");
            String command = s.substring(0, firstSpace);
            String beyondFirstSpace = s.substring(firstSpace).trim();
            if (beyondFirstSpace.startsWith("-"))//has arguments
            {
                int nextSpace = beyondFirstSpace.indexOf(" ");
                if (nextSpace < 0)//just the command and args, no space after exists
                {
                    return new Command(command, beyondFirstSpace, null);
                }
                else//actually exists, has command, args, and param
                {
                    String args = beyondFirstSpace.substring(0, nextSpace);
                    String param = s.substring(nextSpace).trim();
                    return new Command(command, args, param);
                }

            }
            else//no args
            {
                return new Command(command, null, beyondFirstSpace);
            }
        }
    }
    
    /**
     * Converts an Input object into a Command object.
     * @param s String to convert.
     * @return Command object of the Input object.
     */
    public static Command parseIntoCommand(Input i)
    {
        return parseIntoCommand(i.toString());
    }
}
