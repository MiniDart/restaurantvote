package com.restaurant_vote.util;

import com.restaurant_vote.AuthorizedUser;
import com.restaurant_vote.model.Role;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeUtil {
    public static <S,T> T merge (S source, T target){
        try {
            List<Field> sourceFields=new ArrayList<>();
            List<Method> sourceMethods=new ArrayList<>();
            getAllFieldsAndMethods(sourceFields,sourceMethods,source.getClass());
            for (Field field:sourceFields){
                if (Modifier.isStatic(field.getModifiers())) continue;
                Method get=getMethod(sourceMethods,field,source.getClass(),true);
                Object filedVal=get.invoke(source,null);
                if (filedVal!=null&&!field.getName().equals("id")){
                    List<Field> targetFields=new ArrayList<>();
                    List<Method> targetMethods=new ArrayList<>();
                    getAllFieldsAndMethods(targetFields,targetMethods,target.getClass());
                    Field targetField=findFieldByName(targetFields,field);
                    if (targetField!=null&&isAllowedToMerge(targetField)) {
                        Method set=getMethod(targetMethods,targetField,target.getClass(),false);
                        set.invoke(target,filedVal);
                    }
                    else throw new IllegalArgumentException("Should be field "+field.getName()+" with proper restrictions in class "+target.getClass().getSimpleName());
                }
            }
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }
        catch (InvocationTargetException e){
            e.printStackTrace();
        }

        return target;
    }

    private static Field findFieldByName(List<Field> fields, Field fieldToCheck){
        for (Field field:fields){
            if (field.getName().equals(fieldToCheck.getName())) return field;
        }
        return null;
    }

    private static String bigFirstLetter(String word){
        return word.replaceFirst(word.substring(0,1),word.substring(0,1).toUpperCase());
    }

    private static boolean isAllowedToMerge(Field field){
        MergeRestriction[] restrictions=field.getAnnotationsByType(MergeRestriction.class);
        if (restrictions==null||restrictions.length==0) return true;
        for (MergeRestriction m:restrictions){
            for (String role:m.roles()){
                if (AuthorizedUser.get().getAuthorities().contains(Role.valueOf(role))) return true;
            }
        }
        return false;
    }

    private static boolean isGetter(Method method, Field field){
        String fieldName=bigFirstLetter(field.getName());
        return  (method.getName().equals("get"+fieldName)||method.getName().equals("is"+fieldName));
    }

    private static boolean isSetter(Method method, Field field){
        String fieldName=bigFirstLetter(field.getName());
        return  (method.getName().equals("set"+fieldName));
    }

    private static void getAllFieldsAndMethods(List<Field> fields, List<Method> methods, Class<?> clazz){
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        methods.addAll(Arrays.asList(clazz.getMethods()));
        if (clazz.getSuperclass()!=null){
            getAllFieldsAndMethods(fields,methods, clazz.getSuperclass());
        }
    }

    private static Method getMethod(List<Method> methods,Field field,Class<?> clazz,boolean isGetter){
        Method m=null;
        for (Method method:methods){
            if (isGetter?isGetter(method,field):isSetter(method,field)){
                m=method;
                break;
            }
        }
        if (m==null) throw new IllegalArgumentException("Should be "+(isGetter?"getter":"setter")+" for field "+field.getName()+
                " in class "+clazz.getSimpleName());
        return m;
    }
}
