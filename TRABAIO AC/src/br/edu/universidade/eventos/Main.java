package br.edu.universidade.eventos;

import java.util.ArrayList;
import java.util.List;

abstract class PessoaResponsavel {
    protected String nome;
    protected String email;

    public PessoaResponsavel(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
}

class Instrutor extends PessoaResponsavel {
    private String formacao;
    private String areaEspecializacao;

    public Instrutor(String nome, String email, String formacao, String areaEspecializacao) {
        super(nome, email);
        this.formacao = formacao;
        this.areaEspecializacao = areaEspecializacao;
    }
}

class Organizador extends PessoaResponsavel {
    private String funcaoAdministrativa;

    public Organizador(String nome, String email, String funcaoAdministrativa) {
        super(nome, email);
        this.funcaoAdministrativa = funcaoAdministrativa;
    }
}

abstract class Local {
    protected String nome;
    protected int capacidade;
    protected String bloco;

    public Local(String nome, int capacidade, String bloco) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.bloco = bloco;
    }

    public String getNome() { return nome; }
}

class Sala extends Local {
    private int numero;
    private boolean multimidia;

    public Sala(String nome, int capacidade, String bloco, int numero, boolean multimidia) {
        super(nome, capacidade, bloco);
        this.numero = numero;
        this.multimidia = multimidia;
    }
}

class Auditorio extends Local {
    private boolean som;
    private boolean traducaoSimultanea;

    public Auditorio(String nome, int capacidade, String bloco, boolean som, boolean traducaoSimultanea) {
        super(nome, capacidade, bloco);
        this.som = som;
        this.traducaoSimultanea = traducaoSimultanea;
    }
}

class Laboratorio extends Local {
    private int computadores;
    private String tipo;

    public Laboratorio(String nome, int capacidade, String bloco, int computadores, String tipo) {
        super(nome, capacidade, bloco);
        this.computadores = computadores;
        this.tipo = tipo;
    }
}

abstract class Evento {
    protected String titulo;
    protected String data;
    protected int duracaoHoras;
    protected double orcamento;
    protected Local local;
    protected List<Participante> participantes;

    public Evento(String titulo, String data, int duracaoHoras, double orcamento, Local local) {
        this.titulo = titulo;
        this.data = data;
        this.duracaoHoras = duracaoHoras;
        this.orcamento = orcamento;
        this.local = local;
        this.participantes = new ArrayList<>();
    }

    public void adicionarParticipante(Participante p) {
        if (participantes.size() < local.capacidade) {
            participantes.add(p);
        } else {
            System.out.println("Não há vagas no evento: " + titulo);
        }
    }
}

class Palestra extends Evento {
    private String palestrante;
    private String emailPalestrante;
    private String instituicao;

    public Palestra(String titulo, String data, int duracao, double orcamento, Local local,
                    String palestrante, String email, String instituicao) {
        super(titulo, data, duracao, orcamento, local);
        this.palestrante = palestrante;
        this.emailPalestrante = email;
        this.instituicao = instituicao;
    }
}

class Minicurso extends Evento {
    private List<Instrutor> instrutores;
    private int cargaHoraria;
    private List<String> materiais;

    public Minicurso(String titulo, String data, int duracao, double orcamento, Local local,
                     int cargaHoraria) {
        super(titulo, data, duracao, orcamento, local);
        this.cargaHoraria = cargaHoraria;
        this.instrutores = new ArrayList<>();
        this.materiais = new ArrayList<>();
    }

    public void adicionarInstrutor(Instrutor i) { instrutores.add(i); }
    public void adicionarMaterial(String m) { materiais.add(m); }
}

class Seminario extends Evento {
    private String tema;
    private String alunoApresentador;
    private String orientador;

    public Seminario(String titulo, String data, int duracao, double orcamento, Local local,
                     String tema, String alunoApresentador, String orientador) {
        super(titulo, data, duracao, orcamento, local);
        this.tema = tema;
        this.alunoApresentador = alunoApresentador;
        this.orientador = orientador;
    }
}

class Participante {
    private String nome;
    private String cpf;
    private String email;
    private String curso;

    public Participante(String nome, String cpf, String email, String curso) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.curso = curso;
    }
}

public class Main {
    public static void main(String[] args) {
        Sala sala101 = new Sala("Sala 101", 30, "Bloco A", 101, true);
        Auditorio aud1 = new Auditorio("Auditório Central", 200, "Bloco B", true, true);
        Laboratorio lab1 = new Laboratorio("Lab de Informática", 20, "Bloco C", 20, "Informática");

        Instrutor profJoao = new Instrutor("João Silva", "joao@uni.com", "Mestre em TI", "Redes");
        Organizador maria = new Organizador("Maria Souza", "maria@uni.com", "Coordenação");

        Palestra palestra = new Palestra("Inovação em IA", "15/11/2025", 2, 1500, aud1,
                "Dr. Pedro", "pedro@tech.com", "UFRJ");
        Minicurso minicurso = new Minicurso("Java Avançado", "20/11/2025", 4, 800, sala101, 8);
        minicurso.adicionarInstrutor(profJoao);
        minicurso.adicionarMaterial("Apostila.pdf");

        Seminario seminario = new Seminario("Pesquisa em Big Data", "25/11/2025", 3, 500, lab1,
                "Big Data e Sociedade", "Ana Lima", "Prof. João");

        Participante enzo = new Participante("Enzo", "123.456.789-00", "enzo@email.com", "Engenharia");
        palestra.adicionarParticipante(enzo);

        System.out.println("Sistema de Eventos Acadêmicos criado com sucesso!");
    }
}
