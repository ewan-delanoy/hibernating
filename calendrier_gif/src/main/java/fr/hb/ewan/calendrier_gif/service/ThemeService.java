package fr.hb.ewan.calendrier_gif.service;

import java.util.List;

import fr.hb.ewan.calendrier_gif.business.Theme;
import fr.hb.ewan.calendrier_gif.dto.ThemeDto;

public interface ThemeService {

	Theme ajouterTheme(String nom);

	Theme enregistrerTheme(ThemeDto themeDto);

	Theme enregistrerTheme(Theme theme);

	List<Theme> recupererThemes();

	Theme recupererTheme(Long id);

	Theme recupererTheme(String nomTheme);
}
