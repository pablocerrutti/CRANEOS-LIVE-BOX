# Etapa 1 — Validación de hardware

No avanzar a una realización multicámara completa hasta comprobar esta etapa.

## Datos a identificar

- [ ] Variante exacta del Samsung Galaxy A16 5G y memoria RAM.
- [ ] Marca/modelo de la capturadora HDMI 1.
- [ ] Marca/modelo de la capturadora HDMI 2.
- [ ] Chipset/controlador UVC de cada capturadora, si está disponible.
- [ ] Modelo del hub USB y si admite alimentación externa.
- [ ] Fuente de alimentación y capacidad del hub.

## Secuencia de pruebas

1. Conectar el hub al Samsung mediante USB-C OTG.
2. Conectar una capturadora y verificar que Android la enumere.
3. Conectar una fuente HDMI conocida y comprobar imagen y audio.
4. Medir estabilidad durante al menos 15 minutos.
5. Repetir con la segunda capturadora por separado.
6. Conectar ambas a la vez y comprobar que Android enumere las dos.
7. Visualizar ambas entradas y observar cortes, calentamiento y consumo.
8. Repetir con alimentación externa del hub, si corresponde.

## Criterios de aprobación

- Ambas capturadoras son detectadas sin desconexiones.
- Se puede recibir video de ambas al mismo tiempo.
- La imagen no presenta congelamientos continuos.
- El teléfono no se reinicia ni muestra errores USB.
- La temperatura y estabilidad permiten una sesión de prueba razonable.

## Qué enviar para diagnosticar

Fotos de las etiquetas/modelos de las capturadoras y del hub, captura de la información del teléfono (modelo y RAM), y descripción de cualquier mensaje que aparezca al conectar los dispositivos.

**Nota:** que una capturadora funcione individualmente no demuestra que dos funcionen juntas. El ancho de banda USB, el hub y los formatos de video pueden limitar la combinación.
