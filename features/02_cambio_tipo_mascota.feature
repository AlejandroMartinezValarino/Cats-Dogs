Feature: Cambio entre tipo de mascota
  Como usuario
  Quiero poder cambiar entre ver imágenes de perros y gatos
  Para explorar diferentes tipos de mascotas

  Scenario: Cambiar de perros a gatos
    Given que el usuario está viendo imágenes de perros
    When el usuario selecciona el tipo de mascota "gatos"
    Then se actualiza la galería para mostrar imágenes de gatos
    And se resetea el filtro de raza si estaba activo
    And se muestran nuevas imágenes aleatorias de gatos

  Scenario: Cambiar de gatos a perros
    Given que el usuario está viendo imágenes de gatos
    When el usuario selecciona el tipo de mascota "perros"
    Then se actualiza la galería para mostrar imágenes de perros
    And se resetea el filtro de raza si estaba activo
    And se muestran nuevas imágenes aleatorias de perros

  Scenario: Mantener la selección de tipo de mascota al recargar
    Given que el usuario ha seleccionado el tipo de mascota "gatos"
    When el usuario recarga la página
    Then la aplicación mantiene la selección de "gatos"
    And se muestran imágenes de gatos automáticamente

