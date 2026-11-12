# Arquitectura técnica

## 1. Principio central

El procesamiento audiovisual principal ocurre en el Samsung Galaxy A16 5G. La laptop ofrece una consola de operación completa, pero no recibe las señales originales para mezclarlas ni codifica el programa. Android mantiene una única realización y la web controla ese motor.

## 2. Motor Android

Responsabilidades previstas:

- Enumerar dispositivos USB mediante Android USB Host.
- Gestionar permisos USB y conexión/desconexión.
- Capturar entradas UVC, sujeto a compatibilidad del hardware.
- Mantener fuentes Preview y Program.
- Componer video, gráficos y audio.
- Codificar una salida H.264/AAC para grabación o emisión.
- Exponer una API local autenticada y un canal de eventos/estado para el panel web.
- Generar vistas proxy de monitoreo de baja resolución.

### Resolución objetivo

- Programa: 1280×720, 30 fps.
- Monitoreo remoto: resolución y tasa limitadas, configurables según capacidad de la red y laptop.
- Códec de salida inicial: H.264; audio AAC.
- Bitrate inicial de prueba: 3–4 Mbps, ajustable según conexión.

## 3. Entradas

1. HDMI 1 por capturadora USB UVC.
2. HDMI 2 por segunda capturadora USB UVC.
3. Cámara inalámbrica auxiliar desde Redmi Note 10, priorizando SRT por su latencia y tolerancia a redes variables.
4. Medios locales: PNG, imágenes, videos e intros/outros.

NDI se evaluará por separado. No se asumirá compatibilidad NDI nativa sin validar SDK, licencias, rendimiento y compatibilidad con Android.

## 4. Consola web: Modo Control y Modo Estudio

La laptop podrá abrir dos interfaces sobre el mismo motor Android. También se podrá acceder desde tablet u otro teléfono dentro de la red local.

### Modo Control

Interfaz compacta para operar rápidamente:

- Selección de cámaras/fuentes.
- CUT y FADE.
- Activación de gráficos y reproducción de intros/outros.
- Audio, REC, LIVE y estado de conexión.

### Modo Estudio (principal en laptop)

Interfaz amplia inspirada en una consola de realización profesional, organizada por paneles:

- **Multiview:** mosaico de las fuentes disponibles y estado de cada entrada.
- **Preview / Program:** monitor de previsualización y monitor de salida al aire.
- **Switcher:** botones de selección de fuentes, CUT, FADE y transiciones disponibles.
- **Escenas:** guardar y recuperar combinaciones de fuentes, gráficos y medios.
- **Gráficos:** cargar/seleccionar PNG transparentes, zócalos, nombres, cargos, logos, activar/desactivar y ajustar posición/tamaño.
- **Medios:** biblioteca para intros, outros, videos e imágenes; controles de reproducción.
- **Audio:** niveles, mute y selección de fuente cuando el motor Android lo permita.
- **Emisión y grabación:** destino configurado en forma segura, estado LIVE/REC, tiempo transcurrido y alertas.
- **Estado técnico:** conexión con el teléfono, fuentes detectadas, señal, resolución/FPS reportados, uso térmico/errores cuando Android pueda exponerlos.

El panel debe ser utilizable con mouse y teclado, con botones amplios y etiquetas claras. Se prioriza una interfaz web liviana, sin dependencias pesadas innecesarias.

### Política de monitoreo

La laptop no recibirá todas las señales en resolución completa. Android entregará vistas proxy de baja resolución y/o solo las vistas seleccionadas. El mosaico multiview deberá poder reducir FPS, pausar vistas no seleccionadas y ocultar el video para priorizar control en equipos modestos.

El Modo Estudio será completo en funciones de operación, no un segundo motor de mezcla. La composición final, codificación, grabación y transmisión seguirán en Android.

## 5. Comunicación local

La aplicación Android actuará como servidor local durante la sesión. El panel web se conectará a la dirección local del teléfono y se autenticará antes de enviar comandos.

Se evaluará HTTP para operaciones puntuales y WebSocket para estado/eventos en tiempo real. El video de monitoreo se resolverá con un transporte adecuado y medido en pruebas (por ejemplo WebRTC o MJPEG según latencia, compatibilidad y carga). La implementación final deberá validar origen, sesión y comandos permitidos. No se expondrán claves RTMP ni credenciales en el frontend.

## 6. Seguridad y privacidad

- No incluir contraseñas, tokens ni claves de emisión en el repositorio.
- No publicar el panel de control como servicio abierto a Internet.
- Limitar el control a la red local y exigir autenticación.
- Mostrar claramente cuándo cámara, micrófono, grabación o emisión están activos.

## 7. Restricciones de rendimiento

La primera prioridad es comprobar la captura UVC real en el teléfono. La captura simultánea de dos dispositivos, la decodificación de la cámara inalámbrica, la composición y la codificación pueden competir por CPU, memoria, USB y temperatura.

La laptop se usará para interfaz, control y monitoreo reducido. Se implementarán perfiles de monitoreo (solo estado, multiview económico y monitor seleccionado) para adaptarse a equipos modestos. No se prometerá 720p30 estable hasta medirlo en el dispositivo real.
