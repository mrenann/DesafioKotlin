package adicionais

import classes.DigitalHouseManager

fun showMenu() {
    println("\u001B[0m-----------------------------------------\u001B[34m")
    println("| 1 | Criar DigitalHouseManager\n" +
            "| 2 | Opções de Cursos\n" +
            "| 3 | Opções de Professores\n" +
            "| 4 | Opções de Alunos\n" +
            "| 5 | Opções de Matriculas\n" +
            "|NDA| Sair")
    println("\u001B[0m-----------------------------------------\u001B[34m")
    print("O que você deseja Fazer ?  ")

}

fun subMenuCursos() {
    println("\u001B[0m---------------\u001B[31mMENU CURSOS\u001B[0m---------------\u001B[34m")
    println("| 1 | Registrar Curso\n" +
            "| 2 | Excluir Curso\n" +
            "| 3 | Alocar Professores\n" +
            "| 4 | Consultar Curso\n" +
            "| 5 | Listar Cursos\n" +
            "|NDA| Nenhuma das Opções Anteriores")
    print("\nO que você deseja Fazer ?  ")
}

fun subMenuProfessores() {
    println("\u001B[0m------------\u001B[31mMENU PROFESSORES\u001B[0m-------------\u001B[34m")
    println("| 1 | Registrar Professor Titular\n" +
            "| 2 | Registrar Professor Adjunto\n" +
            "| 3 | Excluir Professor\n" +
            "| 4 | Consultar Professor\n" +
            "| 5 | Listar Professores\n" +
            "|NDA| Nenhuma das Opções Anteriores")
    print("\nO que você deseja Fazer ?  ")
}

fun subMenuAlunos() {
    println("\u001B[0m--------------\u001B[31mMENU ALUNOS\u001B[0m---------------\u001B[34m")
    println("| 1 | Registrar Aluno\n" +
            "| 2 | Excluir Aluno\n" +
            "| 3 | Consultar Aluno\n" +
            "| 4 | Listar Alunos\n" +
            "|NDA| Nenhuma das Opções Anteriores")
    print("\nO que você deseja Fazer ?  ")
}

fun subMenuMatriculas() {
    println("\u001B[0m-------------\u001B[31mMENU MATRICULAS\u001B[0m-------------\u001B[34m")
    println("| 1 | Matricular Aluno\n" +
            "| 2 | Excluir Matricula Aluno\n" +
            "| 3 | Listar Matriculas\n" +
            "|NDA| Nenhuma das Opções Anteriores")
    print("\nO que você deseja Fazer ?  ")
}

fun createDigitalHouseManager(digitalHouseManager: DigitalHouseManager?): DigitalHouseManager? {
    digitalHouseManager?.let {
        println("\u001B[31mDigitalHouseManager já Criado!\u001B[0m")
        return digitalHouseManager
    }
    println("\u001B[32mDigitalHouseManager Criado com Sucesso!\u001B[0m")
    return DigitalHouseManager()

}

