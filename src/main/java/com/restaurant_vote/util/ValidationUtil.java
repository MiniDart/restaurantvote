package com.restaurant_vote.util;


import com.restaurant_vote.model.AbstractBaseEntity;
import com.restaurant_vote.util.exception.NotFoundException;

import java.util.Optional;

public class ValidationUtil {
    public static <T> T check(Optional<T> optional){
        if (!optional.isPresent()) throw new NotFoundException("Sorry. There is no such entity");
        return optional.get();
    }

    public static void check(int count){
        if (count==0) throw new NotFoundException("Sorry. There is no such entity");
    }

    public static void checkNew(AbstractBaseEntity entity){
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

     public static void checkId(AbstractBaseEntity entity,int id){
        if (id!=(entity.getId()!=null?entity.getId():-1)) throw new IllegalArgumentException(entity + " must be with id=" + id);
     }
}
