package com.estudo.api_biblioteca.dto.request.validation;

import com.estudo.api_biblioteca.dto.request.EmprestimoRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DataDevolucaoValidator implements ConstraintValidator<DataDevolucaoValida, EmprestimoRequestDTO> {


    @Override
    public void initialize(DataDevolucaoValida constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EmprestimoRequestDTO value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.getDataEmprestimo() == null || value.getDataDevolucaoPrevista() == null) {
            return true;
        }
        return !value.getDataDevolucaoPrevista().isBefore(value.getDataEmprestimo());
    }

}
