# Integración de cámaras móviles RTMP sobre Tailscale

## Decisión de arquitectura

Las aplicaciones Moblin e IRL Streaming serán los codificadores de las cámaras móviles. No se reemplazan ni se les pide que envíen directamente a YouTube/Facebook cuando se usan como cámaras de la realización.

Cada móvil publica una señal RTMP hacia un receptor privado en la laptop Windows 11. Tailscale proporciona la conectividad privada entre el móvil y la laptop. MediaMTX recibe y enruta las señales sin recodificarlas.

```text
Android / iPhone
Moblin o IRL Streaming
       │ RTMP por Tailscale
       ▼
Laptop Windows 11
MediaMTX (receptor RTMP)
       │ WebRTC / WHEP para lectura
       ▼
CRANEOS LIVE BOX
Preview · Program · multiview · audio
       │
       ▼
Salida HDMI → capturadora → Redmi Note 10
       │
       ▼
USB Camera Pro → RTMP de emisión
```

## Por qué MediaMTX

MediaMTX es software libre, tiene un ejecutable nativo para Windows, recibe varias publicaciones en rutas independientes y puede entregar un mismo flujo mediante otro protocolo. La primera etapa no necesita Docker ni FFmpeg ni recodificación, algo conveniente para la laptop Celeron.

Documentación oficial:
- https://mediamtx.org/docs/kickoff/introduction
- https://mediamtx.org/docs/kickoff/install
- https://mediamtx.org/docs/features/publish-and-read
- https://mediamtx.org/docs/features/authentication

## Direcciones de las cámaras

La laptop debe tener Tailscale conectado. Se utilizará su IP Tailscale (normalmente dentro de 100.64.0.0/10) o su nombre MagicDNS.

Cada móvil debe estar conectado al mismo tailnet y autorizado por el propietario.

Rutas sugeridas:
- Cámara 1: `cam1`
- Cámara 2: `cam2`
- Cámara 3: `cam3`
- Cámara 4: `cam4`

Ejemplo de URL de publicación (reemplazar la IP por la IP Tailscale real de la laptop):

```text
rtmp://100.x.y.z:1935/cam1
```

MediaMTX permite autenticación RTMP mediante parámetros `user` y `pass` en la URL. La aplicación móvil debe enviar las credenciales de publicación asignadas a esa cámara. La forma de introducir URL y clave depende de cómo Moblin o IRL Streaming presenten sus campos de servidor y stream key; se verificará con una prueba de cada aplicación antes de dar por cerrado el procedimiento.

No reutilizar las claves de YouTube/Facebook. Crear credenciales independientes para las cámaras.

## Seguridad de red

- No publicar el puerto RTMP 1935 en el router ni abrirlo a Internet.
- Permitir las conexiones entrantes únicamente desde el rango Tailscale en el Firewall de Windows.
- Mantener usuarios separados y permisos limitados a publicar únicamente en su ruta.
- No guardar contraseñas de cámara en el repositorio GitHub.
- El archivo `server/mediamtx.example.yml` contiene credenciales de ejemplo que deben reemplazarse antes de usarlo.
- Tailscale cifra el transporte entre dispositivos; la autenticación de MediaMTX limita quién puede publicar.

Tailscale no crea el receptor de vídeo: MediaMTX debe estar ejecutándose y escuchando en la laptop. Tailscale únicamente proporciona la red privada entre equipos.

## Puertos previstos

- TCP 1935: publicación RTMP desde los móviles.
- TCP 8889 y UDP 8189: lectura WebRTC desde la consola, cuando se integre el reproductor.
- La API administrativa de MediaMTX no se expondrá a los móviles ni a Internet.

La lista exacta de reglas de firewall se ajustará durante la instalación y prueba. No abrir puertos que no se utilicen.

## Integración en la consola

La webapp actual de GitHub Pages no puede recibir RTMP directamente en el navegador. La arquitectura usa MediaMTX como puente:

1. Moblin / IRL Streaming publica RTMP en una ruta de MediaMTX.
2. MediaMTX ofrece esa ruta para lectura WebRTC (WHEP).
3. Un módulo receptor de CRANEOS crea una conexión WebRTC de lectura por cada cámara.
4. Cada pista recibida se registra como una fuente de vídeo independiente en la bandeja de entradas.
5. El operador asigna nombre, etiqueta CAM 1/CAM 2, y selecciona Preview o Program.

La conexión de lectura debe implementarse con un cliente WHEP compatible con MediaMTX y conectarse al ciclo de vida de fuentes de `web/studio.js`. No se debe simular una fuente ni declarar la integración terminada hasta que la pista remota aparezca en Preview, pueda pasar a Program y mantenga el audio correspondiente.

Para que una página HTTPS alojada en GitHub Pages pueda leer el receptor privado, habrá que resolver HTTPS/CORS y la dirección WebRTC accesible por Tailscale (por ejemplo, mediante Tailscale Serve o una configuración equivalente). No se debe mezclar una página HTTPS con un endpoint HTTP inseguro ni habilitar CORS global sin necesidad.

## Etapas de implementación y prueba

### Etapa 1 — receptor privado
- Instalar MediaMTX en Windows 11.
- Configurar autenticación y rutas cam1–cam4.
- Restringir Firewall de Windows al tailnet.
- Probar una publicación RTMP desde un móvil por datos móviles y Tailscale.
- Repetir con el segundo sistema operativo.

### Etapa 2 — integración en CRANEOS
- Añadir panel de cámaras RTMP con estado de conexión.
- Implementar lectura WebRTC/WHEP desde MediaMTX.
- Incorporar cada cámara como fuente nativa del switcher.
- Probar Preview, Program, audio, reconexión y desconexión.

### Etapa 3 — rendimiento
- Empezar con 720p a 25/30 fps y bitrate moderado.
- Añadir cámaras de una en una y observar CPU, memoria, temperatura y estabilidad de red.
- Mantener MediaMTX sin transcodificación en la laptop.
- Ajustar resolución/bitrate por cámara según la capacidad real del equipo y las conexiones.

## Estado honesto

Este documento define la arquitectura. La recepción RTMP, la configuración de MediaMTX, las reglas de firewall y la integración WHEP todavía deben instalarse y probarse en los equipos reales. El panel de redes existente no representa aún cámaras RTMP conectadas.

El bonding multipath es una etapa independiente: Tailscale conecta los dispositivos, pero no agrega por sí solo el ancho de banda de varias conexiones de Internet.
