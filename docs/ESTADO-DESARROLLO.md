# Estado de desarrollo — CRANEOS LIVE BOX

Actualizado: 12 de noviembre de 2026.

## Prioridad actual

**Estabilizar la webapp antes de continuar con Android.**

Webapp publicada: https://pablocerrutti.github.io/CRANEOS-LIVE-BOX/

## Funciones presentes en el código web

- [x] Interfaz adaptable Modo Estudio / Modo Control.
- [x] Selección de entrada USB/UVC desde navegador compatible.
- [x] Una fuente móvil Wi-Fi mediante WebRTC con intercambio manual.
- [x] Carga de videos e imágenes locales.
- [x] Preview y Program en canvas.
- [x] TAKE, CUT y FADE.
- [x] Multiview de fuentes.
- [x] Grabación WebM local de Program.
- [x] Ventana independiente de salida limpia Program para monitor HDMI.
- [x] Validación automática de sintaxis JavaScript mediante GitHub Actions (en ejecución/verificación).

## Pendiente de prueba real

- [ ] Confirmar apertura y funcionamiento completo en Chrome/Edge de laptop.
- [ ] Probar capturadora HDMI USB UVC en laptop.
- [ ] Probar cámara iPhone por Wi-Fi y reconexión.
- [ ] Verificar Preview, TAKE, CUT y FADE con video real.
- [ ] Verificar grabación WebM y reproducción del archivo.
- [ ] Probar salida limpia en un monitor HDMI y capturadora conectada al Redmi.
- [ ] Resolver ruteo y mezcla de audio para la salida HDMI.
- [ ] Incorporar una segunda cámara Wi-Fi y evaluar VDO.Ninja.
- [ ] Optimizar resolución/FPS para la laptop de bajo consumo.

## Límites conocidos

- La webapp no transmite RTMP directamente desde el navegador.
- La salida HDMI limpia actual es de video; el audio no está mezclado ni garantizado en esa salida.
- El soporte UVC en teléfonos Android depende del navegador y del dispositivo; la ruta principal de esta etapa es la laptop.
- Android/RTMP nativo queda en pausa. El último intento de compilación Android falló por incompatibilidad de metadatos Kotlin de la dependencia RootEncoder; no hay APK verificado.

## Próximo hito

Validar el flujo básico en laptop: capturadora USB → Preview → TAKE → Program → salida limpia HDMI. Después incorporar el iPhone como segunda fuente y probar el flujo laptop HDMI → capturadora → Redmi Note 10 → USB Camera Pro.
