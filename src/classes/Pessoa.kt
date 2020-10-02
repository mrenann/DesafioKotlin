package classes

open class Pessoa(val nome: String, val sobrenome: String) {

    override fun toString(): String {
        return "\u001B[0mMeu Nome é $nome $sobrenome"
    }

}