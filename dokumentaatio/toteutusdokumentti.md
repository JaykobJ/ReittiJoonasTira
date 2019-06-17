Ohjelman rakenne on seuraava:
Algoritmit:
  - A* ja Dijkstra
  - Algoritmit saavat syötteenä solmu matriisin, alku- ja päätepisteen. Nämä käyvät kartan läpi ja muuttavat solmujen tietoja jotta graafisen osuuden piirtävä luokka osaa värittää oikeat solmut/ koordinaatit
   - Algoritmi luokat sisältävät ominaisuudet niiden suorittamisesta

Tietorakenteet:
  - Hajautustaulu ja minimi-prioriteettijono
  - Näitä tietorakentneita käytetään hyödyksi algoritmi luokissa.
  
Solmu luokka:
  - Luokka pitää sisällään koordinaattien tietoja, sekä atribuutteja joita hyödynnetään algoritmeissa (painoarvo, f arvo, heuristinen arvo ja solmun vanhempi). Luokkassa on myös mm. laskentaoperaatioita joita hyödynnetään arvojen muuttamisessa.
  
Graafinen esitys:
  - Pääluokka joka avaa graafisen käyttöliittymän sekä paneeli luokka joka luo algoritmin polusta taulukkopaneelin ja värjää vaaditut solmut

Testi luokka:
  - Luokkaa hyödynnetään lähinnä testien uusimisessa, sekä testituloksissa

Ohjelmaa käytetään käyttöliittymän kautta. Käyttöliittymässä valitaan ajettava algoritmi, kartta josta reitti haetaan sekä lähtö, että päätepiste. Käyttäjän valittua edellämainitut ominaisuudet ja painettua valittua algoritmi painiketta polunetsintäalgoritmi käynnistyy. Algoritmi muuttaa solmujen tietoja ja visuaalisen ruudun generoiva luokka hyödyntää näitä tietoja tulostaessa valmiin reitin uuteen ikkunaan. Algoritmi luokka pitää sisällään myös tiedon onko päätepiste saavutettu sekä tämän etsimiseen käytetyn ajan. Algoritmit saavat syötteenä kartasta tehdyn 2D solmu matriisin, lähtö- sekä päätepisteen. Algoritmi käyttää apunaan niille luotuja hajautustaulua ja prioriteettijonoa. 

hajautustaulun haku ja lisäys toimivat suurimmaksi osaksi O(1) taulun koon suurentaminen O(n)
prioriteettu jonon poisto ja lisäys O(log n), taulun koon suurentaminen O(n)
Dijkstra: O(|E|+|V|log|V|) 
A*: O(|E|+|V|log(h*(V))


puutteet: 
- Jump Point Search jää ajan puutteen takia toteuttamatta
- Käyttöliittymä on hyvin yksinkertainen. Jatkokehityksessä käyttöliittymää voisi parantaa

parannusehdotukset:
- Käyttäjälle voisi antaa mahdolliisuuden vaihtaa A* algoritmin optimointia
- Jump Point Searchin lisäsys projektiin

Lähteet
Introduction to algorithms, 2009, Cormen 
https://www.movingai.com/benchmarks/grids.html
http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
