package fr.hb.ewan.plages.initialisation;

import java.text.Normalizer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import fr.hb.ewan.plages.business.Client;
import fr.hb.ewan.plages.business.Concessionnaire;
import fr.hb.ewan.plages.business.File;
import fr.hb.ewan.plages.business.LienDeParente;
import fr.hb.ewan.plages.business.Parasol;
import fr.hb.ewan.plages.business.Pays;
import fr.hb.ewan.plages.dao.ClientDao;
import fr.hb.ewan.plages.dao.ConcessionnaireDao;
import fr.hb.ewan.plages.dao.FileDao;
import fr.hb.ewan.plages.dao.LienDeParenteDao;
import fr.hb.ewan.plages.dao.ParasolDao;
import fr.hb.ewan.plages.dao.PaysDao;
import lombok.AllArgsConstructor;

// Demande a Spring d'instancier cet objet et de placer cette instance dans son conteneur IOC
@Component
@AllArgsConstructor
public class AjoutDonneesInitiales implements CommandLineRunner {

	// on déclare les dépendances au sens Spring de la classe d'ajout
	// Spring va instantier ces objets à notre place
	private FileDao fileDao;
	private ParasolDao parasolDao;
	private PaysDao paysDao;
	private LienDeParenteDao lienDeParenteDao;
	private ClientDao clientDao;
	private ConcessionnaireDao concessionnaireDao;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ajout données");
		ajouterFiles();
		ajouterParasols();
		ajouterPays();
		ajouterLiensDeParente();
		ajouterClients(30);
		ajouterConcessionnaire();
	}

	public void ajouterFiles() {
		double prixInitial = 10;

		if (fileDao.count() == 0) {
			for (Byte i = 1; i <= 8; i++) {
				File file = new File();
				file.setNumero(i);
				file.setPrixJournalier(prixInitial - i);
				fileDao.save(file);
			}
		}
	}

	public void ajouterParasols() {
		if (parasolDao.count() == 0) {
			for (File file : fileDao.findAll()) {
				for (byte p = 1; p <= 36; p++) {
					Parasol parasol = new Parasol();
					parasol.setNumEmplacement(p);
					parasol.setFile(file);
					parasolDao.save(parasol);
				}
			}
		}
	}

	private void ajouterPays() {
		if (paysDao.count() == 0) {
			paysDao.save(new Pays("FR", "France"));
			paysDao.save(new Pays("IT", "Italie"));
			paysDao.save(new Pays("GB", "Royaume-Uni"));
			paysDao.save(new Pays("PT", "Portugal"));
		}
	}

	private void ajouterLiensDeParente() {
		if (lienDeParenteDao.count() == 0) {
			lienDeParenteDao.save(new LienDeParente("Frère/Soeur", 0.5f));
			lienDeParenteDao.save(new LienDeParente("Cousin/Cousine", 0.75f));
			lienDeParenteDao.save(new LienDeParente("Aucun", 1f));
		}
	}

	public void ajouterClients() {
		if (clientDao.count() == 0) {

			Faker faker = new Faker(new Locale("fr-FR"));
			FakeValuesService fakeValuesService = new FakeValuesService(new Locale("fr-FR"),
					new RandomService());
			Random random = new Random();

			// Partie traitement
			List<Pays> listeDePays = paysDao.findAll();
			List<LienDeParente> liens = lienDeParenteDao.findAll();

			for (int i = 0; i < 100; i++) {
				Client client = new Client();
				// On fait appel au faker pour définir le nom de l'utilisateur
				client.setNom(faker.name().lastName());
				client.setPrenom(faker.name().firstName());
				client.setEmail(fakeValuesService.letterify("?????@hb.com"));
				client.setMotDePasse(faker.internet().password(8, 12));
				Pays paysAleatoire = listeDePays.get(random.nextInt(listeDePays.size()));
				LienDeParente lienAleatoire = liens.get(random.nextInt(liens.size()));
				client.setPays(paysAleatoire);
				client.setLienDeParente(lienAleatoire);
				Date dateDebut = Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date dateFin = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				Instant instAleatoire = faker.date().between(dateDebut, dateFin).toInstant();
				client.setDateHeureInscription(LocalDateTime.ofInstant(instAleatoire, ZoneId.systemDefault()));
				// On ajoute l'objet utilisateur dans la map
				clientDao.save(client);
			}

		}
	}

	private void ajouterClientsVersionEwan(int nbClientsAAjouter) {
		if (clientDao.count() == 0) {
			Faker faker = new Faker(new Locale("fr-FR"));
			List<Pays> pays = paysDao.findAll();
			LienDeParente lienDeParenteAucun = lienDeParenteDao.findByTypeDeParente("Aucun");
			Map<String, Client> map = new HashMap<>();
			Calendar calendar = Calendar.getInstance();
			Random random = new Random();
			List<String> terminaisons = Arrays.asList ("hb.com","gmail.com","yahoo.it","hotmail.fr");
			while (map.size() != nbClientsAAjouter) {
				String prenom = faker.name().firstName();
				String nom = faker.name().lastName();
				String prenomNormalise = Normalizer.normalize(prenom.toLowerCase(), Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
				String nomNormalise = Normalizer.normalize(nom.toLowerCase(), Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
				String terminaisonAleatoire = terminaisons.get(random.nextInt(terminaisons.size()));
				String email = prenomNormalise + "." + nomNormalise + "@" + terminaisonAleatoire;
				Client client = Client.builder().nom(nom).prenom(prenom)
						.pays(pays.get(random.nextInt(pays.size()))).email(email)
						.lienDeParente(lienDeParenteAucun)
						.motDePasse(String.valueOf(random.nextInt(99999999) + 10000000)).build();
				calendar.set(2020, 1, 1);
				Date dateDebut = calendar.getTime();
				calendar = Calendar.getInstance();
				Date dateFin = calendar.getTime();
				Date dateAleatoire = faker.date().between(dateDebut, dateFin);
				calendar.setTime(dateAleatoire);
				LocalDateTime dateHeureInscription = dateAleatoire.toInstant().atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				client.setDateHeureInscription(dateHeureInscription);
				map.put(client.getEmail(), client);
			}
			clientDao.saveAll(map.values());
		}
	}

	 private void ajouterClients(int nbClientsAAjouter) {
         if (clientDao.count() == 0) {
                 List<Pays> pays = paysDao.findAll();
                 LienDeParente lienDeParenteAucun = lienDeParenteDao.findByTypeDeParente("Aucun");
                 Map<String, Client> map = new HashMap<>();
                 Random random = new Random();
                 Faker faker = new Faker(Locale.FRENCH);

                 while (map.size() != nbClientsAAjouter) {
                        Client client = Client.builder()
                         .nom(faker.name().lastName())
                         .prenom(faker.name().firstName())
                         .pays(pays.get(random.nextInt(pays.size())))
                         .email(faker.internet().emailAddress().replaceAll(" ", ""))
                         .lienDeParente(lienDeParenteAucun)
                         .motDePasse(String.valueOf(random.nextInt(99999999) + 10000000))
                         .build();
                         client.setDateHeureInscription(LocalDateTime.ofInstant(
                                 faker.date().between(
                                     Date.from(LocalDate.of(2020, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()), 
                                     Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
                                     ).toInstant(), 
                                 ZoneId.systemDefault()));
                         map.put(client.getEmail(), client);
                 }
                 clientDao.saveAll(map.values());
          }
 }
	
	private void ajouterConcessionnaire() {
		if (concessionnaireDao.count() == 0) {
			Faker faker = new Faker(new Locale("fr-FR"));
			Concessionnaire concessionnaire = new Concessionnaire();
			concessionnaire.setNom("ROSSI");
			concessionnaire.setPrenom("Giuseppe");
			concessionnaire.setEmail("peppe@humanbooster.fr");
			concessionnaire.setMotDePasse("12345678");
			concessionnaire.setNumeroDeTelephone(faker.phoneNumber().cellPhone());
			concessionnaireDao.save(concessionnaire);
		}
	}

}
