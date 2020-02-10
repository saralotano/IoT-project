#ifndef PROJECT_CONF_H_ 
#define PROJECT_CONF_H_

#undef NETSTACK_CONF_RDC
#define NETSTACK_CONF_RDC nullrdc_driver

#undef REST_MAX_CHUNK_SIZE // Set the max response payload before fragmentation 
#define REST_MAX_CHUNK_SIZE 64

#undef COAP_MAX_OPEN_TRANSACTIONS // Set the max number of concurrent transactions 
#define COAP_MAX_OPEN_TRANSACTIONS 2 //4

#undef NBR_TABLE_CONF_MAX_NEIGHBORS // Set the max number of entries in neighbors table 
#define NBR_TABLE_CONF_MAX_NEIGHBORS 10 //10

#undef UIP_CONF_MAX_ROUTES // Set the max number of routes handled by the node 
#define UIP_CONF_MAX_ROUTES 10

#undef UIP_CONF_BUFFER_SIZE // Set the amount of memory reserved to the uIP packet buffer 
#define UIP_CONF_BUFFER_SIZE 280 //280

// Trickle parameters
#undef RPL_CONF_DIO_REDUNDANCY 
#define RPL_CONF_DIO_REDUNDANCY 2

#undef RPL_CONF_DIO_INTERVAL_MIN
#define RPL_CONF_DIO_INTERVAL_MIN 3

#undef RPL_CONF_DIO_INTERVAL_DOUBLINGS 
#define RPL_CONF_DIO_INTERVAL_DOUBLINGS 4

#endif
