package classes

class DigitalHouseManager {
    var alunos: MutableList<Aluno> = mutableListOf()
    var professores: MutableList<Professor> = mutableListOf()
    var cursos: MutableList<Curso> = mutableListOf()
    var matriculas: MutableList<Matricula> = mutableListOf()

    fun registrarCurso(nome: String, codigoCurso: Int, qtdMaxAlunos: Int) {
        val curso = Curso(nome, codigoCurso, qtdMaxAlunos)
        when {
            cursos.contains(curso) -> println("\u001B[31mJá Tem um Curso com esse Código\u001B[0m")
            else -> {
                println("\u001B[32mO Curso foi Registrado!\u001B[0m")
                cursos.add(curso)
            }
        }

    }

    fun cursosTemAlunos(curso: Curso? = null): Boolean {
        curso?.let {
            return it.alunosMatriculados.isNotEmpty()
        } ?: return false
    }

    fun professorAlocado(professor: Professor? = null): Boolean {
        professor.let { prof ->
            cursos.forEach {
                if (it.professorAdjunto?.equals(prof) ?: false || it.professorTitular?.equals(prof) ?: false) {
                    return true
                }
            }
            return false
        }
    }

    fun alunoMatriculado(aluno: Aluno? = null): Boolean {
        var hasMatricula = false
        var qtdMatriculas = 0
        matriculas.forEach {
            if (it.aluno.equals(aluno)) {
                hasMatricula = true
                qtdMatriculas++
            }
        }
        return hasMatricula
    }

    fun returnMatriculaAluno(aluno: Aluno? = null): Matricula? {
        matriculas.forEach {
            if (it.aluno.equals(aluno)) {
                return it
            }
        }
        return null
    }

    fun excluirRegistroProfessor(codigoProfessor: Int) {
        var hasProfessor = false
        var professorEncontrado: Professor? = null

        professores.forEach {
            (it as? Professor)?.let { professor ->
                if (professor.equalsCodigo(codigoProfessor)) {
                    hasProfessor = true
                    professorEncontrado = professor
                }
            }
        }

        when (hasProfessor) {
            true -> {
                if (professorAlocado(professorEncontrado)) {
                    println("\u001B[31mProfessor está alocado em algum curso\u001B[0m")
                    println("| 1 | Apagar |NDA| Cancelar")
                    print("O que você deseja Fazer ?  ")
                    val delete: Int = readLine()?.toInt() ?: "0".toInt()
                    if (delete == 1) {
                        cursos.forEach {
                            it.professorTitular?.let { titular ->
                                if (titular.equals(professorEncontrado)) it.professorTitular = null
                            }
                            it.professorAdjunto?.let { adjunto ->
                                if (adjunto.equals(professorEncontrado)) it.professorAdjunto = null
                            }
                        }
                        professores.remove(professorEncontrado)
                        println("\u001B[32mProfessor removido com Sucesso!\u001B[0m")
                    }
                } else {
                    professores.remove(professorEncontrado)
                    println("\u001B[32mProfessor removido com Sucesso!\u001B[0m")
                }
            }
            false -> {
                println("\u001B[31mProfessor Não encontrado!\u001B[0m")
            }
        }
    }

    fun excluirRegistroAluno(codigoAluno: Int) {
        var hasAluno = false
        var alunoEncontrado: Aluno? = null

        alunos.forEach {
            (it as? Aluno)?.let { aluno ->
                if (aluno.equalsCodigo(codigoAluno)) {
                    hasAluno = true
                    alunoEncontrado = aluno
                }
            }
        }

        when (hasAluno) {
            true -> {
                if (alunoMatriculado(alunoEncontrado)) {
                    println("\u001B[31mAluno está matriculado em algum curso\u001B[0m")
                    println("| 1 | Apagar |NDA| Cancelar")
                    print("O que você deseja Fazer ?  ")
                    val delete: Int = readLine()?.toInt() ?: "0".toInt()
                    if (delete == 1) {
                        matriculas.reversed().forEach {
                            if (it.aluno.equals(alunoEncontrado)) {
                                println("${it.aluno.codigoAluno} - ${it.curso.codigoCurso}")
                                excluirMatriculaAluno(it.aluno.codigoAluno, it.curso.codigoCurso)
                            }
                        }
                        alunos.remove(alunoEncontrado)
                        println("\u001B[32mAluno removido com Sucesso!\u001B[0m")
                    }
                } else {
                    alunos.remove(alunoEncontrado)
                    println("\u001B[32mAluno removido com Sucesso!\u001B[0m")
                }
            }
            false -> {
                println("\u001B[31mAluno Não encontrado!\u001B[0m")
            }
        }

    }

