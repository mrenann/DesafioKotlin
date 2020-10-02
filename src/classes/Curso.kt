package classes

class Curso(val nomeCurso: String, val codigoCurso: Int, var capacidadeCurso: Int,
            var professorTitular: ProfessorTitular? = null, var professorAdjunto: ProfessorAdjunto? = null,
            var vagasDisponiveis: Int = capacidadeCurso) {

    var alunosMatriculados: MutableList<Aluno> = mutableListOf()

    fun getVagasOcupadas(): Int = alunosMatriculados.size

    fun getVagasDisponiveis() {
        vagasDisponiveis = capacidadeCurso - getVagasOcupadas()
    }

    fun adicionarAluno(aluno: Aluno):Boolean {
        if(vagasDisponiveis<=0){
            return false
        }
        alunosMatriculados.add(aluno)
        return true
    }

    fun excluirMatriculaAluno(aluno: Aluno?) {
        alunosMatriculados.remove(aluno)
        getVagasDisponiveis()

    }

    override fun equals(other: Any?): Boolean {
        return (other as? Curso)?.let {
            it.codigoCurso == codigoCurso
        } ?: false
    }

    fun equalsCodigo(other: Any?): Boolean {
        return (other as? Int)?.let {
            it == codigoCurso
        } ?: false
    }

    override fun toString(): String {
        return "\u001B[0m"+"CURSO ($codigoCurso)$nomeCurso - $capacidadeCurso VAGAS($vagasDisponiveis) [${professorTitular?.let { it } ?: "SEM PROFESSOR TITULAR"} | " +
                "${professorAdjunto?.let { it } ?: "SEM PROFESSOR ADJUNTO"}]"
    }
}