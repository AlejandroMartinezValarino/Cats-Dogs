Feature: Filtrado de imágenes por raza
  Como usuario
  Quiero filtrar las imágenes por una raza específica
  Para ver solo imágenes de la raza que me interesa

  Scenario: Filtrar imágenes de perros por raza específica
    Given que el usuario está viendo imágenes de perros
    And la lista de razas de perros está disponible
    When el usuario selecciona una raza específica de perro del filtro
    Then se muestran solo las imágenes de perros de la raza seleccionada
    And el filtro de raza queda activo y visible
    And se actualiza la galería con las nuevas imágenes filtradas

  Scenario: Filtrar imágenes de gatos por raza específica
    Given que el usuario está viendo imágenes de gatos
    And la lista de razas de gatos está disponible
    When el usuario selecciona una raza específica de gato del filtro
    Then se muestran solo las imágenes de gatos de la raza seleccionada
    And el filtro de raza queda activo y visible
    And se actualiza la galería con las nuevas imágenes filtradas

  Scenario: Remover filtro de raza
    Given que el usuario tiene activo un filtro de raza
    When el usuario deselecciona el filtro de raza
    Then se muestran imágenes aleatorias sin filtrar
    And el filtro de raza queda desactivado

  Scenario: Cambiar de raza mientras hay un filtro activo
    Given que el usuario tiene activo un filtro de raza "Golden Retriever"
    When el usuario selecciona una nueva raza "Labrador"
    Then se actualiza el filtro a la nueva raza seleccionada
    And se muestran solo las imágenes de la nueva raza

  Scenario: No hay imágenes disponibles para la raza seleccionada
    Given que el usuario está viendo imágenes de perros
    When el usuario selecciona una raza que no tiene imágenes disponibles
    Then se muestra un mensaje indicando que no hay imágenes para esa raza
    And el usuario puede seleccionar otra raza o remover el filtro

