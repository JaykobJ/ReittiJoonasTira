Ohjelman voi ottaa käyttöön lataamalla .jar tiedoston tästä github repositoriosta. Jar tiedosto sijaitsee "*projektin juurihakemisto*/ReittiJoonasTira/build/libs". Ohjelmaa ajettaessa on hyvä huomioida, että ohjelmassa käytettävät kartat tulee olaa samassa hakemistossa missä .jar tiedosto on. Ohjelma hakee täältä käytettävät kartat. Ohjelma lukee vain .map nimisiä tiedostoja jotka on muodostettu oikein (1 rivillä kartan tyyppi, 2 rivillä kartan korkeus(kirjainten lukumäärä), 3 rivillä kartan leveys(kirjainten lukumäärä), 4 rivillä tieto, että kyseessä on kartta) 5 rivistä eteenpäin on koottu kartta kirjaimista. Käyttäjä voi myös luoda itse oman kartan jota käyttää ohjelmassa, mikäli tiedoston sisältö on oikeanlainen. Rivin 2 ja 3 rivi ja sarake määrä tulee täsmätä kartan rivi ja sarake määrään. Karttoj käyttäjä voi hakea osoitteesta https://www.movingai.com/benchmarks/grids.html tai tämän GitHub repositorin kansiosta "*projektin juurihakemisto*/ReittiJoonasTira/". Testi kartat löytyvät kansiosta "*projektin juurihakemisto*/ReittiJoonasTira/testMaps/".

Käyttöliittymän ruudun kokoa voi muuttaa vetämällä ikkunan sivuista ja näkymissä on vetopalkki sivussa jonka avulla isompaa ruutua voi selailla.

------------------------------------

esimerkki .map tiedoston sisällöstä:

type octile

height 512

width 512

map

....@

.....

..@.@

.@..@

.....

------------------------------------

Tällä hetkellä ohjelma lukee mm. vesi ja hidaste alueet joko läpikuljettavana tai esteenä.


Kun ohjelman ajaa avautuu ensimmäiseksi seuraavanlainen ikkuna

Ylimmällä rivillä ohjeistetaan käyttäjää syöttämään aloitus- ja päätesolmun koordinaatit.

Toisella rivillä on huomautus teksti joka ilmoittaa käyttäjälle, että ensiksi tulee valita kartta vetolaatikosta

Kolmannella rivillä on 4 tekstikenttää joihin syötetään halutut koordinaatit järjestyksessä 1) aloituspisteen x, 2) aloituspisteen y, 3) päätepisteen x, 4) päätepisteen y. Syötteenä tulee olla kokonaisluku ja luvut tulee olla >= 0 tai < sarakkeen ja/tai rivin koko.

Viimeisellä rivillä on kartan valinta vetolaatikko, A* algoritmin aloitus painike ja Dijkstran algoritmin aloitus painike
![](/dokumentaatio/kuvat/k%C3%A4ytt%C3%B6%20kuvat/StartScreen.PNG)

Kartan valinta vetolaatikko avattuna, jossa listattu käytössä olevat kartat
![](/dokumentaatio/kuvat/k%C3%A4ytt%C3%B6%20kuvat/MapCBoxOen.PNG)

Kun kartta on valittu, aloitus- ja päätepisteet syötetty voi käyttäjä painaa reitinhaku algoritmi nappeja. Napin panallus avaa visuaalisen esityksen reitin hausta uuteen ikkunaan.
![](/dokumentaatio/kuvat/k%C3%A4ytt%C3%B6%20kuvat/ReadyToDraw.PNG)

Mikäli aloitus- ja päätepistettä ei ole kirjoitettu oikein (koordinaatti >= 0), koordinaatti on suurempi kuin kartan leveys ja/tai korkeus tai piste on seinä niin ohjelma ilmoittaa tästä käyttäjälle. Ilmoitus on toisella rivillä
![](/dokumentaatio/kuvat/k%C3%A4ytt%C3%B6%20kuvat/Error.PNG)
