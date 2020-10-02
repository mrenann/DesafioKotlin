package classes

class Aluno(nome: String, sobrenome: String, val codigoAluno: Int) : Pessoa(nome, sobrenome) {

    override fun equals(other: Any?): Boolean {
        return (other as? Aluno)?.let {
            it.codigoAluno == codigoAluno
        } ?: false
    }

    fun equalsCodigo(other: Any?): Boolean {
        return (other as? Int)?.let {
            it == codigoAluno
        } ?: false
    }

    override fun toString(): String {
        return "\u001B[0m($codigoAluno)$nome $sobrenome"
    }

}

