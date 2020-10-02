import adicionais.*
import classes.*
import java.lang.NumberFormatException

var digitalHouseManager: DigitalHouseManager? = null

fun main() {

    var chooseYourDestiny: Int?

    println("--------------\u001B[31mDIGITAL HOUSE\u001B[0m--------------\u001B[34m")
    println("${Greeting()},Bem-Vindo ao nosso Sistema")
    loop@ while (true) {
        showMenu()
        chooseYourDestiny = readLine()?.toIntOrNull()
        when (chooseYourDestiny) {
            1 -> {
                digitalHouseManager = createDigitalHouseManager(digitalHouseManager)
            }
            2 -> {
                digitalHouseManager?.let {
                    subMenuCursos()
                    chooseYourDestiny = readLine()?.toIntOrNull() ?: 10
                    chooseYourDestiny?.let{chooseYourDestiny->  chooseCursoOption(chooseYourDestiny) }
                } ?: println("\u001B[31mCrie Primeiro o DigitalHouseManager\u001B[0m")
            }
            3 -> {
                digitalHouseManager?.let {
                    subMenuProfessores()
                    chooseYourDestiny = readLine()?.toIntOrNull() ?: 10
                    chooseYourDestiny?.let { chooseYourDestiny ->
                        chooseProfessorOption(chooseYourDestiny)
                    }
                } ?: println("\u001B[31mCrie Primeiro o DigitalHouseManager\u001B[0m")
            }
            4 -> {
                digitalHouseManager?.let {
                    subMenuAlunos()
                    chooseYourDestiny = readLine()?.toIntOrNull() ?: 10
                    chooseYourDestiny?.let { chooseYourDestiny ->
                        chooseAlunoOption(chooseYourDestiny)
                    }
                } ?: println("\u001B[31mCrie Primeiro o DigitalHouseManager\u001B[0m")
            }
            5 -> {
                digitalHouseManager?.let {
                    subMenuMatriculas()
                    chooseYourDestiny = readLine()?.toIntOrNull() ?: 10
                    chooseYourDestiny?.let { chooseYourDestiny ->
                        chooseMatriculaOption(chooseYourDestiny)
                    }
                } ?: println("\u001B[31mCrie Primeiro o DigitalHouseManager\u001B[0m")
            }
            else -> {
                println("\u001B[0m\nObrigado por usar o nosso Sistema!\u001B[0m")
                break@loop
            }
        }
    }

}

fun chooseCursoOption(chooseYourDestiny: Int) {
    var curso: Curso
    var codigoCurso: Int = 0
    var nomeCurso: String = ""
    var qtdMaxAlunos: Int = 0
    when (chooseYourDestiny) {
        1 -> {
            try {
                print("Qual Vai ser o código do Curso ?  ")
               codigoCurso = readLine()?.toInt() ?: 0
                print("Qual Vai ser o nome do Curso ?  ")
                nomeCurso = readLine() ?: ""
                print("Qual Vai ser a capacidade Máxima do Curso ?  ")
                qtdMaxAlunos = readLine()?.toInt() ?: 0
                digitalHouseManager?.registrarCurso(nomeCurso, codigoCurso, qtdMaxAlunos)
            }catch (ex: NumberFormatException){
                println("\u001B[31mInsira valores válidos\u001B[0m")
            }
        }
        2 -> {
            try {
                print("Qual o código do Curso  ")
                var codigo: Int = readLine()?.toInt() ?: 0
                digitalHouseManager?.excluirCurso(codigo)
            }catch (ex: NumberFormatException){
                println("\u001B[31mInsira valores válidos\u001B[0m")
            }
        }
        3 -> {
            try{
            print("Qual o código do Curso que você quer alocar Professores?  ")
            var codigo: Int = readLine()?.toInt() ?: 0
            print("Qual o código do Professor Titular  ")
            var codigoTitular: Int = readLine()?.toInt() ?: 0
            print("Qual o código do Professor Adjunto  ")
            var codigoAdjunto: Int = readLine()?.toInt() ?: 0
            digitalHouseManager?.alocarProfessores(codigo, codigoTitular, codigoAdjunto)
        }catch (ex: NumberFormatException){
        println("\u001B[31mInsira valores válidos\u001B[0m")
    }
        }
        4 -> {
            try {
                print("Qual o código do Curso  ")
                var codigo: Int = readLine()?.toInt() ?: 0
                digitalHouseManager?.consultarCurso(codigo)
            }catch (ex: NumberFormatException){
                println("\u001B[31mInsira valores válidos\u001B[0m")
            }
        }
        5 -> {
            try{
            digitalHouseManager?.let {
                it.cursos.forEach {
                    println(it)
                }
            } ?: println("Crie Primeiro o DigitalHouseManager")
        }catch (ex: NumberFormatException){
        println("\u001B[31mInsira valores válidos\u001B[0m")
    }
        }
        else -> println("\u001B[0mVoltando para o Menu...\u001B[0m")
    }

}

