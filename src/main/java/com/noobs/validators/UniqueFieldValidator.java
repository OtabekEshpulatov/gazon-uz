package com.noobs.validators;

import com.noobs.gazonuz.domains.BaseEntity;
import com.noobs.gazonuz.repositories.BaseDAO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class UniqueFieldValidator implements ConstraintValidator<Unique, String> {

    String column;
    String table;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.column = constraintAnnotation.columnName();
        this.table = constraintAnnotation.tableName();
    }

    @Override
    public boolean isValid(String value , ConstraintValidatorContext context) {
        BaseDAO<BaseEntity, String> baseDAO = new BaseDAO<>() {
        };
        List value1 = baseDAO.em.createNativeQuery("select t from " + this.table + " t where t." + column + "=:value").setParameter("value" , value).getResultList();
        return value1.isEmpty();
    }
}