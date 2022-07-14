package br.com.alura.comex.dominio.cliente;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.comex.infra.cliente.ClienteRepositoryComJPA;

public class IdClienteValidation implements ConstraintValidator<ValidaIdCliente, Long> {

    @Autowired
    private ClienteRepositoryComJPA clienteRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return clienteRepository.findById(value).isPresent();
    }
}
