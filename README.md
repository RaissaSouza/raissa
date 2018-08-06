# raissa
Test UOL Host<br>
#Used Tech:<br>
-> Spring boot framework<br>
-> H2 database<br>
-> Bootstrap<br>

#How it works:<br>
<p>When you launch the system it will open an index page where you will find my identification and two buttons, one is to setup a new player 
and the other one is to list all players in the system.
<p>Clicking in "Cadastrar Jogador" will open a page to fill up a form with the players information. It is mandatory to fill name and email for all new players.</p>
It will be necessary to select a group to be part of, the groups are Vingadores and Liga da Justica. Be aware that Vingadores is always checked.</p>
<p>By clicking in "Salvar" the new player will be added to the database and a "Jogador salvo com sucesso" message will be displayed. The page will be reloaded and
you can keep adding players until you are done or until you get the message "Não há codinomes dísponiveis para esse grupo" for both groups.</p>
<p>Anytime that you want you can click in "Listar Jogadores" to see a list of all players in the sytem. By clicking in name, email, codinome and group the 
system sort the list for you, so you can have a better visualization of the information and find out what you need faster.</p>

#How to lauch the system:<br>
-> Clone or download this repository<br>
-> Goes to the root folder "raissa"<br>
-> Open cmd<br>
-> Run: "mvn clean install" to download all dependencies<br>
-> Run: "mvn spring-boot:run" to lauch the system<br>
-> Open a browser <br>
-> Type: localhost:8080/<br>

Enjoy it!
 

