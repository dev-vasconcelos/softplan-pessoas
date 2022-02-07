package br.com.softplan.pessoas.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.softplan.pessoas.interfaces.DataNascimentoValidator;

@Documented
@Constraint(validatedBy = DataNascimentoValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataNascimento {

    String message() default "Data de nascimento inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}