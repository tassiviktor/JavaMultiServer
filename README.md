# JavaMultiServer
Embeddable multithread multiprotocol server with http(s) handler

To generate key for SSL:

openssl req -x509 -nodes -days 3650 -newkey rsa -keyout cacert.key -out cacert.crt
openssl pkcs12 -export -in cacert.crt -inkey cacert.key -out cacert.p12
