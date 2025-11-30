#!/bin/bash
# Script de inicio para Railway
# Railway asigna automáticamente la variable PORT

# Buscar el JAR ejecutable generado por Spring Boot
JAR_FILE=$(find target -name "pet-gallery-backend-*.jar" ! -name "*-sources.jar" ! -name "*-original.jar" | head -1)

if [ -z "$JAR_FILE" ]; then
  echo "Error: No se encontró el JAR ejecutable en target/"
  echo "Archivos en target/:"
  ls -la target/ || true
  exit 1
fi

echo "Iniciando aplicación con JAR: $JAR_FILE"
echo "Puerto asignado por Railway: ${PORT:-8080}"

# Ejecutar el JAR
exec java -jar "$JAR_FILE"

