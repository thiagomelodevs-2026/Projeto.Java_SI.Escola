
import controller.*;
import model.*;
import enums.Cargo;
import util.Input;
import util.Menu;
import util.Mensagens;
import view.EscolaFrame;

import javax.swing.SwingUtilities;

public class Main {

    private static final ProfessorController professorController =
            new ProfessorController();

    private static final AlunoController alunoController =
            new AlunoController();

    private static final TurmaController turmaController =
            new TurmaController();

    private static final DisciplinaController disciplinaController =
            new DisciplinaController();

    private static final NotaController notaController =
            new NotaController();

    private static final FrequenciaController frequenciaController =
            new FrequenciaController();

    private static final LoginController loginController =
            new LoginController();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EscolaFrame().setVisible(true));
    }

    public static void iniciarConsole() {

        int opcao;

        do {

            Menu.menuPrincipal();

            opcao = Input.lerInteiro();

            switch (opcao) {

                case 1:
                    gerenciarProfessores();
                    break;

                case 2:
                    gerenciarAlunos();
                    break;

                case 3:
                    gerenciarTurmas();
                    break;

                case 4:
                    gerenciarDisciplinas();
                    break;

                case 5:
                    gerenciarNotas();
                    break;

                case 6:
                    gerenciarFrequencias();
                    break;

                case 7:
                    gerenciarUsuarios();
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    Mensagens.opcaoInvalida();
            }

        } while (opcao != 0);
    }
    private static void gerenciarProfessores() {

        int opcao;

        do {

            Menu.menuProfessor();

            opcao = Input.lerInteiro();

            switch (opcao) {

                case 1:
                    cadastrarProfessor();
                    break;

                case 2:
                    listarProfessores();
                    break;

                case 3:
                    atualizarProfessor();
                    break;

                case 4:
                    excluirProfessor();
                    break;

                case 0:
                    break;

                default:
                    Mensagens.opcaoInvalida();
            }

        } while (opcao != 0);
    }

    private static void gerenciarAlunos() {

        int opcao;

        do {

            Menu.menuAluno();

            opcao = Input.lerInteiro();

            switch (opcao) {

                case 1:
                    cadastrarAluno();
                    break;

                case 2:
                    listarAlunos();
                    break;

                case 3:
                    atualizarAluno();
                    break;

                case 4:
                    excluirAluno();
                    break;

                case 0:
                    break;

                default:
                    Mensagens.opcaoInvalida();
            }

        } while (opcao != 0);
    }

    private static void gerenciarTurmas() {

        int opcao;

        do {

            Menu.menuTurma();

            opcao = Input.lerInteiro();

            switch (opcao) {

                case 1:
                    cadastrarTurma();
                    break;

                case 2:
                    listarTurmas();
                    break;

                case 3:
                    atualizarTurma();
                    break;

                case 4:
                    excluirTurma();
                    break;

                case 0:
                    break;

                default:
                    Mensagens.opcaoInvalida();
            }

        } while (opcao != 0);
    }

    private static void gerenciarDisciplinas() {

        int opcao;

        do {

            Menu.menuDisciplina();

            opcao = Input.lerInteiro();

            switch (opcao) {

                case 1:
                    cadastrarDisciplina();
                    break;

                case 2:
                    listarDisciplinas();
                    break;

                case 3:
                    atualizarDisciplina();
                    break;

                case 4:
                    excluirDisciplina();
                    break;

                case 0:
                    break;

                default:
                    Mensagens.opcaoInvalida();
            }

        } while (opcao != 0);
    }

    private static void gerenciarNotas() {

        int opcao;

        do {

            Menu.menuNota();

            opcao = Input.lerInteiro();

            switch (opcao) {

                case 1:
                    cadastrarNota();
                    break;

                case 2:
                    listarNotas();
                    break;

                case 3:
                    atualizarNota();
                    break;

                case 4:
                    excluirNota();
                    break;

                case 0:
                    break;

                default:
                    Mensagens.opcaoInvalida();
            }

        } while (opcao != 0);
    }

    private static void gerenciarFrequencias() {

        int opcao;

        do {

            Menu.menuFrequencia();

            opcao = Input.lerInteiro();

            switch (opcao) {

                case 1:
                    cadastrarFrequencia();
                    break;

                case 2:
                    listarFrequencias();
                    break;

                case 3:
                    lancarFaltas();
                    break;

                case 4:
                    excluirFrequencia();
                    break;

                case 0:
                    break;

                default:
                    Mensagens.opcaoInvalida();
            }

        } while (opcao != 0);
    }

    private static void gerenciarUsuarios() {

        int opcao;

        do {

            Menu.menuUsuario();

            opcao = Input.lerInteiro();

            switch (opcao) {

                case 1:
                    cadastrarUsuario();
                    break;

                case 2:
                    realizarLogin();
                    break;

                case 3:
                    realizarLogout();
                    break;

                case 0:
                    break;

                default:
                    Mensagens.opcaoInvalida();
            }

        } while (opcao != 0);
    }
    private static void cadastrarProfessor() {

        System.out.print("Nome: ");
        String nome = Input.lerTexto();

        System.out.print("Idade: ");
        int idade = Input.lerInteiro();

        System.out.print("CPF: ");
        String cpf = Input.lerTexto();

        System.out.print("Matrícula: ");
        String matricula = Input.lerTexto();

        System.out.print("Salário: ");
        double salario = Input.lerDouble();

        System.out.println("Cargo:");
        System.out.println("1 - Professor");
        System.out.println("2 - Coordenador");
        System.out.println("3 - Diretor");

        int opcaoCargo = Input.lerInteiro();

        Cargo cargo;

        switch (opcaoCargo) {

            case 2:
                cargo = Cargo.COORDENADOR;
                break;

            case 3:
                cargo = Cargo.DIRETOR;
                break;

            default:
                cargo = Cargo.PROFESSOR;
        }

        Professor professor =
                new Professor(
                        nome,
                        idade,
                        cpf,
                        matricula,
                        salario,
                        cargo
                );

        professorController.cadastrarProfessor(professor);
    }
    private static void listarProfessores() {

        System.out.println();

        for (Professor professor :
                professorController.listarProfessores()) {

            System.out.println(professor);
        }
    }
    private static void atualizarProfessor() {

        System.out.print("Matrícula: ");
        String matricula = Input.lerTexto();

        System.out.print("Novo nome: ");
        String novoNome = Input.lerTexto();

        System.out.println(
                professorController.atualizarProfessor(
                        matricula,
                        novoNome
                )
        );
    }
    private static void excluirProfessor() {

        System.out.print("Matrícula: ");
        String matricula = Input.lerTexto();

        System.out.println(
                professorController.deletarProfessor(
                        matricula
                )
        );
    }
    private static void cadastrarTurma() {

        System.out.print("Nome da turma: ");

        String nome = Input.lerTexto();

        Turma turma = new Turma();

        turma.setNome(nome);

        turmaController.cadastrarTurma(turma);
    }
    private static void listarTurmas() {

        System.out.println();

        for (Turma turma :
                turmaController.listarTurmas()) {

            System.out.println(turma);
        }
    }
    private static void atualizarTurma() {

        System.out.print("Nome atual: ");

        String nome = Input.lerTexto();

        System.out.print("Novo nome: ");

        String novoNome = Input.lerTexto();

        System.out.println(
                turmaController.atualizarTurma(
                        nome,
                        novoNome
                )
        );
    }
    private static void excluirTurma() {

        System.out.print("Nome da turma: ");

        String nome = Input.lerTexto();

        System.out.println(
                turmaController.deletarTurma(
                        nome
                )
        );
    }
    private static void cadastrarDisciplina() {

        System.out.print("Nome da disciplina: ");
        String nome = Input.lerTexto();

        System.out.print("Carga horária: ");
        int cargaHoraria = Input.lerInteiro();

        System.out.print("Matrícula do professor: ");
        String matriculaProfessor = Input.lerTexto();

        Professor professor =
                professorController.buscarProfessorPorMatricula(
                        matriculaProfessor
                );

        if (professor == null) {

            System.out.println("Professor não encontrado.");
            return;
        }

        Disciplina disciplina =
                new Disciplina(
                        nome,
                        cargaHoraria,
                        professor
                );

        disciplinaController.cadastrarDisciplina(
                disciplina
        );
    }
    private static void listarDisciplinas() {

        System.out.println();

        for (Disciplina disciplina :
                disciplinaController.listarDisciplinas()) {

            System.out.println(disciplina);
        }
    }
    private static void atualizarDisciplina() {

        System.out.print("Nome atual: ");
        String nomeAtual = Input.lerTexto();

        System.out.print("Novo nome: ");
        String novoNome = Input.lerTexto();

        System.out.print("Nova carga horária: ");
        int novaCarga = Input.lerInteiro();

        System.out.println(
                disciplinaController.atualizarDisciplina(
                        nomeAtual,
                        novoNome,
                        novaCarga
                )
        );
    }
    private static void excluirDisciplina() {

        System.out.print("Nome da disciplina: ");

        String nome = Input.lerTexto();

        System.out.println(
                disciplinaController.deletarDisciplina(
                        nome
                )
        );
    }
    private static void cadastrarAluno() {

        System.out.print("Nome: ");
        String nome = Input.lerTexto();

        System.out.print("Idade: ");
        int idade = Input.lerInteiro();

        System.out.print("CPF: ");
        String cpf = Input.lerTexto();

        System.out.print("Matrícula: ");
        String matricula = Input.lerTexto();

        System.out.print("Nome da turma: ");
        String nomeTurma = Input.lerTexto();

        Turma turma =
                turmaController.buscarTurmaPorNome(
                        nomeTurma
                );

        if (turma == null) {

            System.out.println("Turma não encontrada.");
            return;
        }

        Aluno aluno =
                new Aluno(
                        nome,
                        idade,
                        cpf,
                        turma,
                        matricula
                );

        alunoController.cadastrarAluno(aluno);

        turma.adicionarAluno(aluno);
    }
    private static void listarAlunos() {

        System.out.println();

        for (Aluno aluno :
                alunoController.listarAlunos()) {

            System.out.println(aluno);
        }
    }
    private static void atualizarAluno() {

        System.out.print("Matrícula: ");
        String matricula = Input.lerTexto();

        System.out.print("Novo nome: ");
        String novoNome = Input.lerTexto();

        System.out.println(
                alunoController.atualizarAluno(
                        matricula,
                        novoNome
                )
        );
    }
    private static void excluirAluno() {

        System.out.print("Matrícula: ");
        String matricula = Input.lerTexto();

        System.out.println(
                alunoController.deletarAluno(
                        matricula
                )
        );
    }
    private static void cadastrarNota() {

        System.out.print("Matrícula do aluno: ");
        String matriculaAluno = Input.lerTexto();

        Aluno aluno =
                alunoController.buscarAlunoPorMatricula(
                        matriculaAluno
                );

        if (aluno == null) {

            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Nome da disciplina: ");
        String nomeDisciplina = Input.lerTexto();

        Disciplina disciplina =
                disciplinaController.buscarDisciplinaPorNome(
                        nomeDisciplina
                );

        if (disciplina == null) {

            System.out.println("Disciplina não encontrada.");
            return;
        }

        System.out.print("Nota: ");
        double valor = Input.lerDouble();

        Nota nota =
                new Nota(
                        aluno,
                        disciplina,
                        valor
                );

        notaController.cadastrarNota(nota);
    }
    private static void listarNotas() {

        System.out.println();

        for (Nota nota :
                notaController.listarNotas()) {

            System.out.println(nota);
        }
    }
    private static void atualizarNota() {

        System.out.print("Matrícula do aluno: ");
        String matricula = Input.lerTexto();

        System.out.print("Nome da disciplina: ");
        String disciplina = Input.lerTexto();

        System.out.print("Nova nota: ");
        double novaNota = Input.lerDouble();

        System.out.println(
                notaController.atualizarNota(
                        matricula,
                        disciplina,
                        novaNota
                )
        );
    }
    private static void excluirNota() {

        System.out.print("Matrícula do aluno: ");
        String matricula = Input.lerTexto();

        System.out.print("Nome da disciplina: ");
        String disciplina = Input.lerTexto();

        System.out.println(
                notaController.deletarNota(
                        matricula,
                        disciplina
                )
        );
    }
    private static void cadastrarFrequencia() {

        System.out.print("Matrícula do aluno: ");
        String matriculaAluno = Input.lerTexto();

        Aluno aluno =
                alunoController.buscarAlunoPorMatricula(
                        matriculaAluno
                );

        if (aluno == null) {

            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Nome da disciplina: ");
        String nomeDisciplina = Input.lerTexto();

        Disciplina disciplina =
                disciplinaController.buscarDisciplinaPorNome(
                        nomeDisciplina
                );

        if (disciplina == null) {

            System.out.println("Disciplina não encontrada.");
            return;
        }

        System.out.print("Quantidade de faltas: ");
        int faltas = Input.lerInteiro();

        Frequencia frequencia =
                new Frequencia(
                        aluno,
                        disciplina,
                        faltas
                );

        frequenciaController.registrarFrequencia(
                frequencia
        );
    }
    private static void listarFrequencias() {

        System.out.println();

        for (Frequencia frequencia :
                frequenciaController.listarFrequencias()) {

            System.out.println(frequencia);
        }
    }
    private static void lancarFaltas() {

        System.out.print("Matrícula do aluno: ");
        String matricula = Input.lerTexto();

        System.out.print("Nome da disciplina: ");
        String disciplina = Input.lerTexto();

        System.out.print("Novas faltas: ");
        int faltas = Input.lerInteiro();

        System.out.println(
                frequenciaController.lancarFaltas(
                        matricula,
                        disciplina,
                        faltas
                )
        );
    }
    private static void excluirFrequencia() {

        System.out.print("Matrícula do aluno: ");
        String matricula = Input.lerTexto();

        System.out.print("Nome da disciplina: ");
        String disciplina = Input.lerTexto();

        System.out.println(
                frequenciaController.deletarFrequencia(
                        matricula,
                        disciplina
                )
        );
    }
    private static void cadastrarUsuario() {

        System.out.println("\nTipo de usuário:");
        System.out.println("1 - Aluno");
        System.out.println("2 - Professor");

        int tipo = Input.lerInteiro();

        Pessoa pessoa;

        if (tipo == 1) {

            System.out.print("Matrícula do aluno: ");

            String matricula =
                    Input.lerTexto();

            pessoa =
                    alunoController
                            .buscarAlunoPorMatricula(
                                    matricula
                            );

            if (pessoa == null) {

                System.out.println(
                        "Aluno não encontrado."
                );
                return;
            }

        } else {

            System.out.print(
                    "Matrícula do professor: "
            );

            String matricula =
                    Input.lerTexto();

            pessoa =
                    professorController
                            .buscarProfessorPorMatricula(
                                    matricula
                            );

            if (pessoa == null) {

                System.out.println(
                        "Professor não encontrado."
                );
                return;
            }
        }

        System.out.print("Login: ");
        String login = Input.lerTexto();

        System.out.print("Senha: ");
        String senha = Input.lerTexto();

        Cargo cargo;

        if (pessoa instanceof Aluno) {
            cargo = Cargo.ALUNO;
        } else {
            cargo = Cargo.PROFESSOR;
        }

        Usuario usuario =
                new Usuario(
                        login,
                        senha,
                        cargo,
                        pessoa
                );

        loginController.cadastrarUsuario(
                usuario
        );

        System.out.println(
                "Usuário cadastrado!"
        );
    }
    private static void realizarLogin() {

        System.out.print("Login: ");
        String login = Input.lerTexto();

        System.out.print("Senha: ");
        String senha = Input.lerTexto();

        Usuario usuario =
                loginController
                        .autenticarUsuario(
                                login,
                                senha
                        );

        if (usuario != null) {

            System.out.println(
                    "\nLogin realizado!"
            );

            System.out.println(usuario);

        } else {

            System.out.println(
                    "Login ou senha inválidos."
            );
        }
    }
    private static void realizarLogout() {

        loginController.logout();

        System.out.println(
                "Logout realizado."
        );
    }
}
