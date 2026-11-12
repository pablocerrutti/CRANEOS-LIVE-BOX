# CRANEOS LIVE BOX

Consola audiovisual multicámara con Android como motor de realización y una interfaz web liviana como control remoto.

## Objetivo

Construir una solución de realización en vivo, de bajo costo, orientada a eventos y transmisiones institucionales.

## Arquitectura acordada

- **Samsung Galaxy A16 5G:** motor principal de captura, mezcla, gráficos, audio, grabación y streaming.
- **Dos capturadoras HDMI a USB:** entradas de video UVC, sujetas a validación de compatibilidad simultánea.
- **Redmi Note 10:** cámara inalámbrica auxiliar, inicialmente mediante SRT.
- **Laptop:** panel web de control y monitoreo; no procesa ni mezcla el video principal.
- **Salida objetivo:** 1280×720 a 30 fps (720p30), H.264 y audio AAC.

## Funciones previstas

- Selección de fuentes y vista previa/programa.
- Cortes CUT y transiciones FADE.
- Gráficos PNG transparentes, zócalos, nombres, cargos y logotipos.
- Reproducción de intros y outros desde el almacenamiento.
- Control de audio, grabación local y emisión RTMP/RTMPS.
- Panel web adaptable a laptop, tablet y teléfono dentro de la red local.

## Etapas de desarrollo

1. **Validación de hardware USB:** detectar y visualizar una capturadora; luego probar dos simultáneas y comprobar alimentación/temperatura.
2. Motor de realización: fuentes, preview/programa y transiciones.
3. Gráficos y biblioteca de medios.
4. Cámara inalámbrica mediante SRT; evaluar NDI como integración posterior.
5. Grabación y streaming 720p30.
6. API local segura para control remoto.
7. Panel web liviano de control y monitoreo.
8. Pruebas integrales y optimización.

## Importante

La compatibilidad de dos capturadoras en simultáneo depende del chipset de cada dispositivo, del hub USB, de la alimentación y de las limitaciones del teléfono. No se dará por garantizada hasta completar las pruebas reales.

Este repositorio se desarrollará por etapas. No se considerará lista una función solo por estar dibujada en la interfaz: deberá estar implementada y probada.

## Estado

- [x] Repositorio inicializado.
- [ ] Identificar RAM exacta del Samsung A16 5G.
- [ ] Identificar modelo/chipset de las capturadoras HDMI.
- [ ] Probar USB OTG y captura UVC.
- [ ] Probar dos capturadoras simultáneas.
