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
     * Add an Command object to the CommandHolder.
     *
     * @param cmd
     */
    public void addCommand(Command cmd)
    {
        synchronized (list)
        {
            list.add(cmd);
        }
    }

    /**
     * Add a given String to the CommandHolder.
     *
     * @param s
     */
    public void addCommand(String s)
    {
        synchronized(list)
        {
            list.add(Command.parseIntoCommand(s));
        }
    }  
    
    /**
     * Add a given Input object to the CommandHolder.
     *
     * @param s
     */
    public void addCommand(Input i)
    {
        synchronized(list)
        {
            list.add(Command.parseIntoCommand(i));
        }
    }  
}
