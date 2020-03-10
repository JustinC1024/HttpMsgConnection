import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * 发送HTTP请求
 * @author chm
 */
public class SendHttpMsg {

    /**
     * POST方式传输
     * @param data 请求报文
     * @param url 接收路径
     * @param encode 报文编码
     * @return 相应报文
     * @throws Exception
     */
    public String sendToPost(String data,String url,String encode) throws Exception {
        StringBuilder result = new StringBuilder();
        //构造URL
        URL post_url = new URL(url);
        //创建连接
        HttpURLConnection connection = (HttpURLConnection)post_url.openConnection();
        connection.setConnectTimeout(7000);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-type","text/xml;charset=utf-8");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        //传输数据
        OutputStream out = connection.getOutputStream();
        out.write(data.getBytes(encode));
        //状态码
        int code = connection.getResponseCode();
        if (200 ==code) {
            //获取返回数据
            InputStream in = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in,"GBK");
            BufferedReader buffer = new BufferedReader(reader);
            String line = null;
            while (null != (line = buffer.readLine())) {
                result.append(line);
            }
            in.close();
            reader.close();
            buffer.close();
        }
        out.close();
        return result.toString();
    }

    /**
     * GET方式传输
     * @param data 请求报文
     * @param url 接收路径
     * @return 相应报文
     * @throws Exception
     */
    public String sendToGet(Map<String,String> data,String url) throws Exception {
        StringBuilder result = new StringBuilder();
        //构造URL
        URL get_url = new URL(getGetUrl(url,data));
        //创建连接
        HttpURLConnection connection = (HttpURLConnection)get_url.openConnection();
        connection.setConnectTimeout(7000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        //状态码
        int code = connection.getResponseCode();
        if (200 == code) {
            //获取返回数据
            InputStream in = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader buffer = new BufferedReader(reader);
            String line = null;
            while (null != (line = buffer.readLine())) {
                result.append(line);
            }
            in.close();
            reader.close();
            buffer.close();
        }
        return result.toString();
    }

    /**
     * 拼接GET方式传输的URL
     * @param url_main 接收路径
     * @param data 请求数据
     * @return 带参URL
     */
    private static String getGetUrl(String url_main,Map<String,String> data){
        StringBuilder url = new StringBuilder(url_main+"?");
        for (String key : data.keySet()) {
            String temp = data.get(key);
            url.append(key+"="+temp.replaceAll(" ", "%20")+"&");
        }
        return url.substring(0, url.length()-1);
    }

}
