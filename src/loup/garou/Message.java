/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou;

import java.io.Serializable;

/**
 *
 * @author jrobert.cda
 */
public class Message implements Serializable{
    
    private String etape;
    private Object content;

    public Message() {;
    }

    public String getEtape() {
        return etape;
    }

    public void setEtape(String etape) {
        this.etape = etape;
    }

    public Object getContent() {
        if(this.etape=="NAME"){
            return (String) content;
        }
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" + "etape=" + etape + ", content=" + (String)content + '}';
    }
    
    
    
    
    
    
}
