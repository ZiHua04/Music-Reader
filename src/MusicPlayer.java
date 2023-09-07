import jmp123.PlayBack;
public class MusicPlayer {
	
	static void playmusic(String url) throws Exception{
		PlayBack player = new PlayBack(new jmp123.output.Audio());
        player.open(url,"");
        player.start(true);
	}
}
