Feature: Búsqueda de razas
  Como usuario
  Quiero poder buscar razas por nombre
  Para encontrar rápidamente la raza que me interesa

  Scenario: Buscar raza de perro por nombre
    Given que el usuario está viendo imágenes de perros
    And la lista de razas de perros está disponible
    When el usuario escribe "Golden" en el campo de búsqueda de razas
    Then se filtran las razas mostrando solo las que contienen "Golden" en el nombre
    And se muestra "Golden Retriever" en los resultados si existe

  Scenario: Buscar raza de gato por nombre
    Given que el usuario está viendo imágenes de gatos
    And la lista de razas de gatos está disponible
    When el usuario escribe "Persian" en el campo de búsqueda de razas
    Then se filtran las razas mostrando solo las que contienen "Persian" en el nombre
    And se muestra "Persian" en los resultados si existe

  Scenario: Búsqueda sin resultados
    Given que el usuario está viendo imágenes de perros
    And la lista de razas de perros está disponible
    When el usuario escribe "RazaInexistente" en el campo de búsqueda
    Then se muestra un mensaje indicando que no se encontraron razas
    And la lista de razas queda vacía

  Scenario: Limpiar búsqueda de razas
    Given que el usuario ha realizado una búsqueda de razas
    And hay resultados filtrados mostrándose
    When el usuario limpia el campo de búsqueda
    Then se muestran todas las razas disponibles nuevamente
    And la lista completa de razas se restaura

  Scenario: Búsqueda case-insensitive
    Given que el usuario está viendo imágenes de perros
    And la lista de razas de perros está disponible
    When el usuario escribe "golden" en minúsculas en el campo de búsqueda
    Then se encuentran las razas que contienen "Golden" independientemente de mayúsculas o minúsculas

