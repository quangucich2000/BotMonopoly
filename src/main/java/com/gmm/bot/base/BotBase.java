package com.gmm.bot.base;

import com.gmm.bot.model.*;
import com.gmm.bot.model.layout.ClassicLayout;
import com.gmm.bot.model.layout.Layout;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import sfs2x.client.SmartFox;
import sfs2x.client.core.BaseEvent;
import sfs2x.client.core.IEventListener;
import sfs2x.client.core.SFSEvent;
import sfs2x.client.entities.Room;
import sfs2x.client.entities.User;
import sfs2x.client.requests.ExtensionRequest;
import sfs2x.client.requests.LoginRequest;
import sfs2x.client.util.ConfigData;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static com.gmm.bot.model.Const.USER_QUICK_GAME_JOIN;

@Slf4j
@Component
@Scope("prototype")
@Getter
public class BotBase implements IEventListener {
    private final int ENEMY_PLAYER_ID = 0;
    private final int BOT_PLAYER_ID = 2;
    private final int SNAPSHOT_SIZE = 1;
    private int checkCallFunction = 0;
    private static AtomicInteger countName = new AtomicInteger();
    @Autowired
    protected ThreadPoolTaskScheduler taskScheduler;
    @Value("${smartfox.host}")
    protected String host;
    @Value("${smartfox.zone}")
    protected String zone;
    @Value("${smartfox.port}")
    protected int port;
    @Value("${rolldice.delay}")
    protected int delayRollDice;
    @Value("${find.game.delay}")
    protected int delayFindGame;
    protected SmartFox sfsClient;
    protected Room room;
    protected Player botPlayer;
    protected Player enemyPlayer;
    protected int currentPlayerId;
    protected Layout layout;
    protected Grid grid;
    protected volatile boolean isJoinGameRoom;
    protected String username;
    protected String token;
    protected SFSObject data;
    protected boolean disconnect;
    ScheduledFuture<?> schedule;

    public void connectSmartfox() {
        System.out.println("try connect to smart fox");
        try {
            this.updateStatus("init", "Initializing");
            this.init();
            this.connect();
        } catch (Exception e) {
            this.log("Init bots error =>" + e.getMessage());
        }
    }

    private void init() {
        username = "bot__" + countName.incrementAndGet();
        sfsClient = new SmartFox();
        data = new SFSObject();
        isJoinGameRoom = false;
        disconnect = false;
        this.sfsClient.addEventListener(SFSEvent.CONNECTION, this);
        this.sfsClient.addEventListener(SFSEvent.CONNECTION_LOST, this);
        this.sfsClient.addEventListener(SFSEvent.LOGIN, this);
        this.sfsClient.addEventListener(SFSEvent.LOGIN_ERROR, this);
        this.sfsClient.addEventListener(SFSEvent.ROOM_JOIN, this);
        this.sfsClient.addEventListener(SFSEvent.ROOM_JOIN_ERROR, this);
        this.sfsClient.addEventListener(SFSEvent.ROOM_VARIABLES_UPDATE, this);
        this.sfsClient.addEventListener(SFSEvent.USER_EXIT_ROOM, this);
        this.sfsClient.addEventListener(SFSEvent.USER_ENTER_ROOM, this);
        this.sfsClient.addEventListener(SFSEvent.ROOM_ADD, this);
        this.sfsClient.addEventListener(SFSEvent.ROOM_GROUP_SUBSCRIBE, this);
        this.sfsClient.addEventListener(SFSEvent.ROOM_GROUP_SUBSCRIBE_ERROR, this);
        this.sfsClient.addEventListener(SFSEvent.USER_VARIABLES_UPDATE, this);
        this.sfsClient.addEventListener(SFSEvent.EXTENSION_RESPONSE, this);

    }

