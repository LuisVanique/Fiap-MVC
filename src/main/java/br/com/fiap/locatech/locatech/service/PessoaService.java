package br.com.fiap.locatech.locatech.service;

import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAllPessoas(int page, int size){
        int offset = (page - 1) * size;
        return this.pessoaRepository.findAll(size, offset);
    }

    public Optional<Pessoa> findPessoaById(Long id){
        return this.pessoaRepository.findById(id);
    }

    public void savePessoa(Pessoa pessoa){
        var save = pessoaRepository.save(pessoa);
        Assert.state(save == 1,
                "Erro ao salvar pessoa " + pessoa.getNome());
    }

    public void updatePessoa(Pessoa pessoa, Long id){
        var update = pessoaRepository.update(pessoa, id);
        if(update == 0){
            throw new RuntimeException("Pessoa nao encontrada");
        }
    }

    public void delete(Long id){
        var delete = pessoaRepository.delete(id);
        if(delete == 0){
            throw new RuntimeException("Veiculo nao encontrado");
        }
    }
}
