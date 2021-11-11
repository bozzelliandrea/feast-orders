Ordini e stampe:
1. Sistemare formato stampe
2. Menu omaggio (su stampe menu omaggio e 0 totale), sconti (percentuali)
3. Asporto -> da riportare sulle stampe
4. Nota sul singolo prodotto, ad esempio in inserimento di 2 panini deve essere possibile aggiungere la nota sulla voce "1 senza formaggio". La nota deve essere propagata alla stampa
5. prodotti raggrupati per categorie
6. possibilità di ristampare la stampa di una singola postazione, cioè solo cucina, solo bar. Usata nel caso una delle stampe non va a buon fine o si rovina. 
7. possibilità di nascondere o meno una categoria nella creazione degli ordini, ad esempio dal pc del bar non è possibile selezionare categorie e voci di menu che non appartengono al bar, come ad esempio voci di menu della cucina
8. aggiungere la notifica di creazione ordine: chi crea da dispositivo deve ricevere la notifica che l'ordine è stato inviato correttamente

Dispositivi
- Non funziona con tablet, Da capire, Provato con Samsung Note X e Mediacom
--- soluzione Flutter

Gestione offline
Situazione attuale: un router wifi, senza access point, connesso con delle stampanti di rete e al pc dove gira l'applicazione. Le stampanti di rete sono nelle varie zone del baraccone, ad esempio una in bar, una in cucina ecc.
Dispositivi usati:
- 2 pc, uno per cassa e da asporto e uno che fa da cassa in bar
- 2 dispositivi tablet dedicati, uno Samsung X e un Mediacom, capire se si collegano a rete mobile
- 2 stampanti collegate alla rete wifi (senza access point), una in bar e una in cucina
Problemi:
1. il segnale del router wifi si interrompe durante il concerto, per interferenze con microfoni/casse
2. il segnale del router non copre tutta l'area -> possibile soluzione l'utilizzo di ripetitori
3. il router wifi non è connesso ad internet

Possibili soluzioni

Sol 1. tutto offline su rete locale
Possibili problemi da valutare:
- il segnale del router wifi si interrompe durante il concerto, per interferenze con microfoni/casse
--- consentire di poter creare ordini offline dai dispositivi, quindi salvandoli all'interno del dispositivo stesso, per poi essere inviati al server una volta che il dispositivo si riconnette alla rete locale (wifi senza AP)
- il segnale del router non copre tutta l'area
--- possibile soluzione l'utilizzo di ripetitori

Sol 2. tutto su cloud, sia l'app mobile per visualizzazione listino/creazione ordini sia l'app desktop e anche le stampe passano per cloud
Possibili problemi da valutare:
- come far avvenire la comunicazione tra cloud e stampanti? vanno esposte pubblicamente?

Sol 3. soluzione ibrida che utilizza il cloud (internet tramite rete mobile) per la creazione/gestioni ordini e rete locale per la stampa.
Si prevedono 2 applicazioni o la medesima con due funzionalità distinte:
- un app mobile che consente ai dispositivi di vedere il listino e creare gli ordini,
- un app desktop utilizzata dal pc in cassa che tramite notifiche/polling recupera gli ordini effettuati e li manda in stampa, fa da admin e gestisce gli utenti/statistiche/magazzino ecc.
Possibili problemi da valutare:
- l'app desktop deve essere utilizzata da un pc connesso ad internet e che deve comunicare con le stampanti (il wifi delle stampanti non ha AP). E' possibile connettere il pc a due reti diverse?



Bar possibile stampare copia bar e copia cliente -> categorie che si possono nascondere o meno
Fritti e panini stampati entrambi in cucina ma su due stampe diverse

(Fase 2) Idea stato ordine:
si potrebbe associare un codice (a barre o qrcode) in creazione dell'ordine. L'ordine viene stampato, chi prende in carico l'ordine deve "timbrarlo" tramite un lettore qrcode o codice a barre, portando l'ordine allo stato "In lavorazione". Una volta pronto il cibo da consegnare, l'ordine viene timbrato nuovamente portando lo stato in "completato".
Da capire nel caso di un ordine che comporta più sub-ordini, come cucina, bar, come gestire lo stato complessivo dell'ordine: potrebbe corrispondere all'AND degli stati dei sub orders.
