import java.util.ArrayList;
import java.util.List;

/**
 * HTTP请求
 * @author chm
 */
public abstract class HttpUtil {
    SendHttpMsg send = new SendHttpMsg();

    /**
     * XML报文
     * @param request_object 请求报文类
     * @param respond_object 响应报文类
     * @param url 发送路径
     * @param encode 报文编码格式
     */
    public void doMsgXML(Object request_object, Object respond_object, String url, String encode) {
        StructRequestMsg request = new StructRequestXML();
        StructRespondMsg respond = new StructRespondXML();
        try {
            String data = request.reflectMsg(request_object);//请求报文class->String
            String param = completeXML(data);
            StringBuilder result = new StringBuilder();
            result.append(send.sendToPost(param, url, encode));//发送报文
            if ( result != null) {
                respond.reflectMsg(respond_object, result.toString(), null);//响应报文String->class
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * XML报文（接收大量数据）
     * @param object 请求报文类
     * @param clazz 响应报文类
     * @param url 发送路径
     * @param encode 报文编码格式
     * @param cut 分割标识
     * @return 响应报文List
     */
    public List<Object> doMsgXML(Object object, Class<?> clazz, String url, String encode, String cut) {
        StructRequestMsg request = new StructRequestXML();
        StructRespondMsg respond = new StructRespondXML();
        List<Object> list = new ArrayList<Object>();
        try {
            String data = request.reflectMsg(object);//请求报文class->String
            String param = completeXML(data);
            StringBuilder result = new StringBuilder();
            result.append(send.sendToPost(param, url, encode));//发送报文
            if ( result != null) {
                String bgn = "<"+cut+">";
                String[] results = result.toString().split(bgn);
                if (results.length > 1) {//有返回数据
                    for (int i = 1; i < results.length; i++) {
                        StringBuilder sb = new StringBuilder();
                        if (i == results.length-1) {//是否在末端
                            String end = "</"+cut+">";
                            String[] ss = results[i].split(end);
                            sb.append(bgn);
                            sb.append(ss[0]);
                            sb.append(end);
                        }else {
                            sb.append(bgn);
                            sb.append(results[i]);
                        }
                        Object obj = clazz.newInstance();
                        respond.reflectMsg(obj, sb.toString(), null);//响应报文String->class
                        list.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
        return list;
    }

    /**
     * 拼凑XML
     * @param data 报文内容
     * @return 完整的XML报文
     */
    private String completeXML(String data) {
        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"GBK\"?><PGK><DATA> <![CDATA[<?xml version=\"1.0\" encoding =\"GBK\"?>");
        result.append(data);
        result.append("]]></DATA><CHECKCODE>");
        result.append(GetCheckSumWithCRC32(data));
        result.append("</CHECKCODE></PGK>");
        return result.toString();
    }

    /**
     * 生成识别码
     * @param xmlData 报文内容
     * @return 识别号
     */
    abstract String GetCheckSumWithCRC32(String xmlData);

}
