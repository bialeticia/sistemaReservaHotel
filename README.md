# Sistema de Reserva de Hotel

O Sistema de Reserva de Hotel é uma simulação em Java que utiliza threads para gerenciar a alocação de quartos, o check-in/check-out de hóspedes, e a limpeza de quartos em um ambiente de hotel.

## Funcionalidades

- **Gerenciamento de Quartos**: Alocação dinâmica de quartos para hóspedes, considerando a disponibilidade.
- **Simulação de Hóspedes**: Cada hóspede é representado por uma thread que interage com os quartos e a recepção.
- **Serviço de Limpeza**: Camareiras threads que limpam os quartos após o check-out dos hóspedes ou quando um quarto é necessário limpar.
- **Recepção**: Recepcionistas que gerenciam o check-in e o check-out dos hóspedes.

## Tecnologias Utilizadas

- Java 17
- Threads para simulação de concorrência

## Estrutura do Projeto

O projeto é dividido nas seguintes classes principais:

- `Hotel`: Centraliza as operações e estados do hotel, incluindo quartos e listas de hóspedes e funcionários.
- `Quarto`: Representa os quartos do hotel e gerencia seu estado (ocupado, limpo, sujo, disponível).
- `Hospede`: Thread que simula a vida de um hóspede no hotel.
- `Camareira`: Thread responsável pela limpeza dos quartos.
- `Recepcionista`: Thread que realiza o check-in e o check-out dos hóspedes.
- `SincronizarPrints.java`: Classe auxiliar para logar as operações de forma sincronizada.
- `SituacaoHospede.java`:  Define se o hospede está ou não no quarto.
- `EstadoQuarto.java`:  Define o estado de ocupação e limpeza do quarto.
