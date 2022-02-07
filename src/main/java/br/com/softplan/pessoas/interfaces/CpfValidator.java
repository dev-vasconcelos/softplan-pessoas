package br.com.softplan.pessoas.interfaces;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.softplan.pessoas.common.Cpf;

public class CpfValidator implements ConstraintValidator<Cpf, String> {

	private final int[] PESO_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		String cpfReplace = cpf.replaceAll("\\D", "");

		if ((cpfReplace == null) || (cpfReplace.length() != 11) || cpfReplace.equals("00000000000")
				|| cpfReplace.equals("11111111111") || cpfReplace.equals("22222222222")
				|| cpfReplace.equals("33333333333") || cpfReplace.equals("44444444444")
				|| cpfReplace.equals("55555555555") || cpfReplace.equals("66666666666")
				|| cpfReplace.equals("77777777777") || cpfReplace.equals("88888888888")
				|| cpfReplace.equals("99999999999")) {
			return false;
		}

		Integer digito1 = calcularDigito(cpfReplace.substring(0, 9), PESO_CPF);
		Integer digito2 = calcularDigito(cpfReplace.substring(0, 9) + digito1, PESO_CPF);

		return cpfReplace.equals(cpfReplace.substring(0, 9) + digito1.toString() + digito2.toString());
	}

	private int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
}
