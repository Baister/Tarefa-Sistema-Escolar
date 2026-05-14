package model;

public class Turma {
    private int id;
    private int instituicaoId;
    private int professor_id;
    private String nome;
    private int anoLetivo;
    private String turno;
    private int vagas;

    // Não é necessário criar o "criado em porque o banco já construiu;


    public int getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(int professor_id) {
        this.professor_id = professor_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInstituicaoId() {
        return instituicaoId;
    }

    public void setInstituicaoId(int instituicaoId) {
        this.instituicaoId = instituicaoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public Turma(int id, int instituicaoId, int professor_id, String nome, int anoLetivo, String turno, int vagas) {
        this.id = id;
        this.instituicaoId = instituicaoId;
        this.professor_id = professor_id;
        this.nome = nome;
        this.anoLetivo = anoLetivo;
        this.turno = turno;
        this.vagas = vagas;
    }



    @Override
    public String toString(){
        return String.format(
                "Turma: id=%d - nome=%s  - ano_letivo=%d - turno=%s - vagas=%d",
                id, nome, anoLetivo, turno, vagas
        );
    }
}
