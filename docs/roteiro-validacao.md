# Validação manual e caso de referência

Este roteiro traduz os cenários exigidos no enunciado. Preencha o resultado obtido somente após executar cada cenário na versão final. Salve capturas legíveis em `evidencias/` e relacione cada arquivo ao cenário no relatório.

## Caso principal

| Código | Participante | Orçamento |
|---:|---|---:|
| 1 | Ana | R$ 70,00 |
| 2 | Bruno | R$ 60,00 |
| 3 | Carla | R$ 70,00 |

| Despesa | Tipo | Valor | Participantes |
|---|---|---:|---|
| Transporte | Fixa | R$ 90,00 | Ana, Bruno e Carla |
| Almoço | Por pessoa | R$ 20,00 | Ana, Bruno e Carla |
| Atividade recreativa | Fixa | R$ 30,00 | Ana e Carla |
| Sobremesa | Por pessoa | R$ 10,00 | Ana, Bruno e Carla |

**Esperado:** Ana paga R$ 75,00 (excede R$ 5,00); Bruno paga R$ 60,00 (no limite); Carla paga R$ 75,00 (excede R$ 5,00); custo total R$ 210,00.

## Cenários adicionais

| Cenário | Entrada/ação | Resultado esperado | Obtido/evidência |
|---|---|---|---|
| Centavos no rateio | Despesa fixa R$ 100,00 para códigos 1, 2 e 3 | Código 1 paga R$ 33,34; códigos 2 e 3 pagam R$ 33,33; soma R$ 100,00 | A preencher |
| Entrada inválida | Despesa sem participante, valor negativo/zero ou descrição vazia | Mensagem clara; dados anteriores permanecem | A preencher |
| Código repetido | Cadastrar código já existente | Recusar sem alterar o primeiro participante | A preencher |
| Alterar orçamento | No caso principal, mudar orçamento de Ana para R$ 80,00 | Total permanece R$ 75,00; agora dentro do limite | A preencher |
| Excluir despesa | Excluir “Atividade recreativa” do caso principal | Total R$ 180,00; cada pessoa paga R$ 60,00 | A preencher |
| Salvar e reiniciar | Salvar o caso em `.dat`, fechar, reabrir e carregar | Dados, vínculos e resultados recuperados sem duplicação | A preencher |
| Arquivo ausente/inválido | Abrir caminho inexistente ou arquivo inválido | Mensagem clara; planejamento em uso preservado | A preencher |
| Participação seletiva | Despesa para apenas alguns participantes | Não selecionados têm contribuição zero nessa despesa | A preencher |
| Limite exato | Total individual igual ao orçamento | Situação dentro do orçamento, sem excedente | A preencher |

## Evidências

- Use dados fictícios, sem informações pessoais.
- Nomeie capturas pelo cenário, por exemplo `01-caso-principal.png` e `02-rateio-centavos.png`.
- Para cada cenário, anote entrada, esperado, obtido e evidência no relatório.
- Registre divergências, causa, correção e repetição do cenário.
- Este roteiro não afirma que testes foram executados; preencham com os resultados observados.
