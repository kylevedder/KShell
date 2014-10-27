/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class CommandMemory
{

    private static ArrayList<CommandLine> memory = new ArrayList<CommandLine>();
    private static int counter = -1;

    /**
     * Adds a command to the list of remembered commands.
     *
     * @param cl
     */
    public static void add(CommandLine cl)
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
     * Gets the CommandLine of the given index. "0" returns the most recent
     * command, with higher numbers being the less recent commands.
     *
     * @param index Index of command to get. "0" is the first command.
     * @return Specified CommandLine object.
     */
    public static CommandLine getCommand(int index)
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

    public static CommandLine getNextCommand()
    {
        counter++;
        counter = wrapCounter(counter);
        return getCommand(counter);
    }

    public static CommandLine getPrevCommand()
    {
        counter--;
        counter = wrapCounter(counter);
        return getCommand(counter);
    }
}
