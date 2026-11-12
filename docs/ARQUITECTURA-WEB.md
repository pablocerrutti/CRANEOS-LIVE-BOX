# CRANEOS LIVE BOX — arquitectura web + Android

## Consola web
- GitHub Pages sirve la interfaz de realización.
- Preview/Program, selección de fuente, CUT/FADE y composición del programa en un canvas.
- Captura UVC desde navegador compatible cuando la capturadora está conectada a una laptop.
- Cámara móvil por WebRTC con emparejamiento manual (oferta/respuesta).
- Grabación WebM local desde el navegador, cuando MediaRecorder está disponible.

## Emisión móvil desde Samsung
La aplicación Android envuelve la consola web en WebView y agrega un puente nativo para la emisión RTMP/RTMPS mediante RootEncoder y MediaProjection. La app solicita autorización explícita de captura de pantalla; después codifica la pantalla del dispositivo en H.264 y el audio del micrófono en AAC.

En esta primera integración, la emisión transmite la vista de programa a pantalla completa (modo de salida limpia). Se configura una URL RTMP/RTMPS completa en el panel de emisión de la consola. La dirección incluye la clave de emisión: no compartir capturas de pantalla ni pegarla en archivos del repositorio.

Para detener la transmisión en esta versión inicial, usar la tecla física de bajar volumen. La app debe permanecer abierta durante la prueba. Esta integración es un primer puente funcional de pantalla a RTMP; todavía no equivale a un motor broadcast nativo completo.

## Limitaciones importantes
- MediaProjection captura la pantalla completa del teléfono; por eso la consola pasa a modo Programa antes de iniciar.
- El audio transmitido en esta primera versión es el micrófono del Samsung; todavía no hay mezclador de audio independiente.
- El panel web actual tiene una conexión WebRTC manual para una cámara móvil. Dos cámaras simultáneas y VDO.Ninja como entradas integradas siguen pendientes.
- La capturadora UVC en Android todavía no está conectada al mezclador web; la detección USB previa era solo diagnóstico.
- No se ha validado todavía en el Samsung A16 ni medido estabilidad, temperatura, FPS o latencia.
- No guardar claves RTMP en GitHub ni en capturas públicas.

## Modo estudio con laptop + Redmi
La laptop puede realizar la mezcla y enviar la salida limpia por HDMI a la capturadora conectada al Redmi Note 10. USB Camera Pro en el Redmi realiza la codificación y emisión RTMP. Esta ruta es independiente del codificador Android de la modalidad móvil.

## Prueba inicial en Samsung
1. Esperar que GitHub Pages publique los últimos cambios.
2. Instalar el APK de depuración generado por GitHub Actions.
3. Abrir CRANEOS LIVE BOX y conectar una cámara móvil mediante el flujo WebRTC actual.
4. Seleccionar la cámara en Preview y pulsar TAKE para llevarla a Program.
5. En el panel «Emisión desde Android», ingresar la URL RTMP/RTMPS completa y pulsar «Emitir desde este Samsung».
6. Aceptar el permiso de captura de pantalla de Android.
7. Comprobar en YouTube/Twitch que aparece la imagen y el audio. Bajar volumen detiene la emisión.

## Seguridad
La clave RTMP se introduce en tiempo de ejecución. El repositorio no almacena claves. RTMP sin TLS transmite datos sin cifrar; preferir RTMPS cuando el proveedor lo admita.
