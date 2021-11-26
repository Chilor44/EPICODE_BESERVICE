# EPICODE_BESERVICE


** ENGLISH **
Rest application simulating a CRM software.
The CRUD class (in the it.epicode package) is provided with a method to populate the database (which is handled with PostgreSQL) with:
-the italian provinces and cities present in the csv files (in the src/main/resources/csv path);
-13 addresses;
-15 customers;
-2 invoice statuses - SALDATA (paid) and NON_SALDATA (not paid);
-40 invoices;
-2 users with their roles (User and Admin, the latter authorized to access more functionalities than the User);

In order to start the application properly, please remember to modify the file application.properties (in the src/main/resources path) inserting the details of the database you're using.
Once started, the application will be accessible on the default port 8080.

The RestController classes included in the package it.epicode.controller will give you the endpoints (which can be used both with a Jason body calls or with a html get form from browser) to access the mainly CRUD commands for every class of the it.epicode.model package.
For the front-end i used Bootstrap 5 and Thymeleaf.

Therefore, the ClienteController class gives the possibility to order the customers by:
-name (ragione sociale);
-annual turnover (fatturato annuale);
-province of the legal address(indirizzo sede legale);

and to search them by:
-annual turnover (fatturato annuale);
-part of the name (ragione sociale);
-date of the last contact with them (data ultimo contatto);
-date in which the customer has been saved on the database;

The FatturaController class allows to search the invoices by:
-client id;
-state of the invoice;
-date and year of the invoice;
-range of the total amount of the invoice;

Unlike the normal User, an Admin user can also add customers and invoices.
At the moment of the signin, the default assigned role is User. For the login as an Adimn, please use the "Admin" both as username and password.

Regarding the security, i used the BCrypt hashing algorythm for encrypting the password, while the encryptionof the email has been made with the "convertToDatabaseColumn" method of the "StringAttributeConverter" class, used in the endpoints mapped as "signup1" and "signup".
In the project there is also a Postman collection with the jason bodies for the main endpoints, some Junit tests of the main classes and endpoints and two error pages you will end up in in case you try to register a user with a username or a password already present in the database.




** ITALIANO **

Programma Rest che simula un software CRM di gestione clienti.
La classe CRUD(nel package it.epicode) è dotata di un metodo che popola il database (in questo caso utilizzando il sistema di gestione PostgreSQL) con:
-le provincie e i comuni forniti dai file csv contenuti all'interno della cartella src/main/resources/csv;
-13 indirizzi;
-15 clienti;
-2 statoFattura (SALDATA e NON_SALDATA);
-40 fatture;
-2 utenti con relativi roles (User e Admin, quest'ultimo autorizzato ad accedere a funzionalità aggiuntive rispetto allo user);
 
Per avviare correttamente il programma è necessario modificare il file application.properties (nel percorso src/main/resources), inserendo i dati del database che si intende utilizzare.
Una volta partita, la pagina index sarà visitabile dalla porta di default 8080.

Le classi RestController presenti all'interno del package it.epicode.controller forniscono gli endpoint (utilizzabili sia con un JSON body, sia con un get form html da un'interfaccia utente) per accedere alle principali funzionalità CRUD relative ad ogni classe del package it.epicode.model. 
Per la parte Frontend sono stati utilizzati Bootstrap 5 e Thymeleaf.
  
In aggiunta, la classe ClienteController fornisce la possibilità di ordinare i clienti per: 
-ragione sociale; 
-fatturato annuale;  
-provincia dell'indirizzo della sede legale;

ed effettuare una ricerca per:
-fatturato annuale;  
-parte della ragione sociale;  
-data ultimo contatto;
-data in cui il cliente è stato salvato nel db; 

La classe FatturaController invece fornisce la possibilità di cercare le fatture per:  
-id del cliente;
-stato della fattura; 
-data e anno della fattura;
-range di importo;

L'utente Admin dispone inoltre delle funzionalità aggiutive di aggiunta di clienti e fatture.

In fase di registrazione il ruolo che viene assegnato di default è user.
Per effettuare il login come Admin sarà necessario inserire "Admin" sia come username che come password.
Per quanto riguarda la sicurezza, è stato utilizzato l'algoritmo di hashing BCrypt per criptare la password, mentre per l'email la criptatura avviene tramite il metodo convertToDatabaseColumn della classe "StringAttributeConverter", richiamato negli endpoint mappati come "signup1" e "signup".
Nel progetto sono presenti anche una collezione Postman con i jason body per le chiamate degli endpoint principali, alcuni test JUnit inerenti ai metodi ed enpoint principali e due pagine di errore a cui si accede in caso si cerchi di registrare un utente i cui username o password sono già presenti nel database.


