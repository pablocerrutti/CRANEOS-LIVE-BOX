# CRANEOS LIVE BOX — arquitectura web prioritaria

## Consola web

GitHub Pages sirve una consola adaptable a celular y laptop. El celular puede operar en modo autónomo; la laptop amplía las posibilidades de entradas y monitoreo.

- Preview y Program.
- Selección de fuente y multiview.
- Transiciones CUT y FADE.
- Captura de cámara USB/HDMI UVC mediante getUserMedia en navegadores compatibles.
- Una cámara móvil por Wi-Fi mediante WebRTC con intercambio manual de oferta/respuesta.
- Carga de videos e imágenes locales.
- Grabación local WebM desde el canvas Program mediante MediaRecorder.
- Ventana independiente de salida limpia Program, útil para mover a una pantalla HDMI externa.

## Ruta de emisión externa: laptop + Redmi Note 10

1. La laptop ejecuta la consola web y realiza la composición visual.
2. La ventana de salida limpia se mueve al monitor HDMI conectado a la laptop.
3. La salida HDMI entra a una capturadora USB HDMI conectada al Redmi Note 10.
4. USB Camera Pro en el Redmi codifica y envía RTMP/RTMPS a YouTube o Twitch.

**Importante:** la salida limpia actual contiene video del canvas. El audio todavía no dispone de un mezclador ni de una ruta HDMI garantizada. Para producción con audio se debe diseñar y probar el ruteo de audio del equipo y de la capturadora. La capturadora conectada al Redmi no puede estar simultáneamente conectada a la laptop.

Si se desea ingresar una cámara HDMI a la laptop y además enviar la salida de la laptop al Redmi, se necesitan dos dispositivos de captura independientes: uno para la entrada de cámara y otro para la salida de programa.

## Fuentes inalámbricas

La consola web actual tiene un flujo manual WebRTC para una cámara móvil. Se intercambian la oferta y la respuesta desde la página camera.html. La segunda cámara simultánea y la integración directa de VDO.Ninja están pendientes.

## Compatibilidad y seguridad

- Abrir la webapp mediante HTTPS (GitHub Pages) y conceder permiso de cámara/micrófono cuando corresponda.
- La disponibilidad de UVC depende del navegador y del sistema operativo.
- En Android móvil, la captura UVC en navegador no está garantizada; la laptop es el equipo principal de esta fase.
- La grabación WebM depende del soporte de MediaRecorder y del espacio disponible.
- La webapp no genera RTMP directamente. El Redmi con USB Camera Pro cumple esa función en el flujo acordado.
- No colocar claves RTMP en archivos del repositorio.

## Android nativo

El código Android se conserva como una línea de desarrollo posterior. La integración actual de RTMP no está compilada ni validada en hardware; no debe considerarse operativa ni reemplaza el flujo web + Redmi.


## Modo autónomo desde el celular — etapa web/PWA

La consola incluye captura directa de la cámara frontal o trasera del mismo teléfono mediante `getUserMedia`, entrada de audio del teléfono, selección PREVIEW/PROGRAM, transiciones y grabación local WebM desde el canvas. La interfaz adapta controles táctiles y se puede instalar como PWA en navegadores compatibles. En iPhone, la instalación se realiza desde Compartir → Añadir a pantalla de inicio; en Android Chrome puede aparecer el botón Instalar.

Esta etapa permite realizar y grabar desde el teléfono, pero **no equivale todavía a una emisión RTMP autónoma**. Safari/Chrome no exponen un emisor RTMP directo ni bonding de interfaces al JavaScript de la página. La publicación RTMP y el bonding quedan para el motor nativo Android y su servicio de red. La grabación local y la captura deben probarse en cada teléfono; el navegador puede limitar resolución, duración, multitarea y almacenamiento.
