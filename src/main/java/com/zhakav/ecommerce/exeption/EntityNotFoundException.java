package com.zhakav.ecommerce.exeption;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Object id, String entityType, String idType){

        super("The "+entityType+" with "+idType+" : "+id+", does not exist in our database!!!");

    }


}
