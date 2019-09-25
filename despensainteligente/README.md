# Descripción de proyecto
Este proyecto fue ideado con la finalidad de satisfacer una necesidad personal y cotidiana. Concretamente, ayudar a planificar y optimizar las compras regulares reduciendo el desecho de productos caducados. Se diseñó para ser usado por una persona que de forma individual gestiona su despensa en su casa y hace sus compras. Está basada en SQLite y la versión actual no permite gestión de usuarios/cuentas y uso compartido por parte de varios miembros del hogar (por ejemplo).
Las principales funcionalidades que soporta son:
  - Alta de producto nuevo en despensa, siendo la despensa una representación única del conjunto de neveras, cajones, armarios y otros donde se guardan los productos de uso frecuente. 
  - Gestión de la despensa, permitiendo consultas de productos ya dados de alta en la despensa y edición de los atributos de éstos, así como su eliminación.
  - Gestión de master de listas de compra, siendo posible el mantenimiento de múltiples listas de compra, que además pueden ser reaprovechadas. Por ejemplo, mantener una lista de compra de "los básicos" 
  - Edición del detalle de una lista de compra referiéndose sobretodo a la lista de productos que contiene especificando las cantidades a comprar y las fechas de caducidad. Esto no es limitante y permite en realidad gestionar cualquier producto (a modo de recordatorio) independientemente de si existe en la despensa.
  - Actualización automática de la despensa desde una lista de compra tras una compra real.
  
# Descripción objetvos
#### Objetivos alcanzados:
  - Se dispone de una primera versión operativa que cubre los requisitos básicos garantizando la consistencia de los datos
  - Se han creado con éxito las principales actividades y layouts que incluyen:
  -- adaptadores para las listas custom
  -- listeners para filtrado y actualización de datos en memoria
  -- listeners que permiten la persistencia de los cambios en SQLite
  -- implementación y reuso de una custom view con su funcionalidad encapsulada
  -- implemetación y reuso de un DatePicker en diferentes contextos
  - Se ha ido refactorizando el código a medida que avanzaba el proyecto
  - Definición e implemetación de interfaces relacionados con Servicios que permiten gestionar los datos maestros 

#### Objetivos no alcanzados:
  - Refactorizar el código para incluir menú de fácil acceso a las principales actividades desde la barra
  - Cambiar el color de la lista de compra seleccionada desde el maestro
  - Versión futura: soportar cuentas y multi-usuario que pueden gestionar una misma despensa y lista de compras compartidas
  

# Screenshots y activities y clases más represenrtativas
#### Alta de producto en despensa:
  - xxx
  - 
  [![Alta de Producto]](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligent/doc/altadeproducto.PNG)
