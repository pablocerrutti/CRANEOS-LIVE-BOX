# Detección automática de cámaras USB UVC

## Objetivo
Cuando se conecta una webcam USB o una capturadora HDMI USB compatible con UVC al Samsung Galaxy A16 5G, la app debe detectarla sin reiniciar el teléfono, pedir permiso y ofrecerla como fuente HDMI 1 en la consola web.

## Implementación inicial incluida
La carpeta `android/` contiene un proyecto Android nativo inicial:
- `UsbCameraMonitor.kt` escucha conexión y desconexión USB.
- Inspecciona las interfaces USB y reconoce las interfaces USB Video Class (clase 14, subclases VideoControl 1 y VideoStreaming 2).
- Solicita el permiso USB de Android para cada dispositivo detectado.
- `MainActivity.kt` muestra nombre, VID/PID, cantidad de interfaces UVC y estado del permiso.

## Límite importante
Esta primera etapa detecta el dispositivo y obtiene autorización; **todavía no captura ni muestra fotogramas de video**. Para eso falta integrar y probar un backend de captura UVC (por ejemplo, basado en libusb/libuvc o una biblioteca compatible), enumerar formatos/resoluciones y entregar los frames al motor de composición. Tampoco está conectado aún al servidor/API local que consume la consola web.

La presencia de una interfaz UVC no garantiza que todas las capturadoras funcionen correctamente: hay que probar el modelo concreto, el hub OTG, alimentación y compatibilidad del firmware Android del Samsung.

## Contrato previsto para la API local
Cuando se implemente el servicio Android, `GET /api/status` deberá informar la fuente USB sin exponer datos sensibles. Ejemplo de estructura:

```json
{
  "ok": true,
  "sources": [
    { "id": "hdmi1", "connected": true, "signal": false }
  ],
  "usbCameras": [
    {
      "id": "usb-1",
      "name": "USB Video",
      "vendorId": 1234,
      "productId": 5678,
      "permissionGranted": true,
      "videoReady": false
    }
  ]
}
```

La consola debe diferenciar claramente:
1. Dispositivo USB detectado.
2. Permiso concedido.
3. Señal de video recibida.
4. Fuente disponible para Preview/Program.

No marcar una cámara como lista para emisión hasta recibir fotogramas válidos.

## Cómo probar la detección
1. Instalar la app Android de diagnóstico en el Samsung.
2. Conectar la capturadora al hub OTG y al teléfono.
3. Aceptar el permiso USB que muestre Android.
4. Comprobar que aparece el dispositivo, VID/PID y estado autorizado.
5. Desconectar y volver a conectar para comprobar la detección en caliente.

## Construcción
Abrir la carpeta `android/` con Android Studio (JDK 17 y Android SDK 35). Este repositorio aún no incluye Gradle Wrapper ni un APK compilado. La compilación y la prueba en el Samsung quedan pendientes.
