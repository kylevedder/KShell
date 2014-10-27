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
public class CommandLine
{
    StringBuilder commandString;

    public CommandLine()
    {
        this.commandString = new StringBuilder();
    }   
    
    public CommandLine(String commandString)
    {
        this.commandString = new StringBuilder(commandString);
    }
    
    /**
     * Gets the length of the command
     * @return Length of the command
     */
    public int getLength()
    {
        return this.commandString.length();
    }

    /**
     * Appends the given string to the command.
     * @param s String to append.
     */
    public void append(String s)
    {
        this.commandString.append(s);
    }
    
    /**
     * Inserts the given string at the given point
     * @param start start position
     * @param s String to insert
     */
    public void insert(int start, String s)
    {
        this.commandString.insert(start, s);
    }

    /**
     * Deletes the given region of the string.
     * @param start start value of region
     * @param end end value of the region
     */
    public void delete(int start, int end)
    {
        this.commandString.delete(start, end);
    }
    
    /**
     * Deletes the charecter at the given index.
     * @param index index of charecter to delete. 
     */
    public void delete(int index)
    {
        this.commandString.deleteCharAt(index);
    }
    
    /**
     * Resets the CommandLine object for reuse.
     */
    public void clear()
    {
        this.commandString = new StringBuilder();
    }
    
    public String toLine()
    {
        return "Line" + this.toString();
    }
    
    public String toString()
    {
        return this.commandString.toString();
    }
    
}
