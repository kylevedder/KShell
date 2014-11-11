/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import kshell.old.Input;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class CommandMemory
{

    private static ArrayList<Input> memory = new ArrayList<Input>();
    private static int counter = -1;

    /**
     * Adds a command to the list of remembered commands.
     *
     * @param cl
     */
    public static void add(Input cl)
    {
        memory.add(0, cl);
        counterReset();
    }

    /**
     * Returns the number of commands in memory.
     *
     * @return number of commands in memory.
     */
    public static int getNumCommands()
    {
        return memory.size();
    }

    /**
     * Gets the Input of the given index. "0" returns the most recent
     * command, with higher numbers being the less recent commands.
     *
     * @param index Index of command to get. "0" is the first command.
     * @return Specified Input object.
     */
    public static Input getCommand(int index)
    {
        return memory.get(index);
    }

    /**
     * Resets the memory counter to zero.
     */
    public static void counterReset()
    {
        counter = -1;
    }

    public static int wrapCounter(int counterToWrap)
    {
        if (counterToWrap < 0)
        {
            counterToWrap = memory.size() - 1;
        }
        else if (counterToWrap >= memory.size())
        {
            counterToWrap = 0;
        }
        return counterToWrap;
    }

    public static Input getNextCommand()
    {
        counter++;
        counter = wrapCounter(counter);
        return getCommand(counter);
    }

    public static Input getPrevCommand()
    {
        counter--;
        counter = wrapCounter(counter);
        return getCommand(counter);
    }
}
