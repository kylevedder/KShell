/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old.kshell;

/**
 *
 * @author kyle
 */
class Response
{
    boolean success;
    String message;

    public Response(boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }

    /**
     * If command ran succesfully.
     * @return If command ran succesfully.
     */
    public boolean getSuccess()
    {
        return success;
    }

    /**
     * Gets the message of the response.
     * @return message of the response.
     */
    public String getMessage()
    {
        return message;
    }
    
    
    
    
}
