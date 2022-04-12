# IoT-project

The aim of this project is to periodically monitor the temperature provided by sensors located in a 6LoWPAN/RPL WSN and provide these data to a client outside the network that communicates only with a Proxy.

Requirements
- 6LoWPAN multi-hop network (3-4 hops, 20-30 sensor nodes)
- Trickle algorithm
- Server CoAP with observable resource on Contiki motes
- JSON for data encoding (SenML)
- Proxy (implemented in Californium) with the following tasks:
  - observing CoAP Server resources
  - handling a cache
  - handling GET requests from CoAP Client
