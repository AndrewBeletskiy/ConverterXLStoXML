package sample.model;

/**
 * Created by Андрій on 20.11.2016.
 */
public class ConstantXML implements IXml {

    private String constantXMLContent;

    public ConstantXML(String constantXMLContent) {
        this.constantXMLContent = constantXMLContent;
    }

    public String toXML() {
        if (constantXMLContent == null)
            return "";
            //throw new IXmlException("CONSTANT XML","Constant XML Error: constant value doesn't exist.");

        return constantXMLContent;
    }
}
