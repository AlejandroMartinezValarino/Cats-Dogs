Feature: Paginación de imágenes
  Como usuario
  Quiero poder navegar entre múltiples páginas de imágenes
  Para ver más imágenes de la galería

  Scenario: Navegar a la siguiente página de imágenes
    Given que el usuario está viendo la primera página de imágenes
    And hay más imágenes disponibles
    When el usuario hace clic en el botón "Siguiente"
    Then se carga y muestra la siguiente página de imágenes
    And el número de página se actualiza
    And el botón "Anterior" se habilita si estaba deshabilitado

  Scenario: Navegar a la página anterior de imágenes
    Given que el usuario está viendo una página que no es la primera
    When el usuario hace clic en el botón "Anterior"
    Then se carga y muestra la página anterior de imágenes
    And el número de página se actualiza
    And si se llega a la primera página, el botón "Anterior" se deshabilita

  Scenario: Ir a una página específica
    Given que el usuario está viendo imágenes paginadas
    And hay múltiples páginas disponibles
    When el usuario selecciona un número de página específico
    Then se carga y muestra esa página de imágenes
    And el número de página se actualiza al seleccionado

  Scenario: Deshabilitar botón siguiente en la última página
    Given que el usuario está viendo la última página de imágenes disponibles
    When el usuario intenta navegar a la siguiente página
    Then el botón "Siguiente" está deshabilitado
    And no se realiza ninguna acción

  Scenario: Mantener filtro de raza al cambiar de página
    Given que el usuario tiene activo un filtro de raza
    And está viendo la primera página de imágenes filtradas
    When el usuario navega a la siguiente página
    Then se mantiene el filtro de raza activo
    And se muestran imágenes de la misma raza en la nueva página

  Scenario: Mostrar información de paginación
    Given que el usuario está viendo imágenes paginadas
    Then se muestra información sobre:
      | Elemento              | Descripción                    |
      | Página actual         | Número de página actual        |
      | Total de páginas      | Número total de páginas        |
      | Total de imágenes     | Cantidad total de imágenes     |
      | Imágenes por página   | Cantidad de imágenes mostradas |

  Scenario: Cambiar tamaño de página
    Given que el usuario está viendo imágenes paginadas
    When el usuario cambia el número de imágenes por página
    Then se actualiza la paginación con el nuevo tamaño
    And se recarga la primera página con el nuevo tamaño
    And el total de páginas se recalcula

