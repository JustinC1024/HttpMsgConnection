import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析响应报文
 * @author chm
 */
public abstract class StructRespondMsg {
    MethodName methodName = new MethodName();

    /**
     * 解析响应报文
     * @param object 响应实体类
     * @param data 响应报文
     * @param parent 父标签
     */
    public void reflectMsg(Object object, String data, String parent) throws Exception{
        Field[] objects = object.getClass().getDeclaredFields();
        for (Field field : objects) {
            //属性访问权限
            boolean flag = field.isAccessible();
            try {
                field.setAccessible(true);
                String name = field.getName();
                if (!"class java.lang.String".equals(field.getGenericType().toString())) {//属性类型不为String
                    if (field.get(object) != null) {//属性是否实例化
                        Method method = object.getClass().getMethod(methodName.getMethodName("get",name));
                        reflectMsg(method.invoke(object), data, field.getName());
                    }
                }else {
                    Pattern pattern = Pattern.compile(getDataRegular(parent,name));
                    Matcher matcher = pattern.matcher(data);
                    if (matcher.matches()){//属性值存在
                        String value = matcher.group(1);
                        Method method = object.getClass().getDeclaredMethod(methodName.getMethodName("set", name), String.class);
                        method.invoke(object, value);
                    }
                }
            }finally {
                field.setAccessible(flag);
            }
        }
    }

    /**
     * 构造正则表达式
     * @param parent 父标签
     * @param key 子标签
     * @return
     */
    abstract String getDataRegular(String parent, String key);

}
