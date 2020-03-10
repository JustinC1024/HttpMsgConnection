/**
 * 获取方法名
 * @author chm
 */
public class MethodName {

    /**
     * 编写GET&SET方法
     * @param type 类型
     * @param name 属性字段名
     * @return 方法名
     * @throws Exception
     */
    public String getMethodName(String type,String name) throws Exception{
        StringBuilder result = new StringBuilder();
        byte[] items = name.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        result.append(type);
        result.append(new String(items));
        return result.toString();
    }

}
