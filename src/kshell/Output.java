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
public class Output
{

    private StringBuilder sb;
    private boolean enterHit = false;

    public Output(String value)
    {
        this.sb = new StringBuilder(value);
    }

    public Output()
    {
        this.sb = new StringBuilder();
    }

    public Output(StringBuilder sb)
    {
        this.sb = sb;
    }

    public Output(Output output)
    {
        this.sb = new StringBuilder(output.getString());
    }

    /**
     * Sets that enter has been hit.
     */
    public void enterHit()
    {
        this.enterHit = true;
    }

    /**
     * Gets if enter has been hit
     * @return 
     */
    public boolean isEnterHit()
    {
        return enterHit;
    }
    
    
    
    /**
     * Is the String value empty.
     *
     * @return
     */
    public boolean isEmpty()
    {
        return !(this.sb.length() > 0);
    }

    /**
     * Adds the given String to the String value.
     *
     * @param s
     */
    public void appendString(String s)
    {
        this.sb.append(s);
    }

    /**
     * Adds the given char to the String value.
     *
     * @param s
     */
    public void appendChar(char s)
    {
        this.sb.append(String.valueOf(s));
    }

    /**
     * Deletes the last char from the String.
     */
    public void deleteLastChar()
    {
        if (this.sb.length() > 0)
        {
            this.sb.deleteCharAt(this.sb.length() - 1);
        }
    }

    /**
     * Sets the given String to the String value.
     *
     * @param value
     */
    public void setString(String value)
    {
        this.sb = this.sb.append(value);
    }

    /**
     * Gets the String value of the object.
     *
     * @return
     */
    public String getString()
    {
        return sb.toString();
    }
    
    /**
     * Gets the line string for the object.
     * 
     * This means the herald, plus the actual content.
     * @return 
     */
    public String toLine()
    {
        return ShellVars.getNewLineString() + this.toString();
    }

    @Override
    public String toString()
    {
        return getString();
    }

}
