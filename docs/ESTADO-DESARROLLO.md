# Estado de desarrollo — CRANEOS LIVE BOX

Actualizado: 12 de noviembre de 2026.

## Arquitectura actual

- Samsung Galaxy A16 5G: motor de realización.
- Una capturadora HDMI USB UVC como entrada cableada inicial.
- Fuentes inalámbricas por Wi-Fi: móviles y bodycams que expongan SRT o RTSP; RTMP como alternativa.
- Laptop: Modo Estudio y Modo Control; no realiza la mezcla ni codifica el programa.
- Objetivo de salida: 720p30 H.264/AAC, sujeto a pruebas.

## Implementado en el repositorio

- [x] Arquitectura general y separación entre motor Android y consola web.
- [x] Diseño inicial de Modo Control y Modo Estudio.
- [x] Panel web adaptable con HDMI 1, Wi-Fi 1, Wi-Fi 2 y medios.
- [x] Campos para configurar una fuente de red RTSP/SRT desde la consola.
- [x] Documentación de integración de móviles y bodycams Wi-Fi.

## Pendiente

- [ ] App Android instalable y diagnóstico UVC.
- [ ] Captura real de la capturadora HDMI.
- [ ] Receptor RTSP.
- [ ] Receptor SRT.
- [ ] Decodificación y composición simultánea.
- [ ] Preview/Program real y transporte de video a la laptop.
- [ ] Gráficos, escenas, audio y biblioteca multimedia.
- [ ] Grabación y emisión RTMP/RTMPS.
- [ ] API local autenticada.

## Próximo hito

Probar una capturadora UVC en el A16 y una fuente Wi-Fi (primero Redmi Note 10) al mismo tiempo. Registrar resolución, FPS, latencia, cortes, temperatura y estabilidad durante al menos 30 minutos.

La interfaz web es un prototipo de consola y aún no es un mezclador operativo. Las funciones solo se marcarán como listas después de su implementación y prueba en el teléfono real.
