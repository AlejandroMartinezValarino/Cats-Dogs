Feature: Visualización de detalles de raza
  Como usuario
  Quiero ver información detallada sobre una raza específica
  Para conocer más sobre sus características y propiedades

  Scenario: Ver detalles de raza de perro
    Given que el usuario está viendo imágenes de perros
    And el usuario ha seleccionado una raza específica de perro
    When el usuario solicita ver los detalles de la raza
    Then se muestran las características de la raza incluyendo:
      | Campo              | Descripción                    |
      | Nombre             | Nombre de la raza              |
      | Peso               | Rango de peso (imperial/metric)|
      | Altura             | Rango de altura (imperial/metric)|
      | Esperanza de vida  | Rango de años de vida          |
      | Temperamento       | Características de temperamento|
      | Grupo de raza      | Categoría de la raza           |
      | Propósito          | Para qué fue criada            |

  Scenario: Ver detalles de raza de gato
    Given que el usuario está viendo imágenes de gatos
    And el usuario ha seleccionado una raza específica de gato
    When el usuario solicita ver los detalles de la raza
    Then se muestran las características de la raza incluyendo:
      | Campo              | Descripción                    |
      | Nombre             | Nombre de la raza              |
      | Origen             | País o región de origen        |
      | Esperanza de vida  | Rango de años de vida          |
      | Temperamento       | Características de temperamento|
      | Descripción        | Información general            |

  Scenario: Cerrar panel de detalles de raza
    Given que el usuario está viendo los detalles de una raza
    When el usuario cierra el panel de detalles
    Then el panel de detalles desaparece
    And la galería de imágenes sigue visible

  Scenario: Detalles de raza no disponibles
    Given que el usuario ha seleccionado una raza
    When la información detallada de la raza no está disponible en la API
    Then se muestra un mensaje indicando que los detalles no están disponibles
    And el usuario puede continuar viendo las imágenes de la raza

