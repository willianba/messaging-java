# Speakers

**Renan Zenkner Roggia**: Software developer at SAP. Mainly focused on Java and Javascript programming languages.

Development methodologies, software development best practices and software testing are topics I like to discuss about.

**Willian Alves**:  Graduando de Sistemas de Informação na PUCRS e Desenvolvedor de software na SAP Labs Latin America ;) 

# Introdução

Serviços de mensageria é uma das maneiras possiveis usadas para integrar aplicações.

Um serviço de mensageria faz o meio de campo da comunicação entre aplicações. 

**Vantagens:**

* Decoupling - desacopla a velocidade de entrega e leitura das aplicações
* Resilience - remove a necessidade da aplicação que está enviando dados implementar logicas de retrial
* Resilience - define somente um ponto central de falha na comunicação
* Observability - possibilita event sourcing (???) << double check
* Scale - estrategias para scaling

Dado que esses são alguns dos requerimentos que são muito comuns em arquiteturas de microserviços. é bastente comum ver serviços de mensageria sendo aplicados em uma arquitetura de microservicos para a comunicações dos vários microservicos.

Dito isso vamos ver como funciona a taxonomia de um serviço de mensageria.

# Taxonomia de um serviço de mensageria

Na sua forma mais simples o serviço de mensageria é composto por

publisher: aplicação que publica/notifica os eventos.

canal: queue or topic

receiver: aplicação que le os eventos/notificações.

## Exemplo Queue

mostrar exemplo queue

## Exemplo Topico

mostrar exemplo topico



## Diferenças de protocolos, bibliotecas de implementação, serviços de mensageria

É importante salientar as 





