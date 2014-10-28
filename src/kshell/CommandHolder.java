/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class CommandHolder
{

    private List<Command> list;

    public CommandHolder()
    {
        list = Collections.synchronizedList(new ArrayList<Command>());
    }

    /**
     * Add an Command to the CommandHolder.
     *
     * @param cmd
     */
    public void addCommand(Command cmd)
    {
        list.add(cmd);
    }

    /**
     * Add a given String to the CommandHolder.
     *
     * @param s
     */
    public void addCommand(String s)
    {
//        Command cmd = new Command

    }

    private Command parseIntoCommand(String s)
    {
        s = s.trim();
        if(!s.contains(" "))//simply a command
        {
            return new Command(s, null, null);
        }
        else
        {            
            int firstSpace = s.indexOf(" ");
            String command = s.substring(0, firstSpace);
            String beyondFirstSpace = s.substring(firstSpace).trim();
            if(beyondFirstSpace.startsWith("-"))//has arguments
            {
                int nextSpace = beyondFirstSpace.indexOf(" ");
                if(nextSpace < 0)//just the command and args, no space after exists
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
//        int commandSplit = s.indexOf(" ");
//        String command = s.substring(0, commandSplit);//splits the string at the first space. This is the command string.
//        String args = null;
//        String param = null;
//        if (commandSplit < s.length() - 1)//is more than just a single command
//        {
//            if(s.substring(commandSplit).trim().startsWith("-"))//is an argument
//            {
//                
//            }
//            
//            
//            if (s.substring(commandSplit).startsWith("\""))//starts with quote
//            {
//                int indexOfCloseQuote = s.indexOf(s, commandSplit + 1);
//                if(indexOfCloseQuote>=commandSplit)//close quote exists
//                {
//                    
//                }
//            }
//        }
//        String String[] split = s.split(" ");
    }

}
