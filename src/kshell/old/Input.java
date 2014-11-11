/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell.old;

import kshell.ShellVars;

/**
 *
 * @author Kyle
 */
class Input
{

    private StringBuilder commandString;

    public Input()
    {
        this.commandString = new StringBuilder();
    }

    public Input(String commandString)
    {
        this.commandString = new StringBuilder(commandString);
    }

    /**
     * Gets the length of the command
     *
     * @return Length of the command
     */
    public int getLength()
    {
        return this.commandString.length();
    }

    /**
     * Appends the given string to the command.
     *
     * @param s String to append.
     */
    public void append(String s)
    {
        this.commandString.append(s);
    }

    /**
     * Inserts the given string at the given point
     *
     * @param start start position
     * @param s String to insert
     */
    public void insert(int start, String s)
    {
        this.commandString.insert(start, s);
    }

    /**
     * Deletes the given region of the string.
     *
     * @param start start value of region
     * @param end end value of the region
     */
    public void delete(int start, int end)
    {
        this.commandString.delete(start, end);
    }

    /**
     * Deletes the charecter at the given index.
     *
     * @param index index of charecter to delete.
     */
    public void delete(int index)
    {
        this.commandString.deleteCharAt(index);
    }

    /**
     * Resets the Input object for reuse.
     */
    public void clear()
    {
        this.commandString = new StringBuilder();
    }

    /**
     * Returns if the command is empty.
     * @return is the command empty.
     */
    public boolean isEmpty()
    {
        return !(this.commandString.length() > 0);
    }

    public String toLine()
    {
        return ShellVars.getNewLineString() + this.toString();
    }

    public String toString()
    {
        return this.commandString.toString();
    }

}
