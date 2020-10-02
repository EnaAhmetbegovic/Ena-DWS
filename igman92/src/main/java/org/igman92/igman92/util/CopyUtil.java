package org.igman92.igman92.util;

import org.igman92.igman92.dao.exceptions.ProjectException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;

import java.lang.reflect.Field;
import java.util.*;

public final class CopyUtil {

    private CopyUtil() {
    }

    public static void copyOnly(Object src, Object trg, String... props) {

        copyOnly(src, trg, new ArrayList<>(Arrays.asList(props)));
    }

    public static void copyOnly(Object src, Object trg, Collection<String> props) {

        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);

        props.forEach(p -> trgWrap.setPropertyValue(p, srcWrap.getPropertyValue(p)));

    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }

    public static void copyNonNullProps(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static void copyOnlyIfNotNull(Object src, Object trg, String... props)  {
        copyOnlyIfNotNull(src, trg, new ArrayList<>(Arrays.asList(props)));
    }

    public static void copyOnlyIfNotNull(Object src, Object trg, Collection<String> props)  {

        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);

        for (String p : props) {
            try {
                if (src.getClass().getDeclaredField(p) != null) {
                    trgWrap.setPropertyValue(p, srcWrap.getPropertyValue(p));
                }
            } catch (NoSuchFieldException e) {
                throw new ProjectException("Field does not exist!", e);
            }
        }
    }

    public static void copyExceptIfNotNull(Object src, Object trg, String... props)  {

        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);

        boolean flag;

        for (Field field : src.getClass().getDeclaredFields()) {

            flag = false;

            for (String prop : props) {

                if (prop.equals(field.getName())) {
                    flag = true;
                    break;
                }
            }
            try {
                field.setAccessible(true);
                if (!flag && field.get(src) != null) {
                    trgWrap.setPropertyValue(field.getName(), srcWrap.getPropertyValue(field.getName()));
                }
            } catch (IllegalAccessException e) {
                throw new ProjectException("Field does not exist!", e);
            }
        }
    }
}
