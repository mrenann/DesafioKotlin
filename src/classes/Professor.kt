package classes

open class Professor(nome: String, sobrenome: String, val codigoProfessor: Int, var tempoDeCasa: Int = 0) : Pessoa(nome, sobrenome) {

    override fun toString(): String {
        return "\u001B[0mMeu Nome é $nome $sobrenome, meu código é $codigoProfessor"
    }

    override fun equals(other: Any?): Boolean {
        return (other as? Professor)?.let {
            it.codigoProfessor == codigoProfessor
        } ?: false
    }

    fun equalsCodigo(other: Any?): Boolean {
        return (other as? Int)?.let {
            it == codigoProfessor
        } ?: false
    }


}