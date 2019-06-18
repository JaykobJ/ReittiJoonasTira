Esteettömällä kartalla testaus:
Pää fokuksena tässä testissä on algoritmin suoritusaika kun kartan koko kasvaa.
Dijkstra ja A* algoritmien suorituskykyä on testattu erisuuruisilla syötteillä. Testauksessa on otettu huomioon syötetyn kartan koko solmuina (V^2) sekä algoritmin toiminta-aika (lopetus aika (ms) - aloitus aika (ms)). Testissä karttojen solumäärää tuplataan ja algoritmit etsii nopeimman polun 20 kertaa jonka jälkeen tuloksista otetaan keskiarvo (tulosten summa / tulosten määrä). Karttoina on käytetty esteettömiä karttoja eli kaikki solmut ovat potentiaalisia solmuja jossa algoritmit voivat käydä. Oletuksena on, että algoritmiet löytävät tässä kartassa saman pituisen reitin. Aloitus pisteeksi on otettu kartan keskipiste ja päätepisteeksi äärimmäisin piste kartan lopusta (pituus-1, pituus-1). 
Koska A* on optimoitu nopeuteen verrataan myös reittien pituutta. Näitä ei ole kuvattu kuvaajissa.
Kuvaajia on luotu 3 kappaletta. 1) vertailtu algoritmien suorituskykyä. 2) ja 3) kuvattu algoritmien suorituskykyä kuvaajan avulla.

Verkosta otettu este kartta:
Pää fokuksena tässä testissä on algoritmien lyhyimmän reitin vertailu, sekä oheis vertailun kohteena algoritmien ajankäyttö.
Algoritmien tehokkuutta on testattu myös kartoilla jossa on esteitä. Pisteet on valittu niin, että matkan etäisyys kasvaa, sekä suoran reitin varrella olisi mahdollisimman paljon esteitä. Kartat on valittu summan mutikassa.
Tässä testissä karttoina on käytetty baldursgate pelin originaaleja karttoja osoitteesta: https://www.movingai.com/benchmarks/bgmaps/index.html. Testin kartat (AR0014SR, AR0071SR, AR0205SR ja AR0300SR) löytyvät ohjelman testikartat kansiosta.

Testit on toteutettu projektin omassa testing paketissa olevassa AlgorithmTesting luokassa. Testi voidaan helposti toistaa ajamalla luokka uudestaan. Itselläni testien ajamiseen menee noin 5.5 minuuttia.
Tulokset on kirjattu ylös Exceliin jossa niistä on luotu kuvaaja. A* algoritmin nopeuden testaamisessa on käytetty myös nanosekuntteja jotta sen nopeus-kuvaajasta saataisiin tarkempaa tietoa.

Projekti sisältää testiluokat hajautustaululle, minimi.prioriteettijonolle, A* - ja dijkstra algoritmille sekä ohjelmassa käytettävään solmu (Vertex) luokalle. Nämä testit on toteutettu JUnit testin avulla.
Testit voidaan toistaa ajamalla testiluokat esimerkiksi NetBeans:lla.
