## ADB + Telnet Monkey

* Main Class: co.edu.uniandes.miso4208.entry.Main
* Propiedades 
    * Logging: -Djava.util.logging.SimpleFormatter.format="%1$tH:%1$tM:%1$tS %4$-6s %2$s:-- %5$s%6$s%n"
* Parametros
    * -apk_loc:  Ruta donde se encuentra el apk "C:/Development/draw.apk" (requerido)
    * -apk_name: com.simplemobiletools.draw (requerido)
    * -num:      Numero de eventos (Requerido)
    * -evt:      Listado de tipos de eventos (Requerido) nombres de los eventos a usar, separados por comas
        * text
        * tap
        * swipe
        * key
        * sensor
        * rotate
        * net
    * -tt:       telnet token (Requerido si se usan eventos telnet)
    * -s:        semilla 


[https://raw.githubusercontent.com/femago/miso-4208-pa-taller7/master/taller7-adb-monkey/run.png](https://raw.githubusercontent.com/femago/miso-4208-pa-taller7/master/taller7-adb-monkey/run.png)