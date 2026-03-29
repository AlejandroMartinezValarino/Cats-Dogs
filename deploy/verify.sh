#!/usr/bin/env bash
# Ejecutar en el VPS con el backend escuchando en 127.0.0.1:8082 (Docker o systemd).
set -euo pipefail
BASE="${1:-http://127.0.0.1:8082}"
echo "Comprobando API en ${BASE} ..."
curl -sfS "${BASE}/api/dogs/breeds" | head -c 400
echo
echo "OK: respuesta recibida del backend."
