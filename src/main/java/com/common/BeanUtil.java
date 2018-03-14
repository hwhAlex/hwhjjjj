//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.common;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

public class BeanUtil {
    private static Map<String, BeanCopier> beanCopierMap = new HashMap();

    public BeanUtil() {
    }

    public static void copyProperties(Object source, Object target) {
        if(null != source && null != target) {
            String beanKey = generateKey(source.getClass(), target.getClass());
            BeanCopier copier = null;
            if(!beanCopierMap.containsKey(beanKey)) {
                copier = BeanCopier.create(source.getClass(), target.getClass(), false);
                beanCopierMap.put(beanKey, copier);
            } else {
                copier = (BeanCopier)beanCopierMap.get(beanKey);
            }

            copier.copy(source, target, (Converter)null);
        }
    }

  /*  public static <T> T from(Object source, Class<T> clazz) {
        if(null == source) {
            return null;
        } else {
            try {
                Object e = clazz.newInstance();
                copyProperties(source, e);
                return e;
            } catch (Exception var3) {
                throw new RuntimeException("copyProperties ERROR, obj: " + source.getClass() + " clz: " + clazz, var3);
            }
        }
    }*/

  /*  public static <T> List<T> fromList(Collection<? extends Object> list, Class<T> clazz) {
        ArrayList result = new ArrayList();
        if(null != list) {
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                Object source = var3.next();
                result.add(from(source, clazz));
            }
        }

        return result;
    }
*/
    public static void setProperty(Object bean, String name, Object value) {
        if(value != null) {
            Class beanClass = bean.getClass();
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(beanClass);
            PropertyDescriptor[] var5 = propertyDescriptors;
            int var6 = propertyDescriptors.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                PropertyDescriptor property = var5[var7];
                String key = property.getName();
                if(key.equals(name)) {
                    Method writeMethod = property.getWriteMethod();

                    try {
                        writeMethod.invoke(bean, new Object[]{value});
                        break;
                    } catch (Exception var12) {
                        throw new FatalBeanException("Could not copy property \'" + property.getName() + "\' to target", var12);
                    }
                }
            }

        }
    }

    public static Object getProperty(Object bean, String name) {
        if(bean == null) {
            return null;
        } else {
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(bean.getClass());
            PropertyDescriptor[] var3 = propertyDescriptors;
            int var4 = propertyDescriptors.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                PropertyDescriptor property = var3[var5];
                String key = property.getName();
                if(key.equals(name)) {
                    Method getter = property.getReadMethod();

                    try {
                        return getter.invoke(bean, new Object[0]);
                    } catch (Exception var10) {
                        throw new FatalBeanException("Could not copy property \'" + property.getName() + "\' from source to target", var10);
                    }
                }
            }

            return null;
        }
    }

   /* public static <T> Map<String, Object> beanToMap(T bean, boolean isToUnderlineCase, String[] properties, boolean exclude) {
        if(bean == null) {
            return null;
        } else {
            HashMap map = new HashMap();
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(bean.getClass());
            PropertyDescriptor[] var6 = propertyDescriptors;
            int var7 = propertyDescriptors.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                PropertyDescriptor property = var6[var8];
                String key = property.getName();
                if(isCopy(key)) {
                    Method getter = property.getReadMethod();

                    try {
                        Object e = getter.invoke(bean, new Object[0]);
                        if(null != e) {
                            key = isToUnderlineCase?StringUtil.toUnderline(key, false):key;
                            if(properties != null && properties.length > 0) {
                                if(exclude && !Arrays.asList(properties).contains(key)) {
                                    map.put(key, e);
                                } else if(!exclude && Arrays.asList(properties).contains(key)) {
                                    map.put(key, e);
                                }
                            } else {
                                map.put(key, e);
                            }
                        }
                    } catch (Exception var13) {
                        throw new FatalBeanException("Could not copy property \'" + property.getName() + "\' from source to target", var13);
                    }
                }
            }

            return map;
        }
    }*/

    public static boolean isCopy(String key) {
        return !key.equals("serialVersionUID") && !key.equals("class") && !key.equals("instance");
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }
}
