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
    - adaptadores para las listas custom
    - listeners para filtrado y actualización de datos en memoria
    - listeners que permiten la persistencia de los cambios en SQLite
    - implementación y reuso de una custom view con su funcionalidad encapsulada
    - implemetación y reuso de un DatePicker en diferentes contextos
    - Se ha ido refactorizando el código a medida que avanzaba el proyecto
    - Definición e implemetación de interfaces relacionados con Servicios que permiten gestionar los datos maestros

#### Objetivos no alcanzados:
  - Refactorizar el código para incluir menú de fácil acceso a las principales actividades desde la barra
  - Cambiar el color de la lista de compra seleccionada desde el maestro
  - Incluir más funcionalidades en el gestor de despensa (por ejemplo, filtro de productos con cantidad 0, productos caducados, ordenar productos por cantidad..)
  - Versión futura: soportar cuentas y multi-usuario que pueden gestionar una misma despensa y listas de compra compartidas
  
# Screenshots y activities y clases más representativas
#### Pantalla principal (MainActivity)
  - Dispone de una imagen de ambiente y 3 botones que dan acceso a 3 de las actividades principales:
    - Alta de producto en despensa
    - Gestión de despensa
    - Gestión de master de listas de compra
  - Layouts usados:
    - activity_main.xml

[![Pantalla Principal](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/pantallaprincipal.PNG)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/pantallaprincipal.PNG)

#### Alta de producto en despensa (FormularioProductoDespensaActivity)
  - Consiste en un formulario donde el usuario introduce:
    - Nombre de producto
    - Cantidad/unidades de producto (por defecto es 1). Puede cambiarse mediante la custom view MyValueSelector que permite incrementar y decrementar el número clickando en las flechas que lo rodean.
    - Fecha de caducidad del producto (por defecto es 31/12/9999). Puede cambiarse mediante un DatePicker que muestra el calendario al clickar sobre el campo
  - El nombre del producto es único en la despensa
  - Los datos persisten en la base de datos tras pulsar el boton Actualizar Producto
  - Esta actividad se usa para "llenar" la despensa desde un inicio imitando la actividad de realizar un inventario de los productos disponibles en el hogar
  - Layouts usados:
    - activity_formulario_producto_despensa.xml
    - my_value_selector.xml para la custom view incrustada en el layout principal

[![Alta de Producto](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/altadeproducto.PNG)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/altadeproducto.PNG)

#### Gestión de despensa (ExplorarDespensaActivity)
  - Principalmente usa un adaptador para listar todos los productos de la despensa.
  - Dispone de un filtro que permite buscar productos que contengan la cadena de caracteres introducida. El resultado se actualiza en el instante.
  - Para cada línea:
    - Hay un icono de aspa roja que permite eliminar el producto de la despensa
    - Se muestra el nombre del producto
    - Se muestra la cantidad/unidades de producto, siendo posible modificarla mediante la custom view MyValueSelector. Esta funcionalidad permite incrementar y decrementar el número clickando en las flechas que lo rodean. La cantidad mínima es 0 y se muestra en rojo
    - Se muestra la fecha de caducidad del producto en formato dd/MM/yyyy, siendo posible cambiarla mediante un DatePicker que muestra el calendario al clickar sobre el campo
  - Los cambios persisten en el sistema únicamente si se pulsa el boton Guardar.
  - En el modelo, la despensa emplea TreeMap, siendo la clave el nombre del producto para facilitar el orden alfabético.
  - Layouts usados:
    - activity_explorar_despensa.xml
    - row_model_producto_lista_compra.xml para el adaptador de tipo DetalleDespensaEditAdapter
    - my_value_selector.xml para la custom view incrustada en el layout del adaptador

[![Gestión de despensa](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/gestordespensa.PNG)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/gestordespensa.PNG)
[![Date Picker](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/datepicker.PNG)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/datepicker.PNG)

#### Gestión de master de listas de compra (GestionarListasCompraActivity)
  - Usa un adaptador muy básico para listar las listas de compra existentes mostrando:
    - Longitud de la lista de compra (número de artículos, no total de unidades)
    - Nombre de la lista de compra
  - Arriba de todo hay un campo de texto editable donde el usuario introduciría el nombre de una nueva lista de compra a crear si quisiera. Ésta se crearía y aparecería en la lista abajo tras pulsar el boton "+".
  - Cuando se clicka sobre una lista de compra, ésta queda seleccionada (aunque con la versión actual no quede resaltado), y se pueden hacer las siguientes acciones mediante los botones de abajo:
    - Eliminar: Se elimina la lista de compra de la base de datos así como todo su detalle de artículos
    - Editar: Lleva a otra actividad (EditarListaCompraMaestroActivity) que permite modificar el nombre de la lista y persistirlo en el sistema mediante el boton Guardar. Tras pulsar guardar, la aplicación retorna a la actividad de gestión de master de listas de compra con los cambios aplicados.
    - Productos: Lleva a otra de las actividades clave que permite Editar el detalle de la lista de compra (se detalla a continuación)
  - Layouts usados:
    - activity_gestionar_listas_compra.xml
    - row_model_lista_compra_master.xml para el adaptador de tipo ListasCompraMasterAdapter
    - activity_editar_lista_compra_maestro.xml para la actividad que permite modificar el nombre de la lista de compra

[![Gestión de master de listas de compra](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/masterdelistasdecompra.PNG)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/masterdelistasdecompra.PNG)

