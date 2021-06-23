
# SWE Projekt

 Im folgenden Projekt wird eine JSON-Datei mit Satelliten-Informationen eingelesen und aggregiert. Zum Aggregieren stehen 
 zwei Klassen zur Verfügung. In der Klasse DeutscheTVSender werden alle Satelliten zusammengetragen, die deutschsprachige 
 TV-Sender ausstrahlen. 
Die Klasse TransponderAnzahlSender zählt, wie viele TV- und Radiosender der Satellit mit dem Namen `ABS-2A` verfügt. 
 
 Neben den Klassen zur Aggregation, verfügt das Projekt auch Klassen zur Ausgabe der Aggregation. Die Klasse `OutputGUI` 
 gibt die aggregierten Informationen auf einer grafischen Oberfläche aus.
 Die Klasse `OutputJSON` erstellt ein JSON-File mit den aggregierten Informationen und speichert die Datei unter 
 `resources/output.json`.
 
## Projekt erweitern
 
 Das Projekt kann durch beliebig viele Aggregate erweitert werden. Für jedes neue Aggregat muss lediglich eine neue Klasse erstellt werden, 
 die im package `controller/AggregatStrategyfactory/Aggregate` gespeichert werden muss. 
 Die Klasse muss das Interface `AggregatStrategy` implementieren. 
 Das Interface verfügt über die Methoden `Map<String, List<String>> executeAlgorithm()` und 
 `Map<String, List<String>> getStringMap(Map<Satellite, List<Satellite.Channel>> map)`. 
 
Durch das Implementieren der Methode 
 getStringMap muss jedes Aggregat eine einheitliche Map vom Typ `Map<String, List<String>>` zurückgeben.
Diese StringMap dient als einheitliche Schnittstelle zur Ausgabe. 

Mit Hilfe des Strategy und Factory Designpattern wird das Aggregat verwendet, das in der `config.json` Datei angegeben wird. 

Auch für die Ausgabe werden Strategy und Factory Designpatterns verwendet. Um eine neue Ausgabe hinzuzufügen, muss lediglich
eine Klasse im package 
 `view/outputMethods` erzeugt werden, die das Interface `AusgabeStrategy` implementiert. Die Klasse muss dadurch die Methode
 `public void outputMap(Map<String, List<String>> map);` implementieren, welche die übergebene StringMap
in das gewünschte Ausgabeformat strukturiert. 
 
 ## config.json
 
 Die `config.json` Datei ist folgendermaßen aufgebaut: 
 
 ```
    [
      {
        "satellitesPath" : "resources/satellites.json",
        "aggregat" : "controller.AggregatStrategyFactory.Aggregate.DeutscheTVSender",
        "output" : "view.outputMethods.OutputGUI"
      }
    ]
```

Über die Config-Datei kann die verwendete Aggregation und Ausgabeklasse angegeben werden, sowie der Pfad zur Satelliten-Datei. 
Die Config-Datei darf nicht verschoben oder umbenannt werden. 

Zur Auswahl für das Aggregat stehen bislang:

    - "aggregat" : "controller.AggregatStrategyFactory.Aggregate.DeutscheTVSender"
    - "aggregat" : "controller.AggregatStrategyFactory.Aggregate.TransponderAnzahlSender",

Zur Auswahl für die Ausgabe stehen bislang:

    - "output" : "view.outputMethods.OutputGUI"
    - "output" : "view.outputMethods.OutputJSON"
    
Soll eine neue Satelliten Input JSON mit der selben Struktur eingelesen werden. So muss lediglich der
`"satellitesPath"`mit dem relativen Pfad zur neuen JSON getauscht werden.

## Whitebox Test

Die Whitebox Tests wurden mit der Bibliothek JUnit implementiert.

Zur Abdeckung aller Fälle wurden zwischen den beiden Äquivalenzklassen `Aequivalenzklasse_emptyJSON` und
der `Aequivalenzklasse_fullJSON` unterschieden. Die Äquivalenzklassen lesen jeweils Test Input JSON-Files 
`testSatellites.json` ein, die stellvertretend für alle möglichen Input JSONs stehen.

Dabei deckt die `Aequivalenzklasse_emptyJSON` alle Fälle ab,
bei denen die Satellite Input JSON entweder komplett leer ist, oder keine Attribute enthält,
welche durch die Filter der einzelnen Aggregate rausgefiltert werden, da sie nicht die nötigen 
Eigenschaften besitzen. Ihre `testSatellites.json` ist deshalb eine komplett leere JSON-File.

Die `Aequivalenzklasse_fullJSON` deckt alle Fälle ab,
bei denen die Satellite Input JSON entweder mit Attribute gefüllt ist,
welche durch die Filter der einzelnen Aggregate rausgefiltert werden, da sie die nötigen
Eigenschaften besitzen. Ihre `testSatellites.json` ist deshalb eine JSON-File, die mehrere unterschiedliche
Satelliten und Transponder, sowie unterschiedliche Radio- und Fehrnseherprogramme unterschiedlicher 
Sprachen enthält (deutsch und englisch). Sie steht stellvertretend für alle nichtleeren JSON-Files.

## Ausführung der Whitebox Test

Zur Ausführung der beiden Test-Äquivalenzklassen muss jeweils der `"satellitesPath"` in der config-File
auf den Pfad der `testSatellites.json` geändert werden.

1. Ausführung der `Aequivalenzklasse_emptyJSON`:


    - "satellitesPath" : "src/test/java/org/Aequivalenzklasse_emptyJSON/testResources/testSatellites.json"


2. Ausführung der `Aequivalenzklasse_fullSON`:


    - "satellitesPath" : "src/test/java/org/Aequivalenzklasse_fullJSON/testResources/testSatellites.json"

Nach Änderung des Pfades können die Packages jeweils gesondert ausgeführt werden.

# Stile 

Das Projekt ist in Model-View-Controller aufgeteilt. 
Doppelter Code wurde unteranderem durch die Verwendung der Abstracten-Klasse SuperAggregat vermieden. 
Durch die Designpattern Stategy und Factory ist das Projekt beiebig skalierbar. 

## Contributors

Das Projekt wurde bearbeitet von:

    -Finn Freiheit,         Matr.Nr: 2533282,   Email: inf19044@lehre.dhbw-stuttgart.de
    -Armin Vosoghi Marand,  Matr.Nr: 9747848,   Email: inf19218@lehre.dhbw-stuttgart.de
    -Sarah Kröll,           Matr.Nr: 9875642,   Email: inf19221@lehre.dhbw-stuttgart.de

