Feature: Listado de razas disponibles
  Como usuario
  Quiero ver la lista de razas disponibles para el tipo de mascota seleccionado
  Para poder seleccionar una raza específica para filtrar

  Scenario: Obtener lista de razas de perros
    Given que el usuario está viendo imágenes de perros
    When el usuario solicita ver las razas disponibles
    Then se muestra una lista con todas las razas de perros
    And cada raza muestra su nombre
    And cada raza muestra su ID único

  Scenario: Obtener lista de razas de gatos
    Given que el usuario está viendo imágenes de gatos
    When el usuario solicita ver las razas disponibles
    Then se muestra una lista con todas las razas de gatos
    And cada raza muestra su nombre
    And cada raza muestra su ID único

  Scenario: Mostrar estado de carga al obtener razas
    Given que el usuario está en la página principal
    When el usuario solicita ver las razas disponibles
    Then se muestra un indicador de carga mientras se obtienen las razas
    And cuando las razas se cargan correctamente, el indicador desaparece
    And se muestra la lista de razas

  Scenario: Manejar error al obtener lista de razas
    Given que el usuario está en la página principal
    When ocurre un error al obtener la lista de razas
    Then se muestra un mensaje de error informativo
    And el usuario puede intentar cargar las razas nuevamente

