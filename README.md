# CRANEOS LIVE BOX

Consola audiovisual multicámara con Android como motor de realización y una interfaz web de estudio como control remoto.

## Objetivo
Construir una solución de realización en vivo, de bajo costo, para eventos y transmisiones institucionales.

## Arquitectura acordada
- **Samsung Galaxy A16 5G:** motor principal de captura, mezcla, gráficos, audio, grabación y streaming.
- **Una capturadora HDMI USB UVC:** entrada cableada inicial, con detección USB automática en desarrollo.
- **Fuentes Wi-Fi:** móviles y bodycams que permitan emitir por SRT o RTSP; RTMP podrá evaluarse como alternativa.
- **Redmi Note 10:** primera cámara inalámbrica de prueba.
- **Laptop:** consola web con Modo Control y Modo Estudio. No mezcla ni codifica el programa.
- **Salida objetivo:** 1280×720 a 30 fps, H.264/AAC, sujeto a pruebas reales.

## Funciones previstas
- Modo Control simplificado y Modo Estudio completo.
- Detección de cámaras USB UVC y una entrada HDMI inicial.
- Varias fuentes IP/Wi-Fi según rendimiento.
- Preview/Program, multiview y transiciones CUT/FADE.
- Escenas, gráficos PNG, zócalos, nombres, cargos y logotipos.
- Biblioteca de clips, imágenes, intros y outros.
- Audio, grabación local y emisión RTMP/RTMPS.
- Panel adaptable a laptop, tablet y teléfono en la red local.

## Etapas
1. **Detección USB:** base Android creada; falta compilar y validar con la capturadora real.
2. Implementar captura de video UVC y mostrar señal real.
3. Incorporar una fuente Wi-Fi desde el Redmi Note 10.
4. Probar HDMI + Wi-Fi simultáneamente.
5. Implementar motor de composición, Preview/Program y transiciones.
6. Incorporar gráficos, escenas, medios y audio.
7. Añadir grabación, emisión y API local segura para la consola.
8. Optimizar y probar estabilidad térmica y de red.

## Fuentes inalámbricas
SRT será la opción preferida para contribución móvil cuando el emisor y receptor sean compatibles. RTSP permitirá integrar cámaras IP y bodycams que expongan ese protocolo. Tener Wi-Fi no garantiza integración: algunos equipos solo trabajan con aplicaciones propietarias. Ver [Fuentes Wi-Fi](docs/FUENTES-WIFI.md).

## Importante
La laptop es consola de operación y monitoreo. El procesamiento audiovisual ocurre en Android. Las vistas web serán proxies de baja resolución o vistas seleccionadas para no sobrecargar el equipo.
No se considerará lista una función solo por aparecer en pantalla: deberá implementarse y probarse en el hardware objetivo.

## Estado
- [x] Prototipo web inicial con HDMI 1, Wi-Fi 1, Wi-Fi 2 y medios.
- [x] Base Android de detección UVC y solicitud de permiso USB.
- [ ] Compilar e instalar la app de diagnóstico en el Samsung.
- [ ] Captura real de HDMI y visualización de fotogramas.
- [ ] Conectar detección USB con API local y multiview web.
- [ ] Recepción real SRT/RTSP.
- [ ] Prueba simultánea HDMI + Wi-Fi.

Detalles: [Detección USB UVC](docs/USB-UVC-AUTODETECCION.md).
