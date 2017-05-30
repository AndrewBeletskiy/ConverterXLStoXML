package sample.model;

/**
 * Created by Андрій on 29.10.2016.
 */
public class Declar implements IXml{
    private ConstantXML xmlHeader;
    public DeclarHead declarhead;
    public DeclarBody declarbody;

    public Declar() {
        this.declarhead = new DeclarHead();
        this.declarbody = new DeclarBody();
        xmlHeader = new ConstantXML("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    }

    public String toXML () {
        StringBuilder str = new StringBuilder();
        str.append(xmlHeader.toXML()).append('\n');
        str.append("<DECLAR xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"J1201008.xsd\">").append('\n').append('\t')
                .append(declarhead.toXML()).append('\n').append('\t')
                .append(declarbody.toXML()).append('\n')
                .append("</DECLAR>");
        return str.toString();
    }
}