    fun excluirCurso(codigoCurso: Int) {
        var hasCurso = false
        var cursoEncontrado: Curso? = null

        cursos.forEach {
            (it as? Curso)?.let { curso ->
                if (curso.equalsCodigo(codigoCurso)) {
                    hasCurso = true
                    cursoEncontrado = it
                }
            }
        }

        when (hasCurso) {
            true -> {
                if (cursosTemAlunos(cursoEncontrado)) {
                    println("\u001B[31mCurso possui Alunos\u001B[0m")
                    println("| 1 | Apagar |NDA| Cancelar")
                    print("O que você deseja Fazer ?  ")
                    val delete: Int = readLine()?.toInt() ?: "0".toInt()
                    if (delete == 1) {
                        cursoEncontrado?.alunosMatriculados?.clear()
                        matriculas.reversed().forEach {
                            if (it.curso.equals(cursoEncontrado)) {
                                matriculas.remove(it)
                            }
                        }
                        cursos.remove(cursoEncontrado)
                        println("\u001B[32mCurso Apagado com Sucesso!\u001B[0m")
                    }
                } else {
                    cursos.remove(cursoEncontrado)
                    println("\u001B[32mCurso Apagado com Sucesso!\u001B[0m")
                }

            }
            false -> {
                println("\u001B[31mCurso Não encontrado!\u001B[0m")
            }
        }
    }

    fun registrarProfessorAdjunto(nome: String, sobrenome: String, codigoProfessor: Int, qtdHoras: Int) {
        val professor = ProfessorAdjunto(nome, sobrenome, codigoProfessor, qtdHoras)
        when {
            professores.contains(professor) -> println("\u001B[31mJá Tem um Professor com esse Código\u001B[0m")
            else -> {
                println("\u001B[32mProfessor Registrado com Sucesso!\u001B[0m")
                professores.add(professor)
            }
        }
    }

    fun registrarProfessorTitular(nome: String, sobrenome: String, codigoProfessor: Int, especialidade: String) {
        val professor = ProfessorTitular(nome, sobrenome, codigoProfessor, especialidade)
        when {
            professores.contains(professor) -> println("\u001B[31mJá Tem um Professor com esse Código\u001B[0m")
            else -> {
                println("\u001B[32mProfessor Registrado com Sucesso!\u001B[0m")
                professores.add(professor)
            }
        }
    }

    fun consultarAluno(codigo: Int) {
        var hasAluno = false
        var aluno: Aluno? = null
        alunos.forEach {
            if (it.equalsCodigo(codigo)) {
                aluno = it
                hasAluno = true
            }
        }
        when {
            !hasAluno -> println("\u001B[31mAluno Não encontrado!\u001B[0m")
            else -> println(aluno)
        }
    }

    fun consultarCurso(codigoCurso: Int) {
        var hasCurso = false
        var curso: Curso? = null
        cursos.forEach {
            if (it.equalsCodigo(codigoCurso)) {
                curso = it
                hasCurso = true
            }
        }
        when {
            !hasCurso -> println("\u001B[31mCurso Não encontrado!\u001B[0m")
            else -> {
                println(curso)
                println("\u001B[0m-------------------------------")
                curso?.let {
                    if (it.alunosMatriculados.size > 0) {
                        it.alunosMatriculados.forEach { aluno ->
                            println(aluno)
                        }
                    } else {
                        println("Não há Alunos na Turma")
                    }
                }
            }
        }
    }

    fun consultarProfessor(codigoProfessor: Int) {
        var hasProfessor = false
        var professor: Professor? = null
        professores.forEach {
            if (it.equalsCodigo(codigoProfessor)) {
                professor = it
                hasProfessor = true
            }
        }
        when {
            !hasProfessor -> println("\u001B[31mProfessor Não encontrado!\u001B[0m")
            else -> println(professor)
        }
    }

