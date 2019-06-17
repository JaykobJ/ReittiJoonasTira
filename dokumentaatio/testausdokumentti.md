Dijkstra ja A* algoritmien suorituskykyä on testattu erisuuruisilla syötteillä. Testauksessa on otettu huomioon syötetyn kartan koko solmuina (V^2) sekä algoritmin toiminta aika (lopetus aika (ms) - aloitus aika (ms)). Testissä karttojen solumäärää tuplataan ja algoritmit etsii nopeimman polun 20 kertaa jonka jälkeen tuloksista otetaan keskiarvo (tulosten summa / tulosten määrä). Karttoina on käytetty esteettömiä karttoja eli kaikki solmut ovat potentiaalisia solmuja jossa algoritmit voivat käydä.
Testi on toteutettu projektin omassa testing paketissa olevassa AlgorithmTesting luokassa. Testi voidaan helposti toistaa ajamalla luokka uudestaan.
Tulokset on kirjattu ylös Exceliin jossa niistä on luotu kuvaaja.
Kuvaajia on luotu 3 kappaletta. 1) vertailtu algoritmien suorituskykyä. 2) ja 3) kuvattu algoritmien suorituskykyä kuvaajan avulla.

-- Testit este kartoilla --

Projekti sisältää testiluokat hajautustaululle, minimi.prioriteettijonolle, A* - ja dijkstra algoritmille sekä ohjelmassa käytettävään solmu (Vertex) luokalle. Nämä testit on toteutettu JUnit testin avulla.
Testit voidaan toistaa ajamalla testiluokat esimerkiksi NetBeans:lla.
