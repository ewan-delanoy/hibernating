package fr.hb.ewan.plages.service;

import java.util.List;

import fr.hb.ewan.plages.business.File;

/* this is a test commit */

public interface FileService {

	File ajouterFile(File file);
	List<File> recupererFiles();
	File recupererFile(Long id);
}
