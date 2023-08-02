package fr.hb.ewan.plages.initialisation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.hb.ewan.plages.business.File;
import fr.hb.ewan.plages.business.Parasol;
import fr.hb.ewan.plages.dao.FileDao;
import fr.hb.ewan.plages.dao.ParasolDao;
import lombok.AllArgsConstructor;

// Demande a Spring d'instancier cet objet et de placer cette instance dans son conteneur IOC
@Component
@AllArgsConstructor
public class AjoutDonneesInitiales implements CommandLineRunner {

	// on déclare les dépendances au sens Spring de la classe d'ajout
	// Spring va instantier ces objets à notre place
	private FileDao fileDao;
	private ParasolDao parasolDao;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ajout données");
		ajouterFiles();
		ajouterParasols();

	}

	public void ajouterFiles() {
		double prixInitial=10;
		
		if (fileDao.count() == 0) {
			for (Byte i = 1; i <= 8; i++) {
				File file = new File();
				file.setNumero(i);
				file.setPrixJournalier(prixInitial-i);
				fileDao.save(file);
			}
		}
	}

	public void ajouterParasols() {
		if (parasolDao.count() == 0) {
			for(File file:fileDao.findAll()) {
				for (byte p = 1; p <= 36; p++) {
					Parasol parasol = new Parasol();
					parasol.setNumEmplacement(p);
					parasol.setFile(file);
					parasolDao.save(parasol);
				}
			}
		}
	}

}
