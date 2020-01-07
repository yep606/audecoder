package base;

public class CDPlayer {

    CompactDisc cd;

    public CDPlayer(CompactDisc cd){
        this.cd = cd;
    }

    public CDPlayer(){

    }

    public void play(){
        cd.playSound();
    }

}
