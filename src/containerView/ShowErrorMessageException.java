/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerView;

/**
 *
 * @author Admin
 */
public class ShowErrorMessageException extends Exception{
    public ShowErrorMessageException(){
        super();
    }
    public ShowErrorMessageException(String message){
        super(message);
    }
}
