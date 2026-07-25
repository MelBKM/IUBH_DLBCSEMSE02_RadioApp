package de.iu.radioapp;

import androidx.lifecycle.ViewModel;

import de.iu.radioapp.data.AppRepository;
import de.iu.radioapp.service.ModeratorRatingService;
import de.iu.radioapp.service.PlaylistService;
import de.iu.radioapp.service.SongInfoService;
import de.iu.radioapp.service.SongRequestService;

public class ServiceViewModel extends ViewModel {
    private AppRepository appRepository;
    private SongInfoService songInfoService;
    private PlaylistService playlistService;
    private SongRequestService songRequestService;
    private ModeratorRatingService moderatorRatingService;

    public AppRepository getAppRepository() {
        return appRepository;
    }

    public SongInfoService getSongInfoService() {
        return songInfoService;
    }

    public PlaylistService getPlaylistService(){
        return playlistService;
    }

    public SongRequestService getSongRequestService() {
        return  songRequestService;
    }

    public ModeratorRatingService getModeratorRatingService() {
        return moderatorRatingService;
    }

    public void setAppRepository(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public void setSongInfoService(SongInfoService songInfoService) {
        this.songInfoService = songInfoService;
    }
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }
    public void setSongRequestService(SongRequestService songRequestService) {
        this.songRequestService = songRequestService;
    }
    public void setModeratorRatingService(ModeratorRatingService moderatorRatingService) {
        this.moderatorRatingService = moderatorRatingService;
    }

}
