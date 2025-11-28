Feature: Visualización inicial de imágenes
  Como usuario
  Quiero ver imágenes de perros y gatos al cargar la aplicación
  Para disfrutar de una galería de mascotas desde el inicio

  Scenario: Mostrar imágenes aleatorias de perros al cargar la aplicación
    Given que la aplicación está iniciada
    And el tipo de mascota seleccionado es "perros"
    When el usuario accede a la página principal
    Then se muestran imágenes aleatorias de perros en la galería
    And cada imagen muestra la URL de la imagen
    And cada imagen muestra información de la raza si está disponible

  Scenario: Mostrar imágenes aleatorias de gatos al cargar la aplicación
    Given que la aplicación está iniciada
    And el tipo de mascota seleccionado es "gatos"
    When el usuario accede a la página principal
    Then se muestran imágenes aleatorias de gatos en la galería
    And cada imagen muestra la URL de la imagen
    And cada imagen muestra información de la raza si está disponible

  Scenario: Mostrar estado de carga mientras se obtienen las imágenes
    Given que la aplicación está iniciada
    When el usuario accede a la página principal
    Then se muestra un indicador de carga
    And cuando las imágenes se cargan correctamente, el indicador desaparece
    And se muestran las imágenes en la galería

