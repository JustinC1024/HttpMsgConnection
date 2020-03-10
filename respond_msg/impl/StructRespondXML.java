/**
 * 解析XML报文
 * @author chm
 */
public class StructRespondXML extends StructRespondMsg {
    @Override
    String getDataRegular(String parent,String key) {
        StringBuilder result = new StringBuilder();
        if (parent != null){//是否存在父标签
            result.append(".*<");
            result.append(parent);
            result.append(">.*<");
            result.append(key);
            result.append(">(.*)</");
            result.append(key);
            result.append(">.*</");
            result.append(parent);
            result.append(">.*");
        }else {
            result.append(".*<");
            result.append(key);
            result.append(">");
            result.append("(.*)");
            result.append("</");
            result.append(key);
            result.append(">.*");
        }
        return result.toString();
    }
}
