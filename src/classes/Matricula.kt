package classes

import java.time.LocalDateTime

class Matricula(var aluno: Aluno,var curso: Curso, var dataMatricula: LocalDateTime = LocalDateTime.now()){

    override fun toString(): String {
        return "\u001B[0m${aluno.nome} ${aluno.sobrenome}(${aluno.codigoAluno}) - ${curso.nomeCurso} - " +
                "Realizada no dia ${dataMatricula.dayOfMonth}/${dataMatricula.monthValue}/${dataMatricula.year}" +
                " às ${dataMatricula.hour}:${dataMatricula.minute}:${dataMatricula.second}"
    }

}