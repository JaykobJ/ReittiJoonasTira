Työni tarkoituksena on verrata A*, JPS (jump point search) ja Dijkstra algoritmeja nopeimman/lyhimmän reitin etsinnässä verkossa 
kahden pisteen välillä. Verkon/ kartan pisteet ovat koordinaatteja 2-ulotteisessa kartassa. Valitsin kyseiset algoritmit, koska ne ovat yleisimmät nopeimman reitin hakualgoritmeja ja halusin verrata niiden nopeutta ja toimintaa. Olen myös halunnut itse toteuttaa kyseisiä algoritmeja. JPS valitsin ekstra algoritmiksi, koska sen toiminta vaikutti mielenkiintoiselta ja halusin myös toteuttaa sen mikäli aikaa jää. A* algoritmi on optimoitu nopeuteen, jolloin sen etsintä laajuu kärsii hieman. Algoritmi tasoittaa heuristista arvoa mikäli se lasketaan tälle uudestaan. 
Syötteenä ohjelma saa verkon/ kartan (.map tiedoston). Ohjelma muuttaa saadun kartan solmu matriisiksi. Solmuja luodaan vain koordinaateille joiden läpi voidaan kulkea. Liikkuminen on sallittu 8 eri suuntaa (vertikaalien, horisontaalinen ja diagonaalinenn).
Ohjelma luo matriisiin solmuja joilla on atribuutteina (x- ja y-koordinaatti, merkintä (voiko tämän solmun kautta kulkea), paino (etäisyys lähtöpisteeseen), heuristinen arvo (etäisyys päätepisteeseen), F arvo (käyetään A*) sekä solmun edeltäjä solmu). 
Algoritmeille annetaan syötteenä muunneettu kartta/ matriisi, alku sekä päätepiste jonne se etsii lyhyimmän reitin. 
Projektin työvaiheisiin kuuluu edellämainittujen algoritmien toteutuksen lisäksi hajautustaulun sekä prioriteettijonon toteutus.
Ulostulona käyttäjä saa käytetyn ajan, reitin painoarvon, visuaalisen esityksen algoritmin toiminnasta sekä nopeimmasta 
reitistä.


Toteutan verkon matriisi esityksen(kartan) avulla, jolloin verkon tilavaativuus on (V^2). Verkon tilavaativuus ei ole tällä hetkellä olennainen tässä projektissa. Matriisiin luodaan solmuja vain jos kyseisen kohdan läpi voi kulkea kartassakin. Algoritmi käyttää kartassa vain luotuja solmuja ja lisää niiden atribuuteille tietoa mikäli se on tarpeen.

Odotetut aikavaatimukset:
Dijkstra: O(|E|+|V| * log|V)
A*: O(|E|+|V| * log|V|) - A* aikavaativuus riippuu hyvin paljon sen heuristiikasta ja kuinka se on optimoitu, sekä verkosta.
Hajautustaulu: Lisäys ja etsintä Ω(1), koon kasvatus O(n), n = vanhan listan koko
Prioriteettijono: Lisäys ja poisto Ω(log(n)), koonkasvatus O(n), n = vanhanlistan koko/ tämänhetkisen listan koko

Lähteet:
Introduction to algorithms, 2009, Cormen
http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
Kartat haetaan osoitteesta: https://www.movingai.com/benchmarks/grids.html
