Ohjelman voi ottaa käyttöön lataamalla .jar tiedoston tästä github repositoriosta. Jar tiedosto sijaitsee "*projektin juurihakemisto*/ReittiJoonasTira/build/libs". Ohjelmaa ajettaessa on hyvä huomioida, että ohjelmassa käytettävät kartat tulee olaa samassa hakemistossa missä .jar tiedosto on. Ohjelma hakee täältä käytettävät kartat. Ohjelma lukee vain .map nimisiä tiedostoja jotka on muodostettu oikein (1 rivillä kartan tyyppi, 2 rivillä kartan korkeus(kirjainten lukumäärä), 3 rivillä kartan leveys(kirjainten lukumäärä), 4 rivillä tieto, että kyseessä on kartta) 5 rivistä eteenpäin on koottu kartta kirjaimista. Käyttäjä voi myös luoda itse oman kartan jota käyttää ohjelmassa, mikäli tiedoston sisältö on oikeanlainen. Rivin 2 ja 3 rivi ja sarake määrä tulee täsmätä kartan rivi ja sarake määrään. 

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

Alkunäyttö

Miten ohjelma suoritetaan, miten eri toiminnallisuuksia käytetään
Minkä muotoisia syötteitä ohjelma hyväksyy
Missä hakemistossa on jar ja ajamiseen tarvittavat testitiedostot.
