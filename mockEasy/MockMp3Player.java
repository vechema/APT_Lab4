package mockEasy;

import java.util.ArrayList;

public class MockMp3Player implements Mp3Player{
	boolean isPlaying;
	ArrayList<String> songs;
	int currentPosition;
	
	public MockMp3Player()
	{
		isPlaying = false;
		songs = new ArrayList<String>();
		currentPosition = 0;
	}
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		isPlaying = !songs.isEmpty();
		if(isPlaying)
		{
			currentPosition+=1;
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		isPlaying = false;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		isPlaying = false;
		currentPosition = 0;
	}

	@Override
	public double currentPosition() {
		// TODO Auto-generated method stub
		return (double) currentPosition;
	}

	@Override
	public String currentSong() {
		// TODO Auto-generated method stub
		return songs.get(currentPosition);
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		if(currentPosition +1 < songs.size())
		{
			currentPosition+=1;
		}
	}

	@Override
	public void prev() {
		// TODO Auto-generated method stub
		currentPosition-=1;
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return this.isPlaying;
	}

	@Override
	public void loadSongs(ArrayList names) {
		// TODO Auto-generated method stub
		songs = new ArrayList(names);
	}

}
