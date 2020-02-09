CONTIKI_PROJECT = server
all: $(CONTIKI_PROJECT)

CFLAGS += -DUIP_CONF_IPV6=1 -DWITH_UIP=1 -DUIP_CONF_IPV6_RPL=1 -DRPL_CONF_STATS=1
-DUIP_CONF_TCP=0 -DPROJECT_CONF_H=\"project-conf.h\

#il flag -DUIP_CONF_TCP=0 serve a disattivare TCP in modo dal lavorare solamente con UDP

CONTIKI = ../../..

APPS += er-coap
APPS += rest-engine

WITH_UIP6=1

UIP_CONF_IPV6=1

UIP_CONF_IPV6_RPL=1

include $(CONTIKI)/Makefile.include
