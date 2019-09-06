package ru.ralnik.httpPlayer;

import android.content.Context;

import ru.ralnik.config.myConfig;

public class HttpPlayerFactory {
    private VVVVPlayer playerCommands;
    private static volatile HttpPlayerFactory instance = null;
    private Context context;
    private myConfig cfg;

    public HttpPlayerFactory(Context context){
        super();
        this.context = context;
        cfg = new myConfig(context);
    }

    public static HttpPlayerFactory getInstance(Context context) {
        if (instance == null) {
            synchronized (HttpPlayerFactory.class){
                if (instance == null)
                    instance = new HttpPlayerFactory(context);
            }
        }
        return instance;
    }

    public VVVVPlayer getCommand(){
        if(playerCommands == null){
            playerCommands = new VVVVPlayer(cfg.getHost());
        }
        return playerCommands;
    }

}
