Weather Radar

A Java app that fetches and presents data about the weather on the city passed on as argument using an IPMA API.

O que é um Maven Goal?

    Por omissão, uma Maven Build é composta por 3 Cycles: default, clean e site. Estes, por sua vez, são compostos por Phases que, novamente, são compostas por Goals. Cada Phase é uma sequência de Goals e cada Goal é responsável por uma tarefa específica.

Quais os principais Maven Goals e a sua sequência de invocação?

    Maven Phases:
        >validate;
        >compile;
        >test-compile;
        >test;
        >package;
        >integration-test;
        >install;
        >deploy;

How to run:
    $ mvn exec:java -Dexec.mainClass="ua.weather.app.App" -Dexec.args="1010500" -Dlog4j.configurationFile="src/main/resources/log4j.xml"