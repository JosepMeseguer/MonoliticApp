# Descripción

## Desarrollo paso a paso del projecto. 

Comenzamos con una base mínima, tomamos como ejemplo del desarrollo una empresa de transporte de paqueteria, pero aun así en el primer ejemplo con Value Object se han incluido un set de objetos que puede servir de base para otros contextos, y que pueden tener su recorrido en proyectos específicos.

Las funcionalidades básicas de gestión de recogida, transporte y entrega de paquetes de nuestros clientes serán las primeras a implementar. Pero el proyecto tiene un largo recorrido en cuanto añadamos capas como la de seguridad, servicios de auditoria, tracking de nuestros vehiculos en desplazamiento... 

La arquitectura basada en un Monolito permite comenzar un negocio pequeño con desarrolladores poco experimentados. Pero ofreciendo la funcionalidad deseada siempre que los requisitos operativos se mantengan bajo control. En un repositorio específico se podran ir comparando las versiones monolito vs monolito modular.

El primer paso es conocer el negocio y los datos precisos para gestionarlo. A partir de un buen diseño de los agrupamientos y validaciones precisas para los datos se determina:

+ Qué datos son simples invariantes que pueden ser implementados como value objects
+ Qué agrupaciones de datos pueden ser implementadas como entidades
+ Cuándo lo que tiene sentido lógico en el movimiento de la información es trabajar con DTO's

Como en todo contexto real se trabaja con grises, en ocasiones podremos aplicar herencia o composición para construir value objects más complejos, subentidades dependientes de otras, DTO's validados o pendientes de usar.

A partir del diseño anterior se organizan las capas y se define la arquitectura más idónea a las necesidades.

Adaptar esta base a un prouecto concreto, añadir entidades específicas, capas de servicio, persistencia, presentación, seguridad o auditoria son los siguientes pasos.
