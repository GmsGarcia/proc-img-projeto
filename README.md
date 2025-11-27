# Gestor Tarefas - Processamento de Imagem

Desenvolvido por: Guilherme Garcia [a22409129]

## Modelo UML
- [models.md](https://github.com/GmsGarcia/proc-img-projeto/blob/master/model/model.md)

# Tabela de resumo

| Tema                                         | Gestor de Tarefas                                                                                                                              |
|----------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| **Grupo**                                    | Guilherme Garcia - a22409129                                                                                                                   |
| **Funcionalidade baseada em LLM**            | Classificação automatica de prioridades<br/>Gerir as descrições das tarefas<br/>Sugestões de prazos                                            |
| **Prompts esperados para a func**            | *"Crie uma tarefa para entregar o relatório amanhã às 10h"<br/>"Quais tarefas devo priorizar hoje?"<br/>"Resuma as minhas tarefas pendentes.”* |
| **Classes esperadas e sua responsibilidade** | Ver tabela abaixo                                                                                                                              |

# Classes
| Classe             | Descriçao                                                                                                      |
|--------------------|----------------------------------------------------------------------------------------------------------------|
| **TaskManager**    | Classe principal do sistema; cria, remove, atualiza e lista tarefas e subtarefas                               |
| **StorageManager** | Responsável por guardar e carregar os dados                                                                    |
| **Task**           | Representa uma tarefa individual, contendo título, descrição, prioridade, prazo, status e possíveis subtarefas |
| **TaskItem**       | Subdivisão de uma tarefa; associada diretamente a uma Tarefa                                                   |
| **User**           | Representa o utilizador da aplicação; armazena histórico de tarefas                                            |
