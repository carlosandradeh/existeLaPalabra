
¿Existe la palabra en la que estoy pensando en el idioma Español?

Este pequeño programa hecho en java sirve para que el usuario sepa si una palabra existe en español.

Para esto usuamos nuestras propias estructuras de datos hechas en el curso de Estructura de Datos 
de la carrera de Ciencias de la computación; estas mismas fueron modeladas por los maestros:
Alejandro Hernández Mora, Luis Matínez Damaso y Pablo Camacho González. 

Para la solución de este problema utilizamos un Árbol AVL, lo cual nos permitirá bucar las palabras en complejidad
por definicón de Árbol AVL de O(log n). Usando esta estructura de datos, nos dará una mejor complejidad a comparación si 
usamos otras estructuras como lo sería una lista o un arreglo.

Para ejecutar el programa usar los comandos:

```
$ mvn compile
```

```
$ mvn install
...
$ java -jar target/existePalabra.jar
```

