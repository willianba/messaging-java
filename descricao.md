# INTRODUÇÃO

## Apresentação
- Roggia
- Will

## Sistemas Distribuídos
- O que é?
- Pra que serve?
- Event Driven Architecture

## Serviços de mensageria
- O que são?
- Para que serve?

## Trazer nosso contexto durante a apresentação das coisas acima
- Três sitemas diferentes
- Migração de backing services
  - Rabbit para
    - AWS (AmazonMQ [ActiveMQ])
    - GCP (PubSub)
- Validar opções

# PROBLEMA

## Várias opções de serviços
  - RabbitMQ
  - ActiveMQ
  - Pub/Sub -> Talvez não dê
  - Kafka
  - AWS SQS -> Talvez não dê
  - AWS SNS -> Talvez não dê
  - ActiveMQ Artemis

## Vários protocolos
  - AMQP 0.9
  - AMQP 1.0
  - gRPC (HTTP 2.0)
  - Stomp
  - OpenWire
  - WebSockets

## Frameworks
  - Spring
  - Quarkus

## Bibliotecas (APIs)
  - Depende de cada serviço
    - Exemplos
      - JMS
      - Rhea
      - Rhea-promise
      - Stompit
      - gRPC

## Patterns Mensageria
  - Topic vs Queue
  - Competing consumer
  - Exchanges/Subscriptions

# CENÁRIOS
- Produtor / Consumidor - cenário mais simples (sobre o topic/queue acima)
  - P -> Q -> C
  - P -> T -> C
- Load balancing (sobre o competing consumer acima)
  - P -> Q -> C1 / C2
- Entra em exchanges
  - Exchanges e Routing Key
    - Rabbit e PubSub possuem esse comportamento por padrão
  - Protocolo AMQP 0.9 Rabbit
  - P -> Ex -> Q -> C
    - Muitas comunicações (n(n-1)/2)[mostrar exemplo serviços netflix dark] + coupling (se mexe na fila modifica todo mundo)
  - Da pra fugir pro AMQP 1.0 + ActiveMQ + Wildcards + Virtual Topic
