# MENTCARE #

Laura Canaia VR489361    
Stefano Modenese VR491230

## Informazioni generali ##

Il progetto è stato sviluppato utilizzando come IDE Intellij IDEA Community Edition, come supporto per la gestione delle 
dipendenze Gradle versione 2.3.2 utilizzando come linguaggio di programmazione Java versione 14 per il backend, html e CSS
per quanto riguarda il frontend.

****

## Requisiti ##
Informazioni generali sul progetto -> si richiede di sviluppare un componente per il sistema
mentcare con diversi tipi di funzionalità. \
Il componente deve rappresentare 7 scenari

****

## Scenari ##

Gli scenari sono stati modellati partendo dall'idea di implementare un componente per l'accesso utente al
sistema in modo che sia in grado di prenotare visite, richiedere farmaci, etc ...

### 1. Login ###
__Assunzioni iniziali :__ L'utente si è appena collegato al sito e si interfaccia con la pagina di Login.       
Se l'utente si fosse precedentemente connesso, ora deve ri-loggarsi in modo da poter accedere al sistema.       
__Funzionamento standard :__ La schermata si presenta come un semplice form nel quale viene richiesto all'utente
di loggarsi inserendo il proprio nome utente, password e cliccare il pulsante di login per poter effettuare l'accesso.
Se l'utente sa di non essere registrato, è presente anche un pulsante per permettere di registrarsi.     
__Possibili funzionamenti errati :__ se l'utente tenta di loggarsi senza prima essersi registrato, il sistema 
mostra un messaggio di errore all'utente chiedendo di reinserire le credenziali oppure di registrarsi al sistema.  
__Cosa succede in caso l'esecuzione vada a buon fine :__ l'utente accede senza problemi nel sistema e passa ad un'altra pagina


### 2. Registrazione ###
__Assunzioni iniziali :__ L'utente non possiede un account e accede alla schermata di registrazione tramite il 
pulsante presente sulla schermata di login.   
__Funzionamento standard :__ L'utente si registra al sistema tramite la compilazione del form inserendo : nome, cognome,
codice fiscale, password. Dopo aver inserito tutti i parametri l'utente deve cliccare il pulsante "registrati".   
__Possibili funzionamenti errati :__ Ci sono diversi possibili comportamenti inattesi che possono portare il sistema in
errore :    
a) Se i campi non sono stati tutti compilati, l'utente riceve un messaggio di errore dedicato;    
b) Se il codice fiscale non è valido, nella schermata compare un messaggio di errore dedicato;     
c) Se la password non rispetta le indicazioni date, viene restituito un messaggio di errore dedicato.   
__Cosa succede in caso l'esecuzione vada a buon fine :__ l'utente viene registrato correttamente e viene reindirizzato
alla schermata di login dove potrà accedere al sistema inserendo le credenziali appena create.

### 3. Home Page ###
__Assunzioni iniziali :__ L'utente ha effettuato l'accesso al sistema.    
__Fuznionamento standard :__ All'utente si presenta una pagina con il proprio nome, con a fianco ad esso 2 pulsanti : il 
pulsante di logout e quello di prenotazione di una nuova visita.   
Sotto di esso vi è una tabella (con anche 0 righe potenzialmente) con tutte le visite che sono in programma e, per ogni 
visita nella tabella, c'è la possibilità di modificarla o di cancellarla tramite un pulsante a fianco.    
__Possibili funzionamenti errati :__ essendo una pagina "di passaggio" per altri scenari, l'utente non può direttamente
causare dei funzionamenti inaspettati.    
__Cosa succede in caso l'esecuzione vada a buon fine :__ L'utente riesce ad accedere alla sezione collegata al pulsante
o effettua il logout

### 4. Prenotazione visita ###
__Assunzioni iniziali :__ L'utente ha effettuato l'accesso e proviene dalla pagina della home page. Inoltre supponiamo
che tutti gli orari di visita e tutti i giorni sia possibile effettuare delle visite.   
__Funzionamento standard :__ Per la prenotazione della visita, l'utente deve scegliere un giorno e un orario da una
selezione preposta e successivamente mettere una motivazione (breve) del motivo per il quale si intende prenotare una
visita.
Dopo aver compilato tutti i campi,l'utente deve premere il pulsante "prenota" e viene reinderizzato alla Home Page.   
__Possibili funzionamenti errati :__ Se il form è vuoto viene segnalato all'utente che non può effettuare la prenotazione. 
Se, invece, supera i caratteri disponibili, l'inserimento viene bloccato e segnalato.    
__Altre attività :__ Se l'utente clicca sul pulsante della Home Page senza aver prenotato la visita, tutto quello che 
era stato selezionato o modificato viene perso.    
__Cosa succede in caso l'esecuzione vada a buon fine :__ La prenotazione viene salvata, l'utente torna alla Home Page
e viene visualizzata la visita appena effettuata nella tabella della schermata principale.

### 5. Modifica Visita ###
__Assunzioni iniziali :__ L'utente ha effettuato l'accesso e nel database collegato all'utente è presente almeno una 
visita.    
__Funzionamento standard :__ L'utente può modificare il giorno, l'ora e la motivazione della visita, usando tutti i criteri
illustrati nella sezione 
[Prenotazione visita](https://github.com/LauraCanaia/Elaborato_Ing_Sw?tab=readme-ov-file#4-prenotazione-visita).   
__Possibili funzionamenti errati :__ Sono i medesimi esplicati nella sezione 
[Prenotazione visita](https://github.com/LauraCanaia/Elaborato_Ing_Sw?tab=readme-ov-file#4-prenotazione-visita)
come anche per la sezione "Altre attività".          
__Cosa succede in caso l'esecuzione vada a buon fine :__ Si ritorna alla Home Page e la visita selezionata in 
precedenza è stata aggiornata.   

### 6. Cancellazione visita ###
__Assunzioni iniziali :__ L'utente ha effettuato l'accesso e nel database collegato all'utente è presente almeno una
visita.   
__Funzionamento standard :__ Cliccando il pulsante "Elimina" l'utente elimina la visita dalla tabella e quindi anche 
dal database.    
__Possibili funzionamenti errati :__ Non sono presenti funzionamenti errrati che possono essere compiuti dall'utente in
quanto per l'eliminazione non vi è alcuna interazione con l'utente.    
__Cosa succede in caso l'esecuzione vada a buon fine :__ Nella tabella delle visite non compare più la visita 
selezionata in precedenza.

### 7. Logout ###
__Assunzioni iniziali :__ L'utente ha effettuato l'accesso al sistema.    
__Funzionamento standard :__ Cliccando il pulsante logout l'utente esce dal sistema.
__Possibili funzionamenti errati :__ Non sono presenti funzionamenti errrati che possono essere compiuti dall'utente in
quanto per l'eliminazione non vi è alcuna interazione con l'utente.    
__Cosa succede in caso l'esecuzione vada a buon fine :__ Dopo aver cliccato il pulsante per il logout, l'utente esce
dal sistema e torna alla schermata di Login.

****

## Testing ##