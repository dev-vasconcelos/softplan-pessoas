package br.com.softplan.pessoas.interfaces;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.softplan.pessoas.common.DataNascimento;

public class DataNascimentoValidator implements ConstraintValidator<DataNascimento, Date> {

	@Override
	public boolean isValid(Date nascimento, ConstraintValidatorContext context) {
		if(nascimento.after(new Date())) {
			return false;
		}

		Date jan_1900 = new Date(-2208988799000L);
		if(nascimento.before(jan_1900)) {
			return false;
		}

		return true;
	}
}
