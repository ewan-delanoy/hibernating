package fr.hb.ewan.calendrier_gif.service;

import java.util.List;

import fr.hb.ewan.calendrier_gif.business.Emotion;

public interface EmotionService {
	// Overloading (surcharge de méthodes)
	Emotion ajouterEmotion(String nom, String code);

	Emotion ajouterEmotion(Emotion emotion);

	List<Emotion> recupererEmotions();

	Emotion recupererEmotion(Long id);

	Emotion recupererEmotion(String nom);

	boolean supprimerEmotion(Long id);
}
