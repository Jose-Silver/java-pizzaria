import java.util.List;

public class pessoa {
    public String nome;
    public Integer idade;
    public List<Enderecos> enderecos;

    public pessoa(String nome, Integer idade, List<Enderecos> enderecos) {
        this.nome = nome;
        this.idade = idade;
        this.enderecos = enderecos;
    }

    public List<Enderecos> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Enderecos> enderecos) {
        this.enderecos = enderecos;
    }

    public void addEndereco(Enderecos enderecos) {
        this.enderecos.add(enderecos);
    }

    public pessoa() {


    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
