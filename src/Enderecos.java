public class Enderecos {
    public String Rua;
    public Integer Numero;

    public Enderecos() {
    }

    public Enderecos(String rua, Integer numero) {
        Rua = rua;
        Numero = numero;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }

    public Integer getNumero() {
        return Numero;
    }

    public void setNumero(Integer numero) {
        Numero = numero;
    }
}
