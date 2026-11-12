# CRANEOS LIVE BOX

Consola web de realización audiovisual para laptop, tablet y teléfono.

**Webapp:** https://pablocerrutti.github.io/CRANEOS-LIVE-BOX/

## Prioridad de esta etapa

Dejar primero operativa y comprobable la versión web. El desarrollo del APK y la emisión RTMP nativa quedan en pausa hasta estabilizar la consola.

## Funciones web implementadas en el código

- Modo Estudio y Modo Control adaptable a pantallas pequeñas.
- Captura de una cámara USB/HDMI UVC desde un navegador compatible, conectada al equipo que opera la consola.
- Una cámara móvil por Wi-Fi mediante WebRTC con intercambio manual de oferta y respuesta.
- Incorporación de videos e imágenes locales como fuentes.
- Monitores Preview y Program.
- Selector de fuente, TAKE, CUT y FADE.
- Multiview de las fuentes conectadas.
- Grabación local de la salida Program en WebM cuando el navegador soporta MediaRecorder.
- Ventana independiente de salida limpia Program para colocar en un monitor HDMI externo.

## Flujo recomendado con laptop y Redmi Note 10

1. Abrir la webapp en Chrome o Edge desde la laptop.
2. Conectar la capturadora USB/HDMI a la laptop y seleccionar la fuente en la consola.
3. Conectar una cámara móvil por Wi-Fi si se necesita una segunda fuente.
4. Seleccionar Preview y pulsar TAKE para llevarla a Program.
5. Pulsar **Abrir salida limpia para HDMI**.
6. Mover esa ventana a la pantalla HDMI y maximizarla o ponerla en pantalla completa.
7. Conectar la salida HDMI de la laptop a la capturadora que esté conectada al Redmi Note 10.
8. Usar USB Camera Pro en el Redmi para codificar y emitir a RTMP.

La salida limpia web transmite imagen. El audio HDMI y la mezcla de audio requieren una etapa específica de implementación y prueba. La capturadora usada por el Redmi no puede estar conectada al mismo tiempo a la laptop; para ingresar otra cámara HDMI a la laptop se necesita otra entrada de captura.

## Límites actuales

- Una conexión móvil WebRTC está implementada en la consola; la segunda cámara Wi-Fi y VDO.Ninja integrado siguen pendientes.
- La captura USB funciona a través de las API del navegador en equipos compatibles. El soporte de capturadoras en Android depende del navegador, el sistema y el dispositivo.
- La ventana HDMI es una salida de video del canvas; todavía no hay ruteo completo de audio ni mezclador de audio.
- La webapp no codifica ni envía RTMP por sí sola. Para la ruta actual se usa el Redmi con USB Camera Pro.
- La grabación depende de las capacidades de MediaRecorder del navegador y se descarga como WebM.
- La estabilidad, latencia y rendimiento deben validarse con las cámaras y equipos reales.

## Desarrollo Android

El proyecto Android queda conservado en el repositorio, pero no es el foco de esta etapa. La compilación de Android está pendiente de corregir y validar. No hay un APK verificado para instalar.

## Criterio de calidad

No se marcará una función como terminada solo porque exista un botón o un panel. Debe pasar la validación automática correspondiente y una prueba real con los equipos.
