# Agencies

Agencies es una API simple que permite a los consumidores ver datos relacionados con
las agencias de pago, incluidos el nombre, la descripción, la dirección y las coordenadas
de Geolocalización (latitud y longitud).
La característica «más interesante» de esta API es permitir que los consumidores
obtengan una lista de agencias ubicadas cerca de un punto geográfico determinado
(coordenadas de latitud y longitud).
https://agencies.docs.apiary.io/#introduction/get-nearest-agencies-by-geopoint
Crear una API que permita obtener un listado de agencias que, además de los
parámetros obligatorios y opcionales de Agencies API, acepte como parámetro un
criterio de orden. Los criterios de orden podrán ser:

Por address_line, agency_code y distance.

1. Se sugiere permitir el ingreso por teclado, usando la clase Scanner, de los
parámetros obligatorios y opcionales, además del criterio de orden.
2. Consumir la API de agencias para obtener el listado solicitado.
3. Crear las clases Agency y Address con los atributos de la estructura JSON dada
más arriba para obtener un arreglo de agencias a partir de la respuesta de la API de
agencias.
4. Usar una clase con un método genérico para ordenar el arreglo de agencias según el
criterio de orden seleccionado.
5. ¡PUNTOS EXTRA! Buen manejo de excepciones y escritura prolija de código. Se
sugiere crear una excepción personalizada que encapsule las excepciones que
pudiesen generarse, ofreciendo un mensaje en español y más amigable.
6. ¡PUNTOS EXTRA! Registrar en un archivo de Log local, la fecha, hora y URL completa
del request recibido por SU API.
7. ¡PUNTOS EXTRA! Consumir la API de Google de Geolocalización para obtener la
latitud y longitud a partir de una dirección postal.
El
