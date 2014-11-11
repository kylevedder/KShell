/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import kshell.old.Input;
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
     * Returns if the CommandHolder is empty.
     * @return if the Holder is empty
     */
    public boolean isEmpty()
    {
        return !(list.size() > 0);
    }
    
    /**
     * Gets the next command in the list, removing it from the holder.
     * @return Command object, or null if no objects are left.
     */
    public Command getNextCommand()
    {
        if (list.size() > 0)
        {
            Command c = list.get(0);
            list.remove(0);
            return c;
        }
        return null;
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
        synchronized (list)
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
        synchronized (list)
        {
            list.add(Command.parseIntoCommand(i));
        }
    }
}
