# Fuentes de video inalámbricas

## Objetivo

CRANEOS LIVE BOX comenzará con una capturadora HDMI USB y admitirá fuentes inalámbricas adicionales por Wi-Fi. La arquitectura no debe depender de una marca concreta de teléfono o bodycam.

## Protocolos previstos

- **SRT (preferido para contribución en vivo):** adecuado cuando la cámara o la aplicación emisora lo soporta. Permite configurar latencia y recuperación ante pérdida de paquetes. Requiere emisor y receptor compatibles, dirección/puerto y conectividad entre ambos.
- **RTSP (compatibilidad con cámaras IP):** opción habitual en cámaras IP y algunas bodycams. Se necesita la URL exacta, credenciales si corresponde y compatibilidad de códec.
- **RTMP/RTMPS:** puede servir como alternativa de contribución si el dispositivo emisor lo ofrece, aunque no será el primer protocolo de entrada.
- **Protocolos propietarios:** algunas bodycams Wi-Fi solo transmiten a su propia aplicación/base. No se considerarán compatibles hasta verificar que expongan RTSP, SRT, RTMP, ONVIF o una API/SDK documentada.

## Escenarios

1. Samsung A16 5G: motor de realización y receptor de fuentes.
2. Capturadora HDMI USB: una entrada cableada inicial.
3. Redmi Note 10: cámara inalámbrica mediante una aplicación emisora compatible con SRT o RTSP.
4. Bodycam Wi-Fi: se incorpora si permite extraer un flujo estándar y la red puede alcanzarla.
5. Fuentes inalámbricas futuras: dos o más entradas, limitadas por decodificación, memoria, temperatura y ancho de banda.

## Red recomendada

- Teléfono motor y cámaras en la misma red local o en una red con rutas entre dispositivos.
- Preferir router Wi-Fi 5/6 de doble banda y 5 GHz para video; evitar redes de invitados con aislamiento de clientes.
- Reservar direcciones DHCP para las cámaras/receptores cuando sea posible.
- Medir latencia, pérdida de paquetes, estabilidad y temperatura con cada fuente.
- Para uso móvil en exteriores, probar un router/AP dedicado; el hotspot del propio teléfono puede limitar el rendimiento.

## Seguridad

- No publicar URLs con usuario/contraseña en GitHub.
- No guardar credenciales en JavaScript ni en el repositorio.
- Guardar configuración sensible en el dispositivo Android y mostrarla en el panel solo de forma redactada.
- Mantener el control dentro de la red local y autenticar los comandos.

## Capacidad y límites

La arquitectura permitirá añadir fuentes, pero no implica que el A16 pueda decodificar un número ilimitado de cámaras. La primera meta será una entrada UVC + una fuente Wi-Fi a 720p30; luego se medirán dos fuentes Wi-Fi y se ajustarán resolución, FPS y bitrate. El programa final se mantiene como objetivo 720p30 sujeto a pruebas térmicas y de rendimiento.

## Datos necesarios para habilitar una bodycam

Marca y modelo exactos, manual o ficha técnica, modo Wi-Fi disponible, protocolo de salida (RTSP/SRT/RTMP/ONVIF u otro), resolución/FPS y si permite conexión directa a la red local. Una bodycam que solo funciona dentro de su aplicación propietaria puede no integrarse.
