package view;

import controller.AlunoController;
import controller.DisciplinaController;
import controller.FrequenciaController;
import controller.NotaController;
import controller.ProfessorController;
import controller.TurmaController;
import enums.Cargo;
import model.Aluno;
import model.Disciplina;
import model.Frequencia;
import model.Nota;
import model.Professor;
import model.Turma;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public class EscolaFrame extends JFrame {

    private final ProfessorController professorController = new ProfessorController();
    private final AlunoController alunoController = new AlunoController();
    private final TurmaController turmaController = new TurmaController();
    private final DisciplinaController disciplinaController = new DisciplinaController();
    private final NotaController notaController = new NotaController();
    private final FrequenciaController frequenciaController = new FrequenciaController();

    private final DefaultTableModel professoresModel = tableModel("Matricula", "Nome", "Idade", "CPF", "Cargo", "Salario");
    private final DefaultTableModel turmasModel = tableModel("Nome", "Alunos", "Disciplinas");
    private final DefaultTableModel alunosModel = tableModel("Matricula", "Nome", "Idade", "CPF", "Turma");
    private final DefaultTableModel disciplinasModel = tableModel("Nome", "Carga horaria", "Professor");
    private final DefaultTableModel notasModel = tableModel("Matricula", "Aluno", "Disciplina", "Nota");
    private final DefaultTableModel frequenciasModel = tableModel("Matricula", "Aluno", "Disciplina", "Faltas");

    private JTextField professorNome;
    private JTextField professorIdade;
    private JTextField professorCpf;
    private JTextField professorMatricula;
    private JTextField professorSalario;
    private JComboBox<Cargo> professorCargo;

    private JTextField turmaNome;

    private JTextField alunoNome;
    private JTextField alunoIdade;
    private JTextField alunoCpf;
    private JTextField alunoMatricula;
    private JComboBox<String> alunoTurma;

    private JTextField disciplinaNome;
    private JTextField disciplinaCarga;
    private JComboBox<String> disciplinaProfessor;

    private JComboBox<String> notaAluno;
    private JComboBox<String> notaDisciplina;
    private JTextField notaValor;

    private JComboBox<String> frequenciaAluno;
    private JComboBox<String> frequenciaDisciplina;
    private JTextField frequenciaFaltas;

    public EscolaFrame() {
        super("Sistema de Gestao Escolar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1040, 680));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(header(), BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Professores", professorPanel());
        tabs.addTab("Turmas", turmaPanel());
        tabs.addTab("Alunos", alunoPanel());
        tabs.addTab("Disciplinas", disciplinaPanel());
        tabs.addTab("Notas", notaPanel());
        tabs.addTab("Frequencias", frequenciaPanel());
        add(tabs, BorderLayout.CENTER);

        seedExampleData();
        refreshAll();
    }

    private JPanel header() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(18, 22, 16, 22));
        panel.setBackground(new Color(28, 44, 52));

        JLabel title = new JLabel("Sistema de Gestao Escolar");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel subtitle = new JLabel("Cadastros, turmas, notas e frequencias");
        subtitle.setForeground(new Color(207, 222, 225));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setHorizontalAlignment(SwingConstants.RIGHT);

        panel.add(title, BorderLayout.WEST);
        panel.add(subtitle, BorderLayout.EAST);
        return panel;
    }

    private JPanel professorPanel() {
        professorNome = field();
        professorIdade = field();
        professorCpf = field();
        professorMatricula = field();
        professorSalario = field();
        professorCargo = new JComboBox<>(new Cargo[]{Cargo.PROFESSOR, Cargo.COORDENADOR, Cargo.DIRETOR});

        JPanel form = formPanel();
        addField(form, 0, "Nome", professorNome);
        addField(form, 1, "Idade", professorIdade);
        addField(form, 2, "CPF", professorCpf);
        addField(form, 3, "Matricula", professorMatricula);
        addField(form, 4, "Salario", professorSalario);
        addField(form, 5, "Cargo", professorCargo);

        JTable table = table(professoresModel);
        JButton save = button("Salvar");
        save.addActionListener(e -> saveProfessor());
        JButton update = button("Atualizar nome");
        update.addActionListener(e -> updateProfessor(table));
        JButton delete = dangerButton("Excluir");
        delete.addActionListener(e -> deleteProfessor(table));
        JButton clear = button("Limpar");
        clear.addActionListener(e -> clearProfessorForm());

        return crudPanel(form, table, save, update, delete, clear);
    }

    private JPanel turmaPanel() {
        turmaNome = field();
        JPanel form = formPanel();
        addField(form, 0, "Nome da turma", turmaNome);

        JTable table = table(turmasModel);
        JButton save = button("Salvar");
        save.addActionListener(e -> saveTurma());
        JButton update = button("Atualizar nome");
        update.addActionListener(e -> updateTurma(table));
        JButton delete = dangerButton("Excluir");
        delete.addActionListener(e -> deleteTurma(table));
        JButton clear = button("Limpar");
        clear.addActionListener(e -> turmaNome.setText(""));

        return crudPanel(form, table, save, update, delete, clear);
    }

    private JPanel alunoPanel() {
        alunoNome = field();
        alunoIdade = field();
        alunoCpf = field();
        alunoMatricula = field();
        alunoTurma = new JComboBox<>();

        JPanel form = formPanel();
        addField(form, 0, "Nome", alunoNome);
        addField(form, 1, "Idade", alunoIdade);
        addField(form, 2, "CPF", alunoCpf);
        addField(form, 3, "Matricula", alunoMatricula);
        addField(form, 4, "Turma", alunoTurma);

        JTable table = table(alunosModel);
        JButton save = button("Salvar");
        save.addActionListener(e -> saveAluno());
        JButton update = button("Atualizar nome");
        update.addActionListener(e -> updateAluno(table));
        JButton delete = dangerButton("Excluir");
        delete.addActionListener(e -> deleteAluno(table));
        JButton clear = button("Limpar");
        clear.addActionListener(e -> clearAlunoForm());

        return crudPanel(form, table, save, update, delete, clear);
    }

    private JPanel disciplinaPanel() {
        disciplinaNome = field();
        disciplinaCarga = field();
        disciplinaProfessor = new JComboBox<>();

        JPanel form = formPanel();
        addField(form, 0, "Nome", disciplinaNome);
        addField(form, 1, "Carga horaria", disciplinaCarga);
        addField(form, 2, "Professor", disciplinaProfessor);

        JTable table = table(disciplinasModel);
        JButton save = button("Salvar");
        save.addActionListener(e -> saveDisciplina());
        JButton update = button("Atualizar");
        update.addActionListener(e -> updateDisciplina(table));
        JButton delete = dangerButton("Excluir");
        delete.addActionListener(e -> deleteDisciplina(table));
        JButton clear = button("Limpar");
        clear.addActionListener(e -> clearDisciplinaForm());

        return crudPanel(form, table, save, update, delete, clear);
    }

    private JPanel notaPanel() {
        notaAluno = new JComboBox<>();
        notaDisciplina = new JComboBox<>();
        notaValor = field();

        JPanel form = formPanel();
        addField(form, 0, "Aluno", notaAluno);
        addField(form, 1, "Disciplina", notaDisciplina);
        addField(form, 2, "Nota", notaValor);

        JTable table = table(notasModel);
        JButton save = button("Salvar");
        save.addActionListener(e -> saveNota());
        JButton update = button("Atualizar nota");
        update.addActionListener(e -> updateNota(table));
        JButton delete = dangerButton("Excluir");
        delete.addActionListener(e -> deleteNota(table));
        JButton clear = button("Limpar");
        clear.addActionListener(e -> notaValor.setText(""));

        return crudPanel(form, table, save, update, delete, clear);
    }

    private JPanel frequenciaPanel() {
        frequenciaAluno = new JComboBox<>();
        frequenciaDisciplina = new JComboBox<>();
        frequenciaFaltas = field();

        JPanel form = formPanel();
        addField(form, 0, "Aluno", frequenciaAluno);
        addField(form, 1, "Disciplina", frequenciaDisciplina);
        addField(form, 2, "Faltas", frequenciaFaltas);

        JTable table = table(frequenciasModel);
        JButton save = button("Salvar");
        save.addActionListener(e -> saveFrequencia());
        JButton update = button("Lancar faltas");
        update.addActionListener(e -> updateFrequencia(table));
        JButton delete = dangerButton("Excluir");
        delete.addActionListener(e -> deleteFrequencia(table));
        JButton clear = button("Limpar");
        clear.addActionListener(e -> frequenciaFaltas.setText(""));

        return crudPanel(form, table, save, update, delete, clear);
    }

    private JPanel crudPanel(JPanel form, JTable table, JButton... buttons) {
        JPanel panel = new JPanel(new BorderLayout(16, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        panel.setBackground(new Color(245, 247, 248));

        JPanel left = new JPanel(new BorderLayout(0, 12));
        left.setPreferredSize(new Dimension(320, 520));
        left.setBackground(Color.WHITE);
        left.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(217, 224, 226)),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        JPanel actions = new JPanel(new GridBagLayout());
        actions.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridx = 0;
        for (int i = 0; i < buttons.length; i++) {
            gbc.gridy = i;
            actions.add(buttons[i], gbc);
        }

        left.add(form, BorderLayout.NORTH);
        left.add(actions, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(217, 224, 226)));

        panel.add(left, BorderLayout.WEST);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel formPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        return panel;
    }

    private void addField(JPanel panel, int row, String label, Component component) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row * 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 4, 0);

        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(jLabel, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 0, 12, 0);
        panel.add(component, gbc);
    }

    private JTextField field() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(240, 32));
        return field;
    }

    private JButton button(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(160, 34));
        return button;
    }

    private JButton dangerButton(String text) {
        JButton button = button(text);
        button.setForeground(new Color(150, 36, 36));
        return button;
    }

    private JTable table(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        return table;
    }

    private DefaultTableModel tableModel(String... columns) {
        return new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void saveProfessor() {
        try {
            require(professorNome, "Informe o nome do professor.");
            require(professorMatricula, "Informe a matricula do professor.");
            Professor professor = new Professor(
                    professorNome.getText().trim(),
                    parseInt(professorIdade, "Idade invalida."),
                    professorCpf.getText().trim(),
                    professorMatricula.getText().trim(),
                    parseDouble(professorSalario, "Salario invalido."),
                    (Cargo) professorCargo.getSelectedItem()
            );
            professorController.cadastrarProfessor(professor);
            clearProfessorForm();
            refreshAll();
            info("Professor salvo.");
        } catch (IllegalArgumentException ex) {
            error(ex.getMessage());
        }
    }

    private void updateProfessor(JTable table) {
        int row = selectedRow(table);
        if (row < 0) {
            return;
        }
        String matricula = value(table, row, 0);
        String novoNome = prompt("Novo nome", value(table, row, 1));
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            info(professorController.atualizarProfessor(matricula, novoNome.trim()));
            refreshAll();
        }
    }

    private void deleteProfessor(JTable table) {
        int row = selectedRow(table);
        if (row >= 0 && confirm("Excluir professor selecionado?")) {
            info(professorController.deletarProfessor(value(table, row, 0)));
            refreshAll();
        }
    }

    private void saveTurma() {
        try {
            require(turmaNome, "Informe o nome da turma.");
            Turma turma = new Turma();
            turma.setNome(turmaNome.getText().trim());
            turmaController.cadastrarTurma(turma);
            turmaNome.setText("");
            refreshAll();
            info("Turma salva.");
        } catch (IllegalArgumentException ex) {
            error(ex.getMessage());
        }
    }

    private void updateTurma(JTable table) {
        int row = selectedRow(table);
        if (row < 0) {
            return;
        }
        String nomeAtual = value(table, row, 0);
        String novoNome = prompt("Novo nome da turma", nomeAtual);
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            info(turmaController.atualizarTurma(nomeAtual, novoNome.trim()));
            refreshAll();
        }
    }

    private void deleteTurma(JTable table) {
        int row = selectedRow(table);
        if (row >= 0 && confirm("Excluir turma selecionada?")) {
            info(turmaController.deletarTurma(value(table, row, 0)));
            refreshAll();
        }
    }

    private void saveAluno() {
        try {
            require(alunoNome, "Informe o nome do aluno.");
            require(alunoMatricula, "Informe a matricula do aluno.");
            String turmaSelecionada = selectedCombo(alunoTurma, "Cadastre uma turma antes de cadastrar alunos.");
            Turma turma = turmaController.buscarTurmaPorNome(turmaSelecionada);
            Aluno aluno = new Aluno(
                    alunoNome.getText().trim(),
                    parseInt(alunoIdade, "Idade invalida."),
                    alunoCpf.getText().trim(),
                    turma,
                    alunoMatricula.getText().trim()
            );
            alunoController.cadastrarAluno(aluno);
            if (turma != null && !turma.getAlunos().contains(aluno)) {
                turma.adicionarAluno(aluno);
            }
            clearAlunoForm();
            refreshAll();
            info("Aluno salvo.");
        } catch (IllegalArgumentException ex) {
            error(ex.getMessage());
        }
    }

    private void updateAluno(JTable table) {
        int row = selectedRow(table);
        if (row < 0) {
            return;
        }
        String matricula = value(table, row, 0);
        String novoNome = prompt("Novo nome", value(table, row, 1));
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            info(alunoController.atualizarAluno(matricula, novoNome.trim()));
            refreshAll();
        }
    }

    private void deleteAluno(JTable table) {
        int row = selectedRow(table);
        if (row >= 0 && confirm("Excluir aluno selecionado?")) {
            info(alunoController.deletarAluno(value(table, row, 0)));
            refreshAll();
        }
    }

    private void saveDisciplina() {
        try {
            require(disciplinaNome, "Informe o nome da disciplina.");
            String professorKey = selectedCombo(disciplinaProfessor, "Cadastre um professor antes de cadastrar disciplinas.");
            Professor professor = professorController.buscarProfessorPorMatricula(key(professorKey));
            Disciplina disciplina = new Disciplina(
                    disciplinaNome.getText().trim(),
                    parseInt(disciplinaCarga, "Carga horaria invalida."),
                    professor
            );
            disciplinaController.cadastrarDisciplina(disciplina);
            clearDisciplinaForm();
            refreshAll();
            info("Disciplina salva.");
        } catch (IllegalArgumentException ex) {
            error(ex.getMessage());
        }
    }

    private void updateDisciplina(JTable table) {
        int row = selectedRow(table);
        if (row < 0) {
            return;
        }
        String nomeAtual = value(table, row, 0);
        String novoNome = prompt("Novo nome da disciplina", nomeAtual);
        if (novoNome == null || novoNome.trim().isEmpty()) {
            return;
        }
        String carga = prompt("Nova carga horaria", value(table, row, 1));
        if (carga == null || carga.trim().isEmpty()) {
            return;
        }
        try {
            info(disciplinaController.atualizarDisciplina(nomeAtual, novoNome.trim(), Integer.parseInt(carga.trim())));
            refreshAll();
        } catch (NumberFormatException ex) {
            error("Carga horaria invalida.");
        }
    }

    private void deleteDisciplina(JTable table) {
        int row = selectedRow(table);
        if (row >= 0 && confirm("Excluir disciplina selecionada?")) {
            info(disciplinaController.deletarDisciplina(value(table, row, 0)));
            refreshAll();
        }
    }

    private void saveNota() {
        try {
            Aluno aluno = alunoController.buscarAlunoPorMatricula(key(selectedCombo(notaAluno, "Cadastre um aluno antes de lancar notas.")));
            Disciplina disciplina = disciplinaController.buscarDisciplinaPorNome(selectedCombo(notaDisciplina, "Cadastre uma disciplina antes de lancar notas."));
            Nota nota = new Nota(aluno, disciplina, parseDouble(notaValor, "Nota invalida."));
            notaController.cadastrarNota(nota);
            notaValor.setText("");
            refreshAll();
            info("Nota salva.");
        } catch (IllegalArgumentException ex) {
            error(ex.getMessage());
        }
    }

    private void updateNota(JTable table) {
        int row = selectedRow(table);
        if (row < 0) {
            return;
        }
        String novaNota = prompt("Nova nota", value(table, row, 3));
        if (novaNota == null || novaNota.trim().isEmpty()) {
            return;
        }
        try {
            info(notaController.atualizarNota(value(table, row, 0), value(table, row, 2), Double.parseDouble(novaNota.trim())));
            refreshAll();
        } catch (NumberFormatException ex) {
            error("Nota invalida.");
        }
    }

    private void deleteNota(JTable table) {
        int row = selectedRow(table);
        if (row >= 0 && confirm("Excluir nota selecionada?")) {
            info(notaController.deletarNota(value(table, row, 0), value(table, row, 2)));
            refreshAll();
        }
    }

    private void saveFrequencia() {
        try {
            Aluno aluno = alunoController.buscarAlunoPorMatricula(key(selectedCombo(frequenciaAluno, "Cadastre um aluno antes de registrar frequencia.")));
            Disciplina disciplina = disciplinaController.buscarDisciplinaPorNome(selectedCombo(frequenciaDisciplina, "Cadastre uma disciplina antes de registrar frequencia."));
            Frequencia frequencia = new Frequencia(aluno, disciplina, parseInt(frequenciaFaltas, "Quantidade de faltas invalida."));
            frequenciaController.registrarFrequencia(frequencia);
            frequenciaFaltas.setText("");
            refreshAll();
            info("Frequencia salva.");
        } catch (IllegalArgumentException ex) {
            error(ex.getMessage());
        }
    }

    private void updateFrequencia(JTable table) {
        int row = selectedRow(table);
        if (row < 0) {
            return;
        }
        String faltas = prompt("Novas faltas", "1");
        if (faltas == null || faltas.trim().isEmpty()) {
            return;
        }
        try {
            info(frequenciaController.lancarFaltas(value(table, row, 0), value(table, row, 2), Integer.parseInt(faltas.trim())));
            refreshAll();
        } catch (NumberFormatException ex) {
            error("Quantidade de faltas invalida.");
        }
    }

    private void deleteFrequencia(JTable table) {
        int row = selectedRow(table);
        if (row >= 0 && confirm("Excluir frequencia selecionada?")) {
            info(frequenciaController.deletarFrequencia(value(table, row, 0), value(table, row, 2)));
            refreshAll();
        }
    }

    private void refreshAll() {
        refreshProfessores();
        refreshTurmas();
        refreshAlunos();
        refreshDisciplinas();
        refreshNotas();
        refreshFrequencias();
        refreshCombos();
    }

    private void refreshProfessores() {
        professoresModel.setRowCount(0);
        for (Professor p : professorController.listarProfessores()) {
            professoresModel.addRow(new Object[]{p.getMatricula(), p.getNome(), p.getIdade(), p.getCpf(), p.getCargo(), p.getSalario()});
        }
    }

    private void refreshTurmas() {
        turmasModel.setRowCount(0);
        for (Turma t : turmaController.listarTurmas()) {
            turmasModel.addRow(new Object[]{t.getNome(), t.getAlunos().size(), t.getDisciplinas().size()});
        }
    }

    private void refreshAlunos() {
        alunosModel.setRowCount(0);
        for (Aluno a : alunoController.listarAlunos()) {
            alunosModel.addRow(new Object[]{a.getMatricula(), a.getNome(), a.getIdade(), a.getCpf(), a.getTurma() != null ? a.getTurma().getNome() : ""});
        }
    }

    private void refreshDisciplinas() {
        disciplinasModel.setRowCount(0);
        for (Disciplina d : disciplinaController.listarDisciplinas()) {
            disciplinasModel.addRow(new Object[]{d.getNome(), d.getCargaHoraria(), d.getProfessor() != null ? d.getProfessor().getNome() : ""});
        }
    }

    private void refreshNotas() {
        notasModel.setRowCount(0);
        for (Nota n : notaController.listarNotas()) {
            notasModel.addRow(new Object[]{
                    n.getAluno() != null ? n.getAluno().getMatricula() : "",
                    n.getAluno() != null ? n.getAluno().getNome() : "",
                    n.getDisciplina() != null ? n.getDisciplina().getNome() : "",
                    n.getValor()
            });
        }
    }

    private void refreshFrequencias() {
        frequenciasModel.setRowCount(0);
        for (Frequencia f : frequenciaController.listarFrequencias()) {
            frequenciasModel.addRow(new Object[]{
                    f.getAluno() != null ? f.getAluno().getMatricula() : "",
                    f.getAluno() != null ? f.getAluno().getNome() : "",
                    f.getDisciplina() != null ? f.getDisciplina().getNome() : "",
                    f.getFaltas()
            });
        }
    }

    private void refreshCombos() {
        replaceItems(alunoTurma, turmaNames());
        replaceItems(disciplinaProfessor, professorKeys());
        replaceItems(notaAluno, alunoKeys());
        replaceItems(notaDisciplina, disciplinaNames());
        replaceItems(frequenciaAluno, alunoKeys());
        replaceItems(frequenciaDisciplina, disciplinaNames());
    }

    private String[] turmaNames() {
        List<Turma> turmas = turmaController.listarTurmas();
        String[] names = new String[turmas.size()];
        for (int i = 0; i < turmas.size(); i++) {
            names[i] = turmas.get(i).getNome();
        }
        return names;
    }

    private String[] professorKeys() {
        List<Professor> professores = professorController.listarProfessores();
        String[] keys = new String[professores.size()];
        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            keys[i] = p.getMatricula() + " - " + p.getNome();
        }
        return keys;
    }

    private String[] alunoKeys() {
        List<Aluno> alunos = alunoController.listarAlunos();
        String[] keys = new String[alunos.size()];
        for (int i = 0; i < alunos.size(); i++) {
            Aluno a = alunos.get(i);
            keys[i] = a.getMatricula() + " - " + a.getNome();
        }
        return keys;
    }

    private String[] disciplinaNames() {
        List<Disciplina> disciplinas = disciplinaController.listarDisciplinas();
        String[] names = new String[disciplinas.size()];
        for (int i = 0; i < disciplinas.size(); i++) {
            names[i] = disciplinas.get(i).getNome();
        }
        return names;
    }

    private void replaceItems(JComboBox<String> combo, String[] items) {
        Object selected = combo.getSelectedItem();
        combo.removeAllItems();
        for (String item : items) {
            combo.addItem(item);
        }
        if (selected != null) {
            combo.setSelectedItem(selected);
        }
    }

    private int selectedRow(JTable table) {
        int row = table.getSelectedRow();
        if (row < 0) {
            error("Selecione uma linha na tabela.");
            return -1;
        }
        return row;
    }

    private String value(JTable table, int row, int column) {
        return String.valueOf(table.getValueAt(row, column));
    }

    private String selectedCombo(JComboBox<String> combo, String message) {
        Object selected = combo.getSelectedItem();
        if (selected == null) {
            throw new IllegalArgumentException(message);
        }
        return selected.toString();
    }

    private String key(String comboValue) {
        int separator = comboValue.indexOf(" - ");
        return separator >= 0 ? comboValue.substring(0, separator) : comboValue;
    }

    private void require(JTextField field, String message) {
        if (field.getText().trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    private int parseInt(JTextField field, String message) {
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(message);
        }
    }

    private double parseDouble(JTextField field, String message) {
        try {
            return Double.parseDouble(field.getText().trim().replace(",", "."));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(message);
        }
    }

    private String prompt(String title, String initialValue) {
        return JOptionPane.showInputDialog(this, title, initialValue);
    }

    private boolean confirm(String message) {
        return JOptionPane.showConfirmDialog(this, message, "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void info(String message) {
        JOptionPane.showMessageDialog(this, message, "Sistema Escolar", JOptionPane.INFORMATION_MESSAGE);
    }

    private void error(String message) {
        JOptionPane.showMessageDialog(this, message, "Atencao", JOptionPane.WARNING_MESSAGE);
    }

    private void clearProfessorForm() {
        professorNome.setText("");
        professorIdade.setText("");
        professorCpf.setText("");
        professorMatricula.setText("");
        professorSalario.setText("");
        professorCargo.setSelectedIndex(0);
    }

    private void clearAlunoForm() {
        alunoNome.setText("");
        alunoIdade.setText("");
        alunoCpf.setText("");
        alunoMatricula.setText("");
    }

    private void clearDisciplinaForm() {
        disciplinaNome.setText("");
        disciplinaCarga.setText("");
    }

    private void seedExampleData() {
        if (!professorController.listarProfessores().isEmpty()) {
            return;
        }

        Professor professor = new Professor("Maria Souza", 36, "111.222.333-44", "P001", 4200.0, Cargo.PROFESSOR);
        Turma turma = new Turma();
        turma.setNome("1A");
        Aluno aluno = new Aluno("Lucas Lima", 16, "999.888.777-66", turma, "A001");
        Disciplina disciplina = new Disciplina("Matematica", 80, professor);

        professorController.cadastrarProfessor(professor);
        turmaController.cadastrarTurma(turma);
        alunoController.cadastrarAluno(aluno);
        turma.adicionarAluno(aluno);
        disciplinaController.cadastrarDisciplina(disciplina);
        notaController.cadastrarNota(new Nota(aluno, disciplina, 8.5));
        frequenciaController.registrarFrequencia(new Frequencia(aluno, disciplina, 2));
    }
}
