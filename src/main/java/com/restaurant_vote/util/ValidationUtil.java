package com.restaurant_vote.util;


import com.restaurant_vote.model.AbstractBaseEntity;
import com.restaurant_vote.util.exception.NotFoundException;

import java.util.Optional;

public class ValidationUtil {
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

    public static void checkNew(AbstractBaseEntity entity){
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

     public static void checkId(AbstractBaseEntity entity,int id){
        if (id!=(entity.getId()!=null?entity.getId():-1)) throw new IllegalArgumentException(entity + " must be with id=" + id);
     }

     private static void throwNotFoundException(int id){
         throwNotFoundException("Error. There is no entity with id="+id);
     }
     private static void throwNotFoundException(String msg){
         throw new NotFoundException(msg);
     }
}