    protected void connect() {
        System.out.println("bot base =>>>>>> connect");
        this.updateStatus("connecting", " => Connecting to smartfox server " + host + "|" + port + " zone: " + zone);
        ConfigData cf = new ConfigData();
        cf.setHost(host);
        cf.setPort(port);
        cf.setZone(zone);
        try {
            this.sfsClient.connect(cf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void disconnect() {
        System.out.println("bot base =>>>>>> disconnect");
        this.updateStatus("disconnect|"," manual called disconnect from client");
        try {
            sfsClient.disconnect();
            disconnect = true;
        } catch (Exception e) {
            log.error("disconnect|" + this.username + "|error =>" + e.getMessage());
        }
    }


    public void dispatch(BaseEvent event)  {
        if (event.getType().equals(SFSEvent.CONNECTION)) {
            this.onConnection(event);
        } else if (event.getType().equals(SFSEvent.CONNECTION_LOST)) {
            this.onConnectionLost(event);
        } else if (event.getType().equals(SFSEvent.LOGIN)) {
            this.onLogin(event);
        } else if (event.getType().equals(SFSEvent.LOGIN_ERROR)) {
            this.onLoginError(event);
        } else if (event.getType().equals(SFSEvent.ROOM_JOIN)) {
            this.onRoomJoin(event);
        } else if (event.getType().equals(SFSEvent.ROOM_JOIN_ERROR)) {
            this.onRoomJoinError(event);
        } else if (event.getType().equals(SFSEvent.USER_EXIT_ROOM)) {
            this.onUserExitRoom(event);
        } else {
            if (event.getType().equals(SFSEvent.EXTENSION_RESPONSE)) {
                this.onExtensionResponse(event);
            }

        }
    }

    private void getTokenLogin(){
        // TODO: call api to get token
        this.token = "abc123";
    }


    protected void onUserExitRoom(BaseEvent event) {
/*        User user = (User) event.getArguments().get("user");
        Room room = (Room) event.getArguments().get("room");
        log("onUserExitRoom|called user: " + user.getName() + " room: " + room.getName());*/
    }

    protected void onConnection(BaseEvent event) {
        System.out.println("bot base =>>>>>> try to connection");
        if (event.getArguments().get("success").equals(true)) {
            this.onConnected(event);
        } else {
            this.updateStatus("onConnection|success == false", "Failed to connect");
        }
    }


    protected void onConnected(BaseEvent event) {
        System.out.println("bot base =>>>>>> connected");
        this.updateStatus("connecting", "Connected to smartfox|" + event.getArguments().toString());
        this.login();
    }

    protected void onConnectionLost(BaseEvent event) {
        System.out.println("bot base =>>>>>> connection lost "  + event.getArguments().toString());
        this.updateStatus("onConnectionLost", "userId connection lost server: " + event.getArguments().toString());
        disconnect = true;
        sfsClient.removeAllEventListeners();
    }


    protected void onLoginError(BaseEvent event) {
        System.out.println("bot base =>>>>>> login error");
        this.updateStatus("login-error", "Login failed");
        disconnect();
    }

    protected void onRoomJoin(BaseEvent event) {
        room = (Room) event.getArguments().get("room");
        if (!room.getName().equals("loppy")) {
            updateStatus("Join-room", "Joined room " + this.sfsClient.getLastJoinedRoom().getName());
            System.out.println("bot base =>>>>>> join the room " + room.getName());
            return;
        }
        System.out.println("bot base =>>>>>> join the room " + room.getName());
        sendZoneExtensionRequest(USER_QUICK_GAME_JOIN, data);
        log("Send request Find game from lobby");
    }

    private Date getStartTime(int sizeSnapshot) {
        return new Date(System.currentTimeMillis() + (long) delayRollDice *sizeSnapshot);
    }

    protected void onRoomJoinError(BaseEvent event) {
        System.out.println("bot base =>>>>>> join room error");
        String errorMessage = (String) event.getArguments().get("errorMessage");
        log("Join room error: "+ errorMessage);
    }

    protected void onExtensionResponse(BaseEvent event) {
        String cmd = event.getArguments().containsKey("cmd") ? event.getArguments().get("cmd").toString() : "";
        SFSObject params = (SFSObject) event.getArguments().get("params");
        switch (cmd) {
            case Const.START_GAME:
                ISFSObject gameSession = params.getSFSObject("gameSession");
                startGame(gameSession, room);
                break;
            case Const.END_GAME:
                endGame();
                break;
            case Const.START_TURN:
                startTurn(params);
                break;
            case Const.ON_ROLL_DICE:
                rollDice(params);
                break;
//            case Const.ON_PLAYER_USE_SKILL:
//                handleGems(params);
//                break;
//            case Const.PLAYER_JOINED_GAME:
//                sendExtensionRequest(Const.I_AM_READY, new SFSObject());
//                break;
            case Const.SEND_ALERT:
                showError(params);
                break;
            case Const.ON_PLAYER_READY:
                showReady(params);
                break;
        }

    }

    private void showReady(SFSObject params) {
        Integer numberOfReadyPlayer = params.getInt("numberOfReadyPlayer");
        log("Number user ready: "+ numberOfReadyPlayer);
    }

    private void showError(SFSObject params) {
        String error = params.getUtfString("message");
        log(error);
    }

    protected void rollDice(SFSObject params) {
        System.out.println("bot base =>>>>>> have value dice from server listen by ->>>>>" + username);
        handleRollDice(params);
    }

    protected void handleRollDice(ISFSObject params) {
        ISFSObject gameSession = params.getSFSObject("gameSession");
        currentPlayerId = gameSession.getInt("currentPlayerId");

        //position player, value  roll dice
        System.out.println("______________________________");
        ISFSObject currentPlayer = gameSession.getSFSObject("currentPlayer");
        String currentPlayerName = currentPlayer.getUtfString("displayName");
        ISFSObject currentBlock = currentPlayer.getSFSObject("currentBlock");
        System.out.println("Player " + currentPlayerName + " on block " + currentBlock.getUtfString("name") + " at index " + currentBlock.getInt("position"));

        ISFSArray diceList = params.getSFSArray("diceList");
        for (int i = 0 ; i < diceList.size(); i ++) {
            ISFSObject dice = diceList.getSFSObject(i);
            int numDice = dice.getInt("dots");
            System.out.print("dice " + i + " have " + numDice + " dots -----");
        }
        System.out.println();
        System.out.println("______________________________");
        if (isTurn()) {
            schedule = taskScheduler.schedule(new FinishTurn(false),  getStartTime(1));
        }
    }

    protected void startTurn(ISFSObject params) {
        currentPlayerId = params.getInt("currentPlayerId");
//        System.out.println("bot base =>>>>>> startTurn " + currentPlayerId + " listen by: " + username);
        if (!isTurn()) {
            return;
        }
        taskScheduler.schedule(new SendRequestRollDice(), getStartTime(1));
    }

    private boolean isTurn() {
        int idBot = botPlayer.getId();
//        int idBot = username.charAt(username.length() - 1) - 48;
        return idBot == currentPlayerId;
    }

//    protected GemType selectGem() {
//        return botPlayer.getRecommendGemType().stream().filter(gemType -> grid.getGemTypes().contains(gemType)).findFirst().orElseGet(null);
//    }

    protected boolean isBotTurn() {
        return botPlayer.getId() == currentPlayerId;
    }

    protected void endGame() {
        isJoinGameRoom = false;
        if(schedule != null){
            schedule.cancel(false);
        }

    }

    protected void startGame(ISFSObject gameSession, Room room) {
        System.out.println("bot base =>>>>>> start the game from " + room.getName() + " listen by bot: " + username);
        // Assign Bot player & enemy player
        boolean b = assignPlayers(room);
        if(!b){
            return;
        }
        // Player
        ISFSObject objBotPlayer = gameSession.getSFSObject(botPlayer.getDisplayName());
        ISFSObject objEnemyPlayer = gameSession.getSFSObject(enemyPlayer.getDisplayName());

        // create layout
        System.out.println("bot base =>>>>>> create layout");
        layout = new ClassicLayout();
        taskScheduler.schedule(new FinishTurn(true), getStartTime(SNAPSHOT_SIZE));
        System.out.println("bot base =>>>>>> create layout success");

    }

    protected boolean assignPlayers(Room room) {
        List<User> users = room.getPlayerList();
        if(users.isEmpty()){
            updateStatus("Assign room", "error: " + room.getName());
            return false;
        }
        User user1 = users.get(0);
        System.out.println("assignPlayers " + user1.getName());
        log("id user1: " + user1.getPlayerId() + " name:"+ user1.getName());
        if(users.size() == 1){
            if (user1.isItMe()) {
                botPlayer = new Player(user1.getPlayerId(), "player1");
                enemyPlayer = new Player(ENEMY_PLAYER_ID, "player2");
            }
            return true;
        }
        User user2 = users.get(1);
        System.out.println("assignPlayers " + user2.getName());
        log("id user2: " + user2.getPlayerId()+ " name:"+user2.getName());
        if (user1.isItMe()) {
            botPlayer = new Player(user1.getPlayerId(), "player"+user1.getPlayerId());
            enemyPlayer = new Player(user2.getPlayerId(), "player"+user2.getPlayerId());
        } else {
            botPlayer = new Player(user2.getPlayerId(), "player"+user2.getPlayerId());
            enemyPlayer = new Player(user1.getPlayerId(), "player"+user1.getPlayerId());
        }
        return true;
    }

    protected void updateStatus(String status, String logMsg) {
        log.info(this.username + "|" + status + "|" + logMsg + "\n");
    }

    protected void log(String msg) {
        //log.info(this.username + "|" + msg);
    }

    protected void onLogin(BaseEvent event) {
        System.out.println("bot base =>>>>>> " + username + " login to server");
        try {
            log("onLogin()|" + event.getArguments().toString());
        } catch (Exception e) {
            log("onLogin|error => " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void login() {
        System.out.println("bot base =>>>>>> try to login");
        getTokenLogin();
        SFSObject parameters = new SFSObject();
//        parameters.putUtfString(Const.BATTLE_MODE, BattleMode.CLASSICAL.name());
        parameters.putUtfString(Const.ID_TOKEN, this.token);
        parameters.putUtfString(Const.NICK_NAME, username);
        this.sfsClient.send(new LoginRequest(username));
    }

    public void sendExtensionRequest(String extCmd, ISFSObject params) {
        this.sfsClient.send(new ExtensionRequest(extCmd, params, room));
    }

    public void sendZoneExtensionRequest(String extCmd, ISFSObject params) {
        this.sfsClient.send(new ExtensionRequest(extCmd, params));
    }

    private class FindRoomGame implements Runnable {
        @Override
        public void run() {
            sendZoneExtensionRequest(USER_QUICK_GAME_JOIN, data);
            log("Send request Find game from lobby");
        }
    }

    private class FinishTurn implements Runnable {
        private final boolean isFirstTurn;

        public FinishTurn(boolean isFirstTurn) {
            this.isFirstTurn = isFirstTurn;
        }

        @Override
        public void run() {
            System.out.println("isFirstTurn: " + isFirstTurn);
            SFSObject data = new SFSObject();
            data.putBool("isFirstTurn", isFirstTurn);
            sendExtensionRequest(Const.FINISH_TURN, data);
        }
    }

    private class SendReQuestSkill implements Runnable {
        private final Hero heroCastSkill;

        public SendReQuestSkill(Hero heroCastSkill) {
            this.heroCastSkill = heroCastSkill;
        }

        @Override
        public void run() {
            data.putUtfString("casterId", heroCastSkill.getId().toString());
            Hero target;
//            if (heroCastSkill.isHeroSelfSkill()) {
//                target = botPlayer.firstHeroAlive();
//            } else {
//                target = enemyPlayer.firstHeroAlive();
//            }
//            data.putUtfString("targetId", target.getId().toString());
//            data.putUtfString("selectedGem", String.valueOf(selectGem().getCode()));
            data.putUtfString("gemIndex", String.valueOf(ThreadLocalRandom.current().nextInt(64)));
            data.putBool("isTargetAllyOrNot",false);
            log("sendExtensionRequest() + |extCmd:" + Const.USE_SKILL + "|Hero cast skill: " + heroCastSkill.getName() + "target: ");
            sendExtensionRequest(Const.USE_SKILL, data);
        }

    }

    private class SendRequestRollDice implements Runnable {
        @Override
        public void run() {
            System.out.println("Send request roll dice from ->>" + username);
            log("sendExtensionRequest() + |extCmd:" + Const.ROLL_DICE );
            sendExtensionRequest(Const.ROLL_DICE, data);
        }
    }

    public boolean isConnectSmartFox(){
        return sfsClient.isConnected();
    }

}