# SpringProjectDemo

# COMPRENDE Y ESTABLECE LAS REGLAS BÁSICAS SOBRE LOS DATOS DE TU NEGOCIO

## PRIMERA FASE DE DISEÑO: VALUE OBJECTS Y ENTIDADES

Identificar los objetos de nuestro negocio. Más adelante podemos decidir si seran value objects, entidades o dto's.

Primero analizar contexto y preparar modelos, comenzando por el diseño de value objects

En esta demo se diseñan diversos objetos genéricos reutilizables en múltiples contextos. Pueden tomarse como modelo para el concepto de value object algunas clases como LocalDate o LocalDateTime, que utilizamos habitualmente siguiendo un patron de inmutabilidad y valor sin identidad propia dentro del dominio: un color, una dimension, etc.

En aquellos donde no sean aplicables simplemente se eliminan, se crean nuevos  o bien se extiende si la funcionalidad aportada en insuficiente


### Ejemplos de value objects genéricos y específicos aplicables a diversos contextos (e incluso reutilizables para diversos projectos)

- Cadenas válidas (ChekedString) 
    - Valida tamaños mínimos y máximos

- Telefonos
    - Nacionales (cadenas validadas por regex)
    - Internacionales (con patron y prefijo específico) 

- Email 
    - Validando formato y uso (cadenas válidas con un patron validado por expreg. La entidad  que incluya este objecto se responsabilizara de validar si es autentico y con un simple booleano podría indicae si ha sido validado o no.

- Passwords
    - Fuerte (cadena válida con patron validado por expreg, contiene el password cifrado con BCrypt)
    - EXTRA: Ultra fuerte (* por definir y en estudio constante *)

- Money
    - Encapsula los conceptos de cantidad y moneda en un solo valor, aportando una seguridad extra en la precisión a la hora de recibir y transformar cantidades a valores decimales. Se aporta algun servicio y puerto (siguiendo patrones como el Ports&Adapters) de Hexagonal para las conversiones entre diferentes tipos de moneda. 

- Distancias (puede englobarse en uno o varios value objects específicos segun contexto real y específico). Para este ejemplo descartamos distancias con valor negativo, en otros podrian ser aceptadas.
    - Micro distancias (nanometros a milimetros)
    - Pequeñas distancias (mm a m)
    - Distancias de desplazamiento (metros a km)
    - Distancias Astronomicas (parsecs y sus multiplos)
    * NOTA: Este ejemplo contiene value objects de distancia duplicados meramente a nivel de exposición de su aplicación en diferentes contextos, de todos ellos solo perdudará el más adecuado al caso final

- Pesos (puede englobarse en uno o varios value objects específicos segun contexto real). Para este ejemplo se considera que los objetos a transportar no pueden tener como atributo un peso nulo o negativo. En otros contextos podría aceptarse como válido y el value object se adaptaria en consecuencia o bien se añadiria la precisión del peso a miligramos, o lo que nos marquen los requisitos específicos del negocio.
    - Pequeños pesos (de miligramos a gramos)
    - Pesos (de gramos a Kg)
    - Grandes pesos (de kg a toneladas)

- Dimensiones físicas
    - Al basarnos en value objects que validan su contenido y creación, se simplifica el control de aquellos objetos y entidades que lo usan y que pueden delegar en ellos algunos aspectos de la seguridad sobre su contenido, no tiene sentido validar si los objetos son nulos o con valores negativos (no se permite desde la propia construccion del value object)

- Direcciones físicas
    - Contienen datos sobre el pais, region, ciudad, calle, codigo postal

- Paqueteria
    - Contienen datos sobre el objeto físico a transportar asi como las direcciones físicas de recogida y envio e información importante para la gestión

-  Posicionamiento fisico
    - Coordenadas 2D/3D
    - Geolocalizacion terrestre (longitud/latitud)
    - Posicionamiento Astronómico básico
    - **AMPLIACION ESPECIFICA**: Posicionamiento orbital


### Ejemplo inicial de una entidad Package, que aun estando vinculada a otras entidades como Cliente o Trasnporte tiene identidad propia y es un objeto de negocio de nivel superior a un value object

- ShipmentUnit 
    - Incorpora alguno de los atributos, basados en value objects, característicos de un contenedor de productos a transportar. Cada negocio específico establece sus reglas, modicando su lista de atributos, o estableciendo sus valores máximos en medidas y peso. Por ejemplo, en otros contextos seria innegociable el añadir atributos como fecha de empaquetado y fecha de caducidad usando value objects LocalDateTime, o LocalDate