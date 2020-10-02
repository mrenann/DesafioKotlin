package classes

class ProfessorAdjunto(nome: String, sobrenome: String, codigoProfessor: Int, var qtdHorasMonitoria: Int) : Professor(nome, sobrenome, codigoProfessor) {

    override fun toString(): String {
        return "\u001B[0mAdjunto: ($codigoProfessor)$nome $sobrenome - $qtdHorasMonitoria Horas "
    }

    override fun equals(other: Any?): Boolean {
        return (other as? Professor)?.let {
            it.codigoProfessor == codigoProfessor
        } ?: false
    }

}