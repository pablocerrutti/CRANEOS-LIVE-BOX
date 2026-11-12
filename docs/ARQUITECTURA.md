# Arquitectura técnica

## 1. Principio central
El Samsung Galaxy A16 5G será el motor de realización. La laptop será una consola de operación completa, pero no mezclará ni codificará el programa. El sistema comienza con una capturadora HDMI USB y admite fuentes adicionales por Wi-Fi cuando entreguen protocolos estándar.

## 2. Motor Android
Responsabilidades:
- Enumerar y gestionar una capturadora USB UVC.
- Recibir fuentes de red RTSP y SRT; RTMP podrá evaluarse después.
- Mantener Preview y Program.
- Componer video, gráficos y audio.
- Codificar H.264/AAC para grabación o emisión.
- Exponer API local autenticada y estado/eventos al panel web.
- Generar previews proxy de baja resolución.

## 3. Entradas de video
1. **HDMI 1:** una capturadora USB UVC.
2. **Wi-Fi 1:** móvil (primera prueba: Redmi Note 10) o bodycam con salida RTSP/SRT.
3. **Wi-Fi 2 y futuras:** cámaras IP adicionales si la memoria, decodificación, red y temperatura lo permiten.
4. **Medios:** imágenes, PNG, videos, intros y outros locales.

### Protocolos
- **SRT:** preferido para contribución móvil en vivo si ambos extremos lo soportan.
- **RTSP:** para cámaras IP y bodycams que expongan una URL accesible.
- **RTMP/RTMPS:** posible alternativa de contribución, no primera prioridad.
- **Propietarios:** no se asumirá compatibilidad con cámaras que solo funcionen mediante su aplicación cerrada.

La documentación de compatibilidad y pruebas está en [FUENTES-WIFI.md](FUENTES-WIFI.md).

## 4. Resolución objetivo
- Programa: 1280×720, 30 fps.
- Monitoreo remoto: resolución/FPS reducidos y configurables.
- Códec: H.264 y AAC.
- Bitrate inicial: 3–4 Mbps, ajustable.

## 5. Consola web
La laptop, tablet o teléfono podrán abrir la consola en la red local.

### Modo Control
- Selección de HDMI, cámaras Wi-Fi y medios.
- CUT/FADE/TAKE.
- Gráficos, medios, audio, REC, LIVE y estado.

### Modo Estudio
- Multiview con fuentes disponibles.
- Preview / Program.
- Switcher y transiciones.
- Escenas.
- Gráficos y zócalos.
- Biblioteca de clips y placas.
- Audio y medidores.
- Grabación/emisión.
- Estado técnico de fuentes, red y temperatura cuando Android lo exponga.

La laptop no hará composición final. Recibirá vistas proxy para monitoreo.

## 6. Red y seguridad
- Motor y cámaras deben poder comunicarse por red local.
- Evitar Wi-Fi de invitados con aislamiento entre clientes.
- Preferir banda de 5 GHz y un punto de acceso estable.
- Autenticar comandos y limitar el control a la red local.
- No guardar contraseñas RTSP/SRT, tokens ni claves RTMP en GitHub o JavaScript.
- Guardar credenciales sensibles en Android y redactarlas en la interfaz.

## 7. Rendimiento
La primera meta de prueba es una entrada UVC + una fuente Wi-Fi a 720p30. Después se probarán más fuentes. No se garantiza un número ilimitado de cámaras: cada flujo adicional requiere decodificación, memoria y ancho de banda y eleva la temperatura.