#### Edición del detalle de una lista de compra (EditarListaCompraActivity)
  - Arriba de todo se muestra el nombre de la lista de compra a editar
  - La actividad se carga listando en la zona azul los artículos existentes en la lista de compra. En esta zona se usa el mismo layout que en la gestión de la despensa, permitiendo eliminar productos con el aspa roja, modificar la cantidad con la custom view, y editar la fecha de caducidad con el DatePicker. Lo que cambia es el adaptador, que ahora contiene la lista de la compra.
  - Arriba de todo se dispone de un buscador. A medida que el usuario teclea una cadena de caracteres en el campo editable, justo a continuación (por debajo) se listan los productos existentes en la despensa que cumplen con el criterio de búsqueda. Se visualizan el nombre del producto, la cantidad existente en la despensa y la última fecha de caducidad informada en la despensa (no siendo editables). El usuario puede hacer click sobre un producto del resultado de la búsqueda y el texto editable del buscador queda automáticamente rellenado con el nombre exacto y completo de este producto. (La finalidad de esta funcionalidad es aprovechar los nombres de productos usados que son clave y garantizar la consistencia cuando se actualiza la despensa desde la lista de compra). A continuación el usuario pulsaría el boton "+" con la finalidad de añadir el producto a la lista de compra. Por defecto la cantidad en la lista de compra será 1 y la fecha de caducidad 31/12/9999. El usuario ajustaría la cantidad si se precisa.
  - También se puede añadir a la lista de compra un producto no existente en la despensa. Introduciría el nombre de producto deseado y clickaría en el botón "+" nuevamente. Este producto aparecerá en la lista de compra con cantidad 1 y fecha de caducidad 31/12/9999.
  - Para persistir los datos en la base de datos, es necesario pulsar el botón Guardar Lista.
  - Tras una compra real, el usuario ajustaría las cantidades reales que finalmente compró. Donde interesa (en general para frescos), también indicaría la fecha de caducidad. (La aplicación no soporte múltiple fecha de caducidad para un mismo producto ya que se busca una solución básica.)
  - Si se pulsa el botón Enviar a despensa:
    - Para los productos que ya existen en la despensa (el nombre coincide) se actualiza la cantidad sumando a la actual la que consta en la lista de compra, y se sobrescribe la fecha de caducidad con la infromada en la lista de compra. Esta casuística incluye los productos con cantidad 0 en despensa.
    - Para los productos nuevos que no existen en la despensa (el nombre no ha sido usado), se crea un registro nuevo con los datos procedentes de la lista de compra.
    - Tras actualizar la despensa, se anulan las cantidades de los productos en la lista de compra. Así se evita añadir las cantidades a la despensa por duplicado y el usuario puede reaprovechar el detalle de los artículos para su próxima compra ajustando las cantidades nuevamente.
    - Para un correcto uso, la despensa debe ser revisada y actualizada periódicamente ajustando las cantidades (puede ser tras gastar un producto o hacer limpieza dependiendo del tiempo disponible del usuario). En caso contrario, las cantidades disponibles irían creciendo.
  - Layouts usados:
    - activity_editar_lista_compra.xml
    - row_model_producto_despensa.xml para el adaptador de tipo DespensaListAdapter (para el resultado de búsqueda en despensa)
    - row_model_producto_lista_compra.xml para el adaptador de tipo DetalleListaCompraAdapter (para el detalle de artículos en la lista de compra)
    - my_value_selector.xml para la custom view incrustada en el layout del adaptador de tipo DetalleListaCompraAdapter

[![Editar detalle de lista de compra](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/detallelistadecompra.PNG)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/detallelistadecompra.PNG)

# UMLs
#### POJOs
 - Se han definido 3 clases POJO representando:
    - Producto
    - Lista de Compra
    - Despensa

  [![Editar detalle de lista de compra](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-POJOs.gif)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-POJOs.gif)

#### Adaptadores
  - Adaptadores para Despensa (lista no editable de productos, y lista editable de productos)

  [![Editar detalle de lista de compra](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-Adaptadores-Despensa.gif)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-Adaptadores-Despensa.gif)

  - Adaptadores para Lista de Compra (para maestro, y para editar el detalle de productos)

  [![Editar detalle de lista de compra](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-Adaptadores-ListaCompra.gif)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-Adaptadores-ListaCompra.gif)

#### Custom Views
  - MyValueSelector incrustado en los layouts que usan los adaptadores para editar el detalle de los productos en despensa y lista de compra.

  [![Editar detalle de lista de compra](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-CustomViews.gif)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-Adaptadores-CustomViews.gif)

#### Servicios y Actividades principales
  - Despensa (Interface + Implementación) usado en 3 actividades principales.

  [![Editar detalle de lista de compra](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-Services-Despensa.gif)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-Adaptadores-Services-Despensa.gif)

 - Lista Compra (Interface + Implementación) usado en 3 actividades principales.

  [![Editar detalle de lista de compra](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-Services-ListaCompra.gif)](https://github.com/plamena29/aprendiendoandroid/blob/master/despensainteligente/doc/UML-Adaptadores-Services-ListaCompra.gif)

# Otros
 - La applicación está basada en SQlite.
 - Se ha definido el DatabaseHelper para interactuar con la base de datos en el momento de crearla o hacer un upgrade, y cuando se hace uso de los servicios.


