# Etapa 1 — Validación de hardware y red

## Kit inicial
- Samsung Galaxy A16 5G como motor.
- Una capturadora HDMI USB compatible con UVC.
- Hub USB OTG, si es necesario, con alimentación estable.
- Redmi Note 10 como cámara inalámbrica.
- Router Wi-Fi, preferentemente 5 GHz.

## Prueba USB
1. Conectar la capturadora al Samsung mediante USB-C OTG.
2. Aceptar el permiso USB si Android lo solicita.
3. Verificar enumeración del dispositivo y formato de video.
4. Conectar una fuente HDMI conocida y comprobar imagen y audio.
5. Mantener la prueba al menos 15 minutos y observar cortes, temperatura y carga.

## Prueba Wi-Fi
1. Conectar Samsung y Redmi a la misma red local.
2. Configurar en el Redmi una app emisora compatible con SRT o RTSP.
3. Obtener la URL/dirección del flujo.
4. Comprobar que el Samsung pueda recibirlo.
5. Medir estabilidad y latencia durante al menos 15 minutos.

## Prueba combinada
Mantener la capturadora HDMI y la cámara Wi-Fi activas simultáneamente. Objetivo inicial: salida 720p30, con previews de menor resolución si es necesario. Observar temperatura, cortes, audio y estabilidad durante 30 minutos.

## Criterios de aprobación
- Capturadora funcional individualmente.
- Fuente Wi-Fi funcional individualmente.
- Ambas funcionan simultáneamente sin congelamientos continuos.
- Sin reinicios ni desconexiones.
- Temperatura adecuada para una sesión razonable.

## Datos necesarios
- RAM exacta del A16.
- Marca/modelo de capturadora y hub.
- Marca/modelo de bodycam que se quiera integrar.
- Protocolo disponible: SRT, RTSP, RTMP, ONVIF u otro.

Tener Wi-Fi no garantiza integración: debe existir un flujo de video accesible o una interfaz documentada.
