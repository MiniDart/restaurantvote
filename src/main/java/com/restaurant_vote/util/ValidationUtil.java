package com.restaurant_vote.util;


import com.restaurant_vote.model.AbstractBaseEntity;
import com.restaurant_vote.to.BaseTo;
import com.restaurant_vote.util.exception.NotFoundException;

import java.util.Optional;

public class ValidationUtil {
    private static final String NOT_NEW_ENTITY_MESSAGE="must be new (id=null)";
    private static final String NEW_ENTITY_MESSAGE="must be not new (id!=null)";

    public static <T> T check(Optional<T> optional, int id){
        if (!optional.isPresent()) throwNotFoundException(id);
        return optional.get();
    }

    public static void check(int count, int id){
        if (count==0) throwNotFoundException("Error. There is no entity with id="+id);
    }

    public static void check(int count, String msg){
        if (count==0) throwNotFoundException(msg);
    }

    public static <T> T check(Optional<T> optional,String msg){
        if (!optional.isPresent()) throwNotFoundException(msg);
        return optional.get();
    }

    public static void checkNew(AbstractBaseEntity entity){
        if (!entity.isNew()) throwIllegalArgumentException(entity,NOT_NEW_ENTITY_MESSAGE);
    }

     public static void checkId(AbstractBaseEntity entity,int id){
        checkId(entity.getId(),id);
     }

     public static void checkId(Integer entityId,int id){
         if (id!=(entityId!=null?entityId:-1)) throw new IllegalArgumentException("Entity must be with id=" + id);
     }

     public static void checkNew(BaseTo to){
         if (!to.isNew()) throwIllegalArgumentException(to,NOT_NEW_ENTITY_MESSAGE);
     }

     public static void checkNotNull(Object object, String name){
         if (object==null) throwIllegalArgumentException(name+" must not be null");
     }

     public static void checkNotNew(BaseTo to) {
         if (to.isNew()) throwIllegalArgumentException(to, NEW_ENTITY_MESSAGE);
     }
    public static void checkNotNew(AbstractBaseEntity entity) {
        if (entity.isNew()) throwIllegalArgumentException(entity, NEW_ENTITY_MESSAGE);
    }

    public static void throwNotFoundException(int id){
         throwNotFoundException("Error. Can't find entity with id="+id);
     }
     public static void throwNotFoundException(String msg){
         throw new NotFoundException(msg);
     }

     public static void throwIllegalArgumentException(Object object, String msg){
         throwIllegalArgumentException(object.toString() +" "+msg);
     }

     public static void throwIllegalArgumentException(String msg){
         throw new IllegalArgumentException(msg);
     }

     public static void throwIllegalArgumentException(String name, int id){
         throwIllegalArgumentException("Can't create vote - there is no "+name+" with id="+id);
     }
}
