package sample.model;

/**
 * Created by Андрій on 20.11.2016.
 */
public class IXmlException extends Exception {


    public IXmlException(String tagName, String message) {
        super("<"+tagName+"> ERROR: " + message);
    }
}