    fun alocarProfessores(codigoCurso: Int, codigoProfessorTitular: Int, codigoProfessorAdjunto: Int) {

        var hasAdjunto = false
        var hasTitular = false
        var hasCurso = false

        var adjunto: ProfessorAdjunto? = null
        var titular: ProfessorTitular? = null
        var curso: Curso? = null

        professores.forEach {
            (it as? ProfessorAdjunto)?.let { ad ->
                if (ad.equalsCodigo(codigoProfessorAdjunto)) {
                    hasAdjunto = true
                    adjunto = ad
                }
            }

            (it as? ProfessorTitular)?.let { ti ->
                if (ti.equalsCodigo(codigoProfessorTitular)) {
                    hasTitular = true
                    titular = ti
                }
            }

        }

        cursos.forEach {
            (it as? Curso)?.let { c ->
                if (c.equalsCodigo(codigoCurso)) {
                    hasCurso = true
                    curso = c
                }
            }
        }
        when {
            !hasTitular -> println("\u001B[31mProfessor Titular Não encontrado!\u001B[0m")
        }
        when {
            !hasAdjunto -> println("\u001B[31mProfessor Adjunto Não encontrado!\u001B[0m")
        }
        when {
            !hasCurso -> println("\u001B[31mCurso Não encontrado!\u001B[0m")
        }

        if (hasCurso && hasAdjunto && hasTitular) {
            curso?.let {
                it.professorAdjunto = adjunto
                it.professorTitular = titular
                println("\u001B[32mProfessores Alocados com Sucesso!\u001B[0m")
            }
        }

    }

    fun matricularAluno(codigoAluno: Int, codigoCurso: Int) {
        var hasCurso = false
        var hasAluno = false
        var alunoMatriculado = false

        var curso: Curso? = null
        var aluno: Aluno? = null

        alunos.forEach {
            (it as? Aluno)?.let { itc ->
                if (itc.equalsCodigo(codigoAluno)) {
                    hasAluno = true
                    aluno = itc
                }
            }
        }

        cursos.forEach {
            (it as? Curso)?.let { itc ->
                if (itc.equalsCodigo(codigoCurso)) {
                    hasCurso = true
                    curso = itc
                }
            }
        }

        curso?.let {
            it.alunosMatriculados.forEach { a ->
                if (a.equalsCodigo(codigoAluno)) {
                    alunoMatriculado = true
                }
            }
        }
        when {
            !hasAluno -> println("\u001B[31mAluno não Encontrado!\u001B[0m")
            !hasCurso -> println("\u001B[31mCurso não Encontrado!\u001B[0m")
            alunoMatriculado -> println("\u001B[31mAluno já Matriculado no Curso!\u001B[0m")
            hasAluno && hasCurso && !alunoMatriculado -> {
                curso?.let { c ->
                    aluno?.let {
                        if (c.adicionarAluno(it)) {
                            val matriculaAluno = Matricula(it, c)
                            matriculas.add(matriculaAluno)
                            // curso.alunosMatriculados.add((it))
                            c.getVagasDisponiveis()
                            println(matriculaAluno)

                            println("\u001B[32mAluno foi matriculado no Curso!\u001B[0m")
                        } else {
                            println("\u001B[31mNão há vagas Disponíveis no Curso!\u001B[0m")
                        }
                    }
                }
            }
        }
    }

    fun excluirMatriculaAluno(codigoAluno: Int, codigoCurso: Int) {

        var hasCurso = false
        var hasAluno = false
        var alunoMatriculado = false

        var curso: Curso? = null
        var aluno: Aluno? = null
        var matricula: Matricula? = null

        alunos.forEach {
            (it as? Aluno)?.let { itc ->
                if (itc.equalsCodigo(codigoAluno)) {
                    hasAluno = true
                    aluno = itc
                }
            }
        }

        cursos.forEach {
            (it as? Curso)?.let { itc ->
                if (itc.equalsCodigo(codigoCurso)) {
                    hasCurso = true
                    curso = itc
                }
            }
        }

        curso?.let {
            it.alunosMatriculados.forEach { a ->
                if (a.equalsCodigo(codigoAluno)) {
                    alunoMatriculado = true
                    aluno?.let { aluno ->
                        matricula = returnMatriculaAluno(aluno)
                    }
                }
            }
        }

        when {
            !hasAluno -> println("\u001B[31mAluno não Encontrado!\u001B[0m")
            !hasCurso -> println("\u001B[31mCurso não Encontrado!\u001B[0m")
            !alunoMatriculado -> println("\u001B[31mAluno não está Matriculado no Curso!\u001B[0m")
            hasAluno && hasCurso && alunoMatriculado -> aluno?.let {
                curso?.excluirMatriculaAluno(aluno)
                matriculas.remove(matricula)
            }
        }
    }

    fun registrarAluno(nome: String, sobrenome: String, codigoAluno: Int) {
        val aluno = Aluno(nome, sobrenome, codigoAluno)

        when {
            alunos.contains(aluno) -> println("\u001B[31mJá Tem Aluno com esse Código\u001B[0m")
            else -> {
                println("\u001B[32mAluno Registrado com Sucesso!\u001B[0m")
                alunos.add(aluno)
            }
        }

    }

}
