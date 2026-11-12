 # CRANEOS LIVE BOX — arquitectura de la consola web

## Funciones implementadas en esta etapa
- Consola alojada en GitHub Pages.
- Captura de una cámara UVC/HDMI conectada a la laptop mediante permisos del navegador (getUserMedia).
- Preview y Program en la página, con selección de fuente y TAKE.
- Entrada de video local e imágenes desde archivos.
- Grabación local del programa en WebM mediante MediaRecorder.
- Cámara iPhone por WebRTC con emparejamiento manual mediante oferta/respuesta.

## Límites de GitHub Pages
GitHub Pages sirve archivos estáticos. No mantiene procesos de fondo ni servidores de medios. Por eso no recibe RTMP directamente, no recibe RTSP directamente desde el navegador, no ejecuta un servidor SRT y no sigue procesando si se cierra la pestaña.

## Redmi Note 10
USB Camera Pro puede seguir capturando la capturadora y emitiendo RTMP directamente a YouTube/Twitch. Para convertir esa salida en una fuente interna de esta consola se necesita un receptor RTMP o conversión a WebRTC/HLS en un equipo o servicio de la red. No guardar claves de emisión en GitHub.

## Límites y pruebas
La captura USB se realiza en la laptop, porque el navegador que abre la consola obtiene el dispositivo UVC. La página de cámara del iPhone captura desde Safari y envía WebRTC punto a punto; la negociación se realiza copiando dos códigos. STUN ayuda a establecer la conexión, pero no garantiza conectividad en todas las redes y no sustituye TURN cuando hay NAT restrictivo.

La grabación web se guarda en WebM y depende del soporte del navegador. El audio se incorpora cuando la fuente seleccionada entrega una pista de audio. Esta consola todavía no es un mezclador broadcast profesional ni un emisor RTMP autónomo.

## Prueba
1. Abrir la consola en Chrome o Edge en laptop desde HTTPS.
2. Conectar la capturadora y pulsar USB/HDMI; permitir acceso y elegir la capturadora.
3. Abrir camera.html en Safari del iPhone, activar cámara y copiar oferta.
4. Pegar oferta en consola, crear respuesta y copiarla al iPhone.
5. Pegar respuesta en iPhone y conectar. Seleccionar iPhone en Preview y TAKE.
6. Grabar un minuto y reproducir el WebM descargado.
