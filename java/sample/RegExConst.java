package sample;

/**
 * Created by Андрій on 22.11.2016.
 */
public interface RegExConst {
    String DGLong = "([0-9]{5,10}|[АБВГДЕЄЖЗИІКЛМНОПРСТУФХЦЧШЩЮЯ]{2}[0-9]{6})";
    String nonNegativeInteger = "[0-9]*";
    String DGsti = "[1-9][0-9]?";
    String DGMonth = "([1-9]|1[0-2])";
    String DGPType = "[1-5]";
    String DGYear = "(198[1-9]|199[0-9]|20[0-9]{2})";
    String DGc_dpi = "([1-9][0-9]{2}|1[0-9]{3}|2[0-8][0-9]{2})";
    String DGSTAN = "(1|2|3)";
    String DGDate = "((((0[1-9]|[1-2][0-9])(0(1|[3-9])|1[0-2]))|(30(0(1|[3-9])|1[0-2]))|(31(01|03|05|07|08|10|12)))(19|20)\\d{2})|((0[1-9]|[1-2][0-9])02(19|20)(([0|2|4|6|8][0|4|8])|([1|3|5|7|9][2|6]))|(0[1-9]|[1-2][0-8]|19)02(19|20)(([0|2|4|6|8][1-3|5-7|9])|([1|3|5|7|9][0-1|3-5|7-9])))";
    String DGchk = "1";
    String DGPNtypr = "0[1-9]|1[0-7]";
    String DGI7nom = "[1-9][0-9]{0,6}";
    String DGspecNom = "(2)|(3)|(4)|(5)|(7)";
    String DGI4nom = "[1-9][0-9]{0,3}";
    String DGHNAME = ".+";
    String DGdecimal2_P = "[0-9]+\\.[0-9]{2}";
    String DGHBOS = ".+";
    String DGHNPDV = "[1-9]\\d{3,11}";

    String DGUKTZED = "([0-9]){10}|([0-9]){4}|([0-9]){6}|([0])";
    String DGI4lz = "[0-9]{4}";
    String DGdecimal6_R = "\\-{0,1}[0-9]+(\\.[0-9]{1,6}){0,1}";
    String DGdecimal12_R = "\\-{0,1}[0-9]+(\\.[0-9]{1,12}){0,1}";
    String DGI3nom = "[1-9]\\d{0,2}";
    String DGCodPilg = "\\d{8}";
    String ANY = "^.*$";
}
