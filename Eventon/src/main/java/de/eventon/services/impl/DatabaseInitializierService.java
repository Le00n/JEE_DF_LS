package de.eventon.services.impl;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import de.eventon.core.Address;
import de.eventon.core.BankAccount;
import de.eventon.core.Event;
import de.eventon.core.User;
import de.eventon.services.interfaces.IsDatabaseInitializer;

@RequestScoped
public class DatabaseInitializierService implements IsDatabaseInitializer {
	@Inject
	EventService eventService;
	@Inject
	UserService userService;

	public void init(){
		User user = new User("leonstapper@gmx.de", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", "Leon", "Stapper",
  				new Address("Buchdahlstraße", "6", "48429", "Rheine"),
  				new BankAccount("Leon Stapper", "DE83403500050000123456", "WELADED1RHN"), false);
 		userService.addUser(user);
		
		User manager = new User("david.feldhoff@web.de", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", "David", "Feldhoff",
  				new Address("Moorstraße", "88a", "48432", "Rheine"),
  				new BankAccount("David Feldhoff", "DE83403500050000123457", "WELADED1RHN"), true);
  		userService.addUser(manager);
		
 		Event e = new Event();
 		e.setName("OPEN R Festival 2017");
 		e.setDescription("3 Tage volles Festival-Programm!\n\nFreitag den, 4.8.17 Premiere zum ersten mal in der Geschichte des OPEN R Festivals veranstaltet die Uelzener Ferienwelt ein großartiges Programm unter dem Motto \"House & Club Music\". Das Festival präsentiert die neuen Stars am elektronischen Pophimmel Robin Schulz, Feder, Jonas Blue, Hugel und Deepend. Das Programm startet um 16 Uhr und um 15 Uhr werden die Tore des neunten Festivals geöffnet!\n\nSamstag den, 5.8.17 Uelzen OPEN R Festival \"Neue Töne\". Zum zweiten Mal steht der Samstag unter dem Motto \"Neue Töne\". Das Programm verspricht einen großartigen Sommertag in Uelzen mit den POP-Größen Silbermond, Rea Garvey, Gregoy Meyle und Max Giesinger sowie weiteren Acts. Das Programm startet um 15 Uhr und um 14 Uhr ist Einlass.\n\nSonntag den, 6.8.17 Uelzen OPEN R Festival \"Rockgeschichte\" Der Sonntag besticht durch Nachhaltigkeit und garantiert geschichtsträchtigen Rock vom Feinsten mit Roger Hodgson – formerly of Supertramp mit Band sowie Bob Geldof mit Band.\n\nSie sind Musikgeschichte, sie gehören zu den erfolgreichsten Rock-Bands weltweit. Roger Hodgson, der legendäre Gründer von Supertramp. und Bob Geldof, der irische Rocksänger ist u.a. als Sänger der Boomtown Rats bekannt sowie als der Live Aid Organisator.\n\nMehr Rockgeschichte auf einer OPEN R Bühne geht nicht. Das Programm startet um 15 Uhr und um 14 Uhr ist Einlass.");
 		e.setPriceTicketsNormal(43.33);
 		e.setPriceTicketsPremium(53.85);
 		e.setDatetime(LocalDateTime.of(2017, 8, 4, 15, 0));
 		e.setAmountTicketsNormal(15000);
 		e.setAmountTicketsPremium(1000);
 		e.setAddress(new Address("Almased Arena Uelzen", "Albrecht-Thaer-Str.", "1", "29525", "Uelzen"));
 		e.setManager(manager);
 		e.setPublished(true);
 		e.setFilename("sonntag-open-r-festival-mit-roger-hodgson-und-bob-geldof-0135.jpg");
 		eventService.createEvent(e);
 
 		Event e1 = new Event();
 		e1.setName("Paul Panzer - Glücksritter... Vom Pech verfolgt!");
 		e1.setDescription("Was ist Glück? Wo kann man es finden und wo nicht? In seinem neuem Programm sucht Paul Panzer das Glück, an seltsamen Orten, in merkwürdigen Begebenheiten und nicht zuletzt bei sich selbst.\r\n\r\nVon all` seinen philosophischen, skurrilen und aberwitzigen Abenteuern tritt der Ausnahmekomiker nun seine fantastischste Reise an.\r\n\r\nEine geniale Live – Show vom Sinn und Zweck des Seins, von innerer Mitte und der Frage, ob die Abwesenheit von Pech schon Glück ist ?!\r\n\r\nPaul Panzer in Höchstform!");
 		e1.setPriceTicketsNormal(36.6);
 		e1.setPriceTicketsPremium(0.00);
 		e1.setDatetime(LocalDateTime.of(2017, 10, 22, 18, 0));
 		e1.setAmountTicketsNormal(1200);
 		e1.setAmountTicketsPremium(0);
 		e1.setAddress(new Address("RuhrCongress Bochum", "Stadionring", "20", "44791", "Bochum"));
 		e1.setManager(manager);
 		e1.setPublished(true);
 		e1.setFilename("paul-panzer-17-ft.jpg");
 		eventService.createEvent(e1);
 
 		Event e2 = new Event();
 		e2.setName("Disneys - Der König der Löwen");
 		e2.setDescription("Kaufen Sie Disneys \"Der König der Löwen\"-Tickets und erleben Sie in Deutschland die wunderschöne und farbenprächtige Welt Afrikas. Mit einer aufregenden Mischung aus Popmusik von Sir Elton John und original afrikanischen Rhythmen garantiert das erfolgreiche Musical Gänsehaut-Feeling pur. Die fabelhaften Masken und grandiosen Kostüme krönen die spannende Bühnen-Inszenierung und machen Disneys \"Der König der Löwen\" zu einem einzigartigen Erlebnis.\r\n\r\nErleben Sie die mitreißende und gefühlvolle Geschichte des kleinen Löwenkönigs Simba auf seinem Weg des Erwachsenwerdens. Zwischen Glück und Unglück, Freude und Angst lernt er, bei seinen Abenteuern Verantwortung zu übernehmen und Liebe für andere zu empfinden. So macht sich Simba zwangsläufig auf die Suche nach seinem Platz im Leben.\r\n\r\nRegisseurin Julie Taymor ist es zu verdanken, dass aus Disneys erfolgreichem Zeichentrick-Meisterwerk ein fulminantes und legendäres Bühnenstück geworden ist. Auf Grund ihrer visionären Verwendung des Schatten- und Maskenspiels und der magischen Verbindung von Mensch und Tier konnte Disneys \"Der König der Löwen\" bereits zahlreiche, bedeutende Preise holen.\r\n\r\nAuch Sir Elton John hat mit seinen wunderschönen Melodien und Hits wie \"Kann es wirklich Liebe sein\" oder \"Hakuna Matata\" seinen Teil zum Erfolg des Musicals beigetragen. Ergänzt durch die pulsierenden Rhythmen des Südafrikaners Lebo M klingt die Musik von Disneys \"Der König der Löwen\" einzigartig und erhält auch lange nach der Show einen Hauch von Afrika.\r\n\r\nMit Karten für Disneys \"Der König der Löwen\" erhalten Sie Eintritt zur einzigartigen Spielstätte im Theater im Hafen Hamburg. Die Reise in die Welt Afrikas beginnt hier bereits mit der Anfahrt auf kostenlosen Shuttle-Schiffen. Von den Hamburger Landungsbrücken führt der Weg zum Theater-eigenen Anleger im Hamburger Hafen, wo Sie eine der außergewöhnlichsten und schönsten Theater-Locations Europas erwartet.");
 		e2.setPriceTicketsNormal(98.9);
 		e2.setPriceTicketsPremium(169.9);
 		e2.setDatetime(LocalDateTime.of(2017, 4, 30, 14, 0));
 		e2.setAmountTicketsNormal(5000);
 		e2.setAmountTicketsPremium(2000);
 		e2.setAddress(new Address("Stage Theater im Hafen Hamburg", "Norderelbstrasse", "6", "20457", "Hamburg"));
 		e2.setManager(manager);
 		e2.setPublished(true);
 		e2.setFilename("koenig-der-loewen-logo-564x421.jpg");
 		eventService.createEvent(e2);
 
 		Event e3 = new Event();
 		e3.setName("Kraftklub Konzert");
 		e3.setPriceTicketsNormal(40.33);
 		e3.setPriceTicketsPremium(60.55);
 		e3.setDescription("Es war eine der ganz großen Erfolgsgeschichten der letzten Jahre: Als das Kraftklub-Debüt \"Mit K\" 2012 von null auf eins in die Charts einstieg, war der vorläufige Höhepunkt einer sehr jungen und besonderen Karriere erreicht. Im Anschluss füllte die Band immer größere Hallen. Schließlich wurde \"Mit K\" mit Platin ausgezeichnet. Kein Wunder: Mit ihrem einmaligen Stilmix aus zackigen Indie-Beats, Up-Tempo-Riffs und witzig-nachdenklichen Texten, in denen sich eine ganze Generation wiederfindet, sprechen Kraftklub genre- und generationspbergreifend Hörer an.");
 		e3.setDatetime(LocalDateTime.of(2017, 6, 15, 16, 0));
 		e3.setAmountTicketsNormal(5);
 		e3.setAmountTicketsPremium(10);
 		e3.setAddress(new Address("Stadthalle", "Teststraße", "2", "48429", "Rheine"));
 		e3.setManager(manager);
 		e3.setPublished(true);
 		e3.setFilename("kraftklub_in_schwarz_neues_album310522074562720760.jpg");
 		eventService.createEvent(e3);
 
 		Event e4 = new Event();
 		e4.setName("Disneys - Aladdin");
 		e4.setDescription("Am Broadway wird Disneys Aladdin mit seiner spektakulären und opulenten Inszenierung und der Musik von Tony Award- und Oscar-Gewinner Alan Menken seit Monaten von der Presse gefeiert.\r\n\r\nDas Musical erzählt die zeitlose Geschichte von Aladdin, Dschinni und den drei Wünschen, die seit Generationen über alle Grenzen hinweg geliebt wird und den Zuschauer in die magische Welt des Orients entführt.\r\n\r\nDie auf dem weltbekannten und oscarprämierten Disneyfilm basierende Show wird nun ihre Europa- Premiere im Herbst 2015 in Hamburg feiern.");
 		e4.setPriceTicketsNormal(169.90);
 		e4.setPriceTicketsPremium(75.90);
 		e4.setDatetime(LocalDateTime.of(2017, 4, 29, 20, 0));
 		e4.setAmountTicketsNormal(2300);
 		e4.setAmountTicketsPremium(1200);
 		e4.setAddress(new Address("Stage Theater Neue Flora Hamburg", "Stresemannstraße", "163", "22769", "Hamburg"));
 		e4.setManager(manager);
 		e4.setFilename("disney-aladdin-on-broadway-musical-logo.jpg");
 		eventService.createEvent(e4);
 
 		Event e5 = new Event();
 		e5.setName("Rock am Ring 2017");
 		e5.setDescription("Mit 45 Neuverpflichtungen biegen Rock am Ring und Rock im Park in die Zielgerade ein. Angeführt von Britpop-Legende Liam Gallagher und den Kanadischen Punk-Heroen Sum 41, sind Acts wie die Donots, Simple Plan, Jake Bugg, Crystal Fighters, Rival Sons, Gojira, Dat Adam, Machine Gun Kelly, Clutch, Nimo, Skindred, Bonaparte, The Living End, Schmutzki, Schnipo Schranke oder Egotronic neu bestätigt.\r\nAuch das elektronische Line-Up für die 4. Bühne, das Club Tent, steht fest, erneut präsentiert von „Mukke Berlin“.\r\nNeben dem Headliner Triumvirat Rammstein, Die Toten Hosen und System Of A Down hatten zuvor schon Bands und Künstler wie die Beatsteaks, Prophets Of Rage, Broilers, Macklemore & Ryan Lewis, Marteria, Kraftklub, Beginner, Bastille, Five Finger Death Punch, AnnenMayKantereit, Rag’n‘Bone Man, Alter Bridge, 187 Strassenbande, Wirtz, Bonez MC & RAF Camora, In Flames, Genetikk oder Airbourne ihre Teilnahme angekündigt.\r\nÜber 140.000 Fans haben sich bereits ihr Ticket für die Zwillingsevents vom 2. bis 4. Juni am Nürburgring und in Nürnberg gesichert. Damit stehen für Rock am Ring als auch Rock im Park jeweils noch knapp 10.000 Tickets zur Verfügung. Mit einem frühzeitigen Ausverkauf beider Festivals ist zu rechnen.");
 		e5.setPriceTicketsNormal(187.5);
 		e5.setPriceTicketsPremium(214.5);
 		e5.setDatetime(LocalDateTime.of(2017, 6, 2, 13, 0));
 		e5.setAmountTicketsNormal(80000);
 		e5.setAmountTicketsPremium(20000);
 		e5.setAddress(new Address("Nürburgring", "Otto-Flinn-Straße", "1", "53520", "Nürburg"));
 		e5.setManager(manager);
 		e5.setFilename("rar-fallback.jpg");
 		eventService.createEvent(e5);
 
 		Event e6 = new Event();
 		e6.setName("Deichbrand Festival");
 		e6.setDescription("Das Deichbrand Festival findet jährlich an der Nordsee statt. 2016 feierten mehr als 50.000 Freaks and Folks, Fans and Friends rund 120 Bands, DJ’s, Live-Acts und Slammer auf 6 Bühnen in 4 Tagen und Nächten. Nach der erfolgreichen 12. Auflage ist der Vorverkauf für das Deichbrand Festival 13.0 vom 20. – 23. Juli 2017 bereits gestartet. ");
 		e6.setPriceTicketsNormal(150.00);
 		e6.setPriceTicketsPremium(0.00);
 		e6.setDatetime(LocalDateTime.of(2017, 7, 17, 15, 30));
 		e6.setAmountTicketsNormal(50000);
 		e6.setAmountTicketsPremium(0);
 		e6.setAddress(new Address("Seeflughafen Cuxhaven/Nordholz", "Walter-Carstens-Str.", "1", "27637", "Nordholz/Cuxhaven"));
 		e6.setManager(manager);
 		e6.setPublished(true);
 		e6.setFilename("deichbrand-festival-okt-2012-tickets.jpg");
 		eventService.createEvent(e6);
	}
}
