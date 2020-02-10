#include <stdio.h>
#include <stdlib.h>
#include "contiki.h"
#include "net/ip/uip.h"
#include "net/ipv6/uip-ds6.h"
#include "net/ip/uip-debug.h"
#include "lib/random.h"
// REST headers
#include "contiki-net.h"
#include "rest-engine.h"

// resource
char sensorID[32]; //char buffer reserved to sensor MAC address
static int value = 15; //starting value
//static unsigned short sensorID;

static void per_handler(void);
static void get_handler(void* request, void* response, uint8_t *buffer, uint16_t preferred_size, int32_t *offset);

PERIODIC_RESOURCE(temperature, "title=\"Resource\"; rt=\"temperature\"", get_handler, NULL, NULL,NULL, 30*CLOCK_SECOND, per_handler);

//Periodic callback function
void per_handler(){

  value += random_rand() % 3; // add at most 3 degrees to the actual temperature

  if(value > 40) 
    value = 15;
  
  REST.notify_subscribers(&temperature);
}


void get_handler(void* request, void* response, uint8_t *buffer, uint16_t preferred_size, int32_t *offset)
{

  unsigned int accept = -1;
  REST.get_header_accept(request, &accept);
  
  if(accept == -1 || accept == REST.type.APPLICATION_JSON){ 

      sprintf((char *) buffer, "{\"n\":\"sensor_%s\" ,\"v\":%d}",sensorID, value);
      REST.set_header_content_type(response, REST.type.APPLICATION_JSON);
      REST.set_response_payload(response, buffer, strlen((char *) buffer));

  }else{

      const char *msg = "Bad content type: Supporting only json";
      memcpy((char *) buffer, msg, strlen(msg)); 
      REST.set_response_status(response, REST.status.NOT_ACCEPTABLE);
      REST.set_response_payload(response, buffer, strlen((char *) buffer));
  
  }
}

PROCESS(coap_server_process, "CoAP Server");


AUTOSTART_PROCESSES(&coap_server_process);


PROCESS_THREAD(coap_server_process, ev, data){

  PROCESS_BEGIN();
  
  int i;
  for (i = 0; i < sizeof(uip_lladdr.addr); ++i) {
    sprintf(sensorID + i, "%02x", (unsigned char)uip_lladdr.addr[i]); //setto in esadecimale %02x l'indirizzo MAC del sensore
  }

  rest_init_engine();
  rest_activate_resource(&temperature, "temperature");
 
  while(1){ 
    PROCESS_WAIT_EVENT();
  }

  PROCESS_END();

}