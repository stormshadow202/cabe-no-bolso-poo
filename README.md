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
│   ├── Class Diagram0.asta            # Arquivo-fonte do Astah: diagrama de classes editável
│   ├── Diagrama de Classes.png        # Imagem do Diagrama de Classes UML
│   ├── Diagrama de Classes.puml       # Código-fonte (PlantUML) do diagrama
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


##  Decisões de Modelagem e Arquitetura

O projeto foi estruturado seguindo o padrão arquitetural **MVC (Model-View-Controller)**, dividido em pacotes com responsabilidades bem delimitadas e aplicando os principais pilares da Programação Orientada a Objetos (POO).

## Diagrama de classes UML

<img width="1285" height="1858" alt="Diagrama de Classes" src="https://github.com/user-attachments/assets/bd53b666-fd97-4409-858f-9a93c2115be3" />

<table>
  <thead>
    <tr>
      <th colspan="3" align="center">Legenda</th>
    </tr>
    <tr>
      <th align="center">Categoria</th>
      <th align="center">Notação</th>
      <th align="center">Significado</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Visibilidade</td>
      <td><code>+</code> / <code>-</code> / <code>#</code></td>
      <td><code>public</code> / <code>private</code> / <code>protected</code></td>
    </tr>
    <tr>
      <td>Estilos</td>
      <td><em>Itálico</em> / Sublinhado</td>
      <td>Abstrato (classe ou método) / <code>static</code></td>
    </tr>
    <tr>
      <td>Relações</td>
      <td><code>──▶</code> / <code>- -▶</code></td>
      <td>Associação / Dependência (<code>&lt;&lt;instantiate&gt;&gt;</code>)</td>
    </tr>
    <tr>
      <td>Hierarquia</td>
      <td><code>──▷</code> / <code>- -▷</code></td>
      <td>Herança / Implementação de interface</td>
    </tr>
    <tr>
      <td>Multiplicidade</td>
      <td><code>1</code> / <code>0..*</code> / <code>1..*</code></td>
      <td>Exato / Zero ou muitos / Um ou muitos</td>
    </tr>
  </tbody>
</table>


### 1. Arquitetura e Separação de Responsabilidades (MVC + Pacotes)


| Pacote | Responsabilidade (MVC) | Classes |
| :--- | :--- | :--- |
| `modelo` | Calcula e valida. Guarda participantes e despesas em coleções genéricas. | `Planejamento`, `Participante`, `Despesa`, `DespesaFixa`, `DespesaPorPessoa`, `Dinheiro`, `Validação` |
| `visão` | Recebe as entradas do usuário e mostra resultados e mensagens. | `Ui` |
| `controlador` | Encaminha as ações da tela ao modelo e devolve à visão o resultado ou a mensagem de erro. | `Controlador` |
| `persistencia` | Define o contrato `salvar` / `carregar` e o implementa com serialização em arquivo. | `Persistencia`, `PersistenciaArquivo` |
| `(pacote padrão)` | Cria o controlador, injeta a persistência e abre a interface. | `Main` |


### 2. Herança e Polimorfismo
* **Hierarquia de `Despesa`**: A classe abstrata `Despesa` define a estrutura base e o contrato abstrato `# ratear(): Map<Integer, Long>`. As subclasses **`DespesaFixa`** e **`DespesaPorPessoa`** herdam de `Despesa` e fornecem implementações específicas para o cálculo de divisão de custos.
* **Polimorfismo**: `Planejamento` mantém uma coleção genérica de `Despesa` (`ArrayList<Despesa>`), permitindo calcular totais e contribuições individuais de forma uniforme, delegando a regra de rateio para cada tipo concreto em tempo de execução.
* **Exceção de Domínio (`Validação`)**: Estende `java.lang.Exception` para representar e propagar erros de regra de negócio e validação de forma clara.

### 3. Encapsulamento e Integridade do Domínio
* **Proteção de Estado**: Os atributos das classes de modelo (`codigo`, `nome`, `orcamentoCentavos`, `descricao`, `valorCentavos`, `participantes`, etc.) são `private`, sendo acessados ou modificados apenas por meio de métodos controlados.
* **Validações Internas**: Métodos privados como `validarNome()`, `validarOrcamento()` (em `Participante`) e `validarParticipantes()` (em `Planejamento`) asseguram que o estado dos objetos permaneça sempre consistente.
* **Precisão Monetária**: Todos os valores financeiros são manipulados internamente em centavos utilizando o tipo `long`, evitando erros de arredondamento de ponto flutuante. A classe utilitária `Dinheiro` centraliza a conversão (`parse`) e a formatação (`formatar`).


### 4. Interface de Persistência e Serialização
* **Inversão de Dependência (`Persistencia`)**: O `Controlador` depende da interface `Persistencia` (`salvar` e `carregar`), e não de uma implementação concreta. Isso permite alterar o mecanismo de armazenamento (ex.: banco de dados ou JSON) sem impactar o controlador ou a interface.
* **Implementação em Arquivo (`PersistenciaArquivo`)**: Implementa o contrato `Persistencia` utilizando serialização nativa do Java em `File`.
* **Serialização (`Serializable`)**: As entidades do modelo (`Planejamento`, `Participante`, `Despesa`, `DespesaFixa` e `DespesaPorPessoa`) implementam `Serializable` (com `serialVersionUID = 1L`), habilitando a persistência direta do grafo de objetos.

### 5. Associações e Estruturas de Dados
* **Coleções Genéricas**:
  * `Planejamento` utiliza `TreeMap<Integer, Participante>` para armazenar participantes indexados pelo código, garantindo busca eficiente e ordenação automática pelas chaves.
  * `Planejamento` armazena as despesas em `ArrayList<Despesa>`, e cada `Despesa` mantém uma `List<Participante>` com os envolvidos no rateio.
* **Relacionamentos entre Camadas**: `Main` instancia `Ui` e `Controlador`; `Ui` mantém referência para `Controlador`; `Controlador` orquestra `Planejamento` e `Persistencia`; e `Dinheiro` atua como classe de apoio para formatação e leitura de valores monetários.


## Funcionalidades

- Cadastrar participantes com código, nome e orçamento; alterar nome e orçamento.
- Incluir, editar e excluir despesas, com participantes selecionados.
- Ratear despesas fixas em centavos; os centavos restantes são distribuídos pela ordem crescente dos códigos. Despesas “Por pessoa” cobram o valor unitário de cada participante selecionado.
- Exibir custo total, total individual e excedente do orçamento.
- Salvar e carregar o planejamento completo em arquivo local.




## Integrantes e contribuições

| Integrante | Contribuição principal |
|---|---|
| Anderson Bezerra de Menezes | Concepção, elaboração, formatação, revisão e validação do relatório em PDF; criação, configuração e formatação do repositório GitHub. |
| Lilian Maria Jacob de Oliveira | Organização dos materiais de entrega e conferência da documentação do projeto. |
| Maria da Graça Araújo Ferrer | Concepção, elaboração, formatação, revisão e validação do relatório em PDF. |
| Matheus Almeida de Holanda | Criação, concepção, edição e revisão do diagrama de classes UML. |
| Mateus Morais Tabosa Barroso | Concepção e desenvolvimento do código da aplicação. |
| Thiago Pessoa Oliveira da Silva | Execução de testes funcionais, conferência dos resultados e organização das evidências. |


