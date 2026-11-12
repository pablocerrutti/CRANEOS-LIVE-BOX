# CRANEOS LIVE BOX

Consola audiovisual multicámara con Android como motor de realización y una interfaz web de estudio como control remoto.

## Objetivo
Construir una solución de realización en vivo, de bajo costo, para eventos y transmisiones institucionales.

## Arquitectura acordada
- **Samsung Galaxy A16 5G:** motor principal de captura, mezcla, gráficos, audio, grabación y streaming.
- **Una capturadora HDMI USB UVC:** entrada cableada inicial.
- **Fuentes Wi-Fi:** móviles y bodycams que permitan emitir por SRT o RTSP; RTMP podrá evaluarse como alternativa.
- **Redmi Note 10:** primera cámara inalámbrica de prueba.
- **Laptop:** consola web con Modo Control y Modo Estudio. No mezcla ni codifica el programa.
- **Salida objetivo:** 1280×720 a 30 fps, H.264/AAC, sujeto a pruebas reales.

## Funciones previstas
- Modo Control simplificado y Modo Estudio completo.
- Una entrada HDMI y varias fuentes IP/Wi-Fi según rendimiento.
- Preview/Program, multiview y transiciones CUT/FADE.
- Escenas, gráficos PNG, zócalos, nombres, cargos y logotipos.
- Biblioteca de clips, imágenes, intros y outros.
- Audio, grabación local y emisión RTMP/RTMPS.
- Panel adaptable a laptop, tablet y teléfono en la red local.

## Etapas
1. Validar una capturadora UVC en el Samsung.
2. Incorporar una fuente Wi-Fi desde el Redmi Note 10.
3. Probar HDMI + Wi-Fi simultáneamente.
4. Implementar motor de composición, Preview/Program y transiciones.
5. Incorporar gráficos, escenas, medios y audio.
6. Añadir grabación y emisión.
7. Conectar API local segura y consola web.
8. Optimizar y probar estabilidad térmica y de red.

## Fuentes inalámbricas
SRT será la opción preferida para contribución móvil cuando el emisor y receptor sean compatibles. RTSP permitirá integrar cámaras IP y bodycams que expongan ese protocolo. Tener Wi-Fi no garantiza integración: algunos equipos solo trabajan con aplicaciones propietarias. Ver [Fuentes Wi-Fi](docs/FUENTES-WIFI.md).

## Importante
La laptop es consola de operación y monitoreo. El procesamiento audiovisual ocurre en Android. Las vistas web serán proxies de baja resolución o vistas seleccionadas para no sobrecargar el equipo.
No se considerará lista una función solo por aparecer en pantalla: deberá implementarse y probarse en el hardware objetivo.

## Estado
- [x] Documentación de arquitectura y modos.
- [x] Prototipo web inicial con HDMI 1, Wi-Fi 1, Wi-Fi 2 y medios.
- [x] Documentación de fuentes inalámbricas.
- [ ] Aplicación Android instalable y diagnóstico UVC.
- [ ] Captura real de HDMI.
- [ ] Recepción real SRT/RTSP.
- [ ] Prueba simultánea HDMI + Wi-Fi.
