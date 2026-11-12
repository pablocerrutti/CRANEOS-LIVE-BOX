# Arquitectura técnica

## 1. Principio central

El procesamiento audiovisual principal ocurre en el Samsung Galaxy A16 5G. La laptop no recibe todas las señales para mezclarlas: envía órdenes y muestra información de estado y una vista de monitoreo reducida.

## 2. Motor Android

Responsabilidades previstas:

- Enumerar dispositivos USB mediante Android USB Host.
- Gestionar permisos USB y conexión/desconexión.
- Capturar entradas UVC, sujeto a compatibilidad del hardware.
- Mantener una fuente activa de programa y una fuente de previsualización.
- Componer video, gráficos y audio.
- Codificar una salida H.264/AAC para grabación o emisión.
- Exponer una API local autenticada para el panel web.

### Resolución objetivo

- Programa: 1280×720, 30 fps.
- Monitoreo remoto: resolución reducida y tasa limitada para no sobrecargar el navegador ni la red.
- Códec de salida inicial: H.264; audio AAC.
- Bitrate inicial de prueba: 3–4 Mbps, ajustable según conexión.

## 3. Entradas

1. HDMI 1 por capturadora USB UVC.
2. HDMI 2 por segunda capturadora USB UVC.
3. Cámara inalámbrica auxiliar desde Redmi Note 10, priorizando SRT por su latencia y tolerancia a redes variables.
4. Medios locales: PNG, imágenes, videos e intros/outros.

NDI se evaluará por separado. No se asumirá compatibilidad NDI nativa sin validar SDK, licencias, rendimiento y compatibilidad con Android.

## 4. Panel web

El panel se ejecutará en la laptop u otro dispositivo de la misma red local. Será una interfaz web responsive y de bajo consumo.

Funciones de control:

- Estado del motor y de las entradas.
- Selección de fuente Preview/Program.
- CUT y FADE.
- Activación y posición de gráficos.
- Selección de intros/outros.
- Nivel y mute de audio.
- REC, LIVE y estado de conexión.

El panel no codificará ni mezclará el programa. El monitoreo será opcional y de baja resolución.

## 5. Comunicación local

La aplicación Android actuará como servidor local durante la sesión. El panel web descubrirá o ingresará la dirección local del teléfono y se autenticará antes de enviar comandos.

Se evaluará HTTP para operaciones puntuales y WebSocket para estado/eventos en tiempo real. La implementación final deberá validar origen, sesión y comandos permitidos. No se expondrán claves RTMP ni credenciales en el frontend.

## 6. Seguridad y privacidad

- No incluir contraseñas, tokens ni claves de emisión en el repositorio.
- No publicar el panel de control como servicio abierto a Internet.
- Limitar el control a la red local y exigir autenticación.
- Mostrar claramente cuándo cámara, micrófono, grabación o emisión están activos.

## 7. Restricciones de rendimiento

La primera prioridad es comprobar la captura UVC real en el teléfono. La captura simultánea de dos dispositivos, la decodificación de la cámara inalámbrica, la composición y la codificación pueden competir por CPU, memoria, USB y temperatura.

Se implementarán pruebas incrementales y perfiles de calidad. No se prometerá 720p30 estable hasta medirlo en el dispositivo real.
