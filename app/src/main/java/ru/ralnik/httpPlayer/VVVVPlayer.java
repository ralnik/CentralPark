package ru.ralnik.httpPlayer;

import ru.ralnik.model.Flat;

public class VVVVPlayer extends HttpPlayer {

    private int playStop = 0;
    private int volumeOnOff = 1;
    private String lastLink = "";
    private int volume = 1;
    private int volEffect = 1;
    private int numberTrack;
    private int numberSubTrack;
    private Flat flat = null;

    public VVVVPlayer(String host) {
        super(host);
        if(flat == null ) {
            flat = new Flat();
        }
    }

    @Override
    public void setVolumeOnOff(int volumeOnOff) {
        this.volumeOnOff = volumeOnOff;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public void setVolumeEffect(int volEffect) {
        this.volEffect = volEffect;
    }

    @Override
    public void play() {
        this.playStop = 1;
        super.executeCommand(getFullLink());
    }

    @Override
    public void stop() {
        this.playStop = 0;
        super.executeCommand(getFullLink());
    }

    @Override
    public void selectById(int id) {
        this.playStop = 1;
        this.numberTrack = id;
        if(id == 2){this.numberSubTrack = 0;}
        super.executeCommand(getFullLink());
    }

    @Override
    public void selectBySubId(int id) {
        this.playStop = 1;
        this.numberSubTrack = id;
        super.executeCommand(getFullLink());
    }

    @Override
    public void volume(int vol) {
        this.volume = vol;
        super.executeCommand(getFullLink());
    }

    @Override
    public void volumeEffect(int vol) {
        this.volEffect = vol;
        super.executeCommand(getFullLink());
    }

    @Override
    public void volumeOnOff() {
        volumeOnOff = (volumeOnOff == 1) ? 0: 1;
        super.executeCommand(getFullLink());
    }

    public void setFlatInfo(Flat flat){
        this.flat = flat;
    }

    public String getFullLink(){
        String currentLink = "vvvv?" +
                "track=" + this.numberTrack + "&" +
                "subTrack=" + this.numberSubTrack + "&" +
                "playStop=" + this.playStop + "&" +
                "volumeOnOff=" + this.volumeOnOff + "&" +
                "volume=" + this.volume + "&" +
                "volEffect=" + this.volEffect + "&" +
                flat.toString();

        if (this.lastLink.equals(currentLink)){
            return lastLink;
        }
        this.lastLink = currentLink;
        return currentLink;
    }
}
