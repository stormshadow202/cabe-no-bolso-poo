# Cabe no bolso - Planejador de despesas compartilhadas

Aplicação desktop em Java Swing para cadastrar participantes, organizar despesas compartilhadas e conferir quanto cada pessoa deve pagar em relação ao próprio orçamento. Este repositório contém o código-fonte e materiais para reproduzir a execução e documentar a atividade final de Programação Orientada a Objetos.

## Requisitos

- JDK 8 ou superior (`java` e `javac` disponíveis no terminal).
- Não há dependências externas.

Confira a instalação com `java -version` e `javac -version`.

## Compilar e executar no Windows PowerShell

Abra o terminal nesta pasta (a que contém este README) e execute:

```powershell
$arquivos = Get-ChildItem -Path src -Filter *.java -Recurse | ForEach-Object FullName
New-Item -ItemType Directory -Force bin | Out-Null
javac -encoding UTF-8 -d bin $arquivos
java -cp bin Main
```

A aplicação abre uma janela Swing. No VS Code, instale também a extensão Extension Pack for Java para suporte ao projeto.

## Estrutura

```text
cabe-no-bolso-poo-grupo-6-main
├── .vscode/                           configuração opcional do VS Code
├── docs/
│    ├── Diagrama de Classes.png       Imagem do Diagrama de Classes UML
│    ├── Diagrama de Classes.puml      Codigo Fonte do Diagrama           
│    ├── modelo-relatorio.md 
├── evidencias/                       capturas legíveis dos cenários executados
├── src/                              fontes Java em pacotes MVC
│    ├── controlador/
│    ├── modelo/
│    ├── persistencia/
│    ├── visão/
├── Main.java

```

As classes compiladas ficam em `bin/` e não devem ser enviadas ao GitHub. A aplicação salva o planejamento em arquivos `.dat`; use somente dados fictícios e não publique dados pessoais.

## Funcionalidades

- Cadastrar participantes com código, nome e orçamento; alterar nome e orçamento.
- Incluir, editar e excluir despesas, com participantes selecionados.
- Ratear despesas fixas em centavos; os centavos restantes são distribuídos pela ordem crescente dos códigos. Despesas “Por pessoa” cobram o valor unitário de cada participante selecionado.
- Exibir custo total, total individual e excedente do orçamento.
- Salvar e carregar o planejamento completo em arquivo local.

O caso de referência e cenários adicionais estão em [docs/roteiro-validacao.md](docs/roteiro-validacao.md). O UML editável está em [docs/diagrama-classes.puml](docs/diagrama-classes.puml). Preencha os resultados observados e guarde capturas em `evidencias/` antes de entregar.

## Relatório obrigatório

O repositório não substitui o relatório. A proposta pede PDF técnico de até 10 páginas, em uma coluna e no padrão ABNT, com nomes completos e matrículas, objetivo, link do GitHub, modelagem, implementação, testes e resultados, avaliação, melhorias, referências e contribuição individual. Use [docs/modelo-relatorio.md](docs/modelo-relatorio.md) como roteiro. Cada integrante deve enviar o PDF no AVA.

## Integrantes e contribuições

| Integrante | Contribuição principal |
|---|---|
| Anderson Bezerra de Menezes | Concepção, elaboração, formatação, revisão e validação do relatório em PDF; criação, configuração e formatação do repositório GitHub. |
| Lilian Maria Jacob de Oliveira | Organização dos materiais de entrega e conferência da documentação do projeto. |
| Maria da Graça Araújo Ferrer | Concepção, elaboração, formatação, revisão e validação do relatório em PDF. |
| Matheus Almeida de Holanda | Criação, concepção, edição e revisão do diagrama de classes UML. |
| Mateus Morais Tabosa Barroso | Concepção e desenvolvimento do código da aplicação. |
| Thiago Pessoa Oliveira da Silva | Execução de testes funcionais, conferência dos resultados e organização das evidências. |


