import java.util.Map;
/**
 * 构造XML请求报文
 * @author chm
 */
public class StructRequestXML extends StructRequestMsg {
    @Override
    String doReqiestMsg(Map<String, Object> map) {
        StringBuilder result = new StringBuilder();
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                result.append("<");
                result.append(entry.getKey());
                result.append(">");
                result.append(entry.getValue().toString());
                result.append("</");
                result.append(entry.getKey());
                result.append(">");
            }
        }
        return result.toString();
    }
}
