Työni tarkoituksena on verrata A*, JPS (jump point search) ja Dijkstra algoritmeja nopeimman/lyhimmän reitin etsinnässä verkossa 
kahden pisteen välillä. Verkon (kartan) pisteet ovat koordinaatteja kehikossa/ 2-ulotteisessa kartassa.
Syötteenä algoritmi saa verkon/ kartan (.map), alku- ja päätepisteen. Verkko luodaan lukemalla .map tiedosto.
Algoritmille annetaan syöteenä .map tiedosto josta se luo 2-ulotteisen matriisin. Algoritmi luo matriisiin solmuja joilla on atribuutteina (x- ja y-koordinaatti, merkintä (voiko tämän solmun kautta kulkea) sekä boolean arvon joka kertoo onko solmussa käyty). Reitti käydään läpi algoritmin luoman verkon avulla.
Projektin työvaiheisiin kuuluu edellämainittujen algoritmien toteutuksen lisäksi myös verkon toteutus.
Ulostulona käyttäjä saa mm. käytetyn ajan, käytyjen solmujen määrän, visuaalisen esityksen algoritmin toiminnasta sekä nopeimmasta 
reitistä.

Toteutan verkon vierekkäisyys matriisi esityksen avulla, jolloin verkon tilavaativuus on (V^2). Verkon tilavaativuus ei ole tällä hetkellä olennainen tässä projektissa.
Valitsin vierekkäisyys matriisin, koska naapurisolmujen haku on nopeampaa, .map tiedostossa kartta on matriisi esityksen muodossa sekä solmun poisto ja lisäys vie O(1).

Odotetut aikavaatimukset:
Dijkstra: O((v+e) log v)
A*: O(|E|+|V|log|V|)

Introduction to algorithms, 2009, Cormen
Kartat haetaan osoitteesta: https://www.movingai.com/benchmarks/grids.html
