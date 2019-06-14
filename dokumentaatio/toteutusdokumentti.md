Ohjelmaa käytetään käyttöliittymän kautta. Käyttö liittymässä valitaan ajettava algoritmi, sekä kartta josta reitti haetaan. Käyttäjän valittua polun etsintä algoritmi avautuu uusi ruutu johon generoidaan kartta jossa  on valmiina lyhyin polku sekä alueet joita on käyetty algoritmien aikana. Algoritmit saavat syötteenä kartasta tehdyn 2D solmu taulukon, lähtö sekä loppu pisteen. Algoritmi käyttää apunaan niille luotuja hajautustaulua ja prioriteetti jonoa. 

hajautustaulun haku ja lisäys toimivat suurimmaksi osaksi O(1) taulun koon suurentaminen O(n)
prioriteettu jonon poisto O(1), lisäys O(log n), taulun koon suurentaminen O(n)
Dijkstra: O((v+e) log v) A*: O(|E|+|V|log|V|)

puutteet: ohjelma on vielä hieman kesken ja jump point search jää toteuttamatta. Käyttöliittymää tulee vielä parantaa, jotta käyttäjä saa syötettyä koordinaatit kartasta. Aikatestaus luokka on vielä kesken.

Lähteet
Introduction to algorithms, 2009, Cormen 
https://www.movingai.com/benchmarks/grids.html
http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
