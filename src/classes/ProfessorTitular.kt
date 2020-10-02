package classes

class ProfessorTitular(nome: String, sobrenome: String, codigoProfessor: Int, var especialidade: String) : Professor(nome, sobrenome, codigoProfessor) {

    override fun toString(): String {
        return "\u001B[0m   Titular: ($codigoProfessor)$nome $sobrenome - $especialidade"
    }

    override fun equals(other: Any?): Boolean {
        return (other as? Professor)?.let {
            it.codigoProfessor == codigoProfessor
        } ?: false
    }

}

