Feature: Manejo de errores y estados de carga
  Como usuario
  Quiero recibir información clara sobre el estado de la aplicación y errores
  Para entender qué está sucediendo y poder tomar acciones correctivas

  Scenario: Error al cargar imágenes de perros
    Given que el usuario está en la página principal
    And el tipo de mascota seleccionado es "perros"
    When ocurre un error al obtener las imágenes de perros desde la API
    Then se muestra un mensaje de error claro e informativo
    And el mensaje indica que no se pudieron cargar las imágenes de perros
    And se ofrece la opción de reintentar la carga

  Scenario: Error al cargar imágenes de gatos
    Given que el usuario está en la página principal
    And el tipo de mascota seleccionado es "gatos"
    When ocurre un error al obtener las imágenes de gatos desde la API
    Then se muestra un mensaje de error claro e informativo
    And el mensaje indica que no se pudieron cargar las imágenes de gatos
    And se ofrece la opción de reintentar la carga

  Scenario: Error al obtener lista de razas
    Given que el usuario está en la página principal
    When ocurre un error al obtener la lista de razas desde la API
    Then se muestra un mensaje de error informativo
    And el usuario puede intentar cargar las razas nuevamente
    And la funcionalidad de filtrado queda deshabilitada hasta que se carguen las razas

  Scenario: Error de conexión a la API
    Given que el usuario está en la página principal
    When no hay conexión a internet o la API no está disponible
    Then se muestra un mensaje indicando el problema de conexión
    And se ofrece la opción de reintentar la conexión
    And se muestra un estado de error persistente hasta que se resuelva

  Scenario: Timeout al cargar imágenes
    Given que el usuario está en la página principal
    When la solicitud a la API excede el tiempo máximo de espera
    Then se muestra un mensaje indicando que la solicitud tardó demasiado
    And se ofrece la opción de reintentar la carga
    And el indicador de carga desaparece

  Scenario: Reintentar carga después de un error
    Given que ha ocurrido un error al cargar las imágenes
    And se está mostrando un mensaje de error
    When el usuario hace clic en el botón de reintentar
    Then se realiza un nuevo intento de carga
    And se muestra el indicador de carga
    And si la carga es exitosa, se muestran las imágenes y desaparece el error

