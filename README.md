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
src/                    fontes Java em pacotes MVC
  Main.java
  controlador/
  modelo/
  persistencia/
  visão/
docs/                   UML, roteiro de validação e modelo do relatório
evidencias/             capturas legíveis dos cenários executados
lib/                    dependências externas, se adicionadas
.vscode/                configuração opcional do VS Code
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

## Publicar pelo GitHub Desktop

1. Entre na conta da equipe no GitHub Desktop.
2. Selecione **File > Add local repository...** e escolha a pasta que contém este README.
3. A pasta já foi inicializada como repositório Git na branch `main`. Se o aplicativo não a reconhecer, confirme que escolheu a pasta que contém este README e a pasta oculta `.git`. Mantenha o `.gitignore` existente; não escolha outro. Não é necessária uma licença automática.
4. Em **Changes**, confira a lista: não inclua `bin/`, arquivos `.class`, dados reais ou arquivos pessoais. Faça um commit inicial, por exemplo `Organiza projeto e documenta atividade`.
5. Clique **Publish repository** e escolha um nome simples, como `cabe-no-bolso-poo`. A proposta exige que o avaliador consiga acessar: use público se permitido pela equipe/instituição; se for privado, convide o avaliador e confirme o acesso.
6. Copie a URL publicada para a primeira página do relatório. Após novas alterações, faça commit e **Push origin**.

Não reutilize um repositório de nome parecido encontrado na busca. Publique no repositório da equipe e confira proprietário, visibilidade e URL antes de divulgar o link.



