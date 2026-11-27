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

## Estrutura do Projeto

```text
cabe-no-bolso-poo-grupo-6-main
├── .vscode/                           #configuração opcional do VS Code
├── docs/
│   ├── Diagrama de Classes.png        # Imagem do Diagrama de Classes UML
│   ├── Diagrama de Classes.puml       # Código-fonte (PlantUML) do diagrama     
│   ├── modelo-relatorio.md            # Modelo do relatório técnico
├── evidencias/                        # Capturas legíveis dos cenários executados
├── src/                               # Fontes Java em pacotes MVC
│   ├── controlador/                   # Encaminha as ações da tela ao modelo
│   │   ├── Controlador.java           # Converte os textos da tela, chama o modelo e a persistência
│   ├── modelo/                        # Guarda os dados, calcula e valida
│   │   ├── Despesa.java               # Classe abstrata: descrição, valor, participantes e ratear()
│   │   ├── DespesaFixa.java           # Divide o valor entre os selecionados (centavos pela ordem dos códigos)
│   │   ├── DespesaPorPessoa.java      # Dada selecionado paga o valor unitário
│   │   ├── Dinheiro.java              # Donverte texto em centavos e centavos em texto
│   │   ├── Participante.java          # Código, nome e orçamento, com validação
│   │   ├── Planejamento.java          # Guarda participantes e despesas; calcula totais e excedentes
│   │   ├── Validação.java             # Exceção lançada quando uma regra é violada
│   ├── persistencia/                  # Contrato e implementação de salvar/carregar
│   │   ├── Persistencia.java          # Interface com salvar e carregar
│   │   ├── PersistenciaArquivo.java   # Implementa a interface por serialização em arquivo
│   ├── visão/                         # Interface gráfica
│   │   ├── Ui.java                    # Janela Swing: recebe entradas, mostra resultados e mensagens
├── Main.java                          # Ponto de entrada: cria o controlador e a persistência e abre a janela

```

## 1. Arquitetura e Separação de Responsabilidades (MVC + Pacotes)

| Pacote | Responsabilidade (MVC) | Classes |
| :--- | :--- | :--- |
| `modelo` | Calcula e valida. Guarda participantes e despesas em coleções genéricas. | `Planejamento`, `Participante`, `Despesa`, `DespesaFixa`, `DespesaPorPessoa`, `Dinheiro`, `Validação` |
| `visão` | Recebe as entradas do usuário e mostra resultados e mensagens. | `Ui` |
| `controlador` | Encaminha as ações da tela ao modelo e devolve à visão o resultado ou a mensagem de erro. | `Controlador` |
| `persistencia` | Define o contrato `salvar` / `carregar` e o implementa com serialização em arquivo. | `Persistencia`, `PersistenciaArquivo` |
| `(pacote padrão)` | Cria o controlador, injeta a persistência e abre a interface. | `Main` |

## 2. Herança e Polimorfismo

Hierarquia de Despesa: Despesa atua como classe base (em itálico no diagrama, indicando abstração), com um método abstrato # ratear() : Map<Integer, Long> (também em itálico). As subclasses DespesaFixa e DespesaPorPessoa herdam de Despesa e implementam ratear() de formas distintas conforme a regra de divisão do custo.

Polimorfismo em tempo de execução: Planejamento armazena uma lista de Despesa (- despesas : ArrayList<Despesa>), permitindo tratar DespesaFixa e DespesaPorPessoa de maneira uniforme ao calcular custos e contribuições, delegando o cálculo específico ao ratear() concreto de cada instância.

Exceção de domínio (Validação): Estende java.lang.Exception, permitindo sinalizar falhas de regra de negócio/entrada de forma explícita e tipada.

## 3. Encapsulamento e Invariantes

Atributos privados (-) e métodos protegidos (#) / públicos (+): Os estados internos das entidades (como codigo, nome, orcamentoCentavos em Participante e descricao, valorCentavos, participantes em Despesa) são privados, expondo acesso controlado via getters e operações específicas.

Validação centralizada no modelo: Participante possui métodos internos (- validarNome(...), - validarOrcamento(...)) e Planejamento possui - validarParticipantes(Despesa), garantindo que objetos inválidos ou inconsistentes não entrem no domínio.

Representação monetária em centavos (long): Valores financeiros (orcamentoCentavos, valorCentavos, retornos de totais/rateios) usam long para evitar problemas de precisão de ponto flutuante, enquanto Dinheiro centraliza a conversão (parse(String)) e a formatação (formatar(long)).




## Funcionalidades

- Cadastrar participantes com código, nome e orçamento; alterar nome e orçamento.
- Incluir, editar e excluir despesas, com participantes selecionados.
- Ratear despesas fixas em centavos; os centavos restantes são distribuídos pela ordem crescente dos códigos. Despesas “Por pessoa” cobram o valor unitário de cada participante selecionado.
- Exibir custo total, total individual e excedente do orçamento.
- Salvar e carregar o planejamento completo em arquivo local.




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


