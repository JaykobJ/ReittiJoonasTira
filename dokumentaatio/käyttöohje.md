Ohjelman voi käynnistää lataamalla .jar tiedoston tästä github repositorista. Jar tiedosto sijaitsee "*projektin juurihakemisto*/ReittiJoonasTira/build/libs". Ohjelmaa ajettaessa on hyvä huomioida, että ohjelmassa käyettävät kartat tulee olaa samassa hakemistossa missä .jar tiedosto on. Ohjelma hakee täältä käyettävät kartat. Ohjelma lukee vain .map tiedostoja jotka on muodostettu oikein (1 rivillä kartan tyyppi, 2 rivillä kartan korkeus(kirjainten lukumäärä), 3 rivillä kartan leveys(kirjainten lukumäärä), 4 rivillä tieto, että kyseessä on kartta) 5 rivistä eteenpäin on koottu kartta kirjaimista.
Tällä hetkellä ohjelma lukee mm. vesi ja hidaste alueet joko läpikuljettavana tai esteenä.

Miten ohjelma suoritetaan, miten eri toiminnallisuuksia käytetään
Minkä muotoisia syötteitä ohjelma hyväksyy
Missä hakemistossa on jar ja ajamiseen tarvittavat testitiedostot.
