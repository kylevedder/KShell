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
     * Is the String value empty.
     * @return 
     */
    public boolean isEmpty()
    {
        return !(this.sb.length() > 0);
    }

    /**
     * Adds the given String to the String value.
     * @param s 
     */
    public void appendString(String s)
    {
        this.sb.append(s);
    }
    
    /**
     * Sets the given String to the String value.
     * @param value 
     */
    public void setString(String value)
    {
        this.sb = this.sb.append(value);
    }

        
    /**
     * Gets the String value of the object.
     * @return 
     */
    public String getString()
    {
        return sb.toString();
    }

    @Override
    public String toString()
    {
        return getString();
    }
    
    
}
