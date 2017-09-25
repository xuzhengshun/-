package demo2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 类转换工具
 * 
 * @Description:TODO
 * @exception:
 * @author: 徐正顺
 * @time:2017年9月25日 上午10:54:27
 */
public class ClassConversionUtil {

	public static void main(String[] args) {
		Person per = new Person();
		per.setId(19931018);
		per.setName("徐正顺");
		per.setAge(24);
		per.setDate(parseDate("1993-11-02"));
		per.setAddress("河南省息县项店镇徐集村");

		ChinesePeople chinese = new ChinesePeople();
		ChinesePeople setFieldValue = (ChinesePeople) setFieldValue(per, chinese);
		System.out.println(setFieldValue.toString());
	}

	/**
	 * 将原始对象fromData属性值赋值给转换对象toData
	 * @Description:TODO
	 * @param fromData
	 * @param toData
	 * @return
	 * Object
	 * @exception:
	 * @author: 徐正顺
	 * @time:2017年9月25日 下午2:13:22
	 */
	public static Object setFieldValue(Object fromData, Object toData) {
		//1：将原始对象转换成键值对形式的map
		Map<String, String> valMap = getFieldValueMap(fromData);
		
		
		Class<?> cls = toData.getClass();  
        // 取出bean里的所有方法  
        Method[] methods = cls.getDeclaredMethods();  
        Field[] fields = cls.getDeclaredFields();  
  
        //2:根据反射为toData赋值
        for (Field field : fields) {  
            try {  
                String fieldSetName = parSetName(field.getName());  
                if (!checkSetMet(methods, fieldSetName)) {  
                    continue;  
                }  
                Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());  
                String value = valMap.get(field.getName());  
                if (null != value && !"".equals(value)) {  
                    String fieldType = field.getType().getSimpleName();  
                    if ("String".equals(fieldType)) {  
                        fieldSetMet.invoke(toData, value);  
                    } else if ("Date".equals(fieldType)) {  
                        Date temp = parseDate(value);  
                        fieldSetMet.invoke(toData, temp);  
                    } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {  
                        Integer intval = Integer.parseInt(value);  
                        fieldSetMet.invoke(toData, intval);  
                    } else if ("Long".equalsIgnoreCase(fieldType)) {  
                        Long temp = Long.parseLong(value);  
                        fieldSetMet.invoke(toData, temp);  
                    } else if ("Double".equalsIgnoreCase(fieldType)) {  
                        Double temp = Double.parseDouble(value);  
                        fieldSetMet.invoke(toData, temp);  
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {  
                        Boolean temp = Boolean.parseBoolean(value);  
                        fieldSetMet.invoke(toData, temp);  
                    } else {  
                        System.out.println("not supper type" + fieldType);  
                    }  
                }  
            } catch (Exception e) {  
                continue;  
            }  
        }  
        return toData;

	}
	
	
	/** 
     * 取Bean的属性和值对应关系的MAP 
     * @param bean 
     * @return Map 
     */  
    public static Map<String, String> getFieldValueMap(Object bean) {  
        Class<?> cls = bean.getClass();  
        Map<String, String> valueMap = new HashMap<String, String>();  
        // 取出bean里的所有方法  
        Method[] methods = cls.getDeclaredMethods();  
        Field[] fields = cls.getDeclaredFields();  
  
        for (Field field : fields) {  
            try {  
                String fieldType = field.getType().getSimpleName();  
                String fieldGetName = parGetName(field.getName());  
                if (!checkGetMet(methods, fieldGetName)) {  
                    continue;  
                }  
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});  
                Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});  
                String result = null;  
                if ("Date".equals(fieldType)) {  
                    result = fmtDate((Date) fieldVal);  
                } else {  
                    if (null != fieldVal) {  
                        result = String.valueOf(fieldVal);  
                    }  
                }  
                valueMap.put(field.getName(), result);  
            } catch (Exception e) {  
                continue;  
            }  
        }  
        return valueMap;  
  
    }  
  
	
	
	 /** 
     * 判断是否存在某属性的 get方法 
     * @param methods 
     * @param fieldGetMet 
     * @return boolean 
     */  
    private static boolean checkGetMet(Method[] methods, String fieldGetMet) {  
        for (Method met : methods) {  
            if (fieldGetMet.equals(met.getName())) {  
                return true;  
            }  
        }  
        return false;  
    }  
  
    /** 
     * 拼接某属性的 get方法 
     * @param fieldName 
     * @return String 
     */  
    private static String parGetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        return "get" + fieldName.substring(0, 1).toUpperCase()  
                + fieldName.substring(1);  
    }  
	
	 /** 
     * 拼接在某属性的 set方法 
     * @param fieldName 
     * @return String 
     */  
    private static String parSetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        return "set" + fieldName.substring(0, 1).toUpperCase()  
                + fieldName.substring(1);  
    } 
    
    
    
    
    
    /** 
     * 判断是否存在某属性的 set方法 
     * @param methods 
     * @param fieldSetMet 
     * @return boolean 
     */  
	private static boolean checkSetMet(Method[] methods, String fieldSetMet) {  
        for (Method met : methods) {  
            if (fieldSetMet.equals(met.getName())) {  
                return true;  
            }  
        }  
        return false;  
    }  
    
	/**
	 * 格式化string为Date
	 * 
	 * @param datestr
	 * @return date
	 */
	private static Date parseDate(String datestr) {
		if (null == datestr || "".equals(datestr)) {
			return null;
		}
		try {
			String fmtstr = null;
			if (datestr.indexOf(':') > 0) {
				fmtstr = "yyyy-MM-dd HH:mm:ss";
			} else {
				fmtstr = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
			return sdf.parse(datestr);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 日期转化为String
	 * 
	 * @param date
	 * @return date string
	 */
	private static String fmtDate(Date date) {
		if (null == date) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

}
