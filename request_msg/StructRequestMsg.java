import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

/**
 * 构造请求报文
 * @author chm
 */
public abstract class StructRequestMsg {
    MethodName methodName = new MethodName();

    /**
     * 接收实体类
     * @param object 请求实体类
     * @return
     */
    public String reflectMsg(Object object) throws Exception {
        StringBuilder result = new StringBuilder();
        Map<String, Object> map = new Hashtable<String, Object>();
        Field[] objects = object.getClass().getDeclaredFields();
        for (Field field : objects) {
            //属性访问权限
            boolean flag = field.isAccessible();
            try {
                field.setAccessible(true);
                if (field.get(object) != null) {//属性值不为空
                    if (!"class java.lang.String".equals(field.getGenericType().toString())) {//属性类型不为String
                        Method method = object.getClass().getMethod(methodName.getMethodName("get", field.getName()));
                        map.put(field.getName(), reflectMsg(method.invoke(object)));
                    } else {
                        map.put(field.getName(), field.get(object));
                    }
                }
            }finally {
                field.setAccessible(flag);
            }
        }
        result.append(doReqiestMsg(map));
        return result.toString();
    }

    /**
     * 构造报文
     * @param map 存放实体类的MAP
     * @return
     */
    abstract String doReqiestMsg( Map<String, Object> map);

}
