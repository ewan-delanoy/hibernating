package fr.hb.ewan.plages.initialisation;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import fr.hb.ewan.plages.business.Client;
import fr.hb.ewan.plages.business.File;
import fr.hb.ewan.plages.business.LienDeParente;
import fr.hb.ewan.plages.business.Parasol;
import fr.hb.ewan.plages.business.Pays;
import fr.hb.ewan.plages.dao.ClientDao;
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

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ajout données");
		ajouterFiles();
		ajouterParasols();
		ajouterPays();
		ajouterLiensDeParente();
		ajouterClients();
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
			FakeValuesService fakeValuesService = new FakeValuesService(new Locale("fr-FR"), new RandomService());
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

}
