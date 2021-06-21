
# SWE Projekt
 im folgenden Projekt wird eine JSON-Datei mit Satelliten-Informationen eingelesen und aggregiert. Zum Aggregieren stehen 
 zwei Klassen zur Verfügung. In der Klasse DeutscheTVSender werden alle Satelliten zusammengetragen, die deutschsprachige 
 TV-Sender ausstrahlen. 
Die Klasse TransponderAnzahlSender zählt, wie viele TV- und Radiosender der Satellit mit dem Namen `ABS-2A` verfügt. 
 
 Neben den Klassen zur Aggregation, verfügt das Projekt auch Klassen zur Ausgabe der Aggregation. Die Klasse `OutputGUI` 
 gibt die Aggregierten Satelliten auf einer grafischen Oberfläche aus.
 Die Klasse `OutputJSON` erstellt ein JSON-File mit den aggregierten Satelliten und speichert die Datei unter 
 `resources/sortedSatellites.json`.
 
 # Projekt erweitern
 
 Das Projekt kann durch beliebig viele Aggregate erweitert werden. Für jedes neue Aggregat muss eine Klasse erstellt werden. 
 Die Klasse wird im package `controller/AggregatStrategyfactory/Aggregate` gespeichert. 
 Die Klasse muss das Interface `AggregatStrategy` implementieren. 
 Das Interface verfügt über die Methoden `Map<String, List<String>> executeAlgorithm()` und 
 `Map<String, List<String>> getStringMap(Map<Satellite, List<Satellite.Channel>> map)`. 
 Mithilfe des Strategy und Factory Designpatterns wird das Aggregat verwendet, das in der `config.json` Datei angegeben wird. 
 
 Die Designpatterns werden auch für die Ausgabe verwendet. Um eine neue Ausgabe hinzuzufügen, muss eine Klasse im package 
 `view/outputMethods` erzeugt werden, die das Interface `AusgabeStrategy` implementiert. Die Klasse muss dadurch die Methode
 `public void outputMap(Map<String, List<String>> map);` implementieren. 
 
 # config.json
 
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

# Stile 

Das Projekt ist in Model-View-Controller aufgeteilt. 
Doppelter Code wurde unteranderem durch die Verwendung der Abstracten-Klasse SuperAggregat vermieden. 
Durch die Designpattern Stategy und Factory ist das Projekt beiebig skalierbar. 