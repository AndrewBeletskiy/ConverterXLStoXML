package sample.model;

import sample.RegExConst;

/**
 * Created by Андрій on 29.10.2016.
 */
public class DeclarHead implements IXml{
    public TextXMLTag TIN;
    public ConstantXML C_DOC;
    public ConstantXML C_DOC_SUB;
    public ConstantXML C_DOC_VER;
    public TextXMLTag C_DOC_TYPE;
    public TextXMLTag C_DOC_CNT;
    public TextXMLTag C_REG;
    public TextXMLTag C_RAJ;
    public TextXMLTag PERIOD_MONTH;
    public TextXMLTag PERIOD_TYPE;
    public TextXMLTag PERIOD_YEAR;
    public TextXMLTag C_STI_ORIG;
    public TextXMLTag C_DOC_STAN;
    public ConstantXML LINKED_DOCS;
    public TextXMLTag D_FILL;
    public ConstantXML SOFTWARE;


    public DeclarHead() {
        TIN = new TextXMLTag("TIN", RegExConst.DGLong, 10);
        C_DOC = new ConstantXML("<C_DOC>J12</C_DOC>");
        C_DOC_SUB = new ConstantXML("<C_DOC_SUB>010</C_DOC_SUB>");
        C_DOC_VER = new ConstantXML("<C_DOC_VER>8</C_DOC_VER>");
        C_DOC_TYPE = new TextXMLTag("C_DOC_TYPE", RegExConst.nonNegativeInteger);
        C_DOC_CNT = new TextXMLTag("C_DOC_CNT", RegExConst.nonNegativeInteger);
        C_REG = new TextXMLTag("C_REG", RegExConst.DGsti);
        C_RAJ = new TextXMLTag("C_RAJ", RegExConst.DGsti);
        PERIOD_MONTH = new TextXMLTag("PERIOD_MONTH", RegExConst.DGMonth);
        PERIOD_TYPE = new TextXMLTag("PERIOD_TYPE", RegExConst.DGPType);
        PERIOD_YEAR = new TextXMLTag("PERIOD_YEAR", RegExConst.DGYear);
        C_STI_ORIG = new TextXMLTag("C_STI_ORIG", RegExConst.DGc_dpi);
        C_DOC_STAN = new TextXMLTag("C_DOC_STAN", RegExConst.DGSTAN);
        LINKED_DOCS = new ConstantXML("<LINKED_DOCS xsi:nil=\"true\" />");
        D_FILL = new TextXMLTag("D_FILL", RegExConst.DGDate, 8);
        SOFTWARE = new ConstantXML("");
    }

    public String toXML() {
        return "<DECLARHEAD>\n\t\t" + getContent() + "</DECLARHEAD>";
    }

    private String getContent() {
        StringBuilder res = new StringBuilder();
        res.append(TIN.toXML()).append("\n\t\t")
                .append(C_DOC.toXML()).append("\n\t\t")
                .append(C_DOC_SUB.toXML()).append("\n\t\t")
                .append(C_DOC_VER.toXML()).append("\n\t\t")
                .append(C_DOC_TYPE.toXML()).append("\n\t\t")
                .append(C_DOC_CNT.toXML()).append("\n\t\t")
                .append(C_REG.toXML()).append("\n\t\t")
                .append(C_RAJ.toXML()).append("\n\t\t")
                .append(PERIOD_MONTH.toXML()).append("\n\t\t")
                .append(PERIOD_TYPE.toXML()).append("\n\t\t")
                .append(PERIOD_YEAR.toXML()).append("\n\t\t")
                .append(C_STI_ORIG.toXML()).append("\n\t\t")
                .append(C_DOC_STAN.toXML()).append("\n\t\t")
                .append(LINKED_DOCS.toXML()).append("\n\t\t")
                .append(D_FILL.toXML()).append("\n\t");
                //.append(SOFTWARE.toXML()).;
        return res.toString();
    }
}