fun chooseProfessorOption(chooseYourDestiny: Int) {
    var curso: Curso
    when (chooseYourDestiny) {
        1 -> {
            try{
            print("Qual o código do Professor?  ")
            var codigo: Int = readLine()?.toInt() ?: 0
            print("Qual o nome do Professor  ")
            var nome: String = readLine() ?: ""
            print("Qual o sobrenome do Professor  ")
            var sobrenome: String = readLine() ?: ""
            print("Qual a Especialidade do Professor ?  ")
            var especialidade: String = readLine() ?: ""

            digitalHouseManager?.registrarProfessorTitular(nome, sobrenome, codigo, especialidade)
        }catch (ex: NumberFormatException){
        println("\u001B[31mInsira valores válidos\u001B[0m")
    }
        }
        2 -> {
        try{
            print("Qual o código do Professor?  ")
            var codigo: Int = readLine()?.toInt() ?: 0
            print("Qual o nome do Professor  ")
            var nome: String = readLine() ?: ""
            print("Qual o sobrenome do Professor  ")
            var sobrenome: String = readLine() ?: ""
            print("Quantas horas de monitoria do Professor ?  ")
            var qtdHoras: Int = readLine()?.toInt() ?: 0

            digitalHouseManager?.registrarProfessorAdjunto(nome, sobrenome, codigo, qtdHoras)
    }catch (ex: NumberFormatException){
        println("\u001B[31mInsira valores válidos\u001B[0m")
    }
        }
        3 -> {
    try{
            print("Qual o código do Professor  ")
            var codigo: Int = readLine()?.toInt() ?: 0
            digitalHouseManager?.excluirRegistroProfessor(codigo)
}catch (ex: NumberFormatException){
    println("\u001B[31mInsira valores válidos\u001B[0m")
}
        }
        4 -> {
    try{
            print("Qual o código do Professor  ")
            var codigo: Int = readLine()?.toInt() ?: 0
            digitalHouseManager?.consultarProfessor(codigo)
}catch (ex: NumberFormatException){
    println("\u001B[31mInsira valores válidos\u001B[0m")
}
        }
        5 -> {
    try{
            digitalHouseManager?.let {
                it.professores.forEach {
                    println(it)
                }
            } ?: println("Crie Primeiro o DigitalHouseManager")
}catch (ex: NumberFormatException){
    println("\u001B[31mInsira valores válidos\u001B[0m")
}
        }
        else -> println("\u001B[0mVoltando para o Menu...\u001B[0m")

    }

}

fun chooseAlunoOption(chooseYourDestiny: Int) {
    var aluno: Aluno
    when (chooseYourDestiny) {
        1 -> {
            try{
            print("Qual é o código do Aluno ?  ")
            var codigo: Int = readLine()?.toInt() ?: 0
            print("Qual é o nome do Aluno ?  ")
            var nome: String = readLine() ?: ""
            print("Qual é o sobrenome do Aluno ?  ")
            var sobrenome: String = readLine() ?: ""

            digitalHouseManager?.registrarAluno(nome, sobrenome, codigo)
        }catch (ex: NumberFormatException){
        println("\u001B[31mInsira valores válidos\u001B[0m")
    }
        }
        2 -> {
        try{
            print("Qual o código do Aluno?  ")
            var codigoAluno: Int = readLine()?.toInt() ?: 0
            digitalHouseManager?.excluirRegistroAluno(codigoAluno)
    }catch (ex: NumberFormatException){
        println("\u001B[31mInsira valores válidos\u001B[0m")
    }
        }
        3 -> {
    try{
            print("Qual o código do Aluno  ")
            var codigo: Int = readLine()?.toInt() ?: 0
            digitalHouseManager?.consultarAluno(codigo)
}catch (ex: NumberFormatException){
    println("\u001B[31mInsira valores válidos\u001B[0m")
}
        }
        4 -> {
            try{
            digitalHouseManager?.let {
                it.alunos.forEach {
                    println(it)
                }
            } ?: println("Crie Primeiro o DigitalHouseManager")
            }catch (ex: NumberFormatException){
                println("\u001B[31mInsira valores válidos\u001B[0m")
            }
        }
        else -> println("\u001B[0mVoltando para o Menu...\u001B[0m")
    }

}

fun chooseMatriculaOption(chooseYourDestiny: Int) {
    var matricula: Matricula
    when (chooseYourDestiny) {
        1 -> {
            try{
            print("Qual o código do Aluno?  ")
            var codigoAluno: Int = readLine()?.toInt() ?: 0
            print("Qual o código do Curso?  ")
            var codigoCurso: Int = readLine()?.toInt() ?: 0

            digitalHouseManager?.matricularAluno(codigoAluno, codigoCurso)
        }catch (ex: NumberFormatException){
        println("\u001B[31mInsira valores válidos\u001B[0m")
    }
        }
        2 -> {
            try {
                digitalHouseManager?.let {
                    print("Qual o código do Aluno?  ")
                    var codigoAluno: Int = readLine()?.toInt() ?: 0
                    print("Qual o código do Curso?  ")
                    var codigoCurso: Int = readLine()?.toInt() ?: 0

                    it.excluirMatriculaAluno(codigoAluno, codigoCurso)
                } ?: println("\u001B[31mCrie Primeiro o DigitalHouseManager\u001B[0m")
            }catch (ex: NumberFormatException){
                println("\u001B[31mInsira valores válidos\u001B[0m")
            }
        }
        3 -> {
            try{
            digitalHouseManager?.let {
                it.matriculas.forEach {
                    println(it)
                }
            }
        }catch (ex: NumberFormatException){
        println("\u001B[31mInsira valores válidos\u001B[0m")
    }
        }
        else -> println("\u001B[0mVoltando para o Menu...\u001B[0m")
    }

}

