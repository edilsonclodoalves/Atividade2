import java.io.Serializable;

// Uma classe que terá seus objetos serializados deve implementar a interface Serializable
public class Musicos implements Serializable {
    String nome;
    int idade;
    String banda;

    public Musicos(String nome, int idade, String banda) {
        this.nome = nome;
        this.idade = idade;
        this.banda = banda;
    }

    public void Imprimir() {
        System.out.println("----------------------------");
        System.out.println("Nome...: " + nome);
        System.out.println("Idade...: " + idade);
        System.out.println("Banda...: " + banda);
        System.out.println("----------------------------\n");
    }
}
