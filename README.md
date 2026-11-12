# CRANEOS LIVE BOX

Consola audiovisual multicámara con Android como motor de realización y una interfaz web de estudio completa como control remoto.

## Objetivo

Construir una solución de realización en vivo, de bajo costo, orientada a eventos y transmisiones institucionales.

## Arquitectura acordada

- **Samsung Galaxy A16 5G:** motor principal de captura, mezcla, gráficos, audio, grabación y streaming.
- **Dos capturadoras HDMI a USB:** entradas de video UVC, sujetas a validación de compatibilidad simultánea.
- **Redmi Note 10:** cámara inalámbrica auxiliar, inicialmente mediante SRT.
- **Laptop:** consola web con Modo Control y Modo Estudio completo. No procesa ni mezcla el video principal.
- **Salida objetivo:** 1280×720 a 30 fps (720p30), H.264 y audio AAC.

## Funciones previstas

- Modo Control simplificado para operación rápida.
- Modo Estudio en laptop: multivista de fuentes, Preview/Program, escenas, transiciones, gráficos, biblioteca multimedia, audio, grabación y emisión.
- Selección de fuentes y transiciones CUT/FADE.
- Gráficos PNG transparentes, zócalos, nombres, cargos y logotipos.
- Reproducción de intros y outros desde el almacenamiento.
- Control de audio, grabación local y emisión RTMP/RTMPS.
- Panel adaptable a laptop, tablet y teléfono dentro de la red local.

## Etapas de desarrollo

1. **Validación de hardware USB:** detectar y visualizar una capturadora; luego probar dos simultáneas y comprobar alimentación/temperatura.
2. Motor Android: fuentes, preview/programa y transiciones.
3. Gráficos, escenas y biblioteca de medios.
4. Cámara inalámbrica mediante SRT; evaluar NDI como integración posterior.
5. Grabación y streaming 720p30.
6. API local segura y servicio de monitoreo para el panel.
7. Modo Control y Modo Estudio web completos.
8. Pruebas integrales y optimización.

## Importante

La compatibilidad de dos capturadoras en simultáneo depende del chipset de cada dispositivo, del hub USB, de la alimentación y de las limitaciones del teléfono. No se dará por garantizada hasta completar las pruebas reales.

La laptop será una consola de operación y monitoreo. El video principal seguirá procesándose en Android; las vistas del estudio en laptop serán proxies de baja resolución o vistas seleccionadas para evitar sobrecargar el equipo.

Este repositorio se desarrollará por etapas. No se considerará lista una función solo por estar dibujada en la interfaz: deberá estar implementada y probada.

## Estado

- [x] Repositorio inicializado.
- [x] Arquitectura con Modo Control y Modo Estudio documentada.
- [ ] Identificar RAM exacta del Samsung A16 5G.
- [ ] Identificar modelo/chipset de las capturadoras HDMI.
- [ ] Probar USB OTG y captura UVC.
- [ ] Probar dos capturadoras simultáneas.
