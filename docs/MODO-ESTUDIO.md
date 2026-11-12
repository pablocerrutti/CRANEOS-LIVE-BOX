# Modo Estudio en laptop

## Objetivo

Ofrecer una consola de realización completa desde la laptop, con paneles de operación y monitoreo. La laptop no reemplaza al motor audiovisual Android: lo controla.

## Distribución propuesta

1. **Barra superior:** conexión al Samsung, perfil de producción, estado de audio, REC y LIVE.
2. **Multiview:** cámaras HDMI, cámara inalámbrica y medios; indicador de señal/conexión.
3. **Monitores:** Preview y Program con identificación muy clara.
4. **Switcher:** selección de fuente, CUT, FADE y transiciones.
5. **Escenas:** guardar/recuperar configuraciones de realización.
6. **Gráficos:** biblioteca de PNG, zócalos y créditos, edición de texto, posición, escala y visibilidad.
7. **Medios:** intros, outros, clips e imágenes con controles de reproducción.
8. **Audio:** medidores, mute y controles disponibles en el motor.
9. **Emisión/Grabación:** iniciar/detener, duración, destino configurado y mensajes de estado.
10. **Panel técnico:** fuentes conectadas, resolución/FPS, estado de red y advertencias.

## Perfiles de visualización

- **Control ligero:** sin video continuo; controles y estados.
- **Estudio económico:** multiview con imágenes proxy a baja resolución y FPS limitado.
- **Monitor seleccionado:** una vista ampliada (Preview o Program) y las demás en miniatura o pausadas.

El usuario podrá cambiar el perfil según la capacidad de la laptop y la red.

## Reglas de operación

- La laptop envía comandos al Samsung; no hace composición final ni codificación.
- Android informa el estado real de cada acción. La web no debe mostrar LIVE o REC como activos hasta recibir confirmación.
- Los comandos de seguridad (detener emisión/grabación, desconexión) deben ser visibles y claros.
- Si se pierde la red, el motor Android debe mantener un comportamiento seguro y mostrar la desconexión en el panel.
- Las credenciales de emisión se guardan en Android y nunca se envían al navegador.

## Orden de implementación

1. Conexión y estado del motor.
2. Controles de fuentes y Preview/Program.
3. CUT/FADE.
4. Monitoreo proxy.
5. Gráficos y medios.
6. Escenas.
7. Audio, REC y LIVE.
8. Ajustes de rendimiento y accesibilidad.
